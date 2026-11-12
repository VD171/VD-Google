> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · Italiano · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Privacy

La privacy non è una funzionalità di VD Google - è l'intero scopo. Questa pagina dichiara
esattamente cosa l'app fa e non fa con i tuoi dati, in termini chiari che puoi verificare.

## La promessa

- **Nessun permesso di internet viene concesso.** Il manifest dell'app non dichiara alcun
  permesso - né internet, né archiviazione, né contatti, niente. Non c'è nulla da
  consentire.
- **Niente viene memorizzato, inviato o trasmesso** a qualsiasi file o server fuori dal tuo
  dispositivo.
- Ogni lettura avviene **localmente, tramite root**, e resta sul dispositivo.

## Perché ha bisogno di root invece dei permessi

Lo stato di RCS, Wallet e attestazione che VD Google spiega vive in aree protette di Android
riservate al sistema, che le app normali non possono vedere. VD Google le legge con root
anziché chiederti permessi ampi. Root è la via d'accesso stretta e verificabile; una pila di
permessi runtime sarebbe la via ampia e dispersiva. L'app sceglie quella stretta.

## L'esportazione è sanificata

Quando esporti un report, VD Google lo sanifica prima di scriverlo:

- **Mai incluso:** il tuo numero di telefono, qualsiasi credenziale, cookie o token, il
  contenuto dei messaggi, i nomi dei contatti o i blob grezzi di capacità.
- **Oscurato:** le lunghe sequenze di cifre e i valori che somigliano a segreti vengono
  sostituiti con un segnaposto.
- **La diagnostica** porta solo la struttura (nomi di schema, conteggi, dimensioni,
  versioni). Qualsiasi impronta per-identità che dovesse comparire all'interno di un nome
  viene collassata via.

Il report è tuo. Viene scritto dove scegli, viene condiviso solo se lo condividi tu, e non
contiene nulla che ti identifichi.

## Le azioni sono esplicite e registrate

VD Google può cancellare il risultato di attestazione memorizzato in cache di Google Wallet
affinché una futura verifica riparta da zero. Qualsiasi azione che cambia lo stato:

1. ti chiede prima di confermare, e
2. viene scritta in un log JSON locale sul dispositivo.

Nessuna azione raggiunge mai la rete, e niente lascia il tuo telefono.

## Verificalo tu stesso

Non devi credere a nulla di tutto questo sulla fiducia:

- Il sorgente è pubblico: <https://github.com/VD171/VD-Google>.
- Il manifest è nel sorgente; verifica che non dichiari alcun permesso.
- La Content-Security-Policy della WebUI è nel sorgente; verifica che blocchi ogni origine
  remota.
- I binari inclusi sono documentati con i checksum in [Binari inclusi](BINARIES.md).
