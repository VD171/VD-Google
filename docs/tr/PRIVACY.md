> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · Türkçe · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Gizlilik

Gizlilik, VD Google'ın bir özelliği değildir - tüm amacı budur. Bu sayfa, uygulamanın
verilerinizle ne yapıp ne yapmadığını, doğrulayabileceğiniz açık terimlerle tam olarak
belirtir.

## Söz

- **Hiçbir internet izni verilmez.** Uygulamanın manifestosu hiçbir izin beyan etmez - ne
  internet, ne depolama, ne kişiler, hiçbir şey. İzin verilecek bir şey yoktur.
- **Hiçbir şey saklanmaz, gönderilmez veya iletilmez** - cihazınızın dışındaki hiçbir
  dosyaya veya sunucuya.
- Her okuma **yerel olarak, root aracılığıyla** gerçekleşir ve cihazda kalır.

## Neden izinler yerine root'a ihtiyaç duyar

VD Google'ın açıkladığı RCS, Wallet ve doğrulama durumu, Android'in normal uygulamaların
göremediği, korumalı, yalnızca sisteme özel alanlarında yaşar. VD Google bunları, sizden
geniş izinler isteyerek değil, root ile okur. Root, dar ve denetlenebilir giriş yoludur;
bir yığın çalışma zamanı izni ise geniş ve sızıntılı yol olurdu. Uygulama dar olanı seçer.

## Dışa aktarma temizlenir

Bir rapor dışa aktardığınızda, VD Google onu yazmadan önce temizler:

- **Asla dahil edilmez:** telefon numaranız, herhangi bir kimlik bilgisi, çerez veya
  jeton, mesaj içeriği, kişi adları ya da ham yetenek blobları.
- **Karartılır:** uzun rakam dizileri ve sır gibi görünen değerler bir yer tutucuyla
  (placeholder) değiştirilir.
- **Tanılama** yalnızca yapıyı taşır (şema adları, sayılar, boyutlar, sürümler). Bir ad
  içinde tesadüfen beliren herhangi bir kimlik başına parmak izi çökertilerek kaldırılır.

Rapor sizindir. Seçtiğiniz yere yazılır, yalnızca siz paylaşırsanız paylaşılır ve sizi
tanımlayan hiçbir şey içermez.

## Eylemler açıktır ve günlüğe kaydedilir

VD Google, gelecekteki bir yeniden denetimin sıfırdan başlaması için Google Wallet'ın
önbelleklenmiş doğrulama sonucunu temizleyebilir. Durumu değiştiren herhangi bir eylem:

1. önce onaylamanızı ister ve
2. cihazdaki yerel bir JSON günlüğüne yazılır.

Hiçbir eylem ağa ulaşmaz ve hiçbir şey telefonunuzdan çıkmaz.

## Kendiniz doğrulayın

Bunların hiçbirine güvenerek kabul etmek zorunda değilsiniz:

- Kaynak herkese açıktır: <https://github.com/VD171/VD-Google>.
- Manifesto kaynaktadır; hiçbir izin beyan etmediğini doğrulayın.
- WebUI'nin Content-Security-Policy'si kaynaktadır; her uzak kaynağı engellediğini
  doğrulayın.
- Paket içindeki ikililer, sağlama toplamlarıyla birlikte
  [Paket içindeki ikililer](BINARIES.md) belgesinde belgelenmiştir.
