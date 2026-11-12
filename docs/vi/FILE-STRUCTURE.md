> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · [हिन्दी](../hi/FILE-STRUCTURE.md) · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · Tiếng Việt · [中文](../zh/FILE-STRUCTURE.md)

---

# Cấu trúc tệp

Cách kho mã VD Google được bố trí, để bạn có thể tìm đường trong mã nguồn.

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

## Mọi thứ nằm ở đâu

- **Các trình đọc (reader)** - VD Google đọc gì và nó đọc như thế nào - chỉ nằm trong
  `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (ứng dụng) và
  `VDGoogle/webui/webroot/scripts.js` (WebUI). Hai bên phản chiếu nhau chính xác.
- **Các chuỗi** là nguồn duy nhất của mọi nhãn, trong 21 ngôn ngữ: ứng dụng dưới
  `res/values*/strings.xml`, WebUI dưới `webroot/lang/`. Một nhãn không bao giờ được viết hai
  lần.
- **Tài liệu** nằm trong `docs/`. Nó là nguồn chân lý cho wiki của dự án; wiki được tạo ra từ
  nó (xem `tools/` và `.github/scripts/gen_wiki.sh`).
- **Công cụ đi kèm** (`sqlite3`) nằm trong `assets/bin/` cho ứng dụng và `bin/` cho module,
  và được ghi lại trong [Tệp nhị phân đi kèm](BINARIES.md).

## Kết quả dựng

Các bản dựng release tạo ra APK và tệp ZIP WebUI. Các tạo phẩm dựng (build artifact) không
được commit. Xem readme để biết cách dựng.

## Trạng thái thời gian chạy

Trên thiết bị, module WebUI giữ trạng thái của nó dưới `/data/adb/vdgoogle/`:

- `scratch/` - các bản sao tồn tại ngắn ngủi của một cơ sở dữ liệu, được tách riêng để một
  truy vấn không bao giờ chạm vào tệp đang hoạt động. Mỗi lần đọc sẽ xóa các bản sao của chính
  nó, và mọi phần còn sót lại từ một lần chạy bị gián đoạn đều được dọn sạch vào lần tiếp theo
  WebUI được mở. Không có gì ở đây được giữ lại.
- `actions.jsonl` - nhật ký cục bộ của các hành động thay đổi trạng thái (việc xóa bộ nhớ đệm
  của Google Wallet), mỗi hành động một dòng JSON, giới hạn ở 200 mục gần nhất. Nó không bao
  giờ rời khỏi thiết bị.

Thay vào đó, ứng dụng giữ cùng những bản sao scratch tồn tại ngắn ngủi đó trong bộ nhớ đệm
riêng của nó, và ghi nhật ký hành động của nó bên trong thư mục tệp riêng của chính nó.
