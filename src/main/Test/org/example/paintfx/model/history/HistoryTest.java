package org.example.paintfx.model.history;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.model.display.DrawingPoint;
import org.example.paintfx.model.display.SelectionRectangle;
import org.example.paintfx.model.display.draw.DrawEditor;
import org.example.paintfx.model.display.draw.DrawingPath;
import org.example.paintfx.model.display.text.Letter;
import org.example.paintfx.model.display.text.TextEditor;
import org.example.paintfx.view.MainView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class HistoryTest {
  private final static Logger logger = LoggerFactory.getLogger(HistoryTest.class);


  Point2D point2D;
  int size;
  Color color;
  DrawingPoint drawingPoint;
  DrawingPath drawingPath;
  History history;
  KeyEvent a;

  @BeforeEach
  void init() {
    point2D = new Point2D(10, 10);
    size = 5;
    color = Color.BLACK;
    drawingPoint = new DrawingPoint(point2D, color, size);
    drawingPath = new DrawingPath();
    history = new History();
    a = new KeyEvent(KeyEvent.KEY_PRESSED,
        "a", "a",
        KeyCode.A, false, false, false, false);
  }

  @Test
  public void addDrawToHistory() {
    //arrange
    drawingPath.addPoint(drawingPoint);
    history.addDrawRoad(drawingPath);
    //act
    var lastDrawRoadFromHistory = history.getLastDraw();
    //assert
    assertEquals(drawingPath, lastDrawRoadFromHistory);
  }

  @Test
  public void saveByEditType() {
    //arrange
    MainView mainView = new MainView();
    Letter letter = new Letter(a, drawingPoint);
    TextEditor textEditor = new TextEditor();
    DrawEditor drawEditor = new DrawEditor();
    SelectionRectangle selectionRectangle = new SelectionRectangle(0.0, 0.0);
    //act
    drawingPath.addPoint(drawingPoint);
    textEditor.addLetter(letter);
    drawEditor.setDrawingPath(drawingPath);
    selectionRectangle.setSecondPoint(100.0, 100.0);
    DisplayController displayController = new DisplayController(drawEditor, textEditor, history, selectionRectangle, mainView.getCanvas());
    displayController.saveByEditType();
    var historyText = history.getLastGroupTexts();
    var historyDraw = history.getLastDraw();
    //assert
    assertNotNull(historyText);
    assertNotNull(historyDraw);
  }
}
