> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · 中文

---

# 安全策略

VD Google 是一款隐私工具。它以 root 身份运行并读取敏感状态，因此安全报告会被认真对待。

## 报告漏洞

请**私下**报告安全问题，而非在公开 issue 中：

- 发送电子邮件至 **vd.priv8@pm.me**。
- 描述问题、受影响的版本，以及如何复现它。
- 如果它涉及数据暴露，请描述问题的类别；你无需附上一个可用的利用程序（exploit）。

你会尽快收到回复。在任何公开披露之前，请给予合理的时间窗口以修复该问题。

## 什么算安全问题

鉴于 VD Google 的承诺，以下这些尤为重要：

- 任何会使应用访问网络、或将任何数据发送到设备之外的东西。
- 任何会把个人数据泄露到**导出**中的东西（电话号码、凭据、消息内容、联系人姓名，或按身份的指纹）。
- 任何让那唯一的净化操作在没有确认、或没有被记录的情况下改变状态的东西。

## 什么不算安全问题

- 一项看起来"错误"、但其实是诚实真相的读数（例如，当 RCS 运行在 Google 后端时，运营商侧的 RCS 行读
  出否（No））。参见[用户指南](USER-GUIDE.md)。
- 缺少 Play Integrity 判定。该判定并不在设备上，VD Google 也不会臆造它。

## 受支持的版本

仅支持最新发布的版本。请在报告前于最新构建上复现。
