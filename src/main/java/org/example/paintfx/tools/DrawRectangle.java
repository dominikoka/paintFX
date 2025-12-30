package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.model.display.figure.FigureType;

public class DrawRectangle implements Tool {
  private Point2D clickCordinates = new Point2D(0, 0);
  private Point2D firstRectanglePoint;
  private Point2D secondRectanglePoint;

  @Override
  public void handlePressed(Point2D point2D, DisplayController displayController) {
    firstRectanglePoint = point2D;
  }

  @Override
  public void handleDrag(Point2D point2D, DisplayController displayController) {
    secondRectanglePoint = point2D;
    displayController.renderPreviewFigure(firstRectanglePoint,secondRectanglePoint, FigureType.RECTANGLE);
  }

  @Override
  public void handleReleased(Point2D point2D, DisplayController displayController) {
    displayController.addPreviewFigure();
    //displayController.finalizeDrawElement();
  }
}
