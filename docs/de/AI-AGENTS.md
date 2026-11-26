> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · Deutsch · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# KI-Agenten

VD Google macht Googles eigene Aufzeichnungen auf dem Gerät zu etwas, das ein **KI-Agent in einem einzigen Schritt lesen kann**. Statt mehrere Google-Apps und Datenbanken zu öffnen und ihre Bedeutung zu erraten, liest ein Agent einen einzigen strukturierten **Export** und weiß genau, was das Gerät über RCS, Google Wallet und Play Integrity meldet.

Diese Seite vertieft die im README vorgestellte Idee.

## Der Export

Sowohl die App als auch die WebUI erzeugen denselben JSON-Export:

- **App**: die Aktion **Exportieren** teilt oder speichert den Bericht.
- **WebUI**: die Aktion **Exportieren** schreibt ihn nach `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Beide haben dasselbe Schema, weil App und WebUI identisch gehalten werden. Es wird nichts irgendwohin gesendet: der Export wird auf dem Gerät geschrieben und bleibt dort.

## Was darin steht

Der Export ist ein einziges JSON-Objekt mit diesen Schlüsseln auf oberster Ebene:

- `app`, `version`, `exportedAt`, `timezone` - was den Bericht erzeugt hat und wann, in der gekennzeichneten Zeitzone des Geräts.
- `device` - Hersteller, Modell, Android-Version und SDK-Level.
- `rcs` - Verfügbarkeit, Status, Konfigurationsversion, fähige Kontakte, Netzbetreiber-Freischaltung, Präsenz (UCE) und die zugehörigen Zeitstempel.
- `wallet` - Zahlungsattestierung, NFC-Hardware, der Tap-to-Pay-Status, Akzeptanz der Bildschirmsperre und ob je eine Karte hinzugefügt wurde.
- `diagnostics` - ein Selbsttestabschnitt, für Fehlerberichte gedacht.

Der Export enthält **keine personenbezogenen Daten**: keine Telefonnummer, keine Zugangsdaten, keinen Nachrichteninhalt, keinen Kontaktnamen. Jeder Wert ist die ehrliche Messung - ein echtes Nein bleibt Nein, ein veralteter Cache wird als zwischengespeichert gekennzeichnet, ein fehlender Wert ist Unbekannt.

## Nur lesend, mit einer protokollierten Aktion

VD Google liest. Die einzige Ausnahme ist eine Bereinigungsaktion (Löschen des zwischengespeicherten Attestierungsergebnisses von Google Wallet, um eine erneute Prüfung zu erzwingen). Jede zustandsändernde Aktion verlangt eine Bestätigung auf dem Gerät und wird in ein lokales JSON-Aktionsprotokoll geschrieben. Ein Agent, der die WebUI steuert, sieht dieselbe Bestätigung und dasselbe Protokoll, es gibt keinen versteckten, nicht protokollierten Weg.

## Kein Netzwerkvertrag

VD Google stellt keine Netzwerk-API und keinen Socket bereit: es deklariert überhaupt keine Berechtigungen. Ein Agent nutzt die **Exportdatei**, er ruft keinen Dienst auf. Das ist die Datenschutzgarantie, selbst bei Automatisierung.

<!-- doc-footer -->

---

> _Diese Dokumentation wurde mit Claude (Anthropic) erstellt und wird von VD171 gepflegt. Korrekturen und Verbesserungen sind willkommen, siehe [CONTRIBUTING.md](CONTRIBUTING.md)._
