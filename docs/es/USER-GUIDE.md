> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · Español · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Guía del usuario de VD Google

Un recorrido amigable y completo por VD Google: qué muestra, qué significa cada línea y cómo leer tu
dispositivo con honestidad. VD Google lee el estado de RCS, Google Wallet y las atestaciones de Play
Integrity que solicitan las apps, y explica cada elemento en palabras sencillas. Nunca toca internet,
y nunca cambia nada a menos que se lo pidas.

## Tabla de contenidos

- [1. Qué es VD Google](#1-qué-es-vd-google)
- [2. Qué no es](#2-qué-no-es)
- [3. Requisitos](#3-requisitos)
- [4. App o WebUI](#4-app-o-webui)
- [5. Instalación y primer uso](#5-instalación-y-primer-uso)
- [6. Las pestañas](#6-las-pestañas)
  - [6.1 Acerca de](#61-acerca-de)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Atestaciones](#65-atestaciones)
- [7. Exporta tu informe](#7-exporta-tu-informe)
- [8. Acciones que cambian el estado](#8-acciones-que-cambian-el-estado)
- [9. Idioma y tema](#9-idioma-y-tema)
- [10. Preguntas frecuentes](#10-preguntas-frecuentes)

## 1. Qué es VD Google

VD Google es un lector. Observa lo que las propias apps de Google ya almacenan en tu dispositivo
sobre tres cosas, y explica cada una para que de verdad la entiendas:

- **RCS**: si el chat moderno realmente funciona en tu línea, con quién puedes chatear y si Gemini en
  Google Messages está listo.
- **Google Wallet**: si tu dispositivo está configurado y atestado para pagos sin contacto.
- **Atestaciones de Play Integrity**: qué apps han pedido a Android que demuestre que tu dispositivo
  es auténtico, y si Google actualmente lo certifica.

Todo se lee localmente, a través de root, y se muestra con una explicación. No se inventa ningún
valor: cuando el dispositivo no contiene una respuesta, VD Google dice "Desconocido" en lugar de
adivinar.

## 2. Qué no es

**VD Google no es un verificador de integridad.** No ejecuta ni reporta ningún veredicto basic /
device / strong. Ese resultado vive únicamente dentro del token cifrado que una app envía a su propio
servidor, y nunca se almacena en el dispositivo, así que ningún lector honesto puede mostrarlo. Las
herramientas que afirman mostrar tu "veredicto de Play Integrity" localmente te muestran una
conjetura en caché o una solicitud que ellas mismas hicieron, no la respuesta real.

VD Google tampoco falsea, oculta ni inventa nada. Lee la verdad. Si un valor te parece incorrecto, el
objetivo de esta guía es explicar por qué la verdad se ve así.

## 3. Requisitos

- **Android 8.0 (API 26) o más reciente.**
- **Root.** Los datos que lee VD Google viven en áreas protegidas y exclusivas del sistema de
  Android, alcanzables solo con root. Cualquier solución de root moderna funciona: KernelSU, KernelSU
  Next, Magisk o APatch.
- **Sin internet.** VD Google no declara ningún permiso en absoluto, así que no hay nada que permitir
  ni nada que bloquear.

## 4. App o WebUI

VD Google se distribuye de dos maneras, y muestran exactamente la misma información:

- **La app (APK).** Una app de Android normal que instalas y abres. Pide root la primera vez, como
  cualquier app de root.
- **El módulo WebUI (ZIP).** Flashéalo en tu gestor de root (KernelSU, Magisk o APatch) y ábrelo
  desde ahí. Nada se instala como app; corre dentro de la WebUI de tu gestor de root.

Elige el que prefieras. Las pantallas, la redacción y el informe exportado son idénticos entre los
dos.

## 5. Instalación y primer uso

**App:** instala la APK y ábrela. La primera vez, concede root cuando tu gestor de root lo pida. Si
deniegas o descartas root, VD Google muestra una pantalla que explica por qué se requiere root y
ofrece volver a intentarlo.

**WebUI:** flashea el ZIP como módulo en tu gestor de root, luego abre la WebUI del módulo desde el
gestor. Lee en cuanto se abre.

En la primera apertura puedes elegir tu idioma; puedes cambiarlo más tarde desde el icono del
engranaje en la parte superior.

## 6. Las pestañas

En la parte inferior hay cinco pestañas: Acerca de, DroidGuard, RCS, Wallet y Atestaciones. Acerca de
se abre primero.

### 6.1 Acerca de

Qué es la app, la promesa de privacidad, la versión y los enlaces. Lee la sección de privacidad una
vez: es el objetivo entero de la app.

### 6.2 RCS

Esta es la pestaña más rica. Las marcas de tiempo siempre se muestran en la zona horaria de tu
dispositivo, con la zona etiquetada.

- **Conexión**: la única línea que dice la verdad. Se calcula a partir de la configuración de chat
  real y el motor de aprovisionamiento, de modo que dice Conectado cuando RCS realmente funciona en
  tu línea, incluso en versiones de Android más nuevas donde las banderas antiguas mienten. **En caso
  de duda, confía en esta línea.**
- **Operadora**: la operadora móvil de tu línea (nombre y MCC/MNC), tomada de tu SIM de datos en
  teléfonos con doble SIM.
- **Bandera de disponibilidad (en caché)**: una bandera en caché que la app de mensajería mantiene
  para tu propio número. Puede quedar desactualizada después de que RCS ya funcione, así que un "No"
  aquí no significa que RCS esté apagado. Confía en la línea de Conexión de arriba.
- **Tu número de RCS**: el número registrado para RCS, enmascarado por defecto. Toca el ojo para
  revelarlo.
- **Estado de aprovisionamiento (en caché)** y **Versión de configuración**: valores internos que
  mantiene la app de mensajería. Pueden leer 0 incluso mientras RCS funciona. Consulta la nota sobre
  operadora frente al backend de Google más abajo.
- **Contactos compatibles con RCS**: cuántos de tus contactos se sabe actualmente que admiten chat
  por RCS.
- **Bandera de característica (en caché)**: si la app de mensajería tiene activada la característica
  RCS; en caché, así que puede ir con retraso.
- **Habilitación de operadora**, **Presencia (UCE)**, **Servicio IMS presente**: estas describen la
  ruta de RCS de la *operadora* (la red IMS del operador). Un "No" o "0" aquí es normal cuando tu RCS
  corre a través del propio backend de Google en lugar de tu operadora. Consulta más abajo.
- **Gemini**: si Gemini en Google Messages está listo: **Magic Compose** (la ayuda de escritura de
  Gemini), **AICore** (el motor en el dispositivo que ejecuta Gemini Nano) y **chat de Gemini**, que
  se entrega sobre RCS y por tanto necesita RCS conectado.
- **Marcas de tiempo**: aprovisionamiento, sincronización, último mensaje, caducidad de token y más.

**RCS de operadora frente al backend de Google.** Hay dos maneras en que RCS llega a tu teléfono. Una
es el RCS de tu *operadora* sobre su red IMS; la otra es el *propio backend de Google* (Jibe), que
funciona por encima, independiente de la operadora. Si tu RCS corre a través del backend de Google,
como ocurre para la mayoría de la gente, entonces las líneas del lado de la operadora (habilitación
de operadora, presencia/UCE, servicio IMS, versión de configuración) son genuinamente "No" o "0", y
eso es correcto, no una caché obsoleta. Tu RCS puede haber funcionado así durante años. La línea de
Conexión es la que te dice que está funcionando.

### 6.3 Wallet

- **Dispositivo atestado**: si Google juzgó este dispositivo lo bastante auténtico y seguro como para
  guardar tarjetas de pago. Es el último resultado que Google guardó en este dispositivo, así que
  puede ir con retraso respecto a la app de Wallet hasta que Google vuelva a verificar. Si el
  resultado guardado falta, VD Google muestra **Desconocido** en lugar de un "No" engañoso.
- **Última verificación**: cuándo se completó por última vez esa comprobación de seguridad.
- **Hardware NFC**, **Wallet instalada**, **Servicio de Wallet**, **Tap and pay**, **Bloqueo de
  pantalla aceptado**, **Tarjeta agregada alguna vez**: las piezas que hacen posible el pago sin
  contacto.

### 6.4 DroidGuard

- **Certificado por Play Protect**: si Google Play reconoce tu dispositivo como certificado (la
  certificación que se muestra en la Play Store).
- **Caché de atestación**: las atestaciones de Play Integrity y DroidGuard que tu dispositivo tiene en
  caché, cada una con cuándo caduca.

### 6.5 Atestaciones

El historial de qué apps solicitaron Play Integrity en tu dispositivo: icono, nombre, cuándo
preguntaron por última vez y cuántas veces. Esto te permite saber si una app determinada verifica la
integridad o no. Usa el cuadro de búsqueda para filtrar por nombre de app o paquete.

## 7. Exporta tu informe

El icono de descarga escribe un informe **JSON** con el estado de tu dispositivo, RCS, Wallet y
atestación. Puedes compartirlo o guardarlo como archivo. El informe no contiene **ningún número de
teléfono, ninguna credencial ni ningún contenido personal**: se sanea antes de escribirse. También
incluye una sección opcional de **diagnósticos** (nombres de esquema, recuentos y versiones, nunca
tus datos) que ayuda a solucionar problemas específicos del dispositivo. La app y la WebUI escriben el
mismo informe.

## 8. Acciones que cambian el estado

VD Google es mayormente de solo lectura, pero puede realizar una **acción de saneamiento**: borrar el
resultado de atestación en caché de Google Wallet, lo que permite que una futura reverificación
empiece de cero. Cada acción que cambia el estado te pide confirmación primero y se escribe en un
registro JSON local en el dispositivo. Nada sale de tu teléfono.

## 9. Idioma y tema

VD Google habla 21 idiomas. Elige el tuyo en el primer uso, o cámbialo en cualquier momento desde el
icono del engranaje (también disponible en la pantalla de root denegado). El icono de brillo cambia
entre claro y oscuro.

## 10. Preguntas frecuentes

**RCS dice Conectado pero varias líneas dicen No o 0. ¿Por qué?** Porque tu RCS corre a través del
propio backend de Google, no de tu operadora. Las líneas del lado de la operadora son correctamente
negativas. Consulta [6.2 RCS](#62-rcs).

**Dispositivo atestado dice Desconocido.** El resultado de Wallet en caché falta en tu dispositivo.
Eso es honesto: VD Google no mostrará un "No" que no pueda respaldar.

**¿Dónde está mi veredicto de Play Integrity?** No está en tu dispositivo. Consulta [2. Qué no
es](#2-qué-no-es).

**¿Funciona sin root?** No. Los datos viven en áreas exclusivas del sistema.

<!-- doc-footer -->

---

> _Esta documentación se generó con Claude (Anthropic) y la mantiene VD171. Las correcciones y mejoras son bienvenidas; consulta [CONTRIBUTING.md](CONTRIBUTING.md)._
