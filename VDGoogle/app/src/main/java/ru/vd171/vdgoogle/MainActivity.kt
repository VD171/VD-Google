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
package ru.vd171.vdgoogle

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vd171.vdgoogle.ui.AppViewModel
import ru.vd171.vdgoogle.ui.RootGate
import ru.vd171.vdgoogle.ui.components.LanguageDialog
import ru.vd171.vdgoogle.ui.screens.AboutScreen
import ru.vd171.vdgoogle.ui.screens.AttScreen
import ru.vd171.vdgoogle.ui.screens.DgScreen
import ru.vd171.vdgoogle.ui.screens.RcsScreen
import ru.vd171.vdgoogle.ui.screens.RootDeniedScreen
import ru.vd171.vdgoogle.ui.screens.WalletScreen
import ru.vd171.vdgoogle.ui.theme.VDGoogleTheme
import ru.vd171.vdgoogle.util.Export
import ru.vd171.vdgoogle.util.Fmt
import ru.vd171.vdgoogle.util.LocalePrefs
import ru.vd171.vdgoogle.util.ThemePrefs

class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalePrefs.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val ctx = LocalContext.current
            var themeMode by rememberSaveable { mutableStateOf(ThemePrefs.mode(ctx)) }
            val darkOverride = when (themeMode) {
                "dark" -> true
                "light" -> false
                else -> null
            }
            VDGoogleTheme(darkOverride = darkOverride) {
                val vm: AppViewModel = viewModel()
                val state by vm.state.collectAsStateWithLifecycle()
                var showLang by remember { mutableStateOf(!LocalePrefs.chosen(ctx)) }
                var pendingReport by remember { mutableStateOf("") }
                val effectiveDark = darkOverride ?: isSystemInDarkTheme()
                val saveLauncher = rememberLauncherForActivityResult(
                    ActivityResultContracts.CreateDocument("application/json"),
                ) { uri -> if (uri != null) Export.writeTo(ctx, uri, pendingReport) }

                Box(Modifier.fillMaxSize()) {
                    when (state.gate) {
                        RootGate.CHECKING -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                        RootGate.DENIED -> RootDeniedScreen(
                            onRetry = { restartApp(ctx) },
                            onOpenLanguage = { showLang = true },
                            modifier = Modifier.fillMaxSize(),
                        )
                        RootGate.GRANTED -> MainScaffold(
                            state.rcs, state.wallet, state.att,
                            isDark = effectiveDark,
                            onOpenLanguage = { showLang = true },
                            onRefresh = { vm.refresh() },
                            onToggleTheme = {
                                val next = if (effectiveDark) "light" else "dark"
                                themeMode = next
                                ThemePrefs.setMode(ctx, next)
                            },
                            onReloadAtt = { vm.reloadAtt() },
                            onResetWallet = { vm.resetWalletCheck() },
                            onExport = { Export.share(ctx, Export.buildReport(state.rcs, state.wallet)) },
                            onSave = {
                                pendingReport = Export.buildReport(state.rcs, state.wallet)
                                runCatching { saveLauncher.launch(Fmt.exportFileName()) }
                            },
                        )
                    }
                }

                if (showLang) {
                    LanguageDialog(
                        current = LocalePrefs.lang(ctx),
                        onPick = { code ->
                            LocalePrefs.markChosen(ctx)
                            if (code != LocalePrefs.lang(ctx)) {
                                LocalePrefs.setLang(ctx, code)
                                (ctx as? Activity)?.recreate()
                            } else {
                                showLang = false
                            }
                        },
                        onDismiss = {
                            LocalePrefs.markChosen(ctx)
                            showLang = false
                        },
                    )
                }
            }
        }
    }
}

private fun restartApp(ctx: Context) {
    val intent = ctx.packageManager.getLaunchIntentForPackage(ctx.packageName)
    if (intent != null) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        ctx.startActivity(intent)
    }
    Runtime.getRuntime().exit(0)
}

private data class Tab(val titleRes: Int, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(
    rcs: ru.vd171.vdgoogle.data.RcsData?,
    wallet: ru.vd171.vdgoogle.data.WalletData?,
    att: ru.vd171.vdgoogle.data.AttData?,
    isDark: Boolean,
    onOpenLanguage: () -> Unit,
    onRefresh: () -> Unit,
    onToggleTheme: () -> Unit,
    onReloadAtt: () -> Unit,
    onResetWallet: () -> Unit,
    onExport: () -> Unit,
    onSave: () -> Unit,
) {
    val tabs = remember {
        listOf(
            Tab(R.string.tab_about, Icons.Outlined.Info),
            Tab(R.string.tab_droidguard, Icons.Outlined.Shield),
            Tab(R.string.tab_rcs, Icons.AutoMirrored.Outlined.Chat),
            Tab(R.string.tab_wallet, Icons.Outlined.CreditCard),
            Tab(R.string.tab_att, Icons.Outlined.VerifiedUser),
        )
    }
    var sel by rememberSaveable { mutableIntStateOf(0) }
    var spins by remember { mutableIntStateOf(0) }
    val spin by animateFloatAsState(spins * 360f, tween(durationMillis = 1000, easing = LinearEasing), label = "refreshSpin")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            stringResource(R.string.app_name),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "v" + BuildConfig.VERSION_NAME,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(bottom = 3.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(horizontal = 6.dp, vertical = 1.dp),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { spins++; onRefresh() }) {
                        Icon(Icons.Outlined.Refresh, stringResource(R.string.action_reload), Modifier.rotate(spin))
                    }
                    IconButton(onClick = onSave) {
                        Icon(Icons.Outlined.SaveAlt, stringResource(R.string.action_save))
                    }
                    IconButton(onClick = onExport) {
                        Icon(Icons.Outlined.Share, stringResource(R.string.action_export))
                    }
                    IconButton(onClick = onOpenLanguage) {
                        Icon(Icons.Outlined.Settings, stringResource(R.string.action_language))
                    }
                    IconButton(onClick = onToggleTheme) {
                        Icon(
                            if (isDark) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                            stringResource(R.string.action_theme),
                        )
                    }
                },
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { i, tab ->
                    NavigationBarItem(
                        selected = sel == i,
                        onClick = { sel = i },
                        icon = { Icon(tab.icon, null) },
                        label = { Text(stringResource(tab.titleRes)) },
                    )
                }
            }
        },
    ) { inner ->
        Box(Modifier.fillMaxSize().padding(inner)) {
            when (sel) {
                0 -> AboutScreen()
                1 -> DgScreen(att)
                2 -> RcsScreen(rcs)
                3 -> WalletScreen(wallet, onReset = onResetWallet)
                else -> AttScreen(att, onReload = onReloadAtt)
            }
        }
    }
}
