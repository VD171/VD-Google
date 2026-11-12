> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · Türkçe · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Katkıda bulunma

VD Google'a gösterdiğiniz ilgi için teşekkürler. Katkılar memnuniyetle karşılanır:
çeviriler, hata raporları, belgeler ve kod.

## Temel kurallar

- **VD Google gerçeği okur ve asla hiçbir şeyi taklit etmez.** Katkılar bunu böyle
  tutmalıdır. Gerçek bir olumsuz, olumsuz kalır; eski bir önbellek, önbelleklenmiş olarak
  etiketlenir; eksik bir değer Unknown'dır. Bir okumayı daha hoş görünsün diye
  yumuşatmayın.
- **Gizlilik mutlaktır.** Uygulama hiçbir izin beyan etmez ve WebUI hiçbir uzak kaynağa
  bağlanmaz. Asla bir izin, bir ağ çağrısı veya verileri cihazdan dışarı gönderen hiçbir
  şey eklemeyin.
- **Uygulama ve WebUI aynıdır.** Gösterilen şeye ya da dışa aktarılan rapora yapılan
  herhangi bir değişiklik, aynı ifadelerle her ikisinde de yer almalıdır. Asla ayrışmamalıdırlar.
- **Etiketler bir kez, 21 dilde yaşar.** Kullanıcıya görünen her dize, uygulama
  (`res/values*/strings.xml`) ve WebUI (`webroot/lang/`) için tanımlanır. Bir etiket
  ekler veya değiştirirseniz, tüm dilleri güncelleyin.

## Çeviriler

Çeviriler en kolay ve en değerli katkıdır. Benioku dosyası dile göre çevrilir
(`README.<lang>.md`), uygulama ve WebUI dizeleri dile göre çevrilir ve bu belgeler
`docs/<lang>/` altında çevrilir. Bir dil içinde terminolojiyi tutarlı tutun, mevcut tona
uyun ve emin olmadığınız bir teknik terimi asla makineyle çevirmeyin.

## Bir hatayı bildirme

Cihaz modelinizi, Android sürümünüzü ve - eğer bir okumayla ilgiliyse - bir **dışa
aktarma** (export) içeren bir sorun (issue) açın (hiçbir kişisel veri içermez). Dışa
aktarmanın tanılama bölümü tam da bunun için yapılmıştır.

## Kod

- Her zaman release derleyin, asla debug değil. Gerçek, root'lanmış bir cihazda test edin.
- Yorumları koddan uzak tutun; adlar niyeti açıklar. Tek başlık, her kaynak dosyanın
  tepesindeki lisans afişidir.
- Mevcut stile uyun ve değişiklikleri odaklı tutun.

## Lisans

Katkıda bulunarak, katkınızın projenin **GNU AGPL-3.0-or-later** lisansı altında
lisanslandığını kabul etmiş olursunuz.

<!-- doc-footer -->

---

> _Bu belgeler Claude (Anthropic) ile oluşturulmuş olup VD171 tarafından yürütülmektedir. Düzeltmeler ve iyileştirmeler memnuniyetle karşılanır; bkz. [CONTRIBUTING.md](CONTRIBUTING.md)._
