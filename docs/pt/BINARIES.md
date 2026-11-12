> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · [한국어](../ko/BINARIES.md) · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · Português · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Українська](../uk/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# Binários embarcados: procedência e auditoria

O VD Google promete que nada opaco é embarcado dentro dele. Ele roda como root e lê seu
estado mais privado, então você precisa poder ver exatamente o que ele carrega. Esta
página lista a única ferramenta nativa que o aplicativo e o módulo embarcam, de onde ela
vem e como conferi-la.

## Princípio

O VD Google embarca exatamente uma ferramenta nativa: um comando `sqlite3`, com o pequeno
conjunto de bibliotecas compartilhadas de que ele precisa. Ela é necessária porque o
próprio SQLite do framework do Android falha ao abrir os bancos de dados de outros
aplicativos em algumas ROMs de fabricantes, o que faria o VD Google não ler nada nesses
dispositivos. Veja [Arquitetura](ARCHITECTURE.md) para entender o porquê.

- **Obtido da fonte oficial, nunca de um dispositivo.** O `sqlite3` e suas bibliotecas vêm
  do repositório oficial de pacotes do **Termux** (`packages.termux.dev/apt`), dos pacotes
  `.deb` assinados, nunca copiados de um Termux instalado em algum telefone.
- **Os mesmos bytes em todo lugar.** Os arquivos idênticos são embarcados no aplicativo
  (sob `assets/bin/`) e no módulo WebUI (sob `bin/`), então ambos leem por um único motor e
  concordam.
- **A soma de verificação é a identidade.** Seja qual for o caminho em que uma cópia
  esteja, ela só é a ferramenta real se o seu SHA-256 corresponder ao valor registrado.
- **Uma trilha de auditoria registrada.** Cada diretório de ABI carrega um
  `PROVENANCE.json` listando cada pacote de origem (nome, versão, URL, SHA-256 do `.deb`)
  e o SHA-256 de cada arquivo distribuído. Nada é embarcado sem uma entrada que você possa
  conferir.

## Origem

O `.github/scripts/build_bin.sh` monta o payload em tempo de build, sem compilador e sem
NDK. Para cada ABI, ele:

1. lê o índice do Termux Packages e resolve o pacote `sqlite` mais as suas dependências
   transitivas (`readline`, `ncurses`, `zlib`, `libandroid-support`);
2. baixa e extrai os arquivos `.deb` oficiais assinados;
3. copia o `sqlite3` e, em seguida, copia apenas o fecho de bibliotecas compartilhadas de
   que o binário realmente precisa (resolvido com `readelf`), pulando as bibliotecas que o
   Android sempre fornece por si mesmo;
4. grava o `PROVENANCE.json`.

Os binários do Termux são vinculados dinamicamente às próprias bibliotecas do Termux,
então o VD Google distribui esse fecho junto com o `sqlite3` e o executa com
`LD_LIBRARY_PATH` apontando para o diretório `lib/` embarcado. O Termux usa sonames
versionados (`libz.so.1`, `libreadline.so.8`, `libncursesw.so.6`), então eles nunca
colidem com as próprias bibliotecas do framework do Android. O `LD_LIBRARY_PATH` é
definido apenas para o processo do `sqlite3`, nunca globalmente.

## Os arquivos

Shell de linha de comando **SQLite** oficial, pacote Termux **sqlite 3.54.1-1**. Um
payload por ABI: o binário `sqlite3` mais o seu fecho de quatro bibliotecas
(`libreadline.so.8`, `libncursesw.so.6`, `libz.so.1`, `libandroid-support.so`). Cada
arquivo distribuído, com o seu SHA-256 e tamanho:

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

As mesmas somas de verificação estão registradas em cada `bin/<abi>/PROVENANCE.json`, que é
distribuído ao lado do binário.

## Verifique você mesmo

A partir de um checkout do código-fonte, confirme cada arquivo distribuído:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

Cada hash deve corresponder à listagem acima, e as cópias do aplicativo e da WebUI devem
corresponder uma à outra. Para auditar o payload inteiro de ponta a ponta, baixe novamente
as URLs fixadas dos `.deb` listadas em cada `PROVENANCE.json`, extraia-as e compare os
valores de SHA-256 dos arquivos.

## Para que serve

O `sqlite3` é executado como root, contra uma cópia de um banco de dados tirada para o
lado, puramente para **ler**. Ele nunca é usado para gravar em nenhum dos bancos de dados
do Google. Seu único trabalho é permitir que o VD Google leia os mesmos valores em todo
dispositivo. Nenhum outro binário nativo é embarcado.

<!-- doc-footer -->

---

> _Esta documentação foi gerada com o Claude (Anthropic) e é mantida por VD171. Correções e melhorias são bem-vindas; veja [CONTRIBUTING.md](CONTRIBUTING.md)._
