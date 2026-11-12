> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · Deutsch · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Mitgelieferte Binärdateien: Herkunft und Prüfung

VD Google verspricht, dass nichts Undurchsichtiges in ihm mitgeliefert wird. Es läuft als Root und
liest deinen privatesten Zustand, daher musst du genau sehen können, was es mit sich führt. Diese
Seite listet das eine native Werkzeug auf, das die App und das Modul mitliefern, woher es stammt und
wie man es verifiziert.

## Grundsatz

VD Google liefert genau ein natives Werkzeug mit: einen `sqlite3`-Befehl, zusammen mit dem kleinen
Satz an gemeinsam genutzten Bibliotheken, die er benötigt. Er wird benötigt, weil Androids eigenes
Framework-SQLite auf manchen Hersteller-ROMs daran scheitert, die Datenbanken anderer Apps zu öffnen,
was VD Google auf diesen Geräten nichts lesen lassen würde. Siehe [Architektur](ARCHITECTURE.md) für
das Warum.

- **Von Upstream bezogen, niemals von einem Gerät.** `sqlite3` und seine Bibliotheken stammen aus dem
  offiziellen **Termux**-Paket-Repository (`packages.termux.dev/apt`), den signierten `.deb`-Paketen,
  niemals aus einem auf irgendeinem Telefon installierten Termux kopiert.
- **Überall dieselben Bytes.** Die identischen Dateien werden in der App (unter `assets/bin/`) und im
  WebUI-Modul (unter `bin/`) mitgeliefert, sodass beide über eine Engine lesen und übereinstimmen.
- **Die Prüfsumme ist die Identität.** An welchem Pfad eine Kopie auch liegt, sie ist nur dann das
  echte Werkzeug, wenn ihr SHA-256 mit dem festgehaltenen Wert übereinstimmt.
- **Eine festgehaltene Prüfspur.** Jedes ABI-Verzeichnis trägt eine `PROVENANCE.json`, die jedes
  Quellpaket (Name, Version, URL, `.deb`-SHA-256) und den SHA-256 jeder mitgelieferten Datei
  auflistet. Nichts wird ohne einen Eintrag mitgeliefert, den du prüfen kannst.

## Herkunft

`.github/scripts/build_bin.sh` setzt die Nutzlast zur Build-Zeit zusammen, ohne Compiler und ohne
NDK. Für jedes ABI tut es Folgendes:

1. es liest den Index der Termux Packages und löst das `sqlite`-Paket samt seinen transitiven
   Abhängigkeiten (`readline`, `ncurses`, `zlib`, `libandroid-support`) auf;
2. es lädt die offiziellen signierten `.deb`-Dateien herunter und entpackt sie;
3. es kopiert `sqlite3`, dann kopiert es nur den Abschluss an gemeinsam genutzten Bibliotheken, den
   die Binärdatei tatsächlich benötigt (aufgelöst mit `readelf`), und lässt die Bibliotheken aus, die
   Android stets selbst bereitstellt;
4. es schreibt `PROVENANCE.json`.

Termux-Binärdateien sind dynamisch gegen Termux' eigene Bibliotheken gelinkt, daher liefert VD Google
diesen Abschluss zusammen mit `sqlite3` mit und führt ihn mit `LD_LIBRARY_PATH` aus, das auf das
mitgelieferte `lib/`-Verzeichnis zeigt. Termux verwendet versionierte sonames (`libz.so.1`,
`libreadline.so.8`, `libncursesw.so.6`), sodass sie niemals mit den eigenen Bibliotheken des
Android-Frameworks kollidieren. Der `LD_LIBRARY_PATH` wird nur für den `sqlite3`-Prozess gesetzt,
niemals global.

## Die Dateien

Upstream-**SQLite**-Kommandozeilen-Shell, Termux-Paket **sqlite 3.54.1-1**. Eine Nutzlast pro ABI:
die `sqlite3`-Binärdatei plus ihr Vier-Bibliotheken-Abschluss (`libreadline.so.8`, `libncursesw.so.6`,
`libz.so.1`, `libandroid-support.so`). Jede mitgelieferte Datei, mit ihrem SHA-256 und ihrer Größe:

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

Dieselben Prüfsummen sind in jeder `bin/<abi>/PROVENANCE.json` festgehalten, die neben der Binärdatei
mitgeliefert wird.

## Verifiziere es selbst

Aus einem Checkout des Quellcodes, bestätige jede mitgelieferte Datei:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Jeder Hash muss mit der obigen Auflistung übereinstimmen, und die Kopien der App und der WebUI müssen
miteinander übereinstimmen. Um die gesamte Nutzlast von Anfang bis Ende zu prüfen, hole die in jeder
`PROVENANCE.json` aufgeführten, fixierten `.deb`-URLs erneut, entpacke sie und vergleiche die
SHA-256-Werte der Dateien.

## Wofür es verwendet wird

`sqlite3` wird als Root gegen eine beiseitegelegte Kopie einer Datenbank ausgeführt, rein zum
**Lesen**. Es wird niemals verwendet, um in irgendeine von Googles Datenbanken zu schreiben. Seine
einzige Aufgabe ist es, VD Google auf jedem Gerät dieselben Werte lesen zu lassen. Keine weitere
native Binärdatei wird mitgeliefert.

<!-- doc-footer -->

---

> _Diese Dokumentation wurde mit Claude (Anthropic) erstellt und wird von VD171 gepflegt. Korrekturen und Verbesserungen sind willkommen; siehe [CONTRIBUTING.md](CONTRIBUTING.md)._
