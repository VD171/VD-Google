> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · Español · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Estructura de archivos

Cómo está organizado el repositorio de VD Google, para que puedas orientarte en el código fuente.

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

## Dónde vive cada cosa

- **Los lectores** - lo que lee VD Google y cómo lo lee - viven únicamente en
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (la app) y
  `VDGoogle/webui/webroot/scripts.js` (la WebUI). Los dos se reflejan exactamente.
- **Las cadenas** son la única fuente de cada etiqueta, en 21 idiomas: la app bajo
  `res/values*/strings.xml`, la WebUI bajo `webroot/lang/`. Una etiqueta nunca se escribe dos veces.
- **La documentación** vive en `docs/`. Es la fuente de verdad para el wiki del proyecto; el wiki se
  genera a partir de ella (consulta `tools/` y `.github/scripts/gen_wiki.sh`).
- **La herramienta incluida** (`sqlite3`) vive en `assets/bin/` para la app y `bin/` para el módulo, y
  está documentada en [Binarios incluidos](BINARIES.md).

## Salida de compilación

Las compilaciones de release producen la APK y el ZIP de la WebUI. Los artefactos de compilación no se
confirman. Consulta el readme para saber cómo compilar.

## Estado en tiempo de ejecución

En el dispositivo, el módulo WebUI guarda su estado bajo `/data/adb/vdgoogle/`:

- `scratch/` - copias efímeras de una base de datos, apartadas para que una consulta nunca toque el
  archivo en vivo. Cada lectura borra sus propias copias, y cualquier remanente de una ejecución
  interrumpida se limpia la próxima vez que se abre la WebUI. Aquí no se conserva nada.
- `actions.jsonl` - el registro local de las acciones que cambian el estado (el borrado de la caché de
  Google Wallet), una línea JSON cada una, limitado a las últimas 200 entradas. Nunca sale del
  dispositivo.

La app guarda esas mismas copias efímeras de scratch en su propia caché privada en su lugar, y
escribe su registro de acciones dentro de su propio directorio de archivos privado.

<!-- doc-footer -->

---

> _Esta documentación se generó con Claude (Anthropic) y la mantiene VD171. Las correcciones y mejoras son bienvenidas; consulta [CONTRIBUTING.md](CONTRIBUTING.md)._
