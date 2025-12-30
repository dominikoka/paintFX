package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import lombok.Data;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.model.display.figure.FigureType;

@Data
public class DrawLine implements Tool {
  private Point2D clickCordinates = new Point2D(0, 0);
  private Point2D firstLinePoint;
  private Point2D secondLinePoint;

  @Override
  public void handlePressed(Point2D point2D, DisplayController displayController) {
    firstLinePoint = point2D;
  }

  @Override
  public void handleDrag(Point2D point2D, DisplayController displayController) {
    secondLinePoint = point2D;
    displayController.renderPreviewFigure(firstLinePoint, secondLinePoint, FigureType.LINE);
  }

  @Override
  public void handleReleased(Point2D point2D, DisplayController displayController) {
    displayController.addPreviewFigure();
    //displayController.finalizeDrawElement();
  }
}
