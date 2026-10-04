<!--
  VD GOOGLE - RCS, Google Wallet e atestações Play Integrity, lidos no aparelho.
  Copyright (C) 2026  VD171 - SPDX-License-Identifier: AGPL-3.0-or-later
-->

# VD Google

**Seus serviços Google, lidos no seu aparelho. Sem internet, nunca.**

O VD Google mostra o que o próprio Google registra no seu celular sobre três coisas, e explica o que cada item significa, sem mandar nada para lugar nenhum.

> **Idiomas:** [English](README.md) · [العربية](README.ar.md) · [Čeština](README.cs.md) · [Deutsch](README.de.md) · [Español](README.es.md) · [فارسی](README.fa.md) · [Français](README.fr.md) · [हिन्दी](README.hi.md) · [Bahasa Indonesia](README.in.md) · [Italiano](README.it.md) · [日本語](README.ja.md) · [한국어](README.ko.md) · [Nederlands](README.nl.md) · [Polski](README.pl.md) · [Português](README.pt.md) · [Русский](README.ru.md) · [Svenska](README.sv.md) · [ไทย](README.th.md) · [Türkçe](README.tr.md) · [Tiếng Việt](README.vi.md) · [中文](README.zh.md)

<img src="images/vdgoogle-01.png" height="420"/> <img src="images/vdgoogle-02.png" height="420"/> <img src="images/vdgoogle-03.png" height="420"/> <img src="images/vdgoogle-04.png" height="420"/>

## O que ele mostra

### RCS
Se o RCS está disponível para a sua linha, o seu número RCS, o estado do RCS, a versão da configuração, quantos dos seus contatos têm RCS, a autorização da operadora, a presença (UCE) e os horários relacionados (provisionamento, sincronização, última mensagem, expiração do token e mais).

### Google Carteira
Se o seu aparelho está atestado para pagamentos, se tem hardware NFC, se a Carteira está instalada, o estado do serviço de pagamento e do pagamento por aproximação, se o seu bloqueio de tela é aceito e se algum cartão já foi adicionado.

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

## Contatos

* https://vd171.ru
* https://vd.priv8.ru
* **Telegram:** @VD_Priv8 https://t.me/VD_Priv8
* **Discord:** @VD.Priv8 https://discord.com/users/1296831918989639721
* **E-mail:** vd.priv8@pm.me
* **XDA-Developers:** @VD171 https://xdaforums.com/m/vd171.4699873/
* **GitHub:** @VD171 https://github.com/VD171

## Licença

**GNU AGPL-3.0-or-later** - veja [LICENSE](LICENSE). Copyleft, incluindo a cláusula de rede: quem rodar uma versão modificada (mesmo como serviço) tem que oferecer a fonte. Escolhida de propósito pra manter os forks abertos.

---

`android` `privacy` `security` `rcs` `google-wallet` `play-integrity` `attestation` `nfc` `tap-to-pay` `no-internet` `root` `kernelsu` `magisk` `lsposed` `on-device` `kotlin` `jetpack-compose` `material3` `vdgoogle`
