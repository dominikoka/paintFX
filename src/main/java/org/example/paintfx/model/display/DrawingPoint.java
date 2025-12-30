package org.example.paintfx.model.display;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class DrawingPoint {
  private Point2D point2D;
  private Color color;
  //private Point2D rotationPoint;
  private Integer sizePoint;
  public Point2D getPoint2D() {
    return point2D;
  }

  public Color getColor() {
    return color;
  }


  public DrawingPoint( Point2D point2D,Color color,Integer sizePoint) {
    this.color = color;
    this.point2D = point2D;
    this.sizePoint = sizePoint;
  }
}
