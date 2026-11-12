> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · Tiếng Việt · [中文](../zh/CONTRIBUTING.md)

---

# Đóng góp

Cảm ơn bạn đã quan tâm đến VD Google. Mọi đóng góp đều được hoan nghênh: bản dịch, báo cáo
lỗi, tài liệu và mã nguồn.

## Nguyên tắc nền tảng

- **VD Google đọc sự thật và không bao giờ giả mạo bất cứ điều gì.** Các đóng góp phải giữ
  nguyên điều đó. Một kết quả âm thật sự vẫn là âm; một bộ nhớ đệm cũ được ghi nhãn là đã lưu
  đệm; một giá trị thiếu là Không rõ. Đừng làm dịu một kết quả đọc để nó trông đẹp hơn.
- **Quyền riêng tư là tuyệt đối.** Ứng dụng không khai báo quyền nào và WebUI không liên lạc
  với nguồn gốc từ xa nào. Không bao giờ thêm một quyền, một lệnh gọi mạng, hay bất cứ thứ gì
  gửi dữ liệu ra khỏi thiết bị.
- **Ứng dụng và WebUI là giống hệt nhau.** Bất kỳ thay đổi nào về nội dung hiển thị, hoặc về
  báo cáo xuất ra, đều phải có mặt ở cả hai, với cùng cách diễn đạt. Chúng không bao giờ được
  phép trôi dạt.
- **Nhãn tồn tại một lần duy nhất, trong 21 ngôn ngữ.** Mọi chuỗi hướng tới người dùng đều
  được định nghĩa cho ứng dụng (`res/values*/strings.xml`) và WebUI (`webroot/lang/`). Nếu
  bạn thêm hoặc đổi một nhãn, hãy cập nhật tất cả các ngôn ngữ.

## Bản dịch

Bản dịch là đóng góp dễ nhất và giá trị nhất. Readme được dịch theo từng ngôn ngữ
(`README.<lang>.md`), các chuỗi của ứng dụng và WebUI theo từng ngôn ngữ, và tài liệu này
dưới `docs/<lang>/`. Hãy giữ thuật ngữ nhất quán trong một ngôn ngữ, khớp với giọng điệu hiện
có, và không bao giờ dịch máy một thuật ngữ kỹ thuật mà bạn không chắc chắn.

## Báo cáo lỗi

Mở một issue với kiểu máy thiết bị, phiên bản Android, và - nếu đó là về một kết quả đọc -
một **bản xuất** (nó không chứa dữ liệu cá nhân). Phần chẩn đoán của bản xuất được xây dựng
đúng cho mục đích này.

## Mã nguồn

- Dựng bản release, không bao giờ debug. Kiểm thử trên một thiết bị đã root thật.
- Giữ bình luận ra khỏi mã; tên gọi giải thích ý định. Tiêu đề duy nhất là banner giấy phép ở
  đầu mỗi tệp nguồn.
- Khớp với phong cách hiện có và giữ các thay đổi tập trung.

## Giấy phép

Bằng việc đóng góp, bạn đồng ý rằng đóng góp của bạn được cấp phép theo giấy phép của dự án,
**GNU AGPL-3.0-or-later**.

<!-- doc-footer -->

---

> _Tài liệu này được tạo bằng Claude (Anthropic) và được duy trì bởi VD171. Chúng tôi hoan nghênh các chỉnh sửa và cải tiến; xem [CONTRIBUTING.md](CONTRIBUTING.md)._
