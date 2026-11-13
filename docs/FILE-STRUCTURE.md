> **Languages:** English · [العربية](ar/FILE-STRUCTURE.md) · [Čeština](cs/FILE-STRUCTURE.md) · [Deutsch](de/FILE-STRUCTURE.md) · [Español](es/FILE-STRUCTURE.md) · [فارسی](fa/FILE-STRUCTURE.md) · [Français](fr/FILE-STRUCTURE.md) · [हिन्दी](hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](in/FILE-STRUCTURE.md) · [Italiano](it/FILE-STRUCTURE.md) · [日本語](ja/FILE-STRUCTURE.md) · [한국어](ko/FILE-STRUCTURE.md) · [Nederlands](nl/FILE-STRUCTURE.md) · [Polski](pl/FILE-STRUCTURE.md) · [Português](pt/FILE-STRUCTURE.md) · [Русский](ru/FILE-STRUCTURE.md) · [Svenska](sv/FILE-STRUCTURE.md) · [ไทย](th/FILE-STRUCTURE.md) · [Türkçe](tr/FILE-STRUCTURE.md) · [Українська](uk/FILE-STRUCTURE.md) · [Tiếng Việt](vi/FILE-STRUCTURE.md) · [中文](zh/FILE-STRUCTURE.md)

---

# File structure

How the VD Google repository is laid out, so you can find your way around the source.

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

## Where things live

- **The readers**, what VD Google reads and how it reads it, live only in
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (the app) and
  `VDGoogle/webui/webroot/scripts.js` (the WebUI). The two mirror each other exactly.
- **The strings** are the single source of every label, in 21 languages: the app under
  `res/values*/strings.xml`, the WebUI under `webroot/lang/`. A label is never written
  twice.
- **The documentation** lives in `docs/`. It is the source of truth for the project
  wiki; the wiki is generated from it (see `tools/` and `.github/scripts/gen_wiki.sh`).
- **The bundled tool** (`sqlite3`) lives in `assets/bin/` for the app and `bin/` for the
  module, and is documented in [Bundled binaries](BINARIES.md).

## Build output

Release builds produce the APK and the WebUI ZIP. Build artifacts are not committed.
See the readme for how to build.

## Runtime state

On the device, the WebUI module keeps its state under `/data/adb/vdgoogle/`:

- `scratch/`: short-lived copies of a database, taken aside so a query never touches the
  live file. Each read deletes its own copies, and any leftover from an interrupted run is
  cleared the next time the WebUI opens. Nothing here is kept.
- `actions.jsonl`: the local log of state-changing actions (the Google Wallet cache
  clear), one JSON line each, capped to the last 200 entries. It never leaves the device.

The app keeps the same short-lived scratch copies in its own private cache instead, and
writes its action log inside its own private files directory.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome; see [CONTRIBUTING.md](CONTRIBUTING.md)._
