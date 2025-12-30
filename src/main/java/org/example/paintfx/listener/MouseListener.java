package org.example.paintfx.listener;

import javafx.scene.Scene;
import org.example.paintfx.controller.ToolController;
import org.example.paintfx.model.SelectedTool;
import org.example.paintfx.view.MainView;

import java.util.concurrent.atomic.AtomicReference;

public class MouseListener {

  AtomicReference<Boolean> afterMove = new AtomicReference<>(false);
  ToolController toolController;
  //MainController mainController;

  javafx.scene.canvas.Canvas scene;
  ResizeListener reSizeListener;

  public MouseListener(Scene scene1, ToolController toolController, MainView mainView) {
    this.scene = mainView.getCanvas();
    this.toolController = toolController;
    initializeListener();
    reSizeListener = new ResizeListener(scene1, mainView, toolController);
  }


  public void initializeListener() {
    scene.setOnMouseReleased(mouseEvent -> {
      toolController.getReleased(mouseEvent);
      scene.setOnMouseMoved(mouseEvent1 -> {
        toolController.getMoved(mouseEvent1);
      });
    });
    scene.setOnMouseClicked(mouseEvent -> {
      if (afterMove.get()) {
        afterMove.set(false);
        toolController.getClicked(mouseEvent);
        scene.setOnMouseMoved(null);
      } else {
        if (toolController.getActiveTool() == SelectedTool.PAINT || toolController.getActiveTool() == SelectedTool.WRITE || toolController.getActiveTool() == SelectedTool.ROTATE || toolController.getActiveTool() == SelectedTool.LINE || toolController.getActiveTool() == SelectedTool.RECTANGLE || toolController.getActiveTool() == SelectedTool.OVAL || toolController.getActiveTool() == SelectedTool.PAINTELEMENT) {
          scene.setOnMouseMoved(null);
        } else {
          afterMove.set(true);
        }
        toolController.getClicked(mouseEvent);
      }
    });
    scene.setOnMouseDragged(mouseEvent -> {
      toolController.getDragged(mouseEvent);
    });
    scene.setOnMousePressed(mouseEvent -> {
      toolController.getPressed(mouseEvent);
    });
  }


}
