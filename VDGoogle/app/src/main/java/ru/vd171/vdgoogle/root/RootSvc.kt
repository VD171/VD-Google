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

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.IBinder
import com.topjohnwu.superuser.ipc.RootService
import java.io.File

class RootSvc : RootService() {
    override fun onBind(intent: Intent): IBinder = RootIpc(this)
}

private class RootIpc(private val ctx: Context) : IRootIpc.Stub() {

    override fun readText(path: String): String? =
        runCatching { File(path).readText() }.getOrNull()

    override fun exists(path: String): Boolean =
        runCatching { File(path).exists() }.getOrDefault(false)

    override fun exec(cmd: String): String? = runCatching {
        val p = ProcessBuilder("sh", "-c", cmd).redirectErrorStream(true).start()
        val out = p.inputStream.bufferedReader().use { it.readText() }
        p.waitFor()
        out
    }.getOrNull()

    override fun queryDb(dbPath: String, sql: String): List<String> {
        if (!File(dbPath).exists()) return emptyList()
        return runCatching { runQuery(dbPath, sql, withWal = true) }
            .recoverCatching { runQuery(dbPath, sql, withWal = false) }
            .getOrDefault(emptyList())
    }

    private fun runQuery(dbPath: String, sql: String, withWal: Boolean): List<String> {
        val dir = File(ctx.cacheDir, "qdb").apply { mkdirs() }
        val base = "q" + System.nanoTime()
        val copy = File(dir, "$base.db")
        val out = ArrayList<String>()
        try {
            copyIfExists(File(dbPath), copy)
            if (withWal) copyIfExists(File("$dbPath-wal"), File(dir, "$base.db-wal"))
            SQLiteDatabase.openDatabase(copy.absolutePath, null, SQLiteDatabase.OPEN_READWRITE).use { db ->
                db.rawQuery(sql, null).use { c ->
                    val n = c.columnCount
                    while (c.moveToNext()) {
                        val sb = StringBuilder()
                        for (i in 0 until n) {
                            if (i > 0) sb.append('\t')
                            sb.append(c.getString(i) ?: "")
                        }
                        out.add(sb.toString())
                    }
                }
            }
        } finally {
            dir.listFiles()?.forEach { if (it.name.startsWith(base)) it.delete() }
        }
        return out
    }

    private fun copyIfExists(from: File, to: File) {
        if (from.exists()) from.inputStream().use { i -> to.outputStream().use { o -> i.copyTo(o) } }
    }
}
