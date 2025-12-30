package org.example.paintfx.controller;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.Data;
import org.example.paintfx.model.SelectedTool;
import org.example.paintfx.view.MainView;

@Data
public class ToolController {
  Scene scene;
  MainView mainView;
  DisplayController displayController;
  SelectedTool activeTool;

  public ToolController(Scene scene, MainView MainView, DisplayController displayController) {
    this.scene = scene;
    this.mainView = MainView;
    this.displayController = displayController;
    activeTool = SelectedTool.PAINT;
  }


  public void getDragged(MouseEvent mouseEvent) {
    Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());
    activeTool.getTool().handleDrag(point2D, displayController);
    mainView.draw();

  }

  public void getClicked(MouseEvent mouseEvent) {
    Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());
    activeTool.getTool().handleClick(point2D, displayController);
    showRotateBtns();
    mainView.draw();
  }

  private void showRotateBtns() {
    if (activeTool == SelectedTool.ROTATE) {
      mainView.getRotateLeftBtn().setVisible(true);
      mainView.getRotateRightBtn().setVisible(true);
      mainView.getRotateOkBtn().setVisible(true);
      mainView.getRotateLeftBtn().setManaged(true);
      mainView.getRotateRightBtn().setManaged(true);
      mainView.getRotateOkBtn().setManaged(true);
    }
  }

  public void getReleased(MouseEvent mouseEvent) {
    Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());
    activeTool.getTool().handleReleased(point2D, displayController);
    mainView.draw();
  }

  public void getMoved(MouseEvent mouseEvent) {
    //var selectedTool = SelectedTool.CUT;
    //Point2D point2D = canvasCoordinates.getCoordinates(mouseEvent);
    Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());
    activeTool.getTool().handleMoved(point2D, displayController);
    mainView.draw();
  }

  public void onKey(KeyEvent key) {
    activeTool.getTool().handleKey(key);
    mainView.draw();
  }

  public void getPressed(MouseEvent mouseEvent) {
    Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());

    activeTool.getTool().handlePressed(point2D, displayController);
    mainView.draw();
  }
}
