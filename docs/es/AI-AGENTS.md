> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · Español · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Agentes de IA

VD Google convierte los propios registros de Google en el dispositivo en algo que un **agente de IA puede leer en un solo paso**. En lugar de abrir varias apps y bases de datos de Google y adivinar su significado, un agente lee una única **exportación** estructurada y sabe exactamente qué informa el dispositivo sobre RCS, Google Wallet y Play Integrity.

Esta página amplía la idea presentada en el README.

## La exportación

Tanto la App como la WebUI producen la misma exportación JSON:

- **App**: la acción **Exportar** comparte o guarda el informe.
- **WebUI**: la acción **Exportar** lo escribe en `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Ambas tienen el mismo esquema, porque la app y la WebUI se mantienen idénticas. No se envía nada a ningún sitio: la exportación se escribe en el dispositivo y se queda ahí.

## Qué contiene

La exportación es un único objeto JSON con estas claves de nivel superior:

- `app`, `version`, `exportedAt`, `timezone` - qué generó el informe y cuándo, en la zona horaria etiquetada del dispositivo.
- `device` - fabricante, modelo, versión de Android y nivel de SDK.
- `rcs` - disponibilidad, estado, versión de la configuración, contactos compatibles, habilitación del operador, presencia (UCE) y las marcas de tiempo relacionadas.
- `wallet` - atestación de pago, hardware NFC, el estado del pago sin contacto, aceptación del bloqueo de pantalla y si alguna vez se añadió una tarjeta.
- `diagnostics` - una sección de autocomprobación, pensada para los informes de errores.

La exportación no contiene **ningún dato personal**: ni número de teléfono, ni credencial, ni contenido de mensajes, ni nombre de contacto. Cada valor es la lectura honesta - un negativo real sigue siendo negativo, una caché obsoleta se etiqueta como en caché, un valor ausente es Desconocido.

## Solo lectura, con una acción registrada

VD Google lee. La única excepción es una acción de saneamiento (borrar el resultado de atestación en caché de Google Wallet para forzar una reevaluación). Toda acción que cambia el estado pide una confirmación en el dispositivo y se escribe en un registro de acciones local en JSON. Un agente que maneja la WebUI ve la misma confirmación y el mismo registro, no hay ninguna vía oculta ni sin registrar.

## Sin contrato de red

VD Google no expone ninguna API de red ni socket: no declara ningún permiso. Un agente consume el **archivo de exportación**, no llama a ningún servicio. Esa es la garantía de privacidad, mantenida incluso en la automatización.

<!-- doc-footer -->

---

> _Esta documentación se generó con Claude (Anthropic) y la mantiene VD171. Las correcciones y mejoras son bienvenidas, consulta [CONTRIBUTING.md](CONTRIBUTING.md)._
