> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · Čeština · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Přispívání

Děkujeme za váš zájem o VD Google. Příspěvky jsou vítány: překlady, hlášení chyb,
dokumentace a kód.

## Základní pravidla

- **VD Google čte pravdu a nikdy nic nefalšuje.** Příspěvky to tak musí zachovat.
  Skutečné záporné zjištění zůstává záporné; zastaralá mezipaměť je označena jako uložená
  v mezipaměti; chybějící hodnota je Neznámé. Nezměkčujte čtení, aby vypadalo hezčeji.
- **Soukromí je absolutní.** Aplikace nedeklaruje žádná oprávnění a WebUI nekontaktuje
  žádný vzdálený původ. Nikdy nepřidávejte oprávnění, síťové volání ani cokoli, co posílá
  data mimo zařízení.
- **Aplikace a WebUI jsou identické.** Jakákoli změna toho, co se zobrazuje, nebo
  exportovaného reportu musí dopadnout do obou, se stejným zněním. Nesmějí se nikdy
  rozejít.
- **Popisky žijí jen jednou, ve 21 jazycích.** Každý řetězec viditelný uživateli je
  definován pro aplikaci (`res/values*/strings.xml`) a pro WebUI (`webroot/lang/`). Pokud
  přidáte nebo změníte popisek, aktualizujte všechny jazyky.

## Překlady

Překlady jsou nejsnazší a nejcennější příspěvek. Readme se překládá podle jazyka
(`README.<lang>.md`), řetězce aplikace a WebUI podle jazyka a tato dokumentace pod
`docs/<lang>/`. Udržujte terminologii v rámci jazyka konzistentní, držte se stávajícího
tónu a nikdy strojově nepřekládejte technický termín, kterým si nejste jisti.

## Hlášení chyby

Založte issue s modelem svého zařízení, verzí Androidu a - pokud jde o čtení - s
**exportem** (neobsahuje žádná osobní data). Diagnostická sekce exportu je stavěná přesně
k tomuto.

## Kód

- Sestavujte release, nikdy debug. Testujte na skutečném rootnutém zařízení.
- Komentáře v kódu nenechávejte; záměr vysvětlují názvy. Jediným hlavičkovým textem je
  banner licence na začátku každého zdrojového souboru.
- Držte se stávajícího stylu a udržujte změny soustředěné.

## Licence

Přispěním souhlasíte s tím, že váš příspěvek je licencován pod projektovou
**GNU AGPL-3.0-or-later**.

<!-- doc-footer -->

---

> _Tato dokumentace byla vytvořena pomocí Claude (Anthropic) a spravuje ji VD171. Opravy a vylepšení jsou vítány; viz [CONTRIBUTING.md](CONTRIBUTING.md)._
