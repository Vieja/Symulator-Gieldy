Projekt na Programowanie Obiektowe na 3. semestrze Informatyki, WI, PP.

Celem programu jest uproszczona symulacja rynku giełdowego. Projekt zaliczeniowy na 2. roku informatyki na WI PP. Głównym celem projektu było zaznajomienie się z obsługą obiektów (w szczególności z zastosowaniem praktycznym abstrakcji, enkapsulacji, polimorfizu i dziedziczenia) w języku Java oraz z JavaFX (w celu stworzenia GUI aplikacji), a także z realizacją wielowątkowości.

Program składa się z kilku zakładek:

**Rynki**: 
Wyświetla wszystkie rynki w symulacji. Przyciski pozwalają na dodanie rynku (losowego typu, o losowych informacjach), a także usunięcie już istniejącego.

**Spółki**:
Wybór jednej z giełd papierów wartościowych wyświetli listę spółek notowanych na niej. Z kolei wybór spółki wyświetli o niej szczegółowe informacje. Po prawej stronie jest wyświetlona lista wszystkich utworzonych indeksów – wybór jednego z nich wyświetli listę spółek wchodzacych w jego skład. Przycisk „Dodaj indeks” utworzy losowy indeks na losowej giełdzie, składajacy się z losowej ilości spółek, natomiast „Dodaj WIG” utworzy indeks na giełdzie, który bedzie zawierał X spólek o największym obrocie. Parametr X jest wprowadzany w TextBoxie obok. Jeżeli żaden z rynków nie ma więcej niż X spółek, WIG nie powstanie. „Usuń indeks” usunie indeks.

**Inwestorzy**:
Wyświetla wszystkich inwestorów, zarówno indywidualnych, jak i fundusze inwestycyjne. Wybór inwestora wyświetli o nim szczegółowe informacje, a także ilość aktualnie posiadanych aktyw. W przypadku inwestora indywidualnego wyświetlone zostaną także posiadanie przez niego jednostki funduszu. Przyciski pozwalają na dodanie/usunięcie dowolnego inwestora.

**Aktywa**:
Wyświetla wszystkie możliwe do zakupienia przez inwestorów akcje. Wybranie jednego wyświetli informacje, a także wykreśli wykres przedstawiajacy kurs w ciągu ostatnich 10 dni symulacji. Przyciski pozwalają na dodanie i usuwanie aktyw.

**Statystyka**:
Ta zakładka pozwala na wyświetlanie i porównywanie procentowych zmian kursu wybranych aktyw. Zaznaczenie aktywa z listy doda je jako serię na wykresie. Przycisk „Aktualizuj” wyświetli aktualny stan wybranych aktyw dla 10ciu ostatnich dni, a przycisk „Reset” wyczyści wykres i usunie serie.
