> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · Italiano · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Struttura dei file

Come è organizzato il repository di VD Google, così da poterti orientare nel codice sorgente.

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

## Dove vivono le cose

- **I reader** - cosa VD Google legge e come lo legge - vivono solo in
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (l'app) e
  `VDGoogle/webui/webroot/scripts.js` (la WebUI). I due si rispecchiano esattamente.
- **Le stringhe** sono la fonte unica di ogni etichetta, in 21 lingue: l'app sotto
  `res/values*/strings.xml`, la WebUI sotto `webroot/lang/`. Un'etichetta non viene mai
  scritta due volte.
- **La documentazione** vive in `docs/`. È la fonte autorevole per il wiki del progetto; il
  wiki è generato da essa (vedi `tools/` e `.github/scripts/gen_wiki.sh`).
- **Lo strumento incluso** (`sqlite3`) vive in `assets/bin/` per l'app e `bin/` per il
  modulo, ed è documentato in [Binari inclusi](BINARIES.md).

## Output della build

Le build di release producono l'APK e lo ZIP della WebUI. Gli artefatti di build non sono
committati. Vedi il readme per come compilare.

## Stato a runtime

Sul dispositivo, il modulo WebUI mantiene il suo stato sotto `/data/adb/vdgoogle/`:

- `scratch/` - copie di breve durata di un database, prese da parte affinché una query non
  tocchi mai il file vivo. Ogni lettura elimina le proprie copie, e qualsiasi residuo di
  un'esecuzione interrotta viene ripulito la volta successiva che la WebUI si apre. Niente qui
  viene conservato.
- `actions.jsonl` - il log locale delle azioni che cambiano lo stato (la cancellazione della
  cache di Google Wallet), una riga JSON ciascuna, limitato alle ultime 200 voci. Non lascia
  mai il dispositivo.

L'app mantiene invece le stesse copie scratch di breve durata nella propria cache privata, e
scrive il suo log delle azioni all'interno della propria directory privata dei file.
