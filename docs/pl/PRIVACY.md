> **Languages:** [English](../PRIVACY.md) · [العربية](../ar/PRIVACY.md) · [Čeština](../cs/PRIVACY.md) · [Deutsch](../de/PRIVACY.md) · [Español](../es/PRIVACY.md) · [فارسی](../fa/PRIVACY.md) · [Français](../fr/PRIVACY.md) · [हिन्दी](../hi/PRIVACY.md) · [Bahasa Indonesia](../in/PRIVACY.md) · [Italiano](../it/PRIVACY.md) · [日本語](../ja/PRIVACY.md) · [한국어](../ko/PRIVACY.md) · [Nederlands](../nl/PRIVACY.md) · Polski · [Português](../pt/PRIVACY.md) · [Русский](../ru/PRIVACY.md) · [Svenska](../sv/PRIVACY.md) · [ไทย](../th/PRIVACY.md) · [Türkçe](../tr/PRIVACY.md) · [Українська](../uk/PRIVACY.md) · [Tiếng Việt](../vi/PRIVACY.md) · [中文](../zh/PRIVACY.md)

---

# Prywatność

Prywatność nie jest funkcją VD Google - jest całym jego sensem. Ta strona dokładnie
określa, co aplikacja robi, a czego nie robi z twoimi danymi, w prostych słowach, które
możesz zweryfikować.

## Obietnica

- **Nie jest przyznawane żadne uprawnienie internetowe.** Manifest aplikacji nie deklaruje
  żadnych uprawnień - ani internetu, ani pamięci, ani kontaktów, niczego. Nie ma czego
  zezwalać.
- **Nic nie jest przechowywane, wysyłane ani przesyłane** do żadnego pliku czy serwera
  poza twoim urządzeniem.
- Każdy odczyt odbywa się **lokalnie, przez roota**, i pozostaje na urządzeniu.

## Dlaczego potrzebuje roota zamiast uprawnień

Stan RCS, Wallet i atestacji, który wyjaśnia VD Google, znajduje się w chronionych,
wyłącznie systemowych obszarach Androida, niewidocznych dla zwykłych aplikacji. VD Google
czyta je za pomocą roota, a nie prosząc cię o szerokie uprawnienia. Root to wąska,
audytowalna droga wejścia; sterta uprawnień czasu wykonania byłaby drogą szeroką i
nieszczelną. Aplikacja wybiera tę wąską.

## Eksport jest oczyszczany

Gdy eksportujesz raport, VD Google oczyszcza go przed zapisaniem:

- **Nigdy nieuwzględniane:** twój numer telefonu, jakiekolwiek dane uwierzytelniające,
  pliki cookie czy tokeny, treść wiadomości, nazwy kontaktów ani surowe bloby możliwości.
- **Redagowane:** długie ciągi cyfr i wartości wyglądające jak sekrety są zastępowane
  symbolem zastępczym.
- **Diagnostyka** niesie tylko strukturę (nazwy schematów, liczby, rozmiary, wersje).
  Każdy odcisk powiązany z tożsamością, który przypadkiem pojawi się wewnątrz nazwy, jest
  usuwany.

Raport należy do ciebie. Jest zapisywany tam, gdzie wybierzesz, jest udostępniany tylko
jeśli go udostępnisz, i nie zawiera niczego, co cię identyfikuje.

## Działania są wyraźne i logowane

VD Google może wyczyścić zbuforowany wynik atestacji Google Wallet, aby przyszła ponowna
kontrola zaczęła od nowa. Każde działanie, które zmienia stan:

1. prosi cię najpierw o potwierdzenie, oraz
2. jest zapisywane w lokalnym logu JSON na urządzeniu.

Żadne działanie nigdy nie dociera do sieci i nic nie opuszcza twojego telefonu.

## Zweryfikuj to samodzielnie

Nie musisz brać niczego z tego na wiarę:

- Źródło jest publiczne: <https://github.com/VD171/VD-Google>.
- Manifest jest w źródle; potwierdź, że nie deklaruje żadnych uprawnień.
- Content-Security-Policy WebUI jest w źródle; potwierdź, że blokuje każde zdalne źródło.
- Wbudowane pliki binarne są udokumentowane z sumami kontrolnymi w
  [Wbudowane pliki binarne](BINARIES.md).

<!-- doc-footer -->

---

> _Ta dokumentacja została wygenerowana za pomocą Claude (Anthropic) i jest utrzymywana przez VD171. Poprawki i ulepszenia są mile widziane; zobacz [CONTRIBUTING.md](CONTRIBUTING.md)._
