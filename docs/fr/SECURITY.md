> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · Français · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Politique de sécurité

VD Google est un outil de confidentialité. Il s'exécute en root et lit un état sensible ; les rapports de sécurité sont donc pris au sérieux.

## Signaler une vulnérabilité

Veuillez signaler les problèmes de sécurité **en privé**, non pas dans un ticket public :

- Écrivez à **vd.priv8@pm.me**.
- Décrivez le problème, la version concernée, et comment le reproduire.
- S'il concerne une exposition de données, décrivez la catégorie de problème ; vous n'avez pas besoin de joindre un exploit fonctionnel.

Vous recevrez une réponse dès que possible. Veuillez accorder un délai raisonnable pour corriger le problème avant toute divulgation publique.

## Ce qui constitue un problème de sécurité

En raison de ce que VD Google promet, les points suivants sont particulièrement importants :

- Tout ce qui ferait que l'application atteint le réseau, ou envoie des données hors de l'appareil.
- Tout ce qui ferait fuir des données personnelles dans un **export** (un numéro de téléphone, un identifiant, le contenu d'un message, un nom de contact, ou une empreinte propre à une identité).
- Tout ce qui permettrait à l'unique action d'assainissement de modifier l'état sans confirmation ou sans être consignée.

## Ce qui ne constitue pas un problème de sécurité

- Une lecture qui semble « fausse » mais qui est l'honnête vérité (par exemple, des lignes RCS côté opérateur affichant Non alors que le RCS passe par le backend de Google). Consultez le [guide de l'utilisateur](USER-GUIDE.md).
- L'absence d'un verdict Play Integrity. Ce verdict ne se trouve pas sur l'appareil et VD Google ne l'inventera pas.

## Versions prises en charge

Seule la dernière version publiée est prise en charge. Veuillez reproduire le problème sur la dernière compilation avant de le signaler.
