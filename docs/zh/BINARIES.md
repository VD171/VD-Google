> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · 中文

---

# 内置二进制文件：来源与审计

VD Google 承诺其内部不夹带任何不透明的东西。它以 root 身份运行并读取你最私密的状态，因此你必须能够
确切看到它携带了什么。本页列出应用和模块内置的那一个原生工具、它来自何处，以及如何验证它。

## 原则

VD Google 恰好内置一个原生工具：一个 `sqlite3` 命令，连同它所需的一小组共享库。之所以需要它，是因为
Android 自家框架的 SQLite 在某些厂商 ROM 上无法打开其他应用的数据库，这会使 VD Google 在那些设备上
什么也读不到。原因详见[架构](ARCHITECTURE.md)。

- **取自上游，绝不取自设备。** `sqlite3` 及其各个库来自官方 **Termux** 软件包仓库
  （`packages.termux.dev/apt`），即经过签名的 `.deb` 软件包，绝不从某台手机上安装的 Termux 中复制而
  来。
- **处处是相同的字节。** 完全相同的文件既随应用发布（位于 `assets/bin/`），也随 WebUI 模块发布（位
  于 `bin/`），因此两者通过同一个引擎读取并保持一致。
- **校验和即身份。** 无论一份副本放在哪个路径，只有当它的 SHA-256 与记录的值相符时，它才是真正的工
  具。
- **一份有记录的审计轨迹。** 每个 ABI 目录都带有一个 `PROVENANCE.json`，列出每个来源软件包（名称、
  版本、URL、`.deb` 的 SHA-256）以及每个随附文件的 SHA-256。没有任何东西在缺少一个你可以核查的条目
  的情况下被内置。

## 来源

`.github/scripts/build_bin.sh` 在构建时装配有效载荷（payload），不使用编译器，也不使用 NDK。对每个
ABI，它会：

1. 读取 Termux Packages 索引，并解析出 `sqlite` 软件包及其传递依赖（`readline`、`ncurses`、`zlib`、
   `libandroid-support`）；
2. 下载并解压官方的、经过签名的 `.deb` 文件；
3. 复制 `sqlite3`，然后只复制该二进制文件实际需要的共享库闭包（closure）（用 `readelf` 解析得出），
   跳过 Android 始终自行提供的那些库；
4. 写出 `PROVENANCE.json`。

Termux 二进制文件是与 Termux 自家的库动态链接的，因此 VD Google 把那份闭包连同 `sqlite3` 一起发布，
并在运行它时让 `LD_LIBRARY_PATH` 指向内置的 `lib/` 目录。Termux 使用带版本号的 soname
（`libz.so.1`、`libreadline.so.8`、`libncursesw.so.6`），因此它们绝不会与 Android 框架自家的库发生
冲突。`LD_LIBRARY_PATH` 只为 `sqlite3` 进程设置，绝不在全局范围设置。

## 这些文件

上游 **SQLite** 命令行 shell，Termux 软件包 **sqlite 3.54.1-1**。每个 ABI 一份有效载荷：`sqlite3`
二进制文件外加它的四库闭包（`libreadline.so.8`、`libncursesw.so.6`、`libz.so.1`、
`libandroid-support.so`）。每个随附文件，连同它的 SHA-256 和大小：

`arm64-v8a`:

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a`:

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

相同的校验和记录在每个 `bin/<abi>/PROVENANCE.json` 中，它随二进制文件一同发布于其旁边。

## 自行验证

从源代码的检出目录中，确认每一个随附文件：

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

每个哈希都必须与上面的清单相符，而且应用与 WebUI 的副本必须彼此相符。若要端到端地审计整份有效载荷，
请重新获取每个 `PROVENANCE.json` 中列出的、已固定（pinned）的 `.deb` URL，解压它们，并比对文件的
SHA-256 值。

## 它用来做什么

`sqlite3` 以 root 身份运行，针对旁置的数据库副本，纯粹用于**读取**。它绝不会被用于写入 Google 的任
何数据库。它唯一的职责是让 VD Google 在每台设备上读取相同的数值。不内置任何其他原生二进制文件。
