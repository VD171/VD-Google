> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · Français · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Structure des fichiers

Comment le dépôt VD Google est organisé, pour que vous puissiez vous repérer dans le code source.

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

## Où les choses se trouvent

- **Les lecteurs** - ce que lit VD Google et la façon dont il le lit - vivent uniquement dans `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (l'application) et `VDGoogle/webui/webroot/scripts.js` (la WebUI). Les deux se reflètent exactement l'un l'autre.
- **Les chaînes** sont la source unique de chaque libellé, en 21 langues : l'application sous `res/values*/strings.xml`, la WebUI sous `webroot/lang/`. Un libellé n'est jamais écrit deux fois.
- **La documentation** vit dans `docs/`. Elle est la source de vérité du wiki du projet ; le wiki en est généré (voir `tools/` et `.github/scripts/gen_wiki.sh`).
- **L'outil embarqué** (`sqlite3`) vit dans `assets/bin/` pour l'application et `bin/` pour le module, et est documenté dans [Binaires embarqués](BINARIES.md).

## Sortie de compilation

Les compilations en release produisent l'APK et le ZIP de la WebUI. Les artefacts de compilation ne sont pas versionnés. Consultez le readme pour savoir comment compiler.

## État au moment de l'exécution

Sur l'appareil, le module WebUI conserve son état sous `/data/adb/vdgoogle/` :

- `scratch/` - des copies éphémères d'une base de données, prises à part afin qu'une requête ne touche jamais au fichier actif. Chaque lecture supprime ses propres copies, et tout reliquat d'une exécution interrompue est effacé à la prochaine ouverture de la WebUI. Rien ici n'est conservé.
- `actions.jsonl` - le journal local des actions qui modifient l'état (l'effacement du cache de Google Wallet), une ligne JSON chacune, plafonné aux 200 dernières entrées. Il ne quitte jamais l'appareil.

L'application conserve plutôt les mêmes copies éphémères dans son propre cache privé, et écrit son journal d'actions à l'intérieur de son propre répertoire de fichiers privés.

<!-- doc-footer -->

---

> _Cette documentation a été générée avec Claude (Anthropic) et est maintenue par VD171. Les corrections et améliorations sont les bienvenues ; voir [CONTRIBUTING.md](CONTRIBUTING.md)._
