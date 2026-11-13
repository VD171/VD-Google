<!--
  VD GOOGLE: RCS, Google Wallet och Play Integrity-attesteringar, lästa på enheten.
  Copyright (C) 2026  VD171. SPDX-License-Identifier: AGPL-3.0-or-later
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
  <b>Dina Google-tjänster, lästa på din enhet. Aldrig internet.</b><br>
  VD Google visar vad Google självt registrerar i din telefon om RCS, Wallet och Play Integrity, och förklarar vad varje post betyder, utan att skicka något någonstans.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/sv-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/sv-USER-GUIDE">Användarguide</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/sv-ARCHITECTURE">Arkitektur</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/sv-PRIVACY">Integritet</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/sv-BINARIES">Medföljande binärer</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/sv-FILE-STRUCTURE">Filstruktur</a> ·
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

## App eller WebUI

Varje version finns i två former, och båda läser samma saker på din enhet, utan internet:

- **App**: `.apk`-filen, en vanlig Android-app.
- **WebUI**: `.zip`-filen, en modul som du flashar i din root-hanterare (KernelSU, Magisk eller APatch) och öppnar därifrån.

I båda fallen krävs root.

## Vad den visar

### RCS
Om RCS är tillgängligt för din linje, ditt RCS-nummer, RCS-tillståndet, konfigurationsversionen, hur många av dina kontakter som stöder RCS, operatörsbehörigheten, närvaron (UCE) och de relaterade tidsstämplarna (provisionering, synkronisering, senaste meddelande, tokenutgång med mera). Visar också om Gemini i Google Messages är redo: Magic Compose (Geminis skrivhjälp), AI-motorn på enheten (AICore, som kör Gemini Nano) och Gemini-chatten, som levereras via RCS.

### Google Wallet
Om din enhet är attesterad för betalningar, om den har NFC-hårdvara, om Wallet är installerat, tillståndet för betaltjänsten och betalning genom att hålla mot, om ditt skärmlås accepteras och om ett kort någonsin har lagts till.

### DroidGuard
Om Google Play känner igen den här enheten som certifierad. Det är Play Protect-certifieringen som visas i Play Butik. Attesteringar som den här enheten har cachat för Play Integrity och DroidGuard. Tiden som visas är när var och en upphör att gälla.

### Attesteringar (Play Integrity)
Apparna som nyligen bad Google Play Integrity att gå i god för din enhet, var och en med sin ikon, sitt namn, den fullständiga tiden för sin senaste begäran och hur många begäranden den gjorde i det aktuella fönstret.

Varje post åtföljs av en kort förklaring på vanligt språk om vad den egentligen betyder.

## Integritet: inget internet

VD Google deklarerar **inga behörigheter alls**, inte ens internetåtkomst. Inget av det den läser lämnar någonsin din enhet. Du kan kontrollera detta i manifestet och i källkoden.

## Krav

- Android 8.0 (API 26) eller senare.
- **Root krävs.** Informationen ovan finns i skyddade områden i Android som endast systemet kommer åt. VD Google läser den med superanvändaråtkomst och gör inget annat med den. Utan root visar appen bara en skärm som förklarar varför root behövs.

## Tidsstämplar

Alla tider visas i enhetens aktuella tidszon, och tidszonen anges alltid.

## Dokumentation

- **[Wiki](https://github.com/VD171/VD-Google/wiki/sv-Home)**: all dokumentation, läsbar online.
- **[Användarguide](https://github.com/VD171/VD-Google/wiki/sv-USER-GUIDE)**: vad varje skärm läser och vad varje resultat betyder.
- **[Arkitektur](https://github.com/VD171/VD-Google/wiki/sv-ARCHITECTURE)** och **[Filstruktur](https://github.com/VD171/VD-Google/wiki/sv-FILE-STRUCTURE)**: hur det fungerar och var allt finns.
- **[Integritet](https://github.com/VD171/VD-Google/wiki/sv-PRIVACY)**: inga internetbehörigheter, varje läsning går via root, inget lämnar någonsin enheten.
- **[Medföljande binärer](https://github.com/VD171/VD-Google/wiki/sv-BINARIES)**: ursprung och SHA-256 för varje förbyggd binär, för fullständig granskning.
- **[Bidra](https://github.com/VD171/VD-Google/wiki/sv-CONTRIBUTING)** och **[Säkerhet](https://github.com/VD171/VD-Google/wiki/sv-SECURITY)**.
- **[Ändringslogg](CHANGELOG.md)** och **[Uppförandekod](https://github.com/VD171/VD-Google/wiki/sv-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Se även

Systerprojekt av samma författare:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: en enhetsmetod-debugger som läser varje post genom varje metod som kan läsa den och jämför dem sida vid sida.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: fullständig säkerhetskopiering och återställning av appar och data som root, helt styrd från en WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: en enhetsspoofer som förvandlar ett nytt fingeravtryck till en sammanhängande, konsekvent profil.
- **[VD-dropbear](https://github.com/VD171/VD-dropbear)**: en fristående SSH-daemon (Dropbear) med root.

## Contacts

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Licens

**GNU AGPL-3.0-or-later**: se [LICENSE](LICENSE). Copyleft, inklusive nätverksklausulen: den som kör en modifierad version (även som tjänst) måste erbjuda dess källkod. Vald med avsikt för att hålla forkar öppna.

## Varumärken

VD Google är ett oberoende, inofficiellt projekt. Det är inte anslutet till, godkänt av eller sponsrat av Google LLC. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" och relaterade namn och logotyper är varumärken som tillhör Google LLC. VD Google läser endast det som redan finns på din enhet och skickar ingenting någonstans.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
