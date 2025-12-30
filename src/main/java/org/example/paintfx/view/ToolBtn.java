package org.example.paintfx.view;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import lombok.Data;
import org.example.paintfx.controller.ToolController;
import org.example.paintfx.model.SaveCanvas;
import org.example.paintfx.model.SelectedTool;

@Data
public class ToolBtn {
  public Button cuttingBtn;
  public Button saveBtn;
  public Button backgroundColor;
  public Button pencilBtn;
  public Button drawRectangleBtn;
  public Button drawOvalBtn;
  public Button drawLineBtn;
  public Button rubberBtn;
  public Button backBtn;
  public Button rotateBtn;
  public Button writeBtn;
  public Button resolutionCanvas;
  public Button paintElementBtn;

  private MainView mainView;
  private ToolController toolController;

  public ToolBtn() {

  }

  public void initialize()
  {
    cuttingBtn.setOnAction(e -> getCut());
    saveBtn.setOnAction(e -> save());
    backgroundColor.setOnAction(e -> changeBackgroundColor());
    pencilBtn.setOnAction(e -> pencil());
    drawRectangleBtn.setOnAction(e -> getRectangle());
    drawOvalBtn.setOnAction(e -> drawOval());
    drawLineBtn.setOnAction(e -> drawLine());
    rubberBtn.setOnAction(e -> rubber());
    backBtn.setOnAction(e -> undo());
    rotateBtn.setOnAction(e -> rotatee());
    writeBtn.setOnAction(e -> writee());
    resolutionCanvas.setOnAction(e -> changeResolution());
    paintElementBtn.setOnAction(e -> fillBucket());
  }

  private void fillBucket() {
    toolController.setActiveTool(SelectedTool.PAINTELEMENT);
    mainView.getCanvas().setCursor(Cursor.TEXT);
    mainView.getActualToolBtn().setText("PAINT EL");
    mainView.showWriteFontBtn();
    mainView.hideDrawBtn();
  }

  private void changeResolution() {
    mainView.getActualToolBtn().setText("RESOLUTION");
    mainView.hideBtn();
    mainView.showResolutionBtn();
  }

  private void writee() {
    toolController.setActiveTool(SelectedTool.WRITE);
    mainView.getCanvas().setCursor(Cursor.TEXT);
    mainView.getActualToolBtn().setText("WRITE");
    mainView.showWriteFontBtn();
    mainView.hideDrawBtn();
  }

  private void rotatee() {
    toolController.setActiveTool(SelectedTool.ROTATE);
    //refreshCanvas(0, 0);
    mainView.getActualToolBtn().setText("ROTATE");
    mainView.hideWriteFontBtn();
    mainView.hideDrawBtn();
  }

  private void undo() {
    mainView.gc.clearRect(0, 0, mainView.getCanvas().getHeight(), mainView.getCanvas().getWidth());
    mainView.getDisplayController().undo();
    //drawMoveSquare(displayController);
    mainView.clearCanvas();
    mainView.draw();
    mainView.getActualToolBtn().setText("BACK");
    mainView.hideDrawBtn();
  }

  private void rubber() {
    toolController.setActiveTool(SelectedTool.RUBBER);
    mainView.getActualToolBtn().setText("RUBBER");
    mainView.hideWriteFontBtn();
    mainView.hideDrawBtn();
  }

  private void drawLine() {
    toolController.setActiveTool(SelectedTool.LINE);
    mainView.getActualToolBtn().setText("LINE");
    mainView.hideBtn();
    mainView.showDrawBtn();
  }

  private void drawOval() {
    toolController.setActiveTool(SelectedTool.OVAL);
    mainView.getActualToolBtn().setText("OVAL");
    mainView.hideBtn();
    mainView.showDrawBtn();
  }


  public void getRectangle() {
    toolController.setActiveTool(SelectedTool.RECTANGLE);
    mainView.getActualToolBtn().setText("RECTANGLE");
    mainView.hideBtn();
    mainView.showDrawBtn();
  }
  @FXML
  protected void pencil() {
    toolController.setActiveTool(SelectedTool.PAINT);
    mainView.getCanvas().setCursor(Cursor.CROSSHAIR);
    mainView.getActualToolBtn().setText("PENCIL");
    mainView.hideBtn();
    mainView.showDrawBtn();
  }
  private void changeBackgroundColor() {
    toolController.setActiveTool(SelectedTool.BACKGROUNDER);
      mainView.changeBackground(mainView.getActualColor());
  }


  public void getCut() {
    toolController.setActiveTool(SelectedTool.CUT);
    //refreshCanvas(0, 0);
    mainView.getActualToolBtn().setText("CUT");
    mainView.hideWriteFontBtn();
    mainView.hideDrawBtn();
//    mainView.getCut();
  }

  public void save() {
    SaveCanvas.save(mainView.getCanvas());
  }
}
