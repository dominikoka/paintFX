package org.example.paintfx.model.display.figure;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class FigureBasedRectangle extends Figure {
  private Rectangle2D figure;

  public FigureBasedRectangle(Rectangle2D figure,Color color,FigureType type,int lineSize) {
    super(color,type,lineSize);
    this.figure = figure;
  }


}
