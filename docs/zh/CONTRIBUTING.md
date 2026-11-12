> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · 中文

---

# 贡献

感谢你对 VD Google 的关注。欢迎各类贡献：翻译、错误报告、文档和代码。

## 基本原则

- **VD Google 读取真相，绝不伪造任何东西。** 贡献必须维持这一点。真正的否定结果就保持否定；过期的
  缓存就标注为缓存；缺失的数值就是未知。不要为了让读数显得好看而弱化它。
- **隐私是绝对的。** 应用不声明任何权限，WebUI 不联系任何远程来源。绝不添加权限、网络调用，或任何会
  把数据发送到设备之外的东西。
- **应用和 WebUI 完全一致。** 对所显示内容或导出报告的任何更改，都必须同时落实到两者之中，措辞相
  同。它们绝不能发生偏离。
- **标签只存在一处，涵盖 21 种语言。** 每一个面向用户的字符串都为应用（`res/values*/strings.xml`）
  和 WebUI（`webroot/lang/`）定义。如果你添加或更改一个标签，请更新所有语言。

## 翻译

翻译是最容易、也最有价值的贡献。readme 按语言翻译（`README.<lang>.md`），应用和 WebUI 的字符串按语
言翻译，而本文档则位于 `docs/<lang>/` 之下。请在同一种语言内保持术语一致，贴合既有的语气，绝不对你
拿不准的技术术语进行机器翻译。

## 报告错误

开一个 issue，附上你的设备型号、Android 版本，以及 - 如果与某项读数有关 - 一份**导出**（其中不含个
人数据）。导出的诊断部分正是为此而设的。

## 代码

- 构建 release，绝不构建 debug。在真实的已 root 设备上测试。
- 不要在代码中留注释；标识符名称会说明意图。唯一的头部是每个源文件顶部的许可证横幅。
- 贴合既有风格，并让改动保持聚焦。

## 许可证

通过贡献，你同意你的贡献按项目的 **GNU AGPL-3.0-or-later** 授权。
