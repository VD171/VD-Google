> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · Français · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Binaires embarqués : provenance et audit

VD Google promet que rien d'opaque n'est livré en son sein. Il s'exécute en root et lit votre état le plus privé ; vous devez donc pouvoir voir exactement ce qu'il transporte. Cette page énumère l'unique outil natif que l'application et le module embarquent, d'où il provient, et comment le vérifier.

## Principe

VD Google embarque exactement un outil natif : une commande `sqlite3`, accompagnée du petit ensemble de bibliothèques partagées dont elle a besoin. Il est requis parce que le SQLite du framework Android échoue à ouvrir les bases de données d'autres applications sur certaines ROM de fabricants, ce qui ferait que VD Google ne lirait rien sur ces appareils. Voyez [Architecture](ARCHITECTURE.md) pour en connaître la raison.

- **Récupéré depuis l'amont, jamais depuis un appareil.** `sqlite3` et ses bibliothèques proviennent du dépôt de paquets officiel de **Termux** (`packages.termux.dev/apt`), les paquets `.deb` signés, jamais copiés depuis un Termux installé sur un quelconque téléphone.
- **Les mêmes octets partout.** Les fichiers identiques sont livrés dans l'application (sous `assets/bin/`) et dans le module WebUI (sous `bin/`), de sorte que les deux lisent avec un seul moteur et concordent.
- **La somme de contrôle est l'identité.** Quel que soit le chemin où se trouve une copie, ce n'est le véritable outil que si son SHA-256 correspond à la valeur enregistrée.
- **Une piste d'audit consignée.** Chaque répertoire d'ABI porte un `PROVENANCE.json` qui liste chaque paquet source (nom, version, URL, SHA-256 du `.deb`) et le SHA-256 de chaque fichier livré. Rien n'est embarqué sans une entrée que vous pouvez vérifier.

## Origine

`.github/scripts/build_bin.sh` assemble la charge utile au moment de la compilation, sans aucun compilateur ni NDK. Pour chaque ABI, il :

1. lit l'index de Termux Packages et résout le paquet `sqlite` ainsi que ses dépendances transitives (`readline`, `ncurses`, `zlib`, `libandroid-support`) ;
2. télécharge et extrait les fichiers `.deb` officiels signés ;
3. copie `sqlite3`, puis copie uniquement la fermeture de bibliothèques partagées dont le binaire a réellement besoin (résolue avec `readelf`), en ignorant les bibliothèques qu'Android fournit toujours lui-même ;
4. écrit `PROVENANCE.json`.

Les binaires de Termux sont liés dynamiquement aux bibliothèques propres à Termux ; VD Google livre donc cette fermeture aux côtés de `sqlite3` et l'exécute avec `LD_LIBRARY_PATH` pointant vers le répertoire `lib/` embarqué. Termux utilise des sonames versionnés (`libz.so.1`, `libreadline.so.8`, `libncursesw.so.6`), de sorte qu'ils n'entrent jamais en conflit avec les bibliothèques propres au framework Android. Le `LD_LIBRARY_PATH` n'est défini que pour le processus `sqlite3`, jamais globalement.

## Les fichiers

Shell en ligne de commande **SQLite** amont, paquet Termux **sqlite 3.54.1-1**. Une charge utile par ABI : le binaire `sqlite3` plus sa fermeture de quatre bibliothèques (`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Chaque fichier livré, avec son SHA-256 et sa taille :

`arm64-v8a` :

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a` :

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

Les mêmes sommes de contrôle sont enregistrées dans chaque `bin/<abi>/PROVENANCE.json`, qui est livré à côté du binaire.

## Vérifiez-le vous-même

Depuis une copie locale du code source, confirmez chaque fichier livré :

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Chaque empreinte doit correspondre à la liste ci-dessus, et les copies de l'application et de la WebUI doivent correspondre entre elles. Pour auditer l'ensemble de la charge utile de bout en bout, récupérez de nouveau les URL `.deb` épinglées listées dans chaque `PROVENANCE.json`, extrayez-les, et comparez les valeurs SHA-256 des fichiers.

## À quoi il sert

`sqlite3` est exécuté en root, sur une copie d'une base de données prise à part, uniquement pour **lire**. Il n'est jamais utilisé pour écrire dans une quelconque base de données de Google. Son seul rôle est de permettre à VD Google de lire les mêmes valeurs sur tous les appareils. Aucun autre binaire natif n'est embarqué.
