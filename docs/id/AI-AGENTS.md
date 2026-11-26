> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · Bahasa Indonesia · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Agen AI

VD Google mengubah catatan milik Google di perangkat menjadi sesuatu yang **dapat dibaca agen AI dalam satu langkah**. Alih-alih membuka beberapa aplikasi dan basis data Google lalu menebak maknanya, agen membaca satu **ekspor** terstruktur dan tahu persis apa yang dilaporkan perangkat tentang RCS, Google Wallet, dan Play Integrity.

Halaman ini memperluas gagasan yang diperkenalkan di README.

## Ekspor

Baik aplikasi maupun WebUI menghasilkan ekspor JSON yang sama:

- **Aplikasi**: tindakan **Ekspor** membagikan atau menyimpan laporan.
- **WebUI**: tindakan **Ekspor** menuliskannya ke `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Keduanya memiliki skema yang sama, karena aplikasi dan WebUI dijaga tetap identik. Tidak ada yang dikirim ke mana pun: ekspor ditulis di perangkat dan tetap di sana.

## Apa isinya

Ekspor adalah satu objek JSON dengan kunci tingkat atas berikut:

- `app`, `version`, `exportedAt`, `timezone` - apa yang menghasilkan laporan dan kapan, dalam zona waktu berlabel perangkat.
- `device` - produsen, model, versi Android, dan level SDK.
- `rcs` - ketersediaan, status, versi konfigurasi, kontak yang kompatibel, otorisasi operator, kehadiran (UCE), dan stempel waktu terkait.
- `wallet` - atestasi pembayaran, perangkat keras NFC, status tap-to-pay, penerimaan kunci layar, dan apakah kartu pernah ditambahkan.
- `diagnostics` - bagian pemeriksaan mandiri, dibuat untuk laporan bug.

Ekspor tidak membawa **data pribadi apa pun**: tidak ada nomor telepon, tidak ada kredensial, tidak ada isi pesan, tidak ada nama kontak. Setiap nilai adalah pembacaan yang jujur - sebuah negatif yang nyata tetap negatif, cache usang diberi label sebagai tersimpan, nilai yang hilang adalah Tidak diketahui.

## Hanya-baca, dengan satu tindakan tercatat

VD Google membaca. Satu-satunya pengecualian adalah tindakan sanitasi (menghapus hasil atestasi Google Wallet yang tersimpan di cache untuk memaksa pemeriksaan ulang). Setiap tindakan yang mengubah status meminta konfirmasi di perangkat dan ditulis ke log tindakan lokal dalam JSON. Agen yang mengendalikan WebUI melihat konfirmasi yang sama dan log yang sama, tidak ada jalur tersembunyi atau tak tercatat.

## Tanpa kontrak jaringan

VD Google tidak mengekspos API jaringan maupun soket: ia tidak mendeklarasikan izin apa pun. Agen mengonsumsi **berkas ekspor**, bukan memanggil layanan. Itulah jaminan privasi, yang dipertahankan bahkan untuk otomatisasi.

<!-- doc-footer -->

---

> _Dokumentasi ini dibuat dengan Claude (Anthropic) dan dikelola oleh VD171. Koreksi dan perbaikan dipersilakan, lihat [CONTRIBUTING.md](CONTRIBUTING.md)._
