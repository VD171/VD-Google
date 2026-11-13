> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · Deutsch · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Mitwirken

Danke für dein Interesse an VD Google. Beiträge sind willkommen: Übersetzungen, Fehlermeldungen,
Dokumentation und Code.

## Grundregeln

- **VD Google liest die Wahrheit und fälscht niemals etwas.** Beiträge müssen das so bewahren. Ein
  echtes Negativ bleibt negativ; ein veralteter Cache wird als zwischengespeichert gekennzeichnet;
  ein fehlender Wert ist Unbekannt. Schwäche eine Lesung nicht ab, damit sie schöner aussieht.
- **Privatsphäre ist absolut.** Die App deklariert keine Berechtigungen und die WebUI kontaktiert
  keine entfernte Herkunft. Füge niemals eine Berechtigung, einen Netzwerkaufruf oder irgendetwas
  hinzu, das Daten vom Gerät verschickt.
- **Die App und die WebUI sind identisch.** Jede Änderung an dem, was angezeigt wird, oder am
  exportierten Bericht muss in beiden landen, mit demselben Wortlaut. Sie dürfen niemals
  auseinanderdriften.
- **Beschriftungen leben einmal, in 21 Sprachen.** Jede für den Benutzer sichtbare Zeichenkette ist
  für die App (`res/values*/strings.xml`) und die WebUI (`webroot/lang/`) definiert. Wenn du eine
  Beschriftung hinzufügst oder änderst, aktualisiere alle Sprachen.

## Übersetzungen

Übersetzungen sind der einfachste und wertvollste Beitrag. Die Readme wird je Sprache übersetzt
(`README.<lang>.md`), die App- und WebUI-Zeichenketten je Sprache und diese Dokumentation unter
`docs/<lang>/`. Halte die Terminologie innerhalb einer Sprache konsistent, triff den bestehenden Ton
und übersetze niemals maschinell einen technischen Begriff, bei dem du dir unsicher bist.

## Einen Fehler melden

Eröffne ein Issue mit deinem Gerätemodell, deiner Android-Version und, wenn es um eine Lesung geht,
einem **Export** (er enthält keine persönlichen Daten). Der Diagnoseabschnitt des Exports ist genau
dafür gebaut.

## Code

- Baue Release, niemals Debug. Teste auf einem echten, gerooteten Gerät.
- Halte Kommentare aus dem Code heraus; Namen erklären die Absicht. Der einzige Header ist das
  Lizenz-Banner am Anfang jeder Quelldatei.
- Triff den bestehenden Stil und halte Änderungen fokussiert.

## Lizenz

Durch deinen Beitrag stimmst du zu, dass dein Beitrag unter der **GNU AGPL-3.0-or-later** des
Projekts lizenziert ist.

<!-- doc-footer -->

---

> _Diese Dokumentation wurde mit Claude (Anthropic) erstellt und wird von VD171 gepflegt. Korrekturen und Verbesserungen sind willkommen; siehe [CONTRIBUTING.md](CONTRIBUTING.md)._
