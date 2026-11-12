> **Languages:** [English](../ARCHITECTURE.md) · [العربية](../ar/ARCHITECTURE.md) · [Čeština](../cs/ARCHITECTURE.md) · [Deutsch](../de/ARCHITECTURE.md) · [Español](../es/ARCHITECTURE.md) · [فارسی](../fa/ARCHITECTURE.md) · [Français](../fr/ARCHITECTURE.md) · [हिन्दी](../hi/ARCHITECTURE.md) · [Bahasa Indonesia](../in/ARCHITECTURE.md) · [Italiano](../it/ARCHITECTURE.md) · [日本語](../ja/ARCHITECTURE.md) · [한국어](../ko/ARCHITECTURE.md) · [Nederlands](../nl/ARCHITECTURE.md) · Polski · [Português](../pt/ARCHITECTURE.md) · [Русский](../ru/ARCHITECTURE.md) · [Svenska](../sv/ARCHITECTURE.md) · [ไทย](../th/ARCHITECTURE.md) · [Türkçe](../tr/ARCHITECTURE.md) · [Tiếng Việt](../vi/ARCHITECTURE.md) · [中文](../zh/ARCHITECTURE.md)

---

# Architektura

Jak zbudowano VD Google i dlaczego. Ta strona wyjaśnia projekt i jego zasady. Nie
wymienia dokładnych plików, tabel ani zapytań używanych przez poszczególne elementy -
to żyje wyłącznie w kodzie źródłowym, celowo, tak aby aplikacja i dokumentacja nigdy nie
zamieniły się w poradnik dla kogoś, kto chciałby sfałszować te same wartości.

## Cele projektu

1. **Czytaj prawdę.** Pokazuj to, co zapisały już własne aplikacje Google, wyjaśniaj to i
   nigdy nie wymyślaj wartości. Gdy urządzenie nie ma odpowiedzi, napisz Nieznane.
2. **Zero sieci.** Aplikacja nie deklaruje żadnych uprawnień; WebUI blokuje każde zdalne
   źródło. Nic nigdy nie jest nigdzie wysyłane.
3. **Jedno zachowanie, dwie twarze.** Aplikacja i moduł WebUI są utrzymywane jako
   absolutnie identyczne - te same sygnały, te same sformułowania, ten sam eksportowany
   raport.
4. **Uczciwie, nie pochlebnie.** Prawdziwy wynik negatywny jest pokazywany jako
   negatywny; nieaktualna pamięć podręczna jest oznaczana jako zbuforowana; brakująca
   wartość to Nieznane. Wykrywanie nigdy nie jest łagodzone, by wyglądać ładniej.

## Dwie twarze

- **Aplikacja** jest napisana w Kotlinie z Jetpack Compose (Material 3). Uzyskuje roota
  przez `RootService` z biblioteki libsu, nigdy przez bezpośrednie wywołanie `su`, i
  czyta wewnątrz tej uprzywilejowanej usługi.
- **Moduł WebUI** to niewielki web root w HTML/JS udostępniany przez twojego menedżera
  roota (KernelSU, Magisk lub APatch). Wykonuje te same odczyty przez mostek roota
  menedżera. Ścisła Content-Security-Policy zabrania jakiegokolwiek zdalnego źródła, więc
  strona nie może zadzwonić do domu nawet w teorii.

Oba są generowane z jednego wspólnego zestawu tekstów w 21 językach, więc etykieta nigdy
nie jest tłumaczona dwa razy.

## Czytanie baz danych: wbudowany sqlite3

Część stanu, który czyta VD Google, znajduje się w bazach danych SQLite należących do
aplikacji Google. Android dostarcza własny SQLite, ale frameworkowy SQLite jest zawodny
przy otwieraniu skopiowanej na bok bazy innej aplikacji: na niektórych ROM-ach
producentów zawodzi dla *każdej* bazy danych, nie zwracając nic. Aby czytać w ten sam
sposób na każdym urządzeniu, VD Google dołącza własny plik wykonywalny `sqlite3` (arm64 i
arm32) i uruchamia zapytania przez niego jako root. Aplikacja i WebUI używają identycznego
pliku binarnego, więc zawsze czytają tym samym silnikiem i otrzymują ten sam wynik.
Pochodzenie i sumy kontrolne tego pliku są udokumentowane w
[Wbudowane pliki binarne](BINARIES.md).

Odczyty kopiują bazę danych na bok przed otwarciem, nigdy nie dotykając żywego pliku, i
nigdy nie kopiują indeksu pamięci współdzielonej, który działająca baza danych może
zgłosić jako uszkodzony.

## Sygnał RCS

Najtrudniejszą częścią uczciwego czytania RCS jest to, że oczywiste flagi kłamią. Prosta
flaga "dostępne", flagi funkcji i stan provisioningu mogą wszystkie czytać się negatywnie
na linii, na której RCS działa idealnie, ponieważ są to pamięci podręczne albo dlatego,
że linia działa na własnym zapleczu Google (Jibe), a nie w sieci IMS operatora. VD Google
wylicza jeden werdykt **Połączenie** z rzeczywistej konfiguracji czatu i silnika
provisioningu, który pozostaje poprawny w różnych wersjach Androida i Google Messages, a
kłamliwe flagi prezentuje wyraźnie oznaczone jako zbuforowane, aby informowały, nie
wprowadzając w błąd. Zobacz [przewodnik użytkownika](USER-GUIDE.md), aby dowiedzieć się,
co oznacza każda linia.

## Eksport

Eksport to oczyszczony zrzut JSON. Przed zapisaniem czegokolwiek uruchamia się sanityzer:
usuwa klucze i wartości, które wyglądają jak dane uwierzytelniające, redaguje długie ciągi
cyfr i nigdy nie emituje twojego numeru telefonu, treści wiadomości, nazw kontaktów ani
surowych blobów możliwości. Opcjonalna sekcja diagnostyczna niesie tylko nazwy schematów,
liczby, rozmiary i wersje - a nawet tam każdy odcisk powiązany z tożsamością osadzony w
nazwie jest usuwany. Aplikacja i WebUI produkują ten sam schemat.

## Czego VD Google nigdy nie robi

- Nigdy nie deklaruje uprawnienia sieciowego, a WebUI nigdy nie kontaktuje się ze zdalnym
  źródłem.
- Nigdy nie fabrykuje, nie podszywa się pod ani nie ukrywa żadnej wartości.
- Nigdy nie raportuje werdyktu Play Integrity, ponieważ tego werdyktu nie ma na
  urządzeniu.
- Zmienia stan wyłącznie przez wyraźne, potwierdzone, lokalnie zalogowane działanie.
