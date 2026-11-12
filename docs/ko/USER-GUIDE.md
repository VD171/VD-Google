> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · 한국어 · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# VD Google 사용자 가이드

VD Google에 대한 친절하고 완전한 안내: 무엇을 보여주는지, 모든 항목이 무엇을 의미하는지,
그리고 기기를 정직하게 읽는 법. VD Google는 RCS, Google Wallet, 그리고 앱이 요청하는 Play
Integrity 증명의 상태를 읽고, 각 항목을 쉬운 말로 설명합니다. 인터넷을 전혀 건드리지
않으며, 귀하가 요청하지 않는 한 아무것도 변경하지 않습니다.

## 목차

- [1. VD Google란 무엇인가](#1-vd-google란-무엇인가)
- [2. VD Google가 아닌 것](#2-vd-google가-아닌-것)
- [3. 요구 사항](#3-요구-사항)
- [4. 앱 또는 WebUI](#4-앱-또는-webui)
- [5. 설치 및 첫 실행](#5-설치-및-첫-실행)
- [6. 탭](#6-탭)
  - [6.1 정보](#61-정보)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 증명](#65-증명)
- [7. 보고서 내보내기](#7-보고서-내보내기)
- [8. 상태를 변경하는 작업](#8-상태를-변경하는-작업)
- [9. 언어 및 테마](#9-언어-및-테마)
- [10. 자주 묻는 질문](#10-자주-묻는-질문)

## 1. VD Google란 무엇인가

VD Google는 리더(reader)입니다. Google의 자체 앱이 세 가지에 대해 이미 기기에 저장해 둔
것을 살펴보고, 귀하가 실제로 이해할 수 있도록 각각을 설명합니다:

- **RCS** - 귀하의 회선에서 최신 채팅이 정말로 작동하고 있는지, 누구와 채팅할 수 있는지,
  그리고 Google Messages의 Gemini가 준비되었는지.
- **Google Wallet** - 귀하의 기기가 비접촉 결제를 위해 설정되고 증명되었는지.
- **Play Integrity 증명** - 어떤 앱이 귀하의 기기가 정품임을 증명하라고 Android에
  요청했는지, 그리고 현재 Google가 그것을 인증하는지.

모든 것은 root를 통해 로컬에서 읽히고 설명과 함께 표시됩니다. 어떤 값도 지어내지
않습니다: 기기에 답이 없으면 VD Google는 추측하는 대신 "Unknown"이라고 말합니다.

## 2. VD Google가 아닌 것

**VD Google는 무결성 검사기가 아닙니다.** 어떤 basic / device / strong 판정도 실행하거나
보고하지 않습니다. 그 결과는 앱이 자신의 서버로 보내는 암호화된 토큰 안에만 존재하며,
기기에 결코 저장되지 않으므로, 어떤 정직한 리더도 그것을 보여줄 수 없습니다. 귀하의 "Play
Integrity 판정"을 로컬에서 표시한다고 주장하는 도구는 캐시된 추측이나 그들 스스로 만든
요청을 보여주는 것이지, 진짜 답이 아닙니다.

VD Google는 또한 아무것도 스푸핑하거나, 숨기거나, 위조하지 않습니다. 진실을 읽습니다. 어떤
값이 귀하에게 틀려 보인다면, 이 가이드의 목표는 왜 진실이 그렇게 보이는지 설명하는
것입니다.

## 3. 요구 사항

- **Android 8.0 (API 26) 이상.**
- **Root.** VD Google가 읽는 데이터는 root로만 접근할 수 있는, 보호된 시스템 전용 Android
  영역에 있습니다. 모든 최신 root 솔루션이 작동합니다: KernelSU, KernelSU Next, Magisk
  또는 APatch.
- **인터넷 없음.** VD Google는 어떤 권한도 전혀 선언하지 않으므로, 허용할 것도 차단할
  것도 없습니다.

## 4. 앱 또는 WebUI

VD Google는 두 가지 방식으로 제공되며, 정확히 같은 정보를 보여줍니다:

- **앱 (APK).** 귀하가 설치하고 여는 일반 Android 앱입니다. 다른 root 앱처럼 처음 한 번
  root를 요청합니다.
- **WebUI 모듈 (ZIP).** root 관리자(KernelSU, Magisk 또는 APatch)에서 플래시하고 거기서
  엽니다. 앱으로 설치되는 것은 없으며, 귀하의 root 관리자 WebUI 안에서 실행됩니다.

원하는 것을 고르세요. 화면, 문구, 내보내기 보고서는 둘 사이에 동일합니다.

## 5. 설치 및 첫 실행

**앱:** APK를 설치하고 엽니다. 처음에 root 관리자가 요청할 때 root를 허용하세요. root를
거부하거나 무시하면, VD Google는 왜 root가 필요한지 설명하는 화면을 보여주고 다시 시도할
것을 제안합니다.

**WebUI:** root 관리자에서 ZIP을 모듈로 플래시한 다음, 관리자에서 모듈의 WebUI를 엽니다.
열리는 즉시 읽습니다.

처음 열 때 언어를 고를 수 있으며, 나중에 상단의 톱니바퀴 아이콘에서 변경할 수 있습니다.

## 6. 탭

하단에는 다섯 개의 탭이 있습니다: 정보, DroidGuard, RCS, Wallet, 증명. 정보가 먼저
열립니다.

### 6.1 정보

앱이 무엇인지, 프라이버시 약속, 버전, 그리고 링크. 프라이버시 섹션을 한 번 읽어 보세요:
그것이 앱의 전부입니다.

### 6.2 RCS

이것이 가장 풍부한 탭입니다. 타임스탬프는 항상 귀하 기기의 시간대로, 시간대가 레이블된 채
표시됩니다.

- **Connection** - 진실을 말해 주는 하나의 항목입니다. 실제 채팅 구성과 프로비저닝
  엔진으로부터 계산되므로, 더 오래된 플래그가 거짓말을 하는 최신 Android에서도 RCS가 귀하의
  회선에서 실제로 작동할 때 Connected라고 말합니다. **의심스러울 때는 이 항목을
  믿으세요.**
- **Carrier** - 귀하 회선의 이동통신사(이름과 MCC/MNC)이며, 듀얼 SIM 전화기에서는 데이터
  SIM에서 가져옵니다.
- **Availability flag (cached)** - 메시징 앱이 귀하 자신의 번호에 대해 보관하는 캐시된
  플래그입니다. RCS가 이미 작동한 후에도 오래된 상태로 남아 있을 수 있으므로, 여기의
  "No"가 RCS가 꺼져 있다는 뜻은 아닙니다. 위의 Connection 항목을 믿으세요.
- **Your RCS number** - RCS에 등록된 번호이며, 기본적으로 가려져 있습니다. 눈 아이콘을
  탭하여 드러내세요.
- **Provisioning state (cached)** 와 **Configuration version** - 메시징 앱이 보관하는
  내부 값입니다. RCS가 작동하는 동안에도 0으로 읽힐 수 있습니다. 아래의 통신사 대 Google
  백엔드에 관한 설명을 참조하세요.
- **RCS-capable contacts** - 현재 RCS 채팅을 지원하는 것으로 알려진 연락처가 몇 명인지.
- **Feature flag (cached)** - 메시징 앱이 RCS 기능을 켰는지 여부이며, 캐시되어 있어
  지연될 수 있습니다.
- **Carrier entitlement**, **Presence (UCE)**, **IMS service present** - 이들은
  *통신사의* RCS 경로(사업자의 IMS 네트워크)를 설명합니다. 귀하의 RCS가 통신사 대신
  Google의 자체 백엔드를 통해 실행될 때 여기의 "No" 또는 "0"은 정상입니다. 아래를
  참조하세요.
- **Gemini** - Google Messages의 Gemini가 준비되었는지 여부: **Magic Compose**(Gemini의
  작문 도우미), **AICore**(Gemini Nano를 실행하는 온디바이스 엔진), 그리고 RCS를 통해
  전달되므로 RCS 연결이 필요한 **Gemini chat**.
- **Timestamps** - 프로비저닝, 동기화, 마지막 메시지, 토큰 만료 등.

**통신사 RCS 대 Google 백엔드.** RCS가 귀하의 전화기에 도달하는 방식에는 두 가지가
있습니다. 하나는 *통신사의* RCS로 그들의 IMS 네트워크를 통하는 것이고, 다른 하나는
*Google 자체 백엔드*(Jibe)로, 통신사와 무관하게 오버더톱으로 작동합니다. 대부분의 사람처럼
귀하의 RCS가 Google 백엔드를 통해 실행된다면, 통신사 측 항목(carrier entitlement,
presence/UCE, IMS service, configuration version)은 진짜로 "No" 또는 "0"이며, 이는 오래된
캐시가 아니라 올바른 것입니다. 귀하의 RCS는 이런 방식으로 수년간 작동했을 수 있습니다.
그것이 작동하고 있음을 알려주는 것은 Connection 항목입니다.

### 6.3 Wallet

- **Device attested** - Google가 이 기기를 결제 카드를 보관할 만큼 정품이고 안전하다고
  판단했는지 여부입니다. 이것은 Google가 이 기기에 마지막으로 저장한 결과이므로, Google가
  재검사할 때까지 Wallet 앱보다 지연될 수 있습니다. 저장된 결과가 없으면 VD Google는
  오해를 주는 "No" 대신 **Unknown**을 표시합니다.
- **Last checked** - 그 보안 검사가 마지막으로 완료된 시점.
- **NFC hardware**, **Wallet installed**, **Wallet service**, **Tap and pay**,
  **Screen lock accepted**, **Card ever added** - 비접촉 결제를 가능하게 하는 요소들.

### 6.4 DroidGuard

- **Play Protect certified** - Google Play가 귀하의 기기를 인증된 것으로 인식하는지
  여부(Play 스토어에 표시되는 인증).
- **Attestation cache** - 귀하의 기기가 캐시한 Play Integrity 및 DroidGuard 증명들,
  각각의 만료 시점과 함께.

### 6.5 증명

귀하의 기기에서 어떤 앱이 Play Integrity를 요청했는지의 기록: 아이콘, 이름, 마지막으로
요청한 시점, 그리고 몇 번 요청했는지. 이를 통해 특정 앱이 무결성을 검사하는지 아닌지 알 수
있습니다. 검색 상자를 사용해 앱 이름이나 패키지로 필터링하세요.

## 7. 보고서 내보내기

다운로드 아이콘은 귀하의 기기, RCS, Wallet, 증명 상태를 담은 **JSON** 보고서를 기록합니다.
공유하거나 파일로 저장할 수 있습니다. 보고서에는 **전화번호도, 자격 증명도, 개인 콘텐츠도
없습니다** - 기록되기 전에 정제됩니다. 또한 기기별 문제를 해결하는 데 도움이 되는 선택적
**진단** 섹션(스키마 이름, 개수, 버전이며 귀하의 데이터는 결코 아님)을 포함합니다. 앱과
WebUI는 같은 보고서를 기록합니다.

## 8. 상태를 변경하는 작업

VD Google는 대체로 읽기 전용이지만, 하나의 **정제 작업**을 수행할 수 있습니다: Google
Wallet의 캐시된 증명 결과를 지워 향후 재검사가 새로 시작되게 하는 것입니다. 상태를 변경하는
모든 작업은 먼저 확인을 요청하고, 기기의 로컬 JSON 로그에 기록됩니다. 어떤 것도 귀하의
전화기를 떠나지 않습니다.

## 9. 언어 및 테마

VD Google는 21개 언어를 구사합니다. 첫 실행에서 귀하의 언어를 고르거나, 톱니바퀴
아이콘(root 거부 화면에서도 사용 가능)에서 언제든지 변경하세요. 밝기 아이콘은 밝은 테마와
어두운 테마를 전환합니다.

## 10. 자주 묻는 질문

**RCS는 Connected라고 하는데 여러 항목이 No 또는 0이라고 합니다. 왜죠?** 귀하의 RCS가
통신사가 아니라 Google 자체 백엔드를 통해 실행되기 때문입니다. 통신사 측 항목은 올바르게
부정입니다. [6.2 RCS](#62-rcs)를 참조하세요.

**Device attested가 Unknown이라고 합니다.** 캐시된 Wallet 결과가 귀하의 기기에 없습니다.
그것은 정직한 것입니다 - VD Google는 뒷받침할 수 없는 "No"를 보여주지 않습니다.

**제 Play Integrity 판정은 어디 있나요?** 귀하의 기기에 없습니다. [2. VD Google가 아닌
것](#2-vd-google가-아닌-것)을 참조하세요.

**root 없이 작동하나요?** 아니요. 데이터는 시스템 전용 영역에 있습니다.

<!-- doc-footer -->

---

> _이 문서는 Claude (Anthropic)로 생성되었으며 VD171이 유지 관리합니다. 수정 및 개선을 환영합니다. [CONTRIBUTING.md](CONTRIBUTING.md)를 참조하세요._
