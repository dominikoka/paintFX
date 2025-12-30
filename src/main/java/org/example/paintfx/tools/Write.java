package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.model.display.DrawingPoint;
import org.example.paintfx.model.display.text.Letter;

public class Write implements Tool {
  Point2D drawingPlace;
  DisplayController displayController;

  @Override
  public void handleClick(Point2D point2D, DisplayController displayController) {
    drawingPlace = point2D;
    this.displayController = displayController;
  }

  @Override
  public void handleKey(KeyEvent keyEvent) {
    DrawingPoint drawingPoint = new DrawingPoint(drawingPlace,displayController.getColor(),displayController.getSizeWriteCursor());
    displayController.addLetterWithHistory(new Letter(keyEvent, drawingPoint));
  }
}
