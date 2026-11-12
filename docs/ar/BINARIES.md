> **Languages:** [English](../BINARIES.md) · العربية · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# الثنائيات المرفقة: الأصل والتدقيق

يَعِد VD Google بأنه لا يُشحن بداخله شيء غامض. فهو يعمل بصلاحيات الـ root ويقرأ أخصّ
حالاتك خصوصية، لذا يجب أن تكون قادرًا على رؤية ما يحمله بالضبط. تسرد هذه الصفحة الأداة
الأصلية الوحيدة التي يُرفِقها التطبيق والموديول، ومن أين تأتي، وكيف تتحقق منها.

## المبدأ

يُرفِق VD Google أداة أصلية واحدة بالضبط: أمر `sqlite3`، مع المجموعة الصغيرة من المكتبات
المشتركة التي يحتاجها. وهو مطلوب لأن SQLite الخاصة بإطار عمل Android تفشل في فتح قواعد
بيانات التطبيقات الأخرى على بعض أنظمة ROM من الشركات المصنّعة، ما يجعل VD Google لا يقرأ
شيئًا على تلك الأجهزة. راجع [البنية المعمارية](ARCHITECTURE.md) لمعرفة السبب.

- **مجلوبة من المصدر الأصلي، لا من جهاز قط.** تأتي `sqlite3` ومكتباتها من مستودع حزم
  **Termux** الرسمي (`packages.termux.dev/apt`)، من حزم `.deb` الموقّعة، لا منسوخة أبدًا من
  Termux مثبَّت على هاتف ما.
- **البايتات نفسها في كل مكان.** تُشحن الملفات المتطابقة في التطبيق (تحت `assets/bin/`) وفي
  موديول WebUI (تحت `bin/`)، فيقرأ كلاهما بمحرك واحد ويتفقان.
- **المجموع الاختباري هو الهوية.** أيًّا كان المسار الذي تقبع فيه نسخة، فهي الأداة الحقيقية
  فقط إذا طابق SHA-256 الخاص بها القيمة المسجَّلة.
- **أثر تدقيق مسجَّل.** يحمل كل دليل ABI ملف `PROVENANCE.json` يسرد كل حزمة مصدر (الاسم،
  والإصدار، والرابط، وSHA-256 لملف `.deb`) وSHA-256 لكل ملف مشحون. لا يُرفَق شيء دون مُدخَل
  يمكنك التحقق منه.

## الأصل

يُجمِّع `.github/scripts/build_bin.sh` الحمولة (payload) في وقت البناء، دون مُجمِّع
(compiler) ودون NDK. ولكل ABI فإنه:

1. يقرأ فهرس Termux Packages ويحلّ حزمة `sqlite` إضافةً إلى تبعياتها المتعدّية (`readline`،
   و`ncurses`، و`zlib`، و`libandroid-support`)؛
2. ينزّل ملفات `.deb` الرسمية الموقّعة ويستخرجها؛
3. ينسخ `sqlite3`، ثم ينسخ فقط إغلاق المكتبات المشتركة (shared-library closure) الذي تحتاجه
   الثنائية فعلًا (مُحلًّا بـ `readelf`)، متجاوزًا المكتبات التي يوفّرها Android دائمًا
   بنفسه؛
4. يكتب `PROVENANCE.json`.

ثنائيات Termux مرتبطة ديناميكيًا بمكتبات Termux الخاصة، لذا يشحن VD Google ذلك الإغلاق
(closure) إلى جانب `sqlite3` ويشغّله مع `LD_LIBRARY_PATH` مشيرًا إلى دليل `lib/` المرفق.
ويستخدم Termux أسماء sonames مرقّمة الإصدار (`libz.so.1`، و`libreadline.so.8`،
و`libncursesw.so.6`)، فلا تتعارض أبدًا مع مكتبات إطار عمل Android نفسه. ويُضبَط
`LD_LIBRARY_PATH` لعملية `sqlite3` وحدها فقط، لا على نطاق عام أبدًا.

## الملفات

قشرة سطر الأوامر لـ **SQLite** من المصدر الأصلي، حزمة Termux **sqlite 3.54.1-1**. حمولة
واحدة لكل ABI: ثنائية `sqlite3` إضافةً إلى إغلاق مكتباتها الأربع (`libreadline.so.8`،
و`libncursesw.so.6`، و`libz.so.1`، و`libandroid-support.so`). كل ملف مشحون، مع SHA-256
وحجمه:

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

تُسجَّل المجاميع الاختبارية نفسها في كل `bin/<abi>/PROVENANCE.json`، الذي يُشحَن بجوار
الثنائية.

## تحقّق بنفسك

من نسخة مسحوبة من الكود المصدري، تأكّد من كل ملف مشحون:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

يجب أن يطابق كل hash القائمة أعلاه، ويجب أن تتطابق نسخ التطبيق وWebUI فيما بينها. ولتدقيق
الحمولة بأكملها من البداية إلى النهاية، أعد جلب روابط `.deb` المثبّتة المدرجة في كل
`PROVENANCE.json`، واستخرجها، وقارن قيم SHA-256 للملفات.

## فيمَ تُستخدم

تُشغَّل `sqlite3` بصلاحيات الـ root، على نسخة من قاعدة بيانات مأخوذة جانبًا، لغرض
**القراءة** محضًا. ولا تُستخدم أبدًا للكتابة في أي من قواعد بيانات Google. ومهمتها الوحيدة
هي أن تجعل VD Google يقرأ القيم نفسها على كل جهاز. ولا تُرفَق أي ثنائية أصلية أخرى.
