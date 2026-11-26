> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · Tiếng Việt · [中文](../zh/AI-AGENTS.md)

---

# Tác nhân AI

VD Google biến chính các bản ghi của Google trên thiết bị thành thứ mà một **tác nhân AI có thể đọc trong một bước**. Thay vì mở nhiều ứng dụng và cơ sở dữ liệu của Google rồi đoán ý nghĩa của chúng, một tác nhân đọc một **bản xuất** có cấu trúc duy nhất và biết chính xác thiết bị báo cáo gì về RCS, Google Wallet và Play Integrity.

Trang này mở rộng ý tưởng được giới thiệu trong README.

## Bản xuất

Cả ứng dụng lẫn WebUI đều tạo ra cùng một bản xuất JSON:

- **Ứng dụng**: hành động **Xuất** chia sẻ hoặc lưu báo cáo.
- **WebUI**: hành động **Xuất** ghi nó vào `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Cả hai có cùng một lược đồ, vì ứng dụng và WebUI được giữ giống hệt nhau. Không có gì được gửi đi đâu cả: bản xuất được ghi trên thiết bị và nằm lại ở đó.

## Nó chứa gì

Bản xuất là một đối tượng JSON duy nhất với các khóa cấp cao sau:

- `app`, `version`, `exportedAt`, `timezone` - thứ gì đã tạo báo cáo và khi nào, theo múi giờ được gắn nhãn của thiết bị.
- `device` - nhà sản xuất, kiểu máy, phiên bản Android và cấp SDK.
- `rcs` - tính khả dụng, trạng thái, phiên bản cấu hình, các liên hệ tương thích, quyền của nhà mạng, hiện diện (UCE) và các dấu thời gian liên quan.
- `wallet` - chứng thực thanh toán, phần cứng NFC, trạng thái chạm để thanh toán, việc chấp nhận khóa màn hình và liệu đã từng thêm thẻ hay chưa.
- `diagnostics` - một phần tự kiểm tra, dành cho các báo cáo lỗi.

Bản xuất không chứa **dữ liệu cá nhân nào**: không số điện thoại, không thông tin đăng nhập, không nội dung tin nhắn, không tên liên hệ. Mỗi giá trị là kết quả đọc trung thực - một kết quả không thực sự vẫn là không, một bộ nhớ đệm cũ được gắn nhãn là đã lưu đệm, một giá trị thiếu là Không rõ.

## Chỉ đọc, với một hành động được ghi lại

VD Google đọc. Ngoại lệ duy nhất là một hành động làm sạch (xóa kết quả chứng thực đã lưu đệm của Google Wallet để buộc đánh giá lại). Mọi hành động làm thay đổi trạng thái đều yêu cầu một xác nhận trên thiết bị và được ghi vào một nhật ký hành động cục bộ dạng JSON. Một tác nhân điều khiển WebUI thấy cùng một xác nhận và cùng một nhật ký, không có con đường ẩn hay không được ghi lại nào.

## Không có hợp đồng mạng

VD Google không cung cấp API mạng nào và không có socket: nó không khai báo bất kỳ quyền nào. Một tác nhân dùng **tệp xuất**, nó không gọi một dịch vụ. Đó là sự bảo đảm về quyền riêng tư, được giữ nguyên ngay cả khi tự động hóa.

<!-- doc-footer -->

---

> _Tài liệu này được tạo bằng Claude (Anthropic) và được duy trì bởi VD171. Chúng tôi hoan nghênh các chỉnh sửa và cải tiến, xem [CONTRIBUTING.md](CONTRIBUTING.md)._
