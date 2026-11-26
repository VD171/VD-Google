> **Languages:** [English](../AI-AGENTS.md) · [العربية](../ar/AI-AGENTS.md) · [Čeština](../cs/AI-AGENTS.md) · [Deutsch](../de/AI-AGENTS.md) · [Español](../es/AI-AGENTS.md) · [فارسی](../fa/AI-AGENTS.md) · [Français](../fr/AI-AGENTS.md) · [हिन्दी](../hi/AI-AGENTS.md) · [Bahasa Indonesia](../id/AI-AGENTS.md) · [Italiano](../it/AI-AGENTS.md) · [日本語](../ja/AI-AGENTS.md) · [한국어](../ko/AI-AGENTS.md) · [Nederlands](../nl/AI-AGENTS.md) · Polski · [Português](../pt/AI-AGENTS.md) · [Русский](../ru/AI-AGENTS.md) · [Svenska](../sv/AI-AGENTS.md) · [ไทย](../th/AI-AGENTS.md) · [Türkçe](../tr/AI-AGENTS.md) · [Українська](../uk/AI-AGENTS.md) · [Tiếng Việt](../vi/AI-AGENTS.md) · [中文](../zh/AI-AGENTS.md)

---

# Agenci AI

VD Google zamienia własne zapisy Google na urządzeniu w coś, co **agent AI może odczytać w jednym kroku**. Zamiast otwierać kilka aplikacji i baz danych Google i zgadywać ich znaczenie, agent czyta jeden ustrukturyzowany **eksport** i wie dokładnie, co urządzenie zgłasza na temat RCS, Google Wallet i Play Integrity.

Ta strona rozwija pomysł przedstawiony w README.

## Eksport

Zarówno aplikacja, jak i WebUI tworzą ten sam eksport JSON:

- **Aplikacja**: akcja **Eksportuj** udostępnia lub zapisuje raport.
- **WebUI**: akcja **Eksportuj** zapisuje go w `/sdcard/Download/VDGoogle/vdgoogle-<timestamp>.json`.

Oba mają ten sam schemat, ponieważ aplikacja i WebUI są utrzymywane jako identyczne. Nic nie jest nigdzie wysyłane: eksport jest zapisywany na urządzeniu i tam pozostaje.

## Co się w nim znajduje

Eksport to jeden obiekt JSON z następującymi kluczami najwyższego poziomu:

- `app`, `version`, `exportedAt`, `timezone` - co utworzyło raport i kiedy, w oznaczonej strefie czasowej urządzenia.
- `device` - producent, model, wersja Androida i poziom SDK.
- `rcs` - dostępność, stan, wersja konfiguracji, zgodne kontakty, autoryzacja operatora, obecność (UCE) i powiązane znaczniki czasu.
- `wallet` - atestacja płatności, sprzęt NFC, stan płatności zbliżeniowych, akceptacja blokady ekranu oraz czy kiedykolwiek dodano kartę.
- `diagnostics` - sekcja autodiagnostyki, stworzona do zgłoszeń błędów.

Eksport nie zawiera **żadnych danych osobowych**: żadnego numeru telefonu, żadnych poświadczeń, żadnej treści wiadomości, żadnej nazwy kontaktu. Każda wartość to uczciwy odczyt - prawdziwe nie pozostaje nie, nieaktualna pamięć podręczna jest oznaczana jako z pamięci podręcznej, brakująca wartość to Nieznane.

## Tylko do odczytu, z jedną rejestrowaną akcją

VD Google czyta. Jedynym wyjątkiem jest akcja sanityzacji (wyczyszczenie zapisanego w pamięci podręcznej wyniku atestacji Google Wallet, aby wymusić ponowne sprawdzenie). Każda akcja zmieniająca stan prosi o potwierdzenie na urządzeniu i jest zapisywana w lokalnym dzienniku akcji w formacie JSON. Agent sterujący WebUI widzi to samo potwierdzenie i ten sam dziennik, nie ma ukrytej, nierejestrowanej ścieżki.

## Brak kontraktu sieciowego

VD Google nie udostępnia żadnego API sieciowego ani gniazda: nie deklaruje żadnych uprawnień. Agent korzysta z **pliku eksportu**, nie wywołuje usługi. To jest gwarancja prywatności, zachowana nawet przy automatyzacji.

<!-- doc-footer -->

---

> _Ta dokumentacja została wygenerowana za pomocą Claude (Anthropic) i jest utrzymywana przez VD171. Poprawki i ulepszenia są mile widziane, zobacz [CONTRIBUTING.md](CONTRIBUTING.md)._
