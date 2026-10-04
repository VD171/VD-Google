<!--
  VD GOOGLE - อ่าน RCS, Google Wallet และการรับรอง Play Integrity บนเครื่อง
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**บริการ Google ของคุณ อ่านบนเครื่อง ไม่ใช้อินเทอร์เน็ตเลย**

VD Google แสดงให้คุณเห็นสิ่งที่ Google เองบันทึกไว้ในโทรศัพท์ของคุณเกี่ยวกับสามเรื่อง และอธิบายว่าแต่ละรายการหมายถึงอะไร โดยไม่ส่งอะไรไปที่ใดเลย

> **ภาษา:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## แสดงอะไรบ้าง

### RCS
ว่า RCS พร้อมใช้งานสำหรับเลขหมายของคุณหรือไม่ เลขหมาย RCS ของคุณ สถานะ RCS เวอร์ชันการกำหนดค่า จำนวนรายชื่อที่รองรับ RCS สิทธิ์จากผู้ให้บริการ การแสดงตน (UCE) และการประทับเวลาที่เกี่ยวข้อง (การจัดเตรียม การซิงค์ ข้อความล่าสุด การหมดอายุของโทเค็น และอื่น ๆ)

### Google Wallet
ว่าอุปกรณ์ของคุณได้รับการรับรองสำหรับการชำระเงินหรือไม่ มีฮาร์ดแวร์ NFC หรือไม่ ติดตั้ง Wallet หรือไม่ สถานะของบริการชำระเงินและการแตะเพื่อจ่าย การล็อกหน้าจอของคุณได้รับการยอมรับหรือไม่ และเคยเพิ่มบัตรหรือไม่

### การรับรอง (Play Integrity)
แอปที่เพิ่งขอให้ Google Play Integrity รับรองอุปกรณ์ของคุณ แต่ละแอปพร้อมไอคอน ชื่อ เวลาเต็มของคำขอล่าสุด และจำนวนคำขอที่ทำในช่วงเวลาปัจจุบัน

ทุกรายการมาพร้อมคำอธิบายสั้น ๆ ด้วยภาษาที่เข้าใจง่ายว่ามันหมายถึงอะไรจริง ๆ

## ความเป็นส่วนตัว: ไม่มีอินเทอร์เน็ต

VD Google **ไม่ประกาศสิทธิ์ใด ๆ เลย** แม้แต่การเข้าถึงอินเทอร์เน็ต ไม่มีสิ่งใดที่อ่านออกไปจากอุปกรณ์ของคุณ คุณสามารถตรวจสอบได้ในไฟล์ manifest และในซอร์สโค้ด

## ข้อกำหนด

- Android 8.0 (API 26) ขึ้นไป
- **ต้องมี root** ข้อมูลข้างต้นอยู่ในพื้นที่ที่ได้รับการป้องกันของ Android ซึ่งเข้าถึงได้เฉพาะระบบ VD Google อ่านข้อมูลนี้ด้วยสิทธิ์ผู้ใช้ขั้นสูงและไม่ทำอย่างอื่นกับมัน หากไม่มี root แอปจะแสดงเพียงหน้าจอที่อธิบายว่าทำไมจึงต้องใช้ root

## การประทับเวลา

เวลาทั้งหมดจะแสดงตามเขตเวลาปัจจุบันของอุปกรณ์ของคุณ และจะระบุเขตเวลาเสมอ

## ช่องทางติดต่อ

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## สัญญาอนุญาต

**GNU AGPL-3.0-or-later** - ดู [LICENSE](LICENSE) Copyleft รวมถึงข้อกำหนดเครือข่าย: ผู้ที่รันเวอร์ชันที่ดัดแปลง (แม้เป็นบริการ) ต้องเสนอซอร์สโค้ดของมัน เลือกอย่างตั้งใจเพื่อให้ฟอร์กเปิดอยู่เสมอ

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
