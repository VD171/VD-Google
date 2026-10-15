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

enum class Tone { NEUTRAL, GOOD, BAD, WARN }

enum class RcsHealth { CONNECTED, SETUP, OFF, UNKNOWN }

data class RcsData(
    val available: String? = null,
    val selfNumber: String? = null,
    val mVersion: String? = null,
    val rcsState: String? = null,
    val capableContacts: String? = null,
    val flagEnabled: String? = null,
    val flagUnavailable: String? = null,
    val cfgUpdSecs: Long? = null,
    val cfgValiditySecs: Long? = null,
    val tokenExpMs: Long? = null,
    val provLastSecs: Long? = null,
    val provNextSecs: Long? = null,
    val msgLastMs: Long? = null,
    val capRefreshMs: Long? = null,
    val fullSyncMs: Long? = null,
    val lastSyncMs: Long? = null,
    val entitlement: String? = null,
    val uce: String? = null,
    val imsPkgPresent: Boolean = false,
    val carrier: String? = null,
    val invalidConfigReason: String? = null,
    val configState: String? = null,
    val rcsEnabled: String? = null,
    val engineState: String? = null,
    val provStatusPev2: String? = null,
    val tachyonRegistered: Boolean = false,
    val provConfig: Map<String, String> = emptyMap(),
) {
    val health: RcsHealth
        get() {
            val enabled = rcsEnabled?.equals("true", ignoreCase = true)
            val version = mVersion?.toIntOrNull() ?: 0
            return when {
                enabled == false -> RcsHealth.OFF
                version > 0 -> RcsHealth.CONNECTED
                engineState == "ConfiguredState" || enabled == true -> RcsHealth.SETUP
                else -> RcsHealth.UNKNOWN
            }
        }
}

data class WalletData(
    val hasNfc: Boolean = false,
    val walletPkgPresent: Boolean = false,
    val attested: String? = null,
    val attestedTimeMs: Long? = null,
    val serviceEnabled: String? = null,
    val tapAndPayEnabled: String? = null,
    val hadToken: String? = null,
    val passwordSufficient: String? = null,
)

data class AttApp(
    val pkg: String,
    val apkPath: String?,
    val lastCallMs: Long,
    val calls: String,
)

data class DgFlow(
    val flow: String,
    val issuedSecs: Long,
    val expirySecs: Long,
)

data class AttData(
    val apps: List<AttApp>,
    val certified: String? = null,
    val droidGuard: List<DgFlow> = emptyList(),
)
