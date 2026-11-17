> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · Bahasa Indonesia · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Arsitektur

Bagaimana VD Google dibangun dan mengapa. Halaman ini menjelaskan desain dan prinsipnya. Halaman ini tidak mencantumkan berkas, tabel, atau kueri persis yang digunakan setiap pembaca: itu hanya tersimpan di dalam sumber, memang disengaja, agar aplikasi dan dokumentasi tidak pernah melenceng menjadi panduan praktis bagi seseorang yang ingin memalsukan nilai-nilai yang sama.

## Tujuan desain

1. **Baca kebenaran.** Tampilkan apa yang sudah direkam oleh aplikasi Google sendiri, jelaskan, dan jangan pernah mengarang suatu nilai. Ketika perangkat tidak menyimpan jawaban, katakan Unknown.
2. **Nol jaringan.** Aplikasi tidak mendeklarasikan izin apa pun; WebUI memblokir setiap origin jarak jauh. Tidak ada yang pernah dikirim ke mana pun, selamanya.
3. **Satu perilaku, dua wajah.** Aplikasi dan modul WebUI dijaga benar-benar identik: sinyal yang sama, kata-kata yang sama, laporan ekspor yang sama.
4. **Jujur di atas menyenangkan.** Nilai negatif yang nyata ditampilkan sebagai negatif; cache usang diberi label sebagai tersimpan; nilai yang hilang adalah Unknown. Deteksi tidak pernah diperhalus agar terlihat lebih baik.

## Dua wajah

- **Aplikasi** ditulis dengan Kotlin dan Jetpack Compose (Material 3). Ia memperoleh root melalui libsu `RootService`, tidak pernah dengan memanggil `su` secara langsung, dan ia membaca di dalam layanan berprivilese tersebut.
- **Modul WebUI** adalah root web HTML/JS kecil yang disajikan oleh pengelola root Anda (KernelSU, APatch, atau Magisk). Ia melakukan pembacaan yang sama melalui jembatan root pengelola. Content-Security-Policy yang ketat melarang setiap origin jarak jauh, sehingga halaman tidak bisa menghubungi luar bahkan secara prinsip.

Keduanya dihasilkan dari satu set string bersama dalam 21 bahasa, sehingga sebuah label tidak pernah diterjemahkan dua kali.

## Membaca basis data: sqlite3 yang disertakan

Sebagian status yang dibaca VD Google tersimpan dalam basis data SQLite milik aplikasi Google. Android menyertakan SQLite-nya sendiri, tetapi SQLite kerangka kerja tidak andal untuk membuka salinan basis data aplikasi lain yang disalin ke samping: pada sejumlah ROM pabrikan ia gagal untuk *setiap* basis data, dan tidak mengembalikan apa pun. Agar membaca dengan cara yang sama di setiap perangkat, VD Google menyertakan biner `sqlite3`-nya sendiri (arm64 dan arm32) dan menjalankan kueri melaluinya sebagai root. Aplikasi dan WebUI menggunakan biner yang identik, sehingga keduanya selalu membaca mesin yang sama dan hasil yang sama. Asal-usul dan checksum biner itu didokumentasikan di [Biner yang disertakan](BINARIES.md).

Pembacaan menyalin basis data ke samping sebelum membukanya, tidak pernah menyentuh berkas langsung, dan tidak pernah menyalin indeks memori-bersama, yang bisa dilaporkan rusak oleh basis data yang sedang berjalan.

## Sinyal RCS

Bagian tersulit dalam membaca RCS secara jujur adalah bahwa flag yang tampak jelas justru berbohong. Flag "available" sederhana, flag fitur, dan status provisioning semuanya bisa terbaca negatif pada sebuah jalur tempat RCS bekerja dengan sempurna, karena itu adalah cache atau karena jalur tersebut berjalan di backend Google sendiri (Jibe) alih-alih di jaringan IMS operator. VD Google menghitung satu putusan **Connection** dari konfigurasi obrolan yang sebenarnya dan mesin provisioning, yang tetap benar lintas versi Android dan Google Messages, serta menampilkan flag-flag yang berbohong itu dengan label jelas sebagai tersimpan agar memberi informasi tanpa menyesatkan. Lihat [panduan pengguna](USER-GUIDE.md) untuk arti setiap baris.

## Ekspor

Ekspor adalah snapshot JSON yang telah dibersihkan. Sebuah penyanitasi berjalan sebelum apa pun ditulis: ia membuang kunci dan nilai yang tampak seperti kredensial, menyunting rangkaian digit panjang, dan tidak pernah memancarkan nomor telepon Anda, isi pesan, nama kontak, atau blob kapabilitas mentah. Bagian diagnostik opsional hanya membawa nama skema, jumlah, ukuran, dan versi; dan bahkan di situ, setiap sidik jari per-identitas yang tertanam dalam sebuah nama diringkas hingga hilang. Aplikasi dan WebUI menghasilkan skema yang sama.

## Apa yang tidak pernah dilakukan VD Google

- Ia tidak pernah mendeklarasikan izin jaringan, dan WebUI tidak pernah menghubungi origin jarak jauh.
- Ia tidak pernah mengarang, memalsukan, atau menyembunyikan suatu nilai.
- Ia tidak pernah melaporkan putusan Play Integrity, karena putusan itu tidak ada di perangkat.
- Ia mengubah status hanya melalui tindakan yang eksplisit, dikonfirmasi, dan dicatat secara lokal.

<!-- doc-footer -->

---

> _Dokumentasi ini dibuat dengan Claude (Anthropic) dan dikelola oleh VD171. Koreksi dan perbaikan dipersilakan; lihat [CONTRIBUTING.md](CONTRIBUTING.md)._
