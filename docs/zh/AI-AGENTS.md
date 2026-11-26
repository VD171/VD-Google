> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · 中文

---

# AI 代理

VD Google 把 Google 在设备上自己的记录，变成**AI 代理一步就能读取**的东西。代理无需打开多个 Google 应用和数据库再去猜测其含义，只需读取单个结构化的**导出**，就能确切知道设备对 RCS、Google Wallet 和 Play Integrity 的报告内容。

本页对 README 中提出的想法做进一步展开。

## 导出

应用和 WebUI 都会生成相同的 JSON 导出：

- **应用**：**导出**操作会分享或保存报告。
- **WebUI**：**导出**操作会将其写入 `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`。

两者的结构完全相同，因为应用和 WebUI 保持一致。不会向任何地方发送任何内容：导出写在设备上，并留在设备上。

## 里面有什么

导出是一个 JSON 对象，包含以下顶层键：

- `app`、`version`、`exportedAt`、`timezone` - 由什么生成报告以及何时生成，使用设备带标注的时区。
- `device` - 制造商、型号、Android 版本和 SDK 级别。
- `rcs` - 可用性、状态、配置版本、支持的联系人、运营商授权、在线状态（UCE）以及相关时间戳。
- `wallet` - 支付认证、NFC 硬件、一触即付状态、屏幕锁是否被接受，以及是否曾添加过卡片。
- `diagnostics` - 为错误报告准备的自检部分。

导出**不包含任何个人数据**：没有电话号码、没有凭据、没有消息内容、没有联系人姓名。每个值都是诚实的读取结果 - 真正的否仍然是否，过期的缓存被标注为已缓存，缺失的值是未知。

## 只读，含一个会被记录的操作

VD Google 只读取。唯一的例外是一个清理操作（清除 Google Wallet 缓存的认证结果，以强制重新评估）。每个更改状态的操作都会在设备上请求确认，并写入本地的 JSON 操作日志。驱动 WebUI 的代理会看到相同的确认和相同的日志；不存在任何隐藏的、未记录的路径。

## 没有网络契约

VD Google 不暴露任何网络 API，也没有套接字：它根本不声明任何权限。代理使用的是**导出文件**，而不是调用某个服务。这就是隐私保证，即使在自动化中也得以保持。

<!-- doc-footer -->

---

> _本文档由 Claude (Anthropic) 生成，并由 VD171 维护。欢迎修正和改进；请参阅 [CONTRIBUTING.md](CONTRIBUTING.md)。_
