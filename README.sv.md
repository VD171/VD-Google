<!--
  VD GOOGLE - RCS, Google Wallet och Play Integrity-attesteringar, lästa på enheten.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Dina Google-tjänster, lästa på din enhet. Aldrig internet.**

VD Google visar vad Google självt registrerar i din telefon om tre saker, och förklarar vad varje post betyder, utan att skicka något någonstans.

> **Språk:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Vad den visar

### RCS
Om RCS är tillgängligt för din linje, ditt RCS-nummer, RCS-tillståndet, konfigurationsversionen, hur många av dina kontakter som stöder RCS, operatörsbehörigheten, närvaron (UCE) och de relaterade tidsstämplarna (provisionering, synkronisering, senaste meddelande, tokenutgång med mera).

### Google Wallet
Om din enhet är attesterad för betalningar, om den har NFC-hårdvara, om Wallet är installerat, tillståndet för betaltjänsten och betalning genom att hålla mot, om ditt skärmlås accepteras och om ett kort någonsin har lagts till.

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

## Kontakter

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Licens

**GNU AGPL-3.0-or-later** - se [LICENSE](LICENSE). Copyleft, inklusive nätverksklausulen: den som kör en modifierad version (även som tjänst) måste erbjuda dess källkod. Vald med avsikt för att hålla forkar öppna.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
