> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · Deutsch · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# VD Google Bedienungsanleitung

Eine freundliche, vollständige Durchgehung von VD Google: was es anzeigt, was jede Zeile bedeutet und
wie du dein Gerät ehrlich liest. VD Google liest den Zustand von RCS, Google Wallet und die
Play-Integrity-Attestierungen, die Apps anfordern, und erklärt jeden Punkt in klaren Worten. Es
berührt niemals das Internet, und es ändert niemals etwas, es sei denn, du bittest darum.

## Inhaltsverzeichnis

- [1. Was VD Google ist](#1-was-vd-google-ist)
- [2. Was es nicht ist](#2-was-es-nicht-ist)
- [3. Voraussetzungen](#3-voraussetzungen)
- [4. App oder WebUI](#4-app-oder-webui)
- [5. Installation und erster Start](#5-installation-und-erster-start)
- [6. Die Tabs](#6-die-tabs)
  - [6.1 Über](#61-über)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Attestierungen](#65-attestierungen)
- [7. Deinen Bericht exportieren](#7-deinen-bericht-exportieren)
- [8. Aktionen, die den Zustand ändern](#8-aktionen-die-den-zustand-ändern)
- [9. Sprache und Design](#9-sprache-und-design)
- [10. Häufig gestellte Fragen](#10-häufig-gestellte-fragen)

## 1. Was VD Google ist

VD Google ist ein Reader. Es betrachtet, was Googles eigene Apps bereits auf deinem Gerät über drei
Dinge speichern, und erklärt jedes davon, sodass du es tatsächlich verstehst:

- **RCS**: ob moderner Chat auf deiner Leitung wirklich funktioniert, mit wem du chatten kannst und
  ob Gemini in Google Messages bereit ist.
- **Google Wallet**: ob dein Gerät für kontaktlose Zahlungen eingerichtet und attestiert ist.
- **Play-Integrity-Attestierungen**: welche Apps Android gebeten haben zu beweisen, dass dein Gerät
  echt ist, und ob Google es derzeit zertifiziert.

Alles wird lokal gelesen, über Root, und mit einer Erklärung gezeigt. Kein Wert wird erfunden: Wenn
das Gerät keine Antwort bereithält, sagt VD Google "Unbekannt" statt zu raten.

## 2. Was es nicht ist

**VD Google ist kein Integritätsprüfer.** Es führt kein basic / device / strong Urteil aus und
meldet keines. Dieses Ergebnis lebt allein innerhalb des verschlüsselten Tokens, das eine App an
ihren eigenen Server sendet, und wird niemals auf dem Gerät gespeichert, sodass kein ehrlicher Reader
es zeigen kann. Werkzeuge, die behaupten, dein "Play-Integrity-Urteil" lokal anzuzeigen, zeigen dir
eine zwischengespeicherte Vermutung oder eine Anfrage, die sie selbst gestellt haben, nicht die echte
Antwort.

VD Google täuscht, verbirgt oder fälscht auch nichts. Es liest die Wahrheit. Wenn ein Wert dir falsch
erscheint, ist das Ziel dieser Anleitung, zu erklären, warum die Wahrheit so aussieht.

## 3. Voraussetzungen

- **Android 8.0 (API 26) oder neuer.**
- **Root.** Die Daten, die VD Google liest, leben in geschützten, systemeigenen Bereichen von
  Android, die nur mit Root erreichbar sind. Jede moderne Root-Lösung funktioniert: KernelSU,
  KernelSU Next, APatch oder Magisk.
- **Kein Internet.** VD Google deklariert überhaupt keine Berechtigungen, sodass es nichts zu
  erlauben und nichts zu blockieren gibt.

## 4. App oder WebUI

VD Google wird auf zwei Arten geliefert, und sie zeigen genau dieselben Informationen:

- **Die App (APK).** Eine normale Android-App, die du installierst und öffnest. Fragt beim ersten Mal
  nach Root, wie jede Root-App.
- **Das WebUI-Modul (ZIP).** Flashe es in deinem Root-Manager (KernelSU, APatch oder Magisk) und
  öffne es von dort. Nichts wird als App installiert; es läuft innerhalb der WebUI deines
  Root-Managers.

Wähle, was du bevorzugst. Die Bildschirme, der Wortlaut und der exportierte Bericht sind zwischen den
beiden identisch.

## 5. Installation und erster Start

**App:** Installiere die APK und öffne sie. Gewähre beim ersten Mal Root, wenn dein Root-Manager
fragt. Wenn du Root verweigerst oder ablehnst, zeigt VD Google einen Bildschirm, der erklärt, warum
Root erforderlich ist, und bietet an, es erneut zu versuchen.

**WebUI:** Flashe die ZIP als Modul in deinem Root-Manager, dann öffne die WebUI des Moduls aus dem
Manager. Es liest, sobald es sich öffnet.

Beim ersten Öffnen kannst du deine Sprache wählen; du kannst sie später über das Zahnradsymbol oben
ändern.

## 6. Die Tabs

Am unteren Rand befinden sich fünf Tabs: Über, DroidGuard, RCS, Wallet und Attestierungen. Über
öffnet sich zuerst.

### 6.1 Über

Was die App ist, das Datenschutzversprechen, die Version und Links. Lies den Datenschutzabschnitt
einmal: Er ist der ganze Sinn der App.

### 6.2 RCS

Dies ist der umfangreichste Tab. Zeitstempel werden immer in der Zeitzone deines Geräts angezeigt,
mit gekennzeichneter Zone.

- **Verbindung**: die eine Zeile, die die Wahrheit sagt. Sie wird aus der echten Chat-Konfiguration
  und der Provisionierungs-Engine berechnet, sodass sie Verbunden sagt, wenn RCS auf deiner Leitung
  tatsächlich funktioniert, selbst auf neueren Android-Versionen, wo die älteren Flags lügen. **Im
  Zweifel vertraue dieser Zeile.**
- **Mobilfunkanbieter**: der Mobilfunkanbieter für deine Leitung (Name und MCC/MNC), entnommen
  deiner Daten-SIM auf Dual-SIM-Telefonen.
- **Verfügbarkeits-Flag (zwischengespeichert)**: ein zwischengespeichertes Flag, das die
  Messaging-App für deine eigene Nummer führt. Es kann veraltet bleiben, nachdem RCS bereits
  funktioniert, sodass ein "Nein" hier nicht bedeutet, dass RCS aus ist. Vertraue der Verbindungszeile
  oben.
- **Deine RCS-Nummer**: die für RCS registrierte Nummer, standardmäßig maskiert. Tippe auf das Auge,
  um sie aufzudecken.
- **Provisionierungszustand (zwischengespeichert)** und **Konfigurationsversion**: interne Werte,
  die die Messaging-App führt. Sie können 0 anzeigen, selbst während RCS funktioniert. Siehe die
  Anmerkung zu Mobilfunkanbieter gegenüber Googles Backend unten.
- **RCS-fähige Kontakte**: wie viele deiner Kontakte derzeit bekannt dafür sind, RCS-Chat zu
  unterstützen.
- **Feature-Flag (zwischengespeichert)**: ob die Messaging-App das RCS-Feature eingeschaltet hat;
  zwischengespeichert, also kann es hinterherhinken.
- **Carrier Entitlement**, **Presence (UCE)**, **IMS-Dienst vorhanden**: diese beschreiben den
  RCS-Pfad des *Mobilfunkanbieters* (das IMS-Netz des Betreibers). Ein "Nein" oder "0" hier ist
  normal, wenn dein RCS über Googles eigenes Backend statt über deinen Mobilfunkanbieter läuft. Siehe
  unten.
- **Gemini**: ob Gemini in Google Messages bereit ist: **Magic Compose** (Geminis Schreibhilfe),
  **AICore** (die geräteinterne Engine, die Gemini Nano ausführt) und **Gemini-Chat**, der über RCS
  bereitgestellt wird und daher eine verbundene RCS-Verbindung benötigt.
- **Zeitstempel**: Provisionierung, Synchronisierung, letzte Nachricht, Token-Ablauf und mehr.

**Mobilfunkanbieter-RCS gegenüber Googles Backend.** Es gibt zwei Wege, auf denen RCS dein Telefon
erreicht. Einer ist das RCS deines *Mobilfunkanbieters* über dessen IMS-Netz; der andere ist *Googles
eigenes Backend* (Jibe), das obendrauf funktioniert, unabhängig vom Mobilfunkanbieter. Wenn dein RCS
über Googles Backend läuft, wie bei den meisten Menschen, dann sind die mobilfunkanbieterseitigen
Zeilen (Carrier Entitlement, Presence/UCE, IMS-Dienst, Konfigurationsversion) tatsächlich "Nein" oder
"0", und das ist korrekt, kein veralteter Cache. Dein RCS kann auf diese Weise seit Jahren
funktioniert haben. Die Verbindungszeile ist das, was dir sagt, dass es funktioniert.

### 6.3 Wallet

- **Gerät attestiert**: ob Google dieses Gerät als echt und sicher genug beurteilt hat, um
  Zahlungskarten zu halten. Es ist das letzte Ergebnis, das Google auf diesem Gerät gespeichert hat,
  sodass es der Wallet-App hinterherhinken kann, bis Google erneut prüft. Wenn das gespeicherte
  Ergebnis fehlt, zeigt VD Google **Unbekannt** statt eines irreführenden "Nein".
- **Zuletzt geprüft**: wann diese Sicherheitsprüfung zuletzt abgeschlossen wurde.
- **NFC-Hardware**, **Wallet installiert**, **Wallet-Dienst**, **Tap and Pay**, **Bildschirmsperre
  akzeptiert**, **Karte jemals hinzugefügt**: die Teile, die kontaktloses Zahlen möglich machen.

### 6.4 DroidGuard

- **Play Protect zertifiziert**: ob Google Play dein Gerät als zertifiziert erkennt (die
  Zertifizierung, die im Play Store angezeigt wird).
- **Attestierungs-Cache**: die Play-Integrity- und DroidGuard-Attestierungen, die dein Gerät
  zwischengespeichert hat, jeweils mit dem Zeitpunkt, zu dem sie ablaufen.

### 6.5 Attestierungen

Die Historie, welche Apps Play Integrity auf deinem Gerät angefordert haben: Symbol, Name, wann sie
zuletzt gefragt haben und wie oft. Damit kannst du erkennen, ob eine bestimmte App auf Integrität
prüft oder nicht. Verwende das Suchfeld, um nach App-Name oder Paket zu filtern.

## 7. Deinen Bericht exportieren

Das Download-Symbol schreibt einen **JSON**-Bericht mit deinem Geräte-, RCS-, Wallet- und
Attestierungszustand. Du kannst ihn teilen oder als Datei speichern. Der Bericht enthält **keine
Telefonnummer, keine Anmeldedaten und keinen persönlichen Inhalt**: er wird bereinigt, bevor er
geschrieben wird. Er enthält außerdem einen optionalen **Diagnose**-Abschnitt (Schemanamen, Zählwerte
und Versionen, niemals deine Daten), der hilft, gerätespezifische Probleme zu beheben. Die App und die
WebUI schreiben denselben Bericht.

## 8. Aktionen, die den Zustand ändern

VD Google ist größtenteils schreibgeschützt, kann aber eine **bereinigende Aktion** ausführen: das
Löschen des zwischengespeicherten Attestierungsergebnisses von Google Wallet, was eine künftige
Neuprüfung frisch beginnen lässt. Jede Aktion, die den Zustand ändert, bittet dich zuerst um
Bestätigung und wird in ein lokales JSON-Protokoll auf dem Gerät geschrieben. Nichts verlässt dein
Telefon.

## 9. Sprache und Design

VD Google spricht 21 Sprachen. Wähle deine beim ersten Start oder ändere sie jederzeit über das
Zahnradsymbol (auch auf dem Bildschirm bei verweigertem Root verfügbar). Das Helligkeitssymbol
wechselt zwischen hell und dunkel.

## 10. Häufig gestellte Fragen

**RCS sagt Verbunden, aber mehrere Zeilen sagen Nein oder 0. Warum?** Weil dein RCS über Googles
eigenes Backend läuft, nicht über deinen Mobilfunkanbieter. Die mobilfunkanbieterseitigen Zeilen sind
korrekterweise negativ. Siehe [6.2 RCS](#62-rcs).

**Gerät attestiert sagt Unbekannt.** Das zwischengespeicherte Wallet-Ergebnis fehlt auf deinem Gerät.
Das ist ehrlich: VD Google wird kein "Nein" anzeigen, das es nicht belegen kann.

**Wo ist mein Play-Integrity-Urteil?** Es liegt nicht auf deinem Gerät. Siehe [2. Was es nicht
ist](#2-was-es-nicht-ist).

**Funktioniert es ohne Root?** Nein. Die Daten leben in systemeigenen Bereichen.

<!-- doc-footer -->

---

> _Diese Dokumentation wurde mit Claude (Anthropic) erstellt und wird von VD171 gepflegt. Korrekturen und Verbesserungen sind willkommen; siehe [CONTRIBUTING.md](CONTRIBUTING.md)._
