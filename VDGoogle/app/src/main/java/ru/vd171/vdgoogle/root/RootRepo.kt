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
package ru.vd171.vdgoogle.root

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.ipc.RootService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

object RootRepo {

    @Volatile private var ipc: IRootIpc? = null
    @Volatile private var pending: CompletableDeferred<Boolean>? = null

    val connected: Boolean get() = ipc != null

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            ipc = IRootIpc.Stub.asInterface(service)
            pending?.complete(ipc != null)
        }
        override fun onServiceDisconnected(name: ComponentName?) { ipc = null }
        override fun onBindingDied(name: ComponentName?) { ipc = null; pending?.complete(false) }
        override fun onNullBinding(name: ComponentName?) { ipc = null; pending?.complete(false) }
    }

    suspend fun bind(ctx: Context): Boolean = withContext(Dispatchers.IO) {
        if (ipc != null) return@withContext true
        runCatching { Shell.getCachedShell()?.takeIf { !it.isRoot }?.close() }
        if (!runCatching { Shell.getShell().isRoot }.getOrDefault(false)) return@withContext false
        val def = CompletableDeferred<Boolean>()
        pending = def
        val app = ctx.applicationContext
        Handler(Looper.getMainLooper()).post {
            runCatching { RootService.bind(Intent(app, RootSvc::class.java), conn) }
                .onFailure { def.complete(false) }
        }
        (withTimeoutOrNull(20_000) { def.await() } ?: false).also { pending = null }
    }

    suspend fun readText(path: String): String? = onIpc { it.readText(path) }
    suspend fun exists(path: String): Boolean = onIpc { it.exists(path) } ?: false
    suspend fun exec(cmd: String): String? = onIpc { it.exec(cmd) }
    suspend fun queryDb(dbPath: String, sql: String): List<String> =
        onIpc { it.queryDb(dbPath, sql) } ?: emptyList()

    private suspend fun <T> onIpc(block: (IRootIpc) -> T): T? = withContext(Dispatchers.IO) {
        ipc?.let { runCatching { block(it) }.getOrNull() }
    }
}
