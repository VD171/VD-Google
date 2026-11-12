> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · Українська · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Структура файлів

Як влаштовано репозиторій VD Google, щоб ви могли орієнтуватися у джерелі.

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

## Де що живе

- **Reader'и** - що VD Google читає і як воно читає - живуть лише в
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (застосунок) та
  `VDGoogle/webui/webroot/scripts.js` (WebUI). Обидва точно віддзеркалюють одне одного.
- **Рядки** - це єдине джерело кожної позначки, у 21 мові: застосунок у
  `res/values*/strings.xml`, WebUI у `webroot/lang/`. Позначка ніколи не пишеться двічі.
- **Документація** живе в `docs/`. Вона є джерелом істини для вікі проєкту; вікі
  генерується з неї (дивіться `tools/` та `.github/scripts/gen_wiki.sh`).
- **Вбудований інструмент** (`sqlite3`) живе в `assets/bin/` для застосунку та `bin/` для
  модуля, і задокументований у [Вбудовані бінарні файли](BINARIES.md).

## Вихід збірки

Release-збірки створюють APK та WebUI ZIP. Артефакти збірки не коммітяться.
Дивіться readme, як збирати.

## Стан під час виконання

На пристрої WebUI-модуль тримає свій стан під `/data/adb/vdgoogle/`:

- `scratch/` - короткоживучі копії бази даних, відкладені вбік, щоб запит ніколи не торкався
  живого файлу. Кожне читання видаляє власні копії, а будь-який залишок від перерваного запуску
  очищується наступного разу, коли відкривається WebUI. Тут нічого не зберігається.
- `actions.jsonl` - локальний журнал дій, що змінюють стан (очищення кешу Google Wallet), по
  одному JSON-рядку на кожну, обмежений останніми 200 записами. Він ніколи не залишає пристрій.

Застосунок натомість тримає ті самі короткоживучі scratch-копії у власному приватному кеші та
записує свій журнал дій у власний приватний каталог файлів.

<!-- doc-footer -->

---

> _Ця документація створена за допомогою Claude (Anthropic) і підтримується VD171. Виправлення та покращення вітаються; див. [CONTRIBUTING.md](CONTRIBUTING.md)._
