package org.example.paintfx.model.display.draw;

import javafx.geometry.Point2D;
import lombok.Data;
import org.example.paintfx.model.StableVariables;
import org.example.paintfx.model.display.DrawingPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Data
public class DrawingPath {

  //int counterClick = 0;
  private Stack<DrawingPoint> drawingPath;
  private Boolean isFirstClick;

  public DrawingPath() {
    drawingPath = new Stack<>();
    isFirstClick = false;
  }

  public Stack<DrawingPoint> getCopyDrawingRoad() {
    Stack<DrawingPoint> roadPoint = new Stack<>();
    for (int i = 0; i < drawingPath.size(); i++) {
      var element = drawingPath.get(i);
      DrawingPoint dp = new DrawingPoint(element.getPoint2D(), element.getColor(), element.getSizePoint());
      roadPoint.push(dp);
    }
    return roadPoint;
  }

  public DrawingPath snapshot() {
    DrawingPath drawingPathCopy = new DrawingPath();
    for (var el : drawingPath) {
      DrawingPoint drawingPoint = new DrawingPoint(el.getPoint2D(), el.getColor(), el.getSizePoint());
      drawingPathCopy.addPoint(drawingPoint);
    }
    return drawingPathCopy;
  }

  public Stack<javafx.geometry.Point2D> getPoints() {
    Stack<javafx.geometry.Point2D> roadPoint = new Stack<>();
    for (int i = 0; i < drawingPath.size(); i++) {
      var point = drawingPath.get(i).getPoint2D();
      roadPoint.push(point);
    }
    return roadPoint;
  }

  public void addPoint(DrawingPoint drawingPoint) {
    // System.out.println("WYWOŁANIE ADD POINT PRZY KLIKNIECIU !!  !!  !!  !!  !!" + counterClick);
//    if ( !isFirstClick) {
//      System.out.println("WYWOŁANIE DODAWANIA EXTRA PUNKTOW");
//      addExtraPoint(drawingPoint, paintRoad.peek());
//    } else {
//
//    }
    drawingPath.push(drawingPoint);
    //counterClick++;
  }

  public void addExtraPoint(DrawingPoint current) {
    var x1 = drawingPath.peek().getPoint2D().getX();
    var y1 = drawingPath.peek().getPoint2D().getY();
    var x2 = current.getPoint2D().getX();
    var y2 = current.getPoint2D().getY();
    int counter = 1;
    double diffX = x2 - x1;
    double diffY = y2 - y1;
    double distance = Math.sqrt(diffX * diffX + diffY * diffY);
    while (counter < distance) {
      double percentageDiff = (double) counter / distance;
      Point2D point2D = new Point2D(x1 + diffX * percentageDiff, y1 + diffY * percentageDiff);
      DrawingPoint dp = new DrawingPoint(point2D, current.getColor(), current.getSizePoint());
      this.drawingPath.push(dp);
      counter++;
    }
  }

  public void removeLastPoint() {
    if (!drawingPath.isEmpty()) {
      drawingPath.pop();
    }
  }


  public void removePointAndUpdateDrawingPoint(Point2D drawingPoint) {
    var indexToRemove = getIndexToRemove(drawingPoint);
    var newDrawingPoints = getPointAfterRemove(indexToRemove);
    setDrawingPath(newDrawingPoints);
  }

  private Stack<DrawingPoint> getPointAfterRemove(List<Integer> indexToRemove) {
    var copyDrawingPoint = getCopyDrawingRoad();
    for (var index : indexToRemove) {
      copyDrawingPoint.removeElementAt(index);
    }
    return copyDrawingPoint;
  }

  private List<Integer> getIndexToRemove(Point2D drawingPoint) {
    List<Integer> removeIndex = new ArrayList<>();
    var paintRoadCopy = getCopyDrawingRoad();
    int counter = 0;
    for (var point : paintRoadCopy) {
      double sizePaintBufor = (double) StableVariables.SIZE_CURSOR / 2;
      var p1X = point.getPoint2D().getX() + sizePaintBufor > drawingPoint.getX();
      var p2X = point.getPoint2D().getX() - sizePaintBufor < drawingPoint.getX();
      var p1Y = point.getPoint2D().getY() + sizePaintBufor > drawingPoint.getY();
      var p2Y = point.getPoint2D().getY() - sizePaintBufor < drawingPoint.getY();
      if (p1Y && p1X && p2X && p2Y) {
        removeIndex.add(counter);
      }
      counter++;
    }
    return removeIndex;
  }

  public void setNewPaintRoad(DrawingPath newPaintRoad) {
    drawingPath = new Stack<>();
    drawingPath.addAll(newPaintRoad.getDrawingPath());
  }

  public void clear() {
    drawingPath = new Stack<>();
    isFirstClick = false;
  }
}
