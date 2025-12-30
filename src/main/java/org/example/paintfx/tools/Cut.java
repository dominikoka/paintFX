package org.example.paintfx.tools;

import javafx.geometry.Point2D;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.model.display.SelectionRectangle;

public class Cut implements Tool {

  SelectionRectangle selectionRectangle;

  @Override
  public void handleClick(Point2D point2D, DisplayController displayController) {
    if (!selectionRectangle.getSetFromSquare()) {
      var margin = -7;
      var squareStart = new Point2D(selectionRectangle.getX1(), selectionRectangle.getY1());
      var squareEnd = new Point2D(selectionRectangle.getX2(), selectionRectangle.getY2());

      displayController.updatePaintStatusInRectangle(new Point2D(squareStart.getX()-2,squareStart.getY()-2), new Point2D(squareEnd.getX()-7,squareEnd.getY()-7));
      displayController.updateTextStatusInRectangle(squareStart, squareEnd);
      displayController.cutImageWriteable(squareStart, squareEnd, point2D);

      selectionRectangle.setSetFromSquare(true);
    } else {
      displayController.setSelectionRectangle(null);
      displayController.saveHistory();
      //System.out.println(selectionRectangle.getLastCursorPosition()+" :last position");
      displayController.commitCutBitMap(new Point2D(selectionRectangle.getX1(), selectionRectangle.getY1()));
      displayController.resetStatus();
      selectionRectangle = null;
    }
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

  @Override
  public void handleMoved(Point2D point2D, DisplayController displayController) {
    selectionRectangle.moveRectangle(point2D.getX(), point2D.getY());
    var moveCordinates = selectionRectangle.getMoveFromLastPosition();
    displayController.moveCopyPaintElement(moveCordinates, false);
//    displayController.approvalMoveSelectedLetters(moveCordinates, false);
    displayController.previewMoveSelectedLetters(moveCordinates);
    displayController.previewMoveSelectedBitMap(moveCordinates);

  }
}
