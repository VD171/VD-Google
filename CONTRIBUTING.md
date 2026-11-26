<!--
  ======================================================================
   VD GOOGLE  ::  your Google services, read on-device
   RCS, Google Wallet and Play Integrity attestations: local, no internet.
   Contributing guide.

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

> **Languages:** English · [العربية](docs/ar/CONTRIBUTING.md) · [Čeština](docs/cs/CONTRIBUTING.md) · [Deutsch](docs/de/CONTRIBUTING.md) · [Español](docs/es/CONTRIBUTING.md) · [فارسی](docs/fa/CONTRIBUTING.md) · [Français](docs/fr/CONTRIBUTING.md) · [हिन्दी](docs/hi/CONTRIBUTING.md) · [Bahasa Indonesia](docs/id/CONTRIBUTING.md) · [Italiano](docs/it/CONTRIBUTING.md) · [日本語](docs/ja/CONTRIBUTING.md) · [한국어](docs/ko/CONTRIBUTING.md) · [Nederlands](docs/nl/CONTRIBUTING.md) · [Polski](docs/pl/CONTRIBUTING.md) · [Português](docs/pt/CONTRIBUTING.md) · [Русский](docs/ru/CONTRIBUTING.md) · [Svenska](docs/sv/CONTRIBUTING.md) · [ไทย](docs/th/CONTRIBUTING.md) · [Türkçe](docs/tr/CONTRIBUTING.md) · [Українська](docs/uk/CONTRIBUTING.md) · [Tiếng Việt](docs/vi/CONTRIBUTING.md) · [中文](docs/zh/CONTRIBUTING.md)

---

# Contributing

Thanks for your interest in VD Google. Contributions are welcome: translations, bug
reports, documentation and code.

## Ground rules

- **VD Google reads the truth and never fakes anything.** Contributions must keep it
  that way. A real negative stays negative, a stale cache is labelled cached, a missing
  value is Unknown. Do not soften a reading to make it look nicer.
- **Privacy is absolute.** The app declares no permissions and the WebUI contacts no
  remote origin. Never add a permission, a network call, or anything that sends data off
  the device.
- **The app and the WebUI are identical.** Any change to what is shown, or to the
  exported report, must land in both, with the same wording. They must never drift.
- **Labels live once, in 21 languages.** Every user-facing string is defined for the
  app (`res/values*/strings.xml`) and the WebUI (`webroot/lang/`). If you add or change
  a label, update all languages.

## Translations

Translations are the easiest and most valuable contribution. The readme is translated
per language (`README.<lang>.md`), the app and WebUI strings per language, and this
documentation under `docs/<lang>/`. Keep terminology consistent within a language, match
the existing tone, and never machine-translate a technical term you are unsure of.

## Reporting a bug

Open an issue with your device model, Android version, and, if it is about a reading,
an **export** (it contains no personal data). The export's diagnostics section is built
for exactly this.

## Code

- Build release, never debug. Test on a real, rooted device.
- Keep comments out of the code, names explain intent. The only header is the license
  banner at the top of each source file.
- Match the existing style and keep changes focused.

## License

By contributing you agree that your contribution is licensed under the project's
**GNU AGPL-3.0-or-later**.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome, see [CONTRIBUTING.md](CONTRIBUTING.md)._
