> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · Bahasa Indonesia · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Biner yang disertakan: asal-usul dan audit

VD Google berjanji bahwa tidak ada yang buram dikirim di dalamnya. Ia berjalan sebagai root dan membaca status Anda yang paling pribadi, sehingga Anda harus bisa melihat persis apa yang dibawanya. Halaman ini mencantumkan satu alat native yang disertakan oleh aplikasi dan modul, dari mana ia berasal, dan cara memverifikasinya.

## Prinsip

VD Google menyertakan tepat satu alat native: perintah `sqlite3`, beserta sekumpulan kecil pustaka bersama yang dibutuhkannya. Ia diperlukan karena SQLite kerangka kerja Android sendiri gagal membuka basis data aplikasi lain pada sejumlah ROM pabrikan, yang akan membuat VD Google tidak membaca apa pun di perangkat tersebut. Lihat [Arsitektur](ARCHITECTURE.md) untuk alasannya.

- **Diambil dari upstream, tidak pernah dari perangkat.** `sqlite3` dan pustakanya berasal dari repositori paket **Termux** resmi (`packages.termux.dev/apt`), paket `.deb` yang telah ditandatangani, tidak pernah disalin dari Termux yang terpasang di suatu ponsel.
- **Byte yang sama di mana-mana.** Berkas yang identik dikirim dalam aplikasi (di bawah `assets/bin/`) dan dalam modul WebUI (di bawah `bin/`), sehingga keduanya membaca melalui satu mesin dan sepakat.
- **Checksum adalah identitasnya.** Di jalur mana pun sebuah salinan berada, ia adalah alat yang sebenarnya hanya jika SHA-256-nya cocok dengan nilai yang dicatat.
- **Jejak audit yang tercatat.** Setiap direktori ABI membawa sebuah `PROVENANCE.json` yang mencantumkan setiap paket sumber (nama, versi, URL, SHA-256 `.deb`) dan SHA-256 setiap berkas yang dikirim. Tidak ada yang disertakan tanpa entri yang bisa Anda periksa.

## Asal-usul

`.github/scripts/build_bin.sh` merakit payload pada saat build, tanpa kompilator dan tanpa NDK. Untuk setiap ABI, ia:

1. membaca indeks Termux Packages dan menyelesaikan paket `sqlite` beserta dependensi transitifnya (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. mengunduh dan mengekstrak berkas `.deb` resmi yang telah ditandatangani;
3. menyalin `sqlite3`, lalu menyalin hanya closure pustaka bersama yang benar-benar dibutuhkan biner tersebut (diselesaikan dengan `readelf`), melewati pustaka yang selalu disediakan Android sendiri;
4. menulis `PROVENANCE.json`.

Biner Termux ditautkan secara dinamis terhadap pustaka Termux sendiri, sehingga VD Google mengirim closure itu bersama `sqlite3` dan menjalankannya dengan `LD_LIBRARY_PATH` yang menunjuk ke direktori `lib/` yang disertakan. Termux menggunakan soname berversi (`libz.so.1`, `libreadline.so.8`, `libncursesw.so.6`), sehingga keduanya tidak pernah berbenturan dengan pustaka milik kerangka kerja Android sendiri. `LD_LIBRARY_PATH` disetel hanya untuk proses `sqlite3`, tidak pernah secara global.

## Berkas-berkasnya

Shell baris perintah **SQLite** upstream, paket Termux **sqlite 3.54.1-1**. Satu payload per ABI: biner `sqlite3` beserta closure empat-pustakanya (`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Setiap berkas yang dikirim, dengan SHA-256 dan ukurannya:

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

Checksum yang sama dicatat dalam setiap `bin/<abi>/PROVENANCE.json`, yang dikirim di samping biner.

## Verifikasi sendiri

Dari checkout sumber, pastikan setiap berkas yang dikirim:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Setiap hash harus cocok dengan daftar di atas, dan salinan aplikasi dan WebUI harus cocok satu sama lain. Untuk mengaudit seluruh payload dari ujung ke ujung, ambil ulang URL `.deb` yang dipatok yang tercantum di setiap `PROVENANCE.json`, ekstrak, dan bandingkan nilai SHA-256 berkasnya.

## Untuk apa ia digunakan

`sqlite3` dijalankan sebagai root, terhadap salinan sebuah basis data yang diambil ke samping, murni untuk **membaca**. Ia tidak pernah digunakan untuk menulis ke basis data Google mana pun. Satu-satunya tugasnya adalah membiarkan VD Google membaca nilai yang sama di setiap perangkat. Tidak ada biner native lain yang disertakan.
