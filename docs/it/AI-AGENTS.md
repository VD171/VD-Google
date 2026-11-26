> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · Italiano · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Agenti AI

VD Google trasforma i registri che Google tiene sul dispositivo in qualcosa che un **agente AI può leggere in un solo passaggio**. Invece di aprire diverse app e database di Google e indovinarne il significato, un agente legge un unico **export** strutturato e sa esattamente cosa il dispositivo riporta su RCS, Google Wallet e Play Integrity.

Questa pagina approfondisce l'idea presentata nel README.

## L'export

Sia l'app sia la WebUI producono lo stesso export JSON:

- **App**: l'azione **Esporta** condivide o salva il report.
- **WebUI**: l'azione **Esporta** lo scrive in `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Hanno lo stesso schema, perché app e WebUI sono mantenute identiche. Non viene inviato nulla da nessuna parte: l'export viene scritto sul dispositivo e vi rimane.

## Cosa contiene

L'export è un unico oggetto JSON con queste chiavi di primo livello:

- `app`, `version`, `exportedAt`, `timezone` - cosa ha prodotto il report e quando, nel fuso orario etichettato del dispositivo.
- `device` - produttore, modello, versione di Android e livello SDK.
- `rcs` - disponibilità, stato, versione della configurazione, contatti compatibili, abilitazione dell'operatore, presenza (UCE) e i timestamp correlati.
- `wallet` - attestazione di pagamento, hardware NFC, lo stato del pagamento contactless, accettazione del blocco schermo e se è mai stata aggiunta una carta.
- `diagnostics` - una sezione di autodiagnosi, pensata per le segnalazioni di bug.

L'export non contiene **nessun dato personale**: nessun numero di telefono, nessuna credenziale, nessun contenuto di messaggi, nessun nome di contatto. Ogni valore è la lettura onesta - un negativo reale resta negativo, una cache obsoleta è etichettata come in cache, un valore mancante è Sconosciuto.

## Sola lettura, con un'azione registrata

VD Google legge. L'unica eccezione è un'azione di sanificazione (cancellare il risultato di attestazione in cache di Google Wallet per forzare una nuova verifica). Ogni azione che modifica lo stato chiede una conferma sul dispositivo e viene scritta in un registro azioni locale in JSON. Un agente che guida la WebUI vede la stessa conferma e lo stesso registro, non c'è alcun percorso nascosto o non registrato.

## Nessun contratto di rete

VD Google non espone alcuna API di rete né socket: non dichiara alcun permesso. Un agente consuma il **file di export**, non chiama un servizio. Questa è la garanzia di privacy, mantenuta anche per l'automazione.

<!-- doc-footer -->

---

> _Questa documentazione è stata generata con Claude (Anthropic) ed è gestita da VD171. Correzioni e miglioramenti sono benvenuti, vedi [CONTRIBUTING.md](CONTRIBUTING.md)._
