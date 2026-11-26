> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · [Polski](../pl/AI-AGENTS.md) · Português · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Agentes de IA

O VD Google transforma os próprios registros do Google no aparelho em algo que um **agente de IA consegue ler em um único passo**. Em vez de abrir vários apps e bancos de dados do Google e adivinhar o que significam, um agente lê uma única **exportação** estruturada e sabe exatamente o que o aparelho reporta sobre RCS, Google Carteira e Play Integrity.

Esta página expande a ideia apresentada no README.

## A exportação

Tanto o App quanto a WebUI produzem a mesma exportação JSON:

- **App**: a ação **Exportar** compartilha ou salva o relatório.
- **WebUI**: a ação **Exportar** grava em `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

As duas têm o mesmo esquema, porque o app e a WebUI são mantidos idênticos. Nada é enviado a lugar nenhum: a exportação é gravada no aparelho e fica nele.

## O que há nela

A exportação é um único objeto JSON com estas chaves de nível superior:

- `app`, `version`, `exportedAt`, `timezone` - o que gerou o relatório e quando, no fuso rotulado do aparelho.
- `device` - fabricante, modelo, versão do Android e nível de SDK.
- `rcs` - disponibilidade, estado, versão da configuração, contatos compatíveis, autorização da operadora, presença (UCE) e os horários relacionados.
- `wallet` - atestação de pagamento, hardware NFC, o estado do pagamento por aproximação, aceitação do bloqueio de tela e se algum cartão já foi adicionado.
- `diagnostics` - uma seção de autoverificação, feita para relatórios de bug.

A exportação não carrega **nenhum dado pessoal**: nenhum número de telefone, nenhuma credencial, nenhum conteúdo de mensagem, nenhum nome de contato. Todo valor é a leitura honesta - um negativo real continua negativo, um cache desatualizado é rotulado como em cache, um valor ausente é Desconhecido.

## Somente leitura, com uma ação registrada

O VD Google lê. A única exceção é uma ação de sanitização (limpar o resultado de atestação em cache do Google Carteira para forçar uma reavaliação). Toda ação que altera estado pede uma confirmação no aparelho e é escrita num log de ações local em JSON. Um agente operando a WebUI vê a mesma confirmação e o mesmo log, não existe caminho oculto nem sem registro.

## Sem contrato de rede

O VD Google não expõe nenhuma API de rede nem socket: ele não declara permissão nenhuma. Um agente consome o **arquivo de exportação**, ele não chama um serviço. Essa é a garantia de privacidade, mantida até na automação.

<!-- doc-footer -->

---

> _Esta documentação foi gerada com o Claude (Anthropic) e é mantida por VD171. Correções e melhorias são bem-vindas, veja [CONTRIBUTING.md](CONTRIBUTING.md)._
