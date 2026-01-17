# PaintFX

aplikacja desktopowa w Javie - edytor rysowania i prostej obróbki grafiki w stylu „Paint”.

Najbardziej dopracowane funkcje w tym projekcie:

* **Wiadro (wypełnianie) z algorytmem BFS** – szybkie wypełnianie obszaru o tym samym kolorze
* **Obrót zaznaczenia o 90° / 180° / 270°**
* **Zapis do PNG**

---

## Funkcje

### Rysowanie i kolor

* rysowanie po płótnie (ołówek)
* wybór koloru
* **wiadro (wypełnianie) – BFS
  Wypełnianie działa tak, że zaczyna od klikniętego piksela i „rozlewa” kolor po sąsiadach w obszarze tego samego koloru.

### Edycja

* zaznaczanie fragmentu obrazu
* **obrót zaznaczenia: 90° / 180° / 270°**

### Eksport

* **zapis obrazu do PNG**

---

## Zrzut ekranu

![Podgląd aplikacji](CanvasImage.png)

---

## Jak uruchomić

### Opcja 1: IntelliJ IDEA

1. Otwórz projekt.
2. Poczekaj aż pobiorą się zależności (jeśli używasz Mavena/Gradle).
3. Uruchom klasę startową aplikacji (np. `Main` / `App`).

### Opcja 2: konsola (jeśli masz Maven Wrapper / plugin do JavaFX)

Windows:

```bash
mvnw.cmd clean javafx:run
```

macOS / Linux:

```bash
./mvnw clean javafx:run
```

---

## Jak działa „Wiadro” (BFS) – w skrócie

1. Pobieram kolor klikniętego piksela (kolor startowy).
2. Jeśli kolor startowy = kolor docelowy → nic nie robię.
3. Wrzucam piksel startowy do kolejki.
4. Z kolejki biorę piksel i sprawdzam sąsiadów (np. góra/dół/lewo/prawo).
5. Jeśli sąsiad ma kolor startowy i jest w granicach obrazu - zmieniam mu kolor i dodaję go do kolejki.
6. Powtarzam aż kolejka się skończy.

To daje przewidywalne działanie i nie ryzykuje „zawieszenia” jak przy bardzo głębokiej rekurencji.

**Autor:** Dominik (dominikoka)
