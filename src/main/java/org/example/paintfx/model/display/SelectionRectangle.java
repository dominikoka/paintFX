package org.example.paintfx.model.display;

import javafx.geometry.Point2D;
import lombok.Getter;
import lombok.Setter;


@Getter
public class SelectionRectangle {
  private Double x1;
  private Double y1;
  private Double x2;
  private Double y2;

  private Double x1Copy;
  private Double y1Copy;
  private Double x2Copy;
  private Double y2Copy;

  private Point2D startPointToMove;
  private Point2D lastCursorPosition;
  private Point2D moveFromLastPosition;


  private Boolean isActive = false;
  @Setter
  private Boolean setFromSquare = false;

  private Boolean isFirstPoint = false;
  private Boolean isSecPoint = false;

  public SelectionRectangle(Double x1Input, Double y1Input) {
    this.x1 = x1Input;
    this.y1 = y1Input;
    isFirstPoint = true;
    moveFromLastPosition = new Point2D(0, 0);
  }

  private void updateDragOffset(Double currentX, Double currentY) {
    if (lastCursorPosition == null) {
      lastCursorPosition = new Point2D(currentX, currentY);
    }
    double moveX = currentX - lastCursorPosition.getX();
    double moveY = currentY - lastCursorPosition.getY();
    lastCursorPosition = new Point2D(currentX, currentY);
    moveFromLastPosition = new Point2D(moveX, moveY);
  }


  public void setSecondPoint(Double x2Input, Double y2Input) {
    x2 = x2Input;
    y2 = y2Input;
    setDrawingMoveRectangle(getX1(), getY1(), getX2(), getY2());
    if (Boolean.FALSE.equals(isSecPoint)) {
      isSecPoint = true;
    }
  }

  public void rotateRectangle() {
    double middleX = (x2 + x1) / 2;
    double middleY = (y2 + y1) / 2;
    double newWidthHalf = (y2 - y1) / 2;
    double newHeightHalf = (x2 - x1) / 2;
    x1 = middleX + newWidthHalf;
    x2 = middleX - newWidthHalf;
    y1 = middleY + newHeightHalf;
    y2 = middleY - newHeightHalf;
  }

  private void setDrawingMoveRectangle(Double x1, Double y1, Double x2, Double y2) {
    x1Copy = getX1();
    x2Copy = getX2();
    y1Copy = getY1();
    y2Copy = getY2();
    isActive = true;
  }

  public Double getWidth() {
    return getX2() - getX1();
  }


  public Double getHeight() {
    return getY2() - getY1();
  }

  public void moveRectangle(Double width, Double height) {
    double moveWidth = 0;
    double moveHeight = 0;
    if (startPointToMove == null) {
      startPointToMove = new Point2D(width, height);
      //lastStep = startPointToMove;
    } else {
      moveWidth = width - startPointToMove.getX();
      moveHeight = height - startPointToMove.getY();
    }
    x1 = x1Copy + moveWidth;
    x2 = x2Copy + moveWidth;
    y1 = y1Copy + moveHeight;
    y2 = y2Copy + moveHeight;
    //setDrawingMoveSquare(getX1(), getY1(), getX2(), getY2());
    updateDragOffset(width, height);
  }

  public Double getX1() {
    return Math.min(x1, x2);
  }

  public Double getY1() {
    return Math.min(y1, y2);
  }

  public Double getX2() {
    return Math.max(x1, x2);
  }

  public Double getY2() {
    return Math.max(y1, y2);
  }


  public void resetStatus() {
    isActive = false;
    setFromSquare = false;
    isFirstPoint = false;
    isSecPoint = false;
  }
}
