> **Languages:** [English](../USER-GUIDE.md) · [العربية](../ar/USER-GUIDE.md) · [Čeština](../cs/USER-GUIDE.md) · [Deutsch](../de/USER-GUIDE.md) · [Español](../es/USER-GUIDE.md) · [فارسی](../fa/USER-GUIDE.md) · [Français](../fr/USER-GUIDE.md) · [हिन्दी](../hi/USER-GUIDE.md) · [Bahasa Indonesia](../in/USER-GUIDE.md) · [Italiano](../it/USER-GUIDE.md) · [日本語](../ja/USER-GUIDE.md) · [한국어](../ko/USER-GUIDE.md) · [Nederlands](../nl/USER-GUIDE.md) · Polski · [Português](../pt/USER-GUIDE.md) · [Русский](../ru/USER-GUIDE.md) · [Svenska](../sv/USER-GUIDE.md) · [ไทย](../th/USER-GUIDE.md) · [Türkçe](../tr/USER-GUIDE.md) · [Українська](../uk/USER-GUIDE.md) · [Tiếng Việt](../vi/USER-GUIDE.md) · [中文](../zh/USER-GUIDE.md)

---

# Przewodnik użytkownika VD Google

Przyjazny, kompletny przewodnik po VD Google: co pokazuje, co oznacza każda linia i jak
czytać swoje urządzenie uczciwie. VD Google czyta stan RCS, Google Wallet oraz atestacji
Play Integrity, o które proszą aplikacje, i wyjaśnia każdy element prostymi słowami. Nigdy
nie dotyka internetu i nigdy niczego nie zmienia, chyba że o to poprosisz.

## Spis treści

- [1. Czym jest VD Google](#1-czym-jest-vd-google)
- [2. Czym nie jest](#2-czym-nie-jest)
- [3. Wymagania](#3-wymagania)
- [4. Aplikacja czy WebUI](#4-aplikacja-czy-webui)
- [5. Instalacja i pierwsze uruchomienie](#5-instalacja-i-pierwsze-uruchomienie)
- [6. Karty](#6-karty)
  - [6.1 Informacje](#61-informacje)
  - [6.2 RCS](#62-rcs)
  - [6.3 Wallet](#63-wallet)
  - [6.4 DroidGuard](#64-droidguard)
  - [6.5 Atestacje](#65-atestacje)
- [7. Wyeksportuj swój raport](#7-wyeksportuj-swój-raport)
- [8. Działania, które zmieniają stan](#8-działania-które-zmieniają-stan)
- [9. Język i motyw](#9-język-i-motyw)
- [10. Najczęściej zadawane pytania](#10-najczęściej-zadawane-pytania)

## 1. Czym jest VD Google

VD Google to czytnik. Patrzy na to, co własne aplikacje Google przechowują już na twoim
urządzeniu w trzech sprawach, i wyjaśnia każdą z nich, abyś naprawdę ją zrozumiał:

- **RCS** - czy nowoczesny czat naprawdę działa na twojej linii, z kim możesz czatować i
  czy Gemini w Google Messages jest gotowy.
- **Google Wallet** - czy twoje urządzenie jest skonfigurowane i poświadczone dla
  płatności zbliżeniowych.
- **Atestacje Play Integrity** - które aplikacje poprosiły Androida o udowodnienie, że
  twoje urządzenie jest oryginalne, i czy Google obecnie je certyfikuje.

Wszystko jest czytane lokalnie, przez roota, i pokazywane z wyjaśnieniem. Żadna wartość
nie jest wymyślana: gdy urządzenie nie ma odpowiedzi, VD Google mówi "Nieznane", zamiast
zgadywać.

## 2. Czym nie jest

**VD Google nie jest narzędziem do sprawdzania integralności.** Nie uruchamia ani nie
raportuje żadnego werdyktu basic / device / strong. Ten wynik żyje wyłącznie wewnątrz
zaszyfrowanego tokena, który aplikacja wysyła na swój własny serwer, i nigdy nie jest
przechowywany na urządzeniu, więc żaden uczciwy czytnik nie może go pokazać. Narzędzia,
które twierdzą, że wyświetlają twój "werdykt Play Integrity" lokalnie, pokazują ci
zbuforowane zgadywanie lub żądanie, które same wykonały, a nie prawdziwą odpowiedź.

VD Google również niczego nie podszywa, nie ukrywa ani nie fałszuje. Czyta prawdę. Jeśli
jakaś wartość wygląda dla ciebie na błędną, celem tego przewodnika jest wyjaśnienie,
dlaczego prawda tak wygląda.

## 3. Wymagania

- **Android 8.0 (API 26) lub nowszy.**
- **Root.** Dane, które czyta VD Google, żyją w chronionych, wyłącznie systemowych
  obszarach Androida, osiągalnych tylko z rootem. Działa każde nowoczesne rozwiązanie
  roota: KernelSU, KernelSU Next, Magisk lub APatch.
- **Bez internetu.** VD Google nie deklaruje żadnych uprawnień, więc nie ma czego
  zezwalać ani czego blokować.

## 4. Aplikacja czy WebUI

VD Google jest dostarczany na dwa sposoby i pokazują one dokładnie te same informacje:

- **Aplikacja (APK).** Zwykła aplikacja Androida, którą instalujesz i otwierasz. Prosi o
  roota za pierwszym razem, jak każda aplikacja rootowa.
- **Moduł WebUI (ZIP).** Zainstaluj go w swoim menedżerze roota (KernelSU, Magisk lub
  APatch) i otwórz go stamtąd. Nic nie jest instalowane jako aplikacja; działa wewnątrz
  WebUI twojego menedżera roota.

Wybierz to, co wolisz. Ekrany, sformułowania i eksportowany raport są identyczne między
tymi dwoma.

## 5. Instalacja i pierwsze uruchomienie

**Aplikacja:** zainstaluj APK i otwórz ją. Za pierwszym razem przyznaj roota, gdy
poprosi twój menedżer roota. Jeśli odmówisz lub odrzucisz roota, VD Google pokazuje ekran
wyjaśniający, dlaczego root jest wymagany, i oferuje ponowną próbę.

**WebUI:** zainstaluj ZIP jako moduł w swoim menedżerze roota, a następnie otwórz WebUI
modułu z menedżera. Czyta, gdy tylko się otworzy.

Przy pierwszym otwarciu możesz wybrać swój język; możesz zmienić go później z ikony koła
zębatego u góry.

## 6. Karty

Na dole znajduje się pięć kart: Informacje, DroidGuard, RCS, Wallet i Atestacje. Jako
pierwsza otwiera się Informacje.

### 6.1 Informacje

Czym jest aplikacja, obietnica prywatności, wersja i linki. Przeczytaj raz sekcję
prywatności: to cały sens aplikacji.

### 6.2 RCS

To najbogatsza karta. Znaczniki czasu są zawsze pokazywane w strefie czasowej twojego
urządzenia, z oznaczoną strefą.

- **Połączenie** - jedna linia, która mówi prawdę. Jest wyliczana z rzeczywistej
  konfiguracji czatu i silnika provisioningu, więc mówi Połączono, gdy RCS rzeczywiście
  działa na twojej linii, nawet na nowszym Androidzie, gdzie starsze flagi kłamią. **W
  razie wątpliwości ufaj tej linii.**
- **Operator** - operator komórkowy twojej linii (nazwa i MCC/MNC), pobrany z twojej
  karty SIM z danymi w telefonach dual-SIM.
- **Flaga dostępności (zbuforowana)** - zbuforowana flaga, którą aplikacja do wiadomości
  utrzymuje dla twojego własnego numeru. Może pozostać nieaktualna po tym, jak RCS już
  działa, więc "Nie" tutaj nie oznacza, że RCS jest wyłączony. Ufaj linii Połączenie
  powyżej.
- **Twój numer RCS** - numer zarejestrowany dla RCS, domyślnie zamaskowany. Dotknij oka,
  aby go odsłonić.
- **Stan provisioningu (zbuforowany)** i **Wersja konfiguracji** - wartości wewnętrzne,
  które utrzymuje aplikacja do wiadomości. Mogą czytać się jako 0, nawet gdy RCS działa.
  Zobacz uwagę o operatorze a zapleczu Google poniżej.
- **Kontakty obsługujące RCS** - ilu z twoich kontaktów jest obecnie znanych jako
  obsługujący czat RCS.
- **Flaga funkcji (zbuforowana)** - czy aplikacja do wiadomości ma włączoną funkcję RCS;
  zbuforowana, więc może być opóźniona.
- **Uprawnienie operatora**, **Obecność (UCE)**, **Usługa IMS obecna** - opisują one
  ścieżkę RCS *operatora* (sieć IMS operatora). "Nie" lub "0" tutaj jest normalne, gdy
  twój RCS działa przez własne zaplecze Google zamiast przez operatora. Zobacz poniżej.
- **Gemini** - czy Gemini w Google Messages jest gotowy: **Magic Compose** (pomoc w
  pisaniu od Gemini), **AICore** (silnik na urządzeniu uruchamiający Gemini Nano) oraz
  **czat Gemini**, który jest dostarczany przez RCS, a więc wymaga połączonego RCS.
- **Znaczniki czasu** - provisioning, synchronizacja, ostatnia wiadomość, wygaśnięcie
  tokena i więcej.

**RCS operatora a zaplecze Google.** Są dwa sposoby, w jakie RCS dociera do twojego
telefonu. Jeden to RCS *twojego operatora* przez jego sieć IMS; drugi to *własne zaplecze
Google* (Jibe), które działa niezależnie od operatora. Jeśli twój RCS działa przez
zaplecze Google - jak u większości ludzi - to linie po stronie operatora (uprawnienie
operatora, obecność/UCE, usługa IMS, wersja konfiguracji) są naprawdę "Nie" lub "0" i to
jest poprawne, a nie nieaktualna pamięć podręczna. Twój RCS mógł działać tak od lat. Linia
Połączenie jest tym, co mówi ci, że działa.

### 6.3 Wallet

- **Urządzenie poświadczone** - czy Google uznał to urządzenie za wystarczająco
  oryginalne i bezpieczne, by przechowywać karty płatnicze. To ostatni wynik, który Google
  zapisał na tym urządzeniu, więc może być opóźniony wobec aplikacji Wallet, dopóki Google
  nie sprawdzi ponownie. Jeśli zapisanego wyniku brakuje, VD Google pokazuje **Nieznane**,
  a nie mylące "Nie".
- **Ostatnio sprawdzone** - kiedy ta kontrola bezpieczeństwa ostatnio się zakończyła.
- **Sprzęt NFC**, **Wallet zainstalowany**, **Usługa Wallet**, **Zbliżeniowe płatności**,
  **Blokada ekranu zaakceptowana**, **Karta kiedykolwiek dodana** - elementy, które
  umożliwiają płatność zbliżeniową.

### 6.4 DroidGuard

- **Certyfikacja Play Protect** - czy Google Play rozpoznaje twoje urządzenie jako
  certyfikowane (certyfikacja pokazywana w Sklepie Play).
- **Pamięć podręczna atestacji** - atestacje Play Integrity i DroidGuard, które twoje
  urządzenie zbuforowało, każda z informacją, kiedy wygasa.

### 6.5 Atestacje

Historia tego, które aplikacje żądały Play Integrity na twoim urządzeniu: ikona, nazwa,
kiedy ostatnio pytały i ile razy. Pozwala to stwierdzić, czy dana aplikacja sprawdza
integralność, czy nie. Użyj pola wyszukiwania, aby filtrować po nazwie aplikacji lub
pakiecie.

## 7. Wyeksportuj swój raport

Ikona pobierania zapisuje raport **JSON** ze stanem twojego urządzenia, RCS, Wallet i
atestacji. Możesz go udostępnić lub zapisać jako plik. Raport nie zawiera **żadnego numeru
telefonu, żadnych danych uwierzytelniających ani żadnych treści osobistych** - jest
oczyszczany przed zapisaniem. Zawiera też opcjonalną sekcję **diagnostyczną** (nazwy
schematów, liczby i wersje, nigdy twoje dane), która pomaga rozwiązywać problemy specyficzne
dla urządzenia. Aplikacja i WebUI zapisują ten sam raport.

## 8. Działania, które zmieniają stan

VD Google jest w większości tylko do odczytu, ale może wykonać jedno **działanie
sanityzujące**: wyczyszczenie zbuforowanego wyniku atestacji Google Wallet, co pozwala
przyszłej ponownej kontroli zacząć od nowa. Każde działanie, które zmienia stan, prosi cię
najpierw o potwierdzenie i jest zapisywane w lokalnym logu JSON na urządzeniu. Nic nie
opuszcza twojego telefonu.

## 9. Język i motyw

VD Google mówi w 21 językach. Wybierz swój przy pierwszym uruchomieniu lub zmień go w
dowolnej chwili z ikony koła zębatego (dostępnej również na ekranie odmowy roota). Ikona
jasności przełącza między trybem jasnym a ciemnym.

## 10. Najczęściej zadawane pytania

**RCS mówi Połączono, ale kilka linii mówi Nie lub 0. Dlaczego?** Ponieważ twój RCS
działa przez własne zaplecze Google, a nie przez operatora. Linie po stronie operatora są
poprawnie negatywne. Zobacz [6.2 RCS](#62-rcs).

**Urządzenie poświadczone mówi Nieznane.** Zbuforowanego wyniku Wallet brakuje na twoim
urządzeniu. To uczciwe - VD Google nie pokaże "Nie", którego nie może poprzeć.

**Gdzie jest mój werdykt Play Integrity?** Nie ma go na twoim urządzeniu. Zobacz
[2. Czym nie jest](#2-czym-nie-jest).

**Czy działa bez roota?** Nie. Dane żyją w obszarach wyłącznie systemowych.
