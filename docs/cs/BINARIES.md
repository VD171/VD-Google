> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · Čeština · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Přibalené binárky: původ a auditovatelnost

VD Google slibuje, že uvnitř se nedodává nic neprůhledného. Běží jako root a čte váš
nejsoukromější stav, takže musíte vidět přesně to, co s sebou nese. Tato stránka uvádí
jediný nativní nástroj, který aplikace a modul přibalují, odkud pochází a jak jej ověřit.

## Princip

VD Google přibaluje přesně jeden nativní nástroj: příkaz `sqlite3` spolu s malou sadou
sdílených knihoven, které potřebuje. Je potřeba, protože frameworkové SQLite v Androidu na
některých výrobcovských ROM selhává při otevírání databází jiných aplikací, což by na
těchto zařízeních způsobilo, že by VD Google nepřečetl nic. Proč, viz
[Architektura](ARCHITECTURE.md).

- **Stažen z upstreamu, nikdy ze zařízení.** `sqlite3` a jeho knihovny pocházejí z
  oficiálního repozitáře balíčků **Termux** (`packages.termux.dev/apt`), z podepsaných
  balíčků `.deb`, nikdy zkopírované z nějakého Termuxu nainstalovaného na telefonu.
- **Všude stejné byty.** Identické soubory se dodávají v aplikaci (pod `assets/bin/`) i v
  modulu WebUI (pod `bin/`), takže obě čtou jedním enginem a shodují se.
- **Kontrolní součet je identita.** Ať kopie leží na jakékoli cestě, je to pravý nástroj
  jen tehdy, pokud se její SHA-256 shoduje se zaznamenanou hodnotou.
- **Zaznamenaná auditní stopa.** Každý adresář ABI nese soubor `PROVENANCE.json`, který
  uvádí každý zdrojový balíček (název, verzi, URL, SHA-256 souboru `.deb`) a SHA-256
  každého dodaného souboru. Nic se nepřibaluje bez záznamu, který si můžete ověřit.

## Původ

`.github/scripts/build_bin.sh` sestavuje payload v době sestavení, bez kompilátoru a bez
NDK. Pro každou ABI:

1. čte index Termux Packages a rozřeší balíček `sqlite` plus jeho tranzitivní závislosti
   (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. stáhne a rozbalí oficiální podepsané soubory `.deb`;
3. zkopíruje `sqlite3`, pak zkopíruje jen uzávěr sdílených knihoven, které binárka
   skutečně potřebuje (rozřešený pomocí `readelf`), a přeskočí knihovny, které si Android
   vždy poskytuje sám;
4. zapíše `PROVENANCE.json`.

Binárky z Termuxu jsou dynamicky linkované proti vlastním knihovnám Termuxu, takže VD
Google dodává tento uzávěr vedle `sqlite3` a spouští jej s `LD_LIBRARY_PATH` ukazujícím na
přibalený adresář `lib/`. Termux používá verzované sonames (`libz.so.1`,
`libreadline.so.8`, `libncursesw.so.6`), takže se nikdy nestřetnou s vlastními knihovnami
frameworku Androidu. `LD_LIBRARY_PATH` se nastavuje jen pro proces `sqlite3`, nikdy
globálně.

## Soubory

Shell příkazové řádky upstreamového **SQLite**, balíček Termuxu **sqlite 3.54.1-1**. Jeden
payload na každou ABI: binárka `sqlite3` plus její uzávěr čtyř knihoven
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Každý
dodaný soubor, s jeho SHA-256 a velikostí:

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

Tytéž kontrolní součty jsou zaznamenány v každém `bin/<abi>/PROVENANCE.json`, který se
dodává vedle binárky.

## Ověřte si to sami

Z checkoutu zdrojového kódu ověřte každý dodaný soubor:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Každý hash se musí shodovat s výpisem výše a kopie v aplikaci a ve WebUI se musí shodovat
mezi sebou. Pro audit celého payloadu od začátku do konce znovu stáhněte připnuté URL
souborů `.deb` uvedené v každém `PROVENANCE.json`, rozbalte je a porovnejte hodnoty
SHA-256 souborů.

## K čemu slouží

`sqlite3` se spouští jako root proti kopii databáze vzaté stranou, čistě pro **čtení**.
Nikdy se nepoužívá k zápisu do žádné z databází Google. Jeho jediným úkolem je nechat VD
Google číst stejné hodnoty na každém zařízení. Žádná jiná nativní binárka se nepřibaluje.
