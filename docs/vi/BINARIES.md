> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · Tiếng Việt · [中文](../zh/BINARIES.md)

---

# Tệp nhị phân đi kèm: nguồn gốc và kiểm toán

VD Google cam kết rằng không có gì mờ ám đi kèm bên trong nó. Nó chạy với quyền root và đọc
trạng thái riêng tư nhất của bạn, nên bạn phải có thể thấy chính xác nó mang theo những gì.
Trang này liệt kê một công cụ gốc duy nhất mà ứng dụng và module đi kèm, nó đến từ đâu, và
cách xác minh nó.

## Nguyên tắc

VD Google đi kèm đúng một công cụ gốc: một lệnh `sqlite3`, cùng với bộ nhỏ các thư viện chia
sẻ mà nó cần. Nó cần thiết vì SQLite của chính framework Android thất bại khi mở cơ sở dữ
liệu của ứng dụng khác trên một số ROM của nhà sản xuất, điều này sẽ khiến VD Google không
đọc được gì trên những thiết bị đó. Xem [Kiến trúc](ARCHITECTURE.md) để biết vì sao.

- **Được lấy từ thượng nguồn, không bao giờ từ một thiết bị.** `sqlite3` và các thư viện của
  nó đến từ kho gói **Termux** chính thức (`packages.termux.dev/apt`), từ các gói `.deb` đã
  được ký, không bao giờ được sao chép ra từ một Termux cài trên điện thoại nào đó.
- **Cùng những byte ở mọi nơi.** Những tệp giống hệt nhau đi kèm trong ứng dụng (dưới
  `assets/bin/`) và trong module WebUI (dưới `bin/`), nên cả hai đều đọc qua một engine và
  nhất trí.
- **Tổng kiểm chính là danh tính.** Dù một bản sao nằm ở đường dẫn nào, nó chỉ là công cụ
  thật nếu SHA-256 của nó khớp với giá trị được ghi lại.
- **Một dấu vết kiểm toán được ghi lại.** Mỗi thư mục ABI mang theo một `PROVENANCE.json`
  liệt kê từng gói nguồn (tên, phiên bản, URL, SHA-256 của `.deb`) và SHA-256 của mọi tệp
  được đi kèm. Không có gì được đi kèm mà không có một mục bạn có thể kiểm tra.

## Nguồn gốc

`.github/scripts/build_bin.sh` lắp ráp phần tải (payload) tại thời điểm dựng, không có trình
biên dịch và không có NDK. Với mỗi ABI, nó:

1. đọc chỉ mục Termux Packages và phân giải gói `sqlite` cùng các phụ thuộc bắc cầu của nó
   (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. tải về và giải nén các tệp `.deb` chính thức đã được ký;
3. sao chép `sqlite3`, rồi chỉ sao chép bao đóng (closure) thư viện chia sẻ mà tệp nhị phân
   thực sự cần (được phân giải bằng `readelf`), bỏ qua những thư viện mà Android luôn tự cung
   cấp;
4. ghi `PROVENANCE.json`.

Các tệp nhị phân Termux được liên kết động với các thư viện của chính Termux, nên VD Google
đi kèm bao đóng đó bên cạnh `sqlite3` và chạy nó với `LD_LIBRARY_PATH` trỏ tới thư mục `lib/`
đi kèm. Termux dùng các soname có đánh phiên bản (`libz.so.1`, `libreadline.so.8`,
`libncursesw.so.6`), nên chúng không bao giờ xung đột với các thư viện của chính framework
Android. `LD_LIBRARY_PATH` chỉ được đặt cho tiến trình `sqlite3`, không bao giờ trên phạm vi
toàn cục.

## Các tệp

Shell dòng lệnh **SQLite** từ thượng nguồn, gói Termux **sqlite 3.54.1-1**. Một phần tải
(payload) cho mỗi ABI: tệp nhị phân `sqlite3` cùng bao đóng bốn-thư-viện của nó
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Mọi tệp được
đi kèm, cùng SHA-256 và kích thước của nó:

`arm64-v8a`:

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a`:

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

Cùng những tổng kiểm đó được ghi lại trong mỗi `bin/<abi>/PROVENANCE.json`, được đi kèm ngay
cạnh tệp nhị phân.

## Tự xác minh

Từ một bản checkout của mã nguồn, hãy xác nhận mọi tệp được đi kèm:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Mỗi mã băm phải khớp với danh sách ở trên, và các bản sao của ứng dụng và WebUI phải khớp với
nhau. Để kiểm toán toàn bộ phần tải từ đầu đến cuối, hãy lấy lại các URL `.deb` đã được ghim
được liệt kê trong mỗi `PROVENANCE.json`, giải nén chúng, và so sánh các giá trị SHA-256 của
tệp.

## Nó được dùng để làm gì

`sqlite3` được chạy với quyền root, trên một bản sao của cơ sở dữ liệu được tách riêng, hoàn
toàn chỉ để **đọc**. Nó không bao giờ được dùng để ghi vào bất kỳ cơ sở dữ liệu nào của
Google. Công việc duy nhất của nó là để VD Google đọc cùng những giá trị trên mọi thiết bị.
Không có tệp nhị phân gốc nào khác được đi kèm.
