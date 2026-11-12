> **Languages:** [English](../FILE-STRUCTURE.md) · العربية · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# بنية الملفات

كيف يُنظَّم مستودع VD Google، كي تجد طريقك في الكود المصدري.

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

## أين يعيش كل شيء

- **القارئات** - ما يقرأه VD Google وكيف يقرأه - تعيش فقط في
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (التطبيق) و
  `VDGoogle/webui/webroot/scripts.js` (WebUI). والاثنان يعكسان أحدهما الآخر تمامًا.
- **السلاسل النصية** هي المصدر الوحيد لكل عنوان، في 21 لغة: التطبيق تحت
  `res/values*/strings.xml`، وWebUI تحت `webroot/lang/`. ولا يُكتَب عنوان مرتين أبدًا.
- **التوثيق** يعيش في `docs/`. وهو مصدر الحقيقة لويكي المشروع؛ وتُولَّد الويكي منه (راجع
  `tools/` و`.github/scripts/gen_wiki.sh`).
- **الأداة المرفقة** (`sqlite3`) تعيش في `assets/bin/` للتطبيق و`bin/` للموديول، وهي
  موثّقة في [الثنائيات المرفقة](BINARIES.md).

## مُخرَج البناء

تنتج عمليات بناء release ملف الـ APK وأرشيف ZIP الخاص بـ WebUI. ولا تُلتزَم (commit)
مخرجات البناء. راجع الـ readme لمعرفة كيفية البناء.

## الحالة في وقت التشغيل

على الجهاز، يحتفظ موديول WebUI بحالته تحت `/data/adb/vdgoogle/`:

- `scratch/` - نسخ قصيرة العمر من قاعدة بيانات، مأخوذة جانبًا كي لا يمسّ أي استعلام الملف
  الحيّ أبدًا. وتحذف كل قراءة نسخها الخاصة، ويُمسَح أي بقايا من تشغيل متوقّف في المرة
  التالية التي تُفتَح فيها WebUI. ولا يُحتفَظ هنا بأي شيء.
- `actions.jsonl` - السجل المحلي للإجراءات المغيّرة للحالة (مسح ذاكرة Google Wallet
  المؤقتة)، كلٌّ في سطر JSON واحد، محدودًا بآخر 200 مُدخَل. وهو لا يغادر الجهاز أبدًا.

أما التطبيق فيحتفظ بنسخ scratch القصيرة العمر نفسها في ذاكرته المؤقتة الخاصة بدلًا من ذلك،
ويكتب سجل إجراءاته داخل دليل ملفاته الخاص.
