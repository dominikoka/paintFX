package org.example.paintfx.model.display.draw;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawEditorTest {
  Point2D fPointRectangle = new Point2D(0, 0);
  Point2D sPointRectangle = new Point2D(20, 20);
  DrawEditor drawEditor = new DrawEditor();

  @BeforeEach
  void init() {
    drawEditor.addPoint(new Point2D(0, 0), Color.BLACK);
    drawEditor.addPoint(new Point2D(19, 19), Color.BLACK);
    drawEditor.updatePaintStatusInRectangle(fPointRectangle, sPointRectangle);
  }

  @Test
  public void addPointOutsideSelectionShouldReturnFalseStatus() {
    drawEditor.addPoint(new Point2D(21, 19), Color.BLACK);
    var lastPointStaus = drawEditor.getSelectionState().getEditPoints().getLast();
    assertFalse(lastPointStaus);
  }

  @Test
  public void updateStatusInRectangle() {
    //arrange
    //act
    var statusElementsToDraw = drawEditor.getSelectionState().getEditPoints();
    //assert
    for (var element : statusElementsToDraw) {
      assertTrue(element);
    }
  }

  @Test
  public void resetStatusAllPoints() {
    //arrange
    //act
    drawEditor.resetStatus();
    var statusElementsToDraw = drawEditor.getSelectionState().getEditPoints();
    //assert
    for (var element : statusElementsToDraw) {
      assertFalse(element);
    }
  }


}
