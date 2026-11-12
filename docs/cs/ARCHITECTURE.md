> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · Čeština · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Architektura

Jak je VD Google postaven a proč. Tato stránka vysvětluje návrh a jeho principy.
Neuvádí přesné soubory, tabulky ani dotazy, které používá každá čtečka - to zůstává
záměrně pouze ve zdrojovém kódu, aby se aplikace ani dokumentace nikdy nezvrhly v návod
pro někoho, kdo chce stejné hodnoty zfalšovat.

## Cíle návrhu

1. **Číst pravdu.** Ukázat to, co si samy aplikace Google už zaznamenaly, vysvětlit to a
   nikdy žádnou hodnotu nevymýšlet. Když zařízení odpověď nemá, řekni Neznámé.
2. **Nulová síť.** Aplikace nedeklaruje vůbec žádná oprávnění; WebUI blokuje každý
   vzdálený původ. Nikam se nikdy nic neodesílá.
3. **Jedno chování, dvě tváře.** Aplikace a modul WebUI jsou udržovány naprosto
   identické - stejné signály, stejné znění, stejný exportovaný report.
4. **Raději upřímné než lichotivé.** Skutečné záporné zjištění je zobrazeno jako záporné;
   zastaralá mezipaměť je označena jako uložená v mezipaměti; chybějící hodnota je
   Neznámé. Detekce se nikdy nezměkčuje, aby vypadala hezčeji.

## Dvě tváře

- **Aplikace** je napsaná v Kotlinu s Jetpack Compose (Material 3). Root získává přes
  `RootService` z libsu, nikdy přímým voláním `su`, a čte uvnitř této privilegované
  služby.
- **Modul WebUI** je malý webový root v HTML/JS, obsluhovaný vaším správcem root
  (KernelSU, Magisk nebo APatch). Provádí stejná čtení přes rootovací most správce. Přísná
  Content-Security-Policy zakazuje jakýkoli vzdálený původ, takže stránka nemůže volat
  domů ani v principu.

Obě jsou generovány z jedné sdílené sady řetězců ve 21 jazycích, takže se žádný popisek
nikdy nepřekládá dvakrát.

## Čtení databází: přibalený sqlite3

Část stavu, který VD Google čte, se nachází v databázích SQLite patřících aplikacím
Google. Android dodává vlastní SQLite, ale frameworkové SQLite není spolehlivé pro
otevírání databáze jiné aplikace zkopírované stranou: na některých výrobcovských ROM
selhává u *každé* databáze a nevrací nic. Aby VD Google četl na každém zařízení stejně,
přibaluje si vlastní binárku `sqlite3` (arm64 a arm32) a spouští přes ni dotazy jako root.
Aplikace a WebUI používají identickou binárku, takže čtou vždy stejným enginem a dostanou
stejný výsledek. Původ a kontrolní součty této binárky jsou zdokumentovány v
[Přibalené binárky](BINARIES.md).

Čtení kopíruje databázi stranou, než ji otevře, nikdy se nedotýká živého souboru a nikdy
nekopíruje index sdílené paměti, který může běžící databáze hlásit jako poškozený.

## Signál RCS

Nejtěžší na upřímném čtení RCS je, že zjevné příznaky lžou. Jednoduchý příznak
"dostupné", příznaky funkcí i stav provisioningu mohou všechny vyjít záporně na lince, kde
RCS funguje dokonale, protože jde o mezipaměti nebo protože linka běží přes vlastní backend
Google (Jibe) místo přes síť IMS operátora. VD Google počítá jediný verdikt **Připojení**
ze skutečné konfigurace chatu a z provisioningového enginu, který zůstává správný napříč
verzemi Androidu i Google Messages, a lživé příznaky zobrazuje jasně označené jako uložené
v mezipaměti, aby informovaly, ale nematly. Co která linka znamená, najdete v
[uživatelské příručce](USER-GUIDE.md).

## Export

Export je očištěný snímek JSON. Před čímkoli zapsaným se spustí čistič: zahodí klíče a
hodnoty, které vypadají jako přihlašovací údaje, zredukuje dlouhé posloupnosti číslic a
nikdy nevydá vaše telefonní číslo, obsah zpráv, jména kontaktů ani surové bloby
schopností. Volitelná diagnostická sekce nese jen názvy schémat, počty, velikosti a verze
- a i tam se jakýkoli otisk vázaný na konkrétní identitu, vložený do nějakého názvu,
sbalí pryč. Aplikace i WebUI produkují stejné schéma.

## Co VD Google nikdy nedělá

- Nikdy nedeklaruje síťové oprávnění a WebUI nikdy nekontaktuje vzdálený původ.
- Nikdy nevymýšlí, nefalšuje ani neskrývá žádnou hodnotu.
- Nikdy nehlásí verdikt Play Integrity, protože ten verdikt na zařízení není.
- Stav mění pouze výslovnou, potvrzenou a lokálně zalogovanou akcí.
