package org.example.paintfx.model.display.draw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectionStateTest {
  @Test
  public void shouldAdd10StatusAndSetStatusForDrawingPathActive() {
    //arrange
    SelectionState selectionState = new SelectionState();
    //act
    selectionState.addMove(10);
    var counter = 0;
    for (var element : selectionState.getMoveElement()) {
      selectionState.setStatusForElement(counter, true);
      counter++;
    }
    //assert
    for (var element : selectionState.getMoveElement()) {
      assertTrue(element);
    }
  }

  @Test
  public void shouldAdd10StatusAndSetLastDrawingPathStatusActive() {
    //arrange
    SelectionState selectionState = new SelectionState();
    //act
    selectionState.addMove(10);
    selectionState.setStatusForElement(9, true);
    var counter = 0;
    counter = 0;
    //assert
    for (var element : selectionState.getMoveElement()) {
      if (counter == 9) {
        assertTrue(element);
      } else {
        assertFalse(element);
      }
      counter++;
    }
  }
}
