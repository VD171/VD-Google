> **Languages:** [English](../SECURITY.md) · [العربية](../ar/SECURITY.md) · [Čeština](../cs/SECURITY.md) · [Deutsch](../de/SECURITY.md) · [Español](../es/SECURITY.md) · [فارسی](../fa/SECURITY.md) · [Français](../fr/SECURITY.md) · [हिन्दी](../hi/SECURITY.md) · [Bahasa Indonesia](../in/SECURITY.md) · [Italiano](../it/SECURITY.md) · [日本語](../ja/SECURITY.md) · [한국어](../ko/SECURITY.md) · [Nederlands](../nl/SECURITY.md) · Polski · [Português](../pt/SECURITY.md) · [Русский](../ru/SECURITY.md) · [Svenska](../sv/SECURITY.md) · [ไทย](../th/SECURITY.md) · [Türkçe](../tr/SECURITY.md) · [Українська](../uk/SECURITY.md) · [Tiếng Việt](../vi/SECURITY.md) · [中文](../zh/SECURITY.md)

---

# Polityka bezpieczeństwa

VD Google to narzędzie chroniące prywatność. Działa jako root i czyta wrażliwy stan, więc
zgłoszenia bezpieczeństwa traktowane są poważnie.

## Zgłaszanie podatności

Prosimy zgłaszać problemy bezpieczeństwa **prywatnie**, nie w publicznym zgłoszeniu:

- E-mail **vd.priv8@pm.me**.
- Opisz problem, dotkniętą wersję i sposób jego odtworzenia.
- Jeśli dotyczy ujawnienia danych, opisz klasę problemu; nie musisz dołączać działającego
  exploita.

Otrzymasz odpowiedź tak szybko, jak to możliwe. Prosimy dać rozsądny czas na naprawę
problemu przed jakimkolwiek publicznym ujawnieniem.

## Co liczy się jako problem bezpieczeństwa

Ze względu na to, co obiecuje VD Google, te są szczególnie ważne:

- Cokolwiek, co sprawiłoby, że aplikacja sięgnie do sieci lub wyśle jakiekolwiek dane poza
  urządzenie.
- Cokolwiek, co wyciekłoby dane osobowe do **eksportu** (numer telefonu, dane
  uwierzytelniające, treść wiadomości, nazwę kontaktu lub odcisk powiązany z
  tożsamością).
- Cokolwiek, co pozwala jednemu działaniu sanityzującemu zmienić stan bez potwierdzenia
  lub bez zalogowania.

## Co nie jest problemem bezpieczeństwa

- Odczyt, który wygląda na "błędny", ale jest uczciwą prawdą (na przykład linie RCS po
  stronie operatora czytające się jako Nie, podczas gdy RCS działa na zapleczu Google).
  Zobacz [przewodnik użytkownika](USER-GUIDE.md).
- Brak werdyktu Play Integrity. Tego werdyktu nie ma na urządzeniu i VD Google go nie
  wymyśli.

## Wspierane wersje

Wspierana jest tylko najnowsza wydana wersja. Prosimy odtworzyć problem na najnowszej
kompilacji przed zgłoszeniem.

<!-- doc-footer -->

---

> _Ta dokumentacja została wygenerowana za pomocą Claude (Anthropic) i jest utrzymywana przez VD171. Poprawki i ulepszenia są mile widziane; zobacz [CONTRIBUTING.md](CONTRIBUTING.md)._
