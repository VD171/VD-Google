> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · Français · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Guide de l'utilisateur de VD Google

Une présentation conviviale et complète de VD Google : ce qu'il affiche, ce que signifie chaque ligne, et comment lire votre appareil honnêtement. VD Google lit l'état du RCS, de Google Wallet et des attestations Play Integrity que les applications demandent, et explique chaque élément en mots simples. Il ne touche jamais à internet, et il ne modifie jamais rien à moins que vous ne le demandiez.

## Table des matières

- [1. Ce qu'est VD Google](#1-ce-quest-vd-google)
- [2. Ce qu'il n'est pas](#2-ce-quil-nest-pas)
- [3. Prérequis](#3-prérequis)
- [4. Application ou WebUI](#4-application-ou-webui)
- [5. Installation et premier lancement](#5-installation-et-premier-lancement)
- [6. Les onglets](#6-les-onglets)
  - [6.1 À propos](#61-à-propos)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Attestations](#65-attestations)
- [7. Exporter votre rapport](#7-exporter-votre-rapport)
- [8. Les actions qui modifient l'état](#8-les-actions-qui-modifient-letat)
- [9. Langue et thème](#9-langue-et-thème)
- [10. Foire aux questions](#10-foire-aux-questions)

## 1. Ce qu'est VD Google

VD Google est un lecteur. Il examine ce que les applications de Google elles-mêmes stockent déjà sur votre appareil à propos de trois choses, et il explique chacune d'elles pour que vous la compreniez réellement :

- **RCS** - si la messagerie moderne fonctionne réellement sur votre ligne, avec qui vous pouvez discuter, et si Gemini dans Google Messages est prêt.
- **Google Wallet** - si votre appareil est configuré et attesté pour les paiements sans contact.
- **Attestations Play Integrity** - quelles applications ont demandé à Android de prouver que votre appareil est authentique, et si Google le certifie actuellement.

Tout est lu localement, via le root, et affiché avec une explication. Aucune valeur n'est inventée : lorsque l'appareil ne détient pas de réponse, VD Google affiche « Inconnu » plutôt que de deviner.

## 2. Ce qu'il n'est pas

**VD Google n'est pas un vérificateur d'intégrité.** Il n'exécute ni ne rapporte aucun verdict basic / device / strong. Ce résultat ne vit qu'à l'intérieur du jeton chiffré qu'une application envoie à son propre serveur, et n'est jamais stocké sur l'appareil ; aucun lecteur honnête ne peut donc l'afficher. Les outils qui prétendent afficher votre « verdict Play Integrity » localement vous montrent une supposition mise en cache ou une requête qu'ils ont faite eux-mêmes, non pas la vraie réponse.

VD Google n'usurpe, ne masque ni ne falsifie non plus quoi que ce soit. Il lit la vérité. Si une valeur vous semble fausse, l'objectif de ce guide est d'expliquer pourquoi la vérité se présente ainsi.

## 3. Prérequis

- **Android 8.0 (API 26) ou plus récent.**
- **Root.** Les données que lit VD Google résident dans des zones protégées d'Android, réservées au système, accessibles uniquement avec le root. Toute solution de root moderne convient : KernelSU, KernelSU Next, Magisk ou APatch.
- **Pas d'internet.** VD Google ne déclare aucune permission, il n'y a donc rien à autoriser et rien à bloquer.

## 4. Application ou WebUI

VD Google se décline de deux façons, et elles affichent exactement les mêmes informations :

- **L'application (APK).** Une application Android normale que vous installez et ouvrez. Elle demande le root la première fois, comme toute application root.
- **Le module WebUI (ZIP).** Flashez-le dans votre gestionnaire de root (KernelSU, Magisk ou APatch) et ouvrez-le depuis là. Rien n'est installé comme application ; il s'exécute à l'intérieur de la WebUI de votre gestionnaire de root.

Choisissez celle que vous préférez. Les écrans, les formulations et le rapport exporté sont identiques entre les deux.

## 5. Installation et premier lancement

**Application :** installez l'APK et ouvrez-la. La première fois, accordez le root lorsque votre gestionnaire de root le demande. Si vous refusez ou ignorez le root, VD Google affiche un écran expliquant pourquoi le root est nécessaire et propose de réessayer.

**WebUI :** flashez le ZIP comme module dans votre gestionnaire de root, puis ouvrez la WebUI du module depuis le gestionnaire. Il lit dès qu'il s'ouvre.

À la première ouverture, vous pouvez choisir votre langue ; vous pourrez la changer plus tard depuis l'icône d'engrenage en haut.

## 6. Les onglets

Au bas de l'écran se trouvent cinq onglets : À propos, DroidGuard, RCS, Wallet et Attestations. À propos s'ouvre en premier.

### 6.1 À propos

Ce qu'est l'application, la promesse de confidentialité, la version et les liens. Lisez une fois la section sur la confidentialité : c'est toute la raison d'être de l'application.

### 6.2 RCS

C'est l'onglet le plus riche. Les horodatages sont toujours affichés dans le fuseau horaire de votre appareil, avec le fuseau indiqué.

- **Connexion** - l'unique ligne qui dit la vérité. Elle est calculée à partir de la configuration de chat réelle et du moteur de provisionnement, de sorte qu'elle indique Connecté lorsque le RCS fonctionne réellement sur votre ligne, même sur les versions plus récentes d'Android où les anciens indicateurs mentent. **En cas de doute, fiez-vous à cette ligne.**
- **Opérateur** - l'opérateur mobile de votre ligne (nom et MCC/MNC), pris sur votre SIM de données sur les téléphones à double SIM.
- **Indicateur de disponibilité (en cache)** - un indicateur mis en cache que l'application de messagerie conserve pour votre propre numéro. Il peut rester obsolète après que le RCS fonctionne déjà, donc un « Non » ici ne signifie pas que le RCS est désactivé. Fiez-vous à la ligne Connexion ci-dessus.
- **Votre numéro RCS** - le numéro enregistré pour le RCS, masqué par défaut. Touchez l'œil pour le révéler.
- **État de provisionnement (en cache)** et **Version de configuration** - des valeurs internes que l'application de messagerie conserve. Elles peuvent afficher 0 alors même que le RCS fonctionne. Voyez la note sur l'opérateur vs le backend de Google ci-dessous.
- **Contacts compatibles RCS** - combien de vos contacts sont actuellement connus pour prendre en charge le chat RCS.
- **Indicateur de fonctionnalité (en cache)** - si l'application de messagerie a la fonctionnalité RCS activée ; mis en cache, il peut donc être en retard.
- **Éligibilité opérateur**, **Présence (UCE)**, **Service IMS présent** - ceux-ci décrivent le chemin RCS de l'*opérateur* (le réseau IMS de l'opérateur). Un « Non » ou « 0 » ici est normal lorsque votre RCS passe par le backend de Google au lieu de votre opérateur. Voir ci-dessous.
- **Gemini** - si Gemini dans Google Messages est prêt : **Magic Compose** (l'aide à la rédaction de Gemini), **AICore** (le moteur sur l'appareil qui exécute Gemini Nano), et **Gemini chat**, qui est acheminé via le RCS et nécessite donc une connexion RCS.
- **Horodatages** - provisionnement, synchronisation, dernier message, expiration du jeton, et plus encore.

**RCS de l'opérateur vs backend de Google.** Il y a deux façons dont le RCS parvient à votre téléphone. L'une est le RCS de votre *opérateur* sur son réseau IMS ; l'autre est le *backend de Google* (Jibe), qui fonctionne par-dessus, indépendamment de l'opérateur. Si votre RCS passe par le backend de Google - comme c'est le cas pour la plupart des gens - alors les lignes côté opérateur (éligibilité opérateur, présence/UCE, service IMS, version de configuration) sont véritablement « Non » ou « 0 », et c'est correct, non pas un cache périmé. Votre RCS a pu fonctionner ainsi pendant des années. La ligne Connexion est ce qui vous dit qu'il fonctionne.

### 6.3 Wallet

- **Appareil attesté** - si Google a jugé cet appareil authentique et suffisamment sûr pour héberger des cartes de paiement. C'est le dernier résultat que Google a enregistré sur cet appareil, il peut donc être en retard sur l'application Wallet jusqu'à ce que Google revérifie. Si le résultat enregistré est absent, VD Google affiche **Inconnu** plutôt qu'un « Non » trompeur.
- **Dernière vérification** - quand ce contrôle de sécurité s'est terminé pour la dernière fois.
- **Matériel NFC**, **Wallet installé**, **Service Wallet**, **Paiement sans contact**, **Verrouillage d'écran accepté**, **Carte déjà ajoutée** - les éléments qui rendent le paiement sans contact possible.

### 6.4 DroidGuard

- **Certifié Play Protect** - si Google Play reconnaît votre appareil comme certifié (la certification affichée dans le Play Store).
- **Cache d'attestations** - les attestations Play Integrity et DroidGuard que votre appareil a mises en cache, chacune avec sa date d'expiration.

### 6.5 Attestations

L'historique des applications qui ont demandé Play Integrity sur votre appareil : icône, nom, date de la dernière demande, et nombre de fois. Cela vous permet de savoir si une application donnée vérifie l'intégrité ou non. Utilisez le champ de recherche pour filtrer par nom d'application ou par paquet.

## 7. Exporter votre rapport

L'icône de téléchargement écrit un rapport **JSON** contenant l'état de votre appareil, du RCS, de Wallet et des attestations. Vous pouvez le partager ou l'enregistrer comme fichier. Le rapport ne contient **aucun numéro de téléphone, aucun identifiant et aucun contenu personnel** - il est assaini avant d'être écrit. Il inclut aussi une section facultative de **diagnostics** (noms de schémas, décomptes et versions, jamais vos données) qui aide à résoudre les problèmes propres à un appareil. L'application et la WebUI écrivent le même rapport.

## 8. Les actions qui modifient l'état

VD Google est essentiellement en lecture seule, mais il peut effectuer une **action d'assainissement** : effacer le résultat d'attestation mis en cache par Google Wallet, ce qui permet à une future revérification de repartir de zéro. Toute action qui modifie l'état vous demande d'abord de confirmer, et est écrite dans un journal JSON local sur l'appareil. Rien ne quitte votre téléphone.

## 9. Langue et thème

VD Google parle 21 langues. Choisissez la vôtre au premier lancement, ou changez-la à tout moment depuis l'icône d'engrenage (également disponible sur l'écran de root refusé). L'icône de luminosité bascule entre le thème clair et le thème sombre.

## 10. Foire aux questions

**Le RCS indique Connecté mais plusieurs lignes indiquent Non ou 0. Pourquoi ?** Parce que votre RCS passe par le backend de Google, non pas par votre opérateur. Les lignes côté opérateur sont correctement négatives. Voir [6.2 RCS](#62-rcs).

**Appareil attesté indique Inconnu.** Le résultat Wallet mis en cache est absent sur votre appareil. C'est honnête - VD Google n'affichera pas un « Non » qu'il ne peut pas étayer.

**Où est mon verdict Play Integrity ?** Il n'est pas sur votre appareil. Voir [2. Ce qu'il n'est pas](#2-ce-quil-nest-pas).

**Fonctionne-t-il sans root ?** Non. Les données résident dans des zones réservées au système.
