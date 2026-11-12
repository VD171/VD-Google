> **Languages:** English · [العربية](ar/SECURITY.md) · [Čeština](cs/SECURITY.md) · [Deutsch](de/SECURITY.md) · [Español](es/SECURITY.md) · [فارسی](fa/SECURITY.md) · [Français](fr/SECURITY.md) · [हिन्दी](hi/SECURITY.md) · [Bahasa Indonesia](in/SECURITY.md) · [Italiano](it/SECURITY.md) · [日本語](ja/SECURITY.md) · [한국어](ko/SECURITY.md) · [Nederlands](nl/SECURITY.md) · [Polski](pl/SECURITY.md) · [Português](pt/SECURITY.md) · [Русский](ru/SECURITY.md) · [Svenska](sv/SECURITY.md) · [ไทย](th/SECURITY.md) · [Türkçe](tr/SECURITY.md) · [Українська](uk/SECURITY.md) · [Tiếng Việt](vi/SECURITY.md) · [中文](zh/SECURITY.md)

---

# Security policy

VD Google is a privacy tool. It runs as root and reads sensitive state, so security
reports are taken seriously.

## Reporting a vulnerability

Please report security issues **privately**, not in a public issue:

- E-mail **vd.priv8@pm.me**.
- Describe the problem, the affected version, and how to reproduce it.
- If it concerns data exposure, describe the class of problem; you do not need to attach
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
  [user guide](USER-GUIDE.md).
- The absence of a Play Integrity verdict. That verdict is not on the device and VD
  Google will not invent it.

## Supported versions

Only the latest released version is supported. Please reproduce on the latest build
before reporting.
