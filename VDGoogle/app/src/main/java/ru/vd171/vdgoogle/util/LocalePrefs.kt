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
import android.content.res.Configuration
import java.util.Locale

object LocalePrefs {

    private const val PREF = "settings"
    private const val KEY_LANG = "lang"
    private const val KEY_CHOSEN = "lang_chosen"

    val LANGUAGES: List<Pair<String, String>> = listOf(
        "" to "",
        "en" to "English",
        "ar" to "العربية",
        "cs" to "Čeština",
        "de" to "Deutsch",
        "es" to "Español",
        "fa" to "فارسی",
        "fr" to "Français",
        "hi" to "हिन्दी",
        "in" to "Bahasa Indonesia",
        "it" to "Italiano",
        "ja" to "日本語",
        "ko" to "한국어",
        "nl" to "Nederlands",
        "pl" to "Polski",
        "pt" to "Português",
        "ru" to "Русский",
        "sv" to "Svenska",
        "th" to "ไทย",
        "tr" to "Türkçe",
        "vi" to "Tiếng Việt",
        "zh" to "中文",
    )

    private fun sp(c: Context) = c.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun lang(c: Context): String = runCatching { sp(c).getString(KEY_LANG, "") ?: "" }.getOrDefault("")

    fun setLang(c: Context, code: String) = runCatching { sp(c).edit().putString(KEY_LANG, code).apply() }.let {}

    fun chosen(c: Context): Boolean = runCatching { sp(c).getBoolean(KEY_CHOSEN, false) }.getOrDefault(false)

    fun markChosen(c: Context) = runCatching { sp(c).edit().putBoolean(KEY_CHOSEN, true).apply() }.let {}

    fun wrap(base: Context): Context {
        val code = lang(base)
        if (code.isEmpty()) return base
        val locale = Locale(code)
        Locale.setDefault(locale)
        val cfg = Configuration(base.resources.configuration)
        cfg.setLocale(locale)
        return base.createConfigurationContext(cfg)
    }
}
