> **Languages:** English · [العربية](ar/BINARIES.md) · [Čeština](cs/BINARIES.md) · [Deutsch](de/BINARIES.md) · [Español](es/BINARIES.md) · [فارسی](fa/BINARIES.md) · [Français](fr/BINARIES.md) · [हिन्दी](hi/BINARIES.md) · [Bahasa Indonesia](id/BINARIES.md) · [Italiano](it/BINARIES.md) · [日本語](ja/BINARIES.md) · [한국어](ko/BINARIES.md) · [Nederlands](nl/BINARIES.md) · [Polski](pl/BINARIES.md) · [Português](pt/BINARIES.md) · [Русский](ru/BINARIES.md) · [Svenska](sv/BINARIES.md) · [ไทย](th/BINARIES.md) · [Türkçe](tr/BINARIES.md) · [Українська](uk/BINARIES.md) · [Tiếng Việt](vi/BINARIES.md) · [中文](zh/BINARIES.md)

---

# Bundled binaries: provenance and auditing

VD Google promises that nothing opaque ships inside it. It runs as root and reads your
most private state, so you must be able to see exactly what it carries. This page lists
the one native tool the app and the module bundle, where it comes from, and how to
verify it.

## Principle

VD Google bundles exactly one native tool: a `sqlite3` command, with the small set of
shared libraries it needs. It is required because Android's own framework SQLite fails to
open other apps' databases on some manufacturer ROMs, which would make VD Google read
nothing on those devices. See [Architecture](ARCHITECTURE.md) for why.

- **Fetched from upstream, never from a device.** `sqlite3` and its libraries come from
  the official **Termux** package repository (`packages.termux.dev/apt`), the signed
  `.deb` packages, never copied out of a Termux installed on some phone.
- **The same bytes everywhere.** The identical files ship in the app (under `assets/bin/`)
  and in the WebUI module (under `bin/`), so both read through one engine and agree.
- **The checksum is the identity.** Whatever path a copy sits at, it is the real tool only
  if its SHA-256 matches the recorded value.
- **A recorded audit trail.** Every ABI directory carries a `PROVENANCE.json` listing each
  source package (name, version, URL, `.deb` SHA-256) and the SHA-256 of every shipped
  file. Nothing is bundled without an entry you can check.

## Origin

`.github/scripts/build_bin.sh` assembles the payload at build time, with no compiler and
no NDK. For each ABI it:

1. reads the Termux Packages index and resolves the `sqlite` package plus its transitive
   dependencies (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. downloads and extracts the official signed `.deb` files;
3. copies `sqlite3`, then copies only the shared-library closure the binary actually needs
   (resolved with `readelf`), skipping the libraries Android always provides itself;
4. writes `PROVENANCE.json`.

Termux binaries are dynamically linked against Termux's own libraries, so VD Google ships
that closure alongside `sqlite3` and runs it with `LD_LIBRARY_PATH` pointing at the bundled
`lib/` directory. Termux uses versioned sonames (`libz.so.1`, `libreadline.so.8`,
`libncursesw.so.6`), so they never clash with the Android framework's own libraries. The
`LD_LIBRARY_PATH` is set only for the `sqlite3` process, never globally.

## The files

Upstream **SQLite** command-line shell, Termux package **sqlite 3.54.1-1**. One payload
per ABI: the `sqlite3` binary plus its four-library closure
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Every
shipped file, with its SHA-256 and size:

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

`x86`:

```
9b936d8539634249836b7bfb1b51b68f108f94b5cf3b9e02bab8cbb4a222250c  sqlite3  (1637296 bytes)
b1004239a469cb492b683f28f1b59d525cb40715be6bcb51bf86c3351c0b59d8  lib/libandroid-support.so  (19656 bytes)
e3a0c521443635effaf29bbd3387b080e960b86fea2a50c2c49cd74ead637079  lib/libncursesw.so.6  (357980 bytes)
026876da62e303293d2ea3e33f14231964a02a12a93b076938d9589392323b73  lib/libreadline.so.8  (268876 bytes)
1b5e10b598b40dedac5c51636067c1f94f8b83b643912bc5ad2b8d0b15d7552f  lib/libz.so.1  (77892 bytes)
```

`x86_64`:

```
70ef3fff45e735859d139fb893300cdf7d4cf371030c7d2e6fc3dc737de0642c  sqlite3  (1612376 bytes)
f0f44e60090b07b3536729993fad94bb6127ff1c6f70188f5c6dab6a33b85dda  lib/libandroid-support.so  (20640 bytes)
79789fd21d13b82fa87d6373382c815b3d97838962351ab3ec2f7f77edf79c09  lib/libncursesw.so.6  (384896 bytes)
f8b0eb7ef121241c400824c0cc59e1eb2c02ca8901d526d428d41ee20fc40a13  lib/libreadline.so.8  (308584 bytes)
9ac487f65c341194dd285b5cdb5464190552813800d78379953a45023d5b2a34  lib/libz.so.1  (82824 bytes)
```

The same checksums are recorded in each `bin/<abi>/PROVENANCE.json`, which ships next to
the binary.

## Verify it yourself

From a checkout of the source, confirm every shipped file:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Each hash must match the listing above, and the app and WebUI copies must match each
other. To audit the whole payload end to end, re-fetch the pinned `.deb` URLs listed in
each `PROVENANCE.json`, extract them, and compare the file SHA-256 values.

## Flash-time integrity

The WebUI module checks itself while it installs. Every bundled file ships next to a
`.sha256` companion, and `customize.sh` verifies each one before touching the system; if a
file was corrupted or the `.zip` was truncated in transit, the flash is aborted with a clear
message instead of installing a damaged payload. The check is skipped safely on the rare
device whose recovery lacks a `sha256sum` that understands `-c`.

## What it is used for

`sqlite3` is run as root, against a copy of a database taken aside, purely to **read**. It
is never used to write to any of Google's databases. Its only job is to let VD Google read
the same values on every device. No other native binary is bundled.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome; see [CONTRIBUTING.md](CONTRIBUTING.md)._
