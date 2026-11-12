> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · [Polski](../pl/CONTRIBUTING.md) · Português · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Українська](../uk/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Contribuindo

Obrigado pelo seu interesse no VD Google. Contribuições são bem-vindas: traduções,
relatórios de bugs, documentação e código.

## Regras básicas

- **O VD Google lê a verdade e nunca falsifica nada.** As contribuições devem manter isso.
  Um negativo real continua negativo; um cache desatualizado é rotulado como em cache; um
  valor ausente é Desconhecido. Não suavize uma leitura para fazê-la parecer mais bonita.
- **A privacidade é absoluta.** O aplicativo não declara permissões e a WebUI não contata
  nenhuma origem remota. Nunca adicione uma permissão, uma chamada de rede ou qualquer
  coisa que envie dados para fora do dispositivo.
- **O aplicativo e a WebUI são idênticos.** Qualquer mudança no que é mostrado, ou no
  relatório exportado, deve chegar aos dois, com a mesma redação. Eles nunca podem se
  desviar.
- **Os rótulos vivem uma vez, em 21 idiomas.** Todo texto exibido ao usuário é definido
  para o aplicativo (`res/values*/strings.xml`) e para a WebUI (`webroot/lang/`). Se você
  adicionar ou alterar um rótulo, atualize todos os idiomas.

## Traduções

Traduções são a contribuição mais fácil e mais valiosa. O readme é traduzido por idioma
(`README.<lang>.md`), os textos do aplicativo e da WebUI por idioma, e esta documentação
sob `docs/<lang>/`. Mantenha a terminologia consistente dentro de um idioma, acompanhe o
tom existente e nunca traduza por máquina um termo técnico do qual você não tem certeza.

## Relatando um bug

Abra uma issue com o modelo do seu dispositivo, a versão do Android e - se for sobre uma
leitura - uma **exportação** (ela não contém dados pessoais). A seção de diagnóstico da
exportação é feita exatamente para isso.

## Código

- Compile em release, nunca em debug. Teste em um dispositivo real, com root.
- Mantenha comentários fora do código; os nomes explicam a intenção. O único cabeçalho é o
  banner de licença no topo de cada arquivo-fonte.
- Acompanhe o estilo existente e mantenha as mudanças focadas.

## Licença

Ao contribuir, você concorda que a sua contribuição é licenciada sob a
**GNU AGPL-3.0-or-later** do projeto.
