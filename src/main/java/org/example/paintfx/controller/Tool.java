package org.example.paintfx.controller;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public interface Tool {
  default void handleClick(Point2D point2D, DisplayController displayController) {}
  default void handleDrag(Point2D point2D, DisplayController displayController) {}
  default void handleReleased(Point2D point2D, DisplayController displayController) {}
  default void handleMoved(Point2D point2D, DisplayController displayController) {}
  default void handlePressed(Point2D point2D, DisplayController displayController){}
  default void handleKey(KeyEvent keyEvent) {}


}
