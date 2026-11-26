<!--
  ======================================================================
   VD GOOGLE  ::  your Google services, read on-device
   RCS, Google Wallet and Play Integrity attestations: local, no internet.
   Security policy.

   Copyright (C) 2026  VD171
   SPDX-License-Identifier: AGPL-3.0-or-later

   Free software under the GNU AGPL v3 or later. NETWORK COPYLEFT: run a
   modified version, even as a service, and you MUST offer its source.

   Site           : https://vd171.ru
   Site           : https://vd.priv8.ru
   Source         : https://github.com/VD171/VD-Google
   GitHub         : @VD171 https://github.com/VD171
   XDA-Developers : @VD171 https://xdaforums.com/m/vd171.4699873/
   XDA thread     : https://xdaforums.com/t/VD-Google.4803837/
   Telegram       : @VD_Priv8 https://t.me/VD_Priv8
   Channel        : @Priv8_VD https://t.me/Priv8_VD
   Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
   E-mail         : vd.priv8@pm.me
  ======================================================================
-->

> **Languages:** English · [العربية](docs/ar/SECURITY.md) · [Čeština](docs/cs/SECURITY.md) · [Deutsch](docs/de/SECURITY.md) · [Español](docs/es/SECURITY.md) · [فارسی](docs/fa/SECURITY.md) · [Français](docs/fr/SECURITY.md) · [हिन्दी](docs/hi/SECURITY.md) · [Bahasa Indonesia](docs/id/SECURITY.md) · [Italiano](docs/it/SECURITY.md) · [日本語](docs/ja/SECURITY.md) · [한국어](docs/ko/SECURITY.md) · [Nederlands](docs/nl/SECURITY.md) · [Polski](docs/pl/SECURITY.md) · [Português](docs/pt/SECURITY.md) · [Русский](docs/ru/SECURITY.md) · [Svenska](docs/sv/SECURITY.md) · [ไทย](docs/th/SECURITY.md) · [Türkçe](docs/tr/SECURITY.md) · [Українська](docs/uk/SECURITY.md) · [Tiếng Việt](docs/vi/SECURITY.md) · [中文](docs/zh/SECURITY.md)

---

# Security policy

VD Google is a privacy tool. It runs as root and reads sensitive state, so security
reports are taken seriously.

## Reporting a vulnerability

Please report security issues **privately**, not in a public issue:

- E-mail **vd.priv8@pm.me**.
- Describe the problem, the affected version, and how to reproduce it.
- If it concerns data exposure, describe the class of problem, you do not need to attach
  a working exploit.

You will get a response as soon as possible. Please give a reasonable window to fix the
issue before any public disclosure.

## What counts as a security issue

Because of what VD Google promises, these are especially important:

- Anything that would make the app reach the network, or send any data off the device.
- Anything that would leak personal data into an **export** (a phone number, credential,
  message content, contact name, or a per-identity fingerprint).
- Anything that lets the one sanitizing action change state without confirmation or
  without being logged.

## What is not a security issue

- A reading that looks "wrong" but is the honest truth (for example, carrier-side RCS
  lines reading No while RCS runs on Google's backend). See the
  [user guide](docs/USER-GUIDE.md).
- The absence of a Play Integrity verdict. That verdict is not on the device and VD
  Google will not invent it.

## Supported versions

Only the latest released version is supported. Please reproduce on the latest build
before reporting.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome, see [CONTRIBUTING.md](CONTRIBUTING.md)._
