package org.example.paintfx.model.display.figure;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class FigureBasedLine extends Figure {
  private double x1;
  private double y1;
  private double x2;
  private double y2;

  public FigureBasedLine(Point2D p1, Point2D p2, Color color, FigureType type,int lineSize) {
    super(color,type,lineSize);
    this.x1 = p1.getX();
    this.y1 = p1.getY();
    this.x2 = p2.getX();
    this.y2 = p2.getY();
  }

}
