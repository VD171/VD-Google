> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · 中文

---

# 架构

VD Google 是如何构建的，以及为什么这样构建。本页解释设计与原则。它不会列出每个阅读者所用的确切
文件、数据表或查询语句 - 那些内容按设计只存在于源代码中，这样应用和文档便永远不会演变成一份教人伪
造同样数值的操作指南。

## 设计目标

1. **读取真相。** 展示 Google 自家应用已经记录下来的内容，加以解释，绝不臆造任何数值。当设备中没
   有答案时，就说未知。
2. **零网络。** 应用完全不声明任何权限；WebUI 阻止一切远程来源。任何东西都永远不会被发送到任何地
   方。
3. **一种行为，两副面孔。** 应用和 WebUI 模块保持绝对一致 - 相同的信号、相同的措辞、相同的导出报
   告。
4. **诚实胜过讨好。** 真正的否定结果就显示为否定；过期的缓存就标注为缓存；缺失的数值就是未知。检测
   绝不会为了显得好看而被弱化。

## 两副面孔

- **应用** 使用 Kotlin 搭配 Jetpack Compose（Material 3）编写。它通过 libsu 的 `RootService` 获
  取 root，绝不直接调用 `su`，并在那个特权服务内部进行读取。
- **WebUI 模块** 是一个由你的 root 管理器（KernelSU、Magisk 或 APatch）提供服务的小型 HTML/JS
  web root。它通过管理器的 root 桥接执行相同的读取。严格的 Content-Security-Policy 禁止任何远程
  来源，因此该页面即便在原理上也无法向外联网回传。

两者都由一套共享的、涵盖 21 种语言的字符串生成，因此一个标签绝不会被翻译两遍。

## 读取数据库：内置的 sqlite3

VD Google 读取的部分状态位于属于 Google 应用的 SQLite 数据库中。Android 自带自己的 SQLite，但框
架自带的 SQLite 在打开另一个应用数据库的旁置副本时并不可靠：在某些厂商 ROM 上，它对*每一个*数据库
都会失败，什么也不返回。为了在每台设备上都以同样的方式读取，VD Google 内置了自己的 `sqlite3` 二进
制文件（arm64 和 arm32），并以 root 身份通过它运行查询。应用和 WebUI 使用完全相同的二进制文件，因
此它们始终读取同一个引擎、得到同一个结果。该二进制文件的来源与校验和记录在[内置二进制文件](BINARIES.md)
中。

读取操作会在打开数据库之前将其旁置复制一份，从不触碰正在使用的文件，也从不复制共享内存索引 - 正在运
行的数据库可能会把该索引报告为损坏。

## RCS 信号

诚实读取 RCS 最难之处在于那些显而易见的标志会说谎。简单的"可用"标志、各项功能标志以及开通状态，在
RCS 工作得完美无缺的线路上都可能读出否定，因为它们是缓存，或者因为该线路运行在 Google 自家后端
（Jibe）而非运营商的 IMS 网络上。VD Google 从真实的聊天配置和开通引擎计算出单一的**连接**判定，它
在不同版本的 Android 和 Google Messages 上都保持正确，并把那些说谎的标志清楚地标注为缓存呈现出来，
让它们提供信息而不致误导。关于每一行的含义，请参阅[用户指南](USER-GUIDE.md)。

## 导出

导出是一份经过净化的 JSON 快照。在写入任何内容之前，一个净化器会先运行：它会删除看起来像凭据的键和
值，涂抹掉长串数字，绝不输出你的电话号码、消息内容、联系人姓名或原始的能力数据块（capability
blob）。可选的诊断部分只携带架构名称、计数、大小和版本 - 即便在那里，嵌入某个名称里的任何按身份的指
纹也会被折叠抹去。应用和 WebUI 产生相同的架构。

## VD Google 永不做的事

- 它绝不声明网络权限，WebUI 也绝不联系任何远程来源。
- 它绝不臆造、伪造或隐藏任何数值。
- 它绝不报告 Play Integrity 判定，因为该判定并不在设备上。
- 它只通过一次明确的、经确认的、本地记录在案的操作来改变状态。
