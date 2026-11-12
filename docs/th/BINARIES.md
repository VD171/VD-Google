> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · ไทย · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# ไบนารีที่มาพร้อมในตัว: ที่มาและการตรวจสอบ

VD Google สัญญาว่าจะไม่มีสิ่งที่ทึบแสงใด ๆ อยู่ภายในตัวมัน มันทำงานในฐานะ root และอ่าน
สถานะที่เป็นส่วนตัวที่สุดของคุณ ดังนั้นคุณจึงต้องสามารถมองเห็นได้อย่างชัดเจนว่ามันพกพาอะไรมา
หน้านี้แสดงเครื่องมือเนทีฟหนึ่งเดียวที่แอปและโมดูลมาพร้อมในตัว ว่ามันมาจากไหน และจะยืนยันมัน
ได้อย่างไร

## หลักการ

VD Google มาพร้อมกับเครื่องมือเนทีฟเพียงหนึ่งตัวเท่านั้น นั่นคือคำสั่ง `sqlite3` พร้อมกับชุดเล็ก ๆ
ของไลบรารีที่ใช้ร่วมกัน (shared libraries) ที่มันต้องการ มันจำเป็นเพราะ SQLite ของเฟรมเวิร์กใน
Android เองล้มเหลวในการเปิดฐานข้อมูลของแอปอื่นบนรอม ROM ของผู้ผลิตบางราย ซึ่งจะทำให้
VD Google อ่านอะไรไม่ได้เลยบนอุปกรณ์เหล่านั้น ดู[สถาปัตยกรรม](ARCHITECTURE.md) สำหรับเหตุผล

- **ดึงมาจากต้นทาง ไม่เคยมาจากอุปกรณ์** `sqlite3` และไลบรารีของมันมาจากที่เก็บแพ็กเกจ
  **Termux** อย่างเป็นทางการ (`packages.termux.dev/apt`) จากแพ็กเกจ `.deb` ที่เซ็นรับรอง
  แล้ว ไม่เคยคัดลอกออกมาจาก Termux ที่ติดตั้งบนโทรศัพท์เครื่องใด
- **ไบต์เดียวกันทุกที่** ไฟล์ตัวเดียวกันเป๊ะมาพร้อมในแอป (ภายใต้ `assets/bin/`) และในโมดูล
  WebUI (ภายใต้ `bin/`) ดังนั้นทั้งสองจึงอ่านผ่านเอนจินเดียวกันและตรงกัน
- **เช็กซัมคืออัตลักษณ์** ไม่ว่าสำเนาจะอยู่ที่พาธใด มันจะเป็นเครื่องมือตัวจริงก็ต่อเมื่อ SHA-256
  ของมันตรงกับค่าที่บันทึกไว้
- **ร่องรอยการตรวจสอบที่ถูกบันทึกไว้** ไดเรกทอรีของแต่ละ ABI พก `PROVENANCE.json` ที่แสดง
  รายการแพ็กเกจต้นทางแต่ละตัว (ชื่อ เวอร์ชัน URL, SHA-256 ของ `.deb`) และ SHA-256 ของไฟล์
  ที่จัดส่งทุกไฟล์ ไม่มีอะไรมาพร้อมในตัวโดยปราศจากรายการที่คุณตรวจสอบได้

## ที่มา

`.github/scripts/build_bin.sh` ประกอบเพย์โหลด (payload) ขึ้นในเวลาบิลด์ โดยไม่มีคอมไพเลอร์
และไม่มี NDK สำหรับแต่ละ ABI มันจะ:

1. อ่านดัชนี Termux Packages และแก้ไข (resolve) แพ็กเกจ `sqlite` พร้อมกับการพึ่งพาแบบ
   ส่งต่อ (transitive dependencies) ของมัน (`readline`, `ncurses`, `zlib`,
   `libandroid-support`);
2. ดาวน์โหลดและแตกไฟล์ `.deb` ที่เซ็นรับรองอย่างเป็นทางการ;
3. คัดลอก `sqlite3` จากนั้นคัดลอกเฉพาะชุดปิด (closure) ของไลบรารีที่ใช้ร่วมกันที่ไบนารีต้องใช้
   จริง ๆ (แก้ไขด้วย `readelf`) โดยข้ามไลบรารีที่ Android จัดหาให้เองเสมอ;
4. เขียน `PROVENANCE.json`

ไบนารีของ Termux ถูกลิงก์แบบไดนามิกกับไลบรารีของ Termux เอง ดังนั้น VD Google จึงจัดส่ง
ชุดปิดนั้นไปพร้อมกับ `sqlite3` และรันมันด้วย `LD_LIBRARY_PATH` ที่ชี้ไปยังไดเรกทอรี `lib/`
ที่มาพร้อมในตัว Termux ใช้ soname ที่ระบุเวอร์ชัน (`libz.so.1`, `libreadline.so.8`,
`libncursesw.so.6`) ดังนั้นมันจึงไม่ชนกับไลบรารีของเฟรมเวิร์ก Android เองเลย `LD_LIBRARY_PATH`
ถูกตั้งค่าเฉพาะสำหรับโพรเซส `sqlite3` เท่านั้น ไม่เคยตั้งแบบทั่วทั้งระบบ

## ไฟล์ต่าง ๆ

เชลล์บรรทัดคำสั่งของ **SQLite** จากต้นทาง แพ็กเกจ Termux **sqlite 3.54.1-1** หนึ่งเพย์โหลด
ต่อหนึ่ง ABI: ไบนารี `sqlite3` บวกกับชุดปิดสี่ไลบรารีของมัน (`libreadline.so.8`,
`libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`) ไฟล์ที่จัดส่งทุกไฟล์ พร้อม
SHA-256 และขนาดของมัน:

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

เช็กซัมเดียวกันนี้ถูกบันทึกไว้ในแต่ละ `bin/<abi>/PROVENANCE.json` ซึ่งจัดส่งมาข้าง ๆ ไบนารี

## ตรวจสอบด้วยตัวคุณเอง

จากการเช็กเอาต์ซอร์สโค้ด ยืนยันไฟล์ที่จัดส่งทุกไฟล์:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

แต่ละแฮชต้องตรงกับรายการด้านบน และสำเนาของแอปกับ WebUI ต้องตรงกันเอง ในการตรวจสอบเพย์โหลด
ทั้งหมดตั้งแต่ต้นจนจบ ให้ดึง URL ของ `.deb` ที่ปักหมุดไว้ซึ่งแสดงอยู่ในแต่ละ `PROVENANCE.json`
มาใหม่ แตกไฟล์ และเปรียบเทียบค่า SHA-256 ของไฟล์

## มันถูกใช้ทำอะไร

`sqlite3` ถูกรันในฐานะ root กับสำเนาของฐานข้อมูลที่คัดลอกออกมาแยกไว้ เพื่อ **อ่าน** ล้วน ๆ
มันไม่เคยถูกใช้เขียนลงฐานข้อมูลใด ๆ ของ Google หน้าที่เดียวของมันคือให้ VD Google อ่านค่า
เดียวกันบนทุกอุปกรณ์ ไม่มีไบนารีเนทีฟอื่นใดมาพร้อมในตัว
