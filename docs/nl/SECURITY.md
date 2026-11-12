> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · Nederlands · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Beveiligingsbeleid

VD Google is een privacytool. Ze draait als root en leest gevoelige status, dus
beveiligingsmeldingen worden serieus genomen.

## Een kwetsbaarheid melden

Meld beveiligingsproblemen alsjeblieft **privaat**, niet in een openbare issue:

- E-mail **vd.priv8@pm.me**.
- Beschrijf het probleem, de getroffen versie, en hoe je het reproduceert.
- Als het om gegevensblootstelling gaat, beschrijf de klasse van het probleem; je hoeft geen
  werkende exploit mee te sturen.

Je krijgt zo snel mogelijk een reactie. Geef alsjeblieft een redelijk venster om het
probleem op te lossen voordat er iets openbaar wordt gemaakt.

## Wat telt als een beveiligingsprobleem

Vanwege wat VD Google belooft, zijn deze bijzonder belangrijk:

- Alles wat de app het netwerk zou laten bereiken, of enige gegevens van het toestel zou
  laten versturen.
- Alles wat persoonlijke gegevens zou laten lekken in een **export** (een telefoonnummer,
  inloggegevens, berichtinhoud, contactnaam, of een per-identiteit-vingerafdruk).
- Alles wat de enige opschonende actie de status laat wijzigen zonder bevestiging of zonder
  gelogd te worden.

## Wat geen beveiligingsprobleem is

- Een aflezing die er "verkeerd" uitziet maar de eerlijke waarheid is (bijvoorbeeld
  provider-zijde RCS-regels die No lezen terwijl RCS op Googles backend draait). Zie de
  [gebruikershandleiding](USER-GUIDE.md).
- De afwezigheid van een Play Integrity-oordeel. Dat oordeel staat niet op het toestel en VD
  Google zal het niet verzinnen.

## Ondersteunde versies

Alleen de laatst uitgebrachte versie wordt ondersteund. Reproduceer alsjeblieft op de
nieuwste build voordat je meldt.
