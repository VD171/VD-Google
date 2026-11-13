<!--
  VD GOOGLE: RCS, Google Wallet dan atestasi Play Integrity, dibaca di perangkat.
  Copyright (C) 2026  VD171. SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

<p align="center">
  <img src="images/logo-wordmark.png" alt="VD Google" width="320">
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases/latest"><img src="https://img.shields.io/github/v/release/VD171/VD-Google?label=release&color=0969da" alt="Release"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-AGPL--3.0--or--later-1a7f37" alt="License"></a>
  <a href="https://github.com/VD171/VD-Google/releases"><img src="https://img.shields.io/github/downloads/VD171/VD-Google/total?label=downloads&color=8250df" alt="Downloads"></a>
  <a href="https://github.com/VD171/VD-Google/stargazers"><img src="https://img.shields.io/github/stars/VD171/VD-Google?color=d29922" alt="Stars"></a>
</p>

<p align="center">
  <b>Layanan Google Anda, dibaca di perangkat. Tidak pernah internet.</b><br>
  VD Google menunjukkan apa yang dicatat Google sendiri di ponsel Anda tentang RCS, Wallet, dan Play Integrity, dan menjelaskan arti setiap item, tanpa mengirim apa pun ke mana pun.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/in-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/in-USER-GUIDE">Panduan pengguna</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/in-ARCHITECTURE">Arsitektur</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/in-PRIVACY">Privasi</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/in-BINARIES">Biner yang disertakan</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/in-FILE-STRUCTURE">Struktur berkas</a> ·
  <a href="CHANGELOG.md">Changelog</a> ·
  <a href="https://xdaforums.com/t/VD-Google.4803837/">XDA thread</a>
</p>

> **Languages:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Українська](README.uk.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<p align="center">
  <img src="images/vdgoogle-01.png" height="420"/>
  <img src="images/vdgoogle-02.png" height="420"/>
  <img src="images/vdgoogle-03.png" height="420"/>
  <img src="images/vdgoogle-04.png" height="420"/>
  <img src="images/vdgoogle-05.png" height="420"/>
</p>

---

## Aplikasi atau WebUI

Setiap rilis hadir dalam dua bentuk, dan keduanya membaca hal yang sama di perangkat Anda, tanpa internet:

- **App**: berkas `.apk`, aplikasi Android biasa.
- **WebUI**: berkas `.zip`, modul yang Anda pasang di pengelola root (KernelSU, Magisk, atau APatch) dan buka dari sana.

Keduanya memerlukan root.

## Apa yang ditampilkan

### RCS
Apakah RCS tersedia untuk jalur Anda, nomor RCS Anda, status RCS, versi konfigurasi, berapa banyak kontak Anda yang mendukung RCS, hak operator, kehadiran (UCE), dan stempel waktu terkait (provisioning, sinkronisasi, pesan terakhir, kedaluwarsa token, dan lainnya). Juga menampilkan apakah Gemini di Google Messages siap: Magic Compose (bantuan menulis dari Gemini), mesin AI di perangkat (AICore, yang menjalankan Gemini Nano), dan chat Gemini, yang dikirim melalui RCS.

### Google Wallet
Apakah perangkat Anda teratestasi untuk pembayaran, apakah memiliki perangkat keras NFC, apakah Wallet terpasang, status layanan pembayaran dan bayar dengan menempelkan, apakah kunci layar Anda diterima, dan apakah kartu pernah ditambahkan.

### DroidGuard
Apakah Google Play mengenali perangkat ini sebagai bersertifikat. Ini adalah sertifikasi Play Protect yang ditampilkan di Play Store. Atestasi yang disimpan perangkat ini dalam cache untuk Play Integrity dan DroidGuard. Waktu yang ditampilkan adalah saat masing-masing kedaluwarsa.

### Atestasi (Play Integrity)
Aplikasi yang baru-baru ini meminta Google Play Integrity untuk menjamin perangkat Anda, masing-masing dengan ikon, nama, waktu lengkap permintaan terakhir, dan berapa banyak permintaan yang dibuatnya dalam jendela saat ini.

Setiap item disertai penjelasan singkat dengan bahasa sederhana tentang apa artinya sebenarnya.

## Privasi: tanpa internet

VD Google tidak mendeklarasikan **izin apa pun**, bahkan akses internet. Tidak ada yang dibacanya yang pernah meninggalkan perangkat Anda. Anda dapat memastikannya di manifes dan di kode sumber.

## Persyaratan

- Android 8.0 (API 26) atau lebih baru.
- **Root diperlukan.** Informasi di atas berada di area terlindungi Android yang hanya dapat diakses sistem. VD Google membacanya dengan akses superuser dan tidak melakukan hal lain dengannya. Tanpa root, aplikasi hanya menampilkan layar yang menjelaskan mengapa root diperlukan.

## Stempel waktu

Semua waktu ditampilkan dalam zona waktu perangkat Anda saat ini, dan zona waktu selalu dicantumkan.

## Dokumentasi

- **[Wiki](https://github.com/VD171/VD-Google/wiki/in-Home)**: semua dokumentasi, dapat dijelajahi secara online.
- **[Panduan pengguna](https://github.com/VD171/VD-Google/wiki/in-USER-GUIDE)**: apa yang dibaca setiap layar dan apa arti setiap hasilnya.
- **[Arsitektur](https://github.com/VD171/VD-Google/wiki/in-ARCHITECTURE)** dan **[Struktur berkas](https://github.com/VD171/VD-Google/wiki/in-FILE-STRUCTURE)**: cara kerjanya dan di mana semuanya berada.
- **[Privasi](https://github.com/VD171/VD-Google/wiki/in-PRIVACY)**: tanpa izin internet, setiap pembacaan melewati root, tidak ada yang pernah keluar dari perangkat.
- **[Biner yang disertakan](https://github.com/VD171/VD-Google/wiki/in-BINARIES)**: asal-usul dan SHA-256 dari setiap biner prakompilasi, untuk audit penuh.
- **[Berkontribusi](https://github.com/VD171/VD-Google/wiki/in-CONTRIBUTING)** dan **[Keamanan](https://github.com/VD171/VD-Google/wiki/in-SECURITY)**.
- **[Changelog](CHANGELOG.md)** dan **[Kode etik](https://github.com/VD171/VD-Google/wiki/in-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Lihat juga

Proyek saudara dari penulis yang sama:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: debugger metode perangkat yang membaca setiap item melalui setiap metode yang dapat membacanya dan membandingkannya secara berdampingan.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: pencadangan dan pemulihan penuh aplikasi dan data sebagai root, dijalankan sepenuhnya dari WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: pengusur (spoofer) perangkat yang mengubah fingerprint baru menjadi profil yang koheren dan konsisten.
- **[VD-dropbear](https://github.com/VD171/VD-dropbear)**: daemon SSH mandiri yang berjalan sebagai root (Dropbear).

## Kontak

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## Lisensi

**GNU AGPL-3.0-or-later**: lihat [LICENSE](LICENSE). Copyleft, termasuk klausul jaringan: siapa pun yang menjalankan versi modifikasi (bahkan sebagai layanan) harus menyediakan kode sumbernya. Dipilih dengan sengaja agar fork tetap terbuka.

## Merek dagang

VD Google adalah proyek independen dan tidak resmi. Proyek ini tidak berafiliasi dengan, didukung oleh, atau disponsori oleh Google LLC. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" serta nama dan logo terkait adalah merek dagang dari Google LLC. VD Google hanya membaca apa yang sudah ada di perangkat Anda dan tidak mengirim apa pun ke mana pun.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
