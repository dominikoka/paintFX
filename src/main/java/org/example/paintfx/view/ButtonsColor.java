//package org.example.paintfx.view;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.paint.Color;
//
//public class ButtonsColor {
//  private final MainView mainView;
//
//  @FXML
//  private Button colorRedBtn;
//
//  public ButtonsColor(MainView mainView) {
//    this.mainView = mainView;
//
//    colorRedBtn.setOnAction(e -> changeColorRed());
//  }
//
//  @FXML
//  public void changeColorGreen() {
//    mainView.setActualColorAndIdButton(Color.GREEN, "btn__actualColorGreen");
////    actualCollor = Color.GREEN;
////    gc.setFill(actualCollor);
////    actualColorBtn.setId("btn__actualColorGreen");
//    mainView.changeBackground(Color.GREEN);
//  }
//
//  @FXML
//  public void changeColorYellow() {
//    mainView.setActualColorAndIdButton(Color.YELLOW, "btn__actualColorYellow");
//    mainView.changeBackground(Color.YELLOW);
//  }
//
//  public void changeColorRed() {
////    actualCollor = Color.RED;
////    gc.setFill(actualCollor);
////    actualColorBtn.setId("btn__actualColorRed");
//    mainView.setActualColorAndIdButton(Color.RED, "btn__actualColorRed");
//    mainView.changeBackground(Color.RED);
//  }
//}