> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · Bahasa Indonesia · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Berkontribusi

Terima kasih atas ketertarikan Anda pada VD Google. Kontribusi disambut baik: terjemahan, laporan bug, dokumentasi, dan kode.

## Aturan dasar

- **VD Google membaca kebenaran dan tidak pernah memalsukan apa pun.** Kontribusi harus menjaganya tetap demikian. Nilai negatif yang nyata tetap negatif; cache usang diberi label tersimpan; nilai yang hilang adalah Unknown. Jangan memperhalus suatu pembacaan agar terlihat lebih baik.
- **Privasi bersifat mutlak.** Aplikasi tidak mendeklarasikan izin apa pun dan WebUI tidak menghubungi origin jarak jauh mana pun. Jangan pernah menambahkan izin, panggilan jaringan, atau apa pun yang mengirim data keluar dari perangkat.
- **Aplikasi dan WebUI identik.** Setiap perubahan pada apa yang ditampilkan, atau pada laporan ekspor, harus diterapkan pada keduanya, dengan kata-kata yang sama. Keduanya tidak boleh melenceng.
- **Label hidup sekali saja, dalam 21 bahasa.** Setiap string yang dihadapi pengguna didefinisikan untuk aplikasi (`res/values*/strings.xml`) dan WebUI (`webroot/lang/`). Jika Anda menambah atau mengubah sebuah label, perbarui semua bahasa.

## Terjemahan

Terjemahan adalah kontribusi yang paling mudah dan paling berharga. Readme diterjemahkan per bahasa (`README.<lang>.md`), string aplikasi dan WebUI per bahasa, dan dokumentasi ini di bawah `docs/<lang>/`. Jaga konsistensi terminologi dalam satu bahasa, sesuaikan dengan nada yang sudah ada, dan jangan pernah menerjemahkan dengan mesin sebuah istilah teknis yang tidak Anda yakini.

## Melaporkan bug

Buka issue dengan model perangkat Anda, versi Android, dan - jika menyangkut suatu pembacaan - sebuah **export** (tidak memuat data pribadi). Bagian diagnostik pada export dibuat justru untuk hal ini.

## Kode

- Build release, tidak pernah debug. Uji pada perangkat nyata yang sudah di-root.
- Jauhkan komentar dari kode; nama menjelaskan maksudnya. Satu-satunya header adalah banner lisensi di bagian atas setiap berkas sumber.
- Sesuaikan dengan gaya yang ada dan jaga perubahan tetap terfokus.

## Lisensi

Dengan berkontribusi Anda menyetujui bahwa kontribusi Anda dilisensikan di bawah **GNU AGPL-3.0-or-later** proyek ini.
