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

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object Fmt {

    private fun formatter(): SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).apply { timeZone = TimeZone.getDefault() }

    fun tsMs(ms: Long?): String? = ms?.takeIf { it > 0L }?.let { formatter().format(Date(it)) }

    fun tsSecs(s: Long?): String? = tsMs(s?.let { it * 1000L })

    fun exportFileName(): String {
        val ts = SimpleDateFormat("yyyyMMdd-HHmm", Locale.US).format(Date())
        return "vdgoogle-$ts.json"
    }

    fun timezoneLabel(): String {
        val tz = TimeZone.getDefault()
        val off = tz.getOffset(System.currentTimeMillis())
        val sign = if (off >= 0) "+" else "-"
        val abs = Math.abs(off)
        val hh = abs / 3_600_000
        val mm = (abs / 60_000) % 60
        val gmt = "GMT%s%02d:%02d".format(sign, hh, mm)
        val abbr = tz.getDisplayName(tz.inDaylightTime(Date()), TimeZone.SHORT, Locale.getDefault())
        return if (abbr.isBlank() || abbr == gmt || abbr.startsWith("GMT")) gmt else "$gmt ($abbr)"
    }

    fun maskNumber(value: String): String {
        val tail = value.takeLast(4)
        return "•••• $tail"
    }

    fun durationSecs(secs: Long?): String? {
        if (secs == null || secs <= 0L) return null
        val d = secs / 86_400
        val h = (secs % 86_400) / 3_600
        val m = (secs % 3_600) / 60
        return buildString {
            if (d > 0) append("${d}d ")
            if (h > 0) append("${h}h ")
            if (m > 0 || isEmpty()) append("${m}m")
        }.trim()
    }
}
