> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · فارسی · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# باینری‌های همراه: منشأ و حسابرسی

VD Google قول می‌دهد که هیچ‌چیز مبهمی در درون آن عرضه نمی‌شود. به‌صورت root اجرا می‌شود و خصوصی‌ترین وضعیت شما را می‌خواند، پس باید بتوانید دقیقاً ببینید چه چیزی را با خود حمل می‌کند. این صفحه تنها ابزار بومی‌ای را که اپلیکیشن و ماژول همراه می‌برند، منشأ آن، و نحوهٔ راستی‌آزمایی آن را فهرست می‌کند.

## اصل

VD Google دقیقاً یک ابزار بومی را همراه می‌برد: یک فرمان `sqlite3`، به‌همراه مجموعهٔ کوچکی از کتابخانه‌های مشترک که به آن‌ها نیاز دارد. این لازم است چون SQLite چارچوبِ خودِ اندروید روی برخی ROMهای سازندگان در باز کردن پایگاه‌های دادهٔ اپلیکیشن‌های دیگر شکست می‌خورد، که باعث می‌شود VD Google روی آن دستگاه‌ها چیزی نخواند. برای دلیل آن به [معماری](ARCHITECTURE.md) مراجعه کنید.

- **از بالادست گرفته می‌شود، هرگز از یک دستگاه.** `sqlite3` و کتابخانه‌های آن از مخزن بستهٔ رسمیِ **Termux** (`packages.termux.dev/apt`)، یعنی بسته‌های `.deb` امضاشده، می‌آیند، و هرگز از یک Termux نصب‌شده روی تلفنی کپی نمی‌شوند.
- **همان بایت‌ها در همه‌جا.** فایل‌های یکسان در اپلیکیشن (زیر `assets/bin/`) و در ماژول WebUI (زیر `bin/`) عرضه می‌شوند، بنابراین هر دو با یک موتور می‌خوانند و هم‌رأی‌اند.
- **جمع کنترلی همان هویت است.** یک نسخه در هر مسیری که قرار داشته باشد، تنها در صورتی ابزار واقعی است که SHA-256 آن با مقدار ثبت‌شده مطابقت داشته باشد.
- **یک ردِّ حسابرسیِ ثبت‌شده.** هر پوشهٔ ABI یک `PROVENANCE.json` را با خود دارد که هر بستهٔ منبع (نام، نسخه، URL، SHA-256 مربوط به `.deb`) و SHA-256 هر فایلِ عرضه‌شده را فهرست می‌کند. هیچ‌چیز بدون یک ورودیِ قابل‌بررسی همراه نمی‌شود.

## منشأ

`.github/scripts/build_bin.sh` بار (payload) را در زمان ساخت، بدون هیچ کامپایلر و هیچ NDK، سرِ هم می‌کند. برای هر ABI، این کار را می‌کند:

1. فهرست Termux Packages را می‌خواند و بستهٔ `sqlite` را به‌علاوهٔ وابستگی‌های گذرای آن (`readline`، `ncurses`، `zlib`، `libandroid-support`) resolve می‌کند؛
2. فایل‌های `.deb` رسمیِ امضاشده را دانلود و استخراج می‌کند؛
3. `sqlite3` را کپی می‌کند، سپس تنها فروبستگیِ (closure) کتابخانه‌های مشترکی را که باینری واقعاً به آن نیاز دارد کپی می‌کند (با `readelf` resolve می‌شود)، و از کتابخانه‌هایی که اندروید همیشه خودش فراهم می‌کند صرف‌نظر می‌کند؛
4. `PROVENANCE.json` را می‌نویسد.

باینری‌های Termux به‌صورت پویا به کتابخانه‌های خودِ Termux لینک می‌شوند، پس VD Google آن فروبستگی را در کنار `sqlite3` عرضه می‌کند و آن را با `LD_LIBRARY_PATH` که به پوشهٔ `lib/` همراه اشاره دارد اجرا می‌کند. Termux از sonameهای نسخه‌دار (`libz.so.1`، `libreadline.so.8`، `libncursesw.so.6`) استفاده می‌کند، پس آن‌ها هرگز با کتابخانه‌های خودِ چارچوب اندروید برخورد نمی‌کنند. مقدار `LD_LIBRARY_PATH` تنها برای فرایند `sqlite3` تنظیم می‌شود، هرگز به‌صورت سراسری.

## فایل‌ها

پوستهٔ خط فرمانِ **SQLite** بالادست، بستهٔ Termux **sqlite 3.54.1-1**. یک بار برای هر ABI: باینری `sqlite3` به‌علاوهٔ فروبستگیِ چهار-کتابخانه‌ای آن (`libreadline.so.8`، `libncursesw.so.6`، `libz.so.1`، `libandroid-support.so`). هر فایلِ عرضه‌شده، همراه با SHA-256 و اندازهٔ آن:

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

همان جمع‌های کنترلی در هر `bin/<abi>/PROVENANCE.json` ثبت شده‌اند، که در کنار باینری عرضه می‌شود.

## خودتان راستی‌آزمایی کنید

از یک نسخهٔ گرفته‌شده از کد منبع، هر فایلِ عرضه‌شده را تأیید کنید:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

هر هش باید با فهرست بالا مطابقت کند، و نسخه‌های اپلیکیشن و WebUI باید با یکدیگر مطابقت داشته باشند. برای حسابرسیِ کلِ بار از ابتدا تا انتها، URLهای `.deb` پین‌شده‌ای را که در هر `PROVENANCE.json` فهرست شده‌اند دوباره دریافت کنید، آن‌ها را استخراج کنید، و مقادیر SHA-256 فایل‌ها را مقایسه کنید.

## کاربرد آن

`sqlite3` به‌صورت root، روی نسخه‌ای کنارگذاشته‌شده از یک پایگاه داده، صرفاً برای **خواندن** اجرا می‌شود. هرگز برای نوشتن در هیچ‌یک از پایگاه‌های دادهٔ گوگل به کار نمی‌رود. تنها وظیفهٔ آن این است که به VD Google اجازه دهد همان مقادیر را روی هر دستگاه بخواند. هیچ باینری بومیِ دیگری همراه نمی‌شود.
