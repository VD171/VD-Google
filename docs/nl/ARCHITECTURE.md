> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · Nederlands · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Architectuur

Hoe VD Google is gebouwd en waarom. Deze pagina legt het ontwerp en de principes uit. Ze
somt niet de exacte bestanden, tabellen of query's op die elke lezer gebruikt - dat leeft
alleen in de broncode, met opzet, zodat de app en de documentatie nooit kunnen afglijden
naar een handleiding voor iemand die dezelfde waarden wil vervalsen.

## Ontwerpdoelen

1. **Lees de waarheid.** Toon wat Googles eigen apps al hebben vastgelegd, leg het uit, en
   verzin nooit een waarde. Als het toestel geen antwoord heeft, zeg Unknown.
2. **Geen netwerk.** De app declareert helemaal geen permissies; de WebUI blokkeert elke
   externe oorsprong. Er wordt nooit iets ergens naartoe gestuurd, nooit.
3. **Eén gedrag, twee gezichten.** De app en de WebUI-module worden absoluut identiek
   gehouden - dezelfde signalen, dezelfde bewoordingen, hetzelfde geëxporteerde rapport.
4. **Eerlijk boven vleiend.** Een echte negatief wordt als negatief getoond; een verouderde
   cache wordt als gecachet gelabeld; een ontbrekende waarde is Unknown. Detectie wordt
   nooit afgezwakt om er mooier uit te zien.

## De twee gezichten

- **De app** is Kotlin met Jetpack Compose (Material 3). Ze krijgt root via de libsu
  `RootService`, nooit door rechtstreeks `su` aan te roepen, en ze leest binnen die
  bevoorrechte service.
- **De WebUI-module** is een kleine HTML/JS-webroot die door je root-manager (KernelSU,
  Magisk of APatch) wordt geserveerd. Ze voert dezelfde leesbewerkingen uit via de
  root-brug van de manager. Een strikt Content-Security-Policy verbiedt elke externe
  oorsprong, zodat de pagina zelfs in principe niet naar huis kan bellen.

Beide worden gegenereerd uit één gedeelde set strings in 21 talen, zodat een label nooit
twee keer wordt vertaald.

## Databases lezen: de meegeleverde sqlite3

Een deel van de status die VD Google leest, leeft in SQLite-databases die aan Googles apps
toebehoren. Android levert zijn eigen SQLite, maar de framework-SQLite is onbetrouwbaar
voor het openen van de opzij gekopieerde database van een andere app: op sommige
fabrikant-ROM's faalt ze voor *elke* database en geeft niets terug. Om op elk toestel op
dezelfde manier te lezen, levert VD Google zijn eigen `sqlite3`-binary mee (arm64 en
arm32) en voert de query's er als root doorheen uit. De app en de WebUI gebruiken de
identieke binary, zodat ze altijd dezelfde engine en hetzelfde resultaat lezen. De
herkomst en checksums van die binary zijn gedocumenteerd in
[Meegeleverde binaries](BINARIES.md).

Leesbewerkingen kopiëren de database eerst opzij voordat ze hem openen, raken nooit het
live bestand aan, en kopiëren nooit de gedeelde-geheugenindex, die een draaiende database
als beschadigd kan rapporteren.

## Het RCS-signaal

Het moeilijkste aan het eerlijk lezen van RCS is dat de voor de hand liggende vlaggen
liegen. De eenvoudige "available"-vlag, de feature-vlaggen en de provisioning-status kunnen
allemaal negatief lezen op een lijn waar RCS perfect werkt, omdat het caches zijn of omdat
de lijn op Googles eigen backend (Jibe) draait in plaats van op het IMS-netwerk van de
provider. VD Google berekent één **Connection**-oordeel uit de echte chatconfiguratie en de
provisioning-engine, dat correct blijft over Android- en Google Messages-versies heen, en
presenteert de liegende vlaggen duidelijk gelabeld als gecachet, zodat ze informeren zonder
te misleiden. Zie de [gebruikershandleiding](USER-GUIDE.md) voor wat elke regel betekent.

## De export

De export is een opgeschoonde JSON-momentopname. Een opschoner draait voordat er iets wordt
weggeschreven: hij verwijdert sleutels en waarden die op inloggegevens lijken, redigeert
lange cijferreeksen, en zendt nooit je telefoonnummer, berichtinhoud, contactnamen of ruwe
capability-blobs uit. De optionele diagnostiek-sectie bevat alleen schemanamen, aantallen,
groottes en versies - en zelfs daar wordt elke per-identiteit-vingerafdruk die in een naam
is ingebed, weggevouwen. De app en de WebUI produceren hetzelfde schema.

## Wat VD Google nooit doet

- Ze declareert nooit een netwerkpermissie, en de WebUI contacteert nooit een externe
  oorsprong.
- Ze verzint, spooft of verbergt nooit een waarde.
- Ze rapporteert nooit een Play Integrity-oordeel, omdat dat oordeel niet op het toestel
  staat.
- Ze wijzigt de status alleen via een expliciete, bevestigde, lokaal gelogde actie.
