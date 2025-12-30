package org.example.paintfx.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import org.example.paintfx.model.display.DrawingPoint;
import org.example.paintfx.model.display.draw.DrawingPath;

import java.util.ArrayList;
import java.util.List;

public class MatematicMetod {
  public static Point2D getMidPoint(Point2D pStart, Point2D pFinish) {
    var midX = (pFinish.getX() - pStart.getX()) / 2;
    var midY = (pFinish.getY() - pStart.getY()) / 2;
    return new Point2D(midX + pStart.getX(), midY + pStart.getY());
  }

  public static DrawingPath rotateRoad(int newRadians, DrawingPath roadWithStatus, Point2D midPoint) {
    DrawingPath newPoints = new DrawingPath();
    for (int i = 0; i < roadWithStatus.getCopyDrawingRoad().size(); i++) {
      var drawingPointsCopy = roadWithStatus.getCopyDrawingRoad().get(i);
      double x = drawingPointsCopy.getPoint2D().getX() - midPoint.getX();
      double y = drawingPointsCopy.getPoint2D().getY() - midPoint.getY();
      var angle = Math.atan2(y, x);
      var newAngle = angle + Math.toRadians(newRadians);
      var hypotenuse = Math.sqrt(x * x + y * y);
      var newX = hypotenuse * Math.cos(newAngle) + midPoint.getX();
      var newY = hypotenuse * Math.sin(newAngle) + midPoint.getY();
      var sizePoint = roadWithStatus.getCopyDrawingRoad().get(i).getSizePoint();
      DrawingPoint drawingPoint = new DrawingPoint(new Point2D(newX , newY ),
          roadWithStatus.getCopyDrawingRoad().get(i).getColor(),
          sizePoint);

      newPoints.addPoint(drawingPoint);
    }
    return newPoints;
  }

  public static Point2D rotatePoint(double degrees, Point2D oldLocation, Point2D midPoint) {
    double x = oldLocation.getX() - midPoint.getX();
    double y = oldLocation.getY() - midPoint.getY();
    var angle = Math.atan2(y, x);
    var newAngle = angle + Math.toRadians(degrees);
    var hypotenuse = Math.sqrt(x * x + y * y);
    var test = (Math.cos(newAngle) * 20);
    var newX = hypotenuse * Math.cos(newAngle) + midPoint.getX();
    var newY = hypotenuse * Math.sin(newAngle) + midPoint.getY();
    return new Point2D(newX, newY);
  }

  public static List<DrawingPoint> getPointsFromLine(Point2D point2D, Point2D point2D1, int lineSize, Color color) {
    List<DrawingPoint> result = new ArrayList<>();
    int x1 = (int) point2D.getX();
    int y1 = (int) point2D.getY();
    int x2 = (int) point2D1.getX();
    int y2 = (int) point2D1.getY();
    DrawingPoint drawingPoint;
    int diffX = x2 - x1;
    int diffY = y2 - y1;
    int counter;
    counter = 0;
    int oldX1;
    int oldY1;
    if (Math.abs(diffX) > Math.abs(diffY)) {
      oldX1 = x1;
      oldY1 = y1;
      if (x1 > x2) {
        x1 = x2;
        y1 = y2;
        x2 = oldX1;
        y2 = oldY1;
        diffX = x2 - x1;
        diffY = y2 - y1;
      }
      int actualX = x1;
      while (actualX <= x2) {
        double percent = (double) (actualX - x1) / (x2 - x1);
        double newY2 = y1 + (percent * diffY);
        drawingPoint = new DrawingPoint(new Point2D(actualX - 3, newY2 - 5), color, lineSize);
        actualX = actualX + lineSize / 3;
        result.add(drawingPoint);
      }
    } else {
      oldX1 = x1;
      oldY1 = y1;
      if (y1 > y2) {
        x1 = x2;
        y1 = y2;
        x2 = oldX1;
        y2 = oldY1;
        diffX = x2 - x1;
        diffY = y2 - y1;
      }
      int actualY = y1;
      while (actualY <= y2) {
        double percent = (double) (actualY - y1) / (y2 - y1);
        double newX = x1 + (percent * diffX);
        drawingPoint = new DrawingPoint(new Point2D(newX - 3, actualY - 5), color, lineSize);
        actualY = actualY + lineSize / 3;
        result.add(drawingPoint);
      }
    }
    return result;
  }

  public static List<DrawingPoint> getPointsFromElipse(Rectangle2D rectangle2D, Color color, int lineSize) {
    List<DrawingPoint> result = new ArrayList<>();
    double a = (rectangle2D.getWidth() / 2);
    double b = (rectangle2D.getHeight() / 2);
    double centerX = (rectangle2D.getMinX() + a);
    double centerY = (rectangle2D.getMinY() + b);
    DrawingPoint dp;
    double step = lineSize / 10;
    double degree = 0;
    if (a + b > 600) {
      step = step * 0.5;
    }
    if (a + b < 300) {
      step = step * 2;
    }
    step = Math.max(step, 0.4);
    while (degree < 360) {
      double angle = Math.toRadians(degree);
      double x = (centerX + a * Math.cos(angle));
      double y = (centerY + b * Math.sin(angle));
      dp = new DrawingPoint(new Point2D(x, y), color, lineSize);
      result.add(dp);
      degree += step;
    }
    return result;
  }
}
