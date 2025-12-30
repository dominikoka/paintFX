package org.example.paintfx.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.paintfx.PaintFX;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.ToolController;
import org.example.paintfx.model.SelectedTool;
import org.example.paintfx.model.display.figure.Figure;
import org.example.paintfx.model.display.figure.FigureBasedLine;
import org.example.paintfx.model.display.figure.FigureBasedRectangle;
import org.example.paintfx.model.display.figure.FigureType;
import org.example.paintfx.model.display.text.GroupLetter;
import org.example.paintfx.tools.Rotate;

@Data
public class MainView {
  public Button sizeText;
  public TextField sizeTextField;
  public Button sizeDraw;
  public TextField sizeDrawField;
  @FXML
  public Label label_cordinates;
  public Button rotateOkBtn;
  public StackPane board;
  public Button saveBtn;
  public Button backgroundColor;
  public Button widthCanvas;
  public TextField widthCanvasField;
  public Button heightCanvas;
  public TextField heightCanvasField;
  public Button drawRectangleBtn;
  public Button colorGreenBtn;
  @FXML
  public FlowPane colors;
  @FXML
  public FlowPane tools;
  @FXML
  ColorBtn colorsController;
  @FXML
  ToolBtn toolsController;
  ToolController toolController;

  @Setter
  DisplayController displayController;

  double hSquare;
  double wSquare;
  //  int HCanvas = StableVariables.H - StableVariables.PADDING_TOP_PANEL;
//  int WCanvas = StableVariables.W;
  int HCanvas = 650;
  int WCanvas = 1200;
  @Getter
  GraphicsContext gc;
  Color actualColor;

  private Integer offsetX = 0;
  @Setter
  private PaintFX paintFX;
  @Getter
  @FXML
  private Canvas canvas;
  @FXML
  private Button cuttingBtn, pencilBtn, rubberBtn, backBtn, actualColorBtn, actualToolBtn, rotateLeftBtn,
      rotateRightBtn;

  public void setToolControllerAnd111(ToolController toolController) {
    this.toolController = toolController;
    toolsController.setToolController(toolController);
  }

  @FXML
  private void setRotateLeftBtn() {
    rotate(-90);
    draw();
  }

  @FXML
  private void setRotateRightBtn() {
    rotate(90);
    draw();
  }

  void changeBackground(Color color) {
    if (toolController.getActiveTool().equals(SelectedTool.BACKGROUNDER)) {
      displayController.setBackgroundColor(color);
    }
    draw();
  }

  void setActualColorAndIdButton(Color color, String idBtn) {
    actualColor = color;
    gc.setFill(actualColor);
    actualColorBtn.setId(idBtn);
    displayController.setColor(color);
  }

  @FXML
  public void initialize() {
    hSquare = 0;
    wSquare = 0;
    gc = canvas.getGraphicsContext2D();
//    if (selectedTool == null) {
//      getPencil();
//    }
    gc.setFill(Color.BLACK);

    canvas.setCursor(Cursor.CROSSHAIR);
    canvas.setWidth(WCanvas - 5);
    canvas.setHeight(HCanvas);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(canvas);
    scrollPane.setPannable(false);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (e.getCode() == KeyCode.SPACE) {
        e.consume();
      }
    });
    board.getChildren().setAll(scrollPane);
    actualColor = Color.BLACK;

    hideBtn();
    showDrawBtn();
    heightCanvasField.setText(String.valueOf(HCanvas));
    widthCanvasField.setText(String.valueOf(WCanvas));
    colorsController.setMainView(this);
    toolsController.setMainView(this);
  }

  void hideBtn() {
    hideRotateBtn();
    hideWriteFontBtn();
    hideDrawBtn();
    hideResolutionBtn();
  }

  private void hideResolutionBtn() {
    widthCanvas.setVisible(false);
    widthCanvas.setManaged(false);
    heightCanvas.setVisible(false);
    heightCanvas.setManaged(false);
    widthCanvasField.setVisible(false);
    widthCanvasField.setManaged(false);
    heightCanvasField.setVisible(false);
    heightCanvasField.setManaged(false);
  }

  private void hideRotateBtn() {
    rotateLeftBtn.setVisible(false);
    rotateLeftBtn.setManaged(false);
    rotateOkBtn.setVisible(false);
    rotateOkBtn.setManaged(false);
    rotateRightBtn.setVisible(false);
    rotateRightBtn.setManaged(false);

  }

  void hideWriteFontBtn() {
    sizeText.setVisible(false);
    sizeText.setManaged(false);
    sizeTextField.setVisible(false);
    sizeTextField.setManaged(false);
  }

  void showWriteFontBtn() {
    sizeText.setVisible(true);
    sizeText.setManaged(true);
    sizeTextField.setVisible(true);
    sizeTextField.setManaged(true);
  }

  void showDrawBtn() {
    sizeDraw.setVisible(true);
    sizeDraw.setManaged(true);
    sizeDrawField.setVisible(true);
    sizeDrawField.setManaged(true);
  }

  public void hideDrawBtn() {
    sizeDraw.setVisible(false);
    sizeDraw.setManaged(false);
    sizeDrawField.setVisible(false);
    sizeDrawField.setManaged(false);
  }

  public void useDraw(Point2D p, Integer sizeCursor) {
    gc.fillOval(p.getX(), p.getY(), sizeCursor, sizeCursor);
  }


  public void clearCanvas() {
    gc.clearRect(0, 0, 1200, 1200);
  }


  public void rotate(Integer angle) {
    var selectedTool = toolController.getActiveTool();
    if (selectedTool == SelectedTool.ROTATE) {
      ((Rotate) selectedTool.getTool()).rotates(angle, displayController, this);
    }
  }

  public void setRotateBtnOk(ActionEvent actionEvent) {
    var selectedTool = toolController.getActiveTool();
    if (selectedTool == SelectedTool.ROTATE) {
      ((Rotate) selectedTool.getTool()).resetVariables(displayController, selectedTool);
      hideRotateBtn();
      draw();
    }
  }

  public void draw() {
    clearCanvas();

    drawBackground();
    fillBucket();
    //gc.setFill(actualCollor);
    drawPencil();
    drawWriting();
    //drawFigures();
    drawPreviewFigure();
    drawSelectionRectangle();
    //sandboxDraw();
  }

  private void fillBucket() {
    if (displayController.getLayerImage() != null) {
      gc.drawImage(displayController.getLayerImage(), 0, 0);
    }
     //preview cut
    if (displayController.getWritableImageEditor().getCutElement() != null) {
      gc.drawImage(displayController.getWritableImageEditor().getCutElement(),
          displayController.getWritableImageEditor().getMoveMouse().getX(),
          displayController.getWritableImageEditor().getMoveMouse().getY());
    }
  }

  private void drawPreviewFigure() {
    if (displayController.getFigureEditor().getFigure() != null) {
      previewFigure();
    }
  }

  private void drawFigures() {
    var figures = displayController.getFigureEditor().getFigures();
    for (var figure : figures) {
      gc.setLineWidth(figure.getLineSize());
      drawFigure(figure);
    }
    if (displayController.getFigureEditor().getFigure() != null) {
      previewFigure();
    }
  }

  private void previewFigure() {
    if (displayController.getFigureEditor().getFigure() != null) {
      var figure = displayController.getFigureEditor().getFigure();
      gc.setLineWidth(figure.getLineSize() + 2);
      DrawSingleFigure(figure);
    }
  }

  private void drawFigure(Figure figure) {
    DrawSingleFigure(figure);
  }

  private void DrawSingleFigure(Figure figure) {
    gc.setStroke(figure.getColor());
    if (figure.getFigureType().equals(FigureType.RECTANGLE)) {
      previewRectangle((FigureBasedRectangle) figure);
    }
    if (figure.getFigureType().equals(FigureType.OVAL)) {
      previewOval((FigureBasedRectangle) figure);
    }
    if (figure.getFigureType().equals(FigureType.LINE)) {
      FigureBasedLine figure2D = (FigureBasedLine) figure;
      gc.strokeLine(figure2D.getX1(), figure2D.getY1(), figure2D.getX2(), figure2D.getY2());
    }
  }

  private void previewRectangle(FigureBasedRectangle figure) {
    var rectangle = figure.getFigure();
    gc.strokeRect(rectangle.getMinX(), rectangle.getMinY(), rectangle.getWidth(), rectangle.getHeight());
  }

  private void previewOval(FigureBasedRectangle figure) {
    var oval = figure.getFigure();
    gc.strokeOval(oval.getMinX(), oval.getMinY(), oval.getWidth(), oval.getHeight());
  }

  private void sandboxDraw() {
    gc.setFill(Color.BLUE);
    Rectangle2D rectangle2D = new Rectangle2D(50, 150, 100, 100);
    gc.strokeRect(rectangle2D.getMinX(), rectangle2D.getMinY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    gc.strokeLine(50, 50, 100, 100);
  }

  private void drawBackground() {
    var color = displayController.getBackgroundColor();
    gc.setFill(color);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  private void drawSelectionRectangle() {
    //TODO poprawic
    gc.setFill(Color.RED);
    if (displayController.getSelectionRectangle() != null && displayController.getSelectionRectangle().getIsActive()) {
      // System.out.println("RYSOWANIE RAMKI");
      var square = displayController.getSelectionRectangle();
      //displayController.getDrawingMoveSquare().getDataString();
      gc.setFill(Color.RED);
      gc.setLineWidth(4);
      gc.strokeRect(square.getX1(),
          square.getY1(), square.getWidth(), square.getHeight());
    }
  }

  public void drawPencil() {
    //System.out.println("wymiary canvas"+ canvas.getWidth()+" "+ canvas.getHeight());
    var paintRoadDisplay = displayController.getDrawingPath().getDrawingPath();
    for (int i = 0; i < paintRoadDisplay.size(); i++) {
      var singlePoint = paintRoadDisplay.get(i);
      var color = singlePoint.getColor();
      var sizeCursor = singlePoint.getSizePoint();
      gc.setFill(color);
      gc.rotate(0);
      var point = new Point2D(singlePoint.getPoint2D().getX(),
          singlePoint.getPoint2D().getY());

      useDraw(point, sizeCursor);
    }
  }

  public void drawWriting() {
    GroupLetter groupLetter = displayController.getTextEditor().getGroupLetter();
    var listIndexToRotate = displayController.getIndexOfMoveText();
    var counter = 0;
    for (var letter : groupLetter.getGroupText()) {
      gc.setFill(letter.getColor());
//      if (true) {
      gc.save();
      gc.translate(letter.getRotationMidPoint().getX(), letter.getRotationMidPoint().getY());
      gc.rotate(letter.getLetterRotation().getDegree());
      //System.out.println("angle - "+ letter.getAngleRotation());
      gc.translate(-letter.getRotationMidPoint().getX(), -letter.getRotationMidPoint().getY());
      gc.setFont(new Font(letter.getSize()));
      gc.fillText(letter.getLetterLocation(), letter.getX(), letter.getY());
      gc.restore();
    }
  }

  public void setSizeText(ActionEvent actionEvent) {
    displayController.setFontLetter(Integer.valueOf(sizeTextField.getText()));
  }

  public void setSizeDrawText(ActionEvent actionEvent) {
    displayController.setSizeDrawCursor(Integer.valueOf(sizeDrawField.getText()));
  }

  void showResolutionBtn() {
    widthCanvas.setVisible(true);
    widthCanvas.setManaged(true);
    heightCanvas.setVisible(true);
    heightCanvas.setManaged(true);
    widthCanvasField.setVisible(true);
    widthCanvasField.setManaged(true);
    heightCanvasField.setVisible(true);
    heightCanvasField.setManaged(true);
  }

  public void setWidthCanvas(ActionEvent actionEvent) {
    refreshBoardStyle();
  }

  private void refreshBoardStyle() {
    WCanvas = Integer.parseInt(widthCanvasField.getText());
    HCanvas = Integer.parseInt(heightCanvasField.getText());
    board.setMaxWidth(WCanvas+5);
    board.setMaxHeight(HCanvas+5);
    board.setPrefWidth(WCanvas+5);
    board.setPrefHeight(HCanvas+5);

    canvas.setWidth(WCanvas - 5);
    canvas.setHeight(HCanvas);


    board.requestLayout();
    gc.setFill(Color.WHITE);
    gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    displayController.clear();
  }

  public void setHeightCanvas(ActionEvent actionEvent) {
    refreshBoardStyle();
  }
}