> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · Türkçe · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Güvenlik politikası

VD Google bir gizlilik aracıdır. Root olarak çalışır ve hassas durumu okur, bu yüzden
güvenlik raporları ciddiye alınır.

## Bir güvenlik açığını bildirme

Lütfen güvenlik sorunlarını herkese açık bir sorun (issue) içinde değil, **gizli olarak**
bildirin:

- **vd.priv8@pm.me** adresine e-posta gönderin.
- Sorunu, etkilenen sürümü ve nasıl yeniden üretileceğini açıklayın.
- Veri ifşasıyla ilgiliyse, sorunun sınıfını açıklayın; çalışan bir istismar (exploit)
  eklemeniz gerekmez.

En kısa sürede bir yanıt alacaksınız. Lütfen herhangi bir kamuya açıklamadan önce sorunun
giderilmesi için makul bir süre tanıyın.

## Neler güvenlik sorunu sayılır

VD Google'ın vaat ettiği şey nedeniyle, şunlar özellikle önemlidir:

- Uygulamanın ağa ulaşmasına veya herhangi bir veriyi cihazdan dışarı göndermesine neden
  olacak her şey.
- Kişisel verileri bir **dışa aktarmaya** (export) sızdıracak her şey (bir telefon
  numarası, kimlik bilgisi, mesaj içeriği, kişi adı veya kimlik başına parmak izi).
- Tek temizleme eyleminin, onay olmadan veya günlüğe kaydedilmeden durumu değiştirmesine
  izin veren her şey.

## Neler güvenlik sorunu değildir

- "Yanlış" görünen ama dürüst gerçek olan bir okuma (örneğin, RCS Google'ın arka ucunda
  çalışırken operatör tarafı RCS satırlarının No okuması). [Kullanıcı kılavuzuna](USER-GUIDE.md)
  bakın.
- Bir Play Integrity kararının yokluğu. O karar cihazda değildir ve VD Google onu
  uydurmaz.

## Desteklenen sürümler

Yalnızca en son yayımlanan sürüm desteklenir. Lütfen bildirmeden önce en son derlemede
yeniden üretin.

<!-- doc-footer -->

---

> _Bu belgeler Claude (Anthropic) ile oluşturulmuş olup VD171 tarafından yürütülmektedir. Düzeltmeler ve iyileştirmeler memnuniyetle karşılanır; bkz. [CONTRIBUTING.md](CONTRIBUTING.md)._
