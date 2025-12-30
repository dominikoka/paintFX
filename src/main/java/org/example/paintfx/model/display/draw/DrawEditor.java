package org.example.paintfx.model.display.draw;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import lombok.Data;
import org.example.paintfx.model.MatematicMetod;
import org.example.paintfx.model.display.DrawingPoint;
import org.example.paintfx.model.display.figure.Figure;
import org.example.paintfx.model.display.figure.FigureBasedLine;
import org.example.paintfx.model.display.figure.FigureBasedRectangle;
import org.example.paintfx.model.display.figure.FigureType;

import java.util.List;

@Data
public class DrawEditor {
  private SelectionState selectionState;
  private DrawingPath drawingPath;
  private Color backgroundColor;
  private Integer sizeCursor;


  //  public DrawEditor(StateRoad stateRoad, DrawRoad drawRoad, History history) {
//    this.drawRoad = drawRoad;
//    this.stateRoad = stateRoad;
//    backgroundColor = Color.WHITE;
//    sizeCursor = 5;
//    System.out.println("nowy obiekt");
//  }
  public DrawEditor() {
    drawingPath = new DrawingPath();
    selectionState = new SelectionState();
    backgroundColor = Color.WHITE;
    sizeCursor = 5;
  }

  public void setFirstClick(Boolean isFirstClick) {
    selectionState.setIsSelectionStart(isFirstClick);
  }

  public void resetDrawingStatus() {
    for (int i = 0; i < selectionState.getMoveElement().size(); i++) {
      selectionState.getMoveElement().set(i, false);
    }
  }

  public void addPoint(Point2D point2D, Color color) {
    DrawingPoint dp = new DrawingPoint(point2D, color, getSizeCursor());
    if (!selectionState.getIsSelectionStart()) {
      drawingPath.addExtraPoint(dp);
    } else {
      drawingPath.getDrawingPath().push(dp);
    }
    selectionState.addMove(drawingPath.getDrawingPath().size());
  }

  private DrawingPath getCopyRoad(DrawingPath drawingPath) {
    DrawingPath copyRoad = new DrawingPath();
    DrawingPoint drawingPoint;
    for (var r : drawingPath.getCopyDrawingRoad()) {
      drawingPoint = new DrawingPoint(r.getPoint2D(), r.getColor(), r.getSizePoint());
      copyRoad.addPoint(drawingPoint);
    }
    return copyRoad;
  }

  public boolean isEdit() {
    return selectionState.isEditing();
  }

  public DrawingPath getRoadWithStatus() {
    DrawingPath drawingPath1 = new DrawingPath();
    for (int i = 0; i < selectionState.getMoveElement().size(); i++) {
      var moveEl = selectionState.getMoveElement().get(i);
      if (moveEl) {
        DrawingPoint drawingPoint = drawingPath.getCopyDrawingRoad().get(i);
        drawingPath1.addPoint(drawingPoint);
      }
    }
    return drawingPath1;
  }

  public void updatePaintStatusInRectangle(Point2D fPoint, Point2D sPoint) {
    var x1 = Math.min(fPoint.getX(), sPoint.getX());
    var x2 = Math.max(fPoint.getX(), sPoint.getX());
    var y1 = Math.min(fPoint.getY(), sPoint.getY());
    var y2 = Math.max(fPoint.getY(), sPoint.getY());
    for (int i = 0; i < drawingPath.getCopyDrawingRoad().size(); i++) {
      var xEl = drawingPath.getCopyDrawingRoad().get(i).getPoint2D().getX();
      var yEl = drawingPath.getCopyDrawingRoad().get(i).getPoint2D().getY();
      Boolean checkX = xEl >= x1 && xEl <= x2;
      Boolean checkY = yEl >= y1 && yEl <= y2;
      if (checkX && checkY) {
        //System.out.println("PaintRoadController - update status ");
        selectionState.setStatusForElement(i, true);
      }
    }
  }

  public void moveCopyPaintElement(Point2D howManyMove) {
    for (var indexToMove : selectionState.getIndexesOfMove()) {
      var newX = drawingPath.getPoints().get(indexToMove).getX() + howManyMove.getX();
      var newY = drawingPath.getPoints().get(indexToMove).getY() + howManyMove.getY();
      Point2D nPoint2D = new Point2D(newX, newY);
      DrawingPoint dp = new DrawingPoint(nPoint2D, drawingPath.getCopyDrawingRoad().get(indexToMove).getColor(),
          drawingPath.getCopyDrawingRoad().get(indexToMove).getSizePoint());
      drawingPath.getDrawingPath().set(indexToMove, dp);
    }
  }

//  public void saveHistoryPaint(boolean status) {
//    var road = getCopyRoad(drawRoad);
//    if (status) {
//      history.addDrawRoad(road);
//    }
//  }

  public void resetStatus() {
    for (int i = 0; i < selectionState.getMoveElement().size(); i++) {
      selectionState.getMoveElement().set(i, false);
    }
  }

  public void changeRotatePaintPoint(DrawingPath rotatedPoint) {
    var sizeMarkedPoint = selectionState.getSizeMoveElement();
    if (sizeMarkedPoint == rotatedPoint.getPoints().size()) {
      changeMarkedPoint(rotatedPoint);
    }
  }

  private void changeMarkedPoint(DrawingPath rotatedPoint) {
    int counterRotatedPoint = 0;
    for (int i = 0; i < drawingPath.getCopyDrawingRoad().size(); i++) {
      if (selectionState.getMoveElement().get(i)) {
        var newP = rotatedPoint.getDrawingPath().get(counterRotatedPoint);
        drawingPath.getDrawingPath().set(i, newP);
        counterRotatedPoint++;
      }
    }
  }

  public void removePoint(Point2D point2D) {
    drawingPath.removePointAndUpdateDrawingPoint(point2D);
  }

  public DrawingPath snapshot() {
    return drawingPath.snapshot();
  }

  public void addFigureAsPoints(Figure figure) {
    if (figure.getFigureType() == FigureType.LINE) {
      FigureBasedLine figureBasedLine = (FigureBasedLine) figure;
      addLineAsPoints(new Point2D(figureBasedLine.getX1(), figureBasedLine.getY1()),
          new Point2D(figureBasedLine.getX2(), figureBasedLine.getY2()), figureBasedLine.getLineSize(),
          figureBasedLine.getColor());
    } else if (figure.getFigureType() == FigureType.RECTANGLE) {
      FigureBasedRectangle rectangle = (FigureBasedRectangle) figure;
      var rectangleFigure = rectangle.getFigure();
      addRectangleAsPoints(rectangleFigure, figure.getColor(), figure.getLineSize());
    } else if (figure.getFigureType() == FigureType.OVAL) {
      FigureBasedRectangle rectangle = (FigureBasedRectangle) figure;
      var rectangleFigure = rectangle.getFigure();
      addOvalAsPoints(rectangleFigure, figure.getColor(), figure.getLineSize());
    }
    selectionState.addMove(drawingPath.getDrawingPath().size());
  }

  private void addOvalAsPoints(Rectangle2D rectangleFigure, Color color, int lineSize) {
    var points = MatematicMetod.getPointsFromElipse(rectangleFigure,color,lineSize);
    pushToDrawingPath(points);
  }

  private void addRectangleAsPoints(Rectangle2D rectangleFigure, Color color, int lineSize) {
    var p1 = new Point2D(rectangleFigure.getMinX(), rectangleFigure.getMinY());
    var p2 = new Point2D(rectangleFigure.getMinX() + rectangleFigure.getWidth(), rectangleFigure.getMinY());
    var p3 = new Point2D(rectangleFigure.getMinX(), rectangleFigure.getMinY() + rectangleFigure.getHeight());
    var p4 = new Point2D(rectangleFigure.getMinX() + rectangleFigure.getWidth(),
        rectangleFigure.getMinY() + rectangleFigure.getHeight());
    addLineAsPoints(p1, p2, lineSize, color);
    addLineAsPoints(p2, p4, lineSize, color);
    addLineAsPoints(p4, p3, lineSize, color);
    addLineAsPoints(p3, p1, lineSize, color);
  }


  private void addLineAsPoints(Point2D p1, Point2D p2, int lineSize, Color color) {
    List<DrawingPoint> linePoints = MatematicMetod.getPointsFromLine(new Point2D(p1.getX(),
            p1.getY()), new Point2D(p2.getX(), p2.getY()),
        lineSize, color);

    pushToDrawingPath(linePoints);
  }

  private void pushToDrawingPath(List<DrawingPoint> linePoints) {
    for (var point : linePoints) {
      drawingPath.getDrawingPath().push(point);
    }
  }
}
