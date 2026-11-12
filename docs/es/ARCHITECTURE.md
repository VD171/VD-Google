> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · Español · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Arquitectura

Cómo está construido VD Google y por qué. Esta página explica el diseño y los principios. No enumera
los archivos, tablas o consultas exactos que usa cada lector - eso vive únicamente en el código
fuente, por diseño, para que la app y la documentación nunca deriven hacia un manual para alguien que
quiera falsificar los mismos valores.

## Objetivos de diseño

1. **Leer la verdad.** Mostrar lo que las propias apps de Google ya registraron, explicarlo y nunca
   inventar un valor. Cuando el dispositivo no contiene una respuesta, decir Desconocido.
2. **Cero red.** La app no declara ningún permiso en absoluto; la WebUI bloquea todo origen remoto.
   Nada se envía a ninguna parte, jamás.
3. **Un comportamiento, dos caras.** La app y el módulo WebUI se mantienen absolutamente idénticos -
   las mismas señales, la misma redacción, el mismo informe exportado.
4. **Honesto antes que halagador.** Un negativo real se muestra como negativo; una caché obsoleta se
   etiqueta como almacenada en caché; un valor ausente es Desconocido. La detección nunca se suaviza
   para verse mejor.

## Las dos caras

- **La app** es Kotlin con Jetpack Compose (Material 3). Obtiene root a través del `RootService` de
  libsu, nunca invocando `su` directamente, y lee dentro de ese servicio privilegiado.
- **El módulo WebUI** es una pequeña raíz web HTML/JS servida por tu gestor de root (KernelSU, Magisk
  o APatch). Realiza las mismas lecturas a través del puente de root del gestor. Una
  Content-Security-Policy estricta prohíbe cualquier origen remoto, de modo que la página no puede
  llamar a casa ni siquiera en principio.

Ambas se generan a partir de un único conjunto compartido de cadenas en 21 idiomas, de modo que una
etiqueta nunca se traduce dos veces.

## Leer bases de datos: el sqlite3 incluido

Parte del estado que lee VD Google vive en bases de datos SQLite que pertenecen a las apps de Google.
Android incluye su propio SQLite, pero el SQLite del framework no es fiable para abrir la copia
apartada de la base de datos de otra app: en algunas ROM de fabricante falla con *todas* las bases de
datos, devolviendo nada. Para leer de la misma manera en todos los dispositivos, VD Google incluye su
propio binario `sqlite3` (arm64 y arm32) y ejecuta las consultas a través de él como root. La app y
la WebUI usan el binario idéntico, de modo que siempre leen el mismo motor y el mismo resultado. La
procedencia y las sumas de verificación de ese binario están documentadas en [Binarios
incluidos](BINARIES.md).

Las lecturas copian la base de datos a un lado antes de abrirla, sin tocar nunca el archivo en vivo, y
nunca copian el índice de memoria compartida, que una base de datos en ejecución puede reportar como
malformado.

## La señal de RCS

Lo más difícil de leer RCS de forma honesta es que las banderas obvias mienten. La simple bandera de
"disponible", las banderas de características y el estado de aprovisionamiento pueden leerse todas en
negativo en una línea donde RCS funciona a la perfección, porque son cachés o porque la línea corre
sobre el propio backend de Google (Jibe) en lugar de la red IMS de la operadora. VD Google calcula un
único veredicto de **Conexión** a partir de la configuración de chat real y el motor de
aprovisionamiento, que se mantiene correcto a través de las versiones de Android y de Google Messages,
y presenta las banderas que mienten claramente etiquetadas como almacenadas en caché, de modo que
informen sin confundir. Consulta la [guía del usuario](USER-GUIDE.md) para saber qué significa cada
línea.

## La exportación

La exportación es una instantánea JSON saneada. Un saneador se ejecuta antes de que se escriba nada:
descarta claves y valores que parecen credenciales, redacta largas secuencias de dígitos y nunca
emite tu número de teléfono, el contenido de mensajes, los nombres de contactos ni blobs de
capacidades en bruto. La sección opcional de diagnósticos lleva solo nombres de esquema, recuentos,
tamaños y versiones - e incluso ahí, cualquier huella por identidad incrustada en un nombre se
colapsa y elimina. La app y la WebUI producen el mismo esquema.

## Lo que VD Google nunca hace

- Nunca declara un permiso de red, y la WebUI nunca contacta un origen remoto.
- Nunca fabrica, falsea ni oculta un valor.
- Nunca reporta un veredicto de Play Integrity, porque ese veredicto no está en el dispositivo.
- Cambia el estado solo mediante una acción explícita, confirmada y registrada localmente.
