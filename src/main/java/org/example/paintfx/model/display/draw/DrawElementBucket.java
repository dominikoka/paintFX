//package org.example.paintfx.model.display.draw;
//
//import javafx.geometry.Point2D;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.image.PixelReader;
//import javafx.scene.image.WritableImage;
//import javafx.scene.paint.Color;
//import org.example.paintfx.controller.DisplayController;
//
//public class DrawElementBucket {
//  private boolean[] visited;
//  //Stack<>
//
//  public void createFromPath(DisplayController displayController, Point2D clickedPoint, Color color, Canvas canvas) {
//    int wCanvas = displayController.getResolutionCanvas().getWCanvas();
//    int hCanvas = displayController.getResolutionCanvas().getHCanvas();
//
//    WritableImage canvasImage = new WritableImage(wCanvas, hCanvas);
//    var snapshotCanvas = canvas.snapshot(null, canvasImage);
//    int wXh =
//        wCanvas * hCanvas;
//    if (visited == null || visited.length != wXh) {
//      visited =
//          new boolean[wXh];
//    }
//    if (!isTheSameColor(clickedPoint, snapshotCanvas, color)) {
//    }
//
//
//  }
//
//  private boolean isTheSameColor(Point2D clickedPoint, WritableImage snapshotCanvas, Color color) {
//    PixelReader pixelReader = snapshotCanvas.getPixelReader();
//    var colorInCanvas = pixelReader.getColor((int) clickedPoint.getX(), (int) clickedPoint.getY());
//    if (color.equals(colorInCanvas)) {
//      return true;
//    }
//    return false;
//  }
//}
