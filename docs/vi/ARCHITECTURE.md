> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · Tiếng Việt · [中文](../zh/ARCHITECTURE.md)

---

# Kiến trúc

VD Google được xây dựng như thế nào và vì sao. Trang này giải thích thiết kế và các nguyên
tắc. Nó không liệt kê chính xác những tập tin, bảng hay truy vấn mà mỗi phần đọc sử dụng;
điều đó chỉ tồn tại trong mã nguồn, một cách có chủ đích, để ứng dụng và tài liệu không bao
giờ trôi dạt thành một hướng dẫn cho người muốn giả mạo chính những giá trị đó.

## Mục tiêu thiết kế

1. **Đọc sự thật.** Hiển thị những gì chính các ứng dụng của Google đã ghi lại, giải thích
   nó, và không bao giờ bịa ra một giá trị. Khi thiết bị không giữ câu trả lời, hãy nói
   Không rõ.
2. **Không mạng.** Ứng dụng không khai báo bất kỳ quyền nào; WebUI chặn mọi nguồn gốc từ xa.
   Không có gì được gửi đi đâu cả, bao giờ.
3. **Một hành vi, hai bộ mặt.** Ứng dụng và module WebUI được giữ hoàn toàn giống hệt nhau:
   cùng tín hiệu, cùng cách diễn đạt, cùng báo cáo xuất ra.
4. **Trung thực hơn là tâng bốc.** Một kết quả âm thật sự được hiển thị là âm; một bộ nhớ
   đệm cũ được ghi nhãn là đã lưu đệm; một giá trị thiếu là Không rõ. Việc phát hiện không
   bao giờ bị làm dịu đi để trông đẹp hơn.

## Hai bộ mặt

- **Ứng dụng** được viết bằng Kotlin với Jetpack Compose (Material 3). Nó lấy quyền root qua
  `RootService` của libsu, không bao giờ bằng cách gọi thẳng `su`, và nó đọc bên trong dịch
  vụ đặc quyền đó.
- **Module WebUI** là một web root HTML/JS nhỏ được trình quản lý root của bạn (KernelSU,
  APatch hoặc Magisk) phục vụ. Nó thực hiện cùng những thao tác đọc thông qua cầu nối root
  của trình quản lý. Một Content-Security-Policy nghiêm ngặt cấm mọi nguồn gốc từ xa, nên về
  nguyên tắc trang này không thể gọi về nhà.

Cả hai đều được tạo ra từ một bộ chuỗi dùng chung duy nhất trong 21 ngôn ngữ, nên một nhãn
không bao giờ bị dịch hai lần.

## Đọc cơ sở dữ liệu: sqlite3 đi kèm

Một phần trạng thái mà VD Google đọc nằm trong các cơ sở dữ liệu SQLite thuộc về các ứng
dụng của Google. Android đi kèm SQLite của riêng nó, nhưng SQLite của framework không đáng
tin cậy khi mở bản sao cơ sở dữ liệu của một ứng dụng khác được tách riêng ra: trên một số
ROM của nhà sản xuất, nó thất bại với *mọi* cơ sở dữ liệu, không trả về gì cả. Để đọc theo
cùng một cách trên mọi thiết bị, VD Google đi kèm tệp nhị phân `sqlite3` của riêng mình
(arm64 và arm32) và chạy truy vấn qua nó với quyền root. Ứng dụng và WebUI dùng tệp nhị phân
giống hệt nhau, nên chúng luôn đọc cùng một engine và cùng một kết quả. Nguồn gốc và tổng
kiểm (checksum) của tệp nhị phân đó được ghi lại trong [Tệp nhị phân đi kèm](BINARIES.md).

Các thao tác đọc sao chép cơ sở dữ liệu ra một bên trước khi mở nó, không bao giờ chạm vào
tệp đang hoạt động, và không bao giờ sao chép chỉ mục bộ nhớ chia sẻ, thứ mà một cơ sở dữ
liệu đang chạy có thể báo là bị lỗi.

## Tín hiệu RCS

Phần khó nhất của việc đọc RCS một cách trung thực là những cờ hiển nhiên lại nói dối. Cờ
"có sẵn" đơn giản, các cờ tính năng và trạng thái cấp phép đều có thể đọc ra âm trên một
đường dây mà RCS đang hoạt động hoàn hảo, vì chúng là bộ nhớ đệm hoặc vì đường dây chạy trên
backend của chính Google (Jibe) thay vì mạng IMS của nhà mạng. VD Google tính một phán quyết
**Kết nối** duy nhất từ cấu hình trò chuyện thực và engine cấp phép, nó vẫn đúng qua các
phiên bản Android và Google Messages, và trình bày những cờ nói dối được ghi nhãn rõ ràng là
đã lưu đệm để chúng cung cấp thông tin mà không gây hiểu lầm. Xem [hướng dẫn sử
dụng](USER-GUIDE.md) để biết mỗi dòng có nghĩa gì.

## Phần xuất

Phần xuất là một ảnh chụp JSON đã được làm sạch. Một bộ làm sạch chạy trước khi bất cứ thứ
gì được ghi: nó loại bỏ các khóa và giá trị trông giống thông tin xác thực, biên tập ẩn đi
những chuỗi chữ số dài, và không bao giờ phát ra số điện thoại, nội dung tin nhắn, tên liên
hệ hay các khối dữ liệu khả năng (capability blob) thô của bạn. Phần chẩn đoán tùy chọn chỉ
mang theo tên lược đồ, số đếm, kích thước và phiên bản; và ngay cả ở đó, bất kỳ dấu vân tay
theo-danh-tính nào được nhúng trong một tên đều bị thu gọn đi. Ứng dụng và WebUI tạo ra cùng
một lược đồ.

## Những gì VD Google không bao giờ làm

- Nó không bao giờ khai báo quyền mạng, và WebUI không bao giờ liên lạc với một nguồn gốc từ
  xa.
- Nó không bao giờ bịa đặt, giả mạo hay ẩn giấu một giá trị.
- Nó không bao giờ báo cáo một phán quyết Play Integrity, vì phán quyết đó không nằm trên
  thiết bị.
- Nó chỉ thay đổi trạng thái thông qua một hành động tường minh, đã được xác nhận và được
  ghi nhật ký cục bộ.

<!-- doc-footer -->

---

> _Tài liệu này được tạo bằng Claude (Anthropic) và được duy trì bởi VD171. Chúng tôi hoan nghênh các chỉnh sửa và cải tiến; xem [CONTRIBUTING.md](CONTRIBUTING.md)._
