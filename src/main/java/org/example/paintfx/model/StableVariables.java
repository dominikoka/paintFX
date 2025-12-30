package org.example.paintfx.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//TODO do usuniecia
@Data
public class StableVariables {
  public static Integer H = 800;
  public static Integer W = 1200;
  public static Integer PADDING_TOP_PANEL = 110;
  public static Integer SIZE_CURSOR = 5;
  @Getter
  @Setter
  public static Integer PADDING_H = -93;
  @Getter
  @Setter
  public static double PADDING_W = -4;
  public static double MAX_RECORD_ROAD = 50;
  public static double PADDING_WRITE_LETTER = 5;

//  public static void setPaddingW(double paddingW) {
//    StableVariables.paddingW = paddingW;
//  }

  public static void setH(Integer h) {
    StableVariables.H = h;
  }

  public static void setW(Integer w) {
    StableVariables.W = w;
  }


}
