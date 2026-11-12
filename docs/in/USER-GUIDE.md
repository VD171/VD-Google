> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · Bahasa Indonesia · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Panduan pengguna VD Google

Penjelasan yang ramah dan lengkap tentang VD Google: apa yang ditampilkannya, arti setiap baris, dan cara membaca perangkat Anda secara jujur. VD Google membaca status RCS, Google Wallet, dan atestasi Play Integrity yang diminta oleh aplikasi, serta menjelaskan setiap item dengan kata-kata sederhana. Ia tidak pernah menyentuh internet, dan tidak pernah mengubah apa pun kecuali Anda meminta.

## Daftar isi

- [1. Apa itu VD Google](#1-apa-itu-vd-google)
- [2. Apa yang bukan](#2-apa-yang-bukan)
- [3. Persyaratan](#3-persyaratan)
- [4. Aplikasi atau WebUI](#4-aplikasi-atau-webui)
- [5. Instalasi dan menjalankan pertama kali](#5-instalasi-dan-menjalankan-pertama-kali)
- [6. Tab](#6-tab)
  - [6.1 Tentang](#61-tentang)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Atestasi](#65-atestasi)
- [7. Ekspor laporan Anda](#7-ekspor-laporan-anda)
- [8. Tindakan yang mengubah status](#8-tindakan-yang-mengubah-status)
- [9. Bahasa dan tema](#9-bahasa-dan-tema)
- [10. Pertanyaan yang sering diajukan](#10-pertanyaan-yang-sering-diajukan)

## 1. Apa itu VD Google

VD Google adalah sebuah pembaca. Ia melihat apa yang sudah disimpan oleh aplikasi Google sendiri di perangkat Anda tentang tiga hal, dan menjelaskan masing-masing agar Anda benar-benar memahaminya:

- **RCS** - apakah obrolan modern benar-benar bekerja pada jalur Anda, dengan siapa Anda bisa mengobrol, dan apakah Gemini di Google Messages sudah siap.
- **Google Wallet** - apakah perangkat Anda telah disiapkan dan diatestasi untuk pembayaran nirsentuh.
- **Atestasi Play Integrity** - aplikasi mana yang telah meminta Android untuk membuktikan bahwa perangkat Anda asli, dan apakah Google saat ini menyertifikasinya.

Semuanya dibaca secara lokal, melalui root, dan ditampilkan dengan penjelasan. Tidak ada nilai yang dikarang: ketika perangkat tidak menyimpan jawaban, VD Google mengatakan "Unknown" alih-alih menebak.

## 2. Apa yang bukan

**VD Google bukan pemeriksa integritas.** Ia tidak menjalankan atau melaporkan putusan basic / device / strong apa pun. Hasil itu hanya ada di dalam token terenkripsi yang dikirim sebuah aplikasi ke servernya sendiri, dan tidak pernah disimpan di perangkat, sehingga tidak ada pembaca yang jujur yang bisa menampilkannya. Alat yang mengaku menampilkan "putusan Play Integrity" Anda secara lokal sebenarnya menunjukkan tebakan tersimpan atau permintaan yang mereka buat sendiri, bukan jawaban yang sebenarnya.

VD Google juga tidak melakukan spoof, menyembunyikan, atau memalsukan apa pun. Ia membaca kebenaran. Jika sebuah nilai tampak salah bagi Anda, tujuan panduan ini adalah menjelaskan mengapa kebenaran tampak seperti itu.

## 3. Persyaratan

- **Android 8.0 (API 26) atau yang lebih baru.**
- **Root.** Data yang dibaca VD Google berada di area Android yang terlindungi dan hanya-sistem, yang hanya dapat dijangkau dengan root. Solusi root modern apa pun bisa digunakan: KernelSU, KernelSU Next, Magisk, atau APatch.
- **Tanpa internet.** VD Google tidak mendeklarasikan izin apa pun, jadi tidak ada yang perlu diizinkan dan tidak ada yang perlu diblokir.

## 4. Aplikasi atau WebUI

VD Google hadir dalam dua cara, dan keduanya menampilkan informasi yang persis sama:

- **Aplikasi (APK).** Aplikasi Android biasa yang Anda pasang dan buka. Meminta root pertama kali, seperti aplikasi root mana pun.
- **Modul WebUI (ZIP).** Flash di pengelola root Anda (KernelSU, Magisk, atau APatch) dan buka dari sana. Tidak ada yang dipasang sebagai aplikasi; ia berjalan di dalam WebUI pengelola root Anda.

Pilih mana pun yang Anda sukai. Layar, kata-kata, dan laporan ekspornya identik di antara keduanya.

## 5. Instalasi dan menjalankan pertama kali

**Aplikasi:** pasang APK dan buka. Pertama kali, berikan root saat pengelola root Anda meminta. Jika Anda menolak atau mengabaikan root, VD Google menampilkan layar yang menjelaskan mengapa root diperlukan dan menawarkan untuk mencoba lagi.

**WebUI:** flash ZIP sebagai modul di pengelola root Anda, lalu buka WebUI modul dari pengelola. Ia langsung membaca begitu dibuka.

Pada pembukaan pertama Anda bisa memilih bahasa; Anda bisa mengubahnya nanti dari ikon roda gigi di bagian atas.

## 6. Tab

Di sepanjang bagian bawah terdapat lima tab: About, DroidGuard, RCS, Wallet, dan Attestations. About terbuka lebih dulu.

### 6.1 Tentang

Tentang apa aplikasi ini, janji privasi, versi, dan tautan. Baca bagian privasi sekali: itulah seluruh inti dari aplikasi ini.

### 6.2 RCS

Ini adalah tab yang paling kaya. Stempel waktu selalu ditampilkan dalam zona waktu perangkat Anda, dengan zonanya diberi label.

- **Connection** - satu baris yang mengatakan kebenaran. Ia dihitung dari konfigurasi obrolan yang sebenarnya dan mesin provisioning, sehingga ia mengatakan Connected ketika RCS benar-benar bekerja pada jalur Anda, bahkan pada Android yang lebih baru tempat flag lama berbohong. **Saat ragu, percayai baris ini.**
- **Carrier** - operator seluler untuk jalur Anda (nama dan MCC/MNC), diambil dari SIM data Anda pada ponsel dual-SIM.
- **Availability flag (cached)** - sebuah flag tersimpan yang dijaga aplikasi perpesanan untuk nomor Anda sendiri. Ia bisa tetap usang setelah RCS sudah bekerja, jadi "No" di sini tidak berarti RCS mati. Percayai baris Connection di atas.
- **Your RCS number** - nomor yang terdaftar untuk RCS, disamarkan secara default. Ketuk ikon mata untuk menampilkannya.
- **Provisioning state (cached)** dan **Configuration version** - nilai internal yang dijaga aplikasi perpesanan. Keduanya bisa terbaca 0 bahkan saat RCS bekerja. Lihat catatan tentang operator vs backend Google di bawah.
- **RCS-capable contacts** - berapa banyak kontak Anda yang saat ini diketahui mendukung obrolan RCS.
- **Feature flag (cached)** - apakah aplikasi perpesanan mengaktifkan fitur RCS; tersimpan, jadi bisa tertinggal.
- **Carrier entitlement**, **Presence (UCE)**, **IMS service present** - ini menggambarkan jalur RCS *operator* (jaringan IMS operator). "No" atau "0" di sini normal ketika RCS Anda berjalan melalui backend Google sendiri alih-alih operator Anda. Lihat di bawah.
- **Gemini** - apakah Gemini di Google Messages sudah siap: **Magic Compose** (bantuan menulis Gemini), **AICore** (mesin di perangkat yang menjalankan Gemini Nano), dan **Gemini chat**, yang dikirim melalui RCS sehingga membutuhkan RCS terhubung.
- **Timestamps** - provisioning, sinkronisasi, pesan terakhir, kedaluwarsa token, dan lainnya.

**RCS operator vs backend Google.** Ada dua cara RCS mencapai ponsel Anda. Yang satu adalah RCS *operator* Anda melalui jaringan IMS mereka; yang lain adalah *backend Google sendiri* (Jibe), yang bekerja di atasnya, independen dari operator. Jika RCS Anda berjalan melalui backend Google - seperti pada kebanyakan orang - maka baris-baris sisi-operator (carrier entitlement, presence/UCE, IMS service, configuration version) memang benar-benar "No" atau "0", dan itu benar, bukan cache usang. RCS Anda bisa jadi sudah bekerja selama bertahun-tahun dengan cara ini. Baris Connection-lah yang memberi tahu Anda bahwa ia bekerja.

### 6.3 Wallet

- **Device attested** - apakah Google menilai perangkat ini cukup asli dan aman untuk menyimpan kartu pembayaran. Ini adalah hasil terakhir yang disimpan Google di perangkat ini, sehingga bisa tertinggal dari aplikasi Wallet hingga Google memeriksa ulang. Jika hasil tersimpan tidak ada, VD Google menampilkan **Unknown** alih-alih "No" yang menyesatkan.
- **Last checked** - kapan pemeriksaan keamanan itu terakhir selesai.
- **NFC hardware**, **Wallet installed**, **Wallet service**, **Tap and pay**, **Screen lock accepted**, **Card ever added** - bagian-bagian yang memungkinkan pembayaran nirsentuh.

### 6.4 DroidGuard

- **Play Protect certified** - apakah Google Play mengenali perangkat Anda sebagai tersertifikasi (sertifikasi yang ditampilkan di Play Store).
- **Attestation cache** - atestasi Play Integrity dan DroidGuard yang telah di-cache perangkat Anda, masing-masing dengan kapan ia kedaluwarsa.

### 6.5 Atestasi

Riwayat aplikasi mana yang meminta Play Integrity di perangkat Anda: ikon, nama, kapan terakhir meminta, dan berapa kali. Ini memungkinkan Anda mengetahui apakah suatu aplikasi memeriksa integritas atau tidak. Gunakan kotak pencarian untuk menyaring berdasarkan nama aplikasi atau paket.

## 7. Ekspor laporan Anda

Ikon unduh menulis laporan **JSON** dengan status perangkat, RCS, Wallet, dan atestasi Anda. Anda bisa membagikannya atau menyimpannya sebagai berkas. Laporan tidak memuat **nomor telepon, kredensial, dan konten pribadi apa pun** - ia dibersihkan sebelum ditulis. Ia juga menyertakan bagian **diagnostik** opsional (nama skema, jumlah, dan versi, tidak pernah data Anda) yang membantu memecahkan masalah khusus perangkat. Aplikasi dan WebUI menulis laporan yang sama.

## 8. Tindakan yang mengubah status

VD Google sebagian besar hanya-baca, tetapi ia bisa melakukan satu **tindakan penyanitasian**: menghapus hasil atestasi Google Wallet yang tersimpan di cache, yang memungkinkan pemeriksaan ulang di masa depan dimulai dari awal. Setiap tindakan yang mengubah status meminta Anda mengonfirmasi terlebih dahulu, dan ditulis ke log JSON lokal di perangkat. Tidak ada yang meninggalkan ponsel Anda.

## 9. Bahasa dan tema

VD Google berbicara dalam 21 bahasa. Pilih bahasa Anda saat pertama kali dijalankan, atau ubah kapan saja dari ikon roda gigi (juga tersedia di layar root-ditolak). Ikon kecerahan beralih antara terang dan gelap.

## 10. Pertanyaan yang sering diajukan

**RCS mengatakan Connected tetapi beberapa baris mengatakan No atau 0. Mengapa?** Karena RCS Anda berjalan melalui backend Google sendiri, bukan operator Anda. Baris-baris sisi-operator memang benar negatif. Lihat [6.2 RCS](#62-rcs).

**Device attested mengatakan Unknown.** Hasil Wallet yang tersimpan di cache tidak ada di perangkat Anda. Itu jujur - VD Google tidak akan menampilkan "No" yang tidak bisa dibuktikannya.

**Di mana putusan Play Integrity saya?** Ia tidak ada di perangkat Anda. Lihat [2. Apa yang bukan](#2-apa-yang-bukan).

**Apakah ia bekerja tanpa root?** Tidak. Datanya berada di area hanya-sistem.
