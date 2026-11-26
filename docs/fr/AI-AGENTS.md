> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · Français · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Agents d'IA

VD Google transforme les propres relevés de Google sur l'appareil en quelque chose qu'un **agent d'IA peut lire en une seule étape**. Au lieu d'ouvrir plusieurs applis et bases de données Google et d'en deviner le sens, un agent lit un unique **export** structuré et sait exactement ce que l'appareil rapporte sur le RCS, Google Wallet et Play Integrity.

Cette page développe l'idée présentée dans le README.

## L'export

L'application et la WebUI produisent le même export JSON :

- **Application** : l'action **Exporter** partage ou enregistre le rapport.
- **WebUI** : l'action **Exporter** l'écrit dans `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Les deux ont le même schéma, car l'application et la WebUI sont tenues identiques. Rien n'est envoyé nulle part : l'export est écrit sur l'appareil et y reste.

## Ce qu'il contient

L'export est un unique objet JSON avec ces clés de premier niveau :

- `app`, `version`, `exportedAt`, `timezone` - ce qui a produit le rapport et quand, dans le fuseau horaire étiqueté de l'appareil.
- `device` - fabricant, modèle, version d'Android et niveau de SDK.
- `rcs` - disponibilité, état, version de la configuration, contacts compatibles, autorisation de l'opérateur, présence (UCE) et les horodatages associés.
- `wallet` - attestation de paiement, matériel NFC, l'état du paiement sans contact, acceptation du verrouillage d'écran et si une carte a déjà été ajoutée.
- `diagnostics` - une section d'auto-vérification, conçue pour les rapports de bug.

L'export ne contient **aucune donnée personnelle** : pas de numéro de téléphone, pas d'identifiant, pas de contenu de message, pas de nom de contact. Chaque valeur est la lecture honnête - un vrai négatif reste négatif, un cache périmé est étiqueté comme en cache, une valeur absente est Inconnu.

## Lecture seule, avec une action journalisée

VD Google lit. La seule exception est une action d'assainissement (effacer le résultat d'attestation mis en cache de Google Wallet pour forcer une réévaluation). Toute action qui modifie l'état demande une confirmation sur l'appareil et est écrite dans un journal d'actions local en JSON. Un agent qui pilote la WebUI voit la même confirmation et le même journal, il n'existe aucun chemin caché ou non journalisé.

## Aucun contrat réseau

VD Google n'expose aucune API réseau ni socket : il ne déclare aucune permission. Un agent consomme le **fichier d'export**, il n'appelle aucun service. C'est la garantie de confidentialité, maintenue même pour l'automatisation.

<!-- doc-footer -->

---

> _Cette documentation a été générée avec Claude (Anthropic) et est maintenue par VD171. Les corrections et améliorations sont les bienvenues, voir [CONTRIBUTING.md](CONTRIBUTING.md)._
