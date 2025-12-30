package org.example.paintfx.model.display.text;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.paintfx.model.display.DrawingPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupLetterTest {
  Point2D drawingPlace;
  KeyEvent key1;
  KeyEvent key2;
  KeyEvent key3;
  DrawingPoint drawingPoint1;
  DrawingPoint drawingPoint2;
  DrawingPoint drawingPoint3;
  DrawingPoint drawingPoint4;
  Letter letter1;
  Letter letter2;
  Letter letter3;
  Letter letter4;

  @BeforeEach
  void init() {
    drawingPlace = new Point2D(0, 0);
    key1 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);
    key2 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "o",
        KeyCode.A, false, false, false, false);
    key3 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);
    drawingPoint1 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    drawingPoint2 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    drawingPoint3 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    drawingPoint4 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    letter1 = new Letter(key1, drawingPoint1);
    letter2 = new Letter(key2, drawingPoint2);
    letter3 = new Letter(key3, drawingPoint3);
    letter4 = new Letter(key3, drawingPoint4);
  }
  @Test
  public void shouldCreateSnapshot() {
    //arrange
    GroupLetter text = new GroupLetter();
    text.addElement(letter1);
    text.addElement(letter2);
    text.addElement(letter3);
    var snapshot = text.snapshot();
    //act
    text.setStatus(0,true);
    var firstLetterTextPosition = text.getGroupText().getFirst().getDrawingPlace();
    var firstLetterSnaphotPosition = snapshot.getGroupText().getFirst().getDrawingPlace();
    //assert
    assertNotSame(text.getGroupText(), snapshot.getGroupText());
    assertEquals(firstLetterTextPosition,firstLetterSnaphotPosition);

    assertNotEquals(text.getGroupText().getFirst().getEditStatus(),snapshot.getGroupText().getFirst().getEditStatus());
  }

  @Test
  public void shouldCreateTextInOrder() {
    //arrange
    key1 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);
    key2 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "o",
        KeyCode.A, false, false, false, false);
    key3 = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "w",
        KeyCode.A, false, false, false, false);
    drawingPoint1 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    drawingPoint2 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    drawingPoint3 = new DrawingPoint(drawingPlace, Color.BLACK, 12);
    letter1 = new Letter(key1, drawingPoint1);
    letter2 = new Letter(key2, drawingPoint2);
    letter3 = new Letter(key3, drawingPoint3);
    //act
    String result = "wow";
    GroupLetter text = new GroupLetter();
    text.addElement(letter1);
    text.addElement(letter2);
    text.addElement(letter3);
    //assert
    assertEquals(text.getChapter(), result);
  }

  @Test
  public void shouldChangeGroupText() {
    //arrange
    //act
    GroupLetter text = new GroupLetter();
    text.addElement(letter1);
    GroupLetter text2 = new GroupLetter();
    text2.addElement(letter2);
    text2.addElement(letter3);
    text2.addElement(letter3);
    text2.addElement(letter3);
    text.setGroupText(text2);
    //assert
    assertEquals(text.getGroupText().getFirst(), text2.getGroupText().getFirst());
    assertEquals(text.getGroupText().getLast(), text2.getGroupText().getLast());
    assertEquals(text.getGroupText().size(), text2.getGroupText().size());
    assertNotSame(text.getGroupText(), text2.getGroupText());
  }

  @Test
  public void shouldChangeOnlyFirstLetterStatus() {
    //arrange
    //act
    GroupLetter text = new GroupLetter();
    text.addElement(letter1);
    text.addElement(letter2);
    text.setStatus(0, true);
    //assert
    assertTrue(text.getGroupText().getFirst().getEditStatus());
    assertFalse(text.getGroupText().get(1).getEditStatus());
  }
}
