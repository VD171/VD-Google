<!--
  VD GOOGLE - RCS, Google Wallet i atestacje Play Integrity, odczytane na urządzeniu.
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
  <b>Twoje usługi Google, odczytane na urządzeniu. Nigdy internet.</b><br>
  VD Google pokazuje, co sam Google zapisuje w telefonie na temat RCS, Wallet i Play Integrity, i wyjaśnia każdą pozycję, bez wysyłania czegokolwiek gdziekolwiek.
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

## Aplikacja lub WebUI

Każde wydanie jest dostępne w dwóch postaciach, a obie odczytują te same informacje na Twoim urządzeniu, bez internetu:

- **App** - plik `.apk`, zwykła aplikacja na Androida.
- **WebUI** - plik `.zip`, moduł, który instalujesz w menedżerze roota (KernelSU, Magisk lub APatch) i stamtąd otwierasz.

W obu przypadkach wymagany jest root.

## Co pokazuje

### RCS
Czy RCS jest dostępny dla Twojej linii, Twój numer RCS, stan RCS, wersja konfiguracji, ilu Twoich kontaktów obsługuje RCS, uprawnienie operatora, obecność (UCE) oraz powiązane znaczniki czasu (provisioning, synchronizacja, ostatnia wiadomość, wygaśnięcie tokena i więcej). Pokazuje też, czy Gemini w Google Messages jest gotowy: Magic Compose (pomoc Gemini w pisaniu), silnik SI na urządzeniu (AICore, na którym działa Gemini Nano) oraz czat Gemini, dostarczany przez RCS.

### Google Wallet
Czy Twoje urządzenie jest poświadczone do płatności, czy ma sprzęt NFC, czy Wallet jest zainstalowany, stan usługi płatności i płatności zbliżeniowych, czy Twoja blokada ekranu jest akceptowana oraz czy kiedykolwiek dodano kartę.

### DroidGuard
Czy Google Play rozpoznaje to urządzenie jako certyfikowane. To certyfikat Play Protect widoczny w Sklepie Play. Atestacje, które to urządzenie przechowuje w pamięci podręcznej dla Play Integrity i DroidGuard. Pokazany czas to moment wygaśnięcia każdej z nich.

### Atestacje (Play Integrity)
Aplikacje, które niedawno poprosiły Google Play Integrity o poręczenie za Twoje urządzenie, każda ze swoją ikoną, nazwą, pełnym czasem ostatniego żądania oraz liczbą żądań w bieżącym oknie.

Każda pozycja zawiera krótkie wyjaśnienie prostym językiem, co naprawdę oznacza.

## Prywatność: brak internetu

VD Google nie deklaruje **żadnych uprawnień**, nawet dostępu do internetu. Nic z tego, co odczytuje, nigdy nie opuszcza Twojego urządzenia. Możesz to sprawdzić w manifeście i w kodzie źródłowym.

## Wymagania

- Android 8.0 (API 26) lub nowszy.
- **Wymagany jest root.** Powyższe informacje znajdują się w chronionych, dostępnych tylko dla systemu obszarach Androida. VD Google odczytuje je z dostępem superużytkownika i nie robi z nimi nic więcej. Bez roota aplikacja pokazuje tylko ekran wyjaśniający, dlaczego root jest potrzebny.

## Znaczniki czasu

Wszystkie godziny są wyświetlane w bieżącej strefie czasowej urządzenia, a strefa jest zawsze podana.

## Zobacz także

Projekty siostrzane tego samego autora:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: debuger metod urządzenia, który odczytuje każdą pozycję każdą metodą zdolną ją odczytać i porównuje je obok siebie.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: pełna kopia zapasowa i przywracanie aplikacji oraz danych jako root, sterowane w całości z WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: spoofer urządzenia, który zamienia świeży fingerprint w spójny, zgodny profil.

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

## Licencja

**GNU AGPL-3.0-or-later** - zobacz [LICENSE](LICENSE). Copyleft, w tym klauzula sieciowa: kto uruchamia zmodyfikowaną wersję (nawet jako usługę), musi udostępnić jej kod źródłowy. Wybrana celowo, aby forki pozostały otwarte.

## Znaki towarowe

VD Google to niezależny, nieoficjalny projekt. Nie jest powiązany z Google LLC ani przez nie wspierany czy sponsorowany. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" oraz powiązane nazwy i logo są znakami towarowymi Google LLC. VD Google odczytuje tylko to, co już jest na Twoim urządzeniu, i niczego nigdzie nie wysyła.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
