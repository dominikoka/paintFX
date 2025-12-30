package org.example.paintfx.listener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Data;
import org.example.paintfx.controller.ToolController;
import org.example.paintfx.model.SelectedTool;

@Data
public class KeyListener implements EventHandler<KeyEvent> {
  private ToolController toolController;


  public KeyListener(ToolController toolController) {
    this.toolController = toolController;
  }


  @Override
  public void handle(KeyEvent keyEvent) {
    if (toolController.getActiveTool() == SelectedTool.WRITE) {
      toolController.onKey(keyEvent);
    }
  }
}
