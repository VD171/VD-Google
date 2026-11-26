> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · 日本語 · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# AI エージェント

VD Google は、Google がデバイス上に残す記録を、**AI エージェントが 1 ステップで読めるもの**に変えます。複数の Google アプリやデータベースを開いて意味を推測する代わりに、エージェントは単一の構造化された**エクスポート**を読み、デバイスが RCS、Google Wallet、Play Integrity について何を報告しているかを正確に把握します。

このページは README で紹介した考え方を掘り下げます。

## エクスポート

アプリと WebUI はどちらも同じ JSON エクスポートを生成します。

- **アプリ**: **エクスポート**操作でレポートを共有または保存します。
- **WebUI**: **エクスポート**操作で `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json` に書き込みます。

アプリと WebUI は同一に保たれているため、両者は同じスキーマです。どこにも何も送信されません。エクスポートはデバイス上に書き込まれ、そこに留まります。

## 中身

エクスポートは、次のトップレベルキーを持つ単一の JSON オブジェクトです。

- `app`、`version`、`exportedAt`、`timezone` - 何がいつレポートを生成したか、デバイスのラベル付きタイムゾーンで示します。
- `device` - メーカー、モデル、Android のバージョン、SDK レベル。
- `rcs` - 利用可否、状態、設定バージョン、対応する連絡先、キャリアの利用許可、プレゼンス (UCE)、および関連するタイムスタンプ。
- `wallet` - 支払いの認証、NFC ハードウェア、タップ決済の状態、画面ロックの受け入れ、カードが追加されたことがあるか。
- `diagnostics` - バグ報告のために作られた自己診断セクション。

エクスポートには**個人データは一切含まれません**。電話番号も、認証情報も、メッセージ内容も、連絡先名もありません。すべての値は正直な読み取りです。本当の「いいえ」は「いいえ」のまま、古いキャッシュはキャッシュ済みと表示され、欠落した値は「不明」になります。

## 読み取り専用、ただし記録される 1 つの操作

VD Google は読み取ります。唯一の例外はサニタイズ操作 (再評価を強制するために、キャッシュされた Google Wallet の認証結果を消去する) です。状態を変更するすべての操作は、デバイス上での確認を求め、ローカルの JSON 操作ログに書き込まれます。WebUI を操作するエージェントも同じ確認と同じログを見ます。隠れた、記録されない経路はありません。

## ネットワーク契約なし

VD Google はネットワーク API もソケットも公開しません。パーミッションを一切宣言しません。エージェントは**エクスポートファイル**を利用するのであり、サービスを呼び出しません。これが、自動化でも保たれるプライバシーの保証です。

<!-- doc-footer -->

---

> _このドキュメントは Claude (Anthropic) で生成され、VD171 によって保守されています。修正や改善を歓迎します。[CONTRIBUTING.md](CONTRIBUTING.md) を参照してください。_
