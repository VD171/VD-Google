> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · Français · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Architecture

Comment VD Google est conçu, et pourquoi. Cette page explique la conception et les principes. Elle n'énumère pas les fichiers, tables ou requêtes exacts qu'utilise chaque lecteur - cela ne vit que dans le code source, à dessein, afin que l'application et la documentation ne puissent jamais dériver vers un mode d'emploi pour quelqu'un qui voudrait falsifier ces mêmes valeurs.

## Objectifs de conception

1. **Lire la vérité.** Montrer ce que les applications de Google elles-mêmes ont déjà enregistré, l'expliquer, et ne jamais inventer de valeur. Lorsque l'appareil ne détient aucune réponse, afficher Inconnu.
2. **Zéro réseau.** L'application ne déclare aucune permission ; la WebUI bloque toute origine distante. Rien n'est jamais envoyé où que ce soit.
3. **Un seul comportement, deux visages.** L'application et le module WebUI sont tenus absolument identiques - mêmes signaux, mêmes formulations, même rapport exporté.
4. **Honnête plutôt que flatteur.** Un vrai négatif est affiché comme négatif ; un cache périmé est signalé comme mis en cache ; une valeur absente est Inconnu. La détection n'est jamais adoucie pour faire plus joli.

## Les deux visages

- **L'application** est écrite en Kotlin avec Jetpack Compose (Material 3). Elle obtient le root via le `RootService` de libsu, jamais en appelant directement `su`, et elle effectue ses lectures à l'intérieur de ce service privilégié.
- **Le module WebUI** est un petit web root HTML/JS servi par votre gestionnaire de root (KernelSU, Magisk ou APatch). Il effectue les mêmes lectures via le pont root du gestionnaire. Une Content-Security-Policy stricte interdit toute origine distante, de sorte que la page ne peut, même en principe, appeler l'extérieur.

Les deux sont générés à partir d'un unique jeu de chaînes partagé en 21 langues, de sorte qu'un libellé n'est jamais traduit deux fois.

## Lire les bases de données : le sqlite3 embarqué

Une partie de l'état que lit VD Google réside dans des bases de données SQLite appartenant aux applications de Google. Android fournit son propre SQLite, mais le SQLite du framework n'est pas fiable pour ouvrir la base de données d'une autre application copiée à part : sur certaines ROM de fabricants, il échoue pour *chaque* base de données et ne renvoie rien. Pour lire de la même manière sur tous les appareils, VD Google embarque son propre binaire `sqlite3` (arm64 et arm32) et exécute les requêtes au travers de celui-ci en root. L'application et la WebUI utilisent le binaire identique, de sorte qu'elles lisent toujours avec le même moteur et obtiennent le même résultat. La provenance et les sommes de contrôle de ce binaire sont documentées dans [Binaires embarqués](BINARIES.md).

Les lectures copient la base de données à part avant de l'ouvrir, sans jamais toucher au fichier actif, et sans jamais copier l'index en mémoire partagée, qu'une base de données en cours d'exécution peut signaler comme corrompu.

## Le signal RCS

Le plus difficile, pour lire le RCS honnêtement, c'est que les indicateurs évidents mentent. Le simple indicateur « disponible », les indicateurs de fonctionnalités et l'état de provisionnement peuvent tous être négatifs sur une ligne où le RCS fonctionne parfaitement, parce qu'il s'agit de caches ou parce que la ligne passe par le backend de Google (Jibe) plutôt que par le réseau IMS de l'opérateur. VD Google calcule un unique verdict de **Connexion** à partir de la configuration de chat réelle et du moteur de provisionnement, qui reste correct d'une version d'Android et de Google Messages à l'autre, et présente les indicateurs trompeurs clairement signalés comme mis en cache, afin qu'ils informent sans induire en erreur. Consultez le [guide de l'utilisateur](USER-GUIDE.md) pour savoir ce que signifie chaque ligne.

## L'export

L'export est un instantané JSON assaini. Un assainisseur s'exécute avant toute écriture : il supprime les clés et valeurs qui ressemblent à des identifiants, masque les longues suites de chiffres, et n'émet jamais votre numéro de téléphone, le contenu des messages, les noms de contacts ou les blobs de capacités bruts. La section facultative de diagnostics ne contient que des noms de schémas, des décomptes, des tailles et des versions - et même là, toute empreinte propre à une identité intégrée dans un nom est réduite à néant. L'application et la WebUI produisent le même schéma.

## Ce que VD Google ne fait jamais

- Il ne déclare jamais de permission réseau, et la WebUI ne contacte jamais d'origine distante.
- Il ne fabrique, n'usurpe ni ne masque jamais une valeur.
- Il ne rapporte jamais de verdict Play Integrity, car ce verdict ne se trouve pas sur l'appareil.
- Il ne modifie l'état qu'au travers d'une action explicite, confirmée et consignée localement.
