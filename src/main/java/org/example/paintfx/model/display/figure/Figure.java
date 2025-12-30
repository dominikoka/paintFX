package org.example.paintfx.model.display.figure;

import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class Figure {
  private Color color;
  private FigureType figureType;
  private int lineSize;

  public Figure(Color color, FigureType figureType, int lineSize) {
    this.color = color;
    this.figureType = figureType;
    this.lineSize = lineSize;
  }

  public Figure() {

  }


}
