> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · Español · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Política de seguridad

VD Google es una herramienta de privacidad. Se ejecuta como root y lee estado sensible, así que los
informes de seguridad se toman en serio.

## Reportar una vulnerabilidad

Por favor reporta los problemas de seguridad **en privado**, no en una incidencia pública:

- Envía un correo a **vd.priv8@pm.me**.
- Describe el problema, la versión afectada y cómo reproducirlo.
- Si se trata de una exposición de datos, describe la clase de problema; no necesitas adjuntar un
  exploit funcional.

Recibirás una respuesta tan pronto como sea posible. Por favor concede un plazo razonable para
corregir el problema antes de cualquier divulgación pública.

## Qué cuenta como un problema de seguridad

Debido a lo que promete VD Google, estos son especialmente importantes:

- Cualquier cosa que hiciera que la app alcanzara la red, o enviara algún dato fuera del dispositivo.
- Cualquier cosa que filtrara datos personales a una **exportación** (un número de teléfono, una
  credencial, el contenido de un mensaje, el nombre de un contacto o una huella por identidad).
- Cualquier cosa que permitiera que la única acción de saneamiento cambie el estado sin confirmación
  o sin quedar registrada.

## Qué no es un problema de seguridad

- Una lectura que parece "incorrecta" pero es la verdad honesta (por ejemplo, líneas de RCS del lado
  de la operadora que leen No mientras RCS corre sobre el backend de Google). Consulta la [guía del
  usuario](USER-GUIDE.md).
- La ausencia de un veredicto de Play Integrity. Ese veredicto no está en el dispositivo y VD Google
  no lo inventará.

## Versiones compatibles

Solo se admite la última versión publicada. Por favor reproduce en la compilación más reciente antes
de reportar.
