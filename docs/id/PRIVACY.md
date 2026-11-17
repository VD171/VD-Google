> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · Bahasa Indonesia · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Українська](../uk/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Privasi

Privasi bukanlah sebuah fitur VD Google: itulah seluruh inti dari keberadaannya. Halaman ini menyatakan persis apa yang aplikasi lakukan dan tidak lakukan terhadap data Anda, dalam istilah yang jelas yang bisa Anda verifikasi.

## Janji

- **Tidak ada izin internet yang diberikan.** Manifes aplikasi tidak mendeklarasikan izin apa pun: tidak internet, tidak penyimpanan, tidak kontak, tidak ada apa-apa. Tidak ada yang perlu diizinkan.
- **Tidak ada yang disimpan, dikirim, atau ditransmisikan** ke berkas atau server mana pun di luar perangkat Anda.
- Setiap pembacaan terjadi **secara lokal, melalui root**, dan tetap berada di perangkat.

## Mengapa ia membutuhkan root alih-alih izin

Status RCS, Wallet, dan atestasi yang dijelaskan VD Google berada di area Android yang terlindungi dan hanya-sistem yang tidak bisa dilihat aplikasi biasa. VD Google membacanya dengan root alih-alih meminta izin yang luas kepada Anda. Root adalah jalan masuk yang sempit dan dapat diaudit; setumpuk izin runtime akan menjadi jalan yang luas dan bocor. Aplikasi memilih yang sempit.

## Ekspor telah dibersihkan

Ketika Anda mengekspor sebuah laporan, VD Google membersihkannya sebelum menulis:

- **Tidak pernah disertakan:** nomor telepon Anda, kredensial, cookie, atau token apa pun, isi pesan, nama kontak, atau blob kapabilitas mentah.
- **Disunting:** rangkaian digit panjang dan nilai yang tampak seperti rahasia diganti dengan placeholder.
- **Diagnostik** hanya membawa struktur (nama skema, jumlah, ukuran, versi). Setiap sidik jari per-identitas yang kebetulan muncul di dalam sebuah nama diringkas hingga hilang.

Laporan itu milik Anda. Ia ditulis di tempat yang Anda pilih, hanya dibagikan jika Anda membagikannya, dan tidak memuat apa pun yang mengidentifikasi Anda.

## Tindakan bersifat eksplisit dan dicatat

VD Google dapat menghapus hasil atestasi Google Wallet yang tersimpan di cache sehingga pemeriksaan ulang di masa depan dimulai dari awal. Setiap tindakan yang mengubah status:

1. meminta Anda mengonfirmasi terlebih dahulu, dan
2. ditulis ke log JSON lokal di perangkat.

Tidak ada tindakan yang pernah mencapai jaringan, dan tidak ada yang meninggalkan ponsel Anda.

## Verifikasi sendiri

Anda tidak harus mempercayai semua ini begitu saja:

- Sumbernya publik: <https://github.com/VD171/VD-Google>.
- Manifes ada di dalam sumber; pastikan ia tidak mendeklarasikan izin apa pun.
- Content-Security-Policy WebUI ada di dalam sumber; pastikan ia memblokir setiap origin jarak jauh.
- Biner yang disertakan didokumentasikan dengan checksum di [Biner yang disertakan](BINARIES.md).

<!-- doc-footer -->

---

> _Dokumentasi ini dibuat dengan Claude (Anthropic) dan dikelola oleh VD171. Koreksi dan perbaikan dipersilakan; lihat [CONTRIBUTING.md](CONTRIBUTING.md)._
