> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · Nederlands · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Meegeleverde binaries: herkomst en controle

VD Google belooft dat er niets ondoorzichtigs in meegaat. Ze draait als root en leest je
meest private status, dus je moet precies kunnen zien wat ze bevat. Deze pagina somt de ene
native tool op die de app en de module meeleveren, waar hij vandaan komt, en hoe je hem
verifieert.

## Principe

VD Google levert precies één native tool mee: een `sqlite3`-commando, met de kleine set
gedeelde bibliotheken die het nodig heeft. Het is vereist omdat Androids eigen
framework-SQLite op sommige fabrikant-ROM's faalt om de databases van andere apps te openen,
waardoor VD Google op die toestellen niets zou lezen. Zie [Architectuur](ARCHITECTURE.md)
voor het waarom.

- **Opgehaald van upstream, nooit van een toestel.** `sqlite3` en zijn bibliotheken komen
  uit de officiële **Termux**-pakketrepository (`packages.termux.dev/apt`), de ondertekende
  `.deb`-pakketten, nooit gekopieerd uit een Termux die op een of andere telefoon is
  geïnstalleerd.
- **Overal dezelfde bytes.** De identieke bestanden worden meegeleverd in de app (onder
  `assets/bin/`) en in de WebUI-module (onder `bin/`), zodat beide via één engine lezen en
  het eens zijn.
- **De checksum is de identiteit.** Op welk pad een kopie ook staat, het is alleen de echte
  tool als de SHA-256 ervan overeenkomt met de vastgelegde waarde.
- **Een vastgelegd auditspoor.** Elke ABI-map draagt een `PROVENANCE.json` die elk
  bronpakket opsomt (naam, versie, URL, `.deb` SHA-256) en de SHA-256 van elk meegeleverd
  bestand. Er wordt niets meegeleverd zonder een vermelding die je kunt controleren.

## Herkomst

`.github/scripts/build_bin.sh` stelt de payload samen op build-tijd, zonder compiler en
zonder NDK. Voor elke ABI doet het:

1. de Termux Packages-index lezen en het `sqlite`-pakket plus zijn transitieve afhankelijkheden
   (`readline`, `ncurses`, `zlib`, `libandroid-support`) oplossen;
2. de officiële ondertekende `.deb`-bestanden downloaden en uitpakken;
3. `sqlite3` kopiëren, en daarna alleen de closure van gedeelde bibliotheken kopiëren die de
   binary werkelijk nodig heeft (opgelost met `readelf`), waarbij de bibliotheken die Android
   altijd zelf levert worden overgeslagen;
4. `PROVENANCE.json` schrijven.

Termux-binaries zijn dynamisch gelinkt tegen Termux' eigen bibliotheken, dus VD Google
levert die closure mee naast `sqlite3` en voert hem uit met `LD_LIBRARY_PATH` wijzend naar de
meegeleverde `lib/`-map. Termux gebruikt geversioneerde sonames (`libz.so.1`,
`libreadline.so.8`, `libncursesw.so.6`), zodat ze nooit botsen met de eigen bibliotheken van
het Android-framework. De `LD_LIBRARY_PATH` wordt alleen voor het `sqlite3`-proces ingesteld,
nooit globaal.

## De bestanden

Upstream **SQLite** command-line shell, Termux-pakket **sqlite 3.54.1-1**. Eén payload per
ABI: de `sqlite3`-binary plus zijn vier-bibliotheken-closure (`libreadline.so.8`,
`libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Elk meegeleverd bestand, met zijn
SHA-256 en grootte:

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

Dezelfde checksums zijn vastgelegd in elke `bin/<abi>/PROVENANCE.json`, die naast de binary
wordt meegeleverd.

## Verifieer het zelf

Bevestig vanuit een checkout van de broncode elk meegeleverd bestand:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Elke hash moet overeenkomen met de lijst hierboven, en de app- en WebUI-kopieën moeten met
elkaar overeenkomen. Om de hele payload van begin tot eind te auditen, haal je de vastgezette
`.deb`-URL's die in elke `PROVENANCE.json` staan opnieuw op, pak je ze uit, en vergelijk je de
bestands-SHA-256-waarden.

## Waarvoor het wordt gebruikt

`sqlite3` wordt als root uitgevoerd, tegen een opzij genomen kopie van een database, puur om
te **lezen**. Het wordt nooit gebruikt om naar een van Googles databases te schrijven. De
enige taak ervan is VD Google op elk toestel dezelfde waarden te laten lezen. Er wordt geen
enkele andere native binary meegeleverd.
