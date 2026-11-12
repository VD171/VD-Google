> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · 한국어 · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# 보안 정책

VD Google는 프라이버시 도구입니다. root로 실행되며 민감한 상태를 읽으므로, 보안 신고는
진지하게 받아들입니다.

## 취약점 신고

보안 문제는 공개 이슈가 아니라 **비공개로** 신고해 주세요:

- 이메일 **vd.priv8@pm.me**.
- 문제, 영향을 받는 버전, 재현 방법을 설명하세요.
- 데이터 노출에 관한 것이라면, 문제의 유형을 설명하세요, 작동하는 익스플로잇을 첨부할
  필요는 없습니다.

가능한 한 빨리 답변을 받게 됩니다. 공개 공개(disclosure) 전에 문제를 수정할 합리적인
시간을 주시기 바랍니다.

## 무엇이 보안 문제인가

VD Google가 약속하는 바 때문에, 다음이 특히 중요합니다:

- 앱이 네트워크에 도달하게 하거나, 어떤 데이터든 기기 밖으로 보내게 만드는 모든 것.
- 개인 데이터(전화번호, 자격 증명, 메시지 내용, 연락처 이름, 또는 개별 신원 지문)를
  **내보내기**로 유출시키는 모든 것.
- 유일한 정제 작업이 확인 없이, 또는 기록되지 않은 채로 상태를 변경하게 하는 모든 것.

## 무엇이 보안 문제가 아닌가

- "틀려" 보이지만 정직한 진실인 읽기값(예를 들어, RCS가 Google 백엔드에서 실행되는 동안
  통신사 측 RCS 항목이 No로 읽히는 경우). [사용자 가이드](USER-GUIDE.md)를 참조하세요.
- Play Integrity 판정의 부재. 그 판정은 기기에 없으며 VD Google는 그것을 지어내지
  않습니다.

## 지원되는 버전

최신 릴리스 버전만 지원됩니다. 신고 전에 최신 빌드에서 재현해 주세요.
