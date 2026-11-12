> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · Polski · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Wbudowane pliki binarne: pochodzenie i audyt

VD Google obiecuje, że nie zawiera w sobie niczego nieprzejrzystego. Działa jako root i
czyta twój najbardziej prywatny stan, więc musisz móc dokładnie zobaczyć, co ze sobą
niesie. Ta strona wymienia jedno natywne narzędzie dołączane przez aplikację i moduł,
skąd pochodzi i jak je zweryfikować.

## Zasada

VD Google dołącza dokładnie jedno natywne narzędzie: polecenie `sqlite3` wraz z niewielkim
zestawem bibliotek współdzielonych, których potrzebuje. Jest ono wymagane, ponieważ własny
frameworkowy SQLite Androida zawodzi przy otwieraniu baz danych innych aplikacji na
niektórych ROM-ach producentów, co sprawiłoby, że VD Google nie czytałby niczego na tych
urządzeniach. Zobacz [Architektura](ARCHITECTURE.md), aby poznać powód.

- **Pobierane od źródła, nigdy z urządzenia.** `sqlite3` i jego biblioteki pochodzą z
  oficjalnego repozytorium pakietów **Termux** (`packages.termux.dev/apt`), z podpisanych
  pakietów `.deb`, nigdy nie są kopiowane z Termux zainstalowanego na jakimś telefonie.
- **Te same bajty wszędzie.** Identyczne pliki są dostarczane w aplikacji (pod
  `assets/bin/`) i w module WebUI (pod `bin/`), więc oba czytają tym samym silnikiem i są
  zgodne.
- **Suma kontrolna jest tożsamością.** Niezależnie od ścieżki, w której znajduje się
  kopia, jest ona prawdziwym narzędziem tylko wtedy, gdy jej SHA-256 pasuje do zapisanej
  wartości.
- **Zapisany ślad audytu.** Każdy katalog ABI niesie `PROVENANCE.json` wymieniający każdy
  pakiet źródłowy (nazwa, wersja, URL, SHA-256 pliku `.deb`) oraz SHA-256 każdego
  dostarczonego pliku. Nic nie jest dołączane bez wpisu, który możesz sprawdzić.

## Pochodzenie

`.github/scripts/build_bin.sh` montuje ładunek w czasie kompilacji, bez kompilatora i bez
NDK. Dla każdego ABI:

1. czyta indeks Termux Packages i rozwiązuje pakiet `sqlite` wraz z jego przechodnimi
   zależnościami (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. pobiera i rozpakowuje oficjalne podpisane pliki `.deb`;
3. kopiuje `sqlite3`, a następnie kopiuje tylko domknięcie bibliotek współdzielonych,
   których plik binarny faktycznie potrzebuje (rozwiązane za pomocą `readelf`), pomijając
   biblioteki, które Android zawsze dostarcza sam;
4. zapisuje `PROVENANCE.json`.

Pliki binarne Termux są dynamicznie linkowane z własnymi bibliotekami Termux, więc VD
Google dostarcza to domknięcie obok `sqlite3` i uruchamia je z `LD_LIBRARY_PATH`
wskazującym na dołączony katalog `lib/`. Termux używa wersjonowanych sonames
(`libz.so.1`, `libreadline.so.8`, `libncursesw.so.6`), więc nigdy nie kolidują one z
własnymi bibliotekami frameworka Androida. `LD_LIBRARY_PATH` jest ustawiany wyłącznie dla
procesu `sqlite3`, nigdy globalnie.

## Pliki

Oryginalna powłoka wiersza poleceń **SQLite**, pakiet Termux **sqlite 3.54.1-1**. Jeden
ładunek na ABI: plik binarny `sqlite3` wraz z jego domknięciem czterech bibliotek
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Każdy
dostarczony plik, z jego SHA-256 i rozmiarem:

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

Te same sumy kontrolne są zapisane w każdym `bin/<abi>/PROVENANCE.json`, który jest
dostarczany obok pliku binarnego.

## Zweryfikuj to samodzielnie

Z pobranej kopii źródła potwierdź każdy dostarczony plik:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Każdy hash musi być zgodny z powyższym zestawieniem, a kopie z aplikacji i z WebUI muszą
być zgodne ze sobą nawzajem. Aby zaudytować cały ładunek od początku do końca, pobierz
ponownie przypięte adresy URL plików `.deb` wymienione w każdym `PROVENANCE.json`,
rozpakuj je i porównaj wartości SHA-256 plików.

## Do czego służy

`sqlite3` jest uruchamiany jako root, na kopii bazy danych wziętej na bok, wyłącznie po
to, by **czytać**. Nigdy nie jest używany do zapisu w żadnej z baz danych Google. Jego
jedynym zadaniem jest umożliwienie VD Google czytania tych samych wartości na każdym
urządzeniu. Żaden inny natywny plik binarny nie jest dołączany.
