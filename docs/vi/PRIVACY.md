> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · Tiếng Việt · [中文](../zh/PRIVACY.md)

---

# Quyền riêng tư

Quyền riêng tư không phải là một tính năng của VD Google - nó là toàn bộ mục đích. Trang này
nêu chính xác những gì ứng dụng làm và không làm với dữ liệu của bạn, bằng những lời lẽ rõ
ràng mà bạn có thể xác minh.

## Lời hứa

- **Không có quyền internet nào được cấp.** Manifest của ứng dụng không khai báo quyền nào cả
  - không internet, không lưu trữ, không liên hệ, không gì cả. Chẳng có gì để cho phép.
- **Không có gì được lưu, gửi hay truyền** đến bất kỳ tệp hay máy chủ nào ngoài thiết bị của
  bạn.
- Mọi thao tác đọc diễn ra **cục bộ, thông qua root**, và ở lại trên thiết bị.

## Vì sao nó cần root thay vì các quyền

Trạng thái RCS, Wallet và chứng thực mà VD Google giải thích nằm ở những vùng được bảo vệ,
chỉ-dành-cho-hệ-thống của Android mà các ứng dụng thông thường không thể thấy. VD Google đọc
chúng bằng root thay vì bằng cách xin bạn những quyền rộng. Root là lối vào hẹp, có thể kiểm
toán; một đống quyền thời gian chạy sẽ là lối vào rộng, dễ rò rỉ. Ứng dụng chọn lối hẹp.

## Bản xuất đã được làm sạch

Khi bạn xuất một báo cáo, VD Google làm sạch nó trước khi ghi:

- **Không bao giờ bao gồm:** số điện thoại của bạn, bất kỳ thông tin xác thực, cookie hay
  token nào, nội dung tin nhắn, tên liên hệ, hay các khối dữ liệu khả năng (capability blob)
  thô.
- **Biên tập ẩn:** những chuỗi chữ số dài và những giá trị trông giống bí mật được thay bằng
  một chỗ giữ chỗ (placeholder).
- **Phần chẩn đoán** chỉ mang theo cấu trúc (tên lược đồ, số đếm, kích thước, phiên bản). Bất
  kỳ dấu vân tay theo-danh-tính nào tình cờ xuất hiện bên trong một tên đều bị thu gọn đi.

Báo cáo là của bạn. Nó được ghi ở nơi bạn chọn, nó chỉ được chia sẻ nếu bạn chia sẻ nó, và
nó không chứa gì nhận diện bạn.

## Các hành động là tường minh và được ghi nhật ký

VD Google có thể xóa kết quả chứng thực được lưu đệm của Google Wallet để một lần kiểm tra
lại trong tương lai bắt đầu lại từ đầu. Bất kỳ hành động nào thay đổi trạng thái:

1. yêu cầu bạn xác nhận trước, và
2. được ghi vào một nhật ký JSON cục bộ trên thiết bị.

Không có hành động nào từng chạm tới mạng, và không có gì rời khỏi điện thoại của bạn.

## Tự xác minh

Bạn không phải tin điều nào trong số này một cách mù quáng:

- Mã nguồn là công khai: <https://github.com/VD171/VD-Google>.
- Manifest nằm trong mã nguồn; hãy xác nhận rằng nó không khai báo quyền nào.
- Content-Security-Policy của WebUI nằm trong mã nguồn; hãy xác nhận rằng nó chặn mọi nguồn
  gốc từ xa.
- Các tệp nhị phân đi kèm được ghi lại cùng tổng kiểm trong [Tệp nhị phân đi
  kèm](BINARIES.md).
