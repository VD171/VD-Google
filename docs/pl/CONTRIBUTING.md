> **Languages:** [English](../CONTRIBUTING.md) · [العربية](../ar/CONTRIBUTING.md) · [Čeština](../cs/CONTRIBUTING.md) · [Deutsch](../de/CONTRIBUTING.md) · [Español](../es/CONTRIBUTING.md) · [فارسی](../fa/CONTRIBUTING.md) · [Français](../fr/CONTRIBUTING.md) · [हिन्दी](../hi/CONTRIBUTING.md) · [Bahasa Indonesia](../in/CONTRIBUTING.md) · [Italiano](../it/CONTRIBUTING.md) · [日本語](../ja/CONTRIBUTING.md) · [한국어](../ko/CONTRIBUTING.md) · [Nederlands](../nl/CONTRIBUTING.md) · Polski · [Português](../pt/CONTRIBUTING.md) · [Русский](../ru/CONTRIBUTING.md) · [Svenska](../sv/CONTRIBUTING.md) · [ไทย](../th/CONTRIBUTING.md) · [Türkçe](../tr/CONTRIBUTING.md) · [Tiếng Việt](../vi/CONTRIBUTING.md) · [中文](../zh/CONTRIBUTING.md)

---

# Współtworzenie

Dziękujemy za zainteresowanie VD Google. Wkład jest mile widziany: tłumaczenia, zgłoszenia
błędów, dokumentacja i kod.

## Zasady podstawowe

- **VD Google czyta prawdę i nigdy niczego nie fałszuje.** Wkład musi to zachować.
  Prawdziwy wynik negatywny pozostaje negatywny; nieaktualna pamięć podręczna jest
  oznaczana jako zbuforowana; brakująca wartość to Nieznane. Nie łagodź odczytu, by
  wyglądał ładniej.
- **Prywatność jest absolutna.** Aplikacja nie deklaruje żadnych uprawnień, a WebUI nie
  kontaktuje się z żadnym zdalnym źródłem. Nigdy nie dodawaj uprawnienia, wywołania
  sieciowego ani niczego, co wysyła dane poza urządzenie.
- **Aplikacja i WebUI są identyczne.** Każda zmiana tego, co jest pokazywane, lub
  eksportowanego raportu musi trafić do obu, z tym samym sformułowaniem. Nigdy nie mogą
  się rozjechać.
- **Etykiety żyją raz, w 21 językach.** Każdy tekst widoczny dla użytkownika jest
  zdefiniowany dla aplikacji (`res/values*/strings.xml`) i dla WebUI (`webroot/lang/`).
  Jeśli dodajesz lub zmieniasz etykietę, zaktualizuj wszystkie języki.

## Tłumaczenia

Tłumaczenia to najłatwiejszy i najcenniejszy wkład. Readme jest tłumaczone na język
(`README.<lang>.md`), teksty aplikacji i WebUI na język, a ta dokumentacja pod
`docs/<lang>/`. Zachowuj spójność terminologii w obrębie języka, dopasuj się do
istniejącego tonu i nigdy nie tłumacz maszynowo terminu technicznego, którego nie jesteś
pewien.

## Zgłaszanie błędu

Otwórz zgłoszenie z modelem swojego urządzenia, wersją Androida oraz - jeśli dotyczy
odczytu - **eksportem** (nie zawiera żadnych danych osobowych). Sekcja diagnostyczna
eksportu jest stworzona dokładnie do tego.

## Kod

- Buduj release, nigdy debug. Testuj na prawdziwym, zrootowanym urządzeniu.
- Nie umieszczaj komentarzy w kodzie; nazwy wyjaśniają intencję. Jedynym nagłówkiem jest
  baner licencyjny na górze każdego pliku źródłowego.
- Dopasuj się do istniejącego stylu i utrzymuj zmiany w jednym temacie.

## Licencja

Współtworząc, zgadzasz się, że twój wkład jest licencjonowany na warunkach projektowej
**GNU AGPL-3.0-or-later**.
