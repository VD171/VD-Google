<!--
  VD GOOGLE - RCS, Google Wallet a atestace Play Integrity, čtené v zařízení.
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
  <b>Vaše služby Google, čtené v zařízení. Nikdy internet.</b><br>
  VD Google vám ukáže, co si sám Google ve vašem telefonu zaznamenává o třech věcech, a vysvětlí, co jednotlivé položky znamenají, aniž by cokoli kamkoli posílal.
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

## Aplikace nebo WebUI

Každé vydání je dostupné ve dvou podobách a obě čtou totéž ve vašem zařízení, bez internetu:

- **App** - soubor `.apk`, běžná aplikace pro Android.
- **WebUI** - soubor `.zip`, modul, který nainstalujete ve svém správci rootu (KernelSU, Magisk nebo APatch) a odtud jej otevřete.

V obou případech je vyžadován root.

## Co zobrazuje

### RCS
Zda je RCS dostupné pro vaši linku, vaše číslo RCS, stav RCS, verze konfigurace, kolik vašich kontaktů podporuje RCS, oprávnění operátora, přítomnost (UCE) a související časové značky (provisioning, synchronizace, poslední zpráva, vypršení tokenu a další). Také ukazuje, zda je Gemini v Google Messages připraven: Magic Compose (pomoc Gemini s psaním), engine umělé inteligence v zařízení (AICore, na kterém běží Gemini Nano) a chat s Gemini, který je doručován přes RCS.

### Google Wallet
Zda je vaše zařízení atestováno pro platby, zda má hardware NFC, zda je Wallet nainstalován, stav platební služby a placení přiložením, zda je váš zámek obrazovky přijat a zda byla někdy přidána karta.

### DroidGuard
Zda Google Play rozpoznává toto zařízení jako certifikované. Jde o certifikaci Play Protect zobrazenou v Obchodě Play. Atestace, které má toto zařízení uložené v mezipaměti pro Play Integrity a DroidGuard. Zobrazený čas je okamžik vypršení platnosti každé z nich.

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

## Dokumentace

- **[Wiki](https://github.com/VD171/VD-Google/wiki)**: veškerá dokumentace, k procházení online.
- **[Uživatelská příručka](https://github.com/VD171/VD-Google/wiki/USER-GUIDE)**: co každá obrazovka zobrazuje a co každý výsledek znamená.
- **[Architektura](https://github.com/VD171/VD-Google/wiki/ARCHITECTURE)** a **[Struktura souborů](https://github.com/VD171/VD-Google/wiki/FILE-STRUCTURE)**: jak to funguje a kde se co nachází.
- **[Soukromí](https://github.com/VD171/VD-Google/wiki/PRIVACY)**: žádné oprávnění k internetu, každé čtení probíhá přes root, nic nikdy neopustí zařízení.
- **[Přibalené binární soubory](https://github.com/VD171/VD-Google/wiki/BINARIES)**: původ a SHA-256 každého předkompilovaného binárního souboru, pro úplný audit.
- **[Přispívání](https://github.com/VD171/VD-Google/wiki/CONTRIBUTING)** a **[Zabezpečení](https://github.com/VD171/VD-Google/wiki/SECURITY)**.
- **[Changelog](CHANGELOG.md)** a **[Kodex chování](https://github.com/VD171/VD-Google/wiki/CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Viz také

Sesterské projekty téhož autora:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: debugger metod zařízení, který čte každou položku všemi metodami, které ji dokážou přečíst, a porovnává je vedle sebe.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: úplné zálohování a obnova aplikací a dat jako root, ovládané zcela z WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: spoofer zařízení, který z nové fingerprint vytvoří soudržný, konzistentní profil.

## Kontakty

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Licence

**GNU AGPL-3.0-or-later** - viz [LICENSE](LICENSE). Copyleft včetně síťové klauzule: kdo provozuje upravenou verzi (i jako službu), musí nabídnout její zdrojový kód. Zvoleno záměrně, aby forky zůstaly otevřené.

## Ochranné známky

VD Google je nezávislý, neoficiální projekt. Není spojen se společností Google LLC, není jí podporován ani sponzorován. „Google", „Google Wallet", „Google Play", „Play Integrity", „Android" a související názvy a loga jsou ochranné známky společnosti Google LLC. VD Google čte pouze to, co již ve vašem zařízení je, a nikam nic neposílá.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
