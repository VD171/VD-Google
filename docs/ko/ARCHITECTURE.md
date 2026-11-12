> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · 한국어 · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# 아키텍처

VD Google가 어떻게 만들어졌고 왜 그렇게 만들어졌는지. 이 페이지는 설계와 그 원칙을
설명합니다. 각 독자가 사용하는 정확한 파일, 테이블, 쿼리를 나열하지는 않습니다 - 그것은
설계상 소스 코드에만 존재하며, 그래야 앱과 문서가 같은 값을 위조하려는 사람을 위한
사용법 안내서로 결코 변질되지 않습니다.

## 설계 목표

1. **진실을 읽는다.** Google의 자체 앱이 이미 기록해 둔 것을 보여주고 설명하되, 값을
   지어내지 않습니다. 기기에 답이 없으면 Unknown이라고 말합니다.
2. **네트워크 없음.** 앱은 어떤 권한도 전혀 선언하지 않으며, WebUI는 모든 원격 출처를
   차단합니다. 어떤 것도 어디로도 전송되지 않습니다, 절대로.
3. **하나의 동작, 두 개의 얼굴.** 앱과 WebUI 모듈은 완전히 동일하게 유지됩니다 - 같은
   신호, 같은 문구, 같은 내보내기 보고서.
4. **아첨보다 정직.** 실제 부정은 부정으로 표시되고, 오래된 캐시는 캐시됨으로 표시되며,
   없는 값은 Unknown입니다. 탐지는 보기 좋게 하려고 결코 완화되지 않습니다.

## 두 개의 얼굴

- **앱**은 Jetpack Compose(Material 3)를 사용하는 Kotlin입니다. libsu `RootService`를
  통해 root를 얻으며, `su`를 직접 실행하는 방식은 절대 쓰지 않고, 그 권한 서비스 안에서
  읽습니다.
- **WebUI 모듈**은 root 관리자(KernelSU, Magisk 또는 APatch)가 제공하는 작은 HTML/JS 웹
  루트입니다. 관리자의 root 브리지를 통해 동일한 읽기를 수행합니다. 엄격한
  Content-Security-Policy가 모든 원격 출처를 금지하므로, 페이지는 원리상으로도 외부와
  통신할 수 없습니다.

두 가지 모두 21개 언어로 된 하나의 공유 문자열 집합에서 생성되므로, 레이블이 두 번
번역되는 일은 없습니다.

## 데이터베이스 읽기: 내장 sqlite3

VD Google가 읽는 상태의 일부는 Google 앱에 속한 SQLite 데이터베이스에 있습니다. Android는
자체 SQLite를 제공하지만, 다른 앱의 데이터베이스를 복사해 열 때 프레임워크 SQLite는
신뢰할 수 없습니다: 일부 제조사 ROM에서는 *모든* 데이터베이스에 대해 실패하여 아무것도
반환하지 않습니다. 모든 기기에서 동일하게 읽기 위해, VD Google는 자체 `sqlite3`
바이너리(arm64 및 arm32)를 내장하고 root로 그것을 통해 쿼리를 실행합니다. 앱과 WebUI는
동일한 바이너리를 사용하므로 항상 같은 엔진과 같은 결과를 읽습니다. 그 바이너리의 출처와
체크섬은 [내장 바이너리](BINARIES.md)에 문서화되어 있습니다.

읽기는 데이터베이스를 열기 전에 복사해 두어 라이브 파일은 절대 건드리지 않으며, 실행 중인
데이터베이스가 손상된 것으로 보고할 수 있는 공유 메모리 인덱스는 복사하지 않습니다.

## RCS 신호

RCS를 정직하게 읽는 데 가장 어려운 점은 눈에 띄는 플래그들이 거짓말을 한다는 것입니다.
단순한 "available" 플래그, 기능 플래그, 프로비저닝 상태는 RCS가 완벽하게 작동하는
회선에서도 모두 부정으로 읽힐 수 있는데, 이는 그것들이 캐시이거나 회선이 통신사의 IMS
네트워크가 아니라 Google 자체 백엔드(Jibe)에서 작동하기 때문입니다. VD Google는 실제 채팅
구성과 프로비저닝 엔진으로부터 단일 **Connection** 판정을 계산하며, 이는 Android 및
Google Messages 버전 전반에서 정확하게 유지됩니다. 그리고 거짓말하는 플래그들은 캐시됨으로
명확히 레이블을 붙여, 오해를 주지 않으면서 정보를 제공합니다. 각 항목이 무엇을 의미하는지는
[사용자 가이드](USER-GUIDE.md)를 참조하세요.

## 내보내기

내보내기는 정제된 JSON 스냅샷입니다. 무엇이든 기록되기 전에 정제기가 실행됩니다: 자격
증명처럼 보이는 키와 값을 제거하고, 긴 숫자 나열을 삭제 처리하며, 전화번호, 메시지 내용,
연락처 이름 또는 원시 기능 블롭을 결코 내보내지 않습니다. 선택적 진단 섹션은 스키마 이름,
개수, 크기, 버전만 담으며 - 그마저도 이름에 포함된 개별 신원 지문은 접혀 사라집니다. 앱과
WebUI는 같은 스키마를 생성합니다.

## VD Google가 결코 하지 않는 것

- 네트워크 권한을 결코 선언하지 않으며, WebUI는 결코 원격 출처에 접속하지 않습니다.
- 값을 결코 조작하거나, 위조하거나, 숨기지 않습니다.
- Play Integrity 판정을 결코 보고하지 않습니다, 그 판정은 기기에 없기 때문입니다.
- 명시적이고, 확인되고, 로컬에 기록된 작업을 통해서만 상태를 변경합니다.
