package org.example.paintfx.model.display.writableimage;

import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

@Data
public class FillBucket {
  WritableImage writableImage;
  private Canvas canvas;
  //Color clickedColor;
  private WritableImage layyer;

  public FillBucket() {
    //this.canvas = canvas;

  }

  public void updateSnapshot(Canvas canvas) {
    writableImage = getWritableImage(canvas, 1);
    if (layyer == null) {
      layyer = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
    }
  }

  public void bfs(Point2D clickedPoint, Color newColor) {
    Color clickedColor = getClickedColor(clickedPoint);
    Queue<Point2D> queue = new ArrayDeque<>();
    PixelWriter writer = writableImage.getPixelWriter();
    PixelWriter layer = layyer.getPixelWriter();
    if (!clickedColor.equals(newColor)) {
      queue.add(clickedPoint);
    }
    while (!queue.isEmpty()) {
      var point = queue.remove();
      if (!isOutOnCanvas(point) && isTheSameColor(point, clickedColor)) {
        layer.setColor((int) point.getX(), (int) point.getY(), newColor);
        writer.setColor((int) point.getX(), (int) point.getY(), newColor);
        queue.add(new Point2D(point.getX() - 1, point.getY()));
        queue.add(new Point2D(point.getX() + 1, point.getY()));
        queue.add(new Point2D(point.getX(), point.getY() - 1));
        queue.add(new Point2D(point.getX(), point.getY() + 1));
      }
    }
  }

  private boolean isTheSameColor(Point2D point, Color clickedColor) {
    PixelReader reader = writableImage.getPixelReader();
    return reader.getColor((int) point.getX(), (int) point.getY()).equals(clickedColor);
  }

  private boolean isOutOnCanvas(Point2D point) {
    return (point.getX() >= writableImage.getWidth() || point.getY() >= writableImage.getHeight()) || (point.getX() < 0 || point.getY() < 0);
  }

  public void dfs(WritableImage writableImage, Point2D clickedPoint, Color newColor) {
    Stack<Point2D> stack = new Stack<>();
    Color clickedColor = getClickedColor(clickedPoint);
    PixelReader reader = writableImage.getPixelReader();
    PixelWriter writer = writableImage.getPixelWriter();
    if (!clickedColor.equals(newColor)) {
      //writer.setColor((int) clickedPoint.getX(), (int) clickedPoint.getY(), newColor);
      stack.push(clickedPoint);
    }
    while (!stack.isEmpty()) {
      var point = stack.pop();
      if ((point.getX() >= writableImage.getWidth() || point.getY() >= writableImage.getHeight()) || (point.getX() < 0 || point.getY() < 0) || !reader.getColor((int) point.getX(), (int) point.getY()).equals(clickedColor)) {
      } else {
        writer.setColor((int) point.getX(), (int) point.getY(), newColor);
        stack.push(new Point2D(point.getX() - 1, point.getY()));
        stack.push(new Point2D(point.getX() + 1, point.getY()));
        stack.push(new Point2D(point.getX(), point.getY() - 1));
        stack.push(new Point2D(point.getX(), point.getY() + 1));
      }
    }
  }

  private Color getClickedColor(Point2D point2D) {
    PixelReader reader = writableImage.getPixelReader();
    return reader.getColor((int) point2D.getX(), (int) point2D.getY());
  }

  private WritableImage getWritableImage(Canvas canvas, int pixelScale) {
    WritableImage writableImage = new WritableImage((int) Math.rint(pixelScale * canvas.getWidth()),
        (int) Math.rint(pixelScale * canvas.getHeight()));
    SnapshotParameters spa = new SnapshotParameters();
    spa.setTransform(Transform.scale(pixelScale, pixelScale));
    return canvas.snapshot(spa, writableImage);
  }


}
