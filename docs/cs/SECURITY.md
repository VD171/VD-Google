> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · Čeština · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Bezpečnostní zásady

VD Google je nástroj na ochranu soukromí. Běží jako root a čte citlivý stav, takže
bezpečnostní hlášení bereme vážně.

## Hlášení zranitelnosti

Bezpečnostní problémy prosím hlaste **soukromě**, ne ve veřejném issue:

- Pošlete e-mail na **vd.priv8@pm.me**.
- Popište problém, dotčenou verzi a jak jej reprodukovat.
- Pokud se týká vystavení dat, popište třídu problému; nemusíte přikládat funkční exploit.

Odpověď dostanete co nejdříve. Prosíme, dejte rozumné okno na opravu problému před
jakýmkoli veřejným zveřejněním.

## Co se počítá jako bezpečnostní problém

Vzhledem k tomu, co VD Google slibuje, jsou tyto obzvlášť důležité:

- Cokoli, co by způsobilo, že se aplikace dostane na síť nebo odešle jakákoli data mimo
  zařízení.
- Cokoli, co by do **exportu** prolilo osobní data (telefonní číslo, přihlašovací údaje,
  obsah zprávy, jméno kontaktu nebo otisk vázaný na konkrétní identitu).
- Cokoli, co umožní té jediné očišťovací akci změnit stav bez potvrzení nebo bez zalogování.

## Co není bezpečnostní problém

- Čtení, které vypadá "špatně", ale je upřímnou pravdou (například linky RCS na straně
  operátora hlásící Ne, zatímco RCS běží přes backend Google). Viz
  [uživatelská příručka](USER-GUIDE.md).
- Nepřítomnost verdiktu Play Integrity. Ten verdikt na zařízení není a VD Google si jej
  nevymyslí.

## Podporované verze

Podporována je pouze nejnovější vydaná verze. Před nahlášením prosím reprodukujte na
nejnovějším sestavení.
