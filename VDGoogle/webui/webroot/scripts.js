/*
 * ======================================================================
 *  VD GOOGLE  ::  your Google services, read on-device
 *  RCS, Google Wallet and Play Integrity attestations - local, no internet.
 *
 *  Copyright (C) 2026  VD171
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 *
 *  Free software under the GNU AGPL v3 or later. NETWORK COPYLEFT: run a
 *  modified version, even as a service, and you MUST offer its source.
 *
 *  Site           : https://vd171.ru
 *  Site           : https://vd.priv8.ru
 *  Source         : https://github.com/VD171/VD-Google
 *  GitHub         : @VD171 https://github.com/VD171
 *  XDA-Developers : @VD171 https://xdaforums.com/m/vd171.4699873/
 *  Telegram       : @VD_Priv8 https://t.me/VD_Priv8
 *  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
 *  E-mail         : vd.priv8@pm.me
 * ======================================================================
 */
(function () {
  "use strict";

  var MOD = "/data/adb/modules/vdgoogle";
  var STATE = "/data/adb/vdgoogle";
  var APPVER = "";
  var cache = {};
  var curTab = "about";

  function ex(c) {
    return new Promise(function (ok, no) {
      var n = "c" + Date.now() + "_" + Math.random().toString(36).slice(2, 7);
      window[n] = function (e, o, r) { delete window[n]; e === 0 ? ok(o || "") : no(r || "err " + e); };
      if (typeof ksu !== "undefined" && ksu.exec) ksu.exec(c, "{}", n);
      else { delete window[n]; no("KSU API unavailable"); }
    });
  }

  function hasRoot() { return typeof ksu !== "undefined" && !!ksu.exec; }

  function esc(s) {
    return String(s == null ? "" : s).replace(/[&<>"']/g, function (c) {
      return { "&": "&amp;", "<": "&lt;", ">": "&gt;", "\"": "&quot;", "'": "&#39;" }[c];
    });
  }
  function pad(n) { return n < 10 ? "0" + n : "" + n; }
  function stamp() {
    var d = new Date();
    return "" + d.getFullYear() + pad(d.getMonth() + 1) + pad(d.getDate()) + "-" + pad(d.getHours()) + pad(d.getMinutes());
  }
  function kv(out) {
    var m = {};
    (out || "").split("\n").forEach(function (l) {
      var i = l.indexOf("=");
      if (i > 0) m[l.slice(0, i)] = l.slice(i + 1).trim();
    });
    return m;
  }
  function blank(v) { return v == null || v === "" || v === "null"; }
  function val(v) { return blank(v) ? null : String(v).trim(); }

  function truthy(v) {
    switch ((v == null ? "" : String(v)).trim().toLowerCase()) {
      case "1": case "true": case "yes": case "enabled": case "available": return true;
      case "0": case "false": case "no": case "disabled": return false;
      default: return null;
    }
  }

  function tsMs(ms) {
    ms = Number(ms);
    if (!ms || ms <= 0) return null;
    var d = new Date(ms);
    return d.getFullYear() + "-" + pad(d.getMonth() + 1) + "-" + pad(d.getDate()) +
      " " + pad(d.getHours()) + ":" + pad(d.getMinutes()) + ":" + pad(d.getSeconds());
  }
  function tsSecs(s) { s = Number(s); return s ? tsMs(s * 1000) : null; }

  function durationSecs(secs) {
    secs = Number(secs);
    if (!secs || secs <= 0) return null;
    var d = Math.floor(secs / 86400), h = Math.floor((secs % 86400) / 3600), m = Math.floor((secs % 3600) / 60);
    var s = "";
    if (d > 0) s += d + "d ";
    if (h > 0) s += h + "h ";
    if (m > 0 || s === "") s += m + "m";
    return s.trim();
  }

  function tzLabel() {
    var off = -new Date().getTimezoneOffset();
    var sign = off >= 0 ? "+" : "-", a = Math.abs(off);
    var gmt = "GMT" + sign + pad(Math.floor(a / 60)) + ":" + pad(a % 60);
    try {
      var parts = new Intl.DateTimeFormat(undefined, { timeZoneName: "short" }).formatToParts(new Date());
      var tz = parts.filter(function (p) { return p.type === "timeZoneName"; })[0];
      if (tz && tz.value && !/GMT|UTC/.test(tz.value) && tz.value !== gmt) return gmt + " (" + tz.value + ")";
    } catch (e) {}
    return gmt;
  }

  function carrierLabel(name, mcc, mnc) {
    var nm = val(name); if (nm && nm.toLowerCase() === "null") nm = null;
    var mc = val(mcc), mn = val(mnc);
    if (nm && mc) return nm + " (" + mc + "/" + (mn || "") + ")";
    if (nm) return nm;
    if (mc) return mc + "/" + (mn || "");
    return null;
  }
  function propCarrier(nameRaw, numericRaw) {
    var name = val(nameRaw ? nameRaw.split(",")[0] : null);
    var numS = numericRaw ? numericRaw.split(",")[0].trim() : "";
    var num = numS.length >= 5 ? numS : null;
    return carrierLabel(name, num ? num.slice(0, 3) : null, num ? num.slice(3) : null);
  }
  function domainCarrier(domain) {
    if (blank(domain)) return null;
    var head = String(domain).split(".rcs.")[0];
    if (!head) return null;
    var token = head.split("-").pop().split(".").pop();
    if (!token) return null;
    return token.charAt(0).toUpperCase() + token.slice(1);
  }

  var PRE =
    "MOD=" + MOD + "\n" +
    "ABI=$(getprop ro.product.cpu.abi)\n" +
    "case \"$ABI\" in arm64*) SQ=$MOD/bin/arm64-v8a/sqlite3;; arm*) SQ=$MOD/bin/armeabi-v7a/sqlite3;; *) SQ=$MOD/bin/arm64-v8a/sqlite3;; esac\n" +
    "Q=" + STATE + "/q\n" +
    "mkdir -p $Q\n";

  function cleanQ() { return "find $Q -name '*.db' -delete -o -name '*.db-wal' -delete 2>/dev/null\n"; }
  function copyDb(src, name) {
    return "cp " + src + " $Q/" + name + ".db 2>/dev/null; [ -f " + src + "-wal ] && cp " + src + "-wal $Q/" + name + ".db-wal 2>/dev/null\n";
  }
  function line(key, expr) { return "v=$(" + expr + "); echo \"" + key + "=$v\"\n"; }

  async function readRcs() {
    var cmd = PRE +
      "MSG=/data/data/com.google.android.apps.messaging\n" +
      "PREFS=$MSG/shared_prefs\n" +
      "PV=$(ls $PREFS/provisioning_engine_state_cache_*.pref.xml 2>/dev/null | head -1)\n" +
      "BUG=$PREFS/bugle.xml\n" +
      "PH=$PREFS/PhenotypePrefs.xml\n" +
      "TEL=/data/user_de/0/com.android.providers.telephony/databases/telephony.db\n" +
      "PKGS=/data/system/packages.list\n" +
      copyDb("$MSG/databases/bugle_db", "b") +
      copyDb("$TEL", "t") +
      line("avail", "\"$SQ\" $Q/b.db \"SELECT p.is_rcs_available FROM self_participants s JOIN participants p ON p._id=s.participant_id LIMIT 1\" 2>/dev/null") +
      line("selfnum", "\"$SQ\" $Q/b.db \"SELECT p.normalized_destination FROM self_participants s JOIN participants p ON p._id=s.participant_id LIMIT 1\" 2>/dev/null") +
      line("cap", "\"$SQ\" $Q/b.db \"SELECT COUNT(*) FROM participants WHERE is_rcs_available=1\" 2>/dev/null") +
      line("msglast", "\"$SQ\" $Q/b.db \"SELECT MAX(max(IFNULL(received_timestamp,0),IFNULL(sent_timestamp,0))) FROM messages WHERE message_protocol=3\" 2>/dev/null") +
      line("caplast", "\"$SQ\" $Q/b.db \"SELECT MAX(last_refresh_timestamp) FROM rcs_remote_capabilities_cache\" 2>/dev/null") +
      line("uce", "\"$SQ\" $Q/t.db \"SELECT ims_rcs_uce_enabled FROM siminfo LIMIT 1\" 2>/dev/null") +
      line("simname", "\"$SQ\" $Q/t.db \"SELECT carrier_name FROM siminfo LIMIT 1\" 2>/dev/null") +
      line("simmcc", "\"$SQ\" $Q/t.db \"SELECT mcc_string FROM siminfo LIMIT 1\" 2>/dev/null") +
      line("simmnc", "\"$SQ\" $Q/t.db \"SELECT mnc_string FROM siminfo LIMIT 1\" 2>/dev/null") +
      line("mversion", "grep -oE \"mVersion&quot;:-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*://\" | head -1") +
      line("rcsstate", "grep -oE \"rcsState&quot;:-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*://\" | head -1") +
      line("cfgupd", "grep -oE \"mLastUpdateSecs&quot;:-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*://\" | head -1") +
      line("cfgval", "grep -oE \"mValiditySecs&quot;:-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*://\" | head -1") +
      line("tokenexp", "grep -oE \"mExpirationTime&quot;:-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*://\" | head -1") +
      line("invalid", "grep -oE \"invalidConfigReason&quot;:&quot;[^&]*\" \"$PV\" 2>/dev/null | sed \"s/.*&quot;//\" | head -1") +
      line("engine", "grep -oE \"provisioning_engine_state_key\\\">[^<]*\" \"$PV\" 2>/dev/null | sed \"s/.*>//\" | head -1") +
      line("mdomain", "grep -oE \"mDomain&quot;:&quot;[^&]*\" \"$PV\" 2>/dev/null | sed \"s/.*&quot;//\" | head -1") +
      line("provlast", "grep -oE \"last_provisioning_attempt_time_seconds_key\\\" value=\\\"-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("provnext", "grep -oE \"next_provisioning_attempt_time_seconds_key\\\" value=\\\"-?[0-9]+\" \"$PV\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("fullsync", "grep -oE \"last_full_sync_time_millis\\\" value=\\\"-?[0-9]+\" \"$BUG\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("lastsync", "grep -oE \"last_sync_time_millis\\\" value=\\\"-?[0-9]+\" \"$BUG\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("rcsenabled", "grep -oE \"enable_rcs\\\" value=\\\"(true|false)\" \"$BUG\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("flagen", "grep -oE \"RcsFlags__rcs_enabled\\\" value=\\\"(true|false)\" \"$PH\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("flagun", "grep -oE \"notify_backend_rcs_is_unavailable\\\" value=\\\"(true|false)\" \"$PH\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("ims", "grep -c \"^com.google.android.ims \" \"$PKGS\" 2>/dev/null") +
      line("ent", "dumpsys package com.android.imsserviceentitlement 2>/dev/null | grep -oE \"enabled=[A-Za-z0-9]+\" | head -1 | cut -d= -f2") +
      line("gsmalpha", "getprop gsm.sim.operator.alpha") +
      line("gsmnum", "getprop gsm.sim.operator.numeric") +
      cleanQ();
    return kv(await ex(cmd));
  }

  async function readWallet() {
    var cmd = PRE +
      "GMS=/data/data/com.google.android.gms\n" +
      "TAP=$GMS/shared_prefs/com.google.android.gms.tapandpay.service.TapAndPayServiceStorage.xml\n" +
      "TAPSET=$GMS/shared_prefs/com.google.android.gms.tapandpay.settings.TapAndPaySettingsStorage.xml\n" +
      "PKGS=/data/system/packages.list\n" +
      line("nfc", "pm list features 2>/dev/null | grep -c \"feature:android.hardware.nfc\\$\"") +
      line("wpkg", "grep -c \"^com.google.android.apps.walletnfcrel \" \"$PKGS\" 2>/dev/null") +
      line("watt","grep -oE \"last_attestation_result\\\" value=\\\"(true|false)\" \"$TAP\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("wattt", "grep -oE \"last_attestation_result_time\\\" value=\\\"-?[0-9]+\" \"$TAP\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("wsvc", "grep -oE \"wallet_service_enabled\\\" value=\\\"(true|false)\" \"$TAP\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("wtap", "grep -oE \"tap_and_pay_enabled\\\" value=\\\"(true|false)\" \"$TAP\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("wpwd", "grep -oE \"was_password_sufficient\\\" value=\\\"(true|false)\" \"$TAP\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      line("wtok", "grep -oE \"device_had_token\\\" value=\\\"(true|false)\" \"$TAPSET\" 2>/dev/null | sed \"s/.*\\\"//\" | head -1") +
      cleanQ();
    return kv(await ex(cmd));
  }

  async function readAtt() {
    var cmd = PRE +
      "V=/data/data/com.android.vending/shared_prefs\n" +
      "DEV=/data/data/com.android.vending/files/finsky/shared/device_verification_valuestore.pb\n" +
      "DG=/data/data/com.google.android.gms/databases/dg.db\n" +
      "cp $DG $Q/dg.db 2>/dev/null; [ -f $DG-wal ] && cp $DG-wal $Q/dg.db-wal 2>/dev/null\n" +
      "echo '---TS---'\n" +
      "cat $V/integrity_service_sharedprefs_timestamp_of_last_call.xml 2>/dev/null\n" +
      "echo '---CNT---'\n" +
      "cat $V/integrity_service_sharedprefs_number_of_calls_in_throttling_window.xml 2>/dev/null\n" +
      "echo '---CERT---'\n" +
      "od -An -tu1 -N2 \"$DEV\" 2>/dev/null\n" +
      "echo '---DG---'\n" +
      "\"$SQ\" $Q/dg.db \"SELECT a, b, h FROM main ORDER BY b DESC\" 2>/dev/null\n" +
      cleanQ();
    var out = await ex(cmd);
    var dgPart = out.split("---DG---")[1] || "";
    var beforeDg = out.split("---DG---")[0] || "";
    var certPart = beforeDg.split("---CERT---")[1] || "";
    var head = beforeDg.split("---CERT---")[0] || "";
    var parts = head.split("---CNT---");
    var tsBlock = (parts[0] || "").split("---TS---").pop();
    var cntBlock = parts[1] || "";
    var counts = {};
    (cntBlock.match(/<long name="([^"]+)" value="(\d+)"/g) || []).forEach(function (m) {
      var g = /<long name="([^"]+)" value="(\d+)"/.exec(m);
      if (g) counts[g[1]] = g[2];
    });
    var apps = [];
    (tsBlock.match(/<long name="([^"]+)" value="(\d+)"/g) || []).forEach(function (m) {
      var g = /<long name="([^"]+)" value="(\d+)"/.exec(m);
      if (g) apps.push({ pkg: g[1], ts: Number(g[2]), calls: counts[g[1]] || "1" });
    });
    apps.sort(function (a, b) { return b.ts - a.ts; });
    var ct = certPart.trim().split(/\s+/).filter(Boolean);
    var certified = (ct.length >= 2 && ct[0] === "8") ? (ct[1] === "1" ? "true" : "false") : null;
    var dg = [], dgSeen = {};
    dgPart.split("\n").forEach(function (l) {
      l = l.trim(); if (!l) return;
      var p = l.split("|");
      if (p.length < 3) return;
      var h = Number(p[p.length - 1]), b = Number(p[p.length - 2]);
      var flow = p.slice(0, p.length - 2).join("|").split("/")[0];
      if (flow && !dgSeen[flow]) { dgSeen[flow] = 1; dg.push({ flow: flow, issued: b, expiry: h }); }
    });
    return { apps: apps, certified: certified, droidGuard: dg };
  }

  function rcsHealth(d) {
    var enabled = truthy(d.rcsenabled);
    var version = parseInt(d.mversion, 10) || 0;
    if (enabled === false) return "off";
    if (version > 0) return "connected";
    if (d.engine === "ConfiguredState" || enabled === true) return "setup";
    return "unknown";
  }

  function toneCls(t) { return t ? " " + t : ""; }

  function row(label, value, tone, meaning) {
    var v = blank(value) ? "<span class=\"muted\">-</span>" : esc(value);
    var vc = blank(value) ? "" : toneCls(tone);
    return "<div class=\"row\"><div class=\"k\"><div class=\"label\">" + esc(label) + "</div>" +
      (meaning ? "<div class=\"meaning\">" + esc(meaning) + "</div>" : "") +
      "</div><div class=\"v" + vc + "\">" + v + "</div></div>";
  }

  function boolRow(label, raw, meaning, trueGood) {
    if (trueGood === undefined) trueGood = true;
    var b = truthy(raw);
    var disp = b === true ? t("v_yes") : b === false ? t("v_no") : (blank(raw) ? t("v_unknown") : raw);
    var tone = b === null ? "" : (b === trueGood ? "good" : "bad");
    return row(label, disp, tone, meaning);
  }

  function imsRow(label, raw, meaning, conn) {
    if (!conn) return boolRow(label, raw, meaning);
    var b = truthy(raw);
    return row(label, b === true ? t("v_yes") : b === false ? t("v_no") : val(raw), "", meaning);
  }

  function strip(chips) {
    return "<div class=\"strip\">" + chips.map(function (c) {
      return "<span class=\"chip\"><span class=\"dot " + c[1] + "\"></span>" + esc(c[0]) + "</span>";
    }).join("") + "</div>";
  }

  function tzFoot() { return "<div class=\"tzfoot\">" + esc(t("tz_footer").split("%1$s").join(tzLabel())) + "</div>"; }

  var revealed = false;

  function renderRcs(d) {
    var health = rcsHealth(d);
    var hWord = health === "connected" ? t("rcs_st_connected") : health === "off" ? t("rcs_st_off") : t("rcs_st_setup");
    var hTone = health === "connected" ? "good" : health === "off" ? "bad" : "warn";

    var carrier = carrierLabel(d.simname, d.simmcc, d.simmnc) ||
      propCarrier(d.gsmalpha, d.gsmnum) || domainCarrier(d.mdomain);

    var conn = health === "connected";
    var h = strip([[t("rcs_chip_active"), hTone], [t("rcs_chip_backend"), (truthy(d.ims) || Number(d.ims) > 0 || conn) ? "good" : "warn"]]);

    if (health !== "connected" && d.mversion === "0") {
      h += "<div class=\"callout\">" + esc(t("rcs_callout_mversion_zero")) + "</div>";
    }

    h += "<div class=\"seclabel\">" + esc(t("rcs_sec_status")) + "</div><div class=\"card\">";
    h += row(t("rcs_connected"), hWord, hTone, t("rcs_connected_m"));
    h += row(t("rcs_carrier"), carrier, "", t("rcs_carrier_m"));
    var av = truthy(d.avail);
    h += row(t("rcs_available"), av === true ? t("v_yes") : av === false ? t("v_no") : val(d.avail), "", t("rcs_available_m"));
    if (!blank(d.selfnum)) {
      var num = String(d.selfnum);
      var masked = "•••• " + num.slice(-4);
      h += "<div class=\"row\"><div class=\"k\"><div class=\"label\">" + esc(t("rcs_self_number")) +
        "</div><div class=\"meaning\">" + esc(t("rcs_self_number_m")) + "</div></div><div class=\"v\">" +
        "<span id=\"selfnum\">" + esc(revealed ? num : masked) + "</span> " +
        "<button class=\"reveal\" data-act=\"reveal\">" + esc(revealed ? t("action_hide") : t("action_reveal")) + "</button></div></div>";
    }
    h += row(t("rcs_state"), val(d.rcsstate), "", t("rcs_state_m"));
    h += row(t("rcs_cfg_version"), val(d.mversion), d.mversion === "0" ? "bad" : !blank(d.mversion) ? "good" : "", t("rcs_cfg_version_m"));
    var invalid = val(d.invalid);
    if (invalid && !/UNSPECIFIED$/.test(invalid)) {
      h += row(t("rcs_invalid_reason"), invalid.replace(/^INVALID_CONFIG_REASON_/, ""), "warn", t("rcs_invalid_reason_m"));
    }
    h += row(t("rcs_capable"), val(d.cap), "", t("rcs_capable_m"));
    h += imsRow(t("rcs_flag_enabled"), d.flagen, t("rcs_flag_enabled_m"), conn);
    if (health !== "connected") h += boolRow(t("rcs_flag_unavailable"), d.flagun, t("rcs_flag_unavailable_m"), false);
    h += row(t("rcs_entitlement"), val(d.ent), "", t("rcs_entitlement_m"));
    h += imsRow(t("rcs_uce"), d.uce, t("rcs_uce_m"), conn);
    h += imsRow(t("rcs_ims_pkg"), Number(d.ims) > 0 ? "true" : "false", t("rcs_ims_pkg_m"), conn);
    h += "</div>";

    h += "<div class=\"seclabel\">" + esc(t("rcs_sec_times")) + "</div><div class=\"card\">";
    h += row(t("rcs_cfg_updated"), tsSecs(d.cfgupd), "", t("rcs_cfg_updated_m"));
    h += row(t("rcs_cfg_validity"), durationSecs(d.cfgval), "", t("rcs_cfg_validity_m"));
    h += row(t("rcs_token_exp"), tsMs(d.tokenexp), "", t("rcs_token_exp_m"));
    h += row(t("rcs_prov_last"), tsSecs(d.provlast), "", t("rcs_prov_last_m"));
    h += row(t("rcs_prov_next"), tsSecs(d.provnext), "", t("rcs_prov_next_m"));
    h += row(t("rcs_msg_last"), tsMs(d.msglast), "", t("rcs_msg_last_m"));
    h += row(t("rcs_cap_refresh"), tsMs(d.caplast), "", t("rcs_cap_refresh_m"));
    h += row(t("rcs_full_sync"), tsMs(d.fullsync), "", t("rcs_full_sync_m"));
    h += row(t("rcs_last_sync"), tsMs(d.lastsync), "", t("rcs_last_sync_m"));
    h += "</div>" + tzFoot();
    return h;
  }

  function renderWallet(d) {
    var watt = val(d.watt);
    var attested = watt;
    var at = truthy(attested);
    var attTone = at === true ? "good" : at === false ? "bad" : "";
    var nfc = Number(d.nfc) > 0;

    var h = "<div class=\"toprow\">" +
      strip([[t("wallet_chip_attested"), attTone], [t("wallet_chip_nfc"), nfc ? "good" : "warn"]]) +
      "<button class=\"btn\" data-act=\"reset\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M23 4v6h-6\"></path><path d=\"M20.49 15a9 9 0 1 1-2.12-9.36L23 10\"></path></svg>" + esc(t("wallet_reset_confirm")) + "</button>" +
      "<button class=\"iconbtn\" data-act=\"wallet-info\" title=\"" + esc(t("action_info")) + "\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><circle cx=\"12\" cy=\"12\" r=\"10\"></circle><path d=\"M12 16v-4M12 8h.01\"></path></svg></button>" +
      "</div>";

    h += "<div class=\"seclabel\">" + esc(t("wallet_sec_device")) + "</div><div class=\"card\">";
    h += boolRow(t("wallet_nfc"), nfc ? "true" : "false", t("wallet_nfc_m"));
    h += boolRow(t("wallet_pkg"), Number(d.wpkg) > 0 ? "true" : "false", t("wallet_pkg_m"));
    h += "</div>";

    h += "<div class=\"seclabel\">" + esc(t("wallet_sec_att")) + "</div><div class=\"card\">";
    h += boolRow(t("wallet_attested"), attested, t("wallet_attested_m"));
    h += row(t("wallet_attested_time"), tsMs(d.wattt), "", t("wallet_attested_time_m"));
    h += boolRow(t("wallet_service"), d.wsvc, t("wallet_service_m"));
    h += boolRow(t("wallet_tapandpay"), d.wtap, t("wallet_tapandpay_m"));
    h += boolRow(t("wallet_password"), d.wpwd, t("wallet_password_m"));
    h += boolRow(t("wallet_token"), d.wtok, t("wallet_token_m"));
    h += "</div>" + tzFoot();
    return h;
  }

  var attQuery = "";

  function renderDg(data) {
    var h = "<div class=\"seclabel\">" + esc(t("att_sec_cert")) + "</div><div class=\"card\">" +
      boolRow(t("att_cert"), data.certified, t("att_cert_m")) + "</div>";
    if (data.droidGuard && data.droidGuard.length) {
      h += "<div class=\"seclabel\">" + esc(t("att_sec_dg")) + "</div>";
      h += "<div class=\"note\">" + esc(t("att_dg_intro")) + "</div>";
      h += "<div class=\"card\">";
      data.droidGuard.forEach(function (f) {
        h += row(f.flow, tsSecs(f.expiry), "", t("att_dg_valid").split("%1$s").join(durationSecs(f.expiry - f.issued) || "-"));
      });
      h += "</div>";
    }
    return h + tzFoot();
  }

  function renderAtt(data) {
    var apps = data.apps;
    var h = "<div class=\"toprow\"><div class=\"seclabel\" style=\"margin:6px 4px\">" + esc(t("att_sec_apps")) + "</div></div>";
    if (!apps.length) { return h + "<div class=\"note\">" + esc(t("att_empty")) + "</div>"; }
    h += "<div class=\"note\">" + esc(t("att_intro")) + "</div>";
    h += "<div class=\"search\"><span class=\"lead\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><circle cx=\"11\" cy=\"11\" r=\"8\"></circle><path d=\"M21 21l-4.35-4.35\"></path></svg></span>" +
      "<input id=\"attq\" type=\"text\" placeholder=\"" + esc(t("att_search")) + "\" value=\"" + esc(attQuery) + "\">" +
      "<button class=\"clear\" data-act=\"att-clear\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M18 6L6 18M6 6l12 12\"></path></svg></button></div>";
    h += "<div id=\"attlist\">" + attList(apps) + "</div>" + tzFoot();
    return h;
  }

  function attList(apps) {
    var q = attQuery.trim().toLowerCase();
    var shown = q ? apps.filter(function (a) { return a.pkg.toLowerCase().indexOf(q) >= 0; }) : apps;
    return shown.map(function (a) {
      return "<div class=\"appcard\"><div class=\"glyph\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><rect x=\"4\" y=\"2\" width=\"16\" height=\"20\" rx=\"3\"></rect><path d=\"M10 18h4\"></path></svg></div>" +
        "<div style=\"flex:1 1 auto; min-width:0\"><div class=\"pkg\">" + esc(a.pkg) + "</div>" +
        "<div class=\"meta\">" + esc(t("att_last_call").split("%1$s").join(tsMs(a.ts) || "-")) + "</div>" +
        "<div class=\"meta\">" + esc(t("att_calls").split("%1$s").join(a.calls)) + "</div></div></div>";
    }).join("");
  }

  function aboutRow(ic, label, value, url) {
    var cls = url ? "aboutrow link" : "aboutrow";
    var inner = "<span class=\"ic\">" + ic + "</span><div style=\"flex:1 1 auto; min-width:0\">" +
      (label ? "<div class=\"lbl\">" + esc(label) + "</div>" : "") +
      "<div class=\"val\">" + esc(value) + "</div></div>";
    return url
      ? "<div class=\"" + cls + "\" data-open=\"" + esc(url) + "\">" + inner + "</div>"
      : "<div class=\"" + cls + "\">" + inner + "</div>";
  }

  var IC = {
    lock: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><rect x=\"3\" y=\"11\" width=\"18\" height=\"11\" rx=\"2\"></rect><path d=\"M7 11V7a5 5 0 0 1 10 0v4\"></path></svg>",
    person: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2\"></path><circle cx=\"12\" cy=\"7\" r=\"4\"></circle></svg>",
    code: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M16 18l6-6-6-6M8 6l-6 6 6 6\"></path></svg>",
    gavel: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M14 13l-7.5 7.5a2.12 2.12 0 0 1-3-3L11 10M12.8 7.2l4 4M19 11l-4-4M15 3l6 6\"></path></svg>",
    globe: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><circle cx=\"12\" cy=\"12\" r=\"10\"></circle><path d=\"M2 12h20M12 2a15 15 0 0 1 0 20 15 15 0 0 1 0-20\"></path></svg>",
    send: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M22 2L11 13M22 2l-7 20-4-9-9-4 20-7z\"></path></svg>",
    chat: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z\"></path></svg>",
    mail: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><rect x=\"2\" y=\"4\" width=\"20\" height=\"16\" rx=\"2\"></rect><path d=\"M22 6l-10 7L2 6\"></path></svg>",
    forum: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M21 11.5a8.38 8.38 0 0 1-9 8.5 8.5 8.5 0 0 1-3.8-.9L3 21l1.9-5.2A8.5 8.5 0 0 1 12 3a8.38 8.38 0 0 1 9 8.5z\"></path></svg>",
    link: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M10 13a5 5 0 0 0 7 0l3-3a5 5 0 0 0-7-7l-1 1\"></path><path d=\"M14 11a5 5 0 0 0-7 0l-3 3a5 5 0 0 0 7 7l1-1\"></path></svg>",
    open: "<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><path d=\"M18 13v6a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h6\"></path><path d=\"M15 3h6v6M10 14L21 3\"></path></svg>"
  };

  function renderAbout() {
    var opts = "<option value=\"\">" + esc(t("lang_system")) + "</option>";
    I18N.AVAILABLE.forEach(function (c) {
      var sel = (I18N.current === c && localStorageLang() === c) ? " selected" : "";
      opts += "<option value=\"" + c + "\"" + sel + ">" + esc(I18N.NAMES[c]) + "</option>";
    });

    var h = "<div class=\"callout\" style=\"background:var(--secondary-container); color:var(--on-secondary-container); border-left-color:var(--primary)\"><span class=\"ic\" style=\"flex:0 0 auto\">" + IC.lock + "</span><span>" + esc(t("about_no_internet")) + "</span></div>";

    h += "<div class=\"seclabel\">" + esc(t("action_language")) + "</div><div class=\"card\"><div class=\"row\"><div class=\"k\"><div class=\"label\">" + esc(t("lang_title")) + "</div></div><div class=\"v\"><select id=\"langsel\" style=\"font-family:inherit; font-size:14px; background:var(--surface-variant); color:var(--on-surface); border:none; border-radius:8px; padding:6px 8px\">" + opts + "</select></div></div></div>";

    h += "<div class=\"seclabel\">" + esc(t("about_project")) + "</div><div class=\"card\">";
    h += aboutRow(IC.person, t("about_developer"), "VD171", "https://github.com/VD171");
    h += aboutRow(IC.code, t("about_source"), "github.com/VD171/VD-Google", "https://github.com/VD171/VD-Google");
    h += aboutRow(IC.gavel, t("about_license"), "GNU AGPL-3.0-or-later", null);
    h += "</div>";

    h += "<div class=\"seclabel\">" + esc(t("about_contacts")) + "</div><div class=\"card\">";
    h += aboutRow(IC.globe, null, "https://vd171.ru", "https://vd171.ru");
    h += aboutRow(IC.globe, null, "https://vd.priv8.ru", "https://vd.priv8.ru");
    h += aboutRow(IC.send, "Telegram", "@VD_Priv8", "https://t.me/VD_Priv8");
    h += aboutRow(IC.chat, "Discord", "@VD.Priv8", "https://discord.com/users/1296831918989639721");
    h += aboutRow(IC.mail, "E-mail", "vd.priv8@pm.me", "mailto:vd.priv8@pm.me");
    h += aboutRow(IC.forum, "XDA", "@VD171", "https://xdaforums.com/m/vd171.4699873/");
    h += aboutRow(IC.forum, "XDA thread", "VD-Google", "https://xdaforums.com/t/VD-Google.4803837/");
    h += aboutRow(IC.link, "GitHub", "@VD171", "https://github.com/VD171");
    h += "</div>";

    h += "<div class=\"seclabel\">" + esc(t("about_support")) + "</div><div class=\"card\">";
    h += aboutRow(IC.open, null, "github.com/VD171/VD-Google/releases", "https://github.com/VD171/VD-Google/releases");
    h += aboutRow(IC.open, null, "@RootDetected on Telegram", "https://t.me/RootDetected");
    h += aboutRow(IC.open, null, "@BlankAssistance on Telegram", "https://t.me/BlankAssistance");
    h += "</div>";
    return h;
  }

  function localStorageLang() { try { return localStorage.getItem("vdgoogle_lang") || ""; } catch (e) { return ""; } }

  var view = document.getElementById("view");

  function spinner() { view.innerHTML = "<div class=\"spin\"><div></div></div>"; }

  async function loadTab(tab, force) {
    curTab = tab;
    document.querySelectorAll(".tab").forEach(function (b) {
      b.classList.toggle("active", b.getAttribute("data-tab") === tab);
    });
    if (tab === "about") { view.innerHTML = renderAbout(); return; }
    if (!hasRoot()) { renderNoRoot(); return; }
    var cacheKey = tab === "droidguard" ? "att" : tab;
    if (!force && cache[cacheKey]) { paint(tab); return; }
    spinner();
    try {
      if (tab === "rcs") cache.rcs = await readRcs();
      else if (tab === "wallet") cache.wallet = await readWallet();
      else if (tab === "att" || tab === "droidguard") cache.att = await readAtt();
    } catch (e) {
      view.innerHTML = "<div class=\"note\">" + esc(String(e)) + "</div>";
      return;
    }
    paint(tab);
  }

  function paint(tab) {
    if (tab === "rcs") view.innerHTML = renderRcs(cache.rcs);
    else if (tab === "wallet") view.innerHTML = renderWallet(cache.wallet);
    else if (tab === "att") view.innerHTML = renderAtt(cache.att);
    else if (tab === "droidguard") view.innerHTML = renderDg(cache.att);
  }

  function renderNoRoot() {
    view.innerHTML = "<div class=\"seclabel\">" + esc(t("root_needed_title")) + "</div>" +
      "<div class=\"card\"><div class=\"row\"><div class=\"k\"><div class=\"meaning\">" + esc(t("root_needed_body")) +
      "</div></div></div></div>" +
      "<div class=\"note\">" + esc(t("root_needed_su")) + "</div>" +
      "<div style=\"margin:14px 4px\"><button class=\"btn\" data-act=\"retry\">" + esc(t("root_retry")) + "</button></div>";
  }

  function toast(msg) {
    var el = document.getElementById("toast");
    el.textContent = msg; el.classList.add("show");
    setTimeout(function () { el.classList.remove("show"); }, 2600);
  }

  function confirmDialog(title, msg, yes, no, onYes) {
    var ov = document.getElementById("confirm-ov");
    document.getElementById("confirm-title").textContent = title;
    document.getElementById("confirm-msg").textContent = msg;
    var y = document.getElementById("confirm-yes"), n = document.getElementById("confirm-no");
    y.textContent = yes; n.textContent = no;
    function close() { ov.classList.remove("show"); y.onclick = null; n.onclick = null; }
    y.onclick = function () { close(); onYes(); };
    n.onclick = close;
    ov.classList.add("show");
  }

  function infoDialog(title, msg) {
    var ov = document.getElementById("info-ov");
    document.getElementById("info-title").textContent = title;
    document.getElementById("info-msg").textContent = msg;
    var c = document.getElementById("info-close");
    c.textContent = t("action_close");
    c.onclick = function () { ov.classList.remove("show"); };
    ov.classList.add("show");
  }

  async function logAction(action, ok) {
    var entry = JSON.stringify({ time: tsMs(Date.now()), action: action, ok: ok, appVersion: APPVER });
    try {
      await ex("mkdir -p " + STATE + " && printf '%s\\n' '" + entry + "' >> " + STATE + "/actions.jsonl" +
        " && tail -n 200 " + STATE + "/actions.jsonl > " + STATE + "/actions.jsonl.tmp && mv -f " + STATE + "/actions.jsonl.tmp " + STATE + "/actions.jsonl");
    } catch (e) {}
  }

  async function doExport() {
    try {
      if (!cache.rcs) cache.rcs = await readRcs();
      if (!cache.wallet) cache.wallet = await readWallet();
      var dev = kv(await ex(
        "v=$(getprop ro.product.model); echo \"model=$v\"\n" +
        "v=$(getprop ro.product.brand); echo \"brand=$v\"\n" +
        "v=$(getprop ro.build.version.release); echo \"android=$v\"\n" +
        "v=$(getprop ro.build.version.sdk); echo \"sdk=$v\"\n"));
      var r = cache.rcs, w = cache.wallet;
      var carrier = carrierLabel(r.simname, r.simmcc, r.simmnc) || propCarrier(r.gsmalpha, r.gsmnum) || domainCarrier(r.mdomain);
      var obj = {
        app: "VD Google (WebUI)",
        version: APPVER,
        exportedAt: tsMs(Date.now()),
        timezone: tzLabel(),
        device: { model: val(dev.model), brand: val(dev.brand), android: val(dev.android), sdk: val(dev.sdk) },
        rcs: {
          connected: rcsHealth(r),
          available: val(r.avail),
          carrier: carrier,
          configVersion: val(r.mversion),
          rcsState: val(r.rcsstate),
          rcsEnabled: val(r.rcsenabled),
          engineState: val(r.engine),
          featureEnabled: val(r.flagen),
          markedUnavailable: val(r.flagun),
          carrierEntitlement: val(r.ent),
          uce: val(r.uce),
          imsPresent: Number(r.ims) > 0,
          capableContacts: val(r.cap),
          invalidConfigReason: val(r.invalid),
          domain: val(r.mdomain),
          configUpdated: tsSecs(r.cfgupd),
          configValidity: durationSecs(r.cfgval),
          tokenExpiry: tsMs(r.tokenexp),
          lastProvisioning: tsSecs(r.provlast),
          nextProvisioning: tsSecs(r.provnext),
          lastRcsMessage: tsMs(r.msglast),
          capabilitiesRefreshed: tsMs(r.caplast),
          fullSync: tsMs(r.fullsync),
          lastSync: tsMs(r.lastsync)
        },
        wallet: {
          attested: truthy(val(w.watt)),
          lastChecked: tsMs(w.wattt),
          nfc: Number(w.nfc) > 0,
          walletInstalled: Number(w.wpkg) > 0,
          walletService: val(w.wsvc),
          tapAndPay: val(w.wtap),
          screenLockAccepted: val(w.wpwd),
          cardEverAdded: val(w.wtok)
        }
      };
      var name = "vdgoogle-" + stamp() + ".json";
      var b64 = btoa(unescape(encodeURIComponent(JSON.stringify(obj, null, 2))));
      await ex("mkdir -p /sdcard/Download/VDGoogle && printf '%s' '" + b64 + "' | base64 -d > '/sdcard/Download/VDGoogle/" + name + "'");
      await logAction("export_json", true);
      toast("/sdcard/Download/VDGoogle/" + name);
    } catch (e) { toast(String(e)); }
  }

  async function resetWallet() {
    var ok = false;
    var tap = "/data/data/com.google.android.gms/shared_prefs/com.google.android.gms.tapandpay.service.TapAndPayServiceStorage.xml";
    try {
      await ex("ctx=$(stat -c %C '" + tap + "' 2>/dev/null); " +
        "am force-stop com.google.android.gms; sleep 1; " +
        "sed -i '/name=\"last_attestation_result_time/d' '" + tap + "'; " +
        "[ -n \"$ctx\" ] && chcon \"$ctx\" '" + tap + "'; true");
      ok = true;
    } catch (e) {}
    await logAction("clear_wallet_last_checked", ok);
    cache.wallet = null;
    await loadTab("wallet", true);
  }

  function applyChrome() {
    I18N.apply(document);
    document.getElementById("refresh").setAttribute("title", t("action_reload"));
    document.getElementById("export").setAttribute("title", t("action_export"));
    document.getElementById("theme").setAttribute("title", t("action_theme"));
  }

  function setTheme(mode) {
    try {
      if (mode) { document.documentElement.setAttribute("data-theme", mode); localStorage.setItem("vdgoogle_theme", mode); }
      else { document.documentElement.removeAttribute("data-theme"); localStorage.removeItem("vdgoogle_theme"); }
    } catch (e) {}
  }
  function currentTheme() {
    var m = document.documentElement.getAttribute("data-theme");
    if (m) return m;
    return window.matchMedia && window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light";
  }

  document.getElementById("tabs").addEventListener("click", function (e) {
    var b = e.target.closest(".tab");
    if (b) loadTab(b.getAttribute("data-tab"), false);
  });

  document.getElementById("refresh").addEventListener("click", function () {
    this.classList.remove("spinning"); void this.offsetWidth; this.classList.add("spinning");
    var b = this; setTimeout(function () { b.classList.remove("spinning"); }, 1000);
    if (curTab === "about") return;
    cache[curTab] = null;
    loadTab(curTab, true);
  });

  document.getElementById("export").addEventListener("click", function () {
    if (hasRoot()) doExport();
  });

  document.getElementById("theme").addEventListener("click", function () {
    setTheme(currentTheme() === "dark" ? "light" : "dark");
  });

  view.addEventListener("click", function (e) {
    var open = e.target.closest("[data-open]");
    if (open) { ex("am start -a android.intent.action.VIEW -d '" + open.getAttribute("data-open").replace(/'/g, "") + "'").catch(function () {}); return; }
    var act = e.target.closest("[data-act]");
    if (!act) return;
    var a = act.getAttribute("data-act");
    if (a === "retry") { loadTab(curTab, true); }
    else if (a === "reveal") { revealed = !revealed; paint("rcs"); }
    else if (a === "reset") {
      confirmDialog(t("wallet_reset_btn"), t("wallet_reset_msg"), t("wallet_reset_confirm"), t("wallet_reset_cancel"), resetWallet);
    }
    else if (a === "wallet-info") { infoDialog(t("wallet_reset_btn"), t("wallet_reset_info")); }
    else if (a === "att-clear") { attQuery = ""; var inp = document.getElementById("attq"); if (inp) inp.value = ""; document.getElementById("attlist").innerHTML = attList((cache.att && cache.att.apps) || []); }
  });

  view.addEventListener("input", function (e) {
    if (e.target.id === "attq") {
      attQuery = e.target.value;
      var list = document.getElementById("attlist");
      if (list) list.innerHTML = attList((cache.att && cache.att.apps) || []);
    }
  });

  view.addEventListener("change", function (e) {
    if (e.target.id === "langsel") {
      I18N.set(e.target.value || pickAndClear()).then(function () { applyChrome(); loadTab("about", false); });
    }
  });

  function pickAndClear() {
    try { localStorage.removeItem("vdgoogle_lang"); } catch (e) {}
    var n = (navigator.language || "en").toLowerCase();
    if (I18N.AVAILABLE.indexOf(n) >= 0) return n;
    var base = n.split("-")[0];
    if (base === "id") return "in";
    return I18N.AVAILABLE.indexOf(base) >= 0 ? base : "en";
  }

  (async function init() {
    try { var th = localStorage.getItem("vdgoogle_theme"); if (th) document.documentElement.setAttribute("data-theme", th); } catch (e) {}
    await I18N.init();
    applyChrome();
    if (hasRoot()) {
      ex("grep '^version=' " + MOD + "/module.prop 2>/dev/null | cut -d= -f2")
        .then(function (v) { v = (v || "").trim(); if (v) { document.getElementById("ver").textContent = v; APPVER = v.replace(/^v/, ""); } })
        .catch(function () {});
    }
    loadTab("about", false);
  })();
})();
