> **Languages:** [English](../FILE-STRUCTURE.md) · [العربية](../ar/FILE-STRUCTURE.md) · [Čeština](../cs/FILE-STRUCTURE.md) · [Deutsch](../de/FILE-STRUCTURE.md) · [Español](../es/FILE-STRUCTURE.md) · [فارسی](../fa/FILE-STRUCTURE.md) · [Français](../fr/FILE-STRUCTURE.md) · हिन्दी · [Bahasa Indonesia](../in/FILE-STRUCTURE.md) · [Italiano](../it/FILE-STRUCTURE.md) · [日本語](../ja/FILE-STRUCTURE.md) · [한국어](../ko/FILE-STRUCTURE.md) · [Nederlands](../nl/FILE-STRUCTURE.md) · [Polski](../pl/FILE-STRUCTURE.md) · [Português](../pt/FILE-STRUCTURE.md) · [Русский](../ru/FILE-STRUCTURE.md) · [Svenska](../sv/FILE-STRUCTURE.md) · [ไทย](../th/FILE-STRUCTURE.md) · [Türkçe](../tr/FILE-STRUCTURE.md) · [Українська](../uk/FILE-STRUCTURE.md) · [Tiếng Việt](../vi/FILE-STRUCTURE.md) · [中文](../zh/FILE-STRUCTURE.md)

---

# फ़ाइल संरचना

VD Google रिपॉज़िटरी कैसे व्यवस्थित है, ताकि आप सोर्स में अपना रास्ता ढूँढ़ सकें।

```
VD-Google/
├── README.md, README.<lang>.md       # the readme, in 21 languages
├── LICENSE                           # GNU AGPL-3.0-or-later
├── CHANGELOG.md                      # user-facing release notes
├── images/                           # screenshots used by the readme
├── docs/                             # this documentation (source of the wiki)
│   ├── USER-GUIDE.md, ARCHITECTURE.md, PRIVACY.md, BINARIES.md, FILE-STRUCTURE.md
│   └── <lang>/                       # the same docs in each language
├── tools/                            # maintenance scripts (wiki, docs nav)
├── .github/                          # CI and the wiki-sync workflow
│   ├── workflows/
│   └── scripts/gen_wiki.sh
└── VDGoogle/                         # the Gradle project
    ├── app/                          # the Android app
    │   └── src/main/
    │       ├── java/ru/vd171/vdgoogle/
    │       │   ├── data/             # the readers and their data models
    │       │   ├── ui/               # Compose screens and view model
    │       │   ├── root/             # the libsu RootService bridge
    │       │   └── util/             # export, formatting, action log
    │       ├── res/                  # layouts and the 21-language strings
    │       └── assets/bin/           # the bundled sqlite3 (see BINARIES.md)
    └── webui/                        # the WebUI module
        ├── module.prop, customize.sh, update.json
        ├── bin/                      # the bundled sqlite3, per ABI
        └── webroot/                  # the HTML/JS/CSS WebUI
            ├── index.html, scripts.js
            └── lang/                 # the 21-language strings for the WebUI
```

## चीज़ें कहाँ रहती हैं

- **रीडर्स** - VD Google क्या पढ़ता है और कैसे पढ़ता है - केवल `VDGoogle/app/src/main/java/ru/vd171/vdgoogle/data/` (ऐप) और `VDGoogle/webui/webroot/scripts.js` (WebUI) में रहते हैं। दोनों एक-दूसरे की ठीक-ठीक दर्पण-छवि हैं।
- **स्ट्रिंग्स** हर लेबल का एकमात्र स्रोत हैं, 21 भाषाओं में: ऐप `res/values*/strings.xml` के अंतर्गत, WebUI `webroot/lang/` के अंतर्गत। कोई लेबल कभी दो बार नहीं लिखा जाता।
- **दस्तावेज़ीकरण** `docs/` में रहता है। यह परियोजना wiki के लिए सत्य का स्रोत है; wiki इसी से उत्पन्न होती है (`tools/` और `.github/scripts/gen_wiki.sh` देखें)।
- **बंडल किया गया टूल** (`sqlite3`) ऐप के लिए `assets/bin/` में और मॉड्यूल के लिए `bin/` में रहता है, और [बंडल की गई बाइनरीज़](BINARIES.md) में प्रलेखित है।

## बिल्ड आउटपुट

Release बिल्ड APK और WebUI ZIP उत्पन्न करते हैं। बिल्ड आर्टिफ़ैक्ट कमिट नहीं किए जाते। बिल्ड कैसे करें, इसके लिए readme देखें।

## रनटाइम स्थिति

डिवाइस पर, WebUI मॉड्यूल अपनी स्थिति `/data/adb/vdgoogle/` के अंतर्गत रखता है:

- `scratch/` - किसी डेटाबेस की अल्पकालिक कॉपियाँ, जो अलग ले ली जाती हैं ताकि कोई क्वेरी कभी लाइव फ़ाइल को न छुए। हर रीड अपनी कॉपियों को खुद मिटा देती है, और किसी बाधित रन से बची हुई कोई भी चीज़ अगली बार WebUI खुलने पर साफ़ कर दी जाती है। यहाँ कुछ भी नहीं रखा जाता।
- `actions.jsonl` - स्थिति बदलने वाली क्रियाओं (Google Wallet कैश साफ़ करना) का स्थानीय लॉग, हर एक एक JSON लाइन, अंतिम 200 प्रविष्टियों तक सीमित। यह कभी डिवाइस नहीं छोड़ता।

ऐप वही अल्पकालिक scratch कॉपियाँ इसके बजाय अपने निजी कैश में रखता है, और अपना क्रिया लॉग अपनी निजी फ़ाइल्स डायरेक्टरी के भीतर लिखता है।
