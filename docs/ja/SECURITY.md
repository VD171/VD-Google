> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · 日本語 · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# セキュリティポリシー

VD Google はプライバシーツールです。root として動作し、機微な状態を読むため、セキュリティ
報告は真剣に扱われます。

## 脆弱性を報告する

セキュリティ上の問題は、公開 issue ではなく、**非公開**で報告してください:

- **vd.priv8@pm.me** へメールする。
- 問題、影響を受けるバージョン、再現方法を記述する。
- データ露出に関するものであれば、問題の種類を記述してください。動作する exploit を添付する
  必要はありません。

できるだけ早く返信があります。公開開示の前に、問題を修正するための合理的な猶予を与えて
ください。

## 何がセキュリティ上の問題にあたるか

VD Google が約束していることゆえ、以下は特に重要です:

- アプリをネットワークに到達させる、またはデバイスの外へ何らかのデータを送るもの。
- 個人データを**エクスポート**に漏らすもの（電話番号、資格情報、メッセージの内容、連絡先の
  名前、または個別の識別指紋）。
- 唯一のサニタイズアクションが、確認なしに、または記録されずに状態を変更できるようにするもの。

## 何がセキュリティ上の問題にあたらないか

- 「間違っている」ように見えるが誠実な真実である読み取り（例えば、RCS が Google の
  バックエンドで動いている間、キャリア側の RCS の行が No を示すこと）。[ユーザーガイド](USER-GUIDE.md)
  を参照してください。
- Play Integrity の判定がないこと。その判定はデバイス上に存在せず、VD Google はそれを
  でっち上げません。

## サポートされるバージョン

サポートされるのは最新のリリース版のみです。報告する前に最新のビルドで再現してください。

<!-- doc-footer -->

---

> _このドキュメントは Claude (Anthropic) で生成され、VD171 によって保守されています。修正や改善を歓迎します。[CONTRIBUTING.md](CONTRIBUTING.md) を参照してください。_
