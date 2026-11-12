> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · Čeština · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Soukromí

Soukromí není funkce VD Google - je to celý jeho smysl. Tato stránka přesně uvádí, co
aplikace s vašimi daty dělá a co ne, srozumitelně a ověřitelně.

## Slib

- **Nedává se žádné oprávnění k internetu.** Manifest aplikace nedeklaruje vůbec žádná
  oprávnění - ani internet, ani úložiště, ani kontakty, nic. Není co povolovat.
- **Nic se neukládá, neodesílá ani nepřenáší** do žádného souboru nebo serveru mimo vaše
  zařízení.
- Každé čtení probíhá **lokálně, přes root**, a zůstává na zařízení.

## Proč potřebuje root místo oprávnění

Stav RCS, Wallet a atestací, který VD Google vysvětluje, žije v chráněných, systémových
oblastech Androidu, jež běžné aplikace nevidí. VD Google je čte rootem, místo aby vás
žádal o široká oprávnění. Root je úzká, auditovatelná cesta dovnitř; hromada runtime
oprávnění by byla ta široká, netěsná cesta. Aplikace volí tu úzkou.

## Export je očištěn

Když exportujete report, VD Google jej před zápisem očistí:

- **Nikdy nezahrnuje:** vaše telefonní číslo, žádné přihlašovací údaje, cookie ani token,
  obsah zpráv, jména kontaktů ani surové bloby schopností.
- **Redaguje:** dlouhé posloupnosti číslic a hodnoty, které vypadají jako tajemství, se
  nahradí zástupným symbolem.
- **Diagnostika** nese jen strukturu (názvy schémat, počty, velikosti, verze). Jakýkoli
  otisk vázaný na konkrétní identitu, který se náhodou objeví uvnitř nějakého názvu, se
  sbalí pryč.

Report je váš. Zapisuje se tam, kam zvolíte, sdílí se jen tehdy, když jej sdílíte vy, a
neobsahuje nic, co by vás identifikovalo.

## Akce jsou výslovné a logované

VD Google může vymazat uložený výsledek atestace Google Wallet, aby budoucí opakovaná
kontrola začala načisto. Jakákoli akce, která mění stav:

1. vás nejprve požádá o potvrzení a
2. je zapsána do lokálního logu JSON na zařízení.

Žádná akce nikdy nedosáhne sítě a z vašeho telefonu nic neodchází.

## Ověřte si to sami

Nic z toho nemusíte brát jako samozřejmost:

- Zdrojový kód je veřejný: <https://github.com/VD171/VD-Google>.
- Manifest je ve zdrojovém kódu; ověřte, že nedeklaruje žádná oprávnění.
- Content-Security-Policy WebUI je ve zdrojovém kódu; ověřte, že blokuje každý vzdálený
  původ.
- Přibalené binárky jsou zdokumentovány s kontrolními součty v
  [Přibalené binárky](BINARIES.md).
