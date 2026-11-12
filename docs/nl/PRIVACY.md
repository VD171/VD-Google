> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · Nederlands · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Privacy

Privacy is geen functie van VD Google - het is het hele punt. Deze pagina stelt precies
vast wat de app wel en niet met je gegevens doet, in duidelijke bewoordingen die je kunt
verifiëren.

## De belofte

- **Er wordt geen internetpermissie gegeven.** Het manifest van de app declareert helemaal
  geen permissies - geen internet, geen opslag, geen contacten, niets. Er valt niets toe te
  staan.
- **Er wordt niets opgeslagen, verzonden of overgedragen** naar enig bestand of enige server
  buiten je toestel.
- Elke aflezing gebeurt **lokaal, via root**, en blijft op het toestel.

## Waarom het root nodig heeft in plaats van permissies

De RCS-, Wallet- en attestatiestatus die VD Google uitlegt, leeft in beschermde,
systeem-only delen van Android die gewone apps niet kunnen zien. VD Google leest ze met root
in plaats van je om brede permissies te vragen. Root is de smalle, controleerbare toegang;
een stapel runtime-permissies zou de brede, lekkende weg zijn. De app kiest de smalle.

## De export is opgeschoond

Wanneer je een rapport exporteert, schoont VD Google het op voordat het wordt weggeschreven:

- **Nooit opgenomen:** je telefoonnummer, enige inloggegevens, cookie of token,
  berichtinhoud, contactnamen, of ruwe capability-blobs.
- **Geredigeerd:** lange cijferreeksen en waarden die op geheimen lijken, worden vervangen
  door een tijdelijke aanduiding.
- **Diagnostiek** bevat alleen structuur (schemanamen, aantallen, groottes, versies). Elke
  per-identiteit-vingerafdruk die toevallig binnen een naam voorkomt, wordt weggevouwen.

Het rapport is van jou. Het wordt geschreven waar jij kiest, het wordt alleen gedeeld als
jij het deelt, en het bevat niets dat jou identificeert.

## Acties zijn expliciet en gelogd

VD Google kan het gecachete attestatieresultaat van Google Wallet wissen, zodat een
toekomstige hercontrole opnieuw begint. Elke actie die de status wijzigt:

1. vraagt je eerst om bevestiging, en
2. wordt naar een lokaal JSON-log op het toestel geschreven.

Geen enkele actie bereikt ooit het netwerk, en niets verlaat je telefoon.

## Verifieer het zelf

Je hoeft niets hiervan op vertrouwen aan te nemen:

- De broncode is openbaar: <https://github.com/VD171/VD-Google>.
- Het manifest staat in de broncode; bevestig dat het geen permissies declareert.
- Het Content-Security-Policy van de WebUI staat in de broncode; bevestig dat het elke
  externe oorsprong blokkeert.
- De meegeleverde binaries zijn met checksums gedocumenteerd in
  [Meegeleverde binaries](BINARIES.md).
