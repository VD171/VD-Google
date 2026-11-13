<!--
  VD GOOGLE: RCS, Google Wallet и аттестации Play Integrity, прочитанные на устройстве.
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
  <b>Ваши сервисы Google, прочитанные на устройстве. Никакого интернета.</b><br>
  VD Google показывает, что сам Google записывает на вашем телефоне о RCS, Wallet и Play Integrity, и объясняет каждый пункт, не отправляя ничего никуда.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ru-Home">Вики</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ru-USER-GUIDE">Руководство пользователя</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ru-ARCHITECTURE">Архитектура</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ru-PRIVACY">Конфиденциальность</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ru-BINARIES">Встроенные бинарные файлы</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ru-FILE-STRUCTURE">Структура файлов</a> ·
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

## Приложение или WebUI

Каждый выпуск доступен в двух вариантах, и оба читают одно и то же на вашем устройстве, без интернета:

- **App**: файл `.apk`, обычное приложение Android.
- **WebUI**: файл `.zip`, модуль, который вы устанавливаете в своём root-менеджере (KernelSU, Magisk или APatch) и открываете оттуда.

В обоих случаях требуется root.

## Что он показывает

### RCS
Доступен ли RCS для вашей линии, ваш номер RCS, состояние RCS, версия конфигурации, сколько ваших контактов поддерживают RCS, разрешение оператора, присутствие (UCE) и связанные метки времени (подготовка, синхронизация, последнее сообщение, истечение токена и другое). Также показывает, готов ли Gemini в Google Messages: Magic Compose (помощь Gemini в написании текста), ИИ-движок на устройстве (AICore, на котором работает Gemini Nano) и чат с Gemini, который доставляется через RCS.

### Google Кошелёк
Аттестовано ли ваше устройство для платежей, есть ли у него оборудование NFC, установлен ли Кошелёк, состояние платёжной службы и оплаты прикосновением, принята ли ваша блокировка экрана и добавлялась ли когда-либо карта.

### DroidGuard
Признаёт ли Google Play это устройство сертифицированным. Это сертификация Play Protect, показываемая в Google Play. Аттестации, которые это устройство кэшировало для Play Integrity и DroidGuard. Показано время, когда каждая из них истекает.

### Аттестации (Play Integrity)
Приложения, недавно попросившие Google Play Integrity поручиться за ваше устройство, каждое со своим значком, названием, полным временем последнего запроса и числом запросов в текущем окне.

Каждый пункт сопровождается коротким объяснением простым языком того, что он на самом деле означает.

## Конфиденциальность: без интернета

VD Google не объявляет **вообще никаких разрешений**, даже доступа в интернет. Ничто из того, что он читает, никогда не покидает ваше устройство. Вы можете убедиться в этом в манифесте и в исходном коде.

## Требования

- Android 8.0 (API 26) или новее.
- **Требуется root.** Указанные выше сведения находятся в защищённых областях Android, доступных только системе. VD Google читает их с доступом суперпользователя и ничего больше с ними не делает. Без root приложение лишь показывает экран с объяснением, зачем нужен root.

## Метки времени

Всё время показывается в текущем часовом поясе вашего устройства, и часовой пояс всегда указывается.

## Документация

- **[Вики](https://github.com/VD171/VD-Google/wiki/ru-Home)**: вся документация, доступная для просмотра онлайн.
- **[Руководство пользователя](https://github.com/VD171/VD-Google/wiki/ru-USER-GUIDE)**: что читает каждый экран и что означает каждый результат.
- **[Архитектура](https://github.com/VD171/VD-Google/wiki/ru-ARCHITECTURE)** и **[Структура файлов](https://github.com/VD171/VD-Google/wiki/ru-FILE-STRUCTURE)**: как это работает и где всё находится.
- **[Конфиденциальность](https://github.com/VD171/VD-Google/wiki/ru-PRIVACY)**: никаких интернет-разрешений, каждое чтение идёт через root, ничто никогда не покидает устройство.
- **[Встроенные бинарные файлы](https://github.com/VD171/VD-Google/wiki/ru-BINARIES)**: происхождение и SHA-256 каждого готового бинарного файла, для полного аудита.
- **[Участие в разработке](https://github.com/VD171/VD-Google/wiki/ru-CONTRIBUTING)** и **[Безопасность](https://github.com/VD171/VD-Google/wiki/ru-SECURITY)**.
- **[Журнал изменений](CHANGELOG.md)** и **[Кодекс поведения](https://github.com/VD171/VD-Google/wiki/ru-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Смотрите также

Родственные проекты того же автора:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: отладчик методов устройства, который читает каждый пункт всеми методами, способными его прочитать, и сравнивает их бок о бок.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: полное резервное копирование и восстановление приложений и данных с правами root, полностью управляемое из WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: спуфер устройства, превращающий свежий fingerprint в связный и согласованный профиль.
- **[VD-dropbear](https://github.com/VD171/VD-dropbear)**: автономный SSH-демон с правами root (Dropbear).

## Контакты

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Лицензия

**GNU AGPL-3.0-or-later**: см. [LICENSE](LICENSE). Копилефт, включая сетевую оговорку: любой, кто запускает изменённую версию (даже как сервис), обязан предоставить её исходный код. Выбрана намеренно, чтобы форки оставались открытыми.

## Товарные знаки

VD Google: независимый, неофициальный проект. Он не связан с Google LLC, не одобрен и не спонсируется ею. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" и связанные названия и логотипы являются товарными знаками Google LLC. VD Google лишь читает то, что уже есть на вашем устройстве, и ничего никуда не отправляет.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
