<!--
  VD GOOGLE - RCS、Google ウォレット、Play Integrity 認証を端末上で読み取る。
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**あなたの Google サービスを端末上で読み取る。ネット接続は一切なし。**

VD Google は、Google 自身があなたの端末に記録している 3 つの事柄を表示し、各項目が何を意味するかを説明します。どこにも何も送信しません。

> **言語:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## 表示する内容

### RCS
回線で RCS が利用可能か、あなたの RCS 番号、RCS の状態、構成バージョン、RCS に対応している連絡先の数、通信事業者の権限、プレゼンス (UCE)、および関連するタイムスタンプ (プロビジョニング、同期、最後のメッセージ、トークンの有効期限など)。

### Google ウォレット
端末が決済用に認証されているか、NFC ハードウェアがあるか、ウォレットがインストールされているか、決済サービスとタッチ決済の状態、画面ロックが承認されているか、これまでにカードが追加されたことがあるか。

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

## 連絡先

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## ライセンス

**GNU AGPL-3.0-or-later** - [LICENSE](LICENSE) を参照。ネットワーク条項を含むコピーレフト：改変版を（サービスとしてでも）実行する者は、そのソースを提供しなければなりません。フォークをオープンに保つため意図的に選びました。

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
