package org.example.paintfx.model.display.writableimage;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class WritableImageEditor {
  FillBucket fillBucket = new FillBucket();
  int rotatedAngle = 0;
  int offsetX;
  int offsetY;
  private WritableImage writableImage;
  private WritableImage cutElement;
  private WritableImage layer;
  private Point2D moveMouse;

  public WritableImageEditor() {
  }

  public void setLayer(WritableImage layer) {
    this.layer = layer;
    fillBucket.setLayyer(layer);
  }

  public WritableImage bucketFillLayer(Canvas canvas, Point2D clickedPoint, Color newColor) {
    fillBucket.updateSnapshot(canvas);
    fillBucket.bfs(clickedPoint, newColor);
    writableImage = fillBucket.getWritableImage();
    layer = fillBucket.getLayyer();
    return fillBucket.getLayyer();
  }

  public void cutMoveImage(Point2D squareStart, Point2D squareEnd, Point2D mousePlace) {
    if (writableImage == null) {
      return;
    }

    PixelReader pixelReader = writableImage.getPixelReader();
    int x1 = (int) Math.min(squareStart.getX(), squareEnd.getX());
    int y1 = (int) Math.min(squareStart.getY(), squareEnd.getY());
    int x2 = (int) Math.max(squareStart.getX(), squareEnd.getX());
    int y2 = (int) Math.max(squareStart.getY(), squareEnd.getY());
    int width = Math.abs(x2 - x1);
    int height = Math.abs(y2 - y1);
    cutElement = new WritableImage(pixelReader, x1, y1, width, height);
    moveMouse = new Point2D(x1, y1);
    setTransparentOriginalImage(x1, y1, x2, y2);
  }

  private void setTransparentOriginalImage(int x1, int y1, int x2, int y2) {
    for (int i = x1; i < x2; i++) {
      for (int j = y1; j < y2; j++) {
        writableImage.getPixelWriter().setColor(i, j, Color.TRANSPARENT);
      }
    }
  }

  public void changePositionMoveElement(Point2D moveCordinates) {
    if (moveMouse == null || cutElement == null) {
      return;
    }
    moveMouse = new Point2D(moveMouse.getX() + moveCordinates.getX(), moveMouse.getY() + moveCordinates.getY());
  }

  public WritableImage snapshotLayer(WritableImage layerImage) {
    if (layerImage == null) {
      return null;
    }
    return new WritableImage(layerImage.getPixelReader(), (int) layerImage.getWidth(), (int) layerImage.getHeight());
  }

  public void commitCut(WritableImage cutEl, Point2D totalMove) {
    if (layer == null || cutEl == null || totalMove == null) {
      return;
    }
    PixelWriter layerWriter = layer.getPixelWriter();
    PixelReader cutElement = cutEl.getPixelReader();
    int w = (int) cutEl.getWidth();
    int h = (int) cutEl.getHeight();
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        Color cutElementColor = cutElement.getColor(x, y);
        if (cutElementColor.getOpacity() != 0) {
          layerWriter.setColor((int) (x + totalMove.getX()), (int) (y + totalMove.getY()), cutElementColor);
        }
      }
    }
  }

  public void rotateCutElement(int angle, Point2D center) {

    if (cutElement == null) {
      return;
    }
    Point2D centerAfterRotate = new Point2D(layer.getWidth() / 2, layer.getHeight() / 2);
    if (angle > 0) {
      rotate90(center);
    } else
      rotateMinus90(center);
  }

  private void rotateMinus90(Point2D center) {
    int w = (int) cutElement.getWidth();
    int h = (int) cutElement.getHeight();
    double oldCenterX = center.getX();
    double oldCenterY = center.getY();
    WritableImage newElement = new WritableImage(h, w);
    PixelWriter newElementWriteable = newElement.getPixelWriter();
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        var color = cutElement.getPixelReader().getColor(x, y);
        int newX = y;
        int newY = w - 1 - x;
        newElementWriteable.setColor(newX, newY, color);
      }
    }
    cutElement = newElement;
    offsetX = (int) (oldCenterX - newElement.getWidth() / 2);
    offsetY = (int) (oldCenterY - newElement.getHeight() / 2);
    offsetX = Math.max(0, offsetX);
    offsetX = Math.min((int) layer.getWidth() - (int) cutElement.getWidth(), offsetX);
    offsetY = Math.max(0, offsetY);
    offsetY = Math.min((int) layer.getHeight() - (int) cutElement.getHeight(), offsetY);

    moveMouse = new Point2D(offsetX, offsetY);
  }

  private void rotate90(Point2D center) {
    int w = (int) cutElement.getWidth();
    int h = (int) cutElement.getHeight();
    double oldCenterX = center.getX();
    double oldCenterY = center.getY();
//    System.out.println("--Rotate 90 debug--");
//    System.out.println("przed rotacją");
//    System.out.println("cut element: "+ w+" x "+h);
//    System.out.println("offsetX/Y "+ offsetX+" , "+offsetY);
//    System.out.println("centrum: "+ oldCenterX+","+oldCenterY);
    WritableImage newElement = new WritableImage(h, w);
    PixelWriter newElementWriteable = newElement.getPixelWriter();
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        var color = cutElement.getPixelReader().getColor(x, y);
        int newX = h - 1 - y;
        int newY = x;
        newElementWriteable.setColor(newX, newY, color);
      }
    }
    cutElement = newElement;
//    System.out.println("Po rotacji:");
//    System.out.println(" cut element: "+cutElement.getWidth()+ " x "+ cutElement.getHeight());
    offsetX = (int) (oldCenterX - newElement.getWidth() / 2);
    offsetY = (int) (oldCenterY - newElement.getHeight() / 2);
//    System.out.println("offsetx przed clip "+ offsetX);
//    System.out.println("offsety przed clip "+ offsetY);
    offsetX = Math.max(0, offsetX);
    offsetX = Math.min((int) layer.getWidth() - (int) cutElement.getWidth(), offsetX);
    offsetY = Math.max(0, offsetY);
    offsetY = Math.min((int) layer.getHeight() - (int) cutElement.getHeight(), offsetY);
//    System.out.println("offetX koncowy: "+ offsetX);
//    System.out.println("offetY koncowy: "+ offsetY);
//    System.out.println("nowe center: "+ (offsetX + cutElement.getWidth()/2)+","+(offsetY + cutElement.getHeight()/2));
    moveMouse = new Point2D(offsetX, offsetY);
    //commitCut(cutElement,new Point2D(offsetX,offsetY));
  }

  public void commitCutBitMap() {
    if (cutElement == null) {
      return;
    }
    commitCut(cutElement, new Point2D(offsetX, offsetY));
  }

  public void clear() {
  }

//  private void rotate180(Point2D center) {
//    int w = (int) cutElement.getWidth();
//    int h = (int) cutElement.getHeight();
//    WritableImage newElement = new WritableImage(h, w);
//    PixelWriter newElementWriteable = newElement.getPixelWriter();
//
//    for (int y = 0; y < h; y++) {
//      for (int x = 0; x < w; x++) {
//        var color = cutElement.getPixelReader().getColor(x, y);
//        int newX = h - 1 - y;
//        int newY = x;
//        newElementWriteable.setColor(newX, newY, color);
//      }
//    }
//    cutElement = newElement;
//    int offsetX = (int) (center.getX() - newElement.getWidth() / 2);
//    int offsetY = (int) (center.getY() - newElement.getHeight() / 2);
//    commitCut(cutElement, new Point2D(offsetX, offsetY));
//  }
}
