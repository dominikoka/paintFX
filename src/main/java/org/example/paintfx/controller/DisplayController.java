package org.example.paintfx.controller;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import lombok.Data;
import org.example.paintfx.model.CanvasSize;
import org.example.paintfx.model.display.SelectionRectangle;
import org.example.paintfx.model.display.draw.DrawEditor;
//import org.example.paintfx.model.display.draw.DrawElementBucket;
import org.example.paintfx.model.display.draw.DrawingPath;
import org.example.paintfx.model.display.draw.SelectionState;
import org.example.paintfx.model.display.figure.FigureEditor;
import org.example.paintfx.model.display.figure.FigureType;
import org.example.paintfx.model.display.text.Letter;
import org.example.paintfx.model.display.text.TextEditor;
import org.example.paintfx.model.display.writableimage.WritableImageEditor;
import org.example.paintfx.model.history.History;

import java.util.List;

@Data
public class DisplayController {
  History history;
  SelectionRectangle selectionRectangle;
  Color color = Color.BLACK;
  private Integer padding = 30;
  private DrawingPath drawingPath;
  private SelectionState selectionState;
  private Letter letter;
  private DrawEditor drawEditor;
  private TextEditor textEditor;
//  private DrawElementBucket drawElementBucket;
  private CanvasSize resolutionCanvas;
  private Canvas canvas;
  private FigureEditor figureEditor;

  private WritableImage layerImage;
  private WritableImageEditor writableImageEditor;

  public DisplayController(DrawEditor drawEditor, TextEditor textEditor, History history,
                           SelectionRectangle selectionRectangle, Canvas canvas) {
    this.drawEditor = drawEditor;
    this.textEditor = textEditor;
    //this.drawElementBucket = new DrawElementBucket();
    this.canvas = canvas;
    this.figureEditor = new FigureEditor();

    this.selectionState = drawEditor.getSelectionState();
    selectionState.addMove(drawEditor.getDrawingPath().getDrawingPath().size());
    this.drawingPath = drawEditor.getDrawingPath();
    this.history = history;
    this.selectionRectangle = selectionRectangle;
    updateTextStatusInRectangle(new Point2D(selectionRectangle.getX1(), selectionRectangle.getY1()),
        new Point2D(selectionRectangle.getX2(), selectionRectangle.getY2()));
    updatePaintStatusInRectangle(new Point2D(selectionRectangle.getX1(), selectionRectangle.getY1()),
        new Point2D(selectionRectangle.getX2(), selectionRectangle.getY2()));
    writableImageEditor = new WritableImageEditor();
  }


  public void resetStatus() {
    drawEditor.resetStatus();
    textEditor.resetStatus();
    writableImageEditor.setCutElement(null);
  }


  public void addPointWithHistory(Point2D point2D, Color color) {
    drawEditor.addPoint(point2D, color);
    history.addDrawRoad(getDrawingPath().snapshot());
  }

  public List<Integer> getIndexOfMoveText() {
    return textEditor.getIndexOfMoveLetters();
  }


  public DrawingPath getRoadWithStatus() {
    return drawEditor.getRoadWithStatus();
  }

  public void updatePaintStatusInRectangle(Point2D fPoint, Point2D sPoint) {
    drawEditor.updatePaintStatusInRectangle(fPoint, sPoint);
    //history.addDrawRoad(getDrawRoad());
  }

  public void updateTextStatusInRectangle(Point2D fPoint, Point2D sPoint) {
    textEditor.updateMoveStatusInRectangle(fPoint, sPoint);
  }

  public void moveCopyPaintElement(Point2D p, boolean b) {
    drawEditor.moveCopyPaintElement(p);
  }

  public void approvalMoveSelectedLetters(Point2D howPxToMove) {
    textEditor.moveSelectedLetters(howPxToMove);
  }

  public void previewMoveSelectedLetters(Point2D howPxToMove) {
    textEditor.moveSelectedLetters(howPxToMove);
  }

  public void saveByEditType() {
    history.saveByEditType(drawEditor, textEditor);
  }


  public void undo() {
    history.removeLastStep();
    //paintRoad.setPaintRoad(history.getGroupDrawLast());
    var lastDraw = history.getLastDraw();
    drawingPath.setNewPaintRoad(lastDraw);
    var lastText = history.getLastGroupTexts();
    //groupText.setGroupText(lastText);
    textEditor.setGroupLetter(lastText);
    writableImageEditor.setLayer(history.getLastBucket());
    layerImage = writableImageEditor.getLayer();
    //paintRoad = last;
    //groupText = history.getGroupTextsLast();
  }

  public void changeRotatePaintPoint(DrawingPath rotatedPoint) {
    drawEditor.changeRotatePaintPoint(rotatedPoint);
  }


  public void removeDrawWithHistory(Point2D point2D) {
    drawEditor.removePoint(point2D);
    textEditor.removePoint(point2D);
    //groupText.setGroupText(textEditor.getGroupText());
    textEditor.setGroupLetter(textEditor.getGroupLetter());
  }

  public void removeLetterWithHistory(Point2D point2D) {
    drawEditor.removePoint(point2D);
    textEditor.removePoint(point2D);
  }

  public void addLetterWithHistory(Letter letter) {
    textEditor.addLetter(letter);
    history.addGroupText(textEditor.getGroupLetter().snapshot());
  }

  public void rotateLetter(int angle, Integer index, Point2D midPoint, Rectangle2D rectangleRotate) {
    textEditor.setAngleForLetter(angle, index, midPoint, rectangleRotate);
    //history.addGroupText(textEditor.getGroupText());
  }

  public Color getBackgroundColor() {
    return drawEditor.getBackgroundColor();
  }

  public void setBackgroundColor(Color color) {
    drawEditor.setBackgroundColor(color);
  }

  public Integer getFontLetter() {
    return textEditor.getFontText();
  }

  public void setFontLetter(Integer fontSize) {
    textEditor.setFontText(fontSize);
  }

  public Integer getSizeWriteCursor() {
    return textEditor.getFontText();
  }

  public void setSizeDrawCursor(Integer sizeCursor) {
    drawEditor.setSizeCursor(sizeCursor);
    figureEditor.setLineSize(sizeCursor);
  }

  public void setFirstClickDraw(Boolean isFirstClick) {
    drawEditor.setFirstClick(isFirstClick);
  }

  public void saveHistory() {
    history.addDrawRoad(getDrawingPath().snapshot());
    history.addGroupText(textEditor.getGroupLetter().snapshot());
    if (layerImage != null) {
      history.addLayer(writableImageEditor.snapshotLayer(layerImage));
    }
  }

//  public void finalizeDrawElement(Point2D clickedPoint) {
//    drawElementBucket.createFromPath(this, clickedPoint, color, canvas);
//  }


  public void renderPreviewFigure(Point2D firstRectanglePoint, Point2D secondRectanglePoint, FigureType figureType) {
    figureEditor.previewFigure(firstRectanglePoint, secondRectanglePoint, figureType, color);
  }

  public void addPreviewFigure() {
    drawEditor.addFigureAsPoints(figureEditor.getFigure());
    figureEditor.removePreviewFigure();
    history.addDrawRoad(getDrawingPath().snapshot());
  }

  public void fillPlace(Point2D point2D) {
//    FillBucket fillBucket = new FillBucket(canvas);
//    fillBucket.bfs(point2D,color);
    //writableImage = fillBucket.getLayyer();
    layerImage = writableImageEditor.bucketFillLayer(canvas, point2D, color);
    WritableImage layerSnapshot = writableImageEditor.snapshotLayer(layerImage);
    history.addLayer(layerSnapshot);
  }

  public void cutImageWriteable(Point2D squareStart, Point2D squareEnd, Point2D mousePlace) {
    writableImageEditor.setWritableImage(layerImage);
    writableImageEditor.cutMoveImage(squareStart, squareEnd, mousePlace);
    layerImage = writableImageEditor.getWritableImage();
  }

  public void commitCutBitMap() {
    writableImageEditor.commitCutBitMap();
  }

  public void previewMoveSelectedBitMap(Point2D moveCordinates) {
    writableImageEditor.changePositionMoveElement(moveCordinates);
  }

  public void commitCutBitMap(Point2D lastCursorPosition) {
    var cutEl = writableImageEditor.getCutElement();
    writableImageEditor.commitCut(cutEl, lastCursorPosition);
    layerImage = writableImageEditor.getLayer();
  }

  public void changeRotateSelectedBitMap(int angle, Point2D midPoint) {
    writableImageEditor.rotateCutElement(angle, midPoint);
  }

  public void clear() {
    drawingPath.clear();
    textEditor.clear();
    layerImage = null;
    writableImageEditor.setCutElement(null);
    writableImageEditor.clear();
  }

//  public void commitCutBitMap() {
//    var cutEl = writableImageEditor.getCutElement();
//    writableImageEditor.commitCut(cutEl);
//  }
}
