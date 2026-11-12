> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · فارسی · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# ساختار فایل‌ها

چیدمان مخزن VD Google چگونه است، تا بتوانید در کد منبع راه خود را بیابید.

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

## هر چیز کجا زندگی می‌کند

- **خواننده‌ها** - آنچه VD Google می‌خواند و شیوهٔ خواندن آن - تنها در `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (اپلیکیشن) و `VDGoogle/webui/webroot/scripts.js` (WebUI) زندگی می‌کنند. این دو دقیقاً آینهٔ یکدیگرند.
- **رشته‌ها** منبع یگانهٔ هر برچسب‌اند، در ۲۱ زبان: اپلیکیشن زیر `res/values*/strings.xml`، و WebUI زیر `webroot/lang/`. یک برچسب هرگز دو بار نوشته نمی‌شود.
- **مستندات** در `docs/` زندگی می‌کنند. این‌ها منبع حقیقتِ ویکی پروژه‌اند؛ ویکی از روی آن‌ها تولید می‌شود (به `tools/` و `.github/scripts/gen_wiki.sh` مراجعه کنید).
- **ابزار همراه** (`sqlite3`) برای اپلیکیشن در `assets/bin/` و برای ماژول در `bin/` زندگی می‌کند، و در [باینری‌های همراه](BINARIES.md) مستند شده است.

## خروجی ساخت

ساخت‌های release فایل APK و ZIP مربوط به WebUI را تولید می‌کنند. فرآورده‌های ساخت در مخزن ثبت نمی‌شوند. برای نحوهٔ ساخت به readme مراجعه کنید.

## وضعیت زمان اجرا

روی دستگاه، ماژول WebUI وضعیت خود را زیر `/data/adb/vdgoogle/` نگه می‌دارد:

- `scratch/` - نسخه‌های زودگذری از یک پایگاه داده، که کنار گذاشته می‌شوند تا یک پرس‌وجو هرگز به فایل زنده دست نزند. هر خواندن نسخه‌های خودش را حذف می‌کند، و هر باقی‌مانده از یک اجرای قطع‌شده دفعهٔ بعد که WebUI باز می‌شود پاک می‌شود. هیچ‌چیز در اینجا نگه داشته نمی‌شود.
- `actions.jsonl` - گزارش محلیِ کنش‌های تغییردهندهٔ وضعیت (پاک کردن حافظهٔ نهانِ Google Wallet)، هر کدام یک خط JSON، محدود به ۲۰۰ ورودیِ آخر. هرگز دستگاه را ترک نمی‌کند.

اپلیکیشن همان نسخه‌های زودگذرِ scratch را در عوض در حافظهٔ نهانِ خصوصیِ خودش نگه می‌دارد، و گزارش کنش خود را درون پوشهٔ فایل‌های خصوصیِ خودش می‌نویسد.
