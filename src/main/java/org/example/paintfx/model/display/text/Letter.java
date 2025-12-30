package org.example.paintfx.model.display.text;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import lombok.Data;
import org.example.paintfx.model.MatematicMetod;
import org.example.paintfx.model.display.DrawingPoint;

@Data
public class Letter {
  private LetterRotation letterRotation;
  private DrawingPoint drawingPoint;
  private KeyEvent keyEvent;
  private Boolean editStatus;
  //rotate
  private Point2D rotationMidPoint;
  //private double angleRotation;
  //private Rectangle2D rectangleRotation;
  private Integer size;
  //location
  //private Point2D locationInCanvas;

  public Letter(KeyEvent keyEvent, DrawingPoint drawingPoint) {
    this.keyEvent = keyEvent;
    editStatus = false;
    //TODO 11
    this.drawingPoint = drawingPoint;
    //locationInCanvas = drawingPoint.getPoint2D();
    this.size = drawingPoint.getSizePoint();
    rotationMidPoint = new Point2D(0, 0);
    //angleRotation = 0;
    letterRotation = LetterRotation.NORMAL_0;
    //size = fontSize;

  }

  public String getLetterLocation() {
    return keyEvent.getText();
  }

  public Double getX() {
    return drawingPoint.getPoint2D().getX();
  }

  public double getY() {
    return drawingPoint.getPoint2D().getY();
  }

  public Point2D getDrawingPlace() {
    return drawingPoint.getPoint2D();
  }

  public void setDrawingPlace(Point2D point2D) {
    drawingPoint.setPoint2D(point2D);
  }

  public Color getColor() {
    return drawingPoint.getColor();
  }

  public Point2D getLocationAfterRotate(double newAngle, Point2D midPoint) {
    var newLocation = MatematicMetod.rotatePoint(newAngle, new Point2D(getX(), getY()), midPoint);
    //setLocationInCanvas(newLocation);
    return newLocation;
  }

  public void rotateByShift(int shift) {
    if (shift > 0) {
      letterRotation = letterRotation.next();
    } else {
      letterRotation = letterRotation.prev();
    }
  }


  public Letter snapshot() {
    //TODO 22
    DrawingPoint drawingPointCopy = new DrawingPoint(drawingPoint.getPoint2D(), getColor(),
        drawingPoint.getSizePoint());
    Letter letter = new Letter(keyEvent, drawingPointCopy);
    letter.setEditStatus(getEditStatus());
    letter.setLetterRotation(getLetterRotation());
    letter.setDrawingPlace(getDrawingPlace());
//    letter.setAngleRotation(angleRotation);
//    letter.setRectangleRotation(rectangleRotation);
//    letter.setLocationInCanvas(locationInCanvas);
    letter.setRotationMidPoint(rotationMidPoint);
    return letter;
  }
}
