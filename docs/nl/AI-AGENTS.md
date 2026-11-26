> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · Nederlands · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# AI-agents

VD Google maakt van Googles eigen registraties op het apparaat iets dat een **AI-agent in één stap kan lezen**. In plaats van meerdere Google-apps en databases te openen en naar hun betekenis te raden, leest een agent één gestructureerde **export** en weet precies wat het apparaat meldt over RCS, Google Wallet en Play Integrity.

Deze pagina werkt het idee uit het README verder uit.

## De export

Zowel de app als de WebUI produceren dezelfde JSON-export:

- **App**: de actie **Exporteren** deelt of bewaart het rapport.
- **WebUI**: de actie **Exporteren** schrijft het naar `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Beide hebben hetzelfde schema, omdat de app en de WebUI identiek worden gehouden. Er wordt niets ergens naartoe gestuurd: de export wordt op het apparaat geschreven en blijft daar.

## Wat erin staat

De export is één JSON-object met deze sleutels op het hoogste niveau:

- `app`, `version`, `exportedAt`, `timezone` - wat het rapport heeft gemaakt en wanneer, in de gelabelde tijdzone van het apparaat.
- `device` - fabrikant, model, Android-versie en SDK-niveau.
- `rcs` - beschikbaarheid, status, configuratieversie, geschikte contacten, provider-toegang, aanwezigheid (UCE) en de bijbehorende tijdstempels.
- `wallet` - betalingsattestatie, NFC-hardware, de tap-to-pay-status, acceptatie van de schermvergrendeling en of er ooit een kaart is toegevoegd.
- `diagnostics` - een zelftestsectie, gemaakt voor bugrapporten.

De export bevat **geen persoonsgegevens**: geen telefoonnummer, geen inloggegevens, geen berichtinhoud, geen contactnaam. Elke waarde is de eerlijke meting - een echt nee blijft nee, een verouderde cache wordt als in cache gelabeld, een ontbrekende waarde is Onbekend.

## Alleen-lezen, met één gelogde actie

VD Google leest. De enige uitzondering is een opschoonactie (het in de cache opgeslagen attestatieresultaat van Google Wallet wissen om een hercontrole af te dwingen). Elke actie die de staat wijzigt vraagt om een bevestiging op het apparaat en wordt naar een lokaal JSON-actielogboek geschreven. Een agent die de WebUI aanstuurt ziet dezelfde bevestiging en hetzelfde logboek, er is geen verborgen, niet-gelogd pad.

## Geen netwerkcontract

VD Google biedt geen netwerk-API en geen socket: het declareert helemaal geen permissies. Een agent gebruikt het **exportbestand**, het roept geen service aan. Dat is de privacygarantie, ook bij automatisering behouden.

<!-- doc-footer -->

---

> _Deze documentatie is gegenereerd met Claude (Anthropic) en wordt onderhouden door VD171. Correcties en verbeteringen zijn welkom, zie [CONTRIBUTING.md](CONTRIBUTING.md)._
