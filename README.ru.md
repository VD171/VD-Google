<!--
  VD GOOGLE - RCS, Google Wallet и аттестации Play Integrity, прочитанные на устройстве.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Ваши сервисы Google, прочитанные на устройстве. Никакого интернета.**

VD Google показывает, что сам Google записывает на вашем телефоне о трёх вещах, и объясняет, что означает каждый пункт, не отправляя ничего никуда.

> **Языки:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Что он показывает

### RCS
Доступен ли RCS для вашей линии, ваш номер RCS, состояние RCS, версия конфигурации, сколько ваших контактов поддерживают RCS, разрешение оператора, присутствие (UCE) и связанные метки времени (подготовка, синхронизация, последнее сообщение, истечение токена и другое).

### Google Кошелёк
Аттестовано ли ваше устройство для платежей, есть ли у него оборудование NFC, установлен ли Кошелёк, состояние платёжной службы и оплаты прикосновением, принята ли ваша блокировка экрана и добавлялась ли когда-либо карта.

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

## Контакты

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Лицензия

**GNU AGPL-3.0-or-later** - см. [LICENSE](LICENSE). Копилефт, включая сетевую оговорку: любой, кто запускает изменённую версию (даже как сервис), обязан предоставить её исходный код. Выбрана намеренно, чтобы форки оставались открытыми.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
