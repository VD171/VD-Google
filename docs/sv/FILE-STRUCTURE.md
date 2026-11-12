> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · Svenska · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Filstruktur

Hur VD Google-arkivet är upplagt, så att du kan hitta runt i källkoden.

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

## Var saker lever

- **Läsarna** - vad VD Google läser och hur det läser det - lever endast i
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (appen) och
  `VDGoogle/webui/webroot/scripts.js` (WebUI). De två speglar varandra exakt.
- **Strängarna** är den enda källan till varje etikett, på 21 språk: appen under
  `res/values*/strings.xml`, WebUI under `webroot/lang/`. En etikett skrivs aldrig två
  gånger.
- **Dokumentationen** lever i `docs/`. Den är sanningskällan för projektets wiki; wikin
  genereras från den (se `tools/` och `.github/scripts/gen_wiki.sh`).
- **Det medföljande verktyget** (`sqlite3`) lever i `assets/bin/` för appen och `bin/` för
  modulen, och är dokumenterat i [Medföljande binärer](BINARIES.md).

## Bygg-utdata

Release-byggen producerar APK:n och WebUI-ZIP:en. Byggartefakter checkas inte in. Se
readme för hur man bygger.

## Körtidstillstånd

På enheten håller WebUI-modulen sitt tillstånd under `/data/adb/vdgoogle/`:

- `scratch/` - kortlivade kopior av en databas, tagna åt sidan så att en fråga aldrig rör
  den levande filen. Varje läsning raderar sina egna kopior, och eventuella rester från en
  avbruten körning rensas nästa gång WebUI öppnas. Ingenting här behålls.
- `actions.jsonl` - den lokala loggen över tillståndsändrande åtgärder (rensningen av
  Google Wallet-cachen), en JSON-rad var, begränsad till de senaste 200 posterna. Den
  lämnar aldrig enheten.

Appen håller i stället samma kortlivade scratch-kopior i sin egen privata cache och skriver
sin åtgärdslogg inuti sin egen privata filkatalog.

<!-- doc-footer -->

---

> _Denna dokumentation genererades med Claude (Anthropic) och underhålls av VD171. Rättelser och förbättringar välkomnas; se [CONTRIBUTING.md](CONTRIBUTING.md)._
