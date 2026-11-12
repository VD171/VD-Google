> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · Bahasa Indonesia · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Kebijakan keamanan

VD Google adalah alat privasi. Ia berjalan sebagai root dan membaca status sensitif, sehingga laporan keamanan ditanggapi dengan serius.

## Melaporkan kerentanan

Harap laporkan masalah keamanan **secara pribadi**, bukan di issue publik:

- Kirim e-mail ke **vd.priv8@pm.me**.
- Jelaskan masalahnya, versi yang terdampak, dan cara mereproduksinya.
- Jika menyangkut paparan data, jelaskan kelas masalahnya; Anda tidak perlu melampirkan eksploit yang berfungsi.

Anda akan mendapat tanggapan secepat mungkin. Harap beri tenggang waktu yang wajar untuk memperbaiki masalah sebelum pengungkapan publik apa pun.

## Apa yang dihitung sebagai masalah keamanan

Karena apa yang dijanjikan VD Google, hal-hal berikut sangat penting:

- Apa pun yang akan membuat aplikasi mencapai jaringan, atau mengirim data apa pun keluar dari perangkat.
- Apa pun yang akan membocorkan data pribadi ke dalam sebuah **export** (nomor telepon, kredensial, isi pesan, nama kontak, atau sidik jari per-identitas).
- Apa pun yang memungkinkan satu-satunya tindakan penyanitasian itu mengubah status tanpa konfirmasi atau tanpa dicatat.

## Apa yang bukan masalah keamanan

- Sebuah pembacaan yang tampak "salah" tetapi merupakan kebenaran yang jujur (misalnya, baris RCS sisi-operator terbaca No padahal RCS berjalan di backend Google). Lihat [panduan pengguna](USER-GUIDE.md).
- Ketiadaan putusan Play Integrity. Putusan itu tidak ada di perangkat dan VD Google tidak akan mengarangnya.

## Versi yang didukung

Hanya versi rilis terbaru yang didukung. Harap reproduksi pada build terbaru sebelum melaporkan.
