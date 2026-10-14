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
import org.json.JSONArray
import org.json.JSONObject
import ru.vd171.vdgoogle.BuildConfig
import java.io.File

object ActionLog {

    private const val FILE = "actions.json"
    private const val MAX = 200

    fun file(ctx: Context): File = File(ctx.filesDir, FILE)

    fun record(ctx: Context, action: String, ok: Boolean) {
        runCatching {
            val f = file(ctx)
            val arr = if (f.exists()) JSONArray(f.readText()) else JSONArray()
            arr.put(
                JSONObject()
                    .put("time", Fmt.tsMs(System.currentTimeMillis()))
                    .put("action", action)
                    .put("ok", ok)
                    .put("appVersion", BuildConfig.VERSION_NAME),
            )
            val trimmed = if (arr.length() > MAX) {
                JSONArray().apply { for (i in arr.length() - MAX until arr.length()) put(arr.get(i)) }
            } else {
                arr
            }
            f.writeText(trimmed.toString(2))
        }
    }
}
