package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.model.display.figure.FigureType;

public class DrawOval implements Tool {
  private Point2D firstOvalPoint;
  private Point2D secondOvalPoint;
  @Override
  public void handlePressed(Point2D point2D, DisplayController displayController) {
    firstOvalPoint = point2D;
  }

  @Override
  public void handleDrag(Point2D point2D, DisplayController displayController) {
    secondOvalPoint = point2D;
    displayController.renderPreviewFigure(firstOvalPoint, secondOvalPoint, FigureType.OVAL);
  }

  @Override
  public void handleReleased(Point2D point2D, DisplayController displayController) {
    displayController.addPreviewFigure();
  }
}
