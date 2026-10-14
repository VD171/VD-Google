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
package ru.vd171.vdgoogle.data

import ru.vd171.vdgoogle.root.RootRepo

private const val MSG = "/data/data/com.google.android.apps.messaging"
private const val BUGLE = "$MSG/databases/bugle_db"
private const val PREFS = "$MSG/shared_prefs"
private const val GMS = "/data/data/com.google.android.gms"
private const val TAP = "$GMS/shared_prefs/com.google.android.gms.tapandpay.service.TapAndPayServiceStorage.xml"
private const val TAP_SET = "$GMS/shared_prefs/com.google.android.gms.tapandpay.settings.TapAndPaySettingsStorage.xml"
private const val PAY_DB = "$GMS/databases/android_pay"
private const val VENDING = "/data/data/com.android.vending/shared_prefs"
private const val TELEPHONY = "/data/user_de/0/com.android.providers.telephony/databases/telephony.db"

private fun longPref(xml: String?, name: String): Long? =
    xml?.let { Regex("name=\"" + Regex.escape(name) + "\"\\s+value=\"(-?\\d+)\"").find(it)?.groupValues?.get(1)?.toLongOrNull() }

private fun boolPref(xml: String?, name: String): String? =
    xml?.let { Regex("name=\"" + Regex.escape(name) + "\"\\s+value=\"(true|false)\"").find(it)?.groupValues?.get(1) }

private fun intPref(xml: String?, name: String): String? =
    xml?.let { Regex("name=\"" + Regex.escape(name) + "\"\\s+value=\"(-?\\d+)\"").find(it)?.groupValues?.get(1) }

private fun strPref(xml: String?, name: String): String? =
    xml?.let { Regex("<string name=\"" + Regex.escape(name) + "\">([^<]*)</string>").find(it)?.groupValues?.get(1)?.takeIf { s -> s.isNotBlank() } }

private fun hasPref(xml: String?, name: String): Boolean =
    xml?.let { Regex("name=\"" + Regex.escape(name) + "\"").containsMatchIn(it) } ?: false

private fun jsonInt(xml: String?, field: String): String? =
    xml?.let { Regex("&quot;" + Regex.escape(field) + "&quot;:(-?\\d+)").find(it)?.groupValues?.get(1) }

private fun jsonStr(xml: String?, field: String): String? =
    xml?.let { Regex("&quot;" + Regex.escape(field) + "&quot;:&quot;([^&]*)&quot;").find(it)?.groupValues?.get(1)?.takeIf { s -> s.isNotBlank() } }

private val SENSITIVE_KEY = Regex("password|secret|token|user|identity|phone|iccid|msisdn|imsi|imei|cred|digest|cookie|acs", RegexOption.IGNORE_CASE)
private val SENSITIVE_VALUE = Regex("cookie|secret|token|password|credential", RegexOption.IGNORE_CASE)

private fun unescapeUnicode(s: String): String =
    if (!s.contains("\\u")) s
    else Regex("\\\\u([0-9a-fA-F]{4})").replace(s) { m -> m.groupValues[1].toInt(16).toChar().toString() }

private fun provConfig(prov: String?): Map<String, String> {
    if (prov == null) return emptyMap()
    val out = LinkedHashMap<String, String>()
    Regex("&quot;(\\w+)&quot;:(?:&quot;([^&]*)&quot;|(-?\\d+)|(true|false))").findAll(prov).forEach { m ->
        val key = m.groupValues[1]
        if (key.length < 2 || SENSITIVE_KEY.containsMatchIn(key)) return@forEach
        val raw = m.groupValues[2].ifEmpty { m.groupValues[3].ifEmpty { m.groupValues[4] } }
        if (raw.isBlank() || key in out || SENSITIVE_VALUE.containsMatchIn(raw)) return@forEach
        val value = unescapeUnicode(raw)
        out[key] = if (value.count { it.isDigit() } >= 8) "[redacted]" else value
    }
    return out
}

private fun firstCell(rows: List<String>): String? =
    rows.firstOrNull()?.substringBefore('\t')?.takeIf { it.isNotBlank() }

private fun carrierLabel(name: String?, mcc: String?, mnc: String?): String? {
    val nm = name?.trim()?.takeIf { it.isNotBlank() && !it.equals("null", true) }
    val mc = mcc?.trim()?.takeIf { it.isNotBlank() }
    val mn = mnc?.trim()?.takeIf { it.isNotBlank() }
    return when {
        nm != null && mc != null -> "$nm ($mc/$mn)"
        nm != null -> nm
        mc != null -> "$mc/$mn"
        else -> null
    }
}

private fun propCarrier(nameRaw: String?, numericRaw: String?): String? {
    val name = nameRaw?.substringBefore(',')?.trim()?.takeIf { it.isNotBlank() }
    val num = numericRaw?.substringBefore(',')?.trim()?.takeIf { it.length >= 5 }
    return carrierLabel(name, num?.take(3), num?.drop(3))
}

private fun domainCarrier(domain: String?): String? {
    val head = domain?.substringBefore(".rcs.")?.takeIf { it.isNotBlank() } ?: return null
    val token = head.substringAfterLast('-').substringAfterLast('.').takeIf { it.isNotBlank() } ?: return null
    return token.replaceFirstChar { it.uppercase() }
}

object Root {
    @Volatile private var pkgList: List<String>? = null

    suspend fun text(path: String): String? = RootRepo.readText(path)

    suspend fun glob(pattern: String): String? =
        RootRepo.exec("ls -1 $pattern 2>/dev/null")?.lineSequence()?.map { it.trim() }?.firstOrNull { it.isNotEmpty() }

    suspend fun present(pkg: String): Boolean {
        val list = pkgList ?: (RootRepo.readText("/data/system/packages.list")?.lines() ?: emptyList()).also { pkgList = it }
        return list.any { it.startsWith("$pkg ") }
    }

    suspend fun apkMap(): Map<String, String> {
        val out = HashMap<String, String>()
        val ls = RootRepo.exec("ls -d /data/app/*/base.apk /data/app/*/*/base.apk 2>/dev/null") ?: return out
        ls.lineSequence().map { it.trim() }.filter { it.endsWith("/base.apk") }.forEach { path ->
            val dir = path.removeSuffix("/base.apk").substringAfterLast('/')
            val pkg = dir.substringBefore('-')
            if (pkg.isNotEmpty() && pkg !in out) out[pkg] = path
        }
        return out
    }
}

object RcsReader {
    suspend fun load(): RcsData {
        val self = RootRepo.queryDb(BUGLE, "SELECT p.is_rcs_available, p.normalized_destination FROM self_participants s JOIN participants p ON p._id=s.participant_id")
        val selfRow = self.firstOrNull()?.split('\t')
        val cap = firstCell(RootRepo.queryDb(BUGLE, "SELECT COUNT(*) FROM participants WHERE is_rcs_available=1"))
        val msgLast = firstCell(RootRepo.queryDb(BUGLE, "SELECT MAX(max(IFNULL(received_timestamp,0), IFNULL(sent_timestamp,0))) FROM messages WHERE message_protocol=3"))?.toLongOrNull()
        val capLast = firstCell(RootRepo.queryDb(BUGLE, "SELECT MAX(last_refresh_timestamp) FROM rcs_remote_capabilities_cache"))?.toLongOrNull()
        val uce = firstCell(RootRepo.queryDb(TELEPHONY, "SELECT ims_rcs_uce_enabled FROM siminfo LIMIT 1"))
        val simCarrier = RootRepo.queryDb(TELEPHONY, "SELECT carrier_name, mcc_string, mnc_string FROM siminfo LIMIT 1").firstOrNull()?.split('\t')
        val carrierSim = simCarrier?.let { c ->
            carrierLabel(c.getOrNull(0), c.getOrNull(1), c.getOrNull(2))
        }

        val prov = Root.glob("$PREFS/provisioning_engine_state_cache_*.pref.xml")?.let { Root.text(it) }
        val bugleXml = Root.text("$PREFS/bugle.xml")
        val carrier = carrierSim
            ?: propCarrier(RootRepo.exec("getprop gsm.sim.operator.alpha"), RootRepo.exec("getprop gsm.sim.operator.numeric"))
            ?: domainCarrier(jsonStr(prov, "mDomain"))
        val entRaw = RootRepo.exec("dumpsys package com.android.imsserviceentitlement 2>/dev/null")
        val entitlement = entRaw?.let { Regex("enabled=([A-Za-z0-9]+)").find(it)?.groupValues?.get(1) }

        var flagEnabled: String? = null
        var flagUnavailable: String? = null
        Root.text("$PREFS/PhenotypePrefs.xml")?.let { x ->
            flagEnabled = Regex("RcsFlags__rcs_enabled\"\\s+value=\"(true|false)\"").find(x)?.groupValues?.get(1)
            flagUnavailable = Regex("notify_backend_rcs_is_unavailable\"\\s+value=\"(true|false)\"").find(x)?.groupValues?.get(1)
        }

        return RcsData(
            available = selfRow?.getOrNull(0)?.takeIf { it.isNotBlank() },
            selfNumber = selfRow?.getOrNull(1)?.takeIf { it.isNotBlank() },
            mVersion = jsonInt(prov, "mVersion"),
            rcsState = jsonInt(prov, "rcsState"),
            capableContacts = cap,
            flagEnabled = flagEnabled,
            flagUnavailable = flagUnavailable,
            cfgUpdSecs = jsonInt(prov, "mLastUpdateSecs")?.toLongOrNull(),
            cfgValiditySecs = jsonInt(prov, "mValiditySecs")?.toLongOrNull(),
            tokenExpMs = jsonInt(prov, "mExpirationTime")?.toLongOrNull(),
            provLastSecs = longPref(prov, "last_provisioning_attempt_time_seconds_key"),
            provNextSecs = longPref(prov, "next_provisioning_attempt_time_seconds_key"),
            msgLastMs = msgLast,
            capRefreshMs = capLast,
            fullSyncMs = longPref(bugleXml, "last_full_sync_time_millis"),
            lastSyncMs = longPref(bugleXml, "last_sync_time_millis"),
            entitlement = entitlement,
            uce = uce,
            imsPkgPresent = Root.present("com.google.android.ims"),
            carrier = carrier,
            invalidConfigReason = jsonStr(prov, "invalidConfigReason"),
            configState = jsonInt(prov, "mConfigState"),
            rcsEnabled = boolPref(bugleXml, "enable_rcs"),
            engineState = strPref(prov, "provisioning_engine_state_key"),
            provStatusPev2 = intPref(bugleXml, "rcs_provisioning_status_pev2"),
            tachyonRegistered = hasPref(bugleXml, "tachyon_registration_id"),
            provConfig = provConfig(prov),
        )
    }
}

object WalletReader {
    suspend fun load(hasNfc: Boolean): WalletData {
        val tap = Root.text(TAP)
        val tapSet = Root.text(TAP_SET)
        val failsAtt = firstCell(RootRepo.queryDb(PAY_DB, "SELECT fails_attestation FROM Wallets ORDER BY is_active_wallet DESC LIMIT 1"))
        return WalletData(
            hasNfc = hasNfc,
            walletPkgPresent = Root.present("com.google.android.apps.walletnfcrel"),
            attested = failsAtt?.let { if (it == "0") "true" else "false" } ?: boolPref(tap, "last_attestation_result"),
            attestedTimeMs = longPref(tap, "last_attestation_result_time"),
            serviceEnabled = boolPref(tap, "wallet_service_enabled"),
            tapAndPayEnabled = boolPref(tap, "tap_and_pay_enabled"),
            passwordSufficient = boolPref(tap, "was_password_sufficient"),
            hadToken = boolPref(tapSet, "device_had_token"),
        )
    }

    suspend fun resetAttestation(): Boolean =
        RootRepo.exec(
            "am force-stop com.google.android.gms; sleep 1; " +
                "sed -i '/name=\"last_attestation_result_time/d' '$TAP'",
        ) != null
}

object AttReader {
    suspend fun load(): List<AttApp> {
        val tsXml = Root.text("$VENDING/integrity_service_sharedprefs_timestamp_of_last_call.xml") ?: return emptyList()
        val cntXml = Root.text("$VENDING/integrity_service_sharedprefs_number_of_calls_in_throttling_window.xml")
        val calls = HashMap<String, String>()
        cntXml?.let {
            Regex("<long name=\"([^\"]+)\" value=\"(\\d+)\"").findAll(it).forEach { m -> calls[m.groupValues[1]] = m.groupValues[2] }
        }
        val apks = Root.apkMap()
        val out = ArrayList<AttApp>()
        Regex("<long name=\"([^\"]+)\" value=\"(\\d+)\"").findAll(tsXml).forEach { m ->
            val pkg = m.groupValues[1]
            val ts = m.groupValues[2].toLongOrNull() ?: return@forEach
            out.add(AttApp(pkg = pkg, apkPath = apks[pkg], lastCallMs = ts, calls = calls[pkg] ?: "1"))
        }
        out.sortByDescending { it.lastCallMs }
        return out
    }
}
