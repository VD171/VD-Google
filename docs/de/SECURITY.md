> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · Deutsch · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Sicherheitsrichtlinie

VD Google ist ein Datenschutzwerkzeug. Es läuft als Root und liest sensiblen Zustand, daher werden
Sicherheitsmeldungen ernst genommen.

## Eine Schwachstelle melden

Bitte melde Sicherheitsprobleme **privat**, nicht in einem öffentlichen Issue:

- E-Mail an **vd.priv8@pm.me**.
- Beschreibe das Problem, die betroffene Version und wie man es reproduziert.
- Wenn es um eine Datenoffenlegung geht, beschreibe die Problemklasse; du musst keinen funktionierenden
  Exploit anhängen.

Du erhältst so schnell wie möglich eine Antwort. Bitte gib ein angemessenes Zeitfenster, um das
Problem zu beheben, bevor eine öffentliche Offenlegung erfolgt.

## Was als Sicherheitsproblem zählt

Wegen dem, was VD Google verspricht, sind diese besonders wichtig:

- Alles, was die App dazu bringen würde, das Netzwerk zu erreichen oder irgendwelche Daten vom Gerät
  zu senden.
- Alles, was persönliche Daten in einen **Export** durchsickern ließe (eine Telefonnummer,
  Anmeldedaten, Nachrichteninhalte, einen Kontaktnamen oder einen pro-Identität-Fingerabdruck).
- Alles, was die eine bereinigende Aktion den Zustand ändern lässt, ohne Bestätigung oder ohne
  protokolliert zu werden.

## Was kein Sicherheitsproblem ist

- Eine Lesung, die "falsch" aussieht, aber die ehrliche Wahrheit ist (zum Beispiel
  mobilfunkanbieterseitige RCS-Zeilen, die Nein anzeigen, während RCS über Googles Backend läuft).
  Siehe die [Bedienungsanleitung](USER-GUIDE.md).
- Das Fehlen eines Play-Integrity-Urteils. Dieses Urteil liegt nicht auf dem Gerät, und VD Google
  wird es nicht erfinden.

## Unterstützte Versionen

Nur die zuletzt veröffentlichte Version wird unterstützt. Bitte reproduziere auf dem neuesten Build,
bevor du meldest.
