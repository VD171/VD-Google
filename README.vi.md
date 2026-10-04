<!--
  VD GOOGLE - RCS, Google Ví và chứng thực Play Integrity, đọc trên thiết bị.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Dịch vụ Google của bạn, đọc trên máy. Không bao giờ dùng internet.**

VD Google cho bạn thấy những gì chính Google ghi lại trên điện thoại của bạn về ba thứ, và giải thích ý nghĩa của từng mục, mà không gửi bất cứ thứ gì đi đâu.

> **Ngôn ngữ:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Những gì ứng dụng hiển thị

### RCS
RCS có khả dụng cho số máy của bạn hay không, số RCS của bạn, trạng thái RCS, phiên bản cấu hình, bao nhiêu liên hệ của bạn hỗ trợ RCS, quyền của nhà mạng, hiện diện (UCE) và các dấu thời gian liên quan (cấp phép, đồng bộ, tin nhắn gần nhất, hết hạn token và hơn thế).

### Google Ví
Thiết bị của bạn có được chứng thực để thanh toán hay không, có phần cứng NFC hay không, Ví có được cài hay không, trạng thái dịch vụ thanh toán và chạm để thanh toán, khóa màn hình của bạn có được chấp nhận hay không, và đã từng thêm thẻ nào chưa.

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

## Liên hệ

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Giấy phép

**GNU AGPL-3.0-or-later** - xem [LICENSE](LICENSE). Copyleft, bao gồm điều khoản mạng: bất kỳ ai chạy phiên bản đã sửa đổi (kể cả dưới dạng dịch vụ) đều phải cung cấp mã nguồn của nó. Được chọn có chủ đích để giữ cho các fork luôn mở.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
