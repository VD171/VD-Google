> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · Čeština · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# AI agenti

VD Google promění vlastní záznamy Googlu v zařízení v něco, co **agent s AI přečte v jediném kroku**. Místo otevírání několika aplikací a databází Googlu a hádání jejich významu přečte agent jediný strukturovaný **export** a přesně ví, co zařízení hlásí o RCS, Google Wallet a Play Integrity.

Tato stránka rozvíjí myšlenku představenou v README.

## Export

Jak aplikace, tak WebUI vytvářejí stejný export JSON:

- **Aplikace**: akce **Exportovat** sdílí nebo uloží report.
- **WebUI**: akce **Exportovat** jej zapíše do `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Oba mají stejné schéma, protože aplikace a WebUI jsou udržovány identické. Nic se nikam neodesílá: export se zapíše do zařízení a zůstane v něm.

## Co obsahuje

Export je jediný objekt JSON s těmito klíči nejvyšší úrovně:

- `app`, `version`, `exportedAt`, `timezone` - co report vytvořilo a kdy, v označeném časovém pásmu zařízení.
- `device` - výrobce, model, verze Androidu a úroveň SDK.
- `rcs` - dostupnost, stav, verze konfigurace, kompatibilní kontakty, oprávnění operátora, přítomnost (UCE) a související časové značky.
- `wallet` - atestace plateb, hardware NFC, stav bezkontaktní platby, přijetí zámku obrazovky a zda byla někdy přidána karta.
- `diagnostics` - sekce vlastní kontroly, určená pro hlášení chyb.

Export neobsahuje **žádné osobní údaje**: žádné telefonní číslo, žádné přihlašovací údaje, žádný obsah zpráv, žádné jméno kontaktu. Každá hodnota je poctivé čtení - skutečné ne zůstává ne, zastaralá mezipaměť je označena jako z mezipaměti, chybějící hodnota je Neznámá.

## Jen pro čtení, s jednou zaznamenanou akcí

VD Google čte. Jedinou výjimkou je sanitizační akce (vymazání výsledku atestace uloženého v mezipaměti Google Wallet, aby se vynutila nová kontrola). Každá akce měnící stav vyžaduje potvrzení v zařízení a zapisuje se do místního protokolu akcí ve formátu JSON. Agent ovládající WebUI vidí stejné potvrzení a stejný protokol, neexistuje žádná skrytá ani nezaznamenaná cesta.

## Žádný síťový kontrakt

VD Google nevystavuje žádné síťové API ani socket: nedeklaruje vůbec žádná oprávnění. Agent konzumuje **soubor exportu**, nevolá žádnou službu. To je záruka soukromí, zachovaná i při automatizaci.

<!-- doc-footer -->

---

> _Tato dokumentace byla vytvořena pomocí Claude (Anthropic) a spravuje ji VD171. Opravy a vylepšení jsou vítány, viz [CONTRIBUTING.md](CONTRIBUTING.md)._
