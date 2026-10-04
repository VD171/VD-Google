<!--
  VD GOOGLE - قراءة RCS وGoogle Wallet وإثباتات Play Integrity على الجهاز.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**خدمات Google الخاصة بك، تُقرأ على جهازك. بلا إنترنت أبدًا.**

يعرض لك VD Google ما يسجّله Google نفسه على هاتفك بشأن ثلاثة أمور، ويشرح معنى كل عنصر، دون إرسال أي شيء إلى أي مكان.

> **اللغات:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## ما الذي يعرضه

### RCS
ما إذا كان RCS متاحًا لخطك، ورقم RCS الخاص بك، وحالة RCS، وإصدار الإعداد، وكم من جهات اتصالك تدعم RCS، وتخويل المشغّل، والحضور (UCE)، والطوابع الزمنية ذات الصلة (التهيئة، المزامنة، آخر رسالة، انتهاء الرمز المميز، وغيرها).

### Google Wallet
ما إذا كان جهازك مُثبَتًا للمدفوعات، وما إذا كان يملك عتاد NFC، وما إذا كانت المحفظة مثبَّتة، وحالة خدمة الدفع والدفع باللمس، وما إذا كان قفل شاشتك مقبولًا، وما إذا سبق أن أُضيفت بطاقة.

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

## جهات الاتصال

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## الترخيص

**GNU AGPL-3.0-or-later** - راجع [LICENSE](LICENSE). حقوق متروكة تشمل بند الشبكة: من يشغّل نسخة معدّلة (حتى كخدمة) يجب أن يوفّر شفرتها المصدرية. اختيرت عمدًا لإبقاء الاشتقاقات مفتوحة.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
