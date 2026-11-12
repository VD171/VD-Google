> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · 日本語 · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# 同梱バイナリ: 由来と監査

VD Google は、内部に不透明なものを一切同梱しないと約束します。root として動作し、あなたの
最もプライベートな状態を読むため、何を携えているかを正確に見られる必要があります。この
ページでは、アプリとモジュールが同梱する唯一のネイティブツール、それがどこから来るか、
そしてそれをどう検証するかを列挙します。

## 原則

VD Google は正確にひとつのネイティブツールを同梱します。`sqlite3` コマンドと、それが必要と
する小さな共有ライブラリのセットです。これが必要なのは、Android 自身のフレームワークの
SQLite が一部のメーカー ROM で他のアプリのデータベースを開けず、それらのデバイスで VD Google
が何も読めなくなるためです。理由については[アーキテクチャ](ARCHITECTURE.md)を参照してください。

- **アップストリームから取得し、決してデバイスからは取らない。** `sqlite3` とそのライブラリは、
  公式の **Termux** パッケージリポジトリ（`packages.termux.dev/apt`）、署名された `.deb`
  パッケージから来ており、どこかの電話にインストールされた Termux からコピーしたものでは
  決してありません。
- **どこでも同じバイト。** 同一のファイルがアプリ（`assets/bin/` 以下）と WebUI モジュール
  （`bin/` 以下）に同梱されるため、両者はひとつのエンジンを通じて読み、一致します。
- **チェックサムが同一性である。** コピーがどのパスにあろうと、その SHA-256 が記録された値と
  一致する場合のみ、本物のツールです。
- **記録された監査証跡。** すべての ABI ディレクトリは `PROVENANCE.json` を持ち、各ソース
  パッケージ（名前、バージョン、URL、`.deb` の SHA-256）と、同梱されるすべてのファイルの
  SHA-256 を列挙します。あなたが確認できるエントリなしに同梱されるものは何もありません。

## 由来

`.github/scripts/build_bin.sh` が、コンパイラも NDK もなしに、ビルド時にペイロードを組み立て
ます。各 ABI について:

1. Termux Packages のインデックスを読み、`sqlite` パッケージとその推移的依存関係
   （`readline`、`ncurses`、`zlib`、`libandroid-support`）を解決し、
2. 公式の署名された `.deb` ファイルをダウンロードして展開し、
3. `sqlite3` をコピーし、次にバイナリが実際に必要とする共有ライブラリのクロージャだけを
   （`readelf` で解決して）コピーし、Android が常に自前で提供するライブラリはスキップし、
4. `PROVENANCE.json` を書き出します。

Termux のバイナリは Termux 自身のライブラリに対して動的にリンクされているため、VD Google は
そのクロージャを `sqlite3` と一緒に同梱し、同梱された `lib/` ディレクトリを指す
`LD_LIBRARY_PATH` を設定して実行します。Termux はバージョン付きの soname（`libz.so.1`、
`libreadline.so.8`、`libncursesw.so.6`）を使うため、Android フレームワーク自身のライブラリと
決して衝突しません。`LD_LIBRARY_PATH` は `sqlite3` プロセスに対してのみ設定され、グローバル
には決して設定されません。

## ファイル

アップストリームの **SQLite** コマンドラインシェル、Termux パッケージ **sqlite 3.54.1-1**。
ABI ごとに 1 ペイロード: `sqlite3` バイナリと、その 4 ライブラリのクロージャ
（`libreadline.so.8`、`libncursesw.so.6`、`libz.so.1`、`libandroid-support.so`）。同梱される
すべてのファイルと、その SHA-256 およびサイズ:

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

同じチェックサムは、バイナリの隣に同梱される各 `bin/<abi>/PROVENANCE.json` に記録されています。

## 自分で検証する

ソースのチェックアウトから、同梱されるすべてのファイルを確認します:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

各ハッシュは上の一覧と一致しなければならず、アプリと WebUI のコピーは互いに一致しなければ
なりません。ペイロード全体を端から端まで監査するには、各 `PROVENANCE.json` に列挙された
固定された `.deb` の URL を再取得し、展開し、ファイルの SHA-256 の値を比較してください。

## 何に使われるか

`sqlite3` は root として、脇に取ったデータベースのコピーに対して、純粋に**読み取り**のために
実行されます。Google のいかなるデータベースへの書き込みにも決して使われません。その唯一の
役割は、VD Google があらゆるデバイスで同じ値を読めるようにすることです。他のネイティブ
バイナリは一切同梱されていません。

<!-- doc-footer -->

---

> _このドキュメントは Claude (Anthropic) で生成され、VD171 によって保守されています。修正や改善を歓迎します。[CONTRIBUTING.md](CONTRIBUTING.md) を参照してください。_
