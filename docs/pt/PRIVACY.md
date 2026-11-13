> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · [Polski](../pl/PRIVACY.md) · Português · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Українська](../uk/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Privacidade

Privacidade não é um recurso do VD Google: é todo o seu propósito. Esta página declara
exatamente o que o aplicativo faz e não faz com os seus dados, em termos simples que você
pode verificar.

## A promessa

- **Nenhuma permissão de internet é concedida.** O manifest do aplicativo não declara
  permissão alguma: nem internet, nem armazenamento, nem contatos, nada. Não há nada a
  autorizar.
- **Nada é armazenado, enviado ou transmitido** para qualquer arquivo ou servidor fora do
  seu dispositivo.
- Toda leitura acontece **localmente, por meio do root**, e permanece no dispositivo.

## Por que ele precisa de root em vez de permissões

O estado de RCS, Wallet e atestação que o VD Google explica vive em áreas protegidas,
exclusivas do sistema, do Android, que aplicativos normais não conseguem ver. O VD Google
os lê com root em vez de pedir a você permissões amplas. O root é a entrada estreita e
auditável; uma pilha de permissões de runtime seria a via ampla e vazante. O aplicativo
escolhe a estreita.

## A exportação é higienizada

Quando você exporta um relatório, o VD Google o higieniza antes de gravar:

- **Nunca incluído:** seu número de telefone, qualquer credencial, cookie ou token,
  conteúdo de mensagens, nomes de contatos ou blobs brutos de capacidade.
- **Redigido:** longas sequências de dígitos e valores que parecem segredos são
  substituídos por um marcador.
- **O diagnóstico** carrega apenas estrutura (nomes de esquema, contagens, tamanhos,
  versões). Qualquer impressão digital ligada a uma identidade que por acaso apareça
  dentro de um nome é removida.

O relatório é seu. Ele é gravado onde você escolher, é compartilhado apenas se você o
compartilhar, e não contém nada que identifique você.

## As ações são explícitas e registradas

O VD Google pode limpar o resultado de atestação em cache do Google Wallet, para que uma
futura reavaliação comece do zero. Qualquer ação que altera o estado:

1. pede que você confirme primeiro, e
2. é gravada em um log JSON local no dispositivo.

Nenhuma ação jamais alcança a rede, e nada sai do seu telefone.

## Verifique você mesmo

Você não precisa aceitar nada disso por confiança:

- O código-fonte é público: <https://github.com/VD171/VD-Google>.
- O manifest está no código-fonte; confirme que ele não declara permissões.
- A Content-Security-Policy da WebUI está no código-fonte; confirme que ela bloqueia toda
  origem remota.
- Os binários embarcados estão documentados com somas de verificação em
  [Binários embarcados](BINARIES.md).

<!-- doc-footer -->

---

> _Esta documentação foi gerada com o Claude (Anthropic) e é mantida por VD171. Correções e melhorias são bem-vindas; veja [CONTRIBUTING.md](CONTRIBUTING.md)._
