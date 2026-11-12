> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · Čeština · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Uživatelská příručka VD Google

Přátelský, kompletní průvodce VD Google: co ukazuje, co znamená každý řádek a jak své
zařízení číst upřímně. VD Google čte stav RCS, Google Wallet a atestací Play Integrity,
které aplikace vyžadují, a každou položku vysvětluje srozumitelnými slovy. Nikdy se
nedotkne internetu a nikdy nic nemění, pokud o to nepožádáte.

## Obsah

- [1. Co je VD Google](#1-co-je-vd-google)
- [2. Co to není](#2-co-to-není)
- [3. Požadavky](#3-požadavky)
- [4. Aplikace nebo WebUI](#4-aplikace-nebo-webui)
- [5. Instalace a první spuštění](#5-instalace-a-první-spuštění)
- [6. Záložky](#6-záložky)
  - [6.1 O aplikaci](#61-o-aplikaci)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Atestace](#65-atestace)
- [7. Exportujte svůj report](#7-exportujte-svůj-report)
- [8. Akce, které mění stav](#8-akce-které-mění-stav)
- [9. Jazyk a motiv](#9-jazyk-a-motiv)
- [10. Často kladené otázky](#10-často-kladené-otázky)

## 1. Co je VD Google

VD Google je čtečka. Dívá se na to, co si samy aplikace Google na vašem zařízení už
ukládají o třech věcech, a každou vysvětluje tak, abyste jí skutečně rozuměli:

- **RCS** - zda na vaší lince opravdu funguje moderní chat, s kým si můžete psát a zda je
  připraveno Gemini v Google Messages.
- **Google Wallet** - zda je vaše zařízení nastavené a atestované pro bezkontaktní platby.
- **Atestace Play Integrity** - které aplikace požádaly Android, aby dokázal, že vaše
  zařízení je pravé, a zda jej Google aktuálně certifikuje.

Vše se čte lokálně, přes root, a zobrazuje s vysvětlením. Žádná hodnota se nevymýšlí: když
zařízení odpověď nemá, VD Google řekne "Neznámé", místo aby hádal.

## 2. Co to není

**VD Google není kontrolor integrity.** Nespouští ani nehlásí žádný verdikt basic /
device / strong. Ten výsledek žije jen uvnitř šifrovaného tokenu, který aplikace posílá na
svůj vlastní server, a na zařízení se nikdy neukládá, takže jej žádná upřímná čtečka
ukázat nemůže. Nástroje, které tvrdí, že lokálně zobrazují váš "verdikt Play Integrity",
vám ukazují uložený odhad nebo požadavek, který si samy vytvořily, ne skutečnou odpověď.

VD Google také nic nefalšuje, neskrývá ani nepodvrhuje. Čte pravdu. Pokud vám nějaká
hodnota připadá špatná, cílem této příručky je vysvětlit, proč pravda vypadá tak, jak
vypadá.

## 3. Požadavky

- **Android 8.0 (API 26) nebo novější.**
- **Root.** Data, která VD Google čte, žijí v chráněných, systémových oblastech Androidu,
  dosažitelných jen rootem. Funguje jakékoli moderní rootovací řešení: KernelSU, KernelSU
  Next, Magisk nebo APatch.
- **Žádný internet.** VD Google nedeklaruje vůbec žádná oprávnění, takže není co povolovat
  ani co blokovat.

## 4. Aplikace nebo WebUI

VD Google se dodává dvěma způsoby a oba ukazují přesně stejné informace:

- **Aplikace (APK).** Běžná aplikace pro Android, kterou nainstalujete a otevřete. Při
  prvním spuštění požádá o root, jako každá rootovací aplikace.
- **Modul WebUI (ZIP).** Nainstalujte jej ve svém správci root (KernelSU, Magisk nebo
  APatch) a otevřete jej odtamtud. Nic se neinstaluje jako aplikace; běží uvnitř WebUI
  vašeho správce root.

Vyberte si, co preferujete. Obrazovky, znění i exportovaný report jsou mezi oběma
identické.

## 5. Instalace a první spuštění

**Aplikace:** nainstalujte APK a otevřete jej. Při prvním spuštění udělte root, až o něj
váš správce root požádá. Pokud root odmítnete nebo zavřete, VD Google zobrazí obrazovku
vysvětlující, proč je root potřeba, a nabídne nový pokus.

**WebUI:** nainstalujte ZIP jako modul ve svém správci root a pak otevřete WebUI modulu
ze správce. Čte hned, jakmile se otevře.

Při prvním otevření si můžete vybrat jazyk; změnit jej lze později pomocí ikony ozubeného
kola nahoře.

## 6. Záložky

Dole je pět záložek: O aplikaci, DroidGuard, RCS, Wallet a Atestace. Jako první se otevře
O aplikaci.

### 6.1 O aplikaci

Co aplikace je, slib ochrany soukromí, verze a odkazy. Přečtěte si sekci o soukromí
jednou: je to celý smysl aplikace.

### 6.2 RCS

Toto je nejbohatší záložka. Časové značky se vždy zobrazují v časovém pásmu vašeho
zařízení, s označeným pásmem.

- **Připojení** - jediný řádek, který říká pravdu. Počítá se ze skutečné konfigurace
  chatu a z provisioningového enginu, takže říká Připojeno, když RCS na vaší lince
  opravdu funguje, i na novějším Androidu, kde starší příznaky lžou. **Když si nejste
  jisti, věřte tomuto řádku.**
- **Operátor** - mobilní operátor vaší linky (název a MCC/MNC), převzatý z vaší datové SIM
  u telefonů se dvěma SIM.
- **Příznak dostupnosti (v mezipaměti)** - příznak uložený v mezipaměti, který si
  aplikace pro zprávy drží pro vaše vlastní číslo. Může zůstat zastaralý i poté, co už RCS
  funguje, takže "Ne" zde neznamená, že je RCS vypnuté. Věřte řádku Připojení výše.
- **Vaše číslo RCS** - číslo registrované pro RCS, ve výchozím stavu maskované. Klepnutím
  na oko jej odhalíte.
- **Stav provisioningu (v mezipaměti)** a **Verze konfigurace** - interní hodnoty, které
  si aplikace pro zprávy drží. Mohou ukazovat 0, i když RCS funguje. Viz poznámka o
  operátorovi vs. backendu Google níže.
- **Kontakty podporující RCS** - kolik z vašich kontaktů je aktuálně známo, že podporuje
  chat RCS.
- **Příznak funkce (v mezipaměti)** - zda má aplikace pro zprávy zapnutou funkci RCS;
  uloženo v mezipaměti, takže se může zpožďovat.
- **Nárok operátora (entitlement)**, **Přítomnost (UCE)**, **Služba IMS přítomna** - tyto
  popisují cestu RCS *operátora* (síť IMS operátora). "Ne" nebo "0" zde je normální, když
  vaše RCS běží přes vlastní backend Google místo přes operátora. Viz níže.
- **Gemini** - zda je Gemini v Google Messages připraveno: **Magic Compose** (pomoc s
  psaním od Gemini), **AICore** (engine na zařízení, který pohání Gemini Nano) a **chat
  Gemini**, který se doručuje přes RCS, a proto potřebuje připojené RCS.
- **Časové značky** - provisioning, synchronizace, poslední zpráva, expirace tokenu a
  další.

**RCS operátora vs. backend Google.** RCS se k vašemu telefonu dostává dvěma cestami.
Jednou je RCS vašeho *operátora* přes jeho síť IMS; druhou je *vlastní backend Google*
(Jibe), který funguje nad sítí, nezávisle na operátorovi. Pokud vaše RCS běží přes backend
Google - jako u většiny lidí - pak jsou řádky na straně operátora (nárok operátora,
přítomnost/UCE, služba IMS, verze konfigurace) skutečně "Ne" nebo "0", a to je správně, ne
zastaralá mezipaměť. Vaše RCS mohlo takto fungovat celé roky. Že funguje, vám říká řádek
Připojení.

### 6.3 Wallet

- **Zařízení atestováno** - zda Google posoudil toto zařízení jako dost pravé a bezpečné,
  aby drželo platební karty. Je to poslední výsledek, který Google na tomto zařízení
  uložil, takže se může oproti aplikaci Wallet zpožďovat, dokud Google neprovede
  opakovanou kontrolu. Pokud uložený výsledek chybí, VD Google zobrazí **Neznámé** místo
  zavádějícího "Ne".
- **Naposledy zkontrolováno** - kdy naposledy proběhla ta bezpečnostní kontrola.
- **Hardware NFC**, **Wallet nainstalován**, **Služba Wallet**, **Tap and pay**,
  **Zámek obrazovky přijat**, **Karta kdy přidána** - součásti, které umožňují
  bezkontaktní platbu.

### 6.4 DroidGuard

- **Certifikováno Play Protect** - zda Google Play rozpoznává vaše zařízení jako
  certifikované (certifikace zobrazená v Play Store).
- **Mezipaměť atestací** - atestace Play Integrity a DroidGuard, které má vaše zařízení
  uložené v mezipaměti, každá s tím, kdy vyprší.

### 6.5 Atestace

Historie toho, které aplikace na vašem zařízení vyžádaly Play Integrity: ikona, název,
kdy se ptaly naposledy a kolikrát. To vám umožní poznat, zda daná aplikace kontroluje
integritu, nebo ne. Vyhledávacím polem filtrujte podle názvu aplikace nebo balíčku.

## 7. Exportujte svůj report

Ikona stažení zapíše report ve formátu **JSON** se stavem vašeho zařízení, RCS, Wallet a
atestací. Můžete jej sdílet nebo uložit jako soubor. Report neobsahuje **žádné telefonní
číslo, žádné přihlašovací údaje a žádný osobní obsah** - před zápisem se očistí. Obsahuje
také volitelnou sekci **diagnostiky** (názvy schémat, počty a verze, nikdy vaše data),
která pomáhá řešit problémy specifické pro zařízení. Aplikace a WebUI zapisují stejný
report.

## 8. Akce, které mění stav

VD Google je převážně jen pro čtení, ale umí provést jednu **očišťovací akci**: vymazání
uloženého výsledku atestace Google Wallet, což umožní, aby budoucí opakovaná kontrola
začala načisto. Každá akce, která mění stav, vás nejprve požádá o potvrzení a je zapsána
do lokálního logu JSON na zařízení. Z vašeho telefonu nic neodchází.

## 9. Jazyk a motiv

VD Google mluví 21 jazyky. Vyberte si svůj při prvním spuštění, nebo jej kdykoli změňte
pomocí ikony ozubeného kola (dostupné i na obrazovce odepřeného rootu). Ikona jasu
přepíná mezi světlým a tmavým vzhledem.

## 10. Často kladené otázky

**RCS říká Připojeno, ale několik řádků říká Ne nebo 0. Proč?** Protože vaše RCS běží přes
vlastní backend Google, ne přes operátora. Řádky na straně operátora jsou správně záporné.
Viz [6.2 RCS](#62-rcs).

**Zařízení atestováno říká Neznámé.** Uložený výsledek Wallet na vašem zařízení chybí. To
je upřímné - VD Google neukáže "Ne", které nemůže podložit.

**Kde je můj verdikt Play Integrity?** Na vašem zařízení není. Viz
[2. Co to není](#2-co-to-není).

**Funguje to bez rootu?** Ne. Data žijí v systémových oblastech.
