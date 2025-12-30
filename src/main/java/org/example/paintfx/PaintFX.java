package org.example.paintfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.paintfx.controller.DisplayController;
import org.example.paintfx.controller.ToolController;
import org.example.paintfx.listener.KeyListener;
import org.example.paintfx.listener.MouseListener;
import org.example.paintfx.model.StableVariables;
import org.example.paintfx.model.display.SelectionRectangle;
import org.example.paintfx.model.display.draw.DrawEditor;
import org.example.paintfx.model.display.draw.DrawingPath;
import org.example.paintfx.model.display.draw.SelectionState;
import org.example.paintfx.model.display.text.GroupLetter;
import org.example.paintfx.model.display.text.TextEditor;
import org.example.paintfx.model.history.History;
//import org.example.paintfx.view.ButtonsColor;
import org.example.paintfx.view.MainView;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class PaintFX extends Application {
  private Scene scene;
  private ToolController toolController;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(PaintFX.class.getResource("main.fxml"));
    scene = new Scene(fxmlLoader.load(), StableVariables.W, StableVariables.H);
    MainView mainView = fxmlLoader.getController();
    mainView.setPaintFX(this);



    History history = new History();
    GroupLetter groupLetter = new GroupLetter();


    DrawingPath drawingPath = new DrawingPath();
    SelectionState move = new SelectionState();

    SelectionRectangle selectionRectangle = new SelectionRectangle(0.0, 0.0);
    selectionRectangle.setSecondPoint(0.0, 0.0);
    //DrawEditor drawingEditor = new DrawEditor(move, drawRoad, history);
    DrawEditor drawingEditor = new DrawEditor();
    TextEditor textEditor = new TextEditor();
    DisplayController displayController = new DisplayController(drawingEditor, textEditor, history, selectionRectangle,mainView.getCanvas());
    mainView.setDisplayController(displayController);


    scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (e.getCode() == KeyCode.SPACE) {
        e.consume();
      }
    });
    stage.setX(0);
    stage.setY(0);
    AtomicReference<Boolean> afterMove = new AtomicReference<>(false);
    toolController = new ToolController(scene, mainView, displayController);
    mainView.draw();
    MouseListener mouseListener = new MouseListener(scene, toolController, mainView);
    mainView.setToolControllerAnd111(toolController);

    //KeyListener keyListener = new KeyListener(this,displayController,mainView);
    KeyListener keyListener = new KeyListener(toolController);
//    scene.setOnKeyPressed(keyListener);
    scene.addEventFilter(KeyEvent.KEY_PRESSED, keyListener);

    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }
}