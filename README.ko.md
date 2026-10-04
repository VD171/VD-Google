<!--
  VD GOOGLE - RCS, Google 월렛, Play Integrity 증명을 기기에서 읽기.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**내 Google 서비스를 기기에서 바로 읽기. 인터넷은 절대 사용하지 않음.**

VD Google은 Google이 직접 휴대폰에 기록하는 세 가지 항목을 보여주고, 각 항목이 무엇을 의미하는지 설명합니다. 어떤 것도 어디로도 보내지 않습니다.

> **언어:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## 무엇을 보여주나요

### RCS
회선에서 RCS를 사용할 수 있는지, 내 RCS 번호, RCS 상태, 구성 버전, 연락처 중 몇 명이 RCS를 지원하는지, 통신사 권한, 프레즌스(UCE), 그리고 관련 타임스탬프(프로비저닝, 동기화, 마지막 메시지, 토큰 만료 등).

### Google 월렛
기기가 결제용으로 증명되었는지, NFC 하드웨어가 있는지, 월렛이 설치되어 있는지, 결제 서비스와 터치 결제 상태, 화면 잠금이 승인되었는지, 그리고 카드를 추가한 적이 있는지.

### 증명 (Play Integrity)
최근 Google Play Integrity에 기기 보증을 요청한 앱들로, 각각 아이콘, 이름, 마지막 요청의 전체 시간, 현재 기간 내 요청 횟수가 함께 표시됩니다.

각 항목에는 실제로 무엇을 의미하는지 쉬운 말로 설명한 짧은 안내가 붙어 있습니다.

## 개인정보: 인터넷 없음

VD Google은 **어떤 권한도 선언하지 않습니다**. 인터넷 접근 권한조차 없습니다. 이 앱이 읽는 어떤 것도 기기를 떠나지 않습니다. 매니페스트와 소스 코드에서 확인할 수 있습니다.

## 요구 사항

- Android 8.0 (API 26) 이상.
- **Root가 필요합니다.** 위 정보는 시스템만 접근할 수 있는 Android의 보호된 영역에 있습니다. VD Google은 슈퍼유저 권한으로 이를 읽을 뿐 그 밖의 어떤 일도 하지 않습니다. root가 없으면 앱은 root가 왜 필요한지 설명하는 화면만 표시합니다.

## 타임스탬프

모든 시간은 기기의 현재 표준 시간대로 표시되며, 표준 시간대는 항상 함께 표기됩니다.

## 연락처

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## 라이선스

**GNU AGPL-3.0-or-later** - [LICENSE](LICENSE) 참조. 네트워크 조항을 포함한 카피레프트: 수정된 버전을 (서비스로라도) 실행하는 사람은 그 소스를 제공해야 합니다. 포크를 공개 상태로 유지하기 위해 의도적으로 선택했습니다.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
