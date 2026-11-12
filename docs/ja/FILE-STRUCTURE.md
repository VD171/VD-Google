> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · 日本語 · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# ファイル構成

VD Google リポジトリがどのように配置されているか。ソースの中で迷わないようにするためのものです。

```
VD-Google/
├── README.md, README.<lang>.md       # the readme, in 21 languages
├── LICENSE                           # GNU AGPL-3.0-or-later
├── CHANGELOG.md                      # user-facing release notes
├── images/                           # screenshots used by the readme
├── docs/                             # this documentation (source of the wiki)
│   ├── USER-GUIDE.md, ARCHITECTURE.md, PRIVACY.md, BINARIES.md, FILE-STRUCTURE.md
│   └── <lang>/                       # the same docs in each language
├── tools/                            # maintenance scripts (wiki, docs nav)
├── .github/                          # CI and the wiki-sync workflow
│   ├── workflows/
│   └── scripts/gen_wiki.sh
└── VDGoogle/                         # the Gradle project
    ├── app/                          # the Android app
    │   └── src/main/
    │       ├── java/ru/vd171/vdgoogle/
    │       │   ├── data/             # the readers and their data models
    │       │   ├── ui/               # Compose screens and view model
    │       │   ├── root/             # the libsu RootService bridge
    │       │   └── util/             # export, formatting, action log
    │       ├── res/                  # layouts and the 21-language strings
    │       └── assets/bin/           # the bundled sqlite3 (see BINARIES.md)
    └── webui/                        # the WebUI module
        ├── module.prop, customize.sh, update.json
        ├── bin/                      # the bundled sqlite3, per ABI
        └── webroot/                  # the HTML/JS/CSS WebUI
            ├── index.html, scripts.js
            └── lang/                 # the 21-language strings for the WebUI
```

## 何がどこにあるか

- **リーダー** - VD Google が何を読み、どう読むか - は、`VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/`
  （アプリ）と `VDGoogle/webui/webroot/scripts.js`（WebUI）の中にだけ存在します。両者は
  互いを正確に鏡写しにしています。
- **文字列**は、21 言語におけるすべてのラベルの単一の源です。アプリは `res/values*/strings.xml`
  以下、WebUI は `webroot/lang/` 以下。ラベルは決して二度書かれません。
- **ドキュメント**は `docs/` の中にあります。これはプロジェクトの wiki にとっての信頼できる
  源であり、wiki はそこから生成されます（`tools/` と `.github/scripts/gen_wiki.sh` を参照）。
- **同梱ツール**（`sqlite3`）は、アプリでは `assets/bin/`、モジュールでは `bin/` の中にあり、
  [同梱バイナリ](BINARIES.md)に記載されています。

## ビルド出力

release ビルドは APK と WebUI の ZIP を生成します。ビルド成果物はコミットされません。
ビルド方法については readme を参照してください。

## ランタイムの状態

デバイス上では、WebUI モジュールはその状態を `/data/adb/vdgoogle/` 以下に保持します:

- `scratch/` - データベースの短命なコピー。クエリが生きたファイルに決して触れないよう、脇に
  取られます。各読み取りは自分のコピーを削除し、中断された実行による残留物は次に WebUI が
  開くときにすべてクリアされます。ここには何も保持されません。
- `actions.jsonl` - 状態を変更するアクション（Google Wallet のキャッシュ消去）のローカル
  ログ。それぞれ 1 行の JSON で、最新の 200 エントリに制限されます。決してデバイスから
  出ていきません。

アプリは代わりに、同じ短命な scratch のコピーを自身のプライベートキャッシュに保持し、その
アクションログを自身のプライベートなファイルディレクトリの中に書き込みます。

<!-- doc-footer -->

---

> _このドキュメントは Claude (Anthropic) で生成され、VD171 によって保守されています。修正や改善を歓迎します。[CONTRIBUTING.md](CONTRIBUTING.md) を参照してください。_
