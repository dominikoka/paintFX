package org.example.paintfx.model.display.text;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.paintfx.model.display.DrawingPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class LetterTest {
  KeyEvent a;
  Point2D drawingPlace;
  Color color;
  Point2D point2D;

  @BeforeEach
  void init() {
    point2D = new Point2D(154, 743);
    a = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "a",
        KeyCode.A, false, false, false, false);
    drawingPlace = new Point2D(point2D.getX(), point2D.getY());
    color = Color.YELLOW;
  }

  @Test
  public void shouldSetCoordinatesFromDrawingPoint() {
    //arrange
    Point2D point = new Point2D(20, 50);
    DrawingPoint drawingPoint = new DrawingPoint(point, color, 12);
    //act
    Letter letter = new Letter(a, drawingPoint);
    //assert
    assertEquals(letter.getDrawingPlace(), point);
  }

  @Test
  public void shouldWrapRotationAfterAdd90DegreeWhenIs270() {
    //arrange
    int degree = 90;
    Letter letter = new Letter(a, new DrawingPoint(point2D, color, 12));
    letter.setLetterRotation(LetterRotation.LEFT_270);
    //act
    letter.rotateByShift(degree);
    //assert
    assertEquals(letter.getLetterRotation(), LetterRotation.NORMAL_0);
  }

  @Test
  public void shouldWrapRotationAfterSubtract90DegreeWhenIs0() {
    //arrange
    int degree = -90;
    Letter letter = new Letter(a, new DrawingPoint(point2D, color, 12));
    //act
    letter.rotateByShift(degree);
    //assert
    assertEquals(letter.getLetterRotation(), LetterRotation.LEFT_270);
  }

  @Test
  public void shouldCreateSnapshot() {
    Letter letter = new Letter(a, new DrawingPoint(drawingPlace, Color.BLACK, 12));
    int sizeLetter = letter.getSize();
    var letterCopy = letter.snapshot();
    int sizeNewLetter = 22;
    letter.setSize(sizeNewLetter);

    assertEquals(letter.getLetterRotation(), letterCopy.getLetterRotation());
    assertEquals(letter.getLetterLocation(), letterCopy.getLetterLocation());
    assertEquals(letter.getX(), letterCopy.getX());
    assertEquals(letter.getY(), letterCopy.getY());
    assertEquals(letter.getColor(), letterCopy.getColor());

    assertEquals(sizeLetter, letterCopy.getSize());
    assertEquals(sizeNewLetter, letter.getSize());

    assertNotSame(letter, letterCopy);
  }

  @Test
  public void shouldCreateNewLetterA() {
    //arrange
    DrawingPoint drawingPoint = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    //act
    Letter letter = new Letter(a, drawingPoint);
    //assert
    assertEquals(letter.getKeyEvent().getText(), "a");
  }

}
