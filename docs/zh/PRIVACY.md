> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · 中文

---

# 隐私

隐私并非 VD Google 的一项功能 - 它就是全部的意义所在。本页用你可以核实的直白措辞，确切说明应用对你
的数据做什么、不做什么。

## 承诺

- **不授予任何网络权限。** 应用的清单（manifest）完全不声明任何权限 - 没有网络、没有存储、没有联系
  人，什么都没有。根本没有需要允许的东西。
- **没有任何东西被存储、发送或传输** 到你设备之外的任何文件或服务器。
- 每一次读取都**在本地、通过 root** 进行，并留在设备上。

## 它为何需要 root 而非权限

VD Google 所解释的 RCS、Wallet 和认证状态位于 Android 受保护的、仅限系统的区域，普通应用无法看到它
们。VD Google 用 root 来读取它们，而不是向你索要宽泛的权限。root 是狭窄而可审计的入口；一堆运行时权
限则是宽泛而易漏的入口。应用选择了狭窄的那条路。

## 导出经过净化

当你导出一份报告时，VD Google 会在写入之前对其进行净化：

- **绝不包含：** 你的电话号码、任何凭据、cookie 或 token、消息内容、联系人姓名，或原始的能力数据块
  （capability blob）。
- **涂抹：** 长串数字以及看起来像机密的数值会被替换为一个占位符。
- **诊断** 只携带结构信息（架构名称、计数、大小、版本）。碰巧出现在某个名称里的任何按身份的指纹都会
  被折叠抹去。

报告属于你。它写在你选择的位置，只有你分享它时它才被分享，而且它不含任何能识别你身份的内容。

## 操作是明确且有记录的

VD Google 可以清除 Google Wallet 缓存的认证结果，使未来的重新检查从头开始。任何改变状态的操作：

1. 会先要求你确认，并且
2. 会被写入设备上的一份本地 JSON 日志。

没有任何操作会触及网络，也没有任何东西会离开你的手机。

## 自行验证

你无需对以上任何一点全凭信任：

- 源代码是公开的：<https://github.com/VD171/VD-Google>。
- 清单在源代码中；请确认它不声明任何权限。
- WebUI 的 Content-Security-Policy 在源代码中；请确认它阻止一切远程来源。
- 内置二进制文件连同校验和都记录在[内置二进制文件](BINARIES.md)中。
