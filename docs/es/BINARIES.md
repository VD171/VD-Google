> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · Español · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Binarios incluidos: procedencia y auditoría

VD Google promete que nada opaco viaja dentro de él. Se ejecuta como root y lee tu estado más
privado, así que debes poder ver exactamente qué lleva consigo. Esta página enumera la única
herramienta nativa que incluyen la app y el módulo, de dónde proviene y cómo verificarla.

## Principio

VD Google incluye exactamente una herramienta nativa: un comando `sqlite3`, junto con el pequeño
conjunto de bibliotecas compartidas que necesita. Es necesario porque el propio SQLite del framework
de Android falla al abrir las bases de datos de otras apps en algunas ROM de fabricante, lo que haría
que VD Google no leyera nada en esos dispositivos. Consulta [Arquitectura](ARCHITECTURE.md) para
saber por qué.

- **Obtenido de upstream, nunca de un dispositivo.** `sqlite3` y sus bibliotecas provienen del
  repositorio oficial de paquetes de **Termux** (`packages.termux.dev/apt`), los paquetes `.deb`
  firmados, nunca copiados de un Termux instalado en algún teléfono.
- **Los mismos bytes en todas partes.** Los archivos idénticos viajan en la app (bajo `assets/bin/`)
  y en el módulo WebUI (bajo `bin/`), de modo que ambos leen a través de un solo motor y coinciden.
- **La suma de verificación es la identidad.** En cualquier ruta en la que se encuentre una copia, es
  la herramienta real solo si su SHA-256 coincide con el valor registrado.
- **Un rastro de auditoría registrado.** Cada directorio de ABI lleva un `PROVENANCE.json` que
  enumera cada paquete de origen (nombre, versión, URL, SHA-256 del `.deb`) y el SHA-256 de cada
  archivo distribuido. Nada se incluye sin una entrada que puedas comprobar.

## Origen

`.github/scripts/build_bin.sh` ensambla la carga útil en tiempo de compilación, sin compilador y sin
NDK. Para cada ABI:

1. lee el índice de Termux Packages y resuelve el paquete `sqlite` más sus dependencias transitivas
   (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. descarga y extrae los archivos `.deb` oficiales firmados;
3. copia `sqlite3`, luego copia solo el cierre de bibliotecas compartidas que el binario realmente
   necesita (resuelto con `readelf`), omitiendo las bibliotecas que Android siempre proporciona por
   sí mismo;
4. escribe `PROVENANCE.json`.

Los binarios de Termux están enlazados dinámicamente contra las propias bibliotecas de Termux, así
que VD Google distribuye ese cierre junto con `sqlite3` y lo ejecuta con `LD_LIBRARY_PATH` apuntando
al directorio `lib/` incluido. Termux usa sonames versionados (`libz.so.1`, `libreadline.so.8`,
`libncursesw.so.6`), de modo que nunca chocan con las propias bibliotecas del framework de Android. El
`LD_LIBRARY_PATH` se establece solo para el proceso `sqlite3`, nunca de forma global.

## Los archivos

Shell de línea de comandos **SQLite** upstream, paquete de Termux **sqlite 3.54.1-1**. Una carga útil
por ABI: el binario `sqlite3` más su cierre de cuatro bibliotecas (`libreadline.so.8`,
`libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Cada archivo distribuido, con su SHA-256 y
su tamaño:

`arm64-v8a`:

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a`:

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

Las mismas sumas de verificación están registradas en cada `bin/<abi>/PROVENANCE.json`, que se
distribuye junto al binario.

## Verifícalo tú mismo

Desde una copia de trabajo del código fuente, confirma cada archivo distribuido:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Cada hash debe coincidir con la lista de arriba, y las copias de la app y la WebUI deben coincidir
entre sí. Para auditar toda la carga útil de principio a fin, vuelve a obtener las URL fijadas de los
`.deb` enumeradas en cada `PROVENANCE.json`, extráelas y compara los valores SHA-256 de los archivos.

## Para qué se usa

`sqlite3` se ejecuta como root, contra una copia de una base de datos apartada, puramente para
**leer**. Nunca se usa para escribir en ninguna de las bases de datos de Google. Su única función es
permitir que VD Google lea los mismos valores en todos los dispositivos. No se incluye ningún otro
binario nativo.
