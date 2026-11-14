<!--
  VD GOOGLE: خواندن RCS، Google Wallet و گواهی‌های Play Integrity روی دستگاه.
  Copyright (C) 2026  VD171. SPDX-License-Identifier: AGPL-3.0-or-later
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
  <b>سرویس‌های Google شما، خوانده‌شده روی دستگاه. هرگز اینترنت.</b><br>
  VD Google به شما نشان می‌دهد که خود Google درباره RCS، Wallet و Play Integrity روی گوشی شما چه چیزی ثبت می‌کند و توضیح می‌دهد هر مورد چه معنایی دارد، بدون ارسال هیچ‌چیز به هیچ‌کجا.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fa-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fa-USER-GUIDE">راهنمای کاربر</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fa-ARCHITECTURE">معماری</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fa-PRIVACY">حریم خصوصی</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fa-BINARIES">باینری‌های همراه</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fa-FILE-STRUCTURE">ساختار فایل‌ها</a> ·
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

## اپلیکیشن یا WebUI

هر نسخه به دو شکل عرضه می‌شود و هر دو همان اطلاعات را روی دستگاه شما و بدون اینترنت می‌خوانند:

- **App**: فایل `.apk`، یک برنامهٔ عادی اندروید.
- **WebUI**: فایل `.zip`، ماژولی که در مدیر روت خود (KernelSU، APatch یا Magisk) فلش می‌کنید و از همان‌جا باز می‌کنید.

در هر دو حالت، روت لازم است.

## چه چیزی نمایش می‌دهد

### RCS
اینکه آیا RCS برای خط شما در دسترس است، شماره RCS شما، وضعیت RCS، نسخه پیکربندی، چند نفر از مخاطبان شما از RCS پشتیبانی می‌کنند، اجازه اپراتور، حضور (UCE) و برچسب‌های زمانی مرتبط (تدارک، همگام‌سازی، آخرین پیام، انقضای توکن و موارد دیگر). همچنین نشان می‌دهد که آیا Gemini در Google Messages آماده است: Magic Compose (کمک نگارشی Gemini)، موتور هوش مصنوعی روی دستگاه (AICore که Gemini Nano را اجرا می‌کند) و گفتگوی Gemini که از طریق RCS ارائه می‌شود.

### Google Wallet
اینکه آیا دستگاه شما برای پرداخت گواهی‌شده است، آیا سخت‌افزار NFC دارد، آیا Wallet نصب است، وضعیت سرویس پرداخت و پرداخت با تماس، آیا قفل صفحه شما پذیرفته شده و آیا تا کنون کارتی افزوده شده است.

### DroidGuard
اینکه Google Play این دستگاه را تأییدشده بشناسد. این همان گواهی Play Protect است که در Play Store نمایش داده می‌شود. گواهی‌هایی که این دستگاه برای Play Integrity و DroidGuard در حافظهٔ موقت دارد. زمان نمایش‌داده‌شده زمان انقضای هر کدام است.

### گواهی‌ها (Play Integrity)
اپ‌هایی که اخیراً از Google Play Integrity خواسته‌اند دستگاه شما را تضمین کند، هرکدام با نماد، نام، زمان کامل آخرین درخواست و تعداد درخواست‌ها در بازه فعلی.

هر مورد همراه با توضیحی کوتاه و به زبان ساده درباره معنای واقعی آن است.

## حریم خصوصی: بدون اینترنت

VD Google **هیچ مجوزی** اعلام نمی‌کند، حتی دسترسی به اینترنت. هیچ‌چیزی از آنچه می‌خواند هرگز از دستگاه شما خارج نمی‌شود. می‌توانید این را در مانیفست و در کد منبع بررسی کنید.

## پیش‌نیازها

- Android 8.0 (API 26) یا جدیدتر.
- **روت لازم است.** اطلاعات بالا در نواحی محافظت‌شده Android قرار دارند که فقط سیستم به آن‌ها دسترسی دارد. VD Google آن‌ها را با دسترسی اَبَرکاربر می‌خواند و کار دیگری با آن‌ها نمی‌کند. بدون روت، اپ فقط صفحه‌ای نمایش می‌دهد که توضیح می‌دهد چرا روت لازم است.

## برچسب‌های زمانی

همه زمان‌ها بر اساس منطقه زمانی فعلی دستگاه شما نمایش داده می‌شوند و منطقه زمانی همیشه ذکر می‌شود.

## مستندات

- **[Wiki](https://github.com/VD171/VD-Google/wiki/fa-Home)**: همه مستندات، قابل مرور به‌صورت آنلاین.
- **[راهنمای کاربر](https://github.com/VD171/VD-Google/wiki/fa-USER-GUIDE)**: اینکه هر صفحه چه چیزی را نشان می‌دهد و هر نتیجه چه معنایی دارد.
- **[معماری](https://github.com/VD171/VD-Google/wiki/fa-ARCHITECTURE)** و **[ساختار فایل‌ها](https://github.com/VD171/VD-Google/wiki/fa-FILE-STRUCTURE)**: اینکه چگونه کار می‌کند و هر چیز کجاست.
- **[حریم خصوصی](https://github.com/VD171/VD-Google/wiki/fa-PRIVACY)**: بدون مجوز اینترنت، هر خواندن از طریق روت انجام می‌شود و هیچ‌چیز هرگز از دستگاه خارج نمی‌شود.
- **[باینری‌های همراه](https://github.com/VD171/VD-Google/wiki/fa-BINARIES)**: منشأ و SHA-256 هر باینری از پیش‌کامپایل‌شده، برای بررسی کامل.
- **[مشارکت](https://github.com/VD171/VD-Google/wiki/fa-CONTRIBUTING)** و **[امنیت](https://github.com/VD171/VD-Google/wiki/fa-SECURITY)**.
- **[Changelog](CHANGELOG.md)** و **[آیین‌نامه رفتار](https://github.com/VD171/VD-Google/wiki/fa-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## همچنین ببینید

پروژه‌های خواهر از همان نویسنده:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: یک اشکال‌زدای روش‌های دستگاه که هر مورد را با همه روش‌هایی که می‌توانند آن را بخوانند می‌خواند و آن‌ها را کنار هم مقایسه می‌کند.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: پشتیبان‌گیری و بازیابی کامل برنامه‌ها و داده‌ها به‌صورت روت، که به‌طور کامل از یک WebUI هدایت می‌شود.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: یک جعل‌کننده دستگاه که یک اثرانگشت تازه را به یک نمایه منسجم و یکدست تبدیل می‌کند.
- **[VD-dropbear](https://github.com/VD171/VD-dropbear)**: یک دیمون SSH مستقل که به‌صورت روت اجرا می‌شود (Dropbear).

## راه‌های ارتباط

- https://vd171.ru
- https://vd.priv8.ru
- *Telegram:* @VD_Priv8 https://t.me/VD_Priv8
- *Channel:* @Priv8_VD https://t.me/Priv8_VD
- *Discord:* @VD.Priv8 https://discord.com/users/1296831918989639721
- *E-mail:* vd.priv8@pm.me
- *XDA-Developers:* @VD171 https://xdaforums.com/m/vd171.4699873/
- *XDA thread:* https://xdaforums.com/t/VD-Google.4803837/
- *GitHub:* @VD171 https://github.com/VD171

## مجوز

**GNU AGPL-3.0-or-later**: به [LICENSE](LICENSE) مراجعه کنید. کپی‌لفت شامل بند شبکه: هر کسی نسخه‌ای تغییریافته را (حتی به‌عنوان سرویس) اجرا کند باید کد منبع آن را ارائه دهد. عمداً انتخاب شده تا فورک‌ها باز بمانند.

## علائم تجاری

VD Google یک پروژه مستقل و غیررسمی است. وابسته به Google LLC نیست و توسط آن تأیید یا حمایت مالی نمی‌شود. «Google»، «Google Wallet»، «Google Play»، «Play Integrity»، «Android» و نام‌ها و نشان‌های مرتبط، علائم تجاری Google LLC هستند. VD Google فقط آنچه را که از پیش روی دستگاه شماست می‌خواند و هیچ‌چیزی به هیچ‌کجا ارسال نمی‌کند.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
