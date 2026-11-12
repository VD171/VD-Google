> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · 日本語 · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# 貢献する

VD Google にご興味をお持ちいただきありがとうございます。貢献を歓迎します: 翻訳、バグ報告、
ドキュメント、コード。

## 基本ルール

- **VD Google は真実を読み、決して何も偽らない。** 貢献はそれを保たなければなりません。
  本当の否定は否定のままであり、古いキャッシュはキャッシュと表示され、欠けている値は不明
  です。見栄えを良くするために読み取りを和らげないでください。
- **プライバシーは絶対である。** アプリは一切の権限を宣言せず、WebUI はいかなるリモート
  オリジンにも接続しません。権限、ネットワーク呼び出し、またはデバイスの外へデータを送る
  ものを決して追加しないでください。
- **アプリと WebUI は同一である。** 表示されるもの、またはエクスポートされるレポートへの
  いかなる変更も、同じ文言で両方に反映されなければなりません。両者は決して乖離してはなりません。
- **ラベルは 21 言語で一度だけ存在する。** ユーザーに表示されるすべての文字列は、アプリ
  （`res/values*/strings.xml`）と WebUI（`webroot/lang/`）向けに定義されています。ラベルを
  追加または変更する場合は、すべての言語を更新してください。

## 翻訳

翻訳は最も簡単で、最も価値のある貢献です。readme は言語ごとに（`README.<lang>.md`）、
アプリと WebUI の文字列は言語ごとに、そしてこのドキュメントは `docs/<lang>/` 以下で翻訳
されます。一つの言語の中で用語を一貫させ、既存のトーンに合わせ、確信のない技術用語を決して
機械翻訳しないでください。

## バグを報告する

デバイスのモデル、Android のバージョン、そして - 読み取りに関するものであれば -
**エクスポート**（個人データは含まれません）を添えて issue を開いてください。エクスポートの
診断セクションはまさにこのために作られています。

## コード

- release でビルドし、決して debug ではビルドしない。実際の root 化されたデバイスでテストする。
- コードの中にコメントを置かない。名前が意図を説明する。唯一のヘッダーは各ソースファイルの
  先頭にあるライセンスバナーです。
- 既存のスタイルに合わせ、変更を焦点を絞ったものに保つ。

## ライセンス

貢献することで、あなたは自分の貢献がプロジェクトの **GNU AGPL-3.0-or-later** の下で
ライセンスされることに同意します。
