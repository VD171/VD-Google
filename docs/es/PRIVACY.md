> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · Español · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Privacidad

La privacidad no es una función de VD Google - es el objetivo entero. Esta página expone exactamente
qué hace y qué no hace la app con tus datos, en términos claros que puedes verificar.

## La promesa

- **No se otorga ningún permiso de internet.** El manifiesto de la app no declara ningún permiso en
  absoluto - ni internet, ni almacenamiento, ni contactos, nada. No hay nada que permitir.
- **Nada se almacena, envía ni transmite** a ningún archivo o servidor fuera de tu dispositivo.
- Cada lectura ocurre **localmente, a través de root**, y permanece en el dispositivo.

## Por qué necesita root en lugar de permisos

El estado de RCS, Wallet y atestación que explica VD Google vive en áreas protegidas y exclusivas del
sistema de Android que las apps normales no pueden ver. VD Google las lee con root en vez de pedirte
permisos amplios. Root es la vía de entrada estrecha y auditable; un montón de permisos en tiempo de
ejecución sería la vía amplia y con fugas. La app elige la estrecha.

## La exportación está saneada

Cuando exportas un informe, VD Google lo sanea antes de escribir:

- **Nunca se incluye:** tu número de teléfono, ninguna credencial, cookie o token, el contenido de
  mensajes, los nombres de contactos ni blobs de capacidades en bruto.
- **Redactado:** las largas secuencias de dígitos y los valores que parecen secretos se reemplazan por
  un marcador de posición.
- **Los diagnósticos** solo llevan estructura (nombres de esquema, recuentos, tamaños, versiones).
  Cualquier huella por identidad que aparezca dentro de un nombre se colapsa y elimina.

El informe es tuyo. Se escribe donde tú elijas, se comparte solo si tú lo compartes, y no contiene
nada que te identifique.

## Las acciones son explícitas y quedan registradas

VD Google puede borrar el resultado de atestación en caché de Google Wallet para que una futura
reverificación empiece de cero. Cualquier acción que cambie el estado:

1. te pide confirmación primero, y
2. se escribe en un registro JSON local en el dispositivo.

Ninguna acción llega jamás a la red, y nada sale de tu teléfono.

## Verifícalo tú mismo

No tienes que aceptar nada de esto por confianza:

- El código fuente es público: <https://github.com/VD171/VD-Google>.
- El manifiesto está en el código fuente; confirma que no declara permisos.
- La Content-Security-Policy de la WebUI está en el código fuente; confirma que bloquea todo origen
  remoto.
- Los binarios incluidos están documentados con sumas de verificación en [Binarios
  incluidos](BINARIES.md).
