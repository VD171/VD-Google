> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · Bahasa Indonesia · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Struktur berkas

Bagaimana repositori VD Google ditata, agar Anda bisa menemukan jalan di dalam sumber.

```
VD-Google/
├── README.md, README.<lang>.md       # the readme, in 21 languages
├── LICENSE                           # GNU AGPL-3.0-or-later
├── CHANGELOG.md                      # user-facing release notes
├── images/                           # screenshots used by the readme
├── docs/                             # this documentation (source of the wiki)
│   ├── USER-GUIDE.md, ARCHITECTURE.md, PRIVACY.md, BINARIES.md, FILE-STRUCTURE.md
│   └── <lang>/                       # the same docs in each language
├── tools/                            # maintenance scripts (wiki, docs nav)
├── .github/                          # CI and the wiki-sync workflow
│   ├── workflows/
│   └── scripts/gen_wiki.sh
└── VDGoogle/                         # the Gradle project
    ├── app/                          # the Android app
    │   └── src/main/
    │       ├── java/ru/vd171/vdgoogle/
    │       │   ├── data/             # the readers and their data models
    │       │   ├── ui/               # Compose screens and view model
    │       │   ├── root/             # the libsu RootService bridge
    │       │   └── util/             # export, formatting, action log
    │       ├── res/                  # layouts and the 21-language strings
    │       └── assets/bin/           # the bundled sqlite3 (see BINARIES.md)
    └── webui/                        # the WebUI module
        ├── module.prop, customize.sh, update.json
        ├── bin/                      # the bundled sqlite3, per ABI
        └── webroot/                  # the HTML/JS/CSS WebUI
            ├── index.html, scripts.js
            └── lang/                 # the 21-language strings for the WebUI
```

## Di mana segala sesuatu berada

- **Para pembaca (readers)** - apa yang dibaca VD Google dan bagaimana ia membacanya - hanya berada di `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (aplikasi) dan `VDGoogle/webui/webroot/scripts.js` (WebUI). Keduanya saling mencerminkan dengan persis.
- **String** adalah satu-satunya sumber setiap label, dalam 21 bahasa: aplikasi di bawah `res/values*/strings.xml`, WebUI di bawah `webroot/lang/`. Sebuah label tidak pernah ditulis dua kali.
- **Dokumentasi** berada di `docs/`. Ia adalah sumber kebenaran untuk wiki proyek; wiki dihasilkan darinya (lihat `tools/` dan `.github/scripts/gen_wiki.sh`).
- **Alat yang disertakan** (`sqlite3`) berada di `assets/bin/` untuk aplikasi dan `bin/` untuk modul, dan didokumentasikan di [Biner yang disertakan](BINARIES.md).

## Keluaran build

Build release menghasilkan APK dan ZIP WebUI. Artefak build tidak di-commit. Lihat readme untuk cara membangun.

## Status runtime

Di perangkat, modul WebUI menyimpan statusnya di bawah `/data/adb/vdgoogle/`:

- `scratch/` - salinan berumur-pendek dari sebuah basis data, diambil ke samping agar sebuah kueri tidak pernah menyentuh berkas langsung. Setiap pembacaan menghapus salinannya sendiri, dan sisa apa pun dari proses yang terhenti akan dibersihkan pada kali berikutnya WebUI dibuka. Tidak ada yang disimpan di sini.
- `actions.jsonl` - log lokal tindakan yang mengubah status (penghapusan cache Google Wallet), masing-masing satu baris JSON, dibatasi hingga 200 entri terakhir. Ia tidak pernah meninggalkan perangkat.

Sebagai gantinya, aplikasi menyimpan salinan scratch berumur-pendek yang sama di cache privatnya sendiri, dan menulis log tindakannya di dalam direktori berkas privatnya sendiri.
