> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · Português · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Estrutura de arquivos

Como o repositório do VD Google está organizado, para que você consiga se orientar no
código-fonte.

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

## Onde cada coisa vive

- **Os leitores** - o que o VD Google lê e como lê - vivem apenas em
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (o aplicativo) e
  `VDGoogle/webui/webroot/scripts.js` (a WebUI). Os dois se espelham exatamente.
- **Os textos** são a fonte única de todo rótulo, em 21 idiomas: o aplicativo sob
  `res/values*/strings.xml`, a WebUI sob `webroot/lang/`. Um rótulo nunca é escrito duas
  vezes.
- **A documentação** vive em `docs/`. É a fonte da verdade para o wiki do projeto; o wiki
  é gerado a partir dela (veja `tools/` e `.github/scripts/gen_wiki.sh`).
- **A ferramenta embarcada** (`sqlite3`) vive em `assets/bin/` para o aplicativo e `bin/`
  para o módulo, e está documentada em [Binários embarcados](BINARIES.md).

## Saída da build

Builds de release produzem o APK e o ZIP da WebUI. Os artefatos de build não são
commitados. Veja o readme para saber como compilar.

## Estado em tempo de execução

No dispositivo, o módulo WebUI mantém o seu estado sob `/data/adb/vdgoogle/`:

- `scratch/` - cópias de curta duração de um banco de dados, tiradas para o lado para que
  uma consulta nunca toque no arquivo vivo. Cada leitura apaga as suas próprias cópias, e
  qualquer resíduo de uma execução interrompida é limpo na próxima vez que a WebUI abre.
  Nada aqui é mantido.
- `actions.jsonl` - o log local das ações que alteram o estado (a limpeza do cache do
  Google Wallet), uma linha JSON cada, limitado às últimas 200 entradas. Ele nunca sai do
  dispositivo.

O aplicativo mantém as mesmas cópias scratch de curta duração no seu próprio cache
privado, e grava o seu log de ações dentro do seu próprio diretório de arquivos privado.

<!-- doc-footer -->

---

> _Esta documentação foi gerada com o Claude (Anthropic) e é mantida por VD171. Correções e melhorias são bem-vindas; veja [CONTRIBUTING.md](CONTRIBUTING.md)._
