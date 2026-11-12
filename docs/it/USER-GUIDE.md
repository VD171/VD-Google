> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · Italiano · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Guida utente di VD Google

Una panoramica amichevole e completa di VD Google: cosa mostra, cosa significa ogni riga e
come leggere il tuo dispositivo in modo onesto. VD Google legge lo stato di RCS, Google
Wallet e le attestazioni Play Integrity che le app richiedono, e spiega ogni elemento con
parole semplici. Non tocca mai internet, e non cambia mai nulla a meno che tu non lo chieda.

## Indice

- [1. Cos'è VD Google](#1-cosè-vd-google)
- [2. Cosa non è](#2-cosa-non-è)
- [3. Requisiti](#3-requisiti)
- [4. App o WebUI](#4-app-o-webui)
- [5. Installazione e primo avvio](#5-installazione-e-primo-avvio)
- [6. Le schede](#6-le-schede)
  - [6.1 Informazioni](#61-informazioni)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Attestazioni](#65-attestazioni)
- [7. Esporta il tuo report](#7-esporta-il-tuo-report)
- [8. Azioni che cambiano lo stato](#8-azioni-che-cambiano-lo-stato)
- [9. Lingua e tema](#9-lingua-e-tema)
- [10. Domande frequenti](#10-domande-frequenti)

## 1. Cos'è VD Google

VD Google è un lettore. Guarda ciò che le app di Google memorizzano già sul tuo dispositivo
riguardo a tre cose, e spiega ciascuna così che tu la capisca davvero:

- **RCS** - se la chat moderna funziona davvero sulla tua linea, con chi puoi chattare e se
  Gemini in Google Messages è pronto.
- **Google Wallet** - se il tuo dispositivo è configurato e attestato per i pagamenti
  contactless.
- **Attestazioni Play Integrity** - quali app hanno chiesto ad Android di dimostrare che il
  tuo dispositivo è autentico, e se Google attualmente lo certifica.

Tutto viene letto localmente, tramite root, e mostrato con una spiegazione. Nessun valore
viene inventato: quando il dispositivo non ha una risposta, VD Google dice "Sconosciuto"
anziché tirare a indovinare.

## 2. Cosa non è

**VD Google non è un verificatore di integrità.** Non esegue né riporta alcun verdetto
basic / device / strong. Quel risultato vive solo dentro il token cifrato che un'app invia
al proprio server, e non viene mai memorizzato sul dispositivo, quindi nessun lettore onesto
può mostrarlo. Gli strumenti che affermano di visualizzare localmente il tuo "verdetto Play
Integrity" ti stanno mostrando un'ipotesi in cache o una richiesta che hanno fatto loro
stessi, non la risposta reale.

VD Google inoltre non falsifica, nasconde o simula nulla. Legge la verità. Se un valore ti
sembra sbagliato, l'obiettivo di questa guida è spiegare perché la verità appare così.

## 3. Requisiti

- **Android 8.0 (API 26) o più recente.**
- **Root.** I dati che VD Google legge vivono in aree protette di Android riservate al
  sistema, raggiungibili solo con root. Qualsiasi soluzione di root moderna va bene:
  KernelSU, KernelSU Next, Magisk o APatch.
- **Nessun internet.** VD Google non dichiara alcun permesso, quindi non c'è nulla da
  consentire e nulla da bloccare.

## 4. App o WebUI

VD Google è distribuito in due modi, e mostrano esattamente le stesse informazioni:

- **L'app (APK).** Una normale app Android che installi e apri. Chiede il root la prima
  volta, come qualsiasi app root.
- **Il modulo WebUI (ZIP).** Installalo nel tuo gestore di root (KernelSU, Magisk o APatch)
  e aprilo da lì. Niente viene installato come app; gira dentro la WebUI del tuo gestore di
  root.

Scegli quello che preferisci. Le schermate, la formulazione e il report esportato sono
identici tra i due.

## 5. Installazione e primo avvio

**App:** installa l'APK e aprilo. La prima volta, concedi il root quando il tuo gestore di
root lo chiede. Se neghi o ignori il root, VD Google mostra una schermata che spiega perché
il root è necessario e offre di riprovare.

**WebUI:** installa lo ZIP come modulo nel tuo gestore di root, poi apri la WebUI del modulo
dal gestore. Legge non appena si apre.

Alla prima apertura puoi scegliere la tua lingua; puoi cambiarla in seguito dall'icona
dell'ingranaggio in alto.

## 6. Le schede

Lungo la parte inferiore ci sono cinque schede: Informazioni, DroidGuard, RCS, Wallet e
Attestazioni. Informazioni si apre per prima.

### 6.1 Informazioni

Cos'è l'app, la promessa di privacy, la versione e i link. Leggi la sezione sulla privacy
una volta: è l'intero scopo dell'app.

### 6.2 RCS

Questa è la scheda più ricca. I timestamp sono sempre mostrati nel fuso orario del tuo
dispositivo, con il fuso etichettato.

- **Connessione** - l'unica riga che dice la verità. È calcolata dalla reale configurazione
  della chat e dal motore di provisioning, quindi dice Connesso quando l'RCS funziona
  davvero sulla tua linea, anche su Android più recente dove i flag più vecchi mentono.
  **Nel dubbio, fidati di questa riga.**
- **Operatore** - l'operatore mobile della tua linea (nome e MCC/MNC), preso dalla tua SIM
  dati sui telefoni dual-SIM.
- **Flag di disponibilità (in cache)** - un flag in cache che l'app di messaggistica tiene
  per il tuo stesso numero. Può restare non aggiornato dopo che l'RCS funziona già, quindi
  un "No" qui non significa che l'RCS sia spento. Fidati della riga Connessione qui sopra.
- **Il tuo numero RCS** - il numero registrato per l'RCS, mascherato per impostazione
  predefinita. Tocca l'occhio per rivelarlo.
- **Stato di provisioning (in cache)** e **Versione della configurazione** - valori interni
  che l'app di messaggistica tiene. Possono risultare 0 anche mentre l'RCS funziona. Vedi la
  nota su operatore vs backend di Google qui sotto.
- **Contatti con RCS** - quanti dei tuoi contatti sono attualmente noti per supportare la
  chat RCS.
- **Flag della funzionalità (in cache)** - se l'app di messaggistica ha la funzionalità RCS
  attivata; in cache, quindi può essere in ritardo.
- **Entitlement dell'operatore**, **Presenza (UCE)**, **Servizio IMS presente** - questi
  descrivono il percorso RCS *dell'operatore* (la rete IMS dell'operatore). Un "No" o "0"
  qui è normale quando il tuo RCS gira attraverso il backend di Google anziché il tuo
  operatore. Vedi sotto.
- **Gemini** - se Gemini in Google Messages è pronto: **Magic Compose** (l'aiuto alla
  scrittura di Gemini), **AICore** (il motore sul dispositivo che esegue Gemini Nano) e
  **Gemini chat**, che viene erogato tramite RCS e quindi richiede l'RCS connesso.
- **Timestamp** - provisioning, sincronizzazione, ultimo messaggio, scadenza del token e
  altro.

**RCS dell'operatore vs backend di Google.** Ci sono due modi in cui l'RCS raggiunge il tuo
telefono. Uno è l'RCS *del tuo operatore* sulla sua rete IMS; l'altro è il *backend proprio
di Google* (Jibe), che funziona over-the-top, indipendente dall'operatore. Se il tuo RCS
gira attraverso il backend di Google - come accade per la maggior parte delle persone -
allora le righe lato operatore (entitlement dell'operatore, presenza/UCE, servizio IMS,
versione della configurazione) sono genuinamente "No" o "0", e questo è corretto, non una
cache obsoleta. Il tuo RCS può aver funzionato per anni in questo modo. La riga Connessione
è ciò che ti dice che funziona.

### 6.3 Wallet

- **Dispositivo attestato** - se Google ha giudicato questo dispositivo autentico e
  abbastanza sicuro da contenere carte di pagamento. È l'ultimo risultato che Google ha
  salvato su questo dispositivo, quindi può essere in ritardo rispetto all'app Wallet finché
  Google non effettua una nuova verifica. Se il risultato salvato manca, VD Google mostra
  **Sconosciuto** anziché un fuorviante "No".
- **Ultima verifica** - quando quel controllo di sicurezza si è completato l'ultima volta.
- **Hardware NFC**, **Wallet installato**, **Servizio Wallet**, **Tocca e paga**, **Blocco
  schermo accettato**, **Carta mai aggiunta** - gli elementi che rendono possibile il
  pagamento contactless.

### 6.4 DroidGuard

- **Certificato Play Protect** - se Google Play riconosce il tuo dispositivo come
  certificato (la certificazione mostrata nel Play Store).
- **Cache di attestazione** - le attestazioni Play Integrity e DroidGuard che il tuo
  dispositivo ha memorizzato in cache, ciascuna con la propria scadenza.

### 6.5 Attestazioni

La cronologia di quali app hanno richiesto Play Integrity sul tuo dispositivo: icona, nome,
quando hanno chiesto l'ultima volta e quante volte. Questo ti permette di capire se una data
app verifica l'integrità o no. Usa la casella di ricerca per filtrare per nome dell'app o
package.

## 7. Esporta il tuo report

L'icona di download scrive un report **JSON** con lo stato del tuo dispositivo, RCS, Wallet
e attestazioni. Puoi condividerlo o salvarlo come file. Il report non contiene **alcun
numero di telefono, alcuna credenziale e alcun contenuto personale** - viene sanificato
prima di essere scritto. Include anche una sezione **diagnostica** opzionale (nomi di
schema, conteggi e versioni, mai i tuoi dati) che aiuta a risolvere problemi specifici del
dispositivo. L'app e la WebUI scrivono lo stesso report.

## 8. Azioni che cambiano lo stato

VD Google è per lo più di sola lettura, ma può eseguire un'**azione di sanificazione**:
cancellare il risultato di attestazione memorizzato in cache di Google Wallet, il che
permette a una futura verifica di ripartire da zero. Ogni azione che cambia lo stato ti
chiede prima di confermare, e viene scritta in un log JSON locale sul dispositivo. Niente
lascia il tuo telefono.

## 9. Lingua e tema

VD Google parla 21 lingue. Scegli la tua al primo avvio, o cambiala in qualsiasi momento
dall'icona dell'ingranaggio (disponibile anche nella schermata di root negato). L'icona
della luminosità passa tra chiaro e scuro.

## 10. Domande frequenti

**L'RCS dice Connesso ma diverse righe dicono No o 0. Perché?** Perché il tuo RCS gira
attraverso il backend proprio di Google, non il tuo operatore. Le righe lato operatore sono
correttamente negative. Vedi [6.2 RCS](#62-rcs).

**Dispositivo attestato dice Sconosciuto.** Il risultato Wallet in cache manca sul tuo
dispositivo. Questo è onesto - VD Google non mostrerà un "No" che non può sostenere.

**Dov'è il mio verdetto Play Integrity?** Non è sul tuo dispositivo. Vedi
[2. Cosa non è](#2-cosa-non-è).

**Funziona senza root?** No. I dati vivono in aree riservate al sistema.
