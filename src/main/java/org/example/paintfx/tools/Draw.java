package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;

public class Draw implements Tool {
  private Point2D clickCordinates = new Point2D(0, 0);

  @Override
  public void handlePressed(Point2D point2D, DisplayController displayController) {
    clickCordinates = point2D;
    displayController.setFirstClickDraw(true);
    displayController.addPointWithHistory(new Point2D(point2D.getX(), point2D.getY()), displayController.getColor());
  }

  @Override
  public void handleDrag(Point2D point2D, DisplayController displayController) {
    displayController.setFirstClickDraw(false);
    displayController.addPointWithHistory(new Point2D(point2D.getX(), point2D.getY()), displayController.getColor());
  }

  @Override
  public void handleReleased(Point2D point2D, DisplayController displayController) {
    //displayController.finalizeDrawElement();
  }
}
