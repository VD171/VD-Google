> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · Nederlands · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Bestandsstructuur

Hoe de VD Google-repository is ingedeeld, zodat je je weg kunt vinden in de broncode.

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

## Waar dingen leven

- **De readers** - wat VD Google leest en hoe het dat leest - leven alleen in
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (de app) en
  `VDGoogle/webui/webroot/scripts.js` (de WebUI). De twee weerspiegelen elkaar exact.
- **De strings** zijn de enige bron van elk label, in 21 talen: de app onder
  `res/values*/strings.xml`, de WebUI onder `webroot/lang/`. Een label wordt nooit twee keer
  geschreven.
- **De documentatie** leeft in `docs/`. Het is de bron van waarheid voor de projectwiki; de
  wiki wordt eruit gegenereerd (zie `tools/` en `.github/scripts/gen_wiki.sh`).
- **De meegeleverde tool** (`sqlite3`) leeft in `assets/bin/` voor de app en `bin/` voor de
  module, en is gedocumenteerd in [Meegeleverde binaries](BINARIES.md).

## Build-output

Release-builds produceren de APK en de WebUI-ZIP. Build-artefacten worden niet gecommit. Zie
de readme voor hoe je bouwt.

## Runtime-status

Op het toestel houdt de WebUI-module zijn status onder `/data/adb/vdgoogle/`:

- `scratch/` - kortstondige kopieën van een database, opzij genomen zodat een query nooit het
  live bestand aanraakt. Elke aflezing verwijdert zijn eigen kopieën, en elk restant van een
  onderbroken uitvoering wordt opgeruimd de volgende keer dat de WebUI opent. Niets hier wordt
  bewaard.
- `actions.jsonl` - het lokale log van status-wijzigende acties (het wissen van de Google
  Wallet-cache), elk één JSON-regel, begrensd tot de laatste 200 vermeldingen. Het verlaat
  het toestel nooit.

De app houdt dezelfde kortstondige scratch-kopieën in plaats daarvan in zijn eigen private
cache, en schrijft zijn actielog binnen zijn eigen private bestandenmap.
