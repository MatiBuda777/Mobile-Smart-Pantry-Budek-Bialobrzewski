# 📱 Smart Pantry Project

**Project**: *Red Planet Pantry (Mars Colony)*

**Status projektu**: READY TO LAUNCH
**Klient**: Alan Musk (CEO of Space-X) 

**Cel**: Przetrwanie pierwszych kolonistów na Marsie.

## 🔴 Mission Briefing
Witajcie Inżynierowie! Gratuluję dołączenia do zespołu Space-X. 
Nasz statek Starship wyląduje na Marsie za 6 miesięcy. 
Koloniści będą musieli zarządzać swoimi zapasami jedzenia, tlenu i części zamiennych w ekstremalnie trudnych warunkach.

## 📩 Notatka od Alana Muska
"Słuchajcie, sprawa jest prosta. Jeśli kolonista nie będzie wiedział, że kończą mu się zapasy liofilizowanej pizzy, to mamy problem. Aplikacja musi być szybka jak rakieta Falcon 9 i niezawodna jak silnik Raptor.

Nie obchodzi mnie, jak to zrobicie, ale dane mają być zapisywane w formacie JSON – to nasz standard przesyłu danych między Ziemią a Marsem. Nie chcę widzieć bałaganu w kodzie! Macie pracować w parach, bo na Marsie nikt nie przetrwa sam.

Do roboty!"

— Alan Musk

---

##🛠️ Wymagania Techniczne (Engineering Specs)
### 1. Cargo Manifest (Data Model) & Visuals
Alan Musk jasno zaznaczył: "Podczas awarii na Marsie nikt nie ma czasu czytać małego tekstu. 
Muszę widzieć ikonę i wiedzieć, czy to butla z tlenem, czy paczka krakersów".

Zdefiniujcie model danych Product. Każdy przedmiot w magazynie musi mieć:
- `UUID` (`String`) - unikalny identyfikator przesyłki.
- `Name` (`String`) - nazwa towaru.
- `Quantity` (`Int`) - ilość.
- `Category` (`String`) - np. "Food", "Life Support", "Tools".
- `ImageRef` (`String`) - Referencja wizualna.

Wymóg Techniczny dot. Obrazów: 
- Nie przechowujemy całych zdjęć w JSON! Przechowujemy tylko tekstowy odnośnik do grafiki (np. nazwę pliku w zasobach aplikacji lub URL).
- Aplikacja musi umieć zamienić ten tekst na obrazek w ImageView.

### 2. Local Storage (JSON)
Dane muszą być składowane w pliku inventory.json.

Input/Output: Aplikacja musi potrafić odczytać ten plik przy starcie i zapisać go przy każdej zmianie (CRUD).

### 3. Interface (XML)
Interfejs musi być czytelny nawet podczas burzy piaskowej:

Użyjcie ListView (z własnym Adapterem), aby wyświetlić zapasy.

Zastosujcie ViewBinding, aby kod był czysty i wolny od błędów.

## 🛰️ Procedury Operacyjne (Git Workflow)
Pracujemy jak prawdziwy zespół inżynierów:
- *Lead Engineer* (Osoba A) tworzy repozytorium z tego szablonu.
- *Mission Specialist* (Osoba B) zostaje dodany jako Collaborator.
- Pracujecie na gałęziach (ang. Branches). 

---

## 📦 Definition of Done (Kiedy misja jest sukcesem?)
[ ] Aplikacja uruchamia się bez awarii (ang. Crash).

[ ] Można dodać nowy zapas do listy.

[ ] Po ponownym uruchomieniu aplikacji, dodane dane wciąż tam są (poprawny zapis do JSON).

[ ] Kod na GitHubie jest przejrzysty i opisany w języku technicznym.


---
## 🔴  "Red Alert" & "Mission Control"

### *Critical Levels* (*System Red Alert*) 🚨
Alan Musk nie lubi niespodzianek. Jeśli zapasy jakiegoś zasobu spadną poniżej 5 jednostek, interfejs musi zareagować!

Wymaganie: Gdy `quantity < 5`, tło elementu na liście musi zmienić kolor na ostrzegawczą czerwień lub ikona musi zacząć pulsować.

Cel: Natychmiastowe zwrócenie uwagi kolonisty na kończące się zasoby.


### *Mission Control* (GitHub Kanban) 📋
Inżynierowie nie pracują w chaosie. Wasz postęp musi być widoczny dla centrali w Houston.
* Zadanie: W Waszym repozytorium na GitHubie uruchomcie sekcję Projects i stwórzcie tablicę Kanban.
* Kolumny: To Do (Do zrobienia), In Progress (W trakcie), Review/Testing (Testowanie), Done (Gotowe).
* Wymóg: Każda funkcja (np. "Obsługa JSON", "Layout XML") musi być osobnym zadaniem (ang. Issue) na tablicy.

---
## 🛡️ *Safety Protocol* (*ViewBinding*): 

W projekcie używamy ViewBinding. 

Nie używamy `findViewById`. 

Pamiętajcie, że każda zmiana nazwy ID w XMLu (np. `@+id/btn_save`) zostanie natychmiast odzwierciedlona w kodzie jako` binding.btnSave`. 




