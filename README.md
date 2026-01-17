
# PaintFX

**PaintFX** is a desktop application written in **Java** – a simple drawing and image editing tool inspired by classic “Paint” applications.

The most polished features in this project:

* **Bucket fill using the BFS algorithm** – fast filling of areas with the same color
* **Selection rotation by 90° / 180° / 270°**
* **Export to PNG**

---

## Features

### Drawing and color

* drawing on the canvas (pencil tool)
* color picker
* **bucket fill – BFS**
  The fill operation starts from the clicked pixel and spreads the color to neighboring pixels that have the same original color.

### Editing

* selecting a fragment of the image
* **selection rotation: 90° / 180° / 270°**

### Export

* **saving the image as PNG**

---

## Screenshot

![Application preview](CanvasImage.png)

---

## How to run

### Option 1: IntelliJ IDEA

1. Open the project.
2. Wait for dependencies to be downloaded (if you are using Maven/Gradle).
3. Run the application entry class (e.g. `Main` / `App`).

### Option 2: Command line (if you use Maven Wrapper / JavaFX plugin)

Windows:

```bash
mvnw.cmd clean javafx:run
```

macOS / Linux:

```bash
./mvnw clean javafx:run
```

---

## How the Bucket Tool (BFS) works – short explanation

1. Read the color of the clicked pixel (start color).
2. If the start color is the same as the target color → do nothing.
3. Add the start pixel to a queue.
4. Take a pixel from the queue and check its neighbors (up / down / left / right).
5. If a neighbor has the start color and is inside the image bounds, change its color and add it to the queue.
6. Repeat until the queue is empty.

This approach is predictable and avoids stack overflow issues that can occur with very deep recursion.

**Author:** Dominik (dominikoka)
