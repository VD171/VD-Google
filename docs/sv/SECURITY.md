> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · Svenska · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Säkerhetspolicy

VD Google är ett integritetsverktyg. Det körs som root och läser känsligt tillstånd, så
säkerhetsrapporter tas på allvar.

## Rapportera en sårbarhet

Rapportera säkerhetsproblem **privat**, inte i ett offentligt issue:

- E-posta **vd.priv8@pm.me**.
- Beskriv problemet, den berörda versionen och hur man reproducerar det.
- Om det gäller dataexponering, beskriv problemklassen; du behöver inte bifoga en
  fungerande exploit.

Du får ett svar så snart som möjligt. Ge ett rimligt tidsfönster för att åtgärda problemet
innan något offentliggörande.

## Vad som räknas som ett säkerhetsproblem

På grund av vad VD Google lovar är dessa särskilt viktiga:

- Allt som skulle få appen att nå nätverket, eller skicka någon data från enheten.
- Allt som skulle läcka personuppgifter in i en **export** (ett telefonnummer, en
  autentiseringsuppgift, meddelandeinnehåll, ett kontaktnamn eller ett
  per-identitet-fingeravtryck).
- Allt som låter den enda sanerande åtgärden ändra tillstånd utan bekräftelse eller utan
  att loggas.

## Vad som inte är ett säkerhetsproblem

- En avläsning som ser "fel" ut men är den ärliga sanningen (till exempel att RCS-rader på
  operatörssidan läser Nej medan RCS körs på Googles backend). Se
  [användarguiden](USER-GUIDE.md).
- Avsaknaden av ett Play Integrity-utlåtande. Det utlåtandet finns inte på enheten och VD
  Google kommer inte att hitta på det.

## Versioner som stöds

Endast den senast släppta versionen stöds. Reproducera på den senaste builden innan du
rapporterar.
