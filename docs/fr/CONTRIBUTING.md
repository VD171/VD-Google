> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · Français · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Contribuer

Merci de l'intérêt que vous portez à VD Google. Les contributions sont les bienvenues : traductions, rapports de bugs, documentation et code.

## Règles de base

- **VD Google lit la vérité et ne falsifie jamais rien.** Les contributions doivent préserver cela. Un vrai négatif reste négatif ; un cache périmé est signalé comme mis en cache ; une valeur absente est Inconnu. N'adoucissez pas une lecture pour la rendre plus jolie.
- **La confidentialité est absolue.** L'application ne déclare aucune permission et la WebUI ne contacte aucune origine distante. N'ajoutez jamais de permission, d'appel réseau, ni quoi que ce soit qui envoie des données hors de l'appareil.
- **L'application et la WebUI sont identiques.** Tout changement de ce qui est affiché, ou du rapport exporté, doit arriver dans les deux, avec la même formulation. Elles ne doivent jamais diverger.
- **Les libellés vivent une seule fois, en 21 langues.** Chaque chaîne destinée à l'utilisateur est définie pour l'application (`res/values*/strings.xml`) et la WebUI (`webroot/lang/`). Si vous ajoutez ou modifiez un libellé, mettez à jour toutes les langues.

## Traductions

Les traductions sont la contribution la plus facile et la plus précieuse. Le readme est traduit par langue (`README.<lang>.md`), les chaînes de l'application et de la WebUI par langue, et cette documentation sous `docs/<lang>/`. Gardez une terminologie cohérente au sein d'une langue, respectez le ton existant, et ne traduisez jamais automatiquement un terme technique dont vous n'êtes pas sûr.

## Signaler un bug

Ouvrez un ticket avec le modèle de votre appareil, la version d'Android, et, s'il s'agit d'une lecture, un **export** (il ne contient aucune donnée personnelle). La section de diagnostics de l'export est faite exactement pour cela.

## Code

- Compilez en release, jamais en debug. Testez sur un véritable appareil rooté.
- Ne laissez pas de commentaires dans le code ; les noms expliquent l'intention. Le seul en-tête est la bannière de licence en haut de chaque fichier source.
- Respectez le style existant et gardez vos changements ciblés.

## Licence

En contribuant, vous acceptez que votre contribution soit distribuée sous la licence **GNU AGPL-3.0-or-later** du projet.

<!-- doc-footer -->

---

> _Cette documentation a été générée avec Claude (Anthropic) et est maintenue par VD171. Les corrections et améliorations sont les bienvenues ; voir [CONTRIBUTING.md](CONTRIBUTING.md)._
