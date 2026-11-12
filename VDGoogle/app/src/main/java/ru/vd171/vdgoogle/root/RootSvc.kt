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
 *  XDA thread     : https://xdaforums.com/t/VD-Google.4803837/
 *  Telegram       : @VD_Priv8 https://t.me/VD_Priv8
 *  Channel        : @Priv8_VD https://t.me/Priv8_VD
 *  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
 *  E-mail         : vd.priv8@pm.me
 * ======================================================================
 */
package ru.vd171.vdgoogle.root

import android.content.Context
import android.content.Intent
import android.os.Build
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
        val sq = sqlite3Bin ?: return emptyList()
        return runCatching { runQuery(sq, dbPath, sql, withWal = true) }
            .recoverCatching { runQuery(sq, dbPath, sql, withWal = false) }
            .getOrDefault(emptyList())
    }

    private fun runQuery(sq: File, dbPath: String, sql: String, withWal: Boolean): List<String> {
        val dir = File(ctx.cacheDir, "qdb").apply { mkdirs() }
        val base = "q" + System.nanoTime()
        val copy = File(dir, "$base.db")
        try {
            copyIfExists(File(dbPath), copy)
            if (!copy.exists()) return emptyList()
            if (withWal) copyIfExists(File("$dbPath-wal"), File(dir, "$base.db-wal"))
            val pb = ProcessBuilder(sq.absolutePath, "-separator", "\t", copy.absolutePath, sql)
            pb.environment()["LD_LIBRARY_PATH"] = File(sq.parentFile, "lib").absolutePath
            val p = pb.start()
            val out = p.inputStream.bufferedReader().use { it.readText() }
            p.errorStream.bufferedReader().use { it.readText() }
            p.waitFor()
            if (out.isEmpty()) return emptyList()
            val rows = out.split("\n")
            return if (rows.isNotEmpty() && rows.last().isEmpty()) rows.dropLast(1) else rows
        } finally {
            dir.listFiles()?.forEach { if (it.name.startsWith(base)) it.delete() }
        }
    }

    private val sqlite3Bin: File? by lazy {
        runCatching {
            val abi = when {
                Build.SUPPORTED_ABIS.any { it == "arm64-v8a" } -> "arm64-v8a"
                Build.SUPPORTED_ABIS.any { it == "armeabi-v7a" } -> "armeabi-v7a"
                else -> return@runCatching null
            }
            val dst = File(ctx.cacheDir, "bin/sqlite3").apply { parentFile?.mkdirs() }
            ctx.assets.open("bin/$abi/sqlite3").use { i -> dst.outputStream().use { o -> i.copyTo(o) } }
            dst.setExecutable(true, false)
            val libDir = File(dst.parentFile, "lib").apply { mkdirs() }
            for (lib in ctx.assets.list("bin/$abi/lib").orEmpty()) {
                val lf = File(libDir, lib)
                ctx.assets.open("bin/$abi/lib/$lib").use { i -> lf.outputStream().use { o -> i.copyTo(o) } }
                lf.setReadable(true, false)
            }
            dst
        }.getOrNull()
    }

    private fun copyIfExists(from: File, to: File) {
        if (from.exists()) from.inputStream().use { i -> to.outputStream().use { o -> i.copyTo(o) } }
    }
}
