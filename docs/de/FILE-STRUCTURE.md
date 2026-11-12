> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · Deutsch · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Dateistruktur

Wie das VD-Google-Repository aufgebaut ist, damit du dich im Quellcode zurechtfindest.

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

## Wo die Dinge leben

- **Die Reader** - was VD Google liest und wie es liest - leben allein in
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (die App) und
  `VDGoogle/webui/webroot/scripts.js` (die WebUI). Die beiden spiegeln einander exakt.
- **Die Zeichenketten** sind die einzige Quelle jeder Beschriftung, in 21 Sprachen: die App unter
  `res/values*/strings.xml`, die WebUI unter `webroot/lang/`. Eine Beschriftung wird niemals zweimal
  geschrieben.
- **Die Dokumentation** lebt in `docs/`. Sie ist die maßgebliche Quelle für das Projekt-Wiki; das
  Wiki wird aus ihr erzeugt (siehe `tools/` und `.github/scripts/gen_wiki.sh`).
- **Das mitgelieferte Werkzeug** (`sqlite3`) lebt in `assets/bin/` für die App und `bin/` für das
  Modul und ist in [Mitgelieferte Binärdateien](BINARIES.md) dokumentiert.

## Build-Ausgabe

Release-Builds erzeugen die APK und die WebUI-ZIP. Build-Artefakte werden nicht committet. Siehe die
Readme dafür, wie man baut.

## Laufzeitzustand

Auf dem Gerät hält das WebUI-Modul seinen Zustand unter `/data/adb/vdgoogle/`:

- `scratch/` - kurzlebige Kopien einer Datenbank, beiseitegelegt, sodass eine Abfrage niemals die
  Live-Datei berührt. Jeder Lesevorgang löscht seine eigenen Kopien, und jeder Überrest aus einem
  unterbrochenen Lauf wird beim nächsten Öffnen der WebUI beseitigt. Hier wird nichts aufbewahrt.
- `actions.jsonl` - das lokale Protokoll der zustandsändernden Aktionen (das Löschen des Google-Wallet-
  Caches), jeweils eine JSON-Zeile, begrenzt auf die letzten 200 Einträge. Es verlässt niemals das
  Gerät.

Die App hält dieselben kurzlebigen Scratch-Kopien stattdessen in ihrem eigenen privaten Cache und
schreibt ihr Aktionsprotokoll in ihr eigenes privates Dateiverzeichnis.
