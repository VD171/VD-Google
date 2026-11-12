> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · ไทย · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# โครงสร้างไฟล์

การจัดวางของที่เก็บโค้ด (repository) VD Google เพื่อให้คุณหาทางไปมาในซอร์สโค้ดได้

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

## อะไรอยู่ที่ไหน

- **ตัวอ่าน (readers)** - สิ่งที่ VD Google อ่านและวิธีที่มันอ่าน - อยู่เพียงใน
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (แอป) และ
  `VDGoogle/webui/webroot/scripts.js` (WebUI) ทั้งสองสะท้อนซึ่งกันและกันอย่างเป๊ะ
- **สตริง** คือแหล่งเดียวของป้ายกำกับทุกตัว ใน 21 ภาษา: แอปอยู่ภายใต้
  `res/values*/strings.xml` ส่วน WebUI อยู่ภายใต้ `webroot/lang/` ป้ายกำกับไม่เคยถูกเขียน
  สองครั้ง
- **เอกสาร** อยู่ใน `docs/` มันเป็นแหล่งความจริงของวิกิโครงการ วิกิถูกสร้างขึ้นจากมัน (ดู
  `tools/` และ `.github/scripts/gen_wiki.sh`)
- **เครื่องมือที่มาพร้อมในตัว** (`sqlite3`) อยู่ใน `assets/bin/` สำหรับแอป และ `bin/` สำหรับ
  โมดูล และมีบันทึกไว้ใน [ไบนารีที่มาพร้อมในตัว](BINARIES.md)

## ผลลัพธ์ของการบิลด์

การบิลด์แบบ release สร้าง APK และ WebUI ZIP อาร์ติแฟกต์ของการบิลด์จะไม่ถูกคอมมิต ดู readme
สำหรับวิธีการบิลด์

## สถานะขณะรันไทม์

บนอุปกรณ์ โมดูล WebUI เก็บสถานะของมันไว้ภายใต้ `/data/adb/vdgoogle/`:

- `scratch/` - สำเนาของฐานข้อมูลที่มีอายุสั้น คัดลอกออกมาแยกไว้เพื่อให้คิวรีไม่แตะต้องไฟล์ที่กำลัง
  ใช้งานจริง การอ่านแต่ละครั้งจะลบสำเนาของตัวเอง และสิ่งตกค้างใด ๆ จากการรันที่ถูกขัดจังหวะจะถูก
  ล้างในครั้งถัดไปที่ WebUI เปิด ไม่มีอะไรที่นี่ถูกเก็บไว้
- `actions.jsonl` - ล็อกในเครื่องของการกระทำที่เปลี่ยนสถานะ (การล้างแคชของ Google Wallet)
  บรรทัดละหนึ่ง JSON จำกัดไว้ที่ 200 รายการล่าสุด มันไม่เคยออกจากอุปกรณ์

ส่วนแอปจะเก็บสำเนา scratch ที่มีอายุสั้นแบบเดียวกันไว้ในแคชส่วนตัวของตัวเองแทน และเขียนล็อก
การกระทำของมันไว้ภายในไดเรกทอรีไฟล์ส่วนตัวของตัวเอง

<!-- doc-footer -->

---

> _เอกสารนี้สร้างขึ้นด้วย Claude (Anthropic) และดูแลโดย VD171 ยินดีรับการแก้ไขและการปรับปรุง ดู [CONTRIBUTING.md](CONTRIBUTING.md)_
