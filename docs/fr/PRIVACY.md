> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · Français · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Confidentialité

La confidentialité n'est pas une fonctionnalité de VD Google - c'est toute sa raison d'être. Cette page énonce exactement ce que l'application fait et ne fait pas de vos données, en termes clairs que vous pouvez vérifier.

## La promesse

- **Aucune permission internet n'est accordée.** Le manifeste de l'application ne déclare aucune permission - ni internet, ni stockage, ni contacts, rien. Il n'y a rien à autoriser.
- **Rien n'est stocké, envoyé ou transmis** vers un fichier ou un serveur en dehors de votre appareil.
- Chaque lecture se produit **localement, via le root**, et reste sur l'appareil.

## Pourquoi il a besoin du root plutôt que de permissions

L'état du RCS, de Wallet et des attestations que VD Google explique réside dans des zones protégées d'Android, réservées au système, que les applications normales ne peuvent pas voir. VD Google les lit avec le root plutôt qu'en vous demandant de larges permissions. Le root est la voie d'accès étroite et auditable ; un amas de permissions d'exécution serait la voie large et poreuse. L'application choisit la voie étroite.

## L'export est assaini

Lorsque vous exportez un rapport, VD Google l'assainit avant de l'écrire :

- **Jamais inclus :** votre numéro de téléphone, tout identifiant, cookie ou jeton, le contenu des messages, les noms de contacts, ou les blobs de capacités bruts.
- **Masqué :** les longues suites de chiffres et les valeurs qui ressemblent à des secrets sont remplacées par un espace réservé.
- **Les diagnostics** ne contiennent que la structure (noms de schémas, décomptes, tailles, versions). Toute empreinte propre à une identité qui se trouverait à l'intérieur d'un nom est réduite à néant.

Le rapport est le vôtre. Il est écrit à l'endroit que vous choisissez, il n'est partagé que si vous le partagez, et il ne contient rien qui vous identifie.

## Les actions sont explicites et consignées

VD Google peut effacer le résultat d'attestation mis en cache par Google Wallet, afin qu'une future revérification reparte de zéro. Toute action qui modifie l'état :

1. vous demande d'abord de confirmer, et
2. est écrite dans un journal JSON local sur l'appareil.

Aucune action n'atteint jamais le réseau, et rien ne quitte votre téléphone.

## Vérifiez-le vous-même

Vous n'avez à rien prendre pour argent comptant :

- Le code source est public : <https://github.com/VD171/VD-Google>.
- Le manifeste est dans le code source ; confirmez qu'il ne déclare aucune permission.
- La Content-Security-Policy de la WebUI est dans le code source ; confirmez qu'elle bloque toute origine distante.
- Les binaires embarqués sont documentés avec leurs sommes de contrôle dans [Binaires embarqués](BINARIES.md).
