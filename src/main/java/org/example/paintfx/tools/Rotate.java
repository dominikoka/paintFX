package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.model.MatematicMetod;
import org.example.paintfx.model.SelectedTool;
import org.example.paintfx.model.display.SelectionRectangle;
import org.example.paintfx.view.MainView;

public class Rotate implements Tool {
  Point2D pStart;
  Point2D pFinish;
  SelectionRectangle selectionRectangle;
  private Rectangle2D rectangleRotate = new Rectangle2D(0, 0, 0, 0);


  @Override
  public void handleClick(Point2D point2D, DisplayController displayController) {
    if (!selectionRectangle.getSetFromSquare()) {
      pStart = new Point2D(selectionRectangle.getX1(), selectionRectangle.getY1());

      pFinish = new Point2D(selectionRectangle.getX2(), selectionRectangle.getY2());

      var startImageWriteable = new Point2D(selectionRectangle.getX1()-2, selectionRectangle.getY1()+20);
      var finishImageWriteable = new Point2D(selectionRectangle.getX2()+5, selectionRectangle.getY2()+10);

      rectangleRotate = new Rectangle2D(pStart.getX(), pStart.getY(), pFinish.getX(), pFinish.getY());
      displayController.updatePaintStatusInRectangle(pStart, pFinish);
      displayController.updateTextStatusInRectangle(pStart, pFinish);

      displayController.cutImageWriteable(pStart, pFinish, point2D);

      selectionRectangle.setSetFromSquare(true);
    }
//    else {
//      //TODO po kliknieciu ok w rotate
//      System.out.println("end of rotate");
//      selectionRectangle = null;
//      displayController.setSelectionRectangle(null);
//      displayController.resetStatus();
//    }

  }

  @Override
  public void handleDrag(Point2D point2D, DisplayController displayController) {
    if (selectionRectangle == null) {
      selectionRectangle = new SelectionRectangle(point2D.getX(), point2D.getY());
    } else {
      selectionRectangle.setSecondPoint(point2D.getX(), point2D.getY());
    }
    displayController.setSelectionRectangle(selectionRectangle);
  }

  public void rotates(int angle, DisplayController displayController, MainView mainView) {



    Point2D midPoint = MatematicMetod.getMidPoint(pStart, pFinish);
    var rotatedPoint = MatematicMetod.rotateRoad(angle, displayController.getRoadWithStatus(), midPoint);

    displayController.changeRotatePaintPoint(rotatedPoint);
    displayController.changeRotateSelectedBitMap(angle,midPoint);

    //displayController.getWritableImageEditor().setCutElement(null);
    rotateText(displayController, angle, midPoint);
    displayController.saveByEditType();

    selectionRectangle.rotateRectangle();

  }

  private void rotateText(DisplayController displayController, int angle, Point2D midPoint) {
    for (var index : displayController.getIndexOfMoveText()) {
      displayController.rotateLetter(angle, index, midPoint, rectangleRotate);
    }
  }

  public void resetVariables(DisplayController displayController, SelectedTool selectedTool) {
    displayController.commitCutBitMap();
    selectionRectangle = null;
    displayController.setSelectionRectangle(null);
    displayController.resetStatus();

  }
}
