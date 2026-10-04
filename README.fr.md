<!--
  VD GOOGLE - RCS, Google Wallet et attestations Play Integrity, lus sur l'appareil.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Vos services Google, lus sur votre appareil. Jamais d'Internet.**

VD Google vous montre ce que Google lui-même enregistre sur votre téléphone à propos de trois choses, et explique ce que signifie chaque élément, sans rien envoyer nulle part.

> **Langues :** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Ce qu'il affiche

### RCS
Si le RCS est disponible pour votre ligne, votre numéro RCS, l'état du RCS, la version de la configuration, combien de vos contacts prennent en charge le RCS, l'autorisation de l'opérateur, la présence (UCE) et les horodatages associés (provisionnement, synchronisation, dernier message, expiration du jeton, etc.).

### Google Wallet
Si votre appareil est attesté pour les paiements, s'il dispose du matériel NFC, si Wallet est installé, l'état du service de paiement et du paiement sans contact, si votre verrouillage d'écran est accepté, et si une carte a déjà été ajoutée.

### Attestations (Play Integrity)
Les applications qui ont récemment demandé à Google Play Integrity de se porter garant de votre appareil, chacune avec son icône, son nom, l'heure complète de sa dernière demande et le nombre de demandes effectuées dans la fenêtre actuelle.

Chaque élément est accompagné d'une brève explication, en langage clair, de ce qu'il signifie réellement.

## Confidentialité : pas d'Internet

VD Google ne déclare **aucune autorisation**, pas même l'accès à Internet. Rien de ce qu'il lit ne quitte jamais votre appareil. Vous pouvez le vérifier dans le manifeste et dans le code source.

## Prérequis

- Android 8.0 (API 26) ou plus récent.
- **Le root est requis.** Les informations ci-dessus se trouvent dans des zones protégées d'Android, accessibles uniquement au système. VD Google les lit avec un accès superutilisateur et n'en fait rien d'autre. Sans root, l'application affiche seulement un écran expliquant pourquoi le root est nécessaire.

## Horodatages

Toutes les heures sont affichées dans le fuseau horaire actuel de votre appareil, et le fuseau est toujours indiqué.

## Contacts

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Licence

**GNU AGPL-3.0-or-later** - voir [LICENSE](LICENSE). Copyleft, y compris la clause réseau : quiconque exécute une version modifiée (même comme service) doit en proposer le code source. Choisie délibérément pour garder les forks ouverts.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
