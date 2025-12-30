package org.example.paintfx.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


public class SaveCanvas {
  public static void save(Canvas canvas) {
    FileChooser savefile = new FileChooser();
    savefile.setTitle("Save File");

    File file = savefile.showSaveDialog(null);

    if (file != null) {
      try {
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ImageIO.write(renderedImage, "png", file);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
}
