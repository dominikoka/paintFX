package org.example.paintfx.model;

import lombok.Getter;
import org.example.paintfx.controller.Tool;
import org.example.paintfx.tools.*;

@Getter
public enum SelectedTool {
  PAINT(new Draw()),
  CUT(new Cut()),
  RUBBER(new Rubber()),
  ROTATE(new Rotate()),
  WRITE(new Write()),
  BACKGROUNDER(null),
  RECTANGLE(new DrawRectangle()),
  OVAL(new DrawOval()),
  PAINTELEMENT(new Bucket()),
  LINE(new DrawLine());
  private final Tool tool;


  SelectedTool(Tool tool) {
    this.tool = tool;
  }

//  public static SelectedTool fromSymbol(String symbol) {
//    return Arrays.stream(SelectedTool.values()).filter(e -> e.symbol.equalsIgnoreCase(symbol)).findFirst()
//    .orElseThrow();
//  }

}
