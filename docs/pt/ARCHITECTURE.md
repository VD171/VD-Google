> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · [Polski](../pl/ARCHITECTURE.md) · Português · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Українська](../uk/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Arquitetura

Como o VD Google é construído e por quê. Esta página explica o design e os princípios.
Ela não lista os arquivos, tabelas ou consultas exatos que cada leitura usa - isso vive
apenas no código-fonte, por design, para que o aplicativo e a documentação nunca se
desviem para um passo a passo útil a quem quer forjar os mesmos valores.

## Objetivos de design

1. **Ler a verdade.** Mostrar o que os próprios aplicativos do Google já registraram,
   explicá-lo e nunca inventar um valor. Quando o dispositivo não tem a resposta, dizer
   Desconhecido.
2. **Zero rede.** O aplicativo não declara permissão alguma; a WebUI bloqueia toda origem
   remota. Nada é enviado a lugar nenhum, jamais.
3. **Um comportamento, duas faces.** O aplicativo e o módulo WebUI são mantidos
   absolutamente idênticos - mesmos sinais, mesma redação, mesmo relatório exportado.
4. **Honesto, não bajulador.** Um negativo real é mostrado como negativo; um cache
   desatualizado é rotulado como em cache; um valor ausente é Desconhecido. A detecção
   nunca é suavizada para parecer mais bonita.

## As duas faces

- **O aplicativo** é Kotlin com Jetpack Compose (Material 3). Ele obtém root pelo
  `RootService` da libsu, nunca invocando `su` diretamente, e faz as leituras dentro desse
  serviço privilegiado.
- **O módulo WebUI** é uma pequena web root em HTML/JS servida pelo seu gerenciador de
  root (KernelSU, Magisk ou APatch). Ele realiza as mesmas leituras pela ponte de root do
  gerenciador. Uma Content-Security-Policy estrita proíbe qualquer origem remota, então a
  página não pode ligar para casa nem em princípio.

Ambos são gerados a partir de um único conjunto compartilhado de textos em 21 idiomas, de
modo que um rótulo nunca é traduzido duas vezes.

## Lendo bancos de dados: o sqlite3 embarcado

Parte do estado que o VD Google lê fica em bancos de dados SQLite que pertencem aos
aplicativos do Google. O Android traz o seu próprio SQLite, mas o SQLite do framework é
pouco confiável para abrir o banco de dados de outro aplicativo copiado para o lado: em
algumas ROMs de fabricantes ele falha com *todos* os bancos de dados, não retornando nada.
Para ler da mesma forma em todo dispositivo, o VD Google embarca seu próprio executável
`sqlite3` (arm64 e arm32) e roda as consultas por meio dele como root. O aplicativo e a
WebUI usam o binário idêntico, então sempre leem o mesmo motor e o mesmo resultado. A
procedência e as somas de verificação desse binário estão documentadas em
[Binários embarcados](BINARIES.md).

As leituras copiam o banco de dados para o lado antes de abri-lo, nunca tocando no arquivo
vivo, e nunca copiam o índice de memória compartilhada, que um banco de dados em execução
pode relatar como malformado.

## O sinal do RCS

A parte mais difícil de ler o RCS honestamente é que as flags óbvias mentem. A flag
simples de "disponível", as flags de recurso e o estado de provisionamento podem todos
aparecer negativos em uma linha onde o RCS está funcionando perfeitamente, porque são
caches ou porque a linha roda no próprio backend do Google (Jibe) em vez da rede IMS da
operadora. O VD Google calcula um único veredito **Conexão** a partir da configuração real
do chat e do motor de provisionamento, que permanece correto entre as versões do Android e
do Google Messages, e apresenta as flags mentirosas claramente rotuladas como em cache,
para que informem sem enganar. Veja o [guia do usuário](USER-GUIDE.md) para saber o que
cada linha significa.

## A exportação

A exportação é um instantâneo JSON higienizado. Um higienizador roda antes de qualquer
coisa ser gravada: ele descarta chaves e valores que parecem credenciais, redige longas
sequências de dígitos e nunca emite seu número de telefone, conteúdo de mensagens, nomes
de contatos ou blobs brutos de capacidade. A seção opcional de diagnóstico carrega apenas
nomes de esquema, contagens, tamanhos e versões - e mesmo ali, qualquer impressão digital
ligada a uma identidade embutida num nome é removida. O aplicativo e a WebUI produzem o
mesmo esquema.

## O que o VD Google nunca faz

- Ele nunca declara uma permissão de rede, e a WebUI nunca contata uma origem remota.
- Ele nunca fabrica, falsifica ou oculta um valor.
- Ele nunca relata um veredito do Play Integrity, porque esse veredito não está no
  dispositivo.
- Ele só altera o estado por meio de uma ação explícita, confirmada e registrada
  localmente.
