> **Languages:** English · [العربية](ar/ARCHITECTURE.md) · [Čeština](cs/ARCHITECTURE.md) · [Deutsch](de/ARCHITECTURE.md) · [Español](es/ARCHITECTURE.md) · [فارسی](fa/ARCHITECTURE.md) · [Français](fr/ARCHITECTURE.md) · [हिन्दी](hi/ARCHITECTURE.md) · [Bahasa Indonesia](in/ARCHITECTURE.md) · [Italiano](it/ARCHITECTURE.md) · [日本語](ja/ARCHITECTURE.md) · [한국어](ko/ARCHITECTURE.md) · [Nederlands](nl/ARCHITECTURE.md) · [Polski](pl/ARCHITECTURE.md) · [Português](pt/ARCHITECTURE.md) · [Русский](ru/ARCHITECTURE.md) · [Svenska](sv/ARCHITECTURE.md) · [ไทย](th/ARCHITECTURE.md) · [Türkçe](tr/ARCHITECTURE.md) · [Українська](uk/ARCHITECTURE.md) · [Tiếng Việt](vi/ARCHITECTURE.md) · [中文](zh/ARCHITECTURE.md)

---

# Architecture

How VD Google is built and why. This page explains the design and the principles. It
does not list the exact files, tables or queries each reader uses - that lives only in
the source, by design, so the app and the documentation can never drift into a
how-to for someone who wants to fake the same values.

## Design goals

1. **Read the truth.** Show what Google's own apps already recorded, explain it, and
   never invent a value. When the device holds no answer, say Unknown.
2. **Zero network.** The app declares no permissions at all; the WebUI blocks every
   remote origin. Nothing is sent anywhere, ever.
3. **One behaviour, two faces.** The app and the WebUI module are kept absolutely
   identical - same signals, same wording, same exported report.
4. **Honest over flattering.** A real negative is shown as negative; a stale cache is
   labelled as cached; a missing value is Unknown. Detection is never softened to look
   nicer.

## The two faces

- **The app** is Kotlin with Jetpack Compose (Material 3). It gets root through the
  libsu `RootService`, never by shelling out to `su` directly, and it reads inside that
  privileged service.
- **The WebUI module** is a small HTML/JS web root served by your root manager
  (KernelSU, Magisk or APatch). It performs the same reads through the manager's root
  bridge. A strict Content-Security-Policy forbids any remote origin, so the page cannot
  phone home even in principle.

Both are generated from one shared set of strings in 21 languages, so a label is never
translated twice.

## Reading databases: the bundled sqlite3

Some of the state VD Google reads lives in SQLite databases that belong to Google's
apps. Android ships its own SQLite, but the framework SQLite is unreliable for opening
another app's database copied aside: on some manufacturer ROMs it fails for *every*
database, returning nothing. To read the same way on every device, VD Google bundles
its own `sqlite3` binary (arm64 and arm32) and runs queries through it as root. The app
and the WebUI use the identical binary, so they always read the same engine and the same
result. The provenance and checksums of that binary are documented in
[Bundled binaries](BINARIES.md).

Reads copy the database aside before opening it, never touching the live file, and never
copy the shared-memory index, which a running database can report as malformed.

## The RCS signal

The hardest part of reading RCS honestly is that the obvious flags lie. The simple
"available" flag, the feature flags and the provisioning state can all read negative on
a line where RCS is working perfectly, because they are caches or because the line runs
on Google's own backend (Jibe) rather than the carrier's IMS network. VD Google computes
a single **Connection** verdict from the real chat configuration and the provisioning
engine, which stays correct across Android and Google Messages versions, and presents
the lying flags clearly labelled as cached so they inform without misleading. See the
[user guide](USER-GUIDE.md) for what each line means.

## The export

The export is a sanitized JSON snapshot. A sanitizer runs before anything is written: it
drops keys and values that look like credentials, redacts long digit runs, and never
emits your phone number, message content, contact names or raw capability blobs. The
optional diagnostics section carries only schema names, counts, sizes and versions - and
even there, any per-identity fingerprint embedded in a name is collapsed away. The app
and the WebUI produce the same schema.

## What VD Google never does

- It never declares a network permission, and the WebUI never contacts a remote origin.
- It never fabricates, spoofs or hides a value.
- It never reports a Play Integrity verdict, because that verdict is not on the device.
- It changes state only through an explicit, confirmed, locally logged action.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome; see [CONTRIBUTING.md](CONTRIBUTING.md)._
