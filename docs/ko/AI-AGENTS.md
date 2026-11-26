> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · 한국어 · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# AI 에이전트

VD Google은 Google이 기기에 남기는 자체 기록을 **AI 에이전트가 한 단계로 읽을 수 있는** 것으로 바꿉니다. 여러 Google 앱과 데이터베이스를 열어 그 의미를 추측하는 대신, 에이전트는 구조화된 단일 **내보내기**를 읽고 기기가 RCS, Google Wallet, Play Integrity에 대해 무엇을 보고하는지 정확히 알 수 있습니다.

이 페이지는 README에서 소개한 개념을 확장합니다.

## 내보내기

앱과 WebUI는 모두 동일한 JSON 내보내기를 생성합니다.

- **앱**: **내보내기** 동작이 보고서를 공유하거나 저장합니다.
- **WebUI**: **내보내기** 동작이 `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`에 기록합니다.

앱과 WebUI는 동일하게 유지되므로 둘은 같은 스키마를 가집니다. 아무것도 어디로도 전송되지 않습니다. 내보내기는 기기에 기록되고 기기에 남습니다.

## 무엇이 들어 있나

내보내기는 다음 최상위 키를 가진 단일 JSON 객체입니다.

- `app`, `version`, `exportedAt`, `timezone` - 무엇이 언제 보고서를 생성했는지, 기기의 레이블이 붙은 시간대로 표시됩니다.
- `device` - 제조사, 모델, Android 버전, SDK 레벨.
- `rcs` - 가용성, 상태, 구성 버전, 지원 연락처, 통신사 사용 권한, 프레즌스(UCE), 관련 타임스탬프.
- `wallet` - 결제 증명, NFC 하드웨어, 비접촉 결제 상태, 화면 잠금 수락 여부, 카드가 추가된 적이 있는지.
- `diagnostics` - 버그 보고를 위해 만들어진 자체 점검 섹션.

내보내기에는 **개인 데이터가 전혀 없습니다**. 전화번호도, 자격 증명도, 메시지 내용도, 연락처 이름도 없습니다. 모든 값은 정직한 판독입니다. 실제 아니요는 아니요로 남고, 오래된 캐시는 캐시됨으로 표시되며, 누락된 값은 알 수 없음입니다.

## 읽기 전용, 기록되는 한 가지 동작 포함

VD Google은 읽습니다. 유일한 예외는 정리 동작(재평가를 강제하기 위해 캐시된 Google Wallet 증명 결과를 지우는 것)입니다. 상태를 바꾸는 모든 동작은 기기에서 확인을 요청하고 로컬 JSON 동작 로그에 기록됩니다. WebUI를 조작하는 에이전트도 동일한 확인과 동일한 로그를 봅니다. 숨겨지거나 기록되지 않는 경로는 없습니다.

## 네트워크 계약 없음

VD Google은 네트워크 API도 소켓도 노출하지 않습니다. 어떤 권한도 선언하지 않습니다. 에이전트는 **내보내기 파일**을 사용하며, 서비스를 호출하지 않습니다. 이것이 자동화에서도 유지되는 개인정보 보호 보장입니다.

<!-- doc-footer -->

---

> _이 문서는 Claude (Anthropic)로 생성되었으며 VD171이 유지 관리합니다. 수정 및 개선을 환영합니다. [CONTRIBUTING.md](CONTRIBUTING.md)를 참조하세요._
