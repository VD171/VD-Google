> **Languages:** English · [العربية](ar/USER-GUIDE.md) · [Čeština](cs/USER-GUIDE.md) · [Deutsch](de/USER-GUIDE.md) · [Español](es/USER-GUIDE.md) · [فارسی](fa/USER-GUIDE.md) · [Français](fr/USER-GUIDE.md) · [हिन्दी](hi/USER-GUIDE.md) · [Bahasa Indonesia](in/USER-GUIDE.md) · [Italiano](it/USER-GUIDE.md) · [日本語](ja/USER-GUIDE.md) · [한국어](ko/USER-GUIDE.md) · [Nederlands](nl/USER-GUIDE.md) · [Polski](pl/USER-GUIDE.md) · [Português](pt/USER-GUIDE.md) · [Русский](ru/USER-GUIDE.md) · [Svenska](sv/USER-GUIDE.md) · [ไทย](th/USER-GUIDE.md) · [Türkçe](tr/USER-GUIDE.md) · [Українська](uk/USER-GUIDE.md) · [Tiếng Việt](vi/USER-GUIDE.md) · [中文](zh/USER-GUIDE.md)

---

# VD Google user guide

A friendly, complete walkthrough of VD Google: what it shows, what every line means,
and how to read your device honestly. VD Google reads the state of RCS, Google Wallet
and the Play Integrity attestations that apps request, and explains each item in plain
words. It never touches the internet, and it never changes anything unless you ask.

## Table of contents

- [1. What VD Google is](#1-what-vd-google-is)
- [2. What it is not](#2-what-it-is-not)
- [3. Requirements](#3-requirements)
- [4. App or WebUI](#4-app-or-webui)
- [5. Install and first run](#5-install-and-first-run)
- [6. The tabs](#6-the-tabs)
  - [6.1 About](#61-about)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Attestations](#65-attestations)
- [7. Export your report](#7-export-your-report)
- [8. Actions that change state](#8-actions-that-change-state)
- [9. Language and theme](#9-language-and-theme)
- [10. Frequently asked questions](#10-frequently-asked-questions)

## 1. What VD Google is

VD Google is a reader. It looks at what Google's own apps already store on your device
about three things, and it explains each one so you actually understand it:

- **RCS**: whether modern chat is really working on your line, who you can chat with,
  and whether Gemini in Google Messages is ready.
- **Google Wallet**: whether your device is set up and attested for contactless
  payments.
- **Play Integrity attestations**: which apps have asked Android to prove your device
  is genuine, and whether Google currently certifies it.

Everything is read locally, through root, and shown with an explanation. No value is
invented: when the device does not hold an answer, VD Google says "Unknown" rather
than guessing.

## 2. What it is not

**VD Google is not an integrity checker.** It does not run or report any basic /
device / strong verdict. That result lives only inside the encrypted token an app
sends to its own server, and is never stored on the device, so no honest reader can
show it. Tools that claim to display your "Play Integrity verdict" locally are showing
you a cached guess or a request they made themselves, not the real answer.

VD Google also does not spoof, hide or fake anything. It reads the truth. If a value
looks wrong to you, the goal of this guide is to explain why the truth looks that way.

## 3. Requirements

- **Android 8.0 (API 26) or newer.**
- **Root.** The data VD Google reads lives in protected, system-only areas of Android,
  reachable only with root. Any modern root solution works: KernelSU, KernelSU Next,
  APatch or Magisk.
- **No internet.** VD Google declares no permissions at all, so there is nothing to
  allow and nothing to block.

## 4. App or WebUI

VD Google ships two ways, and they show exactly the same information:

- **The app (APK).** A normal Android app you install and open. Asks for root the
  first time, like any root app.
- **The WebUI module (ZIP).** Flash it in your root manager (KernelSU, APatch or
  Magisk) and open it from there. Nothing is installed as an app; it runs inside your
  root manager's WebUI.

Pick whichever you prefer. The screens, the wording and the exported report are
identical between the two.

## 5. Install and first run

**App:** install the APK and open it. The first time, grant root when your root
manager asks. If you deny or dismiss root, VD Google shows a screen explaining why
root is required and offers to try again.

**WebUI:** flash the ZIP as a module in your root manager, then open the module's
WebUI from the manager. It reads as soon as it opens.

On the first open you can pick your language; you can change it later from the gear
icon at the top.

## 6. The tabs

Across the bottom are five tabs: About, DroidGuard, RCS, Wallet and Attestations.
About opens first.

### 6.1 About

What the app is, the privacy promise, the version, and links. Read the privacy section
once: it is the whole point of the app.

### 6.2 RCS

This is the richest tab. Timestamps are always shown in your device's time zone, with
the zone labelled.

- **Connection**: the one line that tells the truth. It is computed from the real chat
  configuration and the provisioning engine, so it says Connected when RCS is actually
  working on your line, even on newer Android where the older flags lie. **When in
  doubt, trust this line.**
- **Carrier**: the mobile carrier for your line (name and MCC/MNC), taken from your
  data SIM on dual-SIM phones.
- **Availability flag (cached)**: a cached flag the messaging app keeps for your own
  number. It can stay out of date after RCS is already working, so a "No" here does not
  mean RCS is off. Trust the Connection line above.
- **Your RCS number**: the number registered for RCS, masked by default. Tap the eye
  to reveal it.
- **Provisioning state (cached)** and **Configuration version**: internal values the
  messaging app keeps. They can read 0 even while RCS works. See the note on carrier vs
  Google's backend below.
- **RCS-capable contacts**: how many of your contacts are currently known to support
  RCS chat.
- **Feature flag (cached)**: whether the messaging app has the RCS feature switched on;
  cached, so it can lag.
- **Carrier entitlement**, **Presence (UCE)**, **IMS service present**: these describe
  the *carrier's* RCS path (the operator's IMS network). A "No" or "0" here is normal
  when your RCS runs through Google's own backend instead of your carrier. See below.
- **Gemini**: whether Gemini in Google Messages is ready: **Magic Compose** (Gemini's
  writing help), **AICore** (the on-device engine that runs Gemini Nano), and **Gemini
  chat**, which is delivered over RCS and so needs RCS connected.
- **Timestamps**: provisioning, sync, last message, token expiry and more.

**Carrier RCS vs Google's backend.** There are two ways RCS reaches your phone. One is
your *carrier's* RCS over their IMS network; the other is *Google's own backend* (Jibe),
which works over the top, independent of the carrier. If your RCS runs through Google's
backend, as it does for most people, then the carrier-side lines (carrier entitlement,
presence/UCE, IMS service, configuration version) are genuinely "No" or "0", and that is
correct, not a stale cache. Your RCS can have worked for years this way. The Connection
line is what tells you it is working.

### 6.3 Wallet

- **Device attested**: whether Google judged this device genuine and secure enough to
  hold payment cards. It is the last result Google saved on this device, so it can lag
  the Wallet app until Google re-checks. If the saved result is missing, VD Google shows
  **Unknown** rather than a misleading "No".
- **Last checked**: when that security check last completed.
- **NFC hardware**, **Wallet installed**, **Wallet service**, **Tap and pay**,
  **Screen lock accepted**, **Card ever added**: the pieces that make contactless
  payment possible.

### 6.4 DroidGuard

- **Play Protect certified**: whether Google Play recognizes your device as certified
  (the certification shown in the Play Store).
- **Attestation cache**: the Play Integrity and DroidGuard attestations your device has
  cached, each with when it expires.

### 6.5 Attestations

The history of which apps requested Play Integrity on your device: icon, name, when they
last asked, and how many times. This lets you tell whether a given app checks for
integrity or not. Use the search box to filter by app name or package.

## 7. Export your report

The download icon writes a **JSON** report with your device, RCS, Wallet and attestation
state. You can share it or save it as a file. The report contains **no phone number, no
credentials and no personal content**: it is sanitized before it is written. It also
includes an optional **diagnostics** section (schema names, counts and versions, never
your data) that helps troubleshoot device-specific issues. The app and WebUI write the
same report.

## 8. Actions that change state

VD Google is mostly read-only, but it can perform one **sanitizing action**: clearing
Google Wallet's cached attestation result, which lets a future re-check start fresh.
Every action that changes state asks you to confirm first, and is written to a local
JSON log on the device. Nothing leaves your phone.

## 9. Language and theme

VD Google speaks 21 languages. Pick yours on first run, or change it any time from the
gear icon (also available on the root-denied screen). The brightness icon switches
between light and dark.

## 10. Frequently asked questions

**RCS says Connected but several lines say No or 0. Why?** Because your RCS runs through
Google's own backend, not your carrier. The carrier-side lines are correctly negative.
See [6.2 RCS](#62-rcs).

**Device attested says Unknown.** The cached Wallet result is missing on your device.
That is honest. VD Google will not show a "No" it cannot back up.

**Where is my Play Integrity verdict?** It is not on your device. See
[2. What it is not](#2-what-it-is-not).

**Does it work without root?** No. The data lives in system-only areas.

<!-- doc-footer -->

---

> _This documentation was generated with Claude (Anthropic) and is maintained by VD171. Corrections and improvements are welcome; see [CONTRIBUTING.md](CONTRIBUTING.md)._
