<!--
  VD GOOGLE - RCS, Google Wallet e attestazioni Play Integrity, letti sul dispositivo.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**I tuoi servizi Google, letti sul dispositivo. Mai Internet.**

VD Google ti mostra ciò che Google stesso registra sul tuo telefono riguardo a tre cose, e spiega cosa significa ogni voce, senza inviare nulla da nessuna parte.

> **Lingue:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## Cosa mostra

### RCS
Se l'RCS è disponibile per la tua linea, il tuo numero RCS, lo stato RCS, la versione della configurazione, quanti dei tuoi contatti supportano l'RCS, l'abilitazione dell'operatore, la presenza (UCE) e le date e orari correlati (provisioning, sincronizzazione, ultimo messaggio, scadenza del token e altro).

### Google Wallet
Se il tuo dispositivo è attestato per i pagamenti, se dispone di hardware NFC, se Wallet è installato, lo stato del servizio di pagamento e del pagamento contactless, se il tuo blocco schermo è accettato e se è mai stata aggiunta una carta.

### Attestazioni (Play Integrity)
Le app che hanno chiesto di recente a Google Play Integrity di garantire per il tuo dispositivo, ciascuna con la propria icona, il nome, l'orario completo dell'ultima richiesta e quante richieste ha effettuato nella finestra attuale.

Ogni voce è accompagnata da una breve spiegazione, in linguaggio semplice, di cosa significa davvero.

## Privacy: niente Internet

VD Google non dichiara **alcun permesso**, nemmeno l'accesso a Internet. Nulla di ciò che legge lascia mai il tuo dispositivo. Puoi verificarlo nel manifest e nel codice sorgente.

## Requisiti

- Android 8.0 (API 26) o più recente.
- **È necessario il root.** Le informazioni sopra risiedono in aree protette di Android, accessibili solo al sistema. VD Google le legge con accesso da superutente e non ne fa nient'altro. Senza root, l'app mostra solo una schermata che spiega perché serve il root.

## Date e orari

Tutti gli orari sono mostrati nel fuso orario attuale del dispositivo, e il fuso è sempre indicato.

## Contatti

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Licenza

**GNU AGPL-3.0-or-later** - vedi [LICENSE](LICENSE). Copyleft, inclusa la clausola di rete: chiunque esegua una versione modificata (anche come servizio) deve offrirne il codice sorgente. Scelta di proposito per mantenere i fork aperti.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
