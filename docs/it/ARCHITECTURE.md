> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · Italiano · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Architettura

Come è costruito VD Google e perché. Questa pagina illustra il progetto e i principi. Non
elenca i file, le tabelle o le query esatte che ogni lettore usa: questo vive solo nel
codice sorgente, per scelta progettuale, affinché l'app e la documentazione non possano mai
scivolare in un manuale per chi vuole falsificare gli stessi valori.

## Obiettivi di progetto

1. **Leggere la verità.** Mostrare ciò che le app di Google hanno già registrato,
   spiegarlo e non inventare mai un valore. Quando il dispositivo non ha una risposta,
   dire Sconosciuto.
2. **Zero rete.** L'app non dichiara alcun permesso; la WebUI blocca ogni origine remota.
   Niente viene inviato da nessuna parte, mai.
3. **Un comportamento, due volti.** L'app e il modulo WebUI sono mantenuti assolutamente
   identici: stessi segnali, stessa formulazione, stesso report esportato.
4. **Onesti più che lusinghieri.** Un vero esito negativo è mostrato come negativo; una
   cache obsoleta è etichettata come memorizzata in cache; un valore mancante è
   Sconosciuto. Il rilevamento non viene mai ammorbidito per apparire più gradevole.

## I due volti

- **L'app** è in Kotlin con Jetpack Compose (Material 3). Ottiene i permessi di root
  tramite il `RootService` di libsu, mai invocando direttamente `su` dalla shell, e legge
  all'interno di quel servizio privilegiato.
- **Il modulo WebUI** è una piccola web root in HTML/JS servita dal tuo gestore di root
  (KernelSU, Magisk o APatch). Esegue le stesse letture attraverso il ponte root del
  gestore. Una rigida Content-Security-Policy vieta ogni origine remota, così la pagina
  non può telefonare a casa nemmeno in linea di principio.

Entrambi sono generati da un unico insieme condiviso di stringhe in 21 lingue, così
un'etichetta non viene mai tradotta due volte.

## Leggere i database: il sqlite3 incluso

Parte dello stato che VD Google legge risiede in database SQLite che appartengono alle app
di Google. Android include il proprio SQLite, ma il SQLite del framework è inaffidabile per
aprire la copia del database di un'altra app messa da parte: su alcune ROM dei produttori
fallisce per *ogni* database, restituendo nulla. Per leggere allo stesso modo su ogni
dispositivo, VD Google include il proprio binario `sqlite3` (arm64 e arm32) ed esegue le
query tramite esso come root. L'app e la WebUI usano il binario identico, così leggono
sempre lo stesso motore e lo stesso risultato. La provenienza e i checksum di quel binario
sono documentati in [Binari inclusi](BINARIES.md).

Le letture copiano il database da parte prima di aprirlo, senza mai toccare il file vivo, e
senza mai copiare l'indice di memoria condivisa, che un database in esecuzione può segnalare
come malformato.

## Il segnale RCS

La parte più difficile nel leggere l'RCS in modo onesto è che i flag ovvi mentono. Il
semplice flag "disponibile", i flag delle funzionalità e lo stato di provisioning possono
tutti risultare negativi su una linea in cui l'RCS funziona perfettamente, perché sono
cache o perché la linea gira sul backend di Google (Jibe) anziché sulla rete IMS
dell'operatore. VD Google calcola un unico verdetto di **Connessione** dalla reale
configurazione della chat e dal motore di provisioning, che resta corretto tra le versioni
di Android e di Google Messages, e presenta i flag bugiardi chiaramente etichettati come
memorizzati in cache, così informano senza ingannare. Vedi la
[guida utente](USER-GUIDE.md) per il significato di ogni riga.

## L'esportazione

L'esportazione è un'istantanea JSON sanificata. Un sanificatore viene eseguito prima che
qualsiasi cosa venga scritta: scarta chiavi e valori che somigliano a credenziali, oscura
le lunghe sequenze di cifre e non emette mai il tuo numero di telefono, il contenuto dei
messaggi, i nomi dei contatti o i blob grezzi di capacità. La sezione diagnostica opzionale
porta solo nomi di schema, conteggi, dimensioni e versioni - e anche lì, qualsiasi
impronta per-identità incorporata in un nome viene collassata via. L'app e la WebUI
producono lo stesso schema.

## Cosa VD Google non fa mai

- Non dichiara mai un permesso di rete, e la WebUI non contatta mai un'origine remota.
- Non fabbrica, falsifica o nasconde mai un valore.
- Non riporta mai un verdetto Play Integrity, perché quel verdetto non è sul dispositivo.
- Cambia lo stato solo tramite un'azione esplicita, confermata e registrata localmente.

<!-- doc-footer -->

---

> _Questa documentazione è stata generata con Claude (Anthropic) ed è gestita da VD171. Correzioni e miglioramenti sono benvenuti; vedi [CONTRIBUTING.md](CONTRIBUTING.md)._
