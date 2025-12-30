package org.example.paintfx.model.display;

import javafx.geometry.Point2D;
import org.example.paintfx.model.MatematicMetod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO dokonczyc
public class SelectionRectangleTest {
  double firstPositionMouseX;
  double firstPositionMouseY;
  double secondPositionMouseX;
  double secondPositionMouseY;
  SelectionRectangle selectionRectangle;

  @BeforeEach
  void init()
  {
    firstPositionMouseX = 152.0;
    firstPositionMouseY = 150.0;
    secondPositionMouseX = 153;
    secondPositionMouseY = 151;
    selectionRectangle = new SelectionRectangle(0.0, 0.0);
  }

  @Test
  public void shouldMoveRectangle() {
    //arrange
    selectionRectangle.setSecondPoint(50.0, 50.0);
    //first mouse rejestr
    selectionRectangle.moveRectangle(firstPositionMouseX, firstPositionMouseY);
    selectionRectangle.moveRectangle(secondPositionMouseX, secondPositionMouseY);
    //act
    //assert
    assertEquals(secondPositionMouseX - firstPositionMouseX, selectionRectangle.getMoveFromLastPosition().getX());
    assertEquals(secondPositionMouseY - firstPositionMouseY, selectionRectangle.getMoveFromLastPosition().getY());
  }

  @Test
  public void shouldSwapWidthAndHeightAfterRotation() {
    //arrange
    double x1 = 30.0;
    double y1 = 40.0;
    selectionRectangle = new SelectionRectangle(x1, y1);
    double x2 = 100.0;
    double y2 = 120.0;
    Point2D mid = MatematicMetod.getMidPoint(new Point2D(x1,y1),new Point2D(x2,y2));
    selectionRectangle.setSecondPoint(x2, y2);
    //act
    selectionRectangle.rotateRectangle();
    double x1AfterRotation = mid.getX()-(y2-y1)/2;
    double y1AfterRotation = mid.getY()-(x2-x1)/2;
    //assert
    assertEquals(selectionRectangle.getX1(),x1AfterRotation);
    assertEquals(selectionRectangle.getY1(),y1AfterRotation);
  }
}
