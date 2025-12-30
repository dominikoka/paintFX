package org.example.paintfx.model.display.text;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import lombok.Data;
import org.example.paintfx.model.MatematicMetod;
import org.example.paintfx.model.StableVariables;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class TextEditor {
  GroupLetter groupLetter;

  Integer fontText;

  public TextEditor() {
    groupLetter = new GroupLetter();
    fontText = 12;
  }

//  public GroupText snapshot()
//  {
//    return groupText.snapshot();
//  }

  public List<Integer> getIndexOfMoveLetters() {
    int counter = 0;
    List<Integer> indexes = new ArrayList<>();
    for (var letter : groupLetter.getGroupText()) {
      if (letter.getEditStatus()) {
        indexes.add(counter);
      }
      counter++;
    }
    return indexes;
  }

  public void addLetter(Letter letter) {
    groupLetter.addElement(letter);
  }

  public void updateMoveStatusInRectangle(Point2D startPointRectangle, Point2D endPointRectangle) {
    var x1 = Math.min(startPointRectangle.getX(), endPointRectangle.getX());
    var x2 = Math.max(startPointRectangle.getX(), endPointRectangle.getX());
    var y1 = Math.min(startPointRectangle.getY(), endPointRectangle.getY());
    var y2 = Math.max(startPointRectangle.getY(), endPointRectangle.getY());
    for (int i = 0; i < groupLetter.getGroupText().size(); i++) {
      var letter = groupLetter.getGroupText().get(i);
      var degreeBeforeTransform = letter.getLetterRotation().getDegree();
      var cordinatesBeforeTransform = MatematicMetod.rotatePoint(degreeBeforeTransform, new Point2D(letter.getX(),
          letter.getY()), letter.getRotationMidPoint());
//      var xEl = groupLetter.getGroupText().get(i).getLocationInCanvas().getX();
//      var yEl = groupLetter.getGroupText().get(i).getLocationInCanvas().getY();
      var xEl = cordinatesBeforeTransform.getX();
      var yEl = cordinatesBeforeTransform.getY();
      Boolean checkX = xEl >= x1 && xEl <= x2;
      Boolean checkY = yEl >= y1 && yEl <= y2;
      if (checkX && checkY) {
        groupLetter.setStatus(i, true);
        //groupLetter.getGroupText().get(i).setRotationMidPoint(MatematicMetod.getMidPoint(startPointRectangle,endPointRectangle));
      }
    }
  }

  public void moveSelectedLetters(Point2D pxToMove) {
    for (var index : getIndexOfMoveLetters()) {
      Letter letter = groupLetter.getGroupText().get(index);
      Point2D nPoint2D = setNewCordinates(letter, pxToMove);
      letter.setDrawingPlace(nPoint2D);
      groupLetter.getGroupText().set(index, letter);
    }
  }

  private Point2D setNewCordinates(Letter letter, Point2D pxToMove) {
    var angle = letter.getLetterRotation().getDegree();
    if (angle == 0) {
      return new Point2D(letter.getX() + pxToMove.getX(), letter.getY() + pxToMove.getY());
    } else if (angle == 180) {
      return new Point2D(letter.getX() + pxToMove.getX() * -1, letter.getY() + pxToMove.getY() * -1);
    } else if (angle == 90) {
      return new Point2D(letter.getX() + pxToMove.getY(), letter.getY() - pxToMove.getX());
    } else {
      return new Point2D(letter.getX() - pxToMove.getY(), letter.getY() + pxToMove.getX());
    }
  }

//  public void saveHistory() {
//    var text = getGroupText();
//    //history.addGroupText(text);
//  }

  public double getActualRotateLetter(Integer index) {
    return getGroupLetter().getGroupText().get(index).getLetterRotation().getDegree();
  }

  public void setAngleForLetter(int angle, Integer letterIndex, Point2D midPoint, Rectangle2D rectangleRotate) {
    var letter = groupLetter.getGroupText().get(letterIndex);
    //letter.setAngleRotation(newAngle);
    Point2D oldMidPoint = letter.getRotationMidPoint();
    Point2D pointInCanvasNow = MatematicMetod.rotatePoint(letter.getLetterRotation().getDegree(),new Point2D(letter.getX(),letter.getY()),oldMidPoint);

    //letter.setDrawingPlace(pointInCanvasNow);
    letter.rotateByShift(angle);
    var newDegree = letter.getLetterRotation().getDegree();
    Point2D pointInCanvasAfter = MatematicMetod.rotatePoint(angle,pointInCanvasNow,midPoint);
    letter.setRotationMidPoint(midPoint);
    Point2D cordinatesXY = MatematicMetod.rotatePoint(-newDegree,pointInCanvasAfter,midPoint);
    letter.setDrawingPlace(cordinatesXY);
    //letter.setRectangleRotation(rectangleRotate);
    //letter.getLocationAfterRotate(newAngle, midPoint);

    //TODO moj enum
  }

  public void removePoint(Point2D point2D) {
    var indexToRemove = lettersToRemove(point2D);
    var indexToRemoveDesc = indexToRemove.stream().sorted(Comparator.reverseOrder()).toList();
    var newDrawingPoints = removeLettersAt(indexToRemoveDesc);
    setGroupLetter(newDrawingPoints);
    //setPaintRoad(newDrawingPoints);

  }

  private List<Integer> lettersToRemove(Point2D point) {
    List<Integer> removeIndex = new ArrayList<>();
    GroupLetter groupLetterCopy = groupLetter.snapshot();
    int counter = 0;
    for (var letter : groupLetterCopy.getGroupText()) {
      double sizePaintBufor = (double) StableVariables.SIZE_CURSOR / 2;

      var degreeBeforeTransform = letter.getLetterRotation().getDegree();
      var cordinatesBeforeTransform = MatematicMetod.rotatePoint(degreeBeforeTransform, new Point2D(letter.getX(),
          letter.getY()), letter.getRotationMidPoint());
      var p1X = cordinatesBeforeTransform.getX() + sizePaintBufor > point.getX();
      var p2X = cordinatesBeforeTransform.getX() - sizePaintBufor < point.getX() + 5;
      var p1Y = cordinatesBeforeTransform.getY() + sizePaintBufor > point.getY();
      var p2Y = cordinatesBeforeTransform.getY() - sizePaintBufor < point.getY() + 5;
      if (p1Y && p1X && p2X && p2Y) {
        removeIndex.add(counter);
      }
      counter++;
    }
    return removeIndex;
  }

  private GroupLetter removeLettersAt(List<Integer> indexToRemove) {
    var copyGroupText = groupLetter.snapshot();
    //System.out.println("poczatkowy size " + copyDrawingPoint.size());
    for (var index : indexToRemove) {
      copyGroupText.getGroupText().removeElementAt(index);
    }
    return copyGroupText;
  }

  public GroupLetter snapshot() {
    return groupLetter.snapshot();
  }

  public boolean isEdit() {
    return groupLetter.isEdit();
  }

  public void resetStatus() {
    for (int i = 0; i < groupLetter.getGroupText().size(); i++) {
      groupLetter.setStatus(i, false);
    }
  }

  public void clear() {
    groupLetter = new GroupLetter();
    fontText = 12;
  }
}
