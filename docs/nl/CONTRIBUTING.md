> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · Nederlands · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Bijdragen

Bedankt voor je interesse in VD Google. Bijdragen zijn welkom: vertalingen, bugmeldingen,
documentatie en code.

## Basisregels

- **VD Google leest de waarheid en vervalst nooit iets.** Bijdragen moeten dat zo houden.
  Een echte negatief blijft negatief; een verouderde cache wordt gecachet gelabeld; een
  ontbrekende waarde is Unknown. Zwak een aflezing niet af om er mooier uit te laten zien.
- **Privacy is absoluut.** De app declareert geen permissies en de WebUI contacteert geen
  externe oorsprong. Voeg nooit een permissie, een netwerkoproep, of iets dat gegevens van
  het toestel verstuurt toe.
- **De app en de WebUI zijn identiek.** Elke wijziging aan wat wordt getoond, of aan het
  geëxporteerde rapport, moet in beide landen, met dezelfde bewoordingen. Ze mogen nooit
  uit elkaar gaan lopen.
- **Labels leven één keer, in 21 talen.** Elke voor de gebruiker zichtbare string is
  gedefinieerd voor de app (`res/values*/strings.xml`) en de WebUI (`webroot/lang/`). Als je
  een label toevoegt of wijzigt, werk dan alle talen bij.

## Vertalingen

Vertalingen zijn de gemakkelijkste en meest waardevolle bijdrage. De readme wordt per taal
vertaald (`README.<lang>.md`), de app- en WebUI-strings per taal, en deze documentatie onder
`docs/<lang>/`. Houd de terminologie binnen een taal consistent, sluit aan bij de bestaande
toon, en vertaal nooit machinaal een technische term waar je onzeker over bent.

## Een bug melden

Open een issue met je toestelmodel, Android-versie, en, als het over een aflezing gaat,
een **export** (die bevat geen persoonlijke gegevens). De diagnostiek-sectie van de export
is precies hiervoor gemaakt.

## Code

- Bouw release, nooit debug. Test op een echt, geroot toestel.
- Houd commentaar uit de code; namen verklaren de bedoeling. De enige header is de
  licentiebanner bovenaan elk bronbestand.
- Sluit aan bij de bestaande stijl en houd wijzigingen gericht.

## Licentie

Door bij te dragen ga je ermee akkoord dat jouw bijdrage wordt gelicentieerd onder de
**GNU AGPL-3.0-or-later** van het project.

<!-- doc-footer -->

---

> _Deze documentatie is gegenereerd met Claude (Anthropic) en wordt onderhouden door VD171. Correcties en verbeteringen zijn welkom; zie [CONTRIBUTING.md](CONTRIBUTING.md)._
