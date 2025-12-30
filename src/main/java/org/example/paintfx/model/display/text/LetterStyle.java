package org.example.paintfx.model.display.text;

import lombok.Data;

import java.awt.*;

@Data
public class LetterStyle {
  Color color;
  Integer fontSize;

  public LetterStyle(Color color, Integer fontSize) {
    this.color = color;
    this.fontSize = fontSize;
  }
}
