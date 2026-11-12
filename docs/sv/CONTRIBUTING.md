> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · Svenska · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Bidra

Tack för ditt intresse för VD Google. Bidrag är välkomna: översättningar, buggrapporter,
dokumentation och kod.

## Grundregler

- **VD Google läser sanningen och förfalskar aldrig något.** Bidrag måste bevara det. Ett
  verkligt negativt resultat förblir negativt; en inaktuell cache märks som cachad; ett
  saknat värde är Okänt. Mjuka inte upp en avläsning för att få den att se snyggare ut.
- **Integritet är absolut.** Appen deklarerar inga behörigheter och WebUI kontaktar inget
  fjärrursprung. Lägg aldrig till en behörighet, ett nätverksanrop eller något som skickar
  data från enheten.
- **Appen och WebUI är identiska.** Varje ändring av vad som visas, eller av den
  exporterade rapporten, måste landa i båda, med samma formuleringar. De får aldrig glida
  isär.
- **Etiketter lever en gång, på 21 språk.** Varje sträng som användaren ser definieras för
  appen (`res/values*/strings.xml`) och WebUI (`webroot/lang/`). Om du lägger till eller
  ändrar en etikett, uppdatera alla språk.

## Översättningar

Översättningar är det enklaste och mest värdefulla bidraget. Readme-filen översätts per
språk (`README.<lang>.md`), app- och WebUI-strängarna per språk, och denna dokumentation
under `docs/<lang>/`. Håll terminologin konsekvent inom ett språk, matcha den befintliga
tonen och maskinöversätt aldrig en teknisk term du är osäker på.

## Rapportera en bugg

Öppna ett issue med din enhetsmodell, Android-version och - om det gäller en avläsning - en
**export** (den innehåller inga personuppgifter). Exportens diagnostikavsnitt är byggt för
just detta.

## Kod

- Bygg release, aldrig debug. Testa på en verklig, rootad enhet.
- Håll kommentarer borta från koden; namnen förklarar avsikten. Den enda rubriken är
  licensbannern överst i varje källfil.
- Matcha den befintliga stilen och håll ändringarna fokuserade.

## Licens

Genom att bidra godkänner du att ditt bidrag licensieras under projektets
**GNU AGPL-3.0-or-later**.
