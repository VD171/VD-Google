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
package ru.vd171.vdgoogle.ui.screens

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vd171.vdgoogle.R
import ru.vd171.vdgoogle.data.AttApp
import ru.vd171.vdgoogle.data.RcsData
import ru.vd171.vdgoogle.data.RcsHealth
import ru.vd171.vdgoogle.data.Tone
import ru.vd171.vdgoogle.data.WalletData
import ru.vd171.vdgoogle.ui.components.InfoCard
import ru.vd171.vdgoogle.ui.components.InfoRow
import ru.vd171.vdgoogle.ui.components.KeyCallout
import ru.vd171.vdgoogle.ui.components.NoteText
import ru.vd171.vdgoogle.ui.components.RevealRow
import ru.vd171.vdgoogle.ui.components.SectionTitle
import ru.vd171.vdgoogle.ui.components.StatusStrip
import ru.vd171.vdgoogle.ui.components.TzFooter
import ru.vd171.vdgoogle.util.Fmt

private fun truthy(v: String?): Boolean? = when (v?.trim()?.lowercase()) {
    "1", "true", "yes", "enabled", "available" -> true
    "0", "false", "no", "disabled" -> false
    else -> null
}

@Composable
private fun boolRow(label: String, raw: String?, meaning: String, trueGood: Boolean = true) {
    val b = truthy(raw)
    val disp = when (b) {
        true -> stringResource(R.string.v_yes)
        false -> stringResource(R.string.v_no)
        null -> raw
    }
    val tone = when (b) {
        null -> Tone.NEUTRAL
        true -> if (trueGood) Tone.GOOD else Tone.BAD
        false -> if (trueGood) Tone.BAD else Tone.GOOD
    }
    InfoRow(label, disp, tone, meaning)
}

@Composable
private fun Loader() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

private fun pageModifier() = Modifier
    .fillMaxSize()
    .padding(horizontal = 16.dp)

@Composable
fun RcsScreen(data: RcsData?, modifier: Modifier = Modifier) {
    if (data == null) {
        Loader(); return
    }
    Column(modifier.then(pageModifier()).verticalScroll(rememberScrollState())) {
        val (healthWord, healthTone) = when (data.health) {
            RcsHealth.CONNECTED -> R.string.rcs_st_connected to Tone.GOOD
            RcsHealth.OFF -> R.string.rcs_st_off to Tone.BAD
            else -> R.string.rcs_st_setup to Tone.WARN
        }
        StatusStrip(
            listOf(
                stringResource(R.string.rcs_chip_active) to healthTone,
                stringResource(R.string.rcs_chip_backend) to (if (data.imsPkgPresent) Tone.GOOD else Tone.WARN),
            ),
        )

        if (data.health != RcsHealth.CONNECTED && data.mVersion == "0") {
            KeyCallout(stringResource(R.string.rcs_callout_mversion_zero), Tone.BAD)
        }

        SectionTitle(stringResource(R.string.rcs_sec_status))
        InfoCard {
            InfoRow(stringResource(R.string.rcs_connected), stringResource(healthWord), healthTone, stringResource(R.string.rcs_connected_m))
            InfoRow(stringResource(R.string.rcs_carrier), data.carrier, Tone.NEUTRAL, stringResource(R.string.rcs_carrier_m))
            val availWord = when (truthy(data.available)) {
                true -> stringResource(R.string.v_yes)
                false -> stringResource(R.string.v_no)
                null -> data.available
            }
            InfoRow(stringResource(R.string.rcs_available), availWord, Tone.NEUTRAL, stringResource(R.string.rcs_available_m))
            if (data.selfNumber != null) {
                RevealRow(stringResource(R.string.rcs_self_number), data.selfNumber, stringResource(R.string.rcs_self_number_m))
            }
            InfoRow(stringResource(R.string.rcs_state), data.rcsState, Tone.NEUTRAL, stringResource(R.string.rcs_state_m))
            InfoRow(
                stringResource(R.string.rcs_cfg_version),
                data.mVersion,
                if (data.mVersion == "0") Tone.BAD else if (data.mVersion != null) Tone.GOOD else Tone.NEUTRAL,
                stringResource(R.string.rcs_cfg_version_m),
            )
            val invalidReason = data.invalidConfigReason?.takeIf { !it.endsWith("UNSPECIFIED") }
            if (invalidReason != null) {
                InfoRow(
                    stringResource(R.string.rcs_invalid_reason),
                    invalidReason.removePrefix("INVALID_CONFIG_REASON_"),
                    Tone.WARN,
                    stringResource(R.string.rcs_invalid_reason_m),
                )
            }
            InfoRow(stringResource(R.string.rcs_capable), data.capableContacts, Tone.NEUTRAL, stringResource(R.string.rcs_capable_m))
            boolRow(stringResource(R.string.rcs_flag_enabled), data.flagEnabled, stringResource(R.string.rcs_flag_enabled_m))
            if (data.health != RcsHealth.CONNECTED) {
                boolRow(stringResource(R.string.rcs_flag_unavailable), data.flagUnavailable, stringResource(R.string.rcs_flag_unavailable_m), trueGood = false)
            }
            InfoRow(stringResource(R.string.rcs_entitlement), data.entitlement, Tone.NEUTRAL, stringResource(R.string.rcs_entitlement_m))
            boolRow(stringResource(R.string.rcs_uce), data.uce, stringResource(R.string.rcs_uce_m))
            boolRow(stringResource(R.string.rcs_ims_pkg), if (data.imsPkgPresent) "true" else "false", stringResource(R.string.rcs_ims_pkg_m))
        }

        SectionTitle(stringResource(R.string.rcs_sec_times))
        InfoCard {
            InfoRow(stringResource(R.string.rcs_cfg_updated), Fmt.tsSecs(data.cfgUpdSecs), Tone.NEUTRAL, stringResource(R.string.rcs_cfg_updated_m))
            InfoRow(stringResource(R.string.rcs_cfg_validity), Fmt.durationSecs(data.cfgValiditySecs), Tone.NEUTRAL, stringResource(R.string.rcs_cfg_validity_m))
            InfoRow(stringResource(R.string.rcs_token_exp), Fmt.tsMs(data.tokenExpMs), Tone.NEUTRAL, stringResource(R.string.rcs_token_exp_m))
            InfoRow(stringResource(R.string.rcs_prov_last), Fmt.tsSecs(data.provLastSecs), Tone.NEUTRAL, stringResource(R.string.rcs_prov_last_m))
            InfoRow(stringResource(R.string.rcs_prov_next), Fmt.tsSecs(data.provNextSecs), Tone.NEUTRAL, stringResource(R.string.rcs_prov_next_m))
            InfoRow(stringResource(R.string.rcs_msg_last), Fmt.tsMs(data.msgLastMs), Tone.NEUTRAL, stringResource(R.string.rcs_msg_last_m))
            InfoRow(stringResource(R.string.rcs_cap_refresh), Fmt.tsMs(data.capRefreshMs), Tone.NEUTRAL, stringResource(R.string.rcs_cap_refresh_m))
            InfoRow(stringResource(R.string.rcs_full_sync), Fmt.tsMs(data.fullSyncMs), Tone.NEUTRAL, stringResource(R.string.rcs_full_sync_m))
            InfoRow(stringResource(R.string.rcs_last_sync), Fmt.tsMs(data.lastSyncMs), Tone.NEUTRAL, stringResource(R.string.rcs_last_sync_m))
        }

        TzFooter()
    }
}

@Composable
fun WalletScreen(data: WalletData?, onReset: () -> Unit, modifier: Modifier = Modifier) {
    if (data == null) {
        Loader(); return
    }
    var confirmReset by remember { mutableStateOf(false) }
    var showInfo by remember { mutableStateOf(false) }
    Column(modifier.then(pageModifier()).verticalScroll(rememberScrollState())) {
        val attTone = when (truthy(data.attested)) {
            true -> Tone.GOOD
            false -> Tone.BAD
            null -> Tone.WARN
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.weight(1f)) {
                StatusStrip(
                    listOf(
                        stringResource(R.string.wallet_chip_attested) to attTone,
                        stringResource(R.string.wallet_chip_nfc) to (if (data.hasNfc) Tone.GOOD else Tone.WARN),
                    ),
                )
            }
            Button(
                onClick = { confirmReset = true },
                contentPadding = PaddingValues(horizontal = 14.dp),
            ) {
                Icon(Icons.Outlined.RestartAlt, null, Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text(stringResource(R.string.wallet_reset_confirm))
            }
            IconButton(onClick = { showInfo = true }) {
                Icon(Icons.Outlined.Info, stringResource(R.string.action_info))
            }
        }

        if (confirmReset) {
            AlertDialog(
                onDismissRequest = { confirmReset = false },
                title = { Text(stringResource(R.string.wallet_reset_btn)) },
                text = { Text(stringResource(R.string.wallet_reset_msg)) },
                confirmButton = {
                    TextButton(onClick = { confirmReset = false; onReset() }) {
                        Text(stringResource(R.string.wallet_reset_confirm))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { confirmReset = false }) {
                        Text(stringResource(R.string.wallet_reset_cancel))
                    }
                },
            )
        }
        if (showInfo) {
            AlertDialog(
                onDismissRequest = { showInfo = false },
                title = { Text(stringResource(R.string.wallet_reset_btn)) },
                text = { Text(stringResource(R.string.wallet_reset_info)) },
                confirmButton = {
                    TextButton(onClick = { showInfo = false }) {
                        Text(stringResource(R.string.action_close))
                    }
                },
            )
        }

        SectionTitle(stringResource(R.string.wallet_sec_device))
        InfoCard {
            boolRow(stringResource(R.string.wallet_nfc), if (data.hasNfc) "true" else "false", stringResource(R.string.wallet_nfc_m))
            boolRow(stringResource(R.string.wallet_pkg), if (data.walletPkgPresent) "true" else "false", stringResource(R.string.wallet_pkg_m))
        }

        SectionTitle(stringResource(R.string.wallet_sec_att))
        InfoCard {
            boolRow(stringResource(R.string.wallet_attested), data.attested, stringResource(R.string.wallet_attested_m))
            InfoRow(stringResource(R.string.wallet_attested_time), Fmt.tsMs(data.attestedTimeMs), Tone.NEUTRAL, stringResource(R.string.wallet_attested_time_m))
            boolRow(stringResource(R.string.wallet_service), data.serviceEnabled, stringResource(R.string.wallet_service_m))
            boolRow(stringResource(R.string.wallet_tapandpay), data.tapAndPayEnabled, stringResource(R.string.wallet_tapandpay_m))
            boolRow(stringResource(R.string.wallet_password), data.passwordSufficient, stringResource(R.string.wallet_password_m))
            boolRow(stringResource(R.string.wallet_token), data.hadToken, stringResource(R.string.wallet_token_m))
        }

        TzFooter()
    }
}

private class AppMeta(val label: String?, val icon: ImageBitmap?)

@Composable
fun AttScreen(data: List<AttApp>?, onReload: () -> Unit, modifier: Modifier = Modifier) {
    if (data == null) {
        Loader(); return
    }
    val ctx = LocalContext.current
    val meta by produceState(emptyMap<String, AppMeta>(), data) {
        value = withContext(Dispatchers.IO) { data.associate { it.pkg to resolveMeta(ctx, it) } }
    }
    var query by remember { mutableStateOf("") }
    Column(modifier.then(pageModifier()).verticalScroll(rememberScrollState())) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.weight(1f)) { SectionTitle(stringResource(R.string.att_sec_apps)) }
            IconButton(onClick = onReload) {
                Icon(Icons.Outlined.Refresh, stringResource(R.string.action_reload))
            }
        }
        if (data.isEmpty()) {
            NoteText(stringResource(R.string.att_empty))
        } else {
            NoteText(stringResource(R.string.att_intro))
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                leadingIcon = { Icon(Icons.Outlined.Search, null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(Icons.Outlined.Close, stringResource(R.string.action_close))
                        }
                    }
                },
                placeholder = { Text(stringResource(R.string.att_search)) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
            val q = query.trim()
            val shown = if (q.isEmpty()) data else data.filter {
                it.pkg.contains(q, ignoreCase = true) ||
                    (meta[it.pkg]?.label?.contains(q, ignoreCase = true) == true)
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                for (a in shown) AttCard(a, meta[a.pkg])
            }
        }
        TzFooter()
    }
}

private fun resolveMeta(ctx: android.content.Context, a: AttApp): AppMeta {
    val pm = ctx.packageManager
    val info = a.apkPath?.let { runCatching { pm.getPackageArchiveInfo(it, 0) }.getOrNull() }
    info?.applicationInfo?.apply {
        sourceDir = a.apkPath
        publicSourceDir = a.apkPath
    }
    val icon = info?.applicationInfo?.let { runCatching { it.loadIcon(pm) }.getOrNull() }
    val label = info?.applicationInfo?.let { runCatching { it.loadLabel(pm).toString() }.getOrNull() }
    return AppMeta(label, icon?.toBmp()?.asImageBitmap())
}

@Composable
private fun AttCard(a: AttApp, meta: AppMeta?) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val bmp = meta?.icon
        Box(
            Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center,
        ) {
            if (bmp != null) {
                Image(bmp, null, Modifier.size(44.dp))
            } else {
                Icon(Icons.Outlined.Android, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(
                meta?.label ?: a.pkg,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                a.pkg,
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.size(6.dp))
            Text(
                stringResource(R.string.att_last_call, Fmt.tsMs(a.lastCallMs) ?: "-"),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                stringResource(R.string.att_calls, a.calls),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

private fun Drawable.toBmp(): Bitmap {
    if (this is BitmapDrawable && bitmap != null) return bitmap
    val w = intrinsicWidth.takeIf { it > 0 } ?: 96
    val h = intrinsicHeight.takeIf { it > 0 } ?: 96
    val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    val c = Canvas(bmp)
    setBounds(0, 0, c.width, c.height)
    draw(c)
    return bmp
}
