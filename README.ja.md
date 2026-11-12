<!--
  VD GOOGLE - RCS、Google ウォレット、Play Integrity 認証を端末上で読み取る。
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
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
  <b>あなたの Google サービスを端末上で読み取る。ネット接続は一切なし。</b><br>
  VD Google は、Google 自身があなたの端末に RCS、ウォレット、Play Integrity について記録している内容を表示し、各項目を説明します。どこにも何も送信しません。
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/USER-GUIDE">User guide</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/ARCHITECTURE">Architecture</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/PRIVACY">Privacy</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/BINARIES">Bundled binaries</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/FILE-STRUCTURE">File structure</a> ·
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

## アプリまたは WebUI

各リリースには2つの形式があり、どちらもインターネットを使わず、端末上で同じ情報を読み取ります。

- **App** - `.apk`。通常の Android アプリです。
- **WebUI** - `.zip`。root マネージャー（KernelSU、Magisk、APatch）で書き込み、そこから開くモジュールです。

どちらの場合も root が必要です。

## 表示する内容

### RCS
回線で RCS が利用可能か、あなたの RCS 番号、RCS の状態、構成バージョン、RCS に対応している連絡先の数、通信事業者の権限、プレゼンス (UCE)、および関連するタイムスタンプ (プロビジョニング、同期、最後のメッセージ、トークンの有効期限など)。 さらに、Google Messages の Gemini が利用可能かどうかも表示します。Magic Compose (Gemini の文章作成アシスト)、端末上の AI エンジン (Gemini Nano を実行する AICore)、そして RCS 経由で提供される Gemini チャット。

### Google ウォレット
端末が決済用に認証されているか、NFC ハードウェアがあるか、ウォレットがインストールされているか、決済サービスとタッチ決済の状態、画面ロックが承認されているか、これまでにカードが追加されたことがあるか。

### DroidGuard
この端末が認定済みとして Google Play に認識されているかどうか。Play ストアに表示される Play プロテクト認定です。 この端末が Play Integrity と DroidGuard 用にキャッシュしている認証です。表示される時刻は各認証の有効期限です。

### 認証 (Play Integrity)
最近 Google Play Integrity に端末の保証を要求したアプリ。それぞれのアイコン、名前、最後の要求の完全な時刻、現在のウィンドウ内での要求回数を表示します。

各項目には、それが実際に何を意味するかをわかりやすい言葉で説明した短い解説が付いています。

## プライバシー: ネット接続なし

VD Google は**いかなる権限も宣言しません**。インターネットアクセスさえありません。読み取った情報が端末の外に出ることは決してありません。マニフェストとソースコードで確認できます。

## 必要条件

- Android 8.0 (API 26) 以降。
- **Root が必要です。** 上記の情報は、システムのみがアクセスできる Android の保護領域にあります。VD Google はスーパーユーザー権限でそれを読み取り、それ以外のことは一切しません。root がない場合、アプリはなぜ root が必要かを説明する画面を表示するだけです。

## タイムスタンプ

すべての時刻は端末の現在のタイムゾーンで表示され、タイムゾーンは常に明記されます。

## ドキュメント

- **[Wiki](https://github.com/VD171/VD-Google/wiki)**: すべてのドキュメントをオンラインで閲覧できます。
- **[ユーザーガイド](https://github.com/VD171/VD-Google/wiki/USER-GUIDE)**: 各画面が何を読み取り、それぞれの結果が何を意味するか。
- **[アーキテクチャ](https://github.com/VD171/VD-Google/wiki/ARCHITECTURE)** と **[ファイル構成](https://github.com/VD171/VD-Google/wiki/FILE-STRUCTURE)**: どう動作し、どこに何があるか。
- **[プライバシー](https://github.com/VD171/VD-Google/wiki/PRIVACY)**: インターネット権限なし、すべての読み取りは root を介し、何も端末の外に出ません。
- **[同梱バイナリ](https://github.com/VD171/VD-Google/wiki/BINARIES)**: すべてのビルド済みバイナリの由来と SHA-256。完全に監査できます。
- **[貢献する](https://github.com/VD171/VD-Google/wiki/CONTRIBUTING)** と **[セキュリティ](https://github.com/VD171/VD-Google/wiki/SECURITY)**。
- **[Changelog](CHANGELOG.md)** と **[行動規範](https://github.com/VD171/VD-Google/wiki/CODE_OF_CONDUCT)**。

<!-- /vd-docs -->

## 関連プロジェクト

同じ作者による姉妹プロジェクト:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: 各項目を、それを読み取れるすべてのメソッドで読み取り、並べて比較する端末メソッドデバッガー。
- **[VDBackup](https://github.com/VD171/VD-Backup)**: root でアプリとデータを完全にバックアップ・復元し、すべて WebUI から操作。
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: 新しいフィンガープリントを一貫性のある整合したプロファイルに変える端末スプーファー。

## 連絡先

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Channel:** @Priv8_VD https://t.me/Priv8_VD
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **XDA thread:** https://xdaforums.com/t/VD-Google.4803837/
* **GitHub:** @VD171 https://github.com/VD171

## ライセンス

**GNU AGPL-3.0-or-later** - [LICENSE](LICENSE) を参照。ネットワーク条項を含むコピーレフト：改変版を（サービスとしてでも）実行する者は、そのソースを提供しなければなりません。フォークをオープンに保つため意図的に選びました。

## 商標

VD Google は独立した非公式プロジェクトです。Google LLC との提携、承認、スポンサー関係はありません。「Google」「Google Wallet」「Google Play」「Play Integrity」「Android」および関連する名称とロゴは Google LLC の商標です。VD Google は、すでに端末上にあるものを読み取るだけで、どこにも何も送信しません。

## タグ

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
