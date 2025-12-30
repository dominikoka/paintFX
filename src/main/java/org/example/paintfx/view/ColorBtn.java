package org.example.paintfx.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class ColorBtn {
  private MainView mainView;


  @FXML
  private Button colorRedBtn, colorGreenBtn, colorBlueBtn, colorBlackBtn, colorYellowBtn,colorOrangeBtn;

//  public ColorBtn(MainView mainView) {
//    this.mainView = mainView;
//
//
//  }

  public ColorBtn() {
  }


  @FXML
  public void initialize() {
    colorGreenBtn.setOnAction(e -> changeColorGreen());
    colorRedBtn.setOnAction(e -> changeColorRed());
    colorBlueBtn.setOnAction(e -> changeColorBlue());
    colorBlackBtn.setOnAction(e -> changeColorBlack());
    colorYellowBtn.setOnAction(e -> changeColorYellow());
    colorOrangeBtn.setOnAction(e -> changeColorOrange());
  }

  private void changeColorOrange() {
    mainView.setActualColorAndIdButton(Color.ORANGE, "btn__actualColorOrange");
//    actualCollor = Color.GREEN;
//    gc.setFill(actualCollor);
//    actualColorBtn.setId("btn__actualColorGreen");
    mainView.changeBackground(Color.ORANGE);
  }

  @FXML
  public void changeColorGreen() {
    mainView.setActualColorAndIdButton(Color.GREEN, "btn__actualColorGreen");
//    actualCollor = Color.GREEN;
//    gc.setFill(actualCollor);
//    actualColorBtn.setId("btn__actualColorGreen");
    mainView.changeBackground(Color.GREEN);
  }

  @FXML
  public void changeColorBlack() {
    mainView.setActualColorAndIdButton(Color.BLACK, "btn__actualColorBlack");
//    actualCollor = Color.GREEN;
//    gc.setFill(actualCollor);
//    actualColorBtn.setId("btn__actualColorGreen");
    mainView.changeBackground(Color.BLACK);
  }

  @FXML
  public void changeColorBlue() {
    mainView.setActualColorAndIdButton(Color.BLUE, "btn__actualColorBlue");
//    actualCollor = Color.GREEN;
//    gc.setFill(actualCollor);
//    actualColorBtn.setId("btn__actualColorGreen");
    mainView.changeBackground(Color.BLUE);
  }

  @FXML
  public void changeColorYellow() {
    mainView.setActualColorAndIdButton(Color.YELLOW, "btn__actualColorYellow");
    mainView.changeBackground(Color.YELLOW);
  }

  public void changeColorRed() {
//    actualCollor = Color.RED;
//    gc.setFill(actualCollor);
//    actualColorBtn.setId("btn__actualColorRed");
    mainView.setActualColorAndIdButton(Color.RED, "btn__actualColorRed");
    mainView.changeBackground(Color.RED);
  }
}
