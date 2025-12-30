package org.example.paintfx.model.display.draw;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.example.paintfx.model.display.DrawingPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawingPathTest {
  @Test
  public void addPointsBetweenTwoPointsShouldAddPointsSmallerThanLastPoint() {
    //arrange
    int x2 = 30;
    int y2 = 30;
    Point2D firstPoint = new Point2D(0, 0);
    Point2D secontPoint = new Point2D(x2, y2);
    DrawingPoint firstDrawingPoint = new DrawingPoint(firstPoint, Color.BLACK, 12);
    DrawingPoint secondDrawingPoint = new DrawingPoint(secontPoint, Color.BLACK, 12);
    DrawingPath drawingPath = new DrawingPath();
    //act
    drawingPath.addPoint(firstDrawingPoint);
    drawingPath.addExtraPoint(secondDrawingPoint);
    //assert
    for (var point : drawingPath.getDrawingPath()) {
      assertTrue(point.getPoint2D().getX() < x2);
      assertTrue(point.getPoint2D().getY() < y2);
    }
  }

  @Test
  public void checkExtraPointsBetweenTwoPoints() {
    //arrange
    Point2D firstPoint = new Point2D(0, 0);
    Point2D secontPoint = new Point2D(30, 30);
    DrawingPoint firstDrawingPoint = new DrawingPoint(firstPoint, Color.BLACK, 12);
    DrawingPoint secondDrawingPoint = new DrawingPoint(secontPoint, Color.BLACK, 12);
    DrawingPath drawingPath = new DrawingPath();
    //act
    drawingPath.addPoint(firstDrawingPoint);
    drawingPath.addExtraPoint(secondDrawingPoint);
    //assert
    assertTrue(drawingPath.getDrawingPath().size() > 30);
  }

  @Test
  public void checkSnapshot() {
    //arrange
    Point2D firstPoint = new Point2D(0, 0);
    DrawingPoint firstDrawingPoint = new DrawingPoint(firstPoint, Color.BLACK, 12);
    DrawingPath drawingPath = new DrawingPath();
    //act
    drawingPath.addPoint(firstDrawingPoint);
    var drawroad = drawingPath.getCopyDrawingRoad();
    var snapshot = drawingPath.snapshot();
    //assert
    assertEquals(drawroad, snapshot.getCopyDrawingRoad());
  }

  @Test
  public void setNewPaintRoad() {
    //arrange
    Point2D firstPoint = new Point2D(0, 0);
    Point2D secontPoint = new Point2D(30, 30);
    DrawingPoint firstDrawingPoint = new DrawingPoint(firstPoint, Color.BLACK, 12);
    DrawingPoint secondDrawingPoint = new DrawingPoint(secontPoint, Color.BLACK, 12);
    DrawingPath drawingPath = new DrawingPath();
    DrawingPath newDrawingPath = new DrawingPath();
    //act
    drawingPath.addPoint(firstDrawingPoint);
    drawingPath.addExtraPoint(secondDrawingPoint);
    newDrawingPath.setNewPaintRoad(drawingPath);
    //assert
    assertEquals(drawingPath, newDrawingPath);
  }


}
