> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../id/BINARIES.md) · Italiano · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Binari inclusi: provenienza e verifica

VD Google promette che niente di opaco viene distribuito al suo interno. Gira come root e
legge il tuo stato più privato, quindi devi poter vedere esattamente cosa trasporta. Questa
pagina elenca l'unico strumento nativo che l'app e il modulo includono, da dove proviene e
come verificarlo.

## Principio

VD Google include esattamente uno strumento nativo: un comando `sqlite3`, con il piccolo
insieme di librerie condivise di cui ha bisogno. È necessario perché il SQLite del framework
di Android non riesce ad aprire i database di altre app su alcune ROM dei produttori, il che
farebbe sì che VD Google non legga nulla su quei dispositivi. Vedi
[Architettura](ARCHITECTURE.md) per il perché.

- **Preso dall'upstream, mai da un dispositivo.** `sqlite3` e le sue librerie provengono dal
  repository ufficiale dei pacchetti **Termux** (`packages.termux.dev/apt`), i pacchetti
  `.deb` firmati, mai copiati da un Termux installato su qualche telefono.
- **Gli stessi byte ovunque.** I file identici sono distribuiti nell'app (sotto
  `assets/bin/`) e nel modulo WebUI (sotto `bin/`), così entrambi leggono tramite un unico
  motore e concordano.
- **Il checksum è l'identità.** Qualunque percorso una copia occupi, è lo strumento reale
  solo se il suo SHA-256 corrisponde al valore registrato.
- **Una traccia di audit registrata.** Ogni directory ABI porta un `PROVENANCE.json` che
  elenca ciascun pacchetto sorgente (nome, versione, URL, SHA-256 del `.deb`) e lo SHA-256 di
  ogni file distribuito. Niente viene incluso senza una voce che puoi controllare.

## Origine

`.github/scripts/build_bin.sh` assembla il payload in fase di build, senza alcun compilatore
e senza NDK. Per ciascuna ABI:

1. legge l'indice dei pacchetti Termux e risolve il pacchetto `sqlite` più le sue dipendenze
   transitive (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. scarica ed estrae i file `.deb` ufficiali firmati;
3. copia `sqlite3`, poi copia solo la chiusura delle librerie condivise di cui il binario ha
   effettivamente bisogno (risolta con `readelf`), saltando le librerie che Android fornisce
   sempre da sé;
4. scrive `PROVENANCE.json`.

I binari di Termux sono collegati dinamicamente alle librerie proprie di Termux, quindi VD
Google distribuisce quella chiusura insieme a `sqlite3` e lo esegue con `LD_LIBRARY_PATH` che
punta alla directory `lib/` inclusa. Termux usa soname versionati (`libz.so.1`,
`libreadline.so.8`, `libncursesw.so.6`), così non vanno mai in conflitto con le librerie
proprie del framework Android. Il `LD_LIBRARY_PATH` è impostato solo per il processo
`sqlite3`, mai globalmente.

## I file

Shell a riga di comando **SQLite** upstream, pacchetto Termux **sqlite 3.54.1-1**. Un payload
per ABI: il binario `sqlite3` più la sua chiusura di quattro librerie
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Ogni file
distribuito, con il suo SHA-256 e la sua dimensione:

`arm64-v8a`:

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a`:

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

`x86`:

```
9b936d8539634249836b7bfb1b51b68f108f94b5cf3b9e02bab8cbb4a222250c  sqlite3  (1637296 bytes)
b1004239a469cb492b683f28f1b59d525cb40715be6bcb51bf86c3351c0b59d8  lib/libandroid-support.so  (19656 bytes)
e3a0c521443635effaf29bbd3387b080e960b86fea2a50c2c49cd74ead637079  lib/libncursesw.so.6  (357980 bytes)
026876da62e303293d2ea3e33f14231964a02a12a93b076938d9589392323b73  lib/libreadline.so.8  (268876 bytes)
1b5e10b598b40dedac5c51636067c1f94f8b83b643912bc5ad2b8d0b15d7552f  lib/libz.so.1  (77892 bytes)
```

`x86_64`:

```
70ef3fff45e735859d139fb893300cdf7d4cf371030c7d2e6fc3dc737de0642c  sqlite3  (1612376 bytes)
f0f44e60090b07b3536729993fad94bb6127ff1c6f70188f5c6dab6a33b85dda  lib/libandroid-support.so  (20640 bytes)
79789fd21d13b82fa87d6373382c815b3d97838962351ab3ec2f7f77edf79c09  lib/libncursesw.so.6  (384896 bytes)
f8b0eb7ef121241c400824c0cc59e1eb2c02ca8901d526d428d41ee20fc40a13  lib/libreadline.so.8  (308584 bytes)
9ac487f65c341194dd285b5cdb5464190552813800d78379953a45023d5b2a34  lib/libz.so.1  (82824 bytes)
```

Gli stessi checksum sono registrati in ciascun `bin/<abi>/PROVENANCE.json`, che è distribuito
accanto al binario.

## Verificalo tu stesso

Da un checkout del sorgente, verifica ogni file distribuito:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Ogni hash deve corrispondere all'elenco qui sopra, e le copie dell'app e della WebUI devono
corrispondere tra loro. Per verificare l'intero payload da capo a fondo, riscarica gli URL
dei `.deb` fissati elencati in ciascun `PROVENANCE.json`, estraili e confronta i valori
SHA-256 dei file.

## Integrità al momento dell'installazione

Il modulo WebUI verifica sé stesso durante l'installazione. Ogni file incluso è distribuito
accanto a un compagno `.sha256`, e `customize.sh` ne verifica ciascuno prima di toccare il
sistema, se un file è stato corrotto o lo `.zip` è stato troncato durante il trasferimento,
l'installazione viene interrotta con un messaggio chiaro invece di installare un payload
danneggiato. Il controllo viene saltato in modo sicuro sul raro dispositivo il cui recovery
non dispone di un `sha256sum` che comprenda `-c`.

## A cosa serve

`sqlite3` viene eseguito come root, contro una copia di un database messa da parte,
puramente per **leggere**. Non viene mai usato per scrivere su alcun database di Google. Il
suo unico compito è permettere a VD Google di leggere gli stessi valori su ogni dispositivo.
Nessun altro binario nativo è incluso.

<!-- doc-footer -->

---

> _Questa documentazione è stata generata con Claude (Anthropic) ed è gestita da VD171. Correzioni e miglioramenti sono benvenuti, vedi [CONTRIBUTING.md](CONTRIBUTING.md)._
