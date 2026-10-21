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
  var AVAILABLE = ["en", "ar", "cs", "de", "es", "fa", "fr", "hi", "in", "it", "ja",
                   "ko", "nl", "pl", "pt", "ru", "sv", "th", "tr", "ua", "vi", "zh"];
  var NAMES = {
    en: "English", ar: "العربية", cs: "Čeština", de: "Deutsch", es: "Español",
    fa: "فارسی", fr: "Français", hi: "हिन्दी", in: "Bahasa Indonesia", it: "Italiano",
    ja: "日本語", ko: "한국어", nl: "Nederlands", pl: "Polski", pt: "Português",
    ru: "Русский", sv: "Svenska", th: "ไทย", tr: "Türkçe", ua: "Українська", vi: "Tiếng Việt", zh: "中文"
  };
  var RTL = { ar: 1, fa: 1 };
  var dict = {}, fallback = (window.__I18N_EN || {});

  function pick() {
    try { var s = localStorage.getItem("vdgoogle_lang"); if (s && AVAILABLE.indexOf(s) >= 0) return s; } catch (e) {}
    var n = (navigator.language || "en").toLowerCase();
    if (AVAILABLE.indexOf(n) >= 0) return n;
    var base = n.split("-")[0];
    if (base === "id") return "in";
    if (AVAILABLE.indexOf(base) >= 0) return base;
    return "en";
  }

  async function fetchJson(path) {
    try { var r = await fetch(path, { cache: "no-store" }); return r.ok ? await r.json() : {}; }
    catch (e) { return {}; }
  }

  async function load(lang) {
    if (lang === "en") { dict = fallback; return; }
    var d = await fetchJson("lang/" + lang + ".json");
    dict = Object.keys(d).length ? d : fallback;
  }

  function t(key, a1) {
    var s = dict[key]; if (s == null) s = fallback[key]; if (s == null) s = key;
    if (a1 != null) s = s.split("%1$s").join(a1);
    return s;
  }

  function apply(root) {
    var r = root || document;
    r.querySelectorAll("[data-i18n]").forEach(function (el) { el.textContent = t(el.getAttribute("data-i18n")); });
    r.querySelectorAll("[data-i18n-ph]").forEach(function (el) { el.setAttribute("placeholder", t(el.getAttribute("data-i18n-ph"))); });
    r.querySelectorAll("[data-i18n-title]").forEach(function (el) { el.setAttribute("title", t(el.getAttribute("data-i18n-title"))); });
  }

  window.I18N = {
    AVAILABLE: AVAILABLE,
    NAMES: NAMES,
    current: "en",
    t: t,
    apply: apply,
    isRtl: function () { return !!RTL[this.current]; },
    async init() {
      this.current = pick();
      await load(this.current);
      document.documentElement.lang = this.current;
      document.documentElement.dir = this.isRtl() ? "rtl" : "ltr";
      apply(document);
    },
    async set(lang) {
      this.current = lang;
      try { localStorage.setItem("vdgoogle_lang", lang); } catch (e) {}
      await load(lang);
      document.documentElement.lang = lang;
      document.documentElement.dir = this.isRtl() ? "rtl" : "ltr";
      apply(document);
    }
  };
  window.t = t;
})();
