> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · Svenska · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# AI-agenter

VD Google gör Googles egna registreringar på enheten till något som en **AI-agent kan läsa i ett enda steg**. I stället för att öppna flera Google-appar och databaser och gissa deras betydelse läser en agent en enda strukturerad **export** och vet exakt vad enheten rapporterar om RCS, Google Wallet och Play Integrity.

Den här sidan utvecklar idén som presenteras i README.

## Exporten

Både appen och WebUI skapar samma JSON-export:

- **App**: åtgärden **Exportera** delar eller sparar rapporten.
- **WebUI**: åtgärden **Exportera** skriver den till `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Båda har samma schema, eftersom appen och WebUI hålls identiska. Inget skickas någonstans: exporten skrivs på enheten och stannar där.

## Vad den innehåller

Exporten är ett enda JSON-objekt med dessa nycklar på översta nivån:

- `app`, `version`, `exportedAt`, `timezone` - vad som skapade rapporten och när, i enhetens märkta tidszon.
- `device` - tillverkare, modell, Android-version och SDK-nivå.
- `rcs` - tillgänglighet, tillstånd, konfigurationsversion, kompatibla kontakter, operatörsbehörighet, närvaro (UCE) och de relaterade tidsstämplarna.
- `wallet` - betalningsattestering, NFC-hårdvara, tap-to-pay-tillståndet, godkännande av skärmlås och om ett kort någonsin har lagts till.
- `diagnostics` - en självtestsektion, byggd för felrapporter.

Exporten innehåller **inga personuppgifter**: inget telefonnummer, inga autentiseringsuppgifter, inget meddelandeinnehåll, inget kontaktnamn. Varje värde är den ärliga avläsningen - ett verkligt nej förblir nej, en inaktuell cache märks som cachad, ett saknat värde är Okänt.

## Skrivskyddad, med en loggad åtgärd

VD Google läser. Det enda undantaget är en saneringsåtgärd (att rensa Google Wallets cachade attesteringsresultat för att tvinga fram en ny kontroll). Varje åtgärd som ändrar tillstånd ber om en bekräftelse på enheten och skrivs till en lokal JSON-åtgärdslogg. En agent som styr WebUI ser samma bekräftelse och samma logg, det finns ingen dold, ologgad väg.

## Inget nätverkskontrakt

VD Google exponerar inget nätverks-API och ingen socket: den deklarerar inga behörigheter alls. En agent använder **exportfilen**, den anropar ingen tjänst. Det är integritetsgarantin, bevarad även vid automatisering.

<!-- doc-footer -->

---

> _Denna dokumentation genererades med Claude (Anthropic) och underhålls av VD171. Rättelser och förbättringar välkomnas, se [CONTRIBUTING.md](CONTRIBUTING.md)._
