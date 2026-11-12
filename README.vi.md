<!--
  VD GOOGLE - RCS, Google Ví và chứng thực Play Integrity, đọc trên thiết bị.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

<p align="center">
  <img src="images/logo-wordmark.png" alt="VD Google" width="320">
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases/latest"><img src="https://img.shields.io/github/v/release/VD171/VD-Google?label=release&color=0969da" alt="Release"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-AGPL--3.0--or--later-1a7f37" alt="License"></a>
  <a href="https://github.com/VD171/VD-Google/releases"><img src="https://img.shields.io/github/downloads/VD171/VD-Google/total?label=downloads&color=8250df" alt="Downloads"></a>
  <a href="https://github.com/VD171/VD-Google/stargazers"><img src="https://img.shields.io/github/stars/VD171/VD-Google?color=d29922" alt="Stars"></a>
</p>

<p align="center">
  <b>Dịch vụ Google của bạn, đọc trên máy. Không bao giờ dùng internet.</b><br>
  VD Google cho bạn thấy những gì chính Google ghi lại trên điện thoại của bạn về RCS, Ví và Play Integrity, và giải thích từng mục, mà không gửi bất cứ thứ gì đi đâu.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/vi-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/vi-USER-GUIDE">Hướng dẫn sử dụng</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/vi-ARCHITECTURE">Kiến trúc</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/vi-PRIVACY">Quyền riêng tư</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/vi-BINARIES">Các tệp nhị phân đi kèm</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/vi-FILE-STRUCTURE">Cấu trúc tệp</a> ·
  <a href="CHANGELOG.md">Changelog</a> ·
  <a href="https://xdaforums.com/t/VD-Google.4803837/">XDA thread</a>
</p>

> **Languages:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Українська](README.uk.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<p align="center">
  <img src="images/vdgoogle-01.png" height="420"/>
  <img src="images/vdgoogle-02.png" height="420"/>
  <img src="images/vdgoogle-03.png" height="420"/>
  <img src="images/vdgoogle-04.png" height="420"/>
  <img src="images/vdgoogle-05.png" height="420"/>
</p>

---

## Ứng dụng hoặc WebUI

Mỗi bản phát hành có hai dạng, và cả hai đều đọc cùng những thông tin trên thiết bị của bạn, không cần internet:

- **App** - tệp `.apk`, một ứng dụng Android thông thường.
- **WebUI** - tệp `.zip`, một mô-đun bạn cài trong trình quản lý root (KernelSU, Magisk hoặc APatch) và mở từ đó.

Dù bằng cách nào, vẫn cần quyền root.

## Những gì ứng dụng hiển thị

### RCS
RCS có khả dụng cho số máy của bạn hay không, số RCS của bạn, trạng thái RCS, phiên bản cấu hình, bao nhiêu liên hệ của bạn hỗ trợ RCS, quyền của nhà mạng, hiện diện (UCE) và các dấu thời gian liên quan (cấp phép, đồng bộ, tin nhắn gần nhất, hết hạn token và hơn thế). Cũng cho biết Gemini trong Google Messages đã sẵn sàng hay chưa: Magic Compose (trợ giúp viết của Gemini), công cụ AI trên thiết bị (AICore, chạy Gemini Nano) và trò chuyện Gemini, được cung cấp qua RCS.

### Google Ví
Thiết bị của bạn có được chứng thực để thanh toán hay không, có phần cứng NFC hay không, Ví có được cài hay không, trạng thái dịch vụ thanh toán và chạm để thanh toán, khóa màn hình của bạn có được chấp nhận hay không, và đã từng thêm thẻ nào chưa.

### DroidGuard
Liệu Google Play có công nhận thiết bị này là đã chứng nhận hay không. Đây là chứng nhận Play Protect hiển thị trong Cửa hàng Play. Các chứng thực mà thiết bị này đã lưu vào bộ nhớ đệm cho Play Integrity và DroidGuard. Thời gian hiển thị là khi mỗi chứng thực hết hạn.

### Chứng thực (Play Integrity)
Các ứng dụng gần đây đã yêu cầu Google Play Integrity bảo đảm cho thiết bị của bạn, mỗi ứng dụng kèm biểu tượng, tên, thời gian đầy đủ của yêu cầu gần nhất và số yêu cầu đã thực hiện trong cửa sổ hiện tại.

Mỗi mục đều kèm một lời giải thích ngắn gọn, bằng ngôn ngữ dễ hiểu, về ý nghĩa thực sự của nó.

## Quyền riêng tư: không internet

VD Google không khai báo **bất kỳ quyền nào**, kể cả quyền truy cập internet. Không điều gì ứng dụng đọc được rời khỏi thiết bị của bạn. Bạn có thể xác nhận điều này trong manifest và trong mã nguồn.

## Yêu cầu

- Android 8.0 (API 26) trở lên.
- **Cần quyền root.** Những thông tin trên nằm trong các vùng được bảo vệ của Android, chỉ hệ thống mới truy cập được. VD Google đọc chúng bằng quyền siêu người dùng và không làm gì khác với chúng. Không có root, ứng dụng chỉ hiển thị một màn hình giải thích vì sao cần root.

## Dấu thời gian

Mọi thời gian được hiển thị theo múi giờ hiện tại của thiết bị, và múi giờ luôn được ghi rõ.

## Tài liệu

- **[Wiki](https://github.com/VD171/VD-Google/wiki/vi-Home)**: toàn bộ tài liệu, xem được trực tuyến.
- **[Hướng dẫn sử dụng](https://github.com/VD171/VD-Google/wiki/vi-USER-GUIDE)**: mỗi màn hình đọc những gì và mỗi kết quả có ý nghĩa gì.
- **[Kiến trúc](https://github.com/VD171/VD-Google/wiki/vi-ARCHITECTURE)** và **[Cấu trúc tệp](https://github.com/VD171/VD-Google/wiki/vi-FILE-STRUCTURE)**: cách hoạt động và mọi thứ nằm ở đâu.
- **[Quyền riêng tư](https://github.com/VD171/VD-Google/wiki/vi-PRIVACY)**: không có quyền internet, mọi lần đọc đều qua root, không gì rời khỏi thiết bị.
- **[Các tệp nhị phân đi kèm](https://github.com/VD171/VD-Google/wiki/vi-BINARIES)**: nguồn gốc và SHA-256 của mọi tệp nhị phân dựng sẵn, để kiểm tra đầy đủ.
- **[Đóng góp](https://github.com/VD171/VD-Google/wiki/vi-CONTRIBUTING)** và **[Bảo mật](https://github.com/VD171/VD-Google/wiki/vi-SECURITY)**.
- **[Nhật ký thay đổi](CHANGELOG.md)** và **[Quy tắc ứng xử](https://github.com/VD171/VD-Google/wiki/vi-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Xem thêm

Các dự án cùng tác giả:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: trình gỡ lỗi phương thức thiết bị, đọc từng mục qua mọi phương thức có thể đọc nó và so sánh chúng cạnh nhau.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: sao lưu và khôi phục toàn bộ ứng dụng và dữ liệu bằng quyền root, điều khiển hoàn toàn từ WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: trình giả mạo thiết bị, biến một fingerprint mới thành một hồ sơ mạch lạc, nhất quán.

## Liên hệ

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Giấy phép

**GNU AGPL-3.0-or-later** - xem [LICENSE](LICENSE). Copyleft, bao gồm điều khoản mạng: bất kỳ ai chạy phiên bản đã sửa đổi (kể cả dưới dạng dịch vụ) đều phải cung cấp mã nguồn của nó. Được chọn có chủ đích để giữ cho các fork luôn mở.

## Thương hiệu

VD Google là một dự án độc lập, không chính thức. Dự án không liên kết, không được chứng thực hay tài trợ bởi Google LLC. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" và các tên cùng logo liên quan là thương hiệu của Google LLC. VD Google chỉ đọc những gì đã có sẵn trên thiết bị của bạn và không gửi bất cứ thứ gì đi đâu.

## Thẻ

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
