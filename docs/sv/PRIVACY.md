> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · Svenska · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Українська](../uk/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Integritet

Integritet är inte en funktion i VD Google - det är hela poängen. Den här sidan anger exakt
vad appen gör och inte gör med dina data, i klara ordalag som du kan verifiera.

## Löftet

- **Ingen internetbehörighet ges.** Appens manifest deklarerar inga behörigheter alls -
  inte internet, inte lagring, inte kontakter, ingenting. Det finns inget att tillåta.
- **Ingenting lagras, skickas eller överförs** till någon fil eller server utanför din
  enhet.
- Varje läsning sker **lokalt, via root**, och stannar på enheten.

## Varför det behöver root i stället för behörigheter

Tillståndet för RCS, Wallet och attestering som VD Google förklarar lever i skyddade,
endast system-åtkomliga områden av Android som vanliga appar inte kan se. VD Google läser
dem med root i stället för att be dig om breda behörigheter. Root är den smala, granskbara
vägen in; en hög med körtidsbehörigheter skulle vara den breda, läckande vägen. Appen
väljer den smala.

## Exporten saneras

När du exporterar en rapport sanerar VD Google den innan den skrivs:

- **Aldrig inkluderat:** ditt telefonnummer, någon autentiseringsuppgift, cookie eller
  token, meddelandeinnehåll, kontaktnamn eller råa kapacitetsblobbar.
- **Redigerat bort:** långa sifferföljder och värden som ser ut som hemligheter ersätts
  med en platshållare.
- **Diagnostik** bär endast struktur (schemanamn, antal, storlekar, versioner). Varje
  per-identitet-fingeravtryck som råkar förekomma inuti ett namn kollapsas bort.

Rapporten är din. Den skrivs där du väljer, delas endast om du delar den, och innehåller
inget som identifierar dig.

## Åtgärder är uttryckliga och loggade

VD Google kan rensa Google Wallets cachade attesteringsresultat så att en framtida
omkontroll startar på nytt. Varje åtgärd som ändrar tillstånd:

1. ber dig bekräfta först, och
2. skrivs till en lokal JSON-logg på enheten.

Ingen åtgärd når någonsin nätverket, och ingenting lämnar din telefon.

## Verifiera själv

Du behöver inte ta något av detta på tro:

- Källkoden är offentlig: <https://github.com/VD171/VD-Google>.
- Manifestet finns i källkoden; bekräfta att det inte deklarerar några behörigheter.
- WebUI:ns Content-Security-Policy finns i källkoden; bekräfta att den blockerar varje
  fjärrursprung.
- De medföljande binärerna är dokumenterade med kontrollsummor i
  [Medföljande binärer](BINARIES.md).
