> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · Español · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Contribuir

Gracias por tu interés en VD Google. Las contribuciones son bienvenidas: traducciones, informes de
errores, documentación y código.

## Reglas básicas

- **VD Google lee la verdad y nunca falsifica nada.** Las contribuciones deben mantenerlo así. Un
  negativo real sigue siendo negativo; una caché obsoleta se etiqueta como almacenada en caché; un
  valor ausente es Desconocido. No suavices una lectura para que se vea mejor.
- **La privacidad es absoluta.** La app no declara permisos y la WebUI no contacta ningún origen
  remoto. Nunca agregues un permiso, una llamada de red ni nada que envíe datos fuera del
  dispositivo.
- **La app y la WebUI son idénticas.** Cualquier cambio en lo que se muestra, o en el informe
  exportado, debe aterrizar en ambas, con la misma redacción. Nunca deben derivar.
- **Las etiquetas viven una sola vez, en 21 idiomas.** Cada cadena visible para el usuario está
  definida para la app (`res/values*/strings.xml`) y la WebUI (`webroot/lang/`). Si agregas o cambias
  una etiqueta, actualiza todos los idiomas.

## Traducciones

Las traducciones son la contribución más fácil y valiosa. El readme se traduce por idioma
(`README.<lang>.md`), las cadenas de la app y la WebUI por idioma, y esta documentación bajo
`docs/<lang>/`. Mantén la terminología coherente dentro de un idioma, ajústate al tono existente y
nunca traduzcas automáticamente un término técnico del que no estés seguro.

## Reportar un error

Abre una incidencia con el modelo de tu dispositivo, la versión de Android y, si se trata de una
lectura, una **exportación** (no contiene datos personales). La sección de diagnósticos de la
exportación está hecha exactamente para esto.

## Código

- Compila en release, nunca en debug. Prueba en un dispositivo real con root.
- Mantén los comentarios fuera del código; los nombres explican la intención. El único encabezado es
  el banner de licencia al principio de cada archivo fuente.
- Ajústate al estilo existente y mantén los cambios enfocados.

## Licencia

Al contribuir, aceptas que tu contribución se licencia bajo la **GNU AGPL-3.0-or-later** del
proyecto.

<!-- doc-footer -->

---

> _Esta documentación se generó con Claude (Anthropic) y la mantiene VD171. Las correcciones y mejoras son bienvenidas; consulta [CONTRIBUTING.md](CONTRIBUTING.md)._
