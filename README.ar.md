<!--
  VD GOOGLE - قراءة RCS وGoogle Wallet وإثباتات Play Integrity على الجهاز.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

<p align="center">
  <img src="images/logo-wordmark.png" alt="VD Google" width="320">
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases/latest"><img src="https://img.shields.io/github/v/release/VD171/VD-Google?label=release&color=0969da" alt="Release"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-AGPL--3.0--or--later-1a7f37" alt="License"></a>
  <a href="https://github.com/VD171/VD-Google/releases"><img src="https://img.shields.io/github/downloads/VD171/VD-Google/total?label=downloads&color=8250df" alt="Downloads"></a>
  <a href="https://github.com/VD171/VD-Google/stargazers"><img src="https://img.shields.io/github/stars/VD171/VD-Google?color=d29922" alt="Stars"></a>
</p>

<p align="center">
  <b>خدمات Google الخاصة بك، تُقرأ على جهازك. بلا إنترنت أبدًا.</b><br>
  يعرض لك VD Google ما يسجّله Google نفسه على هاتفك بشأن ثلاثة أمور، ويشرح معنى كل عنصر، دون إرسال أي شيء إلى أي مكان.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/USER-GUIDE">User guide</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ARCHITECTURE">Architecture</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/PRIVACY">Privacy</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/BINARIES">Bundled binaries</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/FILE-STRUCTURE">File structure</a> ·
  <a href="CHANGELOG.md">Changelog</a> ·
  <a href="https://xdaforums.com/t/VD-Google.4803837/">XDA thread</a>
</p>

> **Languages:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Українська](README.uk.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<p align="center">
  <img src="images/vdgoogle-01.png" height="420"/>
  <img src="images/vdgoogle-02.png" height="420"/>
  <img src="images/vdgoogle-03.png" height="420"/>
  <img src="images/vdgoogle-04.png" height="420"/>
  <img src="images/vdgoogle-05.png" height="420"/>
</p>

---

## التطبيق أو WebUI

يأتي كل إصدار بطريقتين، وكلاهما يقرأ المعلومات نفسها على جهازك دون إنترنت:

- **App** - ملف `.apk`، تطبيق أندرويد عادي.
- **WebUI** - ملف `.zip`، وحدة تثبّتها في مدير الروت لديك (KernelSU أو Magisk أو APatch) وتفتحها من هناك.

في كلتا الحالتين، الروت مطلوب.

## ما الذي يعرضه

### RCS
ما إذا كان RCS متاحًا لخطك، ورقم RCS الخاص بك، وحالة RCS، وإصدار الإعداد، وكم من جهات اتصالك تدعم RCS، وتخويل المشغّل، والحضور (UCE)، والطوابع الزمنية ذات الصلة (التهيئة، المزامنة، آخر رسالة، انتهاء الرمز المميز، وغيرها). ويُظهر أيضًا ما إذا كان Gemini في Google Messages جاهزًا: ميزة Magic Compose (مساعدة Gemini في الكتابة)، ومحرك الذكاء الاصطناعي على الجهاز (AICore الذي يشغّل Gemini Nano)، ومحادثة Gemini التي تصل عبر RCS.

### Google Wallet
ما إذا كان جهازك مُثبَتًا للمدفوعات، وما إذا كان يملك عتاد NFC، وما إذا كانت المحفظة مثبَّتة، وحالة خدمة الدفع والدفع باللمس، وما إذا كان قفل شاشتك مقبولًا، وما إذا سبق أن أُضيفت بطاقة.

### DroidGuard
ما إذا كان Google Play يعترف بهذا الجهاز كجهاز معتمد. هذه هي شهادة Play Protect الظاهرة في متجر Play. التصديقات التي خزّنها هذا الجهاز مؤقتًا لـ Play Integrity و DroidGuard. الوقت المعروض هو موعد انتهاء صلاحية كل منها.

### الإثباتات (Play Integrity)
التطبيقات التي طلبت مؤخرًا من Google Play Integrity أن يضمن جهازك، كل منها مع أيقونته واسمه والوقت الكامل لآخر طلب وعدد الطلبات التي أجراها في النافذة الحالية.

يرافق كل عنصر شرح قصير بلغة بسيطة لما يعنيه فعلًا.

## الخصوصية: بلا إنترنت

لا يعلن VD Google عن **أي أذونات على الإطلاق**، ولا حتى الوصول إلى الإنترنت. لا شيء مما يقرأه يغادر جهازك أبدًا. يمكنك التأكد من ذلك في ملف البيان وفي الكود المصدري.

## المتطلبات

- Android 8.0 (API 26) أو أحدث.
- **الجذر مطلوب.** توجد المعلومات أعلاه في مناطق محمية من Android لا يصل إليها سوى النظام. يقرأها VD Google بوصول المستخدم الخارق ولا يفعل بها أي شيء آخر. بدون جذر، يعرض التطبيق فقط شاشة تشرح سبب الحاجة إلى الجذر.

## الطوابع الزمنية

تُعرض جميع الأوقات حسب المنطقة الزمنية الحالية لجهازك، والمنطقة الزمنية مُبيّنة دائمًا.

## انظر أيضًا

مشاريع شقيقة من المؤلف نفسه:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: مُنقّح طرق للجهاز يقرأ كل عنصر عبر كل طريقة يمكنها قراءته ويقارن بينها جنبًا إلى جنب.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: نسخ احتياطي واستعادة كاملان للتطبيقات والبيانات بصلاحيات الجذر، يُدار بالكامل من WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: مُزيّف للجهاز يحوّل بصمة جديدة إلى ملف تعريف متماسك ومتّسق.

## جهات الاتصال

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## الترخيص

**GNU AGPL-3.0-or-later** - راجع [LICENSE](LICENSE). حقوق متروكة تشمل بند الشبكة: من يشغّل نسخة معدّلة (حتى كخدمة) يجب أن يوفّر شفرتها المصدرية. اختيرت عمدًا لإبقاء الاشتقاقات مفتوحة.

## العلامات التجارية

VD Google مشروع مستقل غير رسمي. وهو غير تابع لـ Google LLC ولا معتمد منها ولا مموّل منها. إنّ "Google" و"Google Wallet" و"Google Play" و"Play Integrity" و"Android" والأسماء والشعارات ذات الصلة هي علامات تجارية لشركة Google LLC. لا يقرأ VD Google سوى ما هو موجود بالفعل على جهازك ولا يرسل أي شيء إلى أي مكان.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
