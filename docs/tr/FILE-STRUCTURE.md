> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · Türkçe · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# Dosya yapısı

VD Google deposunun (repository) nasıl düzenlendiği, böylece kaynak içinde yolunuzu
bulabilirsiniz.

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

## Her şeyin yeri

- **Okuyucular (readers)** - VD Google'ın ne okuduğu ve nasıl okuduğu - yalnızca
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (uygulama) ve
  `VDGoogle/webui/webroot/scripts.js` (WebUI) içinde yaşar. İkisi birbirini tam olarak
  yansıtır.
- **Dizeler**, 21 dilde her etiketin tek kaynağıdır: uygulama `res/values*/strings.xml`
  altında, WebUI `webroot/lang/` altında. Bir etiket asla iki kez yazılmaz.
- **Belgeler**, `docs/` içinde yaşar. Proje wiki'sinin doğruluk kaynağıdır; wiki ondan
  üretilir (bkz. `tools/` ve `.github/scripts/gen_wiki.sh`).
- **Paket içindeki araç** (`sqlite3`), uygulama için `assets/bin/` ve modül için `bin/`
  içinde yaşar ve [Paket içindeki ikililer](BINARIES.md) belgesinde belgelenmiştir.

## Derleme çıktısı

Release derlemeleri, APK'yi ve WebUI ZIP'ini üretir. Derleme yapıtları işlenmez (commit
edilmez). Nasıl derleneceği için benioku dosyasına bakın.

## Çalışma zamanı durumu

Cihazda, WebUI modülü durumunu `/data/adb/vdgoogle/` altında tutar:

- `scratch/` - bir veritabanının kısa ömürlü kopyaları; bir sorgunun canlı dosyaya asla
  dokunmaması için kenara alınır. Her okuma kendi kopyalarını siler ve kesintiye uğramış
  bir çalıştırmadan kalan her şey, WebUI bir sonraki açılışında temizlenir. Burada hiçbir
  şey saklanmaz.
- `actions.jsonl` - durumu değiştiren eylemlerin (Google Wallet önbellek temizleme) yerel
  günlüğü; her biri bir JSON satırı, son 200 girdiyle sınırlı. Cihazdan asla çıkmaz.

Uygulama ise aynı kısa ömürlü scratch kopyalarını kendi özel önbelleğinde tutar ve eylem
günlüğünü kendi özel dosyalar dizininin içine yazar.
