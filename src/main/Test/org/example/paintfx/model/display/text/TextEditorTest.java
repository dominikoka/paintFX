package org.example.paintfx.model.display.text;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.paintfx.model.MatematicMetod;
import org.example.paintfx.model.display.DrawingPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextEditorTest {
  Point2D drawingPlace;
  KeyEvent key1;
  DrawingPoint drawingPoint;

  @BeforeEach
  void init() {
    drawingPlace = new Point2D(0, 0);
    key1 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);
    drawingPoint = new DrawingPoint(drawingPlace, Color.BLACK, 12);
  }


  @Test
  public void shouldMoveSelectedLetterFor20px() {
    //arrange
    Point2D moveLetter = new Point2D(18, 34);
    Point2D startPointRectangle = new Point2D(0, 0);
    Point2D endPointRectangle = new Point2D(50, 50);
    Point2D drawingPlace = new Point2D(0, 0);
    KeyEvent key1 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);
    DrawingPoint drawingPoint = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    Letter letter1 = new Letter(key1, drawingPoint);
    //act
    TextEditor textEditor = new TextEditor();
    textEditor.addLetter(letter1);
    textEditor.updateMoveStatusInRectangle(startPointRectangle, endPointRectangle);
    textEditor.moveSelectedLetters(moveLetter);
    //assert
    assertEquals(textEditor.getGroupLetter().getGroupText().get(0).getX(), moveLetter.getX());
    assertEquals(textEditor.getGroupLetter().getGroupText().get(0).getY(), moveLetter.getY());
  }

  @Test
  public void shouldSet90DegreeForLetter() {
    //arrange
    int degree = 90;
    Rectangle2D rectangleRotate = new Rectangle2D(0, 0, 0, 0);
    Point2D drawingPlace = new Point2D(0, 0);
    KeyEvent key1 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);

    DrawingPoint drawingPoint = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    Letter letter1 = new Letter(key1, drawingPoint);
    Point2D midPoint = new Point2D(25,25);
    //act
    TextEditor textEditor = new TextEditor();
    textEditor.addLetter(letter1);
    textEditor.setAngleForLetter(degree, 0, midPoint, rectangleRotate);
    //assert
    assertEquals(textEditor.getGroupLetter().getGroupText().get(0).getLetterRotation().getDegree(), degree);
  }

  @Test
  public void shouldAddLetterThenRemoveLetter() {
    //arrange
    DrawingPoint drawingPoint = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    Letter letter1 = new Letter(key1, drawingPoint);
    //act
    TextEditor textEditor = new TextEditor();
    textEditor.addLetter(letter1);
    textEditor.removePoint(new Point2D(0, 0));
    //assert
    assertEquals(textEditor.getGroupLetter().getGroupText().size(), 0);
  }

}
