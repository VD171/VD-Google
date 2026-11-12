> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · Tiếng Việt · [中文](../zh/USER-GUIDE.md)

---

# Hướng dẫn sử dụng VD Google

Một hướng dẫn thân thiện, đầy đủ về VD Google: nó hiển thị gì, mỗi dòng có nghĩa gì, và cách
đọc thiết bị của bạn một cách trung thực. VD Google đọc trạng thái của RCS, Google Wallet và
các chứng thực Play Integrity mà các ứng dụng yêu cầu, và giải thích mỗi mục bằng lời lẽ giản
dị. Nó không bao giờ chạm tới internet, và nó không bao giờ thay đổi bất cứ điều gì trừ khi
bạn yêu cầu.

## Mục lục

- [1. VD Google là gì](#1-vd-google-là-gì)
- [2. VD Google không phải là gì](#2-vd-google-không-phải-là-gì)
- [3. Yêu cầu](#3-yêu-cầu)
- [4. App hay WebUI](#4-app-hay-webui)
- [5. Cài đặt và lần chạy đầu tiên](#5-cài-đặt-và-lần-chạy-đầu-tiên)
- [6. Các tab](#6-các-tab)
  - [6.1 Giới thiệu](#61-giới-thiệu)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Chứng thực](#65-chứng-thực)
- [7. Xuất báo cáo của bạn](#7-xuất-báo-cáo-của-bạn)
- [8. Các hành động thay đổi trạng thái](#8-các-hành-động-thay-đổi-trạng-thái)
- [9. Ngôn ngữ và giao diện](#9-ngôn-ngữ-và-giao-diện)
- [10. Câu hỏi thường gặp](#10-câu-hỏi-thường-gặp)

## 1. VD Google là gì

VD Google là một trình đọc. Nó xem những gì chính các ứng dụng của Google đã lưu trên thiết
bị của bạn về ba thứ, và nó giải thích từng thứ để bạn thực sự hiểu nó:

- **RCS** - liệu trò chuyện hiện đại có thực sự hoạt động trên đường dây của bạn không, bạn
  có thể trò chuyện với ai, và liệu Gemini trong Google Messages đã sẵn sàng chưa.
- **Google Wallet** - liệu thiết bị của bạn đã được thiết lập và chứng thực cho thanh toán
  không tiếp xúc chưa.
- **Các chứng thực Play Integrity** - những ứng dụng nào đã yêu cầu Android chứng minh thiết
  bị của bạn là chính hãng, và liệu Google hiện có chứng nhận điều đó không.

Mọi thứ được đọc cục bộ, thông qua root, và được hiển thị kèm một lời giải thích. Không giá
trị nào bị bịa ra: khi thiết bị không giữ câu trả lời, VD Google nói "Không rõ" thay vì đoán.

## 2. VD Google không phải là gì

**VD Google không phải là một trình kiểm tra tính toàn vẹn (integrity checker).** Nó không
chạy hay báo cáo bất kỳ phán quyết basic / device / strong nào. Kết quả đó chỉ tồn tại bên
trong token được mã hóa mà một ứng dụng gửi tới máy chủ của chính nó, và không bao giờ được
lưu trên thiết bị, nên không trình đọc trung thực nào có thể hiển thị nó. Những công cụ tuyên
bố hiển thị "phán quyết Play Integrity" của bạn một cách cục bộ đang cho bạn thấy một phỏng
đoán được lưu đệm hoặc một yêu cầu do chính chúng tạo ra, không phải câu trả lời thật.

VD Google cũng không giả mạo, ẩn giấu hay bịa đặt bất cứ điều gì. Nó đọc sự thật. Nếu một giá
trị trông sai đối với bạn, mục tiêu của hướng dẫn này là giải thích vì sao sự thật lại trông
như vậy.

## 3. Yêu cầu

- **Android 8.0 (API 26) trở lên.**
- **Root.** Dữ liệu mà VD Google đọc nằm ở những vùng được bảo vệ, chỉ-dành-cho-hệ-thống của
  Android, chỉ có thể tiếp cận bằng root. Mọi giải pháp root hiện đại đều hoạt động: KernelSU,
  KernelSU Next, Magisk hoặc APatch.
- **Không internet.** VD Google không khai báo quyền nào cả, nên chẳng có gì để cho phép và
  chẳng có gì để chặn.

## 4. App hay WebUI

VD Google đến theo hai cách, và chúng hiển thị chính xác cùng một thông tin:

- **Ứng dụng (APK).** Một ứng dụng Android bình thường mà bạn cài và mở. Xin quyền root lần
  đầu, giống như bất kỳ ứng dụng root nào.
- **Module WebUI (ZIP).** Nạp (flash) nó trong trình quản lý root của bạn (KernelSU, Magisk
  hoặc APatch) và mở nó từ đó. Không có gì được cài như một ứng dụng; nó chạy bên trong WebUI
  của trình quản lý root của bạn.

Chọn cái bạn thích hơn. Các màn hình, cách diễn đạt và báo cáo xuất ra là giống hệt nhau giữa
hai bên.

## 5. Cài đặt và lần chạy đầu tiên

**Ứng dụng:** cài APK và mở nó. Lần đầu tiên, cấp quyền root khi trình quản lý root của bạn
hỏi. Nếu bạn từ chối hoặc bỏ qua root, VD Google hiển thị một màn hình giải thích vì sao cần
root và đề nghị thử lại.

**WebUI:** nạp (flash) tệp ZIP như một module trong trình quản lý root của bạn, rồi mở WebUI
của module từ trình quản lý. Nó đọc ngay khi mở.

Ở lần mở đầu tiên bạn có thể chọn ngôn ngữ; bạn có thể đổi nó sau từ biểu tượng bánh răng ở
trên cùng.

## 6. Các tab

Dọc dưới cùng là năm tab: Giới thiệu, DroidGuard, RCS, Wallet và Chứng thực. Giới thiệu mở
trước.

### 6.1 Giới thiệu

Ứng dụng là gì, lời hứa về quyền riêng tư, phiên bản, và các liên kết. Hãy đọc phần quyền
riêng tư một lần: nó là toàn bộ mục đích của ứng dụng.

### 6.2 RCS

Đây là tab phong phú nhất. Dấu thời gian luôn được hiển thị theo múi giờ của thiết bị bạn, có
ghi nhãn múi giờ.

- **Kết nối** - dòng duy nhất nói lên sự thật. Nó được tính từ cấu hình trò chuyện thực và
  engine cấp phép, nên nó nói Đã kết nối khi RCS thực sự đang hoạt động trên đường dây của
  bạn, ngay cả trên Android mới hơn nơi các cờ cũ nói dối. **Khi còn nghi ngờ, hãy tin dòng
  này.**
- **Nhà mạng** - nhà mạng di động cho đường dây của bạn (tên và MCC/MNC), lấy từ SIM dữ liệu
  của bạn trên điện thoại hai SIM.
- **Cờ khả dụng (đã lưu đệm)** - một cờ được lưu đệm mà ứng dụng nhắn tin giữ cho chính số
  của bạn. Nó có thể vẫn lỗi thời sau khi RCS đã hoạt động, nên một "Không" ở đây không có
  nghĩa là RCS đã tắt. Hãy tin dòng Kết nối ở trên.
- **Số RCS của bạn** - số được đăng ký cho RCS, mặc định được che. Chạm vào biểu tượng con
  mắt để lộ nó.
- **Trạng thái cấp phép (đã lưu đệm)** và **Phiên bản cấu hình** - những giá trị nội bộ mà
  ứng dụng nhắn tin giữ. Chúng có thể đọc ra 0 ngay cả khi RCS hoạt động. Xem ghi chú về
  nhà mạng và backend của Google bên dưới.
- **Liên hệ hỗ trợ RCS** - hiện có bao nhiêu liên hệ của bạn được biết là hỗ trợ trò chuyện
  RCS.
- **Cờ tính năng (đã lưu đệm)** - liệu ứng dụng nhắn tin có bật tính năng RCS hay không; được
  lưu đệm, nên nó có thể trễ.
- **Quyền lợi nhà mạng (carrier entitlement)**, **Hiện diện (UCE)**, **Dịch vụ IMS có mặt** -
  những thứ này mô tả đường RCS của *nhà mạng* (mạng IMS của nhà khai thác). Một "Không" hay
  "0" ở đây là bình thường khi RCS của bạn chạy qua backend của chính Google thay vì nhà mạng
  của bạn. Xem bên dưới.
- **Gemini** - liệu Gemini trong Google Messages đã sẵn sàng chưa: **Magic Compose** (trợ
  giúp viết của Gemini), **AICore** (engine trên-thiết-bị chạy Gemini Nano), và **Gemini
  chat**, được chuyển qua RCS nên cần RCS đã kết nối.
- **Dấu thời gian** - cấp phép, đồng bộ, tin nhắn cuối, hết hạn token và nhiều hơn nữa.

**RCS nhà mạng với backend của Google.** Có hai cách RCS đến điện thoại của bạn. Một là RCS
của *nhà mạng* qua mạng IMS của họ; cách kia là *backend của chính Google* (Jibe), hoạt động
bên trên (over the top), độc lập với nhà mạng. Nếu RCS của bạn chạy qua backend của Google -
như với hầu hết mọi người - thì các dòng phía-nhà-mạng (quyền lợi nhà mạng, hiện diện/UCE,
dịch vụ IMS, phiên bản cấu hình) thực sự là "Không" hay "0", và điều đó là đúng, không phải
một bộ nhớ đệm cũ. RCS của bạn có thể đã hoạt động theo cách này trong nhiều năm. Dòng Kết
nối là thứ cho bạn biết nó đang hoạt động.

### 6.3 Wallet

- **Thiết bị được chứng thực** - liệu Google có đánh giá thiết bị này là chính hãng và đủ an
  toàn để giữ thẻ thanh toán hay không. Đó là kết quả cuối cùng mà Google đã lưu trên thiết
  bị này, nên nó có thể trễ hơn ứng dụng Wallet cho đến khi Google kiểm tra lại. Nếu kết quả
  đã lưu bị thiếu, VD Google hiển thị **Không rõ** thay vì một "Không" gây hiểu lầm.
- **Lần kiểm tra cuối** - khi nào lần kiểm tra bảo mật đó hoàn tất gần nhất.
- **Phần cứng NFC**, **Wallet đã cài**, **Dịch vụ Wallet**, **Chạm để thanh toán**, **Khóa
  màn hình được chấp nhận**, **Từng thêm thẻ** - những mảnh ghép làm cho thanh toán không
  tiếp xúc trở nên khả thi.

### 6.4 DroidGuard

- **Được Play Protect chứng nhận** - liệu Google Play có nhận diện thiết bị của bạn là đã
  chứng nhận hay không (chứng nhận được hiển thị trong Play Store).
- **Bộ nhớ đệm chứng thực** - các chứng thực Play Integrity và DroidGuard mà thiết bị của bạn
  đã lưu đệm, mỗi cái kèm thời điểm hết hạn.

### 6.5 Chứng thực

Lịch sử những ứng dụng nào đã yêu cầu Play Integrity trên thiết bị của bạn: biểu tượng, tên,
lần hỏi gần nhất, và bao nhiêu lần. Điều này cho phép bạn biết liệu một ứng dụng nhất định có
kiểm tra tính toàn vẹn hay không. Dùng ô tìm kiếm để lọc theo tên ứng dụng hoặc gói
(package).

## 7. Xuất báo cáo của bạn

Biểu tượng tải xuống ghi một báo cáo **JSON** với trạng thái thiết bị, RCS, Wallet và chứng
thực của bạn. Bạn có thể chia sẻ nó hoặc lưu nó thành một tệp. Báo cáo không chứa **số điện
thoại, không thông tin xác thực và không nội dung cá nhân** - nó được làm sạch trước khi được
ghi. Nó cũng bao gồm một phần **chẩn đoán** tùy chọn (tên lược đồ, số đếm và phiên bản, không
bao giờ là dữ liệu của bạn) giúp khắc phục những vấn đề riêng của từng thiết bị. Ứng dụng và
WebUI ghi cùng một báo cáo.

## 8. Các hành động thay đổi trạng thái

VD Google hầu như chỉ-đọc, nhưng nó có thể thực hiện một **hành động làm sạch**: xóa kết quả
chứng thực được lưu đệm của Google Wallet, điều này cho phép một lần kiểm tra lại trong tương
lai bắt đầu lại từ đầu. Mọi hành động thay đổi trạng thái đều yêu cầu bạn xác nhận trước, và
được ghi vào một nhật ký JSON cục bộ trên thiết bị. Không có gì rời khỏi điện thoại của bạn.

## 9. Ngôn ngữ và giao diện

VD Google nói 21 ngôn ngữ. Chọn ngôn ngữ của bạn ở lần chạy đầu, hoặc đổi nó bất cứ lúc nào
từ biểu tượng bánh răng (cũng có sẵn trên màn hình bị-từ-chối-root). Biểu tượng độ sáng
chuyển giữa giao diện sáng và tối.

## 10. Câu hỏi thường gặp

**RCS nói Đã kết nối nhưng vài dòng nói Không hoặc 0. Vì sao?** Vì RCS của bạn chạy qua
backend của chính Google, không phải nhà mạng của bạn. Các dòng phía-nhà-mạng đúng là âm. Xem
[6.2 RCS](#62-rcs).

**Thiết bị được chứng thực nói Không rõ.** Kết quả Wallet được lưu đệm bị thiếu trên thiết bị
của bạn. Điều đó là trung thực - VD Google sẽ không hiển thị một "Không" mà nó không thể
chứng minh.

**Phán quyết Play Integrity của tôi ở đâu?** Nó không nằm trên thiết bị của bạn. Xem [2. VD
Google không phải là gì](#2-vd-google-không-phải-là-gì).

**Nó có hoạt động không cần root không?** Không. Dữ liệu nằm ở những vùng chỉ-dành-cho-hệ-
thống.

<!-- doc-footer -->

---

> _Tài liệu này được tạo bằng Claude (Anthropic) và được duy trì bởi VD171. Chúng tôi hoan nghênh các chỉnh sửa và cải tiến; xem [CONTRIBUTING.md](CONTRIBUTING.md)._
