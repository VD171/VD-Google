> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · Türkçe · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Paket içindeki ikililer: kaynak ve denetim

VD Google, içinde opak hiçbir şeyin gelmediğini vaat eder. Root olarak çalışır ve en özel
durumunuzu okur, bu yüzden tam olarak ne taşıdığını görebilmeniz gerekir. Bu sayfa,
uygulamanın ve modülün paketlediği tek yerel aracı, nereden geldiğini ve nasıl
doğrulanacağını listeler.

## İlke

VD Google tam olarak bir yerel araç paketler: bir `sqlite3` komutu, ihtiyaç duyduğu küçük
paylaşımlı kitaplık (shared library) kümesiyle birlikte. Buna ihtiyaç duyulur çünkü
Android'in kendi çerçeve SQLite'ı bazı üretici ROM'larında başka uygulamaların
veritabanlarını açmakta başarısız olur ve bu da VD Google'ın o cihazlarda hiçbir şey
okuyamamasına neden olur. Nedeni için [Mimari](ARCHITECTURE.md) belgesine bakın.

- **Üst kaynaktan alınır, asla bir cihazdan değil.** `sqlite3` ve kitaplıkları, resmi
  **Termux** paket deposundan (`packages.termux.dev/apt`), imzalı `.deb` paketlerinden
  gelir; asla bir telefona kurulu bir Termux'tan kopyalanmaz.
- **Her yerde aynı baytlar.** Aynı dosyalar, uygulamada (`assets/bin/` altında) ve WebUI
  modülünde (`bin/` altında) gelir, böylece ikisi de tek bir motor üzerinden okur ve
  uyuşur.
- **Sağlama toplamı kimliktir.** Bir kopya hangi yolda bulunursa bulunsun, yalnızca
  SHA-256'sı kaydedilen değerle eşleşiyorsa gerçek araçtır.
- **Kaydedilmiş bir denetim izi.** Her ABI dizini, her kaynak paketi (ad, sürüm, URL,
  `.deb` SHA-256'sı) ve gönderilen her dosyanın SHA-256'sını listeleyen bir
  `PROVENANCE.json` taşır. Kontrol edebileceğiniz bir girdi olmadan hiçbir şey paketlenmez.

## Köken

`.github/scripts/build_bin.sh`, yükü (payload) derleme zamanında, hiçbir derleyici ve
hiçbir NDK olmadan bir araya getirir. Her ABI için:

1. Termux Packages dizinini okur ve `sqlite` paketini, geçişli bağımlılıklarıyla
   (`readline`, `ncurses`, `zlib`, `libandroid-support`) birlikte çözer;
2. resmi imzalı `.deb` dosyalarını indirir ve çıkarır;
3. `sqlite3`'ü kopyalar, ardından yalnızca ikilinin gerçekten ihtiyaç duyduğu paylaşımlı
   kitaplık kapanışını (closure) kopyalar (`readelf` ile çözülür), Android'in her zaman
   kendisinin sağladığı kitaplıkları atlar;
4. `PROVENANCE.json` yazar.

Termux ikilileri, Termux'un kendi kitaplıklarına dinamik olarak bağlanır, bu yüzden VD
Google o kapanışı `sqlite3` ile birlikte gönderir ve onu, paket içindeki `lib/` dizinine
işaret eden `LD_LIBRARY_PATH` ile çalıştırır. Termux, sürümlenmiş soname'ler
(`libz.so.1`, `libreadline.so.8`, `libncursesw.so.6`) kullanır, bu yüzden Android
çerçevesinin kendi kitaplıklarıyla asla çakışmazlar. `LD_LIBRARY_PATH` yalnızca `sqlite3`
işlemi için ayarlanır, asla genel olarak değil.

## Dosyalar

Üst kaynaktaki **SQLite** komut satırı kabuğu, Termux paketi **sqlite 3.54.1-1**. ABI
başına bir yük: `sqlite3` ikilisi ve onun dört kitaplıklı kapanışı (`libreadline.so.8`,
`libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Gönderilen her dosya,
SHA-256'sı ve boyutuyla:

`arm64-v8a`:

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a`:

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

Aynı sağlama toplamları, ikilinin yanında gönderilen her `bin/<abi>/PROVENANCE.json`
içinde kaydedilir.

## Kendiniz doğrulayın

Kaynağın bir kopyasından (checkout), gönderilen her dosyayı doğrulayın:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Her sağlama (hash), yukarıdaki listeyle eşleşmeli ve uygulama ile WebUI kopyaları
birbiriyle eşleşmelidir. Tüm yükü baştan sona denetlemek için, her `PROVENANCE.json`
içinde listelenen sabitlenmiş `.deb` URL'lerini yeniden alın, çıkarın ve dosya SHA-256
değerlerini karşılaştırın.

## Ne için kullanılır

`sqlite3`, kenara alınmış bir veritabanı kopyasına karşı, yalnızca **okumak** için root
olarak çalıştırılır. Google'ın hiçbir veritabanına yazmak için asla kullanılmaz. Tek işi,
VD Google'ın her cihazda aynı değerleri okumasını sağlamaktır. Başka hiçbir yerel ikili
paketlenmez.
