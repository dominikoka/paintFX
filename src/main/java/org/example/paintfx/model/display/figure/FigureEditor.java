package org.example.paintfx.model.display.figure;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FigureEditor {
  private List<Figure> figures;
  private Figure figure;
  private int lineSize;


  public FigureEditor() {
    this.figures = new ArrayList<>();
    lineSize = 10;
  }

  public void previewFigure(Point2D fPoint, Point2D sPoint, FigureType type, Color color) {
    if (type.equals(FigureType.RECTANGLE)) {
      createPreviewRectangle(fPoint, sPoint, color);
    } else if (type.equals(FigureType.OVAL)) {
      createPreviewOval(fPoint, sPoint, FigureType.OVAL, color);
    } else {
      createPreviewLine(fPoint, sPoint, color);
    }
  }

  private void createPreviewLine(Point2D fPoint, Point2D sPoint, Color color) {
    figure = new FigureBasedLine(fPoint, sPoint, color, FigureType.LINE, lineSize);
  }

  public void removePreviewFigure() {
//    figures.add(figure);
    figure = null;
  }

  private void createPreviewOval(Point2D fPoint, Point2D sPoint, FigureType oval, Color color) {
    Rectangle2D rectangle2D = new Rectangle2D(Math.min(fPoint.getX(), sPoint.getX()), Math.min(fPoint.getY(),
        sPoint.getY()), Math.abs(fPoint.getX() - sPoint.getX()), Math.abs(fPoint.getY() - sPoint.getY()));
    figure = new FigureBasedRectangle(rectangle2D, color, oval, lineSize);
  }

  private void createPreviewRectangle(Point2D fPoint, Point2D sPoint, Color color) {
    Rectangle2D rectangle2D = new Rectangle2D(Math.min(fPoint.getX(), sPoint.getX()), Math.min(fPoint.getY(),
        sPoint.getY()), Math.abs(fPoint.getX() - sPoint.getX()), Math.abs(fPoint.getY() - sPoint.getY()));
    figure = new FigureBasedRectangle(rectangle2D, color, FigureType.RECTANGLE, lineSize);
  }
}
