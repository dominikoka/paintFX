module org.example.paintfx {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires static lombok;
  requires javafx.swing;


  opens org.example.paintfx to javafx.fxml;
  exports org.example.paintfx;
  exports org.example.paintfx.controller;
  opens org.example.paintfx.controller to javafx.fxml;
  exports org.example.paintfx.model;
  opens org.example.paintfx.model to javafx.fxml;
  exports org.example.paintfx.model.display.draw;
  opens org.example.paintfx.model.display.draw to javafx.fxml;
//  exports org.example.paintfx.controller.scene;
//  opens org.example.paintfx.controller.scene to javafx.fxml;
  exports org.example.paintfx.listener;
  opens org.example.paintfx.listener to javafx.fxml;
  exports org.example.paintfx.view;
  opens org.example.paintfx.view to javafx.fxml;
  exports org.example.paintfx.model.display.text;
  opens org.example.paintfx.model.display.text to javafx.fxml;
  exports org.example.paintfx.model.display;
  opens org.example.paintfx.model.display to javafx.fxml;
  exports org.example.paintfx.model.history;
  opens org.example.paintfx.model.history to javafx.fxml;
}