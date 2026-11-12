> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · Svenska · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

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

## Vad den används till

`sqlite3` körs som root, mot en kopia av en databas tagen åt sidan, enbart för att **läsa**.
Den används aldrig för att skriva till någon av Googles databaser. Dess enda uppgift är att
låta VD Google läsa samma värden på varje enhet. Ingen annan inbyggd binär levereras.

<!-- doc-footer -->

---

> _Denna dokumentation genererades med Claude (Anthropic) och underhålls av VD171. Rättelser och förbättringar välkomnas; se [CONTRIBUTING.md](CONTRIBUTING.md)._
