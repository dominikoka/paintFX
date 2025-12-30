package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;

public class Rubber implements Tool {
  @Override
  public void handleDrag(Point2D point2D, DisplayController displayController) {
    displayController.removeDrawWithHistory(point2D);
  }
}
