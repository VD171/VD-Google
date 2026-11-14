> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · Deutsch · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Architektur

Wie VD Google aufgebaut ist und warum. Diese Seite erklärt den Entwurf und die Grundsätze. Sie
führt nicht die genauen Dateien, Tabellen oder Abfragen auf, die jeder Leser verwendet. Das lebt
allein im Quellcode, so gewollt, damit App und Dokumentation niemals zu einer Anleitung für jemanden
abdriften, der dieselben Werte fälschen möchte.

## Entwurfsziele

1. **Die Wahrheit lesen.** Zeigen, was Googles eigene Apps bereits aufgezeichnet haben, es erklären
   und niemals einen Wert erfinden. Wenn das Gerät keine Antwort bereithält, sage Unbekannt.
2. **Null Netzwerk.** Die App deklariert überhaupt keine Berechtigungen; die WebUI blockiert jede
   entfernte Herkunft. Es wird niemals irgendetwas irgendwohin gesendet.
3. **Ein Verhalten, zwei Gesichter.** Die App und das WebUI-Modul werden absolut identisch gehalten:
   dieselben Signale, derselbe Wortlaut, derselbe exportierte Bericht.
4. **Ehrlich statt schmeichelhaft.** Ein echtes Negativ wird als negativ gezeigt; ein veralteter
   Cache wird als zwischengespeichert gekennzeichnet; ein fehlender Wert ist Unbekannt. Die Erkennung
   wird niemals abgeschwächt, um schöner auszusehen.

## Die zwei Gesichter

- **Die App** ist Kotlin mit Jetpack Compose (Material 3). Sie erhält Root über den libsu
  `RootService`, niemals indem sie direkt `su` aufruft, und sie liest innerhalb dieses
  privilegierten Dienstes.
- **Das WebUI-Modul** ist ein kleiner HTML/JS-Web-Root, der von deinem Root-Manager (KernelSU,
  APatch oder Magisk) bereitgestellt wird. Es führt dieselben Lesevorgänge über die Root-Brücke des
  Managers aus. Eine strikte Content-Security-Policy verbietet jede entfernte Herkunft, sodass die
  Seite schon im Prinzip nicht nach Hause telefonieren kann.

Beide werden aus einem einzigen gemeinsamen Satz von Zeichenketten in 21 Sprachen erzeugt, sodass
eine Beschriftung niemals zweimal übersetzt wird.

## Datenbanken lesen: das mitgelieferte sqlite3

Ein Teil des Zustands, den VD Google liest, lebt in SQLite-Datenbanken, die zu Googles Apps gehören.
Android liefert sein eigenes SQLite mit, aber das Framework-SQLite ist unzuverlässig, wenn es die
beiseitegelegte Kopie der Datenbank einer anderen App öffnen soll: Auf manchen Hersteller-ROMs
scheitert es bei *jeder* Datenbank und gibt nichts zurück. Um auf jedem Gerät auf dieselbe Weise zu
lesen, liefert VD Google seine eigene `sqlite3`-Binärdatei mit (arm64 und arm32) und führt Abfragen
als Root darüber aus. Die App und die WebUI verwenden dieselbe Binärdatei, sodass sie stets dieselbe
Engine und dasselbe Ergebnis lesen. Die Herkunft und die Prüfsummen dieser Binärdatei sind in
[Mitgelieferte Binärdateien](BINARIES.md) dokumentiert.

Lesevorgänge kopieren die Datenbank beiseite, bevor sie geöffnet wird, berühren niemals die
Live-Datei und kopieren niemals den Index des gemeinsam genutzten Speichers, den eine laufende
Datenbank als fehlerhaft melden kann.

## Das RCS-Signal

Das Schwierigste beim ehrlichen Lesen von RCS ist, dass die offensichtlichen Flags lügen. Das
einfache "Verfügbar"-Flag, die Feature-Flags und der Provisionierungszustand können allesamt negativ
anzeigen auf einer Leitung, auf der RCS einwandfrei funktioniert, weil sie Caches sind oder weil die
Leitung über Googles eigenes Backend (Jibe) statt über das IMS-Netz des Mobilfunkanbieters läuft. VD
Google berechnet ein einziges **Verbindungs**-Urteil aus der echten Chat-Konfiguration und der
Provisionierungs-Engine, das über Android- und Google-Messages-Versionen hinweg korrekt bleibt, und
stellt die lügenden Flags klar als zwischengespeichert gekennzeichnet dar, sodass sie informieren,
ohne in die Irre zu führen. Siehe die [Bedienungsanleitung](USER-GUIDE.md) dafür, was jede Zeile
bedeutet.

## Der Export

Der Export ist ein bereinigter JSON-Schnappschuss. Ein Bereiniger läuft, bevor irgendetwas
geschrieben wird: Er verwirft Schlüssel und Werte, die wie Anmeldedaten aussehen, schwärzt lange
Ziffernfolgen und gibt niemals deine Telefonnummer, Nachrichteninhalte, Kontaktnamen oder rohe
Capability-Blobs aus. Der optionale Diagnoseabschnitt trägt nur Schemanamen, Zählwerte, Größen und
Versionen, und selbst dort wird jeder in einem Namen eingebettete pro-Identität-Fingerabdruck
zusammengefaltet und entfernt. Die App und die WebUI erzeugen dasselbe Schema.

## Was VD Google niemals tut

- Es deklariert niemals eine Netzwerkberechtigung, und die WebUI kontaktiert niemals eine entfernte
  Herkunft.
- Es fälscht, täuscht oder verbirgt niemals einen Wert.
- Es meldet niemals ein Play-Integrity-Urteil, weil dieses Urteil nicht auf dem Gerät liegt.
- Es ändert den Zustand nur durch eine ausdrückliche, bestätigte, lokal protokollierte Aktion.

<!-- doc-footer -->

---

> _Diese Dokumentation wurde mit Claude (Anthropic) erstellt und wird von VD171 gepflegt. Korrekturen und Verbesserungen sind willkommen; siehe [CONTRIBUTING.md](CONTRIBUTING.md)._
