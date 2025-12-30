package org.example.paintfx.model.display.writeableimage;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.example.paintfx.model.display.writableimage.WritableImageEditor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WritableImageEditorTest {
  private WritableImageEditor writableImageEditor;
  @BeforeEach
  void initialize() {
    writableImageEditor = new WritableImageEditor();
  }

  @Test
  void shouldRotateCutEl90Degrees() {
    //arrange
    WritableImage cutEl = new WritableImage(50, 30);
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        cutEl.getPixelWriter().setColor(i, j, Color.GREEN);
      }
    }
    writableImageEditor.setCutElement(cutEl);
    writableImageEditor.setLayer(new WritableImage(300, 300));
    Point2D center = new Point2D(150, 150);
    //act
    writableImageEditor.rotateCutElement(90, center);
    //assert
    assertNotNull(writableImageEditor.getCutElement());
    assertEquals(50, writableImageEditor.getCutElement().getHeight());
    assertEquals(30, writableImageEditor.getCutElement().getWidth());
  }

  @Test
  void commitCutElementToLayer() {
    //arrange
    WritableImage layer = new WritableImage(50, 50);
    WritableImage cutEl = new WritableImage(10, 10);
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        cutEl.getPixelWriter().setColor(i, j, Color.GREEN);
      }
    }
    writableImageEditor.setLayer(layer);
    Point2D totalMove = new Point2D(10, 10);
    //act
    writableImageEditor.commitCut(cutEl, totalMove);
    //assert
    PixelReader layerReader = layer.getPixelReader();
    assertEquals(Color.GREEN, layerReader.getColor(15, 15));
    assertEquals(Color.GREEN, layerReader.getColor(10, 10));
  }

  @Test
  void shouldNotCommitWhenLayerIsNull() {
    //arrange
    WritableImage cutEl = new WritableImage(10, 10);
    Point2D totalMove = new Point2D(10, 10);
    //assert
    try {
      writableImageEditor.commitCut(cutEl, totalMove);
    } catch (Exception e) {
      fail("nie powinno wyrzucic wyjatku");
    }
  }

  @Test
  void snapshotLayer() {
    //arrange
    WritableImage layer = new WritableImage(50, 50);
    layer.getPixelWriter().setColor(10, 10, Color.GREEN);
    //act
    WritableImage snapshot = writableImageEditor.snapshotLayer(layer);

    //assert
    assertNotNull(snapshot);
    assertEquals(Color.GREEN, snapshot.getPixelReader().getColor(10, 10));
  }

  @Test
  void shouldChangePositionMoveElement() {
    //arrange
    writableImageEditor.setMoveMouse(new Point2D(50, 100));
    Point2D moveDiff = new Point2D(10, -10);
    writableImageEditor.setCutElement(new WritableImage(10,10));

    //act
    writableImageEditor.changePositionMoveElement(moveDiff);
    //assert
    assertEquals(60, writableImageEditor.getMoveMouse().getX());
    assertEquals(90, writableImageEditor.getMoveMouse().getY());
  }

  @Test
  void shouldCutImage() {
    //arrange
    WritableImage testImage = new WritableImage(30, 30);
    for (int i = 0; i < 30; i++) {
      for (int j = 0; j < 30; j++) {
        testImage.getPixelWriter().setColor(i, j, Color.GREEN);
      }
    }
    writableImageEditor.setWritableImage(testImage);
    Point2D startP = new Point2D(10, 10);
    Point2D endP = new Point2D(20, 20);
    Point2D mouse = new Point2D(15, 15);

    //act
    writableImageEditor.cutMoveImage(startP, endP, mouse);
    //assert
    assertEquals(10, writableImageEditor.getCutElement().getWidth());
    assertEquals(10, writableImageEditor.getCutElement().getHeight());
  }

//  @Test
//  void shouldBFSFillRectangle() {
//    //arrange
//    new JFXPanel();
//    Canvas canvas = new Canvas(50, 50);
//    WritableImage layer = new WritableImage(50, 50);
//    for (int i = 0; i < 50; i++) {
//      for (int j = 0; j < 50; j++) {
//        layer.getPixelWriter().setColor(i, j, Color.WHITE);
//      }
//    }
//    for (int i = 10; i < 20; i++) {
//      for (int j = 10; j < 20; j++) {
//        layer.getPixelWriter().setColor(i, j, Color.GREEN);
//      }
//    }
//
//    writableImageEditor.setLayer(layer);
//    Point2D clickPoint = new Point2D(15, 15);
//
//    //act
//    WritableImage result = writableImageEditor.bucketFillLayer(canvas, clickPoint, Color.BLACK);
//    //assert
//    assertEquals(Color.BLACK, result.getPixelReader().getColor(11, 11));
//    assertEquals(Color.BLACK, result.getPixelReader().getColor(19, 19));
//  }
}
