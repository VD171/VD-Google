> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · Türkçe · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# VD Google kullanıcı kılavuzu

VD Google'ın dostça ve eksiksiz bir tanıtımı: neyi gösterdiği, her satırın ne anlama
geldiği ve cihazınızı dürüstçe nasıl okuyacağınız. VD Google, RCS, Google Wallet ve
uygulamaların istediği Play Integrity doğrulamalarının durumunu okur ve her maddeyi
sade sözcüklerle açıklar. İnternete asla dokunmaz ve siz istemedikçe hiçbir şeyi asla
değiştirmez.

## İçindekiler

- [1. VD Google nedir](#1-vd-google-nedir)
- [2. Ne değildir](#2-ne-değildir)
- [3. Gereksinimler](#3-gereksinimler)
- [4. Uygulama veya WebUI](#4-uygulama-veya-webui)
- [5. Kurulum ve ilk çalıştırma](#5-kurulum-ve-ilk-çalıştırma)
- [6. Sekmeler](#6-sekmeler)
  - [6.1 Hakkında](#61-hakkında)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Doğrulamalar](#65-doğrulamalar)
- [7. Raporunuzu dışa aktarın](#7-raporunuzu-dışa-aktarın)
- [8. Durumu değiştiren eylemler](#8-durumu-değiştiren-eylemler)
- [9. Dil ve tema](#9-dil-ve-tema)
- [10. Sık sorulan sorular](#10-sık-sorulan-sorular)

## 1. VD Google nedir

VD Google bir okuyucudur. Google'ın kendi uygulamalarının cihazınızda üç şey hakkında
zaten sakladığı bilgilere bakar ve gerçekten anlamanız için her birini açıklar:

- **RCS** - modern sohbetin hattınızda gerçekten çalışıp çalışmadığı, kimlerle sohbet
  edebileceğiniz ve Google Messages'taki Gemini'nin hazır olup olmadığı.
- **Google Wallet** - cihazınızın temassız ödemeler için kurulup kurulmadığı ve
  doğrulanıp doğrulanmadığı.
- **Play Integrity doğrulamaları** - hangi uygulamaların Android'den cihazınızın gerçek
  olduğunu kanıtlamasını istediği ve Google'ın şu anda onu belgeleyip belgelemediği.

Her şey yerel olarak, root aracılığıyla okunur ve bir açıklamayla gösterilir. Hiçbir
değer uydurulmaz: cihaz bir yanıt tutmadığında, VD Google tahmin etmek yerine "Unknown"
der.

## 2. Ne değildir

**VD Google bir bütünlük denetleyicisi (integrity checker) değildir.** Herhangi bir basic
/ device / strong kararı çalıştırmaz veya bildirmez. O sonuç yalnızca bir uygulamanın
kendi sunucusuna gönderdiği şifreli jetonun içinde yaşar ve cihazda asla saklanmaz, bu
yüzden hiçbir dürüst okuyucu onu gösteremez. "Play Integrity kararınızı" yerel olarak
gösterdiğini iddia eden araçlar, size gerçek yanıtı değil, önbelleklenmiş bir tahmini ya
da kendi yaptıkları bir isteği gösteriyordur.

VD Google ayrıca hiçbir şeyi taklit etmez, gizlemez veya sahtelemez. Gerçeği okur. Bir
değer size yanlış görünüyorsa, bu kılavuzun amacı gerçeğin neden öyle göründüğünü
açıklamaktır.

## 3. Gereksinimler

- **Android 8.0 (API 26) veya daha yenisi.**
- **Root.** VD Google'ın okuduğu veriler, Android'in yalnızca root ile erişilebilen,
  korumalı, yalnızca sisteme özel alanlarında yaşar. Herhangi bir modern root çözümü
  çalışır: KernelSU, KernelSU Next, Magisk veya APatch.
- **İnternet yok.** VD Google hiçbir izin beyan etmez, bu yüzden izin verilecek ve
  engellenecek bir şey yoktur.

## 4. Uygulama veya WebUI

VD Google iki şekilde gelir ve ikisi de tam olarak aynı bilgileri gösterir:

- **Uygulama (APK).** Kurduğunuz ve açtığınız normal bir Android uygulaması. Herhangi bir
  root uygulaması gibi, ilk seferde root ister.
- **WebUI modülü (ZIP).** Root yöneticinizde (KernelSU, Magisk veya APatch) flaşlayın ve
  oradan açın. Hiçbir şey uygulama olarak kurulmaz; root yöneticinizin WebUI'si içinde
  çalışır.

Hangisini tercih ederseniz onu seçin. Ekranlar, ifadeler ve dışa aktarılan rapor ikisi
arasında aynıdır.

## 5. Kurulum ve ilk çalıştırma

**Uygulama:** APK'yi kurun ve açın. İlk seferde, root yöneticiniz sorduğunda root'u
verin. Root'u reddeder veya kapatırsanız, VD Google root'un neden gerekli olduğunu
açıklayan bir ekran gösterir ve tekrar denemeyi önerir.

**WebUI:** ZIP'i root yöneticinizde bir modül olarak flaşlayın, ardından modülün WebUI'sini
yöneticiden açın. Açılır açılmaz okur.

İlk açılışta dilinizi seçebilirsiniz; daha sonra üstteki dişli simgesinden
değiştirebilirsiniz.

## 6. Sekmeler

Alt kısımda beş sekme vardır: Hakkında, DroidGuard, RCS, Wallet ve Doğrulamalar. İlk
olarak Hakkında açılır.

### 6.1 Hakkında

Uygulamanın ne olduğu, gizlilik sözü, sürüm ve bağlantılar. Gizlilik bölümünü bir kez
okuyun: uygulamanın tüm amacı budur.

### 6.2 RCS

Bu en zengin sekmedir. Zaman damgaları, bölge etiketiyle birlikte her zaman cihazınızın
saat diliminde gösterilir.

- **Connection** - gerçeği söyleyen tek satır. Gerçek sohbet yapılandırmasından ve
  provizyon motorundan hesaplanır, bu yüzden eski bayrakların yalan söylediği yeni
  Android'de bile, RCS hattınızda gerçekten çalışırken Connected der. **Şüphe
  duyduğunuzda bu satıra güvenin.**
- **Carrier** - hattınız için mobil operatör (ad ve MCC/MNC), çift SIM'li telefonlarda
  veri SIM'inizden alınır.
- **Availability flag (cached)** - mesajlaşma uygulamasının kendi numaranız için tuttuğu
  önbelleklenmiş bir bayrak. RCS zaten çalışmaya başladıktan sonra güncelliğini
  yitirebilir, bu yüzden buradaki bir "No", RCS'nin kapalı olduğu anlamına gelmez.
  Yukarıdaki Connection satırına güvenin.
- **RCS numaranız** - RCS için kayıtlı numara, varsayılan olarak maskelenir. Göstermek
  için göz simgesine dokunun.
- **Provisioning state (cached)** ve **Configuration version** - mesajlaşma uygulamasının
  tuttuğu dahili değerler. RCS çalışırken bile 0 okuyabilirler. Aşağıdaki operatör ve
  Google'ın arka ucu notuna bakın.
- **RCS destekli kişiler** - kişilerinizden kaçının şu anda RCS sohbetini desteklediğinin
  bilindiği.
- **Feature flag (cached)** - mesajlaşma uygulamasının RCS özelliğinin açık olup
  olmadığı; önbelleklenmiş olduğundan geride kalabilir.
- **Carrier entitlement**, **Presence (UCE)**, **IMS service present** - bunlar
  *operatörün* RCS yolunu (operatörün IMS ağı) tanımlar. RCS'niz operatörünüz yerine
  Google'ın kendi arka ucu üzerinden çalıştığında, buradaki bir "No" ya da "0" normaldir.
  Aşağıya bakın.
- **Gemini** - Google Messages'taki Gemini'nin hazır olup olmadığı: **Magic Compose**
  (Gemini'nin yazma yardımı), **AICore** (Gemini Nano'yu çalıştıran cihaz üzerindeki
  motor) ve RCS üzerinden sunulan ve bu yüzden bağlı bir RCS gerektiren **Gemini chat**.
- **Zaman damgaları** - provizyon, eşitleme, son mesaj, jeton süresi ve dahası.

**Operatör RCS'si ve Google'ın arka ucu.** RCS'nin telefonunuza ulaşmasının iki yolu
vardır. Biri *operatörünüzün* IMS ağı üzerinden RCS'si; diğeri operatörden bağımsız
olarak, üstten çalışan *Google'ın kendi arka ucudur* (Jibe). RCS'niz Google'ın arka ucu
üzerinden çalışıyorsa - çoğu insanda olduğu gibi - o zaman operatör tarafı satırlar
(carrier entitlement, presence/UCE, IMS service, configuration version) gerçekten "No"
ya da "0" olur ve bu doğrudur, eski bir önbellek değildir. RCS'niz bu şekilde yıllardır
çalışıyor olabilir. Çalıştığını size söyleyen şey Connection satırıdır.

### 6.3 Wallet

- **Device attested** - Google'ın bu cihazı ödeme kartlarını tutacak kadar gerçek ve
  güvenli bulup bulmadığı. Bu, Google'ın bu cihazda sakladığı son sonuçtur, bu yüzden
  Google yeniden denetleyene kadar Wallet uygulamasının gerisinde kalabilir. Kaydedilen
  sonuç eksikse, VD Google yanıltıcı bir "No" yerine **Unknown** gösterir.
- **Last checked** - o güvenlik denetiminin en son ne zaman tamamlandığı.
- **NFC hardware**, **Wallet installed**, **Wallet service**, **Tap and pay**,
  **Screen lock accepted**, **Card ever added** - temassız ödemeyi mümkün kılan parçalar.

### 6.4 DroidGuard

- **Play Protect certified** - Google Play'in cihazınızı belgelenmiş olarak tanıyıp
  tanımadığı (Play Store'da gösterilen belgelendirme).
- **Attestation cache** - cihazınızın önbelleğe aldığı Play Integrity ve DroidGuard
  doğrulamaları, her biri ne zaman süresinin dolacağıyla birlikte.

### 6.5 Doğrulamalar

Cihazınızda hangi uygulamaların Play Integrity istediğinin geçmişi: simge, ad, en son ne
zaman istedikleri ve kaç kez. Bu, belirli bir uygulamanın bütünlüğü kontrol edip
etmediğini anlamanızı sağlar. Uygulama adına veya pakete göre filtrelemek için arama
kutusunu kullanın.

## 7. Raporunuzu dışa aktarın

İndirme simgesi, cihaz, RCS, Wallet ve doğrulama durumunuzla birlikte bir **JSON** raporu
yazar. Onu paylaşabilir veya bir dosya olarak kaydedebilirsiniz. Rapor **hiçbir telefon
numarası, hiçbir kimlik bilgisi ve hiçbir kişisel içerik** içermez - yazılmadan önce
temizlenir. Ayrıca, cihaza özgü sorunları gidermeye yardımcı olan isteğe bağlı bir
**tanılama** bölümü de içerir (şema adları, sayılar ve sürümler, asla verileriniz değil).
Uygulama ve WebUI aynı raporu yazar.

## 8. Durumu değiştiren eylemler

VD Google çoğunlukla yalnızca okunur, ancak bir **temizleme eylemi** gerçekleştirebilir:
Google Wallet'ın önbelleklenmiş doğrulama sonucunu temizlemek, bu da gelecekteki bir
yeniden denetimin sıfırdan başlamasını sağlar. Durumu değiştiren her eylem önce
onaylamanızı ister ve cihazdaki yerel bir JSON günlüğüne yazılır. Hiçbir şey telefonunuzdan
çıkmaz.

## 9. Dil ve tema

VD Google 21 dil konuşur. İlk çalıştırmada dilinizi seçin veya dişli simgesinden (root
reddedildi ekranında da mevcuttur) istediğiniz zaman değiştirin. Parlaklık simgesi açık
ve koyu arasında geçiş yapar.

## 10. Sık sorulan sorular

**RCS Connected diyor ama birkaç satır No veya 0 diyor. Neden?** Çünkü RCS'niz
operatörünüz üzerinden değil, Google'ın kendi arka ucu üzerinden çalışıyor. Operatör
tarafı satırlar doğru biçimde olumsuzdur. [6.2 RCS](#62-rcs) bölümüne bakın.

**Device attested, Unknown diyor.** Önbelleklenmiş Wallet sonucu cihazınızda eksik. Bu
dürüstçedir - VD Google destekleyemeyeceği bir "No" göstermez.

**Play Integrity kararım nerede?** Cihazınızda değil.
[2. Ne değildir](#2-ne-değildir) bölümüne bakın.

**Root olmadan çalışır mı?** Hayır. Veriler yalnızca sisteme özel alanlarda yaşar.
