<!--
  VD GOOGLE - RCS, Google Wallet a atestace Play Integrity, čtené v zařízení.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Vaše služby Google, čtené v zařízení. Nikdy internet.**

VD Google vám ukáže, co si sám Google ve vašem telefonu zaznamenává o třech věcech, a vysvětlí, co jednotlivé položky znamenají, aniž by cokoli kamkoli posílal.

> **Jazyky:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Co zobrazuje

### RCS
Zda je RCS dostupné pro vaši linku, vaše číslo RCS, stav RCS, verze konfigurace, kolik vašich kontaktů podporuje RCS, oprávnění operátora, přítomnost (UCE) a související časové značky (provisioning, synchronizace, poslední zpráva, vypršení tokenu a další).

### Google Wallet
Zda je vaše zařízení atestováno pro platby, zda má hardware NFC, zda je Wallet nainstalován, stav platební služby a placení přiložením, zda je váš zámek obrazovky přijat a zda byla někdy přidána karta.

### Atestace (Play Integrity)
Aplikace, které nedávno požádaly Google Play Integrity, aby se za vaše zařízení zaručil, každá se svou ikonou, názvem, úplným časem poslední žádosti a počtem žádostí v aktuálním okně.

Každá položka obsahuje krátké vysvětlení srozumitelným jazykem, co skutečně znamená.

## Soukromí: žádný internet

VD Google nedeklaruje **vůbec žádná oprávnění**, dokonce ani přístup k internetu. Nic z toho, co čte, nikdy neopustí vaše zařízení. Můžete si to ověřit v manifestu a ve zdrojovém kódu.

## Požadavky

- Android 8.0 (API 26) nebo novější.
- **Je vyžadován root.** Výše uvedené informace se nacházejí v chráněných oblastech Androidu přístupných pouze systému. VD Google je čte s přístupem superuživatele a nic jiného s nimi nedělá. Bez rootu aplikace pouze zobrazí obrazovku vysvětlující, proč je root potřeba.

## Časové značky

Všechny časy se zobrazují v aktuálním časovém pásmu vašeho zařízení a pásmo je vždy uvedeno.

## Kontakty

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Licence

**GNU AGPL-3.0-or-later** - viz [LICENSE](LICENSE). Copyleft včetně síťové klauzule: kdo provozuje upravenou verzi (i jako službu), musí nabídnout její zdrojový kód. Zvoleno záměrně, aby forky zůstaly otevřené.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
