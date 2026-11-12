> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · [Polski](../pl/SECURITY.md) · Português · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Política de segurança

O VD Google é uma ferramenta de privacidade. Ele roda como root e lê estado sensível,
então relatos de segurança são levados a sério.

## Relatando uma vulnerabilidade

Por favor, relate problemas de segurança **em privado**, não em uma issue pública:

- E-mail **vd.priv8@pm.me**.
- Descreva o problema, a versão afetada e como reproduzi-lo.
- Se tratar de exposição de dados, descreva a classe do problema; você não precisa anexar
  um exploit funcional.

Você receberá uma resposta o mais rápido possível. Por favor, dê um prazo razoável para
corrigir o problema antes de qualquer divulgação pública.

## O que conta como problema de segurança

Por causa do que o VD Google promete, estes são especialmente importantes:

- Qualquer coisa que faria o aplicativo alcançar a rede ou enviar quaisquer dados para
  fora do dispositivo.
- Qualquer coisa que vazaria dados pessoais para uma **exportação** (um número de
  telefone, credencial, conteúdo de mensagem, nome de contato ou uma impressão digital
  ligada a uma identidade).
- Qualquer coisa que permita que a única ação de higienização altere o estado sem
  confirmação ou sem ser registrada.

## O que não é um problema de segurança

- Uma leitura que parece "errada" mas é a verdade honesta (por exemplo, linhas de RCS do
  lado da operadora aparecendo como Não enquanto o RCS roda no backend do Google). Veja o
  [guia do usuário](USER-GUIDE.md).
- A ausência de um veredito do Play Integrity. Esse veredito não está no dispositivo e o
  VD Google não vai inventá-lo.

## Versões suportadas

Apenas a versão lançada mais recente é suportada. Por favor, reproduza na build mais
recente antes de relatar.
