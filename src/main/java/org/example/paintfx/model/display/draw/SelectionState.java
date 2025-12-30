package org.example.paintfx.model.display.draw;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Data
public class SelectionState {
  private Stack<Boolean> editPoints;
  private Boolean isSelectionStart;

  public SelectionState() {
    editPoints = new Stack<>();
    isSelectionStart = true;
  }

  public Stack<Boolean> getMoveElement() {
    return editPoints;
  }

  public void addMove(int pointCount) {
    while (editPoints.size() < pointCount) {
      editPoints.push(false);
    }
  }

  public void removeLastPoint() {
    if (!editPoints.isEmpty()) {
      editPoints.pop();
    }
  }

  public Boolean isEditing() {
    for (var el : editPoints) {
      if (el) {
        return true;
      }
    }
    return false;
  }


  public Integer getSizeMoveElement() {
    int counter = 0;
    for (var el : editPoints) {
      if (el) {
        counter++;
      }
    }
    return counter;
  }

  public List<Integer> getIndexesOfMove() {
    int counter = 0;
    List<Integer> indexes = new ArrayList<>();
    for (var onePointMove : editPoints) {
      if (onePointMove) {
        indexes.add(counter);
      }
      counter++;
    }
    return indexes;
  }

  public void setStatusForElement(int i, Boolean status) {
    editPoints.set(i, status);
  }
}
