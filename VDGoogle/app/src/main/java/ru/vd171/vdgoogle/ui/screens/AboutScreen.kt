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

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.LockPerson
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vd171.vdgoogle.BuildConfig
import ru.vd171.vdgoogle.R

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    val targetSdk = LocalContext.current.applicationInfo.targetSdkVersion.toString()
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        Row(Modifier.padding(top = 12.dp, bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Outlined.Shield, null,
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "v" + BuildConfig.VERSION_NAME + "  ·  SDK " + targetSdk,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 6.dp, vertical = 1.dp),
                    )
                }
                Text(
                    stringResource(R.string.app_tagline),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Callout(Icons.Outlined.LockPerson, stringResource(R.string.about_no_internet))

        AboutSectionLabel(stringResource(R.string.about_project))
        AboutCard {
            AboutRow(Icons.Outlined.Person, stringResource(R.string.about_developer), "VD171", "https://github.com/VD171")
            AboutRow(Icons.Outlined.Code, stringResource(R.string.about_source), "github.com/VD171/VD-Google", "https://github.com/VD171/VD-Google")
            AboutRow(Icons.Outlined.Gavel, stringResource(R.string.about_license), "GNU AGPL-3.0-or-later")
        }

        AboutSectionLabel(stringResource(R.string.about_contacts))
        AboutCard {
            AboutRow(Icons.Outlined.Language, null, "https://vd171.ru", "https://vd171.ru")
            AboutRow(Icons.Outlined.Language, null, "https://vd.priv8.ru", "https://vd.priv8.ru")
            AboutRow(Icons.AutoMirrored.Outlined.Send, "Telegram", "@VD_Priv8", "https://t.me/VD_Priv8")
            AboutRow(Icons.AutoMirrored.Outlined.Chat, "Discord", "@VD.Priv8", "https://discord.com/users/1296831918989639721")
            AboutRow(Icons.Outlined.Email, "E-mail", "vd.priv8@pm.me", "mailto:vd.priv8@pm.me")
            AboutRow(Icons.Outlined.Forum, "XDA", "@VD171", "https://xdaforums.com/m/vd171.4699873/")
            AboutRow(Icons.Outlined.Link, "GitHub", "@VD171", "https://github.com/VD171")
        }

        AboutSectionLabel(stringResource(R.string.about_support))
        AboutCard {
            AboutRow(Icons.AutoMirrored.Outlined.OpenInNew, null, "github.com/VD171/VD-Google/releases", "https://github.com/VD171/VD-Google/releases")
            AboutRow(Icons.AutoMirrored.Outlined.OpenInNew, null, "@RootDetected on Telegram", "https://t.me/RootDetected")
            AboutRow(Icons.AutoMirrored.Outlined.OpenInNew, null, "@BlankAssistance on Telegram", "https://t.me/BlankAssistance")
        }

        Spacer(Modifier.size(16.dp))
    }
}

@Composable
private fun Callout(icon: ImageVector, text: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.55f))
            .padding(12.dp),
    ) {
        Icon(
            icon, null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}

@Composable
private fun AboutSectionLabel(text: String) {
    Text(
        text.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(start = 8.dp, top = 20.dp, bottom = 6.dp),
    )
}

@Composable
private fun AboutCard(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
            .padding(4.dp),
        content = content,
    )
}

@Composable
private fun AboutRow(icon: ImageVector, label: String?, value: String, url: String? = null) {
    val ctx = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .then(
                if (url == null) Modifier
                else Modifier.clickable {
                    runCatching { ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
                },
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            icon, null,
            modifier = Modifier.size(22.dp),
            tint = if (url == null) MaterialTheme.colorScheme.onSurfaceVariant
            else MaterialTheme.colorScheme.primary,
        )
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            if (label != null) Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                value,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = if (url == null) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.primary,
            )
        }
    }
}
