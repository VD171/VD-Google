<!--
  VD GOOGLE - RCS, Google Wallet en Play Integrity-attestaties, gelezen op het toestel.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Je Google-diensten, gelezen op je toestel. Nooit internet.**

VD Google laat je zien wat Google zelf op je telefoon vastlegt over drie dingen, en legt uit wat elk item betekent, zonder iets ergens naartoe te sturen.

> **Talen:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Wat het toont

### RCS
Of RCS beschikbaar is voor je lijn, je RCS-nummer, de RCS-status, de configuratieversie, hoeveel van je contacten RCS ondersteunen, de providerrechten, de aanwezigheid (UCE) en de bijbehorende tijdstempels (provisioning, synchronisatie, laatste bericht, tokenverval en meer).

### Google Wallet
Of je toestel geattesteerd is voor betalingen, of het NFC-hardware heeft, of Wallet geïnstalleerd is, de status van de betaaldienst en van tikken om te betalen, of je schermvergrendeling geaccepteerd is, en of er ooit een kaart is toegevoegd.

### Attestaties (Play Integrity)
De apps die onlangs Google Play Integrity hebben gevraagd om voor je toestel in te staan, elk met zijn pictogram, zijn naam, de volledige tijd van het laatste verzoek en hoeveel verzoeken het in het huidige venster deed.

Elk item bevat een korte uitleg in gewone taal van wat het echt betekent.

## Privacy: geen internet

VD Google vraagt **helemaal geen toestemmingen**, zelfs geen internettoegang. Niets van wat het leest verlaat ooit je toestel. Je kunt dit controleren in het manifest en in de broncode.

## Vereisten

- Android 8.0 (API 26) of nieuwer.
- **Root is vereist.** De bovenstaande informatie bevindt zich in beschermde, alleen voor het systeem toegankelijke gebieden van Android. VD Google leest ze met superuser-toegang en doet er verder niets mee. Zonder root toont de app alleen een scherm dat uitlegt waarom root nodig is.

## Tijdstempels

Alle tijden worden getoond in de huidige tijdzone van je toestel, en de tijdzone wordt altijd vermeld.

## Contacten

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Licentie

**GNU AGPL-3.0-or-later** - zie [LICENSE](LICENSE). Copyleft, inclusief de netwerkclausule: wie een gewijzigde versie draait (ook als dienst) moet de broncode aanbieden. Bewust gekozen om forks open te houden.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
