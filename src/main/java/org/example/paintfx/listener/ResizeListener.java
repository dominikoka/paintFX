package org.example.paintfx.listener;

import javafx.scene.Scene;
import org.example.paintfx.controller.ToolController;
import org.example.paintfx.view.MainView;

public class ResizeListener {
  Scene scene;
  ToolController toolController;


  public ResizeListener(Scene scene, MainView mainView, ToolController toolController) {
    this.scene = scene;
    resize();
    this.toolController = toolController;
  }

  private void resize() {
    scene.widthProperty().addListener((property, lastValue, newValue) ->
        {
        }
    );
    scene.heightProperty().addListener((property, lastValue, newValue) ->
        {
        }
    );
  }


//  private void setHeight(int height) {
//    if (height > 800) {
//      var padding = ((height - 800) / 2) +93;
//      toolController.setPaddingCordinates_H(-padding);
//    } else {
//      toolController.setPaddingCordinates_H(-93);
//    }
//  }
//
//  private void setWidth(Integer width) {
//    System.out.println("width"+ width);
//    if (width > 1200) {
//      var padding = (width - 1200) / 2;
//      toolController.setPaddingCordinates_W(-padding);
//    } else {
//      toolController.setPaddingCordinates_W(-4);
//    }
//  }


}
