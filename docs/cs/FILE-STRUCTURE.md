> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · Čeština · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Struktura souborů

Jak je rozvržené úložiště VD Google, abyste se ve zdrojovém kódu vyznali.

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

## Kde co žije

- **Čtečky**: co VD Google čte a jak to čte, žijí jen v
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (aplikace) a
  `VDGoogle/webui/webroot/scripts.js` (WebUI). Obě se navzájem přesně zrcadlí.
- **Řetězce** jsou jediným zdrojem každého popisku, ve 21 jazycích: aplikace pod
  `res/values*/strings.xml`, WebUI pod `webroot/lang/`. Popisek se nikdy nepíše dvakrát.
- **Dokumentace** žije v `docs/`. Je zdrojem pravdy pro projektovou wiki; wiki se z ní
  generuje (viz `tools/` a `.github/scripts/gen_wiki.sh`).
- **Přibalený nástroj** (`sqlite3`) žije v `assets/bin/` pro aplikaci a v `bin/` pro
  modul a je zdokumentován v [Přibalené binárky](BINARIES.md).

## Výstup sestavení

Release sestavení produkují APK a ZIP WebUI. Artefakty sestavení se necommitují. Jak
sestavovat, viz readme.

## Stav za běhu

Na zařízení si modul WebUI drží svůj stav pod `/data/adb/vdgoogle/`:

- `scratch/`: krátkodobé kopie databáze, vzaté stranou, aby se dotaz nikdy nedotkl živého
  souboru. Každé čtení své vlastní kopie smaže a jakýkoli zbytek z přerušeného běhu se
  vyčistí při příštím otevření WebUI. Nic se zde neuchovává.
- `actions.jsonl`: lokální log akcí měnících stav (vymazání mezipaměti Google Wallet),
  každá na jednom řádku JSON, omezeno na posledních 200 záznamů. Nikdy neopouští zařízení.

Aplikace si drží stejné krátkodobé scratch kopie místo toho ve své vlastní soukromé
mezipaměti a svůj log akcí zapisuje do svého vlastního soukromého adresáře souborů.

<!-- doc-footer -->

---

> _Tato dokumentace byla vytvořena pomocí Claude (Anthropic) a spravuje ji VD171. Opravy a vylepšení jsou vítány; viz [CONTRIBUTING.md](CONTRIBUTING.md)._
