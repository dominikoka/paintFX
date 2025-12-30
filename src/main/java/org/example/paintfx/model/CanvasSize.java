package org.example.paintfx.model;

import lombok.Data;

@Data
public class CanvasSize {
  public CanvasSize(int WCanvas, int HCanvas) {
    this.WCanvas = WCanvas;
    this.HCanvas = HCanvas;
  }

  private int WCanvas;
  private int HCanvas;

}
