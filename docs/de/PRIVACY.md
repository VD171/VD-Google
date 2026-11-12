> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · Deutsch · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Українська](../uk/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Privatsphäre

Privatsphäre ist kein Feature von VD Google - sie ist der ganze Sinn. Diese Seite erklärt genau, was
die App mit deinen Daten tut und was nicht, in klaren Worten, die du überprüfen kannst.

## Das Versprechen

- **Keine Internetberechtigung wird erteilt.** Das Manifest der App deklariert überhaupt keine
  Berechtigungen - kein Internet, keinen Speicher, keine Kontakte, nichts. Es gibt nichts zu
  erlauben.
- **Nichts wird gespeichert, gesendet oder übertragen** an irgendeine Datei oder einen Server
  außerhalb deines Geräts.
- Jeder Lesevorgang geschieht **lokal, über Root**, und bleibt auf dem Gerät.

## Warum es Root statt Berechtigungen braucht

Der RCS-, Wallet- und Attestierungszustand, den VD Google erklärt, lebt in geschützten,
systemeigenen Bereichen von Android, die normale Apps nicht sehen können. VD Google liest sie mit
Root, statt dich um weitreichende Berechtigungen zu bitten. Root ist der enge, prüfbare Zugang; ein
Haufen Laufzeitberechtigungen wäre der breite, undichte Weg. Die App wählt den engen.

## Der Export ist bereinigt

Wenn du einen Bericht exportierst, bereinigt VD Google ihn, bevor es schreibt:

- **Niemals enthalten:** deine Telefonnummer, irgendwelche Anmeldedaten, Cookies oder Tokens,
  Nachrichteninhalte, Kontaktnamen oder rohe Capability-Blobs.
- **Geschwärzt:** lange Ziffernfolgen und Werte, die wie Geheimnisse aussehen, werden durch einen
  Platzhalter ersetzt.
- **Diagnose** trägt nur Struktur (Schemanamen, Zählwerte, Größen, Versionen). Jeder
  pro-Identität-Fingerabdruck, der zufällig in einem Namen auftaucht, wird zusammengefaltet und
  entfernt.

Der Bericht gehört dir. Er wird dort geschrieben, wo du es wählst, er wird nur geteilt, wenn du ihn
teilst, und er enthält nichts, was dich identifiziert.

## Aktionen sind ausdrücklich und protokolliert

VD Google kann das zwischengespeicherte Attestierungsergebnis von Google Wallet löschen, sodass eine
künftige Neuprüfung frisch beginnt. Jede Aktion, die den Zustand ändert:

1. bittet dich zuerst um Bestätigung, und
2. wird in ein lokales JSON-Protokoll auf dem Gerät geschrieben.

Keine Aktion erreicht jemals das Netzwerk, und nichts verlässt dein Telefon.

## Verifiziere es selbst

Du musst nichts davon auf Vertrauen hinnehmen:

- Der Quellcode ist öffentlich: <https://github.com/VD171/VD-Google>.
- Das Manifest ist im Quellcode; bestätige, dass es keine Berechtigungen deklariert.
- Die Content-Security-Policy der WebUI ist im Quellcode; bestätige, dass sie jede entfernte Herkunft
  blockiert.
- Die mitgelieferten Binärdateien sind mit Prüfsummen in [Mitgelieferte Binärdateien](BINARIES.md)
  dokumentiert.
