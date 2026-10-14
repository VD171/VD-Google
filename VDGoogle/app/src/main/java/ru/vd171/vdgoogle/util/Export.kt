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
package ru.vd171.vdgoogle.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import org.json.JSONObject
import ru.vd171.vdgoogle.BuildConfig
import ru.vd171.vdgoogle.data.RcsData
import ru.vd171.vdgoogle.data.WalletData

object Export {

    private fun jb(raw: String?): Any = when (raw?.trim()?.lowercase()) {
        "1", "true", "yes", "enabled", "available" -> true
        "0", "false", "no", "disabled" -> false
        null, "" -> JSONObject.NULL
        else -> raw
    }

    private fun jn(raw: String?): Any =
        raw?.trim()?.toLongOrNull() ?: (raw?.takeIf { it.isNotBlank() } ?: JSONObject.NULL)

    private fun js(raw: String?): Any = raw?.takeIf { it.isNotBlank() } ?: JSONObject.NULL

    private fun jauto(v: String): Any = when (v) {
        "true" -> true
        "false" -> false
        else -> v.toLongOrNull() ?: v
    }

    fun buildReport(rcs: RcsData?, wallet: WalletData?): String {
        val root = JSONObject()
        root.put("app", "VD Google")
        root.put("version", BuildConfig.VERSION_NAME)
        root.put(
            "device",
            JSONObject()
                .put("manufacturer", Build.MANUFACTURER)
                .put("model", Build.MODEL)
                .put("androidRelease", Build.VERSION.RELEASE)
                .put("sdk", Build.VERSION.SDK_INT),
        )
        root.put("timezone", Fmt.timezoneLabel())

        rcs?.let {
            val r = JSONObject()
            r.put("carrier", js(it.carrier))
            r.put("connected", it.health.name.lowercase())
            r.put("rcsEnabled", jb(it.rcsEnabled))
            r.put("engineState", js(it.engineState))
            r.put("provisioningStatusPev2", jn(it.provStatusPev2))
            r.put("transportRegistered", it.tachyonRegistered)
            r.put("available", jb(it.available))
            r.put("state", jn(it.rcsState))
            r.put("configVersion", jn(it.mVersion))
            r.put("configState", jn(it.configState))
            r.put("invalidConfigReason", js(it.invalidConfigReason))
            r.put("capableContacts", jn(it.capableContacts))
            r.put("featureEnabled", jb(it.flagEnabled))
            r.put("markedUnavailable", jb(it.flagUnavailable))
            r.put("carrierEntitlement", jb(it.entitlement))
            r.put("presenceUce", jb(it.uce))
            r.put("imsServicePresent", it.imsPkgPresent)
            r.put("configUpdated", js(Fmt.tsSecs(it.cfgUpdSecs)))
            r.put("configValidity", js(Fmt.durationSecs(it.cfgValiditySecs)))
            r.put("tokenExpiration", js(Fmt.tsMs(it.tokenExpMs)))
            r.put("lastProvisioning", js(Fmt.tsSecs(it.provLastSecs)))
            r.put("nextProvisioning", js(Fmt.tsSecs(it.provNextSecs)))
            r.put("lastRcsMessage", js(Fmt.tsMs(it.msgLastMs)))
            r.put("capabilitiesRefreshed", js(Fmt.tsMs(it.capRefreshMs)))
            r.put("fullSync", js(Fmt.tsMs(it.fullSyncMs)))
            r.put("lastSync", js(Fmt.tsMs(it.lastSyncMs)))
            if (it.provConfig.isNotEmpty()) {
                val prov = JSONObject()
                for ((k, v) in it.provConfig) prov.put(k, jauto(v))
                r.put("provisioning", prov)
            }
            root.put("rcs", r)
        }

        wallet?.let {
            val w = JSONObject()
            w.put("deviceAttested", jb(it.attested))
            w.put("lastChecked", js(Fmt.tsMs(it.attestedTimeMs)))
            w.put("nfcHardware", it.hasNfc)
            w.put("walletInstalled", it.walletPkgPresent)
            w.put("walletService", jb(it.serviceEnabled))
            w.put("tapAndPay", jb(it.tapAndPayEnabled))
            w.put("screenLockAccepted", jb(it.passwordSufficient))
            w.put("cardEverAdded", jb(it.hadToken))
            root.put("wallet", w)
        }

        return root.toString(2)
    }

    fun share(ctx: Context, text: String) {
        val send = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "VD Google export")
            putExtra(Intent.EXTRA_TEXT, text)
        }
        runCatching { ctx.startActivity(Intent.createChooser(send, null)) }
    }

    fun writeTo(ctx: Context, uri: Uri, text: String) {
        runCatching { ctx.contentResolver.openOutputStream(uri)?.use { it.write(text.toByteArray()) } }
    }
}
