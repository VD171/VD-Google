> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../id/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · Svenska · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Medföljande binärer: härkomst och granskning

VD Google lovar att inget ogenomskinligt levereras inuti det. Det körs som root och läser
ditt mest privata tillstånd, så du måste kunna se exakt vad det bär med sig. Den här sidan
listar det enda inbyggda verktyg som appen och modulen levererar, varifrån det kommer och
hur man verifierar det.

## Princip

VD Google levererar exakt ett inbyggt verktyg: ett `sqlite3`-kommando, med den lilla
uppsättning delade bibliotek det behöver. Det krävs eftersom Androids eget ramverks-SQLite
misslyckas med att öppna andra appars databaser på vissa tillverkar-ROM:ar, vilket skulle
få VD Google att inte läsa något på de enheterna. Se [Arkitektur](ARCHITECTURE.md) för
varför.

- **Hämtat från uppström, aldrig från en enhet.** `sqlite3` och dess bibliotek kommer från
  det officiella **Termux**-paketförrådet (`packages.termux.dev/apt`), de signerade
  `.deb`-paketen, aldrig kopierade ur en Termux installerad på någon telefon.
- **Samma byte överallt.** De identiska filerna levereras i appen (under `assets/bin/`)
  och i WebUI-modulen (under `bin/`), så båda läser genom en motor och är överens.
- **Kontrollsumman är identiteten.** Oavsett vilken sökväg en kopia ligger på är den det
  verkliga verktyget endast om dess SHA-256 matchar det registrerade värdet.
- **Ett registrerat granskningsspår.** Varje ABI-katalog bär en `PROVENANCE.json` som
  listar varje källpaket (namn, version, URL, `.deb`-SHA-256) och SHA-256 för varje
  levererad fil. Ingenting levereras utan en post du kan kontrollera.

## Ursprung

`.github/scripts/build_bin.sh` sätter samman nyttolasten vid byggtid, utan kompilator och
utan NDK. För varje ABI:

1. läser den Termux Packages-indexet och löser upp `sqlite`-paketet plus dess transitiva
   beroenden (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. laddar ner och extraherar de officiella signerade `.deb`-filerna;
3. kopierar `sqlite3`, kopierar sedan endast det delade-bibliotek-hölje som binären
   faktiskt behöver (upplöst med `readelf`), och hoppar över de bibliotek som Android alltid
   tillhandahåller själv;
4. skriver `PROVENANCE.json`.

Termux-binärer är dynamiskt länkade mot Termux egna bibliotek, så VD Google levererar det
höljet tillsammans med `sqlite3` och kör det med `LD_LIBRARY_PATH` pekande på den
medföljande `lib/`-katalogen. Termux använder versionerade soname:er (`libz.so.1`,
`libreadline.so.8`, `libncursesw.so.6`), så de krockar aldrig med Android-ramverkets egna
bibliotek. `LD_LIBRARY_PATH` sätts endast för `sqlite3`-processen, aldrig globalt.

## Filerna

Uppströms kommandoradsskal för **SQLite**, Termux-paketet **sqlite 3.54.1-1**. En nyttolast
per ABI: `sqlite3`-binären plus dess fyra-biblioteks-hölje
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Varje
levererad fil, med sin SHA-256 och storlek:

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

`x86`:

```
9b936d8539634249836b7bfb1b51b68f108f94b5cf3b9e02bab8cbb4a222250c  sqlite3  (1637296 bytes)
b1004239a469cb492b683f28f1b59d525cb40715be6bcb51bf86c3351c0b59d8  lib/libandroid-support.so  (19656 bytes)
e3a0c521443635effaf29bbd3387b080e960b86fea2a50c2c49cd74ead637079  lib/libncursesw.so.6  (357980 bytes)
026876da62e303293d2ea3e33f14231964a02a12a93b076938d9589392323b73  lib/libreadline.so.8  (268876 bytes)
1b5e10b598b40dedac5c51636067c1f94f8b83b643912bc5ad2b8d0b15d7552f  lib/libz.so.1  (77892 bytes)
```

`x86_64`:

```
70ef3fff45e735859d139fb893300cdf7d4cf371030c7d2e6fc3dc737de0642c  sqlite3  (1612376 bytes)
f0f44e60090b07b3536729993fad94bb6127ff1c6f70188f5c6dab6a33b85dda  lib/libandroid-support.so  (20640 bytes)
79789fd21d13b82fa87d6373382c815b3d97838962351ab3ec2f7f77edf79c09  lib/libncursesw.so.6  (384896 bytes)
f8b0eb7ef121241c400824c0cc59e1eb2c02ca8901d526d428d41ee20fc40a13  lib/libreadline.so.8  (308584 bytes)
9ac487f65c341194dd285b5cdb5464190552813800d78379953a45023d5b2a34  lib/libz.so.1  (82824 bytes)
```

Samma kontrollsummor är registrerade i varje `bin/<abi>/PROVENANCE.json`, som levereras
bredvid binären.

## Verifiera själv

Från en utcheckning av källkoden, bekräfta varje levererad fil:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Varje hash måste matcha listan ovan, och app- och WebUI-kopiorna måste matcha varandra. För
att granska hela nyttolasten från början till slut, hämta på nytt de fastnålade
`.deb`-URL:erna som listas i varje `PROVENANCE.json`, extrahera dem och jämför filernas
SHA-256-värden.

## Integritet vid flashning

WebUI-modulen kontrollerar sig själv medan den installeras. Varje levererad fil levereras
bredvid en `.sha256`-kompanjon, och `customize.sh` verifierar var och en innan den rör
systemet, om en fil var skadad eller om `.zip`-filen kapades under överföringen avbryts
flashningen med ett tydligt meddelande i stället för att installera en skadad nyttolast.
Kontrollen hoppas säkert över på den sällsynta enhet vars recovery saknar en `sha256sum`
som förstår `-c`.

## Vad den används till

`sqlite3` körs som root, mot en kopia av en databas tagen åt sidan, enbart för att **läsa**.
Den används aldrig för att skriva till någon av Googles databaser. Dess enda uppgift är att
låta VD Google läsa samma värden på varje enhet. Ingen annan inbyggd binär levereras.

<!-- doc-footer -->

---

> _Denna dokumentation genererades med Claude (Anthropic) och underhålls av VD171. Rättelser och förbättringar välkomnas, se [CONTRIBUTING.md](CONTRIBUTING.md)._
