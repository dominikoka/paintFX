package org.example.paintfx.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.example.paintfx.model.display.DrawingPoint;
import org.example.paintfx.model.display.draw.DrawingPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatematicMetodTest {
  @Test
  public void shouldRotatePoint90Degree()
  {
    //arrange
    Point2D oldPoint = new Point2D(0,0);
    Point2D mid = new Point2D(0,20);
    int radians = 90;
    //act
    Point2D newPoint = MatematicMetod.rotatePoint(radians,oldPoint,mid);
    var pointAfterRotate = new Point2D(20,20);
    //assert
    assertEquals(newPoint,pointAfterRotate);
  }
  @Test
  public void shouldRotatePointMinus90Degree()
  {
    //arrange
    Point2D oldPoint = new Point2D(20,0);
    Point2D mid = new Point2D(0,0);
    int radians = -90;
    //act
    Point2D newPoint = MatematicMetod.rotatePoint(radians,oldPoint,mid);
    var pointAfterRotate = new Point2D(0,-20);
    //assert
    assertEquals(newPoint.getX(),pointAfterRotate.getX(),1e-9);
    assertEquals(newPoint.getY(),pointAfterRotate.getY());
  }
  @Test
  public void shouldRotateRoad180degree()
  {
    //arrange
    Point2D mid = new Point2D(0,50);
    int radians = 180;
    Point2D p1 = new Point2D(0,0);
    Point2D p2 = new Point2D(1,0);
    DrawingPoint drawingPoint1 = new DrawingPoint(p1, Color.BLACK,12);
    DrawingPoint drawingPoint2 = new DrawingPoint(p2, Color.BLACK,12);
    DrawingPath drawingPath = new DrawingPath();
    drawingPath.addPoint(drawingPoint1);
    drawingPath.addPoint(drawingPoint2);
    //act
    var rotatedDrawingPoint = MatematicMetod.rotateRoad(radians, drawingPath,mid);
    var x = rotatedDrawingPoint.getPoints().get(0).getX();
    var y = rotatedDrawingPoint.getPoints().get(0).getY();
    Point2D newPoint = new Point2D(0,100);
    //assert
    assertEquals(newPoint.getX(),x,1e-9);
    assertEquals(newPoint.getY(),y);
  }
  @Test
  public void shouldGetMidPoint()
  {
    //arrange
    Point2D p1 = new Point2D(0,0);
    Point2D p2 = new Point2D(200,200);
    //act
    var midPoint = MatematicMetod.getMidPoint(p1,p2);
    Point2D midPointTest = new Point2D(100,100);
    //assert
    assertEquals(midPoint.getX(),midPointTest.getX());
    assertEquals(midPoint.getY(),midPointTest.getY());
  }
}
