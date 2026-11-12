> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · हिन्दी · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# बंडल की गई बाइनरीज़: उत्पत्ति और ऑडिटिंग

VD Google वादा करता है कि इसके भीतर कुछ भी अपारदर्शी नहीं भेजा जाता। यह root के रूप में चलता है और आपकी सबसे निजी स्थिति पढ़ता है, इसलिए आपको ठीक-ठीक देख पाना चाहिए कि यह क्या साथ लेकर चलता है। यह पृष्ठ उस एकमात्र नेटिव टूल को सूचीबद्ध करता है जिसे ऐप और मॉड्यूल बंडल करते हैं, वह कहाँ से आता है, और उसे कैसे सत्यापित करें।

## सिद्धांत

VD Google ठीक एक नेटिव टूल बंडल करता है: एक `sqlite3` कमांड, उन कुछ साझा लाइब्रेरियों के छोटे समूह के साथ जिनकी उसे ज़रूरत होती है। इसकी ज़रूरत इसलिए है क्योंकि Android का अपना फ़्रेमवर्क SQLite कुछ निर्माता ROMs पर दूसरे ऐप के डेटाबेस खोलने में विफल हो जाता है, जिससे VD Google उन डिवाइसों पर कुछ भी नहीं पढ़ पाता। क्यों, इसके लिए [आर्किटेक्चर](ARCHITECTURE.md) देखें।

- **अपस्ट्रीम से प्राप्त, कभी किसी डिवाइस से नहीं।** `sqlite3` और उसकी लाइब्रेरियाँ आधिकारिक **Termux** पैकेज रिपॉज़िटरी (`packages.termux.dev/apt`) से, हस्ताक्षरित `.deb` पैकेजों से आती हैं, कभी किसी फ़ोन पर इंस्टॉल किए गए Termux से कॉपी नहीं की जातीं।
- **हर जगह वही बाइट्स।** समान फ़ाइलें ऐप में (`assets/bin/` के अंतर्गत) और WebUI मॉड्यूल में (`bin/` के अंतर्गत) भेजी जाती हैं, इसलिए दोनों एक ही इंजन से पढ़ते हैं और सहमत होते हैं।
- **चेकसम ही पहचान है।** कोई कॉपी चाहे किसी भी पथ पर बैठी हो, वह असली टूल तभी है जब उसका SHA-256 दर्ज मान से मेल खाता हो।
- **एक दर्ज किया गया ऑडिट ट्रेल।** हर ABI डायरेक्टरी में एक `PROVENANCE.json` होता है जो हर स्रोत पैकेज (नाम, संस्करण, URL, `.deb` SHA-256) और भेजी गई हर फ़ाइल का SHA-256 सूचीबद्ध करता है। ऐसी किसी भी प्रविष्टि के बिना कुछ भी बंडल नहीं किया जाता जिसे आप जाँच सकें।

## उत्पत्ति

`.github/scripts/build_bin.sh` बिल्ड के समय पेलोड को इकट्ठा करता है, बिना किसी कंपाइलर और बिना किसी NDK के। हर ABI के लिए यह:

1. Termux Packages इंडेक्स पढ़ता है और `sqlite` पैकेज के साथ-साथ उसकी ट्रांज़िटिव निर्भरताओं (`readline`, `ncurses`, `zlib`, `libandroid-support`) को सुलझाता है;
2. आधिकारिक हस्ताक्षरित `.deb` फ़ाइलें डाउनलोड और एक्सट्रैक्ट करता है;
3. `sqlite3` कॉपी करता है, फिर केवल उस साझा-लाइब्रेरी क्लोज़र को कॉपी करता है जिसकी बाइनरी को वास्तव में ज़रूरत होती है (`readelf` से सुलझाया गया), उन लाइब्रेरियों को छोड़ देता है जो Android हमेशा खुद ही प्रदान करता है;
4. `PROVENANCE.json` लिखता है।

Termux बाइनरीज़ Termux की अपनी लाइब्रेरियों के विरुद्ध डायनेमिकली लिंक होती हैं, इसलिए VD Google उस क्लोज़र को `sqlite3` के साथ भेजता है और उसे `LD_LIBRARY_PATH` के साथ चलाता है जो बंडल की गई `lib/` डायरेक्टरी की ओर इंगित करता है। Termux संस्करण-युक्त sonames (`libz.so.1`, `libreadline.so.8`, `libncursesw.so.6`) का उपयोग करता है, इसलिए वे Android फ़्रेमवर्क की अपनी लाइब्रेरियों से कभी नहीं टकरातीं। `LD_LIBRARY_PATH` केवल `sqlite3` प्रक्रिया के लिए सेट किया जाता है, कभी वैश्विक रूप से नहीं।

## फ़ाइलें

अपस्ट्रीम **SQLite** कमांड-लाइन शेल, Termux पैकेज **sqlite 3.54.1-1**। प्रति ABI एक पेलोड: `sqlite3` बाइनरी के साथ-साथ उसका चार-लाइब्रेरी क्लोज़र (`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`)। भेजी गई हर फ़ाइल, उसके SHA-256 और आकार के साथ:

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

वही चेकसम हर `bin/<abi>/PROVENANCE.json` में दर्ज हैं, जो बाइनरी के बगल में भेजा जाता है।

## इसे खुद सत्यापित करें

सोर्स के एक checkout से, भेजी गई हर फ़ाइल की पुष्टि करें:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

हर हैश ऊपर दी गई सूची से मेल खाना चाहिए, और ऐप तथा WebUI की कॉपियाँ एक-दूसरे से मेल खानी चाहिए। पूरे पेलोड को आद्योपांत ऑडिट करने के लिए, हर `PROVENANCE.json` में सूचीबद्ध पिन किए गए `.deb` URLs को फिर से प्राप्त करें, उन्हें एक्सट्रैक्ट करें, और फ़ाइल SHA-256 मानों की तुलना करें।

## इसका उपयोग किसलिए होता है

`sqlite3` को root के रूप में, किसी डेटाबेस की अलग ली गई कॉपी के विरुद्ध, पूरी तरह से **पढ़ने** के लिए चलाया जाता है। इसका उपयोग Google के किसी भी डेटाबेस में लिखने के लिए कभी नहीं किया जाता। इसका एकमात्र काम VD Google को हर डिवाइस पर वही मान पढ़ने देना है। कोई अन्य नेटिव बाइनरी बंडल नहीं की जाती।
