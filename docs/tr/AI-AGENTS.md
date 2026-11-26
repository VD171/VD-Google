> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · Türkçe · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Yapay zekâ ajanları

VD Google, Google'ın cihazdaki kendi kayıtlarını bir **yapay zekâ ajanının tek adımda okuyabileceği** bir şeye dönüştürür. Birkaç Google uygulamasını ve veritabanını açıp anlamlarını tahmin etmek yerine, bir ajan tek bir yapılandırılmış **dışa aktarımı** okur ve cihazın RCS, Google Wallet ve Play Integrity hakkında tam olarak ne bildirdiğini bilir.

Bu sayfa README'de tanıtılan fikri genişletir.

## Dışa aktarım

Hem uygulama hem de WebUI aynı JSON dışa aktarımını üretir:

- **Uygulama**: **Dışa aktar** eylemi raporu paylaşır veya kaydeder.
- **WebUI**: **Dışa aktar** eylemi onu `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json` konumuna yazar.

İkisi de aynı şemaya sahiptir, çünkü uygulama ve WebUI özdeş tutulur. Hiçbir yere hiçbir şey gönderilmez: dışa aktarım cihaza yazılır ve orada kalır.

## İçinde ne var

Dışa aktarım, şu üst düzey anahtarlara sahip tek bir JSON nesnesidir:

- `app`, `version`, `exportedAt`, `timezone` - raporu neyin ürettiği ve ne zaman, cihazın etiketli saat diliminde.
- `device` - üretici, model, Android sürümü ve SDK düzeyi.
- `rcs` - kullanılabilirlik, durum, yapılandırma sürümü, uyumlu kişiler, operatör yetkilendirmesi, varlık (UCE) ve ilgili zaman damgaları.
- `wallet` - ödeme beyanı, NFC donanımı, temassız ödeme durumu, ekran kilidinin kabulü ve hiç kart eklenip eklenmediği.
- `diagnostics` - hata raporları için hazırlanmış bir öz denetim bölümü.

Dışa aktarım **hiçbir kişisel veri** taşımaz: telefon numarası yok, kimlik bilgisi yok, mesaj içeriği yok, kişi adı yok. Her değer dürüst okumadır - gerçek bir hayır hayır olarak kalır, eski bir önbellek önbellekli olarak etiketlenir, eksik bir değer Bilinmiyor olur.

## Salt okunur, tek bir kayıtlı eylemle

VD Google okur. Tek istisna bir temizleme eylemidir (yeniden denetimi zorlamak için Google Wallet'ın önbelleğe alınmış beyan sonucunu silmek). Durumu değiştiren her eylem cihazda bir onay ister ve yerel bir JSON eylem günlüğüne yazılır. WebUI'yi yöneten bir ajan aynı onayı ve aynı günlüğü görür, gizli veya kaydı tutulmayan hiçbir yol yoktur.

## Ağ sözleşmesi yok

VD Google hiçbir ağ API'si ve soket sunmaz: hiçbir izin beyan etmez. Bir ajan **dışa aktarım dosyasını** kullanır, bir hizmeti çağırmaz. Bu, otomasyonda bile korunan gizlilik güvencesidir.

<!-- doc-footer -->

---

> _Bu belgeler Claude (Anthropic) ile oluşturulmuş olup VD171 tarafından yürütülmektedir. Düzeltmeler ve iyileştirmeler memnuniyetle karşılanır, bkz. [CONTRIBUTING.md](CONTRIBUTING.md)._
