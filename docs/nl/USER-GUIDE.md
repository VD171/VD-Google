> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · Nederlands · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# VD Google gebruikershandleiding

Een vriendelijke, volledige rondleiding door VD Google: wat het toont, wat elke regel
betekent, en hoe je je toestel eerlijk leest. VD Google leest de status van RCS, Google
Wallet en de Play Integrity-attestaties die apps aanvragen, en legt elk item in gewone
woorden uit. Het raakt nooit het internet aan, en het verandert nooit iets tenzij je erom
vraagt.

## Inhoudsopgave

- [1. Wat VD Google is](#1-wat-vd-google-is)
- [2. Wat het niet is](#2-wat-het-niet-is)
- [3. Vereisten](#3-vereisten)
- [4. App of WebUI](#4-app-of-webui)
- [5. Installatie en eerste start](#5-installatie-en-eerste-start)
- [6. De tabbladen](#6-de-tabbladen)
  - [6.1 Over](#61-over)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Attestaties](#65-attestaties)
- [7. Je rapport exporteren](#7-je-rapport-exporteren)
- [8. Acties die de status wijzigen](#8-acties-die-de-status-wijzigen)
- [9. Taal en thema](#9-taal-en-thema)
- [10. Veelgestelde vragen](#10-veelgestelde-vragen)

## 1. Wat VD Google is

VD Google is een lezer. Het kijkt naar wat Googles eigen apps al op je toestel opslaan over
drie dingen, en het legt elk ervan uit zodat je het echt begrijpt:

- **RCS** - of moderne chat echt werkt op je lijn, met wie je kunt chatten, en of Gemini in
  Google Messages klaar is.
- **Google Wallet** - of je toestel is ingesteld en geattesteerd voor contactloze
  betalingen.
- **Play Integrity-attestaties** - welke apps Android hebben gevraagd te bewijzen dat je
  toestel echt is, en of Google het momenteel certificeert.

Alles wordt lokaal gelezen, via root, en getoond met een uitleg. Geen enkele waarde wordt
verzonnen: als het toestel geen antwoord heeft, zegt VD Google "Unknown" in plaats van te
gokken.

## 2. Wat het niet is

**VD Google is geen integriteitscontroleur.** Het voert geen basic / device / strong oordeel
uit of rapporteert het. Dat resultaat leeft alleen binnen het versleutelde token dat een app
naar zijn eigen server stuurt, en wordt nooit op het toestel opgeslagen, dus geen enkele
eerlijke lezer kan het tonen. Tools die beweren je "Play Integrity-oordeel" lokaal te tonen,
laten je een gecachete gok zien of een verzoek dat ze zelf hebben gedaan, niet het echte
antwoord.

VD Google spooft, verbergt of vervalst ook niets. Het leest de waarheid. Als een waarde er
voor jou verkeerd uitziet, is het doel van deze gids uit te leggen waarom de waarheid er zo
uitziet.

## 3. Vereisten

- **Android 8.0 (API 26) of nieuwer.**
- **Root.** De gegevens die VD Google leest, leven in beschermde, systeem-only delen van
  Android, alleen bereikbaar met root. Elke moderne root-oplossing werkt: KernelSU, KernelSU
  Next, Magisk of APatch.
- **Geen internet.** VD Google declareert helemaal geen permissies, dus er is niets toe te
  staan en niets te blokkeren.

## 4. App of WebUI

VD Google wordt op twee manieren geleverd, en ze tonen exact dezelfde informatie:

- **De app (APK).** Een normale Android-app die je installeert en opent. Vraagt de eerste
  keer om root, zoals elke root-app.
- **De WebUI-module (ZIP).** Flash hem in je root-manager (KernelSU, Magisk of APatch) en
  open hem van daaruit. Er wordt niets als app geïnstalleerd; het draait binnen de WebUI van
  je root-manager.

Kies wat je verkiest. De schermen, de bewoordingen en het geëxporteerde rapport zijn tussen
de twee identiek.

## 5. Installatie en eerste start

**App:** installeer de APK en open hem. Verleen de eerste keer root wanneer je root-manager
erom vraagt. Als je root weigert of wegklikt, toont VD Google een scherm dat uitlegt waarom
root vereist is en biedt aan het opnieuw te proberen.

**WebUI:** flash de ZIP als een module in je root-manager, en open dan de WebUI van de module
vanuit de manager. Het leest zodra het opent.

Bij de eerste opening kun je je taal kiezen; je kunt die later wijzigen via het tandwielicoon
bovenaan.

## 6. De tabbladen

Onderaan staan vijf tabbladen: Over, DroidGuard, RCS, Wallet en Attestaties. Over opent als
eerste.

### 6.1 Over

Wat de app is, de privacybelofte, de versie, en links. Lees de privacy-sectie één keer: het
is het hele punt van de app.

### 6.2 RCS

Dit is het rijkste tabblad. Tijdstempels worden altijd getoond in de tijdzone van je
toestel, met de zone gelabeld.

- **Connection** - de ene regel die de waarheid vertelt. Ze wordt berekend uit de echte
  chatconfiguratie en de provisioning-engine, dus ze zegt Connected wanneer RCS werkelijk
  werkt op je lijn, zelfs op nieuwere Android waar de oudere vlaggen liegen. **Vertrouw bij
  twijfel op deze regel.**
- **Carrier** - de mobiele provider voor je lijn (naam en MCC/MNC), genomen van je data-SIM
  op dual-SIM-telefoons.
- **Availability flag (cached)** - een gecachete vlag die de berichten-app bijhoudt voor je
  eigen nummer. Ze kan verouderd blijven nadat RCS al werkt, dus een "No" hier betekent niet
  dat RCS uit staat. Vertrouw op de Connection-regel hierboven.
- **Your RCS number** - het voor RCS geregistreerde nummer, standaard gemaskeerd. Tik op het
  oog om het te onthullen.
- **Provisioning state (cached)** en **Configuration version** - interne waarden die de
  berichten-app bijhoudt. Ze kunnen 0 lezen terwijl RCS werkt. Zie de opmerking over
  provider versus Googles backend hieronder.
- **RCS-capable contacts** - hoeveel van je contacten momenteel bekend zijn als
  ondersteunend voor RCS-chat.
- **Feature flag (cached)** - of de berichten-app de RCS-functie heeft ingeschakeld;
  gecachet, dus kan achterlopen.
- **Carrier entitlement**, **Presence (UCE)**, **IMS service present** - deze beschrijven het
  RCS-pad van de *provider* (het IMS-netwerk van de operator). Een "No" of "0" hier is normaal
  wanneer je RCS via Googles eigen backend loopt in plaats van via je provider. Zie hieronder.
- **Gemini** - of Gemini in Google Messages klaar is: **Magic Compose** (Gemini's schrijfhulp),
  **AICore** (de on-device engine die Gemini Nano draait), en **Gemini chat**, die over RCS
  wordt geleverd en dus RCS verbonden nodig heeft.
- **Timestamps** - provisioning, synchronisatie, laatste bericht, tokenvervaldatum en meer.

**Provider-RCS versus Googles backend.** Er zijn twee manieren waarop RCS je telefoon
bereikt. Eén is de RCS van je *provider* over hun IMS-netwerk; de andere is *Googles eigen
backend* (Jibe), die over-the-top werkt, onafhankelijk van de provider. Als je RCS via Googles
backend loopt - zoals bij de meeste mensen - dan zijn de provider-zijde regels (carrier
entitlement, presence/UCE, IMS service, configuration version) oprecht "No" of "0", en dat is
correct, geen verouderde cache. Je RCS kan op deze manier jarenlang hebben gewerkt. De
Connection-regel is wat je vertelt dat het werkt.

### 6.3 Wallet

- **Device attested** - of Google dit toestel echt en veilig genoeg oordeelde om
  betaalkaarten te bevatten. Het is het laatste resultaat dat Google op dit toestel heeft
  opgeslagen, dus het kan achterlopen op de Wallet-app totdat Google opnieuw controleert. Als
  het opgeslagen resultaat ontbreekt, toont VD Google **Unknown** in plaats van een misleidend
  "No".
- **Last checked** - wanneer die beveiligingscontrole als laatste werd voltooid.
- **NFC hardware**, **Wallet installed**, **Wallet service**, **Tap and pay**,
  **Screen lock accepted**, **Card ever added** - de onderdelen die contactloos betalen
  mogelijk maken.

### 6.4 DroidGuard

- **Play Protect certified** - of Google Play je toestel als gecertificeerd herkent (de
  certificering die in de Play Store wordt getoond).
- **Attestation cache** - de Play Integrity- en DroidGuard-attestaties die je toestel heeft
  gecachet, elk met wanneer ze vervallen.

### 6.5 Attestaties

De geschiedenis van welke apps Play Integrity op je toestel hebben aangevraagd: icoon, naam,
wanneer ze het laatst vroegen, en hoe vaak. Zo kun je zien of een bepaalde app op integriteit
controleert of niet. Gebruik het zoekvak om te filteren op app-naam of pakket.

## 7. Je rapport exporteren

Het downloadicoon schrijft een **JSON**-rapport met je toestel-, RCS-, Wallet- en
attestatiestatus. Je kunt het delen of als bestand opslaan. Het rapport bevat **geen
telefoonnummer, geen inloggegevens en geen persoonlijke inhoud** - het wordt opgeschoond
voordat het wordt geschreven. Het bevat ook een optionele **diagnostiek**-sectie (schemanamen,
aantallen en versies, nooit je gegevens) die helpt bij het oplossen van toestelspecifieke
problemen. De app en de WebUI schrijven hetzelfde rapport.

## 8. Acties die de status wijzigen

VD Google is grotendeels alleen-lezen, maar het kan één **opschonende actie** uitvoeren: het
wissen van het gecachete attestatieresultaat van Google Wallet, waardoor een toekomstige
hercontrole opnieuw begint. Elke actie die de status wijzigt, vraagt je eerst om bevestiging,
en wordt naar een lokaal JSON-log op het toestel geschreven. Niets verlaat je telefoon.

## 9. Taal en thema

VD Google spreekt 21 talen. Kies de jouwe bij de eerste start, of wijzig die op elk moment
via het tandwielicoon (ook beschikbaar op het scherm bij geweigerde root). Het helderheidsicoon
schakelt tussen licht en donker.

## 10. Veelgestelde vragen

**RCS zegt Connected maar meerdere regels zeggen No of 0. Waarom?** Omdat je RCS via Googles
eigen backend loopt, niet via je provider. De provider-zijde regels zijn correct negatief. Zie
[6.2 RCS](#62-rcs).

**Device attested zegt Unknown.** Het gecachete Wallet-resultaat ontbreekt op je toestel. Dat
is eerlijk - VD Google toont geen "No" die het niet kan staven.

**Waar is mijn Play Integrity-oordeel?** Het staat niet op je toestel. Zie [2. Wat het niet
is](#2-wat-het-niet-is).

**Werkt het zonder root?** Nee. De gegevens leven in systeem-only delen.
