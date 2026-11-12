> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · [Polski](../pl/USER-GUIDE.md) · Português · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Guia do usuário do VD Google

Um passo a passo amigável e completo do VD Google: o que ele mostra, o que cada linha
significa e como ler o seu dispositivo com honestidade. O VD Google lê o estado de RCS,
Google Wallet e das atestações do Play Integrity que os aplicativos solicitam, e explica
cada item em palavras simples. Ele nunca toca na internet e nunca altera nada a menos que
você peça.

## Índice

- [1. O que é o VD Google](#1-o-que-é-o-vd-google)
- [2. O que ele não é](#2-o-que-ele-não-é)
- [3. Requisitos](#3-requisitos)
- [4. Aplicativo ou WebUI](#4-aplicativo-ou-webui)
- [5. Instalação e primeira execução](#5-instalação-e-primeira-execução)
- [6. As abas](#6-as-abas)
  - [6.1 Sobre](#61-sobre)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Atestações](#65-atestações)
- [7. Exporte seu relatório](#7-exporte-seu-relatório)
- [8. Ações que alteram o estado](#8-ações-que-alteram-o-estado)
- [9. Idioma e tema](#9-idioma-e-tema)
- [10. Perguntas frequentes](#10-perguntas-frequentes)

## 1. O que é o VD Google

O VD Google é um leitor. Ele olha o que os próprios aplicativos do Google já armazenam no
seu dispositivo sobre três coisas, e explica cada uma delas para que você realmente a
entenda:

- **RCS** - se o chat moderno está mesmo funcionando na sua linha, com quem você pode
  conversar e se o Gemini no Google Messages está pronto.
- **Google Wallet** - se o seu dispositivo está configurado e atestado para pagamentos por
  aproximação.
- **Atestações do Play Integrity** - quais aplicativos pediram ao Android para provar que
  o seu dispositivo é genuíno, e se o Google atualmente o certifica.

Tudo é lido localmente, por meio do root, e mostrado com uma explicação. Nenhum valor é
inventado: quando o dispositivo não tem uma resposta, o VD Google diz "Desconhecido" em
vez de adivinhar.

## 2. O que ele não é

**O VD Google não é um verificador de integridade.** Ele não executa nem relata nenhum
veredito basic / device / strong. Esse resultado vive apenas dentro do token criptografado
que um aplicativo envia ao seu próprio servidor, e nunca é armazenado no dispositivo,
então nenhum leitor honesto pode mostrá-lo. Ferramentas que afirmam exibir o seu "veredito
do Play Integrity" localmente estão mostrando a você um palpite em cache ou uma requisição
que elas mesmas fizeram, não a resposta real.

O VD Google também não falsifica, oculta nem forja nada. Ele lê a verdade. Se um valor
parecer errado para você, o objetivo deste guia é explicar por que a verdade parece assim.

## 3. Requisitos

- **Android 8.0 (API 26) ou mais recente.**
- **Root.** Os dados que o VD Google lê vivem em áreas protegidas, exclusivas do sistema,
  do Android, alcançáveis apenas com root. Qualquer solução de root moderna funciona:
  KernelSU, KernelSU Next, Magisk ou APatch.
- **Sem internet.** O VD Google não declara permissão alguma, então não há nada a
  autorizar nem nada a bloquear.

## 4. Aplicativo ou WebUI

O VD Google é distribuído de duas formas, e elas mostram exatamente as mesmas informações:

- **O aplicativo (APK).** Um aplicativo Android normal que você instala e abre. Pede root
  na primeira vez, como qualquer aplicativo root.
- **O módulo WebUI (ZIP).** Instale-o no seu gerenciador de root (KernelSU, Magisk ou
  APatch) e abra-o de lá. Nada é instalado como aplicativo; ele roda dentro da WebUI do seu
  gerenciador de root.

Escolha o que preferir. As telas, a redação e o relatório exportado são idênticos entre os
dois.

## 5. Instalação e primeira execução

**Aplicativo:** instale o APK e abra-o. Na primeira vez, conceda root quando o seu
gerenciador de root pedir. Se você negar ou dispensar o root, o VD Google mostra uma tela
explicando por que o root é necessário e oferece tentar de novo.

**WebUI:** instale o ZIP como módulo no seu gerenciador de root e, em seguida, abra a
WebUI do módulo pelo gerenciador. Ele lê assim que abre.

Na primeira abertura você pode escolher o seu idioma; você pode mudá-lo depois pelo ícone
de engrenagem no topo.

## 6. As abas

Na parte de baixo há cinco abas: Sobre, DroidGuard, RCS, Wallet e Atestações. A aba Sobre
abre primeiro.

### 6.1 Sobre

O que é o aplicativo, a promessa de privacidade, a versão e os links. Leia a seção de
privacidade uma vez: é todo o propósito do aplicativo.

### 6.2 RCS

Esta é a aba mais rica. Os horários são sempre mostrados no fuso horário do seu
dispositivo, com o fuso rotulado.

- **Conexão** - a única linha que diz a verdade. Ela é calculada a partir da configuração
  real do chat e do motor de provisionamento, então diz Conectado quando o RCS está de
  fato funcionando na sua linha, mesmo no Android mais novo, onde as flags antigas mentem.
  **Na dúvida, confie nesta linha.**
- **Operadora** - a operadora móvel da sua linha (nome e MCC/MNC), obtida do seu SIM de
  dados em telefones com dois SIMs.
- **Flag de disponibilidade (em cache)** - uma flag em cache que o aplicativo de mensagens
  mantém para o seu próprio número. Ela pode ficar desatualizada depois que o RCS já está
  funcionando, então um "Não" aqui não significa que o RCS está desligado. Confie na linha
  Conexão acima.
- **Seu número de RCS** - o número registrado para RCS, mascarado por padrão. Toque no
  olho para revelá-lo.
- **Estado de provisionamento (em cache)** e **Versão de configuração** - valores internos
  que o aplicativo de mensagens mantém. Eles podem aparecer como 0 mesmo com o RCS
  funcionando. Veja a nota sobre operadora x backend do Google abaixo.
- **Contatos com RCS** - quantos dos seus contatos são atualmente conhecidos por suportar
  chat RCS.
- **Flag de recurso (em cache)** - se o aplicativo de mensagens tem o recurso RCS ligado;
  em cache, então pode atrasar.
- **Direito da operadora**, **Presença (UCE)**, **Serviço IMS presente** - estes descrevem
  o caminho de RCS *da operadora* (a rede IMS da operadora). Um "Não" ou "0" aqui é normal
  quando o seu RCS roda pelo próprio backend do Google em vez da operadora. Veja abaixo.
- **Gemini** - se o Gemini no Google Messages está pronto: **Magic Compose** (a ajuda de
  escrita do Gemini), **AICore** (o motor no dispositivo que roda o Gemini Nano) e **chat
  do Gemini**, que é entregue por RCS e, portanto, precisa do RCS conectado.
- **Horários** - provisionamento, sincronização, última mensagem, expiração do token e
  mais.

**RCS da operadora x backend do Google.** Há duas formas de o RCS chegar ao seu telefone.
Uma é o RCS *da sua operadora* pela rede IMS dela; a outra é o *próprio backend do Google*
(Jibe), que funciona por cima, independente da operadora. Se o seu RCS roda pelo backend
do Google - como é o caso da maioria das pessoas - então as linhas do lado da operadora
(direito da operadora, presença/UCE, serviço IMS, versão de configuração) são
genuinamente "Não" ou "0", e isso está correto, não é um cache desatualizado. O seu RCS
pode ter funcionado assim por anos. A linha Conexão é o que diz a você que ele está
funcionando.

### 6.3 Wallet

- **Dispositivo atestado** - se o Google julgou este dispositivo genuíno e seguro o
  suficiente para guardar cartões de pagamento. É o último resultado que o Google salvou
  neste dispositivo, então pode atrasar em relação ao aplicativo Wallet até o Google
  reavaliar. Se o resultado salvo estiver ausente, o VD Google mostra **Desconhecido** em
  vez de um "Não" enganoso.
- **Última verificação** - quando essa verificação de segurança foi concluída pela última
  vez.
- **Hardware NFC**, **Wallet instalado**, **Serviço do Wallet**, **Tocar e pagar**,
  **Bloqueio de tela aceito**, **Cartão já adicionado** - as peças que tornam o pagamento
  por aproximação possível.

### 6.4 DroidGuard

- **Certificado pelo Play Protect** - se o Google Play reconhece o seu dispositivo como
  certificado (a certificação mostrada na Play Store).
- **Cache de atestação** - as atestações do Play Integrity e do DroidGuard que o seu
  dispositivo tem em cache, cada uma com quando expira.

### 6.5 Atestações

O histórico de quais aplicativos solicitaram o Play Integrity no seu dispositivo: ícone,
nome, quando pediram pela última vez e quantas vezes. Isso permite que você saiba se um
determinado aplicativo verifica integridade ou não. Use a caixa de busca para filtrar por
nome do aplicativo ou pacote.

## 7. Exporte seu relatório

O ícone de download grava um relatório **JSON** com o estado do seu dispositivo, RCS,
Wallet e atestação. Você pode compartilhá-lo ou salvá-lo como arquivo. O relatório não
contém **nenhum número de telefone, nenhuma credencial e nenhum conteúdo pessoal** - ele é
higienizado antes de ser gravado. Ele também inclui uma seção opcional de **diagnóstico**
(nomes de esquema, contagens e versões, nunca os seus dados) que ajuda a resolver problemas
específicos do dispositivo. O aplicativo e a WebUI gravam o mesmo relatório.

## 8. Ações que alteram o estado

O VD Google é, em sua maior parte, somente leitura, mas pode executar uma **ação de
higienização**: limpar o resultado de atestação em cache do Google Wallet, o que permite
que uma futura reavaliação comece do zero. Toda ação que altera o estado pede que você
confirme primeiro, e é gravada em um log JSON local no dispositivo. Nada sai do seu
telefone.

## 9. Idioma e tema

O VD Google fala 21 idiomas. Escolha o seu na primeira execução, ou mude-o a qualquer
momento pelo ícone de engrenagem (também disponível na tela de root negado). O ícone de
brilho alterna entre claro e escuro.

## 10. Perguntas frequentes

**O RCS diz Conectado, mas várias linhas dizem Não ou 0. Por quê?** Porque o seu RCS roda
pelo próprio backend do Google, não pela sua operadora. As linhas do lado da operadora
estão corretamente negativas. Veja [6.2 RCS](#62-rcs).

**Dispositivo atestado diz Desconhecido.** O resultado em cache do Wallet está ausente no
seu dispositivo. Isso é honesto - o VD Google não vai mostrar um "Não" que não consegue
sustentar.

**Onde está o meu veredito do Play Integrity?** Ele não está no seu dispositivo. Veja
[2. O que ele não é](#2-o-que-ele-não-é).

**Funciona sem root?** Não. Os dados vivem em áreas exclusivas do sistema.
