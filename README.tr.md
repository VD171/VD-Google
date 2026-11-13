<!--
  VD GOOGLE: RCS, Google Cüzdan ve Play Integrity tasdikleri, cihazda okunur.
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
  <b>Google hizmetleriniz, cihazınızda okunur. Asla internet yok.</b><br>
  VD Google, Google'ın RCS, Wallet ve Play Integrity hakkında telefonunuza kaydettiklerini size gösterir ve her bir öğenin ne anlama geldiğini açıklar; hiçbir yere hiçbir şey göndermeden.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/tr-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/tr-USER-GUIDE">Kullanıcı kılavuzu</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/tr-ARCHITECTURE">Mimari</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/tr-PRIVACY">Gizlilik</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/tr-BINARIES">Paketlenmiş ikili dosyalar</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/tr-FILE-STRUCTURE">Dosya yapısı</a> ·
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

## Uygulama veya WebUI

Her sürüm iki biçimde gelir ve ikisi de aynı bilgileri cihazınızda, internet olmadan okur:

- **App**: `.apk` dosyası, normal bir Android uygulaması.
- **WebUI**: `.zip` dosyası, root yöneticinize (KernelSU, Magisk veya APatch) kurup oradan açtığınız bir modül.

Her iki durumda da root gereklidir.

## Neleri gösterir

### RCS
Hattınız için RCS'nin kullanılabilir olup olmadığı, RCS numaranız, RCS durumu, yapılandırma sürümü, kişilerinizden kaçının RCS desteklediği, operatör yetkisi, mevcudiyet (UCE) ve ilgili zaman damgaları (sağlama, eşitleme, son mesaj, jeton sona ermesi ve daha fazlası). Ayrıca Google Messages'taki Gemini'nin hazır olup olmadığını da gösterir: Magic Compose (Gemini'nin yazma yardımı), cihazdaki yapay zekâ motoru (Gemini Nano'yu çalıştıran AICore) ve RCS üzerinden sunulan Gemini sohbeti.

### Google Cüzdan
Cihazınızın ödemeler için tasdikli olup olmadığı, NFC donanımı olup olmadığı, Cüzdan'ın yüklü olup olmadığı, ödeme hizmeti ve dokunarak ödeme durumu, ekran kilidinizin kabul edilip edilmediği ve hiç kart eklenip eklenmediği.

### DroidGuard
Google Play'in bu cihazı sertifikalı olarak tanıyıp tanımadığı. Play Store'da gösterilen Play Protect sertifikasıdır. Bu cihazın Play Integrity ve DroidGuard için önbelleğe aldığı doğrulamalar. Gösterilen saat, her birinin sona erdiği zamandır.

### Tasdikler (Play Integrity)
Yakın zamanda Google Play Integrity'den cihazınıza kefil olmasını isteyen uygulamalar; her biri simgesi, adı, son isteğinin tam zamanı ve geçerli penceredeki istek sayısıyla.

Her öğe, gerçekte ne anlama geldiğini sade bir dille anlatan kısa bir açıklama içerir.

## Gizlilik: internet yok

VD Google **hiçbir izin** tanımlamaz, internet erişimi bile. Okuduğu hiçbir şey cihazınızdan asla çıkmaz. Bunu manifestte ve kaynak kodunda doğrulayabilirsiniz.

## Gereksinimler

- Android 8.0 (API 26) veya daha yenisi.
- **Root gereklidir.** Yukarıdaki bilgiler Android'in yalnızca sisteme açık korumalı alanlarında bulunur. VD Google bunları süper kullanıcı erişimiyle okur ve başka hiçbir şey yapmaz. Root olmadan uygulama yalnızca root'un neden gerektiğini açıklayan bir ekran gösterir.

## Zaman damgaları

Tüm saatler cihazınızın geçerli saat diliminde gösterilir ve saat dilimi her zaman belirtilir.

## Belgeler

- **[Wiki](https://github.com/VD171/VD-Google/wiki/tr-Home)**: tüm belgeler, çevrimiçi olarak göz atılabilir.
- **[Kullanıcı kılavuzu](https://github.com/VD171/VD-Google/wiki/tr-USER-GUIDE)**: her ekranın ne okuduğu ve her sonucun ne anlama geldiği.
- **[Mimari](https://github.com/VD171/VD-Google/wiki/tr-ARCHITECTURE)** ve **[Dosya yapısı](https://github.com/VD171/VD-Google/wiki/tr-FILE-STRUCTURE)**: nasıl çalıştığı ve her şeyin nerede olduğu.
- **[Gizlilik](https://github.com/VD171/VD-Google/wiki/tr-PRIVACY)**: internet izni yok, her okuma root üzerinden geçer, hiçbir şey cihazdan asla çıkmaz.
- **[Paketlenmiş ikili dosyalar](https://github.com/VD171/VD-Google/wiki/tr-BINARIES)**: her önceden derlenmiş ikili dosyanın kaynağı ve SHA-256'sı, tam denetim için.
- **[Katkıda bulunma](https://github.com/VD171/VD-Google/wiki/tr-CONTRIBUTING)** ve **[Güvenlik](https://github.com/VD171/VD-Google/wiki/tr-SECURITY)**.
- **[Değişiklik günlüğü](CHANGELOG.md)** ve **[Davranış kuralları](https://github.com/VD171/VD-Google/wiki/tr-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Ayrıca bakınız

Aynı yazarın kardeş projeleri:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: her öğeyi okuyabilen her yöntemle okuyan ve bunları yan yana karşılaştıran bir cihaz yöntem hata ayıklayıcısı.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: root olarak tam uygulama ve veri yedekleme ve geri yükleme, tamamen bir WebUI'den yönetilir.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: yeni bir parmak izini tutarlı, kararlı bir profile dönüştüren bir cihaz sahteleyici.
- **[VD-dropbear](https://github.com/VD171/VD-dropbear)**: root olarak çalışan bağımsız bir SSH daemon'ı (Dropbear).

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

## Lisans

**GNU AGPL-3.0-or-later**: bkz. [LICENSE](LICENSE). Ağ maddesi dahil copyleft: değiştirilmiş bir sürümü (hizmet olarak bile) çalıştıran herkes kaynak kodunu sunmalıdır. Fork'ları açık tutmak için bilinçli olarak seçildi.

## Ticari markalar

VD Google bağımsız, resmi olmayan bir projedir. Google LLC ile bağlantılı değildir, onun tarafından onaylanmamış veya desteklenmemiştir. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" ve ilgili adlar ve logolar Google LLC'nin ticari markalarıdır. VD Google yalnızca cihazınızda zaten bulunanı okur ve hiçbir yere hiçbir şey göndermez.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
