> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · Svenska · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Arkitektur

Hur VD Google är byggt och varför. Den här sidan förklarar designen och principerna. Den
listar inte de exakta filer, tabeller eller frågor som varje läsare använder. Det lever
bara i källkoden, avsiktligt, så att appen och dokumentationen aldrig kan glida in i en
bruksanvisning för någon som vill förfalska samma värden.

## Designmål

1. **Läs sanningen.** Visa det som Googles egna appar redan har registrerat, förklara det
   och hitta aldrig på ett värde. När enheten inte har något svar, säg Okänt.
2. **Noll nätverk.** Appen deklarerar inga behörigheter alls; WebUI blockerar varje
   fjärrursprung. Ingenting skickas någonstans, någonsin.
3. **Ett beteende, två ansikten.** Appen och WebUI-modulen hålls absolut identiska: samma
   signaler, samma formuleringar, samma exporterade rapport.
4. **Ärlig framför smickrande.** Ett verkligt negativt resultat visas som negativt; en
   inaktuell cache märks som cachad; ett saknat värde är Okänt. Detektering mjukas aldrig
   upp för att se snyggare ut.

## De två ansiktena

- **Appen** är Kotlin med Jetpack Compose (Material 3). Den får root via libsu-
  `RootService`, aldrig genom att köra `su` direkt, och den läser inuti den privilegierade
  tjänsten.
- **WebUI-modulen** är en liten HTML/JS-webbrot som serveras av din root-hanterare
  (KernelSU, APatch eller Magisk). Den utför samma läsningar via hanterarens root-brygga.
  En strikt Content-Security-Policy förbjuder varje fjärrursprung, så sidan kan inte ringa
  hem ens i princip.

Båda genereras från en enda delad uppsättning strängar på 21 språk, så en etikett
översätts aldrig två gånger.

## Läsa databaser: den medföljande sqlite3

En del av tillståndet som VD Google läser lever i SQLite-databaser som tillhör Googles
appar. Android levererar sitt eget SQLite, men ramverkets SQLite är opålitligt när det
gäller att öppna en annan apps databas kopierad åt sidan: på vissa tillverkar-ROM:ar
misslyckas det för *varje* databas och returnerar ingenting. För att läsa på samma sätt på
varje enhet levererar VD Google sin egen `sqlite3`-binär (arm64 och arm32) och kör frågor
genom den som root. Appen och WebUI använder den identiska binären, så de läser alltid
samma motor och samma resultat. Härkomst och kontrollsummor för den binären finns
dokumenterade i [Medföljande binärer](BINARIES.md).

Läsningar kopierar databasen åt sidan innan den öppnas, rör aldrig den levande filen och
kopierar aldrig index för delat minne, som en körande databas kan rapportera som felaktigt.

## RCS-signalen

Det svåraste med att läsa RCS ärligt är att de uppenbara flaggorna ljuger. Den enkla
"tillgänglig"-flaggan, funktionsflaggorna och provisioneringstillståndet kan alla läsa
negativt på en linje där RCS fungerar perfekt, eftersom de är cachar eller eftersom linjen
körs på Googles egen backend (Jibe) i stället för operatörens IMS-nätverk. VD Google
beräknar ett enda **Anslutnings**-utlåtande utifrån den verkliga chattkonfigurationen och
provisioneringsmotorn, vilket förblir korrekt över Android- och Google Messages-versioner,
och presenterar de ljugande flaggorna tydligt märkta som cachade så att de informerar utan
att vilseleda. Se [användarguiden](USER-GUIDE.md) för vad varje rad betyder.

## Exporten

Exporten är en sanerad JSON-ögonblicksbild. En sanerare körs innan något skrivs: den
släpper nycklar och värden som ser ut som autentiseringsuppgifter, redigerar bort långa
sifferföljder och lämnar aldrig ut ditt telefonnummer, meddelandeinnehåll, kontaktnamn
eller råa kapacitetsblobbar. Det valfria diagnostikavsnittet bär endast schemanamn,
antal, storlekar och versioner, och även där kollapsas varje per-identitet-fingeravtryck
inbäddat i ett namn bort. Appen och WebUI producerar samma schema.

## Vad VD Google aldrig gör

- Den deklarerar aldrig en nätverksbehörighet, och WebUI kontaktar aldrig ett
  fjärrursprung.
- Den fabricerar, förfalskar eller döljer aldrig ett värde.
- Den rapporterar aldrig ett Play Integrity-utlåtande, eftersom det utlåtandet inte finns
  på enheten.
- Den ändrar tillstånd endast genom en uttrycklig, bekräftad, lokalt loggad åtgärd.

<!-- doc-footer -->

---

> _Denna dokumentation genererades med Claude (Anthropic) och underhålls av VD171. Rättelser och förbättringar välkomnas; se [CONTRIBUTING.md](CONTRIBUTING.md)._
