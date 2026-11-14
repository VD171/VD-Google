<!--
  VD GOOGLE: RCS, Google Wallet e atestações Play Integrity, lidos no aparelho.
  Copyright (C) 2026  VD171. SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

<p align="center">
  <img src="images/logo-wordmark.png" alt="VD Google" width="320">
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases/latest"><img src="https://img.shields.io/github/v/release/VD171/VD-Google?label=release&color=0969da" alt="Release"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-AGPL--3.0--or--later-1a7f37" alt="License"></a>
  <a href="https://github.com/VD171/VD-Google/releases"><img src="https://img.shields.io/github/downloads/VD171/VD-Google/total?label=downloads&color=8250df" alt="Downloads"></a>
  <a href="https://github.com/VD171/VD-Google/stargazers"><img src="https://img.shields.io/github/stars/VD171/VD-Google?color=d29922" alt="Stars"></a>
</p>

<p align="center">
  <b>Seus serviços Google, lidos no seu aparelho. Sem internet, nunca.</b><br>
  O VD Google mostra o que o próprio Google registra no seu celular sobre RCS, Wallet e Play Integrity, e explica cada item, sem mandar nada para lugar nenhum.
</p>

<p align="center">
  <a href="https://github.com/VD171/VD-Google/releases">Releases</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/pt-Home">Wiki</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/pt-USER-GUIDE">Guia do usuário</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/pt-ARCHITECTURE">Arquitetura</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/pt-PRIVACY">Privacidade</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/pt-BINARIES">Binários embarcados</a> ·
  <a href="https://github.com/VD171/VD-Google/wiki/pt-FILE-STRUCTURE">Estrutura de arquivos</a> ·
  <a href="CHANGELOG.md">Changelog</a> ·
  <a href="https://xdaforums.com/t/VD-Google.4803837/">XDA thread</a>
</p>

> **Languages:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Українська](README.uk.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<p align="center">
  <img src="images/vdgoogle-01.png" height="420"/>
  <img src="images/vdgoogle-02.png" height="420"/>
  <img src="images/vdgoogle-03.png" height="420"/>
  <img src="images/vdgoogle-04.png" height="420"/>
  <img src="images/vdgoogle-05.png" height="420"/>
</p>

---

## App ou WebUI

Cada versão vem de duas formas, e as duas leem as mesmas coisas no seu aparelho, sem internet:

- **App**: o `.apk`, um aplicativo Android comum.
- **WebUI**: o `.zip`, um módulo que você instala no seu gerenciador de root (KernelSU, APatch ou Magisk) e abre por lá.

De qualquer forma, é necessário root.

## O que ele mostra

### RCS
Se o RCS está disponível para a sua linha, o seu número RCS, o estado do RCS, a versão da configuração, quantos dos seus contatos têm RCS, a autorização da operadora, a presença (UCE) e os horários relacionados (provisionamento, sincronização, última mensagem, expiração do token e mais). Também mostra se o Gemini no Google Messages está pronto: o Magic Compose (a ajuda de escrita do Gemini), o modelo no aparelho (AICore, que roda o Gemini Nano) e o chat com o Gemini, que é entregue via RCS.

### Google Carteira
Se o seu aparelho está atestado para pagamentos, se tem hardware NFC, se a Carteira está instalada, o estado do serviço de pagamento e do pagamento por aproximação, se o seu bloqueio de tela é aceito e se algum cartão já foi adicionado.

### DroidGuard
Se o Google Play reconhece este aparelho como certificado. É a certificação do Play Protect exibida na Play Store. Atestações que este aparelho tem em cache para o Play Integrity e o DroidGuard. O horário mostrado é quando cada uma expira.

### Atestações (Play Integrity)
Os apps que pediram recentemente ao Google Play Integrity para atestar o seu aparelho, cada um com o seu ícone, o seu nome, o horário completo do último pedido e quantos pedidos fez na janela atual.

Cada item traz uma explicação curta, em linguagem simples, do que ele realmente significa.

## Privacidade: sem internet

O VD Google não declara **nenhuma permissão**, nem mesmo acesso à internet. Nada do que ele lê sai do seu aparelho. Você pode conferir isso no manifesto e no código-fonte.

## Requisitos

- Android 8.0 (API 26) ou mais recente.
- **É necessário root.** As informações acima ficam em áreas protegidas do Android, acessíveis apenas ao sistema. O VD Google as lê com acesso de superusuário e não faz mais nada com elas. Sem root, o app apenas mostra uma tela explicando por que o root é necessário.

## Horários

Todos os horários são exibidos no fuso atual do seu aparelho, e o fuso é sempre identificado.

## Documentação

- **[Wiki](https://github.com/VD171/VD-Google/wiki/pt-Home)**: toda a documentação, para navegar online.
- **[Guia do usuário](https://github.com/VD171/VD-Google/wiki/pt-USER-GUIDE)**: o que cada tela lê e o que cada resultado significa.
- **[Arquitetura](https://github.com/VD171/VD-Google/wiki/pt-ARCHITECTURE)** e **[Estrutura de arquivos](https://github.com/VD171/VD-Google/wiki/pt-FILE-STRUCTURE)**: como funciona e onde cada coisa fica.
- **[Privacidade](https://github.com/VD171/VD-Google/wiki/pt-PRIVACY)**: sem permissão de internet, toda leitura passa pelo root, nada nunca sai do aparelho.
- **[Binários embarcados](https://github.com/VD171/VD-Google/wiki/pt-BINARIES)**: procedência e SHA-256 de cada binário pré-compilado, para auditoria completa.
- **[Contribuindo](https://github.com/VD171/VD-Google/wiki/pt-CONTRIBUTING)** e **[Segurança](https://github.com/VD171/VD-Google/wiki/pt-SECURITY)**.
- **[Changelog](CHANGELOG.md)** e **[Código de conduta](https://github.com/VD171/VD-Google/wiki/pt-CODE_OF_CONDUCT)**.

<!-- /vd-docs -->

## Veja também

Projetos irmãos do mesmo autor:

- **[VDInfos](https://github.com/VD171/VD-Infos)**: um debugger de métodos do aparelho que lê cada item por todos os métodos capazes de lê-lo e os compara lado a lado.
- **[VDBackup](https://github.com/VD171/VD-Backup)**: backup e restauração completos de apps e dados como root, controlados inteiramente por uma WebUI.
- **[COPG-VD](https://github.com/VD171/COPG-VD)**: um spoofer de dispositivo que transforma um fingerprint novo em um perfil coerente e consistente.
- **[VD-dropbear](https://github.com/VD171/VD-dropbear)**: um daemon SSH autônomo com root (Dropbear).

## Contatos

- https://vd171.ru
- https://vd.priv8.ru
- *Telegram:* @VD_Priv8 https://t.me/VD_Priv8
- *Channel:* @Priv8_VD https://t.me/Priv8_VD
- *Discord:* @VD.Priv8 https://discord.com/users/1296831918989639721
- *E-mail:* vd.priv8@pm.me
- *XDA-Developers:* @VD171 https://xdaforums.com/m/vd171.4699873/
- *XDA thread:* https://xdaforums.com/t/VD-Google.4803837/
- *GitHub:* @VD171 https://github.com/VD171

## Licença

**GNU AGPL-3.0-or-later**: veja [LICENSE](LICENSE). Copyleft, incluindo a cláusula de rede: quem rodar uma versão modificada (mesmo como serviço) tem que oferecer a fonte. Escolhida de propósito pra manter os forks abertos.

## Marcas registradas

O VD Google é um projeto independente e não oficial. Não é afiliado, endossado nem patrocinado pela Google LLC. "Google", "Google Wallet", "Google Play", "Play Integrity", "Android" e os nomes e logotipos relacionados são marcas registradas da Google LLC. O VD Google apenas lê o que já está no seu aparelho e não envia nada para lugar nenhum.

## Tags

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
