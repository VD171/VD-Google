> **Languages:** English · [العربية](ar/AI-AGENTS.md) · [Čeština](cs/AI-AGENTS.md) · [Deutsch](de/AI-AGENTS.md) · [Español](es/AI-AGENTS.md) · [فارسی](fa/AI-AGENTS.md) · [Français](fr/AI-AGENTS.md) · [हिन्दी](hi/AI-AGENTS.md) · [Bahasa Indonesia](id/AI-AGENTS.md) · [Italiano](it/AI-AGENTS.md) · [日本語](ja/AI-AGENTS.md) · [한국어](ko/AI-AGENTS.md) · [Nederlands](nl/AI-AGENTS.md) · [Polski](pl/AI-AGENTS.md) · [Português](pt/AI-AGENTS.md) · [Русский](ru/AI-AGENTS.md) · [Svenska](sv/AI-AGENTS.md) · [ไทย](th/AI-AGENTS.md) · [Türkçe](tr/AI-AGENTS.md) · [Українська](uk/AI-AGENTS.md) · [Tiếng Việt](vi/AI-AGENTS.md) · [中文](zh/AI-AGENTS.md)

---

# AI agents

VD Google turns Google's own on-device records into something an **AI agent can read in one step**. Instead of opening several Google apps and databases and guessing at their meaning, an agent reads a single structured **export** and knows exactly what the device reports about RCS, Google Wallet and Play Integrity.

This page expands on the idea introduced in the README.

## The export

Both the App and the WebUI produce the same JSON export:

- **App**: the **Export** action shares or saves the report.
- **WebUI**: the **Export** action writes it to `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

They are the same schema, because the app and the WebUI are kept identical. Nothing is sent anywhere: the export is written on the device and stays there.

## What is in it

The export is one JSON object with these top-level keys:

- `app`, `version`, `exportedAt`, `timezone` - what produced the report and when, in the device's labelled timezone.
- `device` - manufacturer, model, Android release and SDK level.
- `rcs` - availability, state, configuration version, capable contacts, carrier entitlement, presence (UCE) and the related timestamps.
- `wallet` - payment attestation, NFC hardware, the tap-to-pay state, screen-lock acceptance and whether a card was ever added.
- `diagnostics` - a self-check section, built for bug reports.

The export carries **no personal data**: no phone number, no credential, no message content, no contact name. Every value is the honest reading - a real negative stays negative, a stale cache is labelled cached, a missing value is Unknown.

## Read-only, with one logged action

VD Google reads. The one exception is a sanitizing action (clearing Google Wallet's cached attestation result to force a re-check). Every state-changing action asks for an on-device confirmation and is written to a local JSON action log. An agent driving the WebUI sees the same confirmation and the same log, there is no hidden, unlogged path.

## No network contract

VD Google exposes no network API and no socket: it declares no permissions at all. An agent consumes the **export file**, it does not call a service. That is the privacy guarantee, kept even for automation.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome, see [CONTRIBUTING.md](../CONTRIBUTING.md)._
