> **Languages:** English · [العربية](ar/CONTRIBUTING.md) · [Čeština](cs/CONTRIBUTING.md) · [Deutsch](de/CONTRIBUTING.md) · [Español](es/CONTRIBUTING.md) · [فارسی](fa/CONTRIBUTING.md) · [Français](fr/CONTRIBUTING.md) · [हिन्दी](hi/CONTRIBUTING.md) · [Bahasa Indonesia](in/CONTRIBUTING.md) · [Italiano](it/CONTRIBUTING.md) · [日本語](ja/CONTRIBUTING.md) · [한국어](ko/CONTRIBUTING.md) · [Nederlands](nl/CONTRIBUTING.md) · [Polski](pl/CONTRIBUTING.md) · [Português](pt/CONTRIBUTING.md) · [Русский](ru/CONTRIBUTING.md) · [Svenska](sv/CONTRIBUTING.md) · [ไทย](th/CONTRIBUTING.md) · [Türkçe](tr/CONTRIBUTING.md) · [Українська](uk/CONTRIBUTING.md) · [Tiếng Việt](vi/CONTRIBUTING.md) · [中文](zh/CONTRIBUTING.md)

---

# Contributing

Thanks for your interest in VD Google. Contributions are welcome: translations, bug
reports, documentation and code.

## Ground rules

- **VD Google reads the truth and never fakes anything.** Contributions must keep it
  that way. A real negative stays negative; a stale cache is labelled cached; a missing
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
- Keep comments out of the code; names explain intent. The only header is the license
  banner at the top of each source file.
- Match the existing style and keep changes focused.

## License

By contributing you agree that your contribution is licensed under the project's
**GNU AGPL-3.0-or-later**.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome; see [CONTRIBUTING.md](CONTRIBUTING.md)._
