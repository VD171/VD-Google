> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · Polski · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Struktura plików

Jak rozłożone jest repozytorium VD Google, abyś mógł się odnaleźć w kodzie źródłowym.

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

## Gdzie co żyje

- **Czytniki** - co VD Google czyta i jak to czyta - żyją wyłącznie w
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (aplikacja) i
  `VDGoogle/webui/webroot/scripts.js` (WebUI). Oba odzwierciedlają się nawzajem dokładnie.
- **Teksty** są jedynym źródłem każdej etykiety, w 21 językach: aplikacja pod
  `res/values*/strings.xml`, WebUI pod `webroot/lang/`. Etykieta nigdy nie jest pisana
  dwa razy.
- **Dokumentacja** żyje w `docs/`. Jest źródłem prawdy dla wiki projektu; wiki jest z niej
  generowane (zobacz `tools/` i `.github/scripts/gen_wiki.sh`).
- **Wbudowane narzędzie** (`sqlite3`) żyje w `assets/bin/` dla aplikacji i `bin/` dla
  modułu, i jest udokumentowane w [Wbudowane pliki binarne](BINARIES.md).

## Wynik kompilacji

Kompilacje release produkują APK i ZIP WebUI. Artefakty kompilacji nie są commitowane.
Zobacz readme, aby dowiedzieć się, jak budować.

## Stan czasu wykonania

Na urządzeniu moduł WebUI przechowuje swój stan pod `/data/adb/vdgoogle/`:

- `scratch/` - krótkotrwałe kopie bazy danych, brane na bok, aby zapytanie nigdy nie
  dotykało żywego pliku. Każdy odczyt usuwa swoje własne kopie, a wszelkie pozostałości po
  przerwanym uruchomieniu są czyszczone przy następnym otwarciu WebUI. Nic tutaj nie jest
  przechowywane.
- `actions.jsonl` - lokalny log działań zmieniających stan (wyczyszczenie pamięci
  podręcznej Google Wallet), po jednej linii JSON każde, ograniczony do ostatnich 200
  wpisów. Nigdy nie opuszcza urządzenia.

Aplikacja przechowuje te same krótkotrwałe kopie scratch we własnej prywatnej pamięci
podręcznej, a swój log działań zapisuje wewnątrz własnego prywatnego katalogu plików.

<!-- doc-footer -->

---

> _Ta dokumentacja została wygenerowana za pomocą Claude (Anthropic) i jest utrzymywana przez VD171. Poprawki i ulepszenia są mile widziane; zobacz [CONTRIBUTING.md](CONTRIBUTING.md)._
