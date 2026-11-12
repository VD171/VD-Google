> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · Italiano · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Contribuire

Grazie per il tuo interesse in VD Google. I contributi sono benvenuti: traduzioni,
segnalazioni di bug, documentazione e codice.

## Regole di base

- **VD Google legge la verità e non falsifica mai nulla.** I contributi devono mantenerlo
  tale. Un vero esito negativo resta negativo; una cache obsoleta è etichettata come
  memorizzata in cache; un valore mancante è Sconosciuto. Non ammorbidire una lettura per
  farla apparire più gradevole.
- **La privacy è assoluta.** L'app non dichiara alcun permesso e la WebUI non contatta alcuna
  origine remota. Non aggiungere mai un permesso, una chiamata di rete o qualunque cosa che
  invii dati fuori dal dispositivo.
- **L'app e la WebUI sono identiche.** Qualsiasi modifica a ciò che viene mostrato, o al
  report esportato, deve approdare in entrambe, con la stessa formulazione. Non devono mai
  divergere.
- **Le etichette vivono una sola volta, in 21 lingue.** Ogni stringa rivolta all'utente è
  definita per l'app (`res/values*/strings.xml`) e per la WebUI (`webroot/lang/`). Se
  aggiungi o modifichi un'etichetta, aggiorna tutte le lingue.

## Traduzioni

Le traduzioni sono il contributo più semplice e prezioso. Il readme è tradotto per lingua
(`README.<lang>.md`), le stringhe dell'app e della WebUI per lingua, e questa documentazione
sotto `docs/<lang>/`. Mantieni la terminologia coerente all'interno di una lingua, rispetta
il tono esistente e non tradurre mai automaticamente un termine tecnico di cui non sei sicuro.

## Segnalare un bug

Apri una issue con il modello del tuo dispositivo, la versione di Android e - se riguarda una
lettura - un'**esportazione** (non contiene dati personali). La sezione diagnostica
dell'esportazione è costruita esattamente per questo.

## Codice

- Compila in release, mai in debug. Testa su un dispositivo reale con root.
- Tieni i commenti fuori dal codice; i nomi spiegano l'intento. L'unica intestazione è il
  banner della licenza in cima a ogni file sorgente.
- Rispetta lo stile esistente e mantieni le modifiche mirate.

## Licenza

Contribuendo, accetti che il tuo contributo sia rilasciato sotto la
**GNU AGPL-3.0-or-later** del progetto.
