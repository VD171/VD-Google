<!--
  VD GOOGLE - RCS, Google Wallet et attestations Play Integrity, lus sur l'appareil.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

<p align="center">
  <img src="images/logo-wordmark.png" alt="VD Google" width="320">
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases/latest"><img src="https://img.shields.io/github/v/release/VD171/VD-Google?label=release&color=0969da" alt="Release"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-AGPL--3.0--or--later-1a7f37" alt="License"></a>
  <a href="https://github.com/VD171/VD-Google/releases"><img src="https://img.shields.io/github/downloads/VD171/VD-Google/total?label=downloads&color=8250df" alt="Downloads"></a>
  <a href="https://github.com/VD171/VD-Google/stargazers"><img src="https://img.shields.io/github/stars/VD171/VD-Google?color=d29922" alt="Stars"></a>
</p>

<p align="center">
  <b>Vos services Google, lus sur votre appareil. Jamais d'Internet.</b><br>
  VD Google vous montre ce que Google lui-même enregistre sur votre téléphone à propos du RCS, de Wallet et de Play Integrity, et explique ce que signifie chaque élément, sans rien envoyer nulle part.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fr-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fr-USER-GUIDE">Guide d'utilisation</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fr-ARCHITECTURE">Architecture</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fr-PRIVACY">Confidentialité</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fr-BINARIES">Binaires inclus</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/fr-FILE-STRUCTURE">Structure des fichiers</a> ·
  <a href="CHANGELOG.md">Changelog</a> ·
  <a href="https://xdaforums.com/t/VD-Google.4803837/">XDA thread</a>
</p>

> **Languages:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Українська](README.uk.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<p align="center">
  <img src="images/vdgoogle-01.png" height="420"/>
  <img src="images/vdgoogle-02.png" height="420"/>
  <img src="images/vdgoogle-03.png" height="420"/>
  <img src="images/vdgoogle-04.png" height="420"/>
  <img src="images/vdgoogle-05.png" height="420"/>
</p>

---

## App ou WebUI

Chaque version existe sous deux formes, et les deux lisent les mêmes informations sur votre appareil, sans Internet :

- **App** - le `.apk`, une application Android classique.
- **WebUI** - le `.zip`, un module que vous flashez dans votre gestionnaire root (KernelSU, Magisk ou APatch) et que vous ouvrez depuis celui-ci.

Dans les deux cas, le root est requis.

## Ce qu'il affiche

### RCS
Si le RCS est disponible pour votre ligne, votre numéro RCS, l'état du RCS, la version de la configuration, combien de vos contacts prennent en charge le RCS, l'autorisation de l'opérateur, la présence (UCE) et les horodatages associés (provisionnement, synchronisation, dernier message, expiration du jeton, etc.). Indique aussi si Gemini dans Google Messages est prêt : Magic Compose (l'aide à la rédaction de Gemini), le modèle sur l'appareil (AICore, qui exécute Gemini Nano) et le chat Gemini, qui passe par le RCS.

### Google Wallet
Si votre appareil est attesté pour les paiements, s'il dispose du matériel NFC, si Wallet est installé, l'état du service de paiement et du paiement sans contact, si votre verrouillage d'écran est accepté, et si une carte a déjà été ajoutée.

### DroidGuard
Si Google Play reconnaît cet appareil comme certifié. C'est la certification Play Protect affichée dans le Play Store. Attestations que cet appareil a mises en cache pour Play Integrity et DroidGuard. L'heure indiquée est celle de leur expiration.

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

## Documentation

- **[Wiki](https://github.com/VD171/VD-Google/wiki/fr-Home)**: toute la documentation, consultable en ligne.
- **[Guide d'utilisation](https://github.com/VD171/VD-Google/wiki/fr-USER-GUIDE)**: ce que chaque écran affiche et ce que signifie chaque résultat.
- **[Architecture](https://github.com/VD171/VD-Google/wiki/fr-ARCHITECTURE)** et **[Structure des fichiers](https://github.com/VD171/VD-Google/wiki/fr-FILE-STRUCTURE)**: comment ça fonctionne et où se trouve chaque chose.
- **[Confidentialité](https://github.com/VD171/VD-Google/wiki/fr-PRIVACY)**: aucune autorisation d'accès à Internet, chaque lecture passe par le root, rien ne quitte jamais l'appareil.
- **[Binaires inclus](https://github.com/VD171/VD-Google/wiki/fr-BINARIES)**: provenance et SHA-256 de chaque binaire précompilé, pour un audit complet.
- **[Contribuer](https://github.com/VD171/VD-Google/wiki/fr-CONTRIBUTING)** et **[Sécurité](https://github.com/VD171/VD-Google/wiki/fr-SECURITY)**.
- **[Changelog](CHANGELOG.md)** et **[Code de conduite](https://github.com/VD171/VD-Google/wiki/fr-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Voir aussi

Projets frères du même auteur :

- **[VDInfos](https://github.com/VD171/VD-Infos)**: un débogueur de méthodes de l'appareil qui lit chaque élément via toutes les méthodes capables de le lire et les compare côte à côte.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: sauvegarde et restauration complètes des applications et des données en root, pilotées entièrement depuis une WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: un usurpateur d'appareil qui transforme une empreinte neuve en un profil cohérent et constant.

## Contacts

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Licence

**GNU AGPL-3.0-or-later** - voir [LICENSE](LICENSE). Copyleft, y compris la clause réseau : quiconque exécute une version modifiée (même comme service) doit en proposer le code source. Choisie délibérément pour garder les forks ouverts.

## Marques

VD Google est un projet indépendant et non officiel. Il n'est ni affilié à Google LLC, ni approuvé ou sponsorisé par elle. « Google », « Google Wallet », « Google Play », « Play Integrity », « Android » ainsi que les noms et logos associés sont des marques de Google LLC. VD Google lit uniquement ce qui se trouve déjà sur votre appareil et n'envoie rien nulle part.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
