<!--
  VD GOOGLE - RCS, Google Wallet und Play-Integrity-Attestierungen, auf dem Gerät gelesen.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
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
  <b>Deine Google-Dienste, direkt auf dem Gerät gelesen. Niemals Internet.</b><br>
  VD Google zeigt dir, was Google selbst auf deinem Telefon zu drei Dingen festhält, und erklärt, was jeder Punkt bedeutet, ohne irgendetwas irgendwohin zu senden.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/USER-GUIDE">User guide</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ARCHITECTURE">Architecture</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/PRIVACY">Privacy</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/BINARIES">Bundled binaries</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/FILE-STRUCTURE">File structure</a> ·
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

## App oder WebUI

Jede Version gibt es in zwei Formen, und beide lesen dieselben Dinge auf deinem Gerät, ohne Internet:

- **App** - die `.apk`, eine normale Android-App.
- **WebUI** - die `.zip`, ein Modul, das du in deinem Root-Manager (KernelSU, Magisk oder APatch) flashst und von dort öffnest.

In beiden Fällen ist Root erforderlich.

## Was es anzeigt

### RCS
Ob RCS für deine Leitung verfügbar ist, deine RCS-Nummer, der RCS-Zustand, die Konfigurationsversion, wie viele deiner Kontakte RCS unterstützen, die Anbieter-Berechtigung, die Präsenz (UCE) und die zugehörigen Zeitstempel (Bereitstellung, Synchronisierung, letzte Nachricht, Token-Ablauf und mehr). Zeigt auch, ob Gemini in Google Messages bereit ist: Magic Compose (Geminis Schreibhilfe), die KI-Engine auf dem Gerät (AICore, auf dem Gemini Nano läuft) und der Gemini-Chat, der über RCS bereitgestellt wird.

### Google Wallet
Ob dein Gerät für Zahlungen attestiert ist, ob es NFC-Hardware hat, ob Wallet installiert ist, der Zustand des Zahlungsdienstes und des Bezahlens durch Auflegen, ob deine Bildschirmsperre akzeptiert wird und ob jemals eine Karte hinzugefügt wurde.

### DroidGuard
Ob Google Play dieses Gerät als zertifiziert erkennt. Das ist die Play Protect-Zertifizierung, die im Play Store angezeigt wird. Attestierungen, die dieses Gerät für Play Integrity und DroidGuard zwischengespeichert hat. Die angezeigte Zeit ist der jeweilige Ablaufzeitpunkt.

### Attestierungen (Play Integrity)
Die Apps, die kürzlich Google Play Integrity gebeten haben, für dein Gerät zu bürgen, jeweils mit Symbol, Name, der vollständigen Zeit der letzten Anfrage und der Anzahl der Anfragen im aktuellen Zeitfenster.

Jeder Punkt enthält eine kurze, verständliche Erklärung, was er wirklich bedeutet.

## Datenschutz: kein Internet

VD Google deklariert **überhaupt keine Berechtigungen**, nicht einmal Internetzugriff. Nichts, was es liest, verlässt jemals dein Gerät. Du kannst das im Manifest und im Quellcode überprüfen.

## Voraussetzungen

- Android 8.0 (API 26) oder neuer.
- **Root ist erforderlich.** Die obigen Informationen liegen in geschützten, nur für das System zugänglichen Bereichen von Android. VD Google liest sie mit Superuser-Zugriff und tut sonst nichts damit. Ohne Root zeigt die App nur einen Bildschirm, der erklärt, warum Root nötig ist.

## Zeitstempel

Alle Zeiten werden in der aktuellen Zeitzone deines Geräts angezeigt, und die Zeitzone wird immer angegeben.

## Dokumentation

- **[Wiki](https://github.com/VD171/VD-Google/wiki/de-Home)**: die gesamte Dokumentation, online durchsuchbar.
- **[Benutzerhandbuch](https://github.com/VD171/VD-Google/wiki/de-USER-GUIDE)**: was jeder Bildschirm anzeigt und was jedes Ergebnis bedeutet.
- **[Architektur](https://github.com/VD171/VD-Google/wiki/de-ARCHITECTURE)** und **[Dateistruktur](https://github.com/VD171/VD-Google/wiki/de-FILE-STRUCTURE)**: wie es funktioniert und wo alles liegt.
- **[Datenschutz](https://github.com/VD171/VD-Google/wiki/de-PRIVACY)**: keine Internetberechtigung, jede Lesung läuft über Root, nichts verlässt jemals das Gerät.
- **[Mitgelieferte Binärdateien](https://github.com/VD171/VD-Google/wiki/de-BINARIES)**: Herkunft und SHA-256 jeder vorkompilierten Binärdatei, für eine vollständige Prüfung.
- **[Mitwirken](https://github.com/VD171/VD-Google/wiki/de-CONTRIBUTING)** und **[Sicherheit](https://github.com/VD171/VD-Google/wiki/de-SECURITY)**.
- **[Changelog](CHANGELOG.md)** und **[Verhaltenskodex](https://github.com/VD171/VD-Google/wiki/de-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Siehe auch

Schwesterprojekte desselben Autors:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: ein Geräte-Methoden-Debugger, der jeden Punkt über jede Methode liest, die ihn lesen kann, und sie nebeneinander vergleicht.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: vollständige Sicherung und Wiederherstellung von Apps und Daten als Root, komplett über eine WebUI gesteuert.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: ein Geräte-Spoofer, der aus einem frischen Fingerprint ein kohärentes, konsistentes Profil macht.

## Kontakte

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Lizenz

**GNU AGPL-3.0-or-later** - siehe [LICENSE](LICENSE). Copyleft, einschließlich der Netzwerk-Klausel: wer eine modifizierte Version betreibt (auch als Dienst), muss ihren Quellcode anbieten. Bewusst gewählt, um Forks offen zu halten.

## Markenzeichen

VD Google ist ein unabhängiges, inoffizielles Projekt. Es steht in keiner Verbindung zu Google LLC und wird von Google LLC weder unterstützt noch gefördert. „Google", „Google Wallet", „Google Play", „Play Integrity", „Android" und zugehörige Namen und Logos sind Marken von Google LLC. VD Google liest nur das, was bereits auf deinem Gerät vorhanden ist, und sendet nichts irgendwohin.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
