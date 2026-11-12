> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · Türkçe · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Mimari

VD Google'ın nasıl ve neden inşa edildiği. Bu sayfa tasarımı ve ilkeleri açıklar. Her
okuyucunun kullandığı tam dosyaları, tabloları veya sorguları listelemez; bunlar tasarım
gereği yalnızca kaynak kodda bulunur, böylece uygulama ve belgeler asla aynı değerleri
taklit etmek isteyen biri için bir nasıl-yapılır kılavuzuna dönüşemez.

## Tasarım hedefleri

1. **Gerçeği oku.** Google'ın kendi uygulamalarının zaten kaydettiğini göster, açıkla ve
   asla bir değer uydurma. Cihaz bir yanıt tutmadığında Unknown de.
2. **Sıfır ağ.** Uygulama hiçbir izin beyan etmez; WebUI her uzak kaynağı engeller.
   Hiçbir şey hiçbir yere, hiçbir zaman gönderilmez.
3. **Tek davranış, iki yüz.** Uygulama ve WebUI modülü kesinlikle aynı tutulur - aynı
   sinyaller, aynı ifadeler, aynı dışa aktarılan rapor.
4. **Yaltaklanmaktansa dürüst.** Gerçek bir olumsuz, olumsuz olarak gösterilir; eski bir
   önbellek, önbelleklenmiş olarak etiketlenir; eksik bir değer Unknown'dır. Algılama,
   daha hoş görünsün diye asla yumuşatılmaz.

## İki yüz

- **Uygulama**, Jetpack Compose (Material 3) ile Kotlin'dir. Root'u doğrudan `su`
  çağırarak değil, libsu `RootService` aracılığıyla alır ve okumayı o ayrıcalıklı servis
  içinde yapar.
- **WebUI modülü**, root yöneticiniz (KernelSU, Magisk veya APatch) tarafından sunulan
  küçük bir HTML/JS web kökü (web root) olur. Aynı okumaları yöneticinin root köprüsü
  üzerinden gerçekleştirir. Katı bir Content-Security-Policy her uzak kaynağı yasaklar,
  bu yüzden sayfa ilke olarak bile eve telefon edemez.

Her ikisi de 21 dilde tek bir ortak dize kümesinden üretilir, böylece bir etiket asla
iki kez çevrilmez.

## Veritabanlarını okumak: paket içindeki sqlite3

VD Google'ın okuduğu durumun bir kısmı, Google'ın uygulamalarına ait SQLite
veritabanlarında bulunur. Android kendi SQLite'ını içerir, ancak çerçeve SQLite'ı başka
bir uygulamanın kenara kopyalanmış veritabanını açmakta güvenilir değildir: bazı üretici
ROM'larında *her* veritabanı için başarısız olur ve hiçbir şey döndürmez. Her cihazda
aynı şekilde okumak için VD Google kendi `sqlite3` ikilisini (arm64 ve arm32) paketler
ve sorguları root olarak onun aracılığıyla çalıştırır. Uygulama ve WebUI aynı ikiliyi
kullanır, böylece her zaman aynı motoru ve aynı sonucu okurlar. O ikilinin kaynağı ve
sağlama toplamları [Paket içindeki ikililer](BINARIES.md) belgesinde belgelenmiştir.

Okumalar, veritabanını açmadan önce kenara kopyalar; canlı dosyaya asla dokunmaz ve
çalışan bir veritabanının bozuk olarak bildirebileceği paylaşımlı bellek dizinini asla
kopyalamaz.

## RCS sinyali

RCS'yi dürüstçe okumanın en zor yanı, bariz bayrakların yalan söylemesidir. Basit
"available" bayrağı, özellik bayrakları ve provizyon durumunun hepsi, RCS'nin kusursuz
çalıştığı bir hatta olumsuz okunabilir; çünkü bunlar önbelleklerdir ya da hat,
operatörün IMS ağı yerine Google'ın kendi arka ucunda (Jibe) çalışıyordur. VD Google,
gerçek sohbet yapılandırmasından ve provizyon motorundan tek bir **Connection** kararı
hesaplar; bu, Android ve Google Messages sürümleri arasında doğru kalır ve yalan söyleyen
bayrakları, yanıltmadan bilgi vermeleri için önbelleklenmiş olarak açıkça etiketlenmiş
biçimde sunar. Her satırın ne anlama geldiği için [kullanıcı kılavuzuna](USER-GUIDE.md)
bakın.

## Dışa aktarma

Dışa aktarma, temizlenmiş bir JSON anlık görüntüsüdür. Herhangi bir şey yazılmadan önce
bir temizleyici çalışır: kimlik bilgisi gibi görünen anahtarları ve değerleri atar, uzun
rakam dizilerini karartır ve asla telefon numaranızı, mesaj içeriğini, kişi adlarını veya
ham yetenek bloblarını yaymaz. İsteğe bağlı tanılama bölümü yalnızca şema adlarını,
sayıları, boyutları ve sürümleri taşır - ve orada bile, bir ada gömülü kimlik başına
parmak izi varsa çökertilerek kaldırılır. Uygulama ve WebUI aynı şemayı üretir.

## VD Google'ın asla yapmadıkları

- Asla bir ağ izni beyan etmez ve WebUI asla bir uzak kaynağa bağlanmaz.
- Asla bir değeri uydurmaz, taklit etmez veya gizlemez.
- Asla bir Play Integrity kararı bildirmez, çünkü o karar cihazda değildir.
- Durumu yalnızca açık, onaylanmış ve yerel olarak günlüğe kaydedilmiş bir eylem
  aracılığıyla değiştirir.
