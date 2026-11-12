> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · 한국어 · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# 기여하기

VD Google에 관심을 가져 주셔서 감사합니다. 번역, 버그 신고, 문서, 코드 등 기여를
환영합니다.

## 기본 원칙

- **VD Google는 진실을 읽고 아무것도 위조하지 않습니다.** 기여는 그 상태를 유지해야
  합니다. 실제 부정은 부정으로 남고, 오래된 캐시는 캐시됨으로 표시되며, 없는 값은
  Unknown입니다. 읽은 값을 보기 좋게 하려고 완화하지 마세요.
- **프라이버시는 절대적입니다.** 앱은 어떤 권한도 선언하지 않으며 WebUI는 어떤 원격
  출처에도 접속하지 않습니다. 권한, 네트워크 호출, 또는 데이터를 기기 밖으로 보내는 어떤
  것도 절대 추가하지 마세요.
- **앱과 WebUI는 동일합니다.** 표시되는 내용이나 내보내기 보고서에 대한 모든 변경은 같은
  문구로 양쪽 모두에 반영되어야 합니다. 둘은 결코 어긋나서는 안 됩니다.
- **레이블은 21개 언어로, 한 곳에만 존재합니다.** 사용자에게 보이는 모든 문자열은
  앱(`res/values*/strings.xml`)과 WebUI(`webroot/lang/`)에 정의됩니다. 레이블을 추가하거나
  변경하면 모든 언어를 업데이트하세요.

## 번역

번역은 가장 쉬우면서 가장 가치 있는 기여입니다. readme는 언어별로
번역되고(`README.<lang>.md`), 앱과 WebUI 문자열은 언어별로, 이 문서는 `docs/<lang>/` 아래에
번역됩니다. 한 언어 안에서 용어를 일관되게 유지하고, 기존 톤에 맞추며, 확신이 없는 기술
용어는 절대 기계 번역하지 마세요.

## 버그 신고

기기 모델, Android 버전, 그리고 - 읽은 값에 관한 것이라면 - **내보내기**(개인 데이터가
포함되지 않음)를 함께 이슈로 올리세요. 내보내기의 진단 섹션은 바로 이 목적을 위해
만들어졌습니다.

## 코드

- 디버그가 아니라 릴리스로 빌드하세요. 실제 루팅된 기기에서 테스트하세요.
- 코드에 주석을 두지 마세요, 이름이 의도를 설명합니다. 유일한 헤더는 각 소스 파일 상단의
  라이선스 배너입니다.
- 기존 스타일에 맞추고 변경을 집중된 범위로 유지하세요.

## 라이선스

기여함으로써 귀하의 기여가 프로젝트의 **GNU AGPL-3.0-or-later** 라이선스에 따라 제공됨에
동의하는 것입니다.
