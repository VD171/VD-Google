> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · Русский · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Структура файлов

Как устроен репозиторий VD Google, чтобы вы могли ориентироваться в исходном коде.

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

## Где что живёт

- **Читатели** - что VD Google читает и как он это читает - живут только в
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (приложение) и
  `VDGoogle/webui/webroot/scripts.js` (WebUI). Эти два точно зеркалят друг друга.
- **Строки** - единственный источник каждой подписи, на 21 языке: у приложения под
  `res/values*/strings.xml`, у WebUI под `webroot/lang/`. Подпись никогда не пишется
  дважды.
- **Документация** живёт в `docs/`. Это источник истины для вики проекта; вики
  генерируется из неё (см. `tools/` и `.github/scripts/gen_wiki.sh`).
- **Встроенный инструмент** (`sqlite3`) живёт в `assets/bin/` для приложения и `bin/` для
  модуля и описан в разделе [Встроенные двоичные файлы](BINARIES.md).

## Результат сборки

Релизные сборки производят APK и ZIP WebUI. Артефакты сборки не коммитятся. О том, как
собирать, см. в readme.

## Состояние времени выполнения

На устройстве модуль WebUI держит своё состояние в `/data/adb/vdgoogle/`:

- `scratch/` - недолговечные копии базы данных, отложенные в сторону, чтобы запрос никогда
  не трогал живой файл. Каждое чтение удаляет свои собственные копии, а любой остаток от
  прерванного запуска очищается при следующем открытии WebUI. Ничего здесь не хранится.
- `actions.jsonl` - локальный журнал действий, изменяющих состояние (очистка кеша Google
  Wallet), по одной JSON-строке на каждое, ограниченный последними 200 записями. Он никогда
  не покидает устройство.

Приложение держит такие же недолговечные временные копии вместо этого в своём собственном
приватном кеше и пишет свой журнал действий внутри собственного приватного каталога файлов.

<!-- doc-footer -->

---

> _Эта документация создана с помощью Claude (Anthropic) и поддерживается VD171. Исправления и улучшения приветствуются; см. [CONTRIBUTING.md](CONTRIBUTING.md)._
