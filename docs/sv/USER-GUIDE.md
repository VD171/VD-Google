> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · Svenska · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# VD Google användarguide

En vänlig, komplett genomgång av VD Google: vad det visar, vad varje rad betyder och hur
du läser din enhet ärligt. VD Google läser tillståndet för RCS, Google Wallet och de Play
Integrity-attesteringar som appar begär, och förklarar varje post i klartext. Det rör
aldrig internet, och det ändrar aldrig något om du inte ber om det.

## Innehållsförteckning

- [1. Vad VD Google är](#1-vad-vd-google-är)
- [2. Vad det inte är](#2-vad-det-inte-är)
- [3. Krav](#3-krav)
- [4. App eller WebUI](#4-app-eller-webui)
- [5. Installation och första start](#5-installation-och-första-start)
- [6. Flikarna](#6-flikarna)
  - [6.1 Om](#61-om)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Attesteringar](#65-attesteringar)
- [7. Exportera din rapport](#7-exportera-din-rapport)
- [8. Åtgärder som ändrar tillstånd](#8-åtgärder-som-ändrar-tillstånd)
- [9. Språk och tema](#9-språk-och-tema)
- [10. Vanliga frågor](#10-vanliga-frågor)

## 1. Vad VD Google är

VD Google är en läsare. Den tittar på vad Googles egna appar redan lagrar på din enhet om
tre saker, och förklarar var och en så att du faktiskt förstår den:

- **RCS** - om modern chatt verkligen fungerar på din linje, vem du kan chatta med och om
  Gemini i Google Messages är redo.
- **Google Wallet** - om din enhet är konfigurerad och attesterad för kontaktlösa
  betalningar.
- **Play Integrity-attesteringar** - vilka appar som har bett Android att bevisa att din
  enhet är äkta, och om Google för närvarande certifierar den.

Allt läses lokalt, via root, och visas med en förklaring. Inget värde hittas på: när
enheten inte har ett svar säger VD Google "Okänt" i stället för att gissa.

## 2. Vad det inte är

**VD Google är inte en integritetskontroll.** Det kör eller rapporterar inte något basic /
device / strong-utlåtande. Det resultatet lever endast inuti den krypterade token som en
app skickar till sin egen server, och lagras aldrig på enheten, så ingen ärlig läsare kan
visa det. Verktyg som påstår sig visa ditt "Play Integrity-utlåtande" lokalt visar dig en
cachad gissning eller en begäran de själva gjorde, inte det verkliga svaret.

VD Google förfalskar, döljer eller fejkar inte heller något. Det läser sanningen. Om ett
värde ser fel ut för dig är målet med den här guiden att förklara varför sanningen ser ut
så.

## 3. Krav

- **Android 8.0 (API 26) eller nyare.**
- **Root.** Datan som VD Google läser lever i skyddade, endast system-åtkomliga områden av
  Android, nåbara endast med root. Vilken modern root-lösning som helst fungerar: KernelSU,
  KernelSU Next, Magisk eller APatch.
- **Inget internet.** VD Google deklarerar inga behörigheter alls, så det finns inget att
  tillåta och inget att blockera.

## 4. App eller WebUI

VD Google levereras på två sätt, och de visar exakt samma information:

- **Appen (APK).** En vanlig Android-app som du installerar och öppnar. Ber om root första
  gången, som vilken root-app som helst.
- **WebUI-modulen (ZIP).** Flasha den i din root-hanterare (KernelSU, Magisk eller APatch)
  och öppna den därifrån. Ingenting installeras som en app; den körs inuti din
  root-hanterares WebUI.

Välj det du föredrar. Skärmarna, formuleringarna och den exporterade rapporten är
identiska mellan de två.

## 5. Installation och första start

**App:** installera APK:n och öppna den. Första gången, bevilja root när din root-hanterare
frågar. Om du nekar eller avvisar root visar VD Google en skärm som förklarar varför root
krävs och erbjuder att försöka igen.

**WebUI:** flasha ZIP:en som en modul i din root-hanterare, öppna sedan modulens WebUI från
hanteraren. Den läser så snart den öppnas.

Vid första öppningen kan du välja ditt språk; du kan ändra det senare från kugghjulsikonen
högst upp.

## 6. Flikarna

Längs botten finns fem flikar: Om, DroidGuard, RCS, Wallet och Attesteringar. Om öppnas
först.

### 6.1 Om

Vad appen är, integritetslöftet, versionen och länkar. Läs integritetsavsnittet en gång:
det är hela poängen med appen.

### 6.2 RCS

Detta är den mest innehållsrika fliken. Tidsstämplar visas alltid i din enhets tidszon,
med zonen märkt.

- **Anslutning** - den enda rad som säger sanningen. Den beräknas utifrån den verkliga
  chattkonfigurationen och provisioneringsmotorn, så den säger Ansluten när RCS faktiskt
  fungerar på din linje, även på nyare Android där de äldre flaggorna ljuger. **Vid
  tvekan, lita på den här raden.**
- **Operatör** - mobiloperatören för din linje (namn och MCC/MNC), hämtad från ditt
  data-SIM på telefoner med dubbla SIM.
- **Tillgänglighetsflagga (cachad)** - en cachad flagga som meddelandeappen håller för ditt
  eget nummer. Den kan förbli inaktuell efter att RCS redan fungerar, så ett "Nej" här
  betyder inte att RCS är av. Lita på Anslutnings-raden ovan.
- **Ditt RCS-nummer** - numret som registrerats för RCS, maskerat som standard. Tryck på
  ögat för att visa det.
- **Provisioneringstillstånd (cachat)** och **Konfigurationsversion** - interna värden som
  meddelandeappen håller. De kan läsa 0 även medan RCS fungerar. Se noten om operatör kontra
  Googles backend nedan.
- **RCS-kapabla kontakter** - hur många av dina kontakter som för närvarande är kända för
  att stödja RCS-chatt.
- **Funktionsflagga (cachad)** - om meddelandeappen har RCS-funktionen påslagen; cachad, så
  den kan släpa efter.
- **Operatörsberättigande (entitlement)**, **Närvaro (UCE)**, **IMS-tjänst närvarande** -
  dessa beskriver *operatörens* RCS-väg (operatörens IMS-nätverk). Ett "Nej" eller "0" här
  är normalt när din RCS körs genom Googles egen backend i stället för din operatör. Se
  nedan.
- **Gemini** - om Gemini i Google Messages är redo: **Magic Compose** (Geminis
  skrivhjälp), **AICore** (motorn på enheten som kör Gemini Nano) och **Gemini-chatt**, som
  levereras över RCS och därför behöver RCS anslutet.
- **Tidsstämplar** - provisionering, synkronisering, senaste meddelande, tokenförfall med
  mera.

**Operatörs-RCS kontra Googles backend.** Det finns två sätt som RCS når din telefon. Det
ena är din *operatörs* RCS över deras IMS-nätverk; det andra är *Googles egen backend*
(Jibe), som fungerar ovanpå, oberoende av operatören. Om din RCS körs genom Googles backend
- som den gör för de flesta - då är raderna på operatörssidan (operatörsberättigande,
närvaro/UCE, IMS-tjänst, konfigurationsversion) genuint "Nej" eller "0", och det är
korrekt, inte en inaktuell cache. Din RCS kan ha fungerat i åratal på det här sättet.
Anslutnings-raden är det som talar om för dig att den fungerar.

### 6.3 Wallet

- **Enhet attesterad** - om Google bedömde den här enheten som äkta och säker nog att hålla
  betalkort. Det är det senaste resultatet som Google sparade på den här enheten, så det
  kan släpa efter Wallet-appen tills Google kontrollerar på nytt. Om det sparade resultatet
  saknas visar VD Google **Okänt** i stället för ett vilseledande "Nej".
- **Senast kontrollerad** - när den säkerhetskontrollen senast slutfördes.
- **NFC-hårdvara**, **Wallet installerad**, **Wallet-tjänst**, **Tap and pay**,
  **Skärmlås accepterat**, **Kort någonsin tillagt** - delarna som gör kontaktlös betalning
  möjlig.

### 6.4 DroidGuard

- **Play Protect-certifierad** - om Google Play erkänner din enhet som certifierad
  (certifieringen som visas i Play Store).
- **Attesteringscache** - de Play Integrity- och DroidGuard-attesteringar som din enhet har
  cachat, var och en med när den förfaller.

### 6.5 Attesteringar

Historiken över vilka appar som begärde Play Integrity på din enhet: ikon, namn, när de
senast frågade och hur många gånger. Detta låter dig avgöra om en given app kontrollerar
integritet eller inte. Använd sökrutan för att filtrera på appnamn eller paket.

## 7. Exportera din rapport

Nedladdningsikonen skriver en **JSON**-rapport med din enhet, RCS, Wallet och
attesteringstillstånd. Du kan dela den eller spara den som en fil. Rapporten innehåller
**inget telefonnummer, inga autentiseringsuppgifter och inget personligt innehåll** - den
saneras innan den skrivs. Den innehåller också ett valfritt **diagnostik**-avsnitt
(schemanamn, antal och versioner, aldrig dina data) som hjälper till att felsöka
enhetsspecifika problem. Appen och WebUI skriver samma rapport.

## 8. Åtgärder som ändrar tillstånd

VD Google är mestadels skrivskyddat, men det kan utföra en **sanerande åtgärd**: att rensa
Google Wallets cachade attesteringsresultat, vilket låter en framtida omkontroll starta på
nytt. Varje åtgärd som ändrar tillstånd ber dig bekräfta först och skrivs till en lokal
JSON-logg på enheten. Ingenting lämnar din telefon.

## 9. Språk och tema

VD Google talar 21 språk. Välj ditt vid första start, eller ändra det när som helst från
kugghjulsikonen (även tillgänglig på skärmen för nekad root). Ljusstyrkeikonen växlar
mellan ljust och mörkt.

## 10. Vanliga frågor

**RCS säger Ansluten men flera rader säger Nej eller 0. Varför?** Eftersom din RCS körs
genom Googles egen backend, inte din operatör. Raderna på operatörssidan är korrekt
negativa. Se [6.2 RCS](#62-rcs).

**Enhet attesterad säger Okänt.** Det cachade Wallet-resultatet saknas på din enhet. Det är
ärligt - VD Google kommer inte att visa ett "Nej" som det inte kan styrka.

**Var är mitt Play Integrity-utlåtande?** Det finns inte på din enhet. Se
[2. Vad det inte är](#2-vad-det-inte-är).

**Fungerar det utan root?** Nej. Datan lever i endast system-åtkomliga områden.

<!-- doc-footer -->

---

> _Denna dokumentation genererades med Claude (Anthropic) och underhålls av VD171. Rättelser och förbättringar välkomnas; se [CONTRIBUTING.md](CONTRIBUTING.md)._
