> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · 中文

---

# 文件结构

VD Google 代码仓库的布局方式，便于你在源代码中找到方向。

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

## 各部分位于何处

- **各个读取器（reader）**: VD Google 读取什么、如何读取，仅位于
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/`（应用）和
  `VDGoogle/webui/webroot/scripts.js`（WebUI）。两者彼此精确镜像。
- **各个字符串** 是每个标签的唯一来源，涵盖 21 种语言：应用位于 `res/values*/strings.xml` 之下，
  WebUI 位于 `webroot/lang/` 之下。一个标签绝不会被写两遍。
- **文档** 位于 `docs/`。它是项目 wiki 的真相来源；wiki 由它生成（参见 `tools/` 和
  `.github/scripts/gen_wiki.sh`）。
- **内置工具**（`sqlite3`）在应用中位于 `assets/bin/`，在模块中位于 `bin/`，其说明见[内置二进制文
  件](BINARIES.md)。

## 构建产物

release 构建会产生 APK 和 WebUI 的 ZIP。构建产物不会被提交。构建方法请参阅 readme。

## 运行时状态

在设备上，WebUI 模块将其状态保存在 `/data/adb/vdgoogle/` 之下：

- `scratch/`: 数据库的短暂存活副本，旁置于此，使查询永远不会触碰正在使用的文件。每次读取都会删除它自
  己的副本，而任何来自被中断运行的残留，都会在下一次 WebUI 打开时被清除。这里不保留任何东西。
- `actions.jsonl`: 改变状态的操作（清除 Google Wallet 缓存）的本地日志，每项一行 JSON，上限为最近
  200 条。它绝不会离开设备。

应用则改为把同样的短暂存活 scratch 副本保存在它自己的私有缓存中，并把它的操作日志写入它自己的私有文件
目录内。

<!-- doc-footer -->

---

> _本文档由 Claude (Anthropic) 生成，并由 VD171 维护。欢迎修正和改进；请参阅 [CONTRIBUTING.md](CONTRIBUTING.md)。_
