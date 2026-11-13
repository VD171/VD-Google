> **Languages:** English · [العربية](ar/PRIVACY.md) · [Čeština](cs/PRIVACY.md) · [Deutsch](de/PRIVACY.md) · [Español](es/PRIVACY.md) · [فارسی](fa/PRIVACY.md) · [Français](fr/PRIVACY.md) · [हिन्दी](hi/PRIVACY.md) · [Bahasa Indonesia](in/PRIVACY.md) · [Italiano](it/PRIVACY.md) · [日本語](ja/PRIVACY.md) · [한국어](ko/PRIVACY.md) · [Nederlands](nl/PRIVACY.md) · [Polski](pl/PRIVACY.md) · [Português](pt/PRIVACY.md) · [Русский](ru/PRIVACY.md) · [Svenska](sv/PRIVACY.md) · [ไทย](th/PRIVACY.md) · [Türkçe](tr/PRIVACY.md) · [Українська](uk/PRIVACY.md) · [Tiếng Việt](vi/PRIVACY.md) · [中文](zh/PRIVACY.md)

---

# Privacy

Privacy is not a feature of VD Google. It is the whole point. This page states exactly
what the app does and does not do with your data, in plain terms you can verify.

## The promise

- **No internet permission is given.** The app's manifest declares no permissions at
  all: not internet, not storage, not contacts, nothing. There is nothing to allow.
- **Nothing is stored, sent or transmitted** to any file or server off your device.
- Every read happens **locally, through root**, and stays on the device.

## Why it needs root instead of permissions

The RCS, Wallet and attestation state VD Google explains lives in protected, system-only
areas of Android that normal apps cannot see. VD Google reads them with root rather than
by asking you for broad permissions. Root is the narrow, auditable way in; a pile of
runtime permissions would be the broad, leaky way. The app chooses the narrow one.

## The export is sanitized

When you export a report, VD Google sanitizes it before writing:

- **Never included:** your phone number, any credential, cookie or token, message
  content, contact names, or raw capability blobs.
- **Redacted:** long digit runs and values that look like secrets are replaced with a
  placeholder.
- **Diagnostics** carry only structure (schema names, counts, sizes, versions). Any
  per-identity fingerprint that happens to appear inside a name is collapsed away.

The report is yours. It is written where you choose, it is shared only if you share it,
and it contains nothing that identifies you.

## Actions are explicit and logged

VD Google can clear Google Wallet's cached attestation result so a future re-check
starts fresh. Any action that changes state:

1. asks you to confirm first, and
2. is written to a local JSON log on the device.

No action ever reaches the network, and nothing leaves your phone.

## Verify it yourself

You do not have to take any of this on trust:

- The source is public: <https://github.com/VD171/VD-Google>.
- The manifest is in the source; confirm it declares no permissions.
- The WebUI's Content-Security-Policy is in the source; confirm it blocks every remote
  origin.
- The bundled binaries are documented with checksums in [Bundled binaries](BINARIES.md).

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome; see [CONTRIBUTING.md](CONTRIBUTING.md)._
