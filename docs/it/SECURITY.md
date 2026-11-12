> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · Italiano · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Politica di sicurezza

VD Google è uno strumento per la privacy. Gira come root e legge stato sensibile, quindi le
segnalazioni di sicurezza vengono prese sul serio.

## Segnalare una vulnerabilità

Per favore segnala i problemi di sicurezza **in privato**, non in una issue pubblica:

- Invia un'e-mail a **vd.priv8@pm.me**.
- Descrivi il problema, la versione interessata e come riprodurlo.
- Se riguarda un'esposizione di dati, descrivi la classe del problema; non devi allegare un
  exploit funzionante.

Riceverai una risposta il prima possibile. Per favore concedi una finestra di tempo
ragionevole per correggere il problema prima di qualsiasi divulgazione pubblica.

## Cosa conta come problema di sicurezza

Per via di ciò che VD Google promette, questi sono particolarmente importanti:

- Qualsiasi cosa che farebbe raggiungere la rete all'app, o inviare qualsiasi dato fuori dal
  dispositivo.
- Qualsiasi cosa che farebbe trapelare dati personali in un'**esportazione** (un numero di
  telefono, una credenziale, il contenuto di un messaggio, il nome di un contatto o
  un'impronta per-identità).
- Qualsiasi cosa che permetta all'unica azione di sanificazione di cambiare lo stato senza
  conferma o senza essere registrata.

## Cosa non è un problema di sicurezza

- Una lettura che sembra "sbagliata" ma è l'onesta verità (per esempio, le linee RCS lato
  operatore che risultano No mentre l'RCS gira sul backend di Google). Vedi la
  [guida utente](USER-GUIDE.md).
- L'assenza di un verdetto Play Integrity. Quel verdetto non è sul dispositivo e VD Google
  non lo inventerà.

## Versioni supportate

È supportata solo l'ultima versione rilasciata. Per favore riproduci sull'ultima build prima
di segnalare.

<!-- doc-footer -->

---

> _Questa documentazione è stata generata con Claude (Anthropic) ed è gestita da VD171. Correzioni e miglioramenti sono benvenuti; vedi [CONTRIBUTING.md](CONTRIBUTING.md)._
