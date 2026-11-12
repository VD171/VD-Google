> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · 한국어 · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# 파일 구조

VD Google 저장소가 어떻게 구성되어 있는지, 그래서 소스를 둘러볼 때 길을 찾을 수 있도록.

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

## 각 요소의 위치

- **리더(readers)** - VD Google가 무엇을 어떻게 읽는지 - 는 오직
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/`(앱)와
  `VDGoogle/webui/webroot/scripts.js`(WebUI)에만 존재합니다. 둘은 서로를 정확히
  반영합니다.
- **문자열(strings)** 은 21개 언어로 된 모든 레이블의 단일 소스입니다: 앱은
  `res/values*/strings.xml` 아래에, WebUI는 `webroot/lang/` 아래에. 레이블은 결코 두 번
  작성되지 않습니다.
- **문서(documentation)** 는 `docs/`에 있습니다. 이것은 프로젝트 위키의 단일 진실
  소스이며, 위키는 이로부터 생성됩니다(`tools/`와 `.github/scripts/gen_wiki.sh` 참조).
- **내장 도구**(`sqlite3`)는 앱의 경우 `assets/bin/`에, 모듈의 경우 `bin/`에 있으며,
  [내장 바이너리](BINARIES.md)에 문서화되어 있습니다.

## 빌드 출력

릴리스 빌드는 APK와 WebUI ZIP을 생성합니다. 빌드 산출물은 커밋되지 않습니다. 빌드 방법은
readme를 참조하세요.

## 런타임 상태

기기에서 WebUI 모듈은 자신의 상태를 `/data/adb/vdgoogle/` 아래에 보관합니다:

- `scratch/` - 쿼리가 라이브 파일을 결코 건드리지 않도록 따로 떠 둔, 수명이 짧은
  데이터베이스 복사본입니다. 각 읽기는 자신의 복사본을 삭제하며, 중단된 실행에서 남은 것은
  다음에 WebUI가 열릴 때 정리됩니다. 여기의 어떤 것도 보관되지 않습니다.
- `actions.jsonl` - 상태를 변경하는 작업(Google Wallet 캐시 지우기)의 로컬 로그이며, 각각
  한 줄의 JSON으로, 최근 200개 항목으로 제한됩니다. 기기를 결코 떠나지 않습니다.

앱은 대신 동일한 수명 짧은 scratch 복사본을 자신의 전용 캐시에 보관하고, 작업 로그는 자신의
전용 파일 디렉터리 안에 기록합니다.
