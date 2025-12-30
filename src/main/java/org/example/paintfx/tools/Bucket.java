package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;

public class Bucket implements Tool {
  @Override
  public void handleClick(Point2D point2D, DisplayController displayController) {
    displayController.fillPlace(point2D);
    //displayController.finalizeDrawElement(point2D);
  }
}
