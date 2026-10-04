<!--
  VD GOOGLE - RCS, Google Wallet und Play-Integrity-Attestierungen, auf dem Gerät gelesen.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Deine Google-Dienste, direkt auf dem Gerät gelesen. Niemals Internet.**

VD Google zeigt dir, was Google selbst auf deinem Telefon zu drei Dingen festhält, und erklärt, was jeder Punkt bedeutet, ohne irgendetwas irgendwohin zu senden.

> **Sprachen:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Was es anzeigt

### RCS
Ob RCS für deine Leitung verfügbar ist, deine RCS-Nummer, der RCS-Zustand, die Konfigurationsversion, wie viele deiner Kontakte RCS unterstützen, die Anbieter-Berechtigung, die Präsenz (UCE) und die zugehörigen Zeitstempel (Bereitstellung, Synchronisierung, letzte Nachricht, Token-Ablauf und mehr).

### Google Wallet
Ob dein Gerät für Zahlungen attestiert ist, ob es NFC-Hardware hat, ob Wallet installiert ist, der Zustand des Zahlungsdienstes und des Bezahlens durch Auflegen, ob deine Bildschirmsperre akzeptiert wird und ob jemals eine Karte hinzugefügt wurde.

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

## Kontakte

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Lizenz

**GNU AGPL-3.0-or-later** - siehe [LICENSE](LICENSE). Copyleft, einschließlich der Netzwerk-Klausel: wer eine modifizierte Version betreibt (auch als Dienst), muss ihren Quellcode anbieten. Bewusst gewählt, um Forks offen zu halten.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
