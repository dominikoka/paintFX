package org.example.paintfx.model.display.text;

import javafx.geometry.Point2D;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import org.example.paintfx.model.StableVariables;

import java.util.Objects;
import java.util.Stack;


public class GroupLetter {
  @Getter
  @Setter
  private Stack<Letter> groupText = new Stack<>();


  private Double size = 0.0;

  public void addElement(Letter letter) {
    var keyCode = letter.getKeyEvent().getCode().getCode();
    if (keyCode == 8 && !groupText.isEmpty()) {
      groupText.pop();
    } else {
      addLetterAndSetLocation(letter);
    }
  }

  private Double getLastPositionX() {
    if (!groupText.isEmpty()) {
      return groupText.getLast().getDrawingPlace().getX();
    }
    return (double) -1;
  }

  private Double getLastPositionY() {
    if (!groupText.isEmpty()) {
      return groupText.getLast().getDrawingPlace().getY();
    }
    return (double) -1;
  }

  private void addLetterAndSetLocation(Letter letter) {
    if (groupText.isEmpty()) {
      groupText.add(letter);
      size = getWidthLetter(letter) + letter.getSize() * 0.3;
      return;
    }
    if (isTheSameLine(letter, groupText.getLast()) && !groupText.isEmpty()) {
      changePositionLetter(size, letter);
    }
    groupText.add(letter);
    size = getWidthLetter(letter) + letter.getSize() * 0.3;
  }

  private void changePositionLetter(Double size, Letter letter) {
    letter.setDrawingPlace(new Point2D(getLastPositionX() + size, getLastPositionY()));
    letter.setDrawingPlace(new Point2D(getLastPositionX() + size, getLastPositionY()));
  }

  private boolean isTheSameLine(Letter letter, Letter last) {
    return letter.getY() == last.getY();
  }

  private Double getWidthLetter(Letter letter) {
    Text t = new Text(letter.getLetterLocation());
    return t.getLayoutBounds().getWidth() + StableVariables.PADDING_WRITE_LETTER;
  }

  public String getChapter() {
    StringBuilder stringBuilder = new StringBuilder();
    for (var oneChar : groupText) {
      stringBuilder.append(oneChar.getKeyEvent().getText());
    }
    return stringBuilder.toString();
  }

  public void setStatus(int i, boolean status) {
    groupText.get(i).setEditStatus(status);
  }

  public GroupLetter snapshot() {
    GroupLetter groupLetterCopy = new GroupLetter();
    for (var el : groupText) {
      var letterCopy = el.snapshot();
      groupLetterCopy.getGroupText().push(letterCopy);
    }
    return groupLetterCopy;
  }

  public void setGroupText(GroupLetter lastText) {
    groupText = new Stack<>();
    for (var el : lastText.getGroupText()) {
      groupText.push(el);
    }
  }

  //  private InputText changeTextRoad(Point2D dp, int counter) {
//    InputText inputTextCopy = new InputText();
//    for (int i = 0; i < inputText.getInputText().size(); i++) {
//      if (counter != i) {
//        inputTextCopy.addElement(inputText.getInputText().get(i));
//      } else {
//        Letter letterWithNewCordinates = inputText.getInputText().get(i);
//        letterWithNewCordinates.setDrawingPlace(dp);
//        inputTextCopy.addElement(letterWithNewCordinates);
//      }
//    }
//    return inputTextCopy;
//  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GroupLetter other = (GroupLetter) obj;
    if (!Objects.equals(other.getLastPositionX(), getLastPositionX()))
      return false;
    if (!Objects.equals(other.getLastPositionY(), getLastPositionY()))
      return false;
    if (other.getGroupText().size() != getGroupText().size()) {
      return false;
    }
    if (!other.getChapter().toString().contentEquals(getChapter())) {
      return false;
    }
    return true;
  }

  public boolean isEdit() {
    for (var el : groupText) {
      if (el.getEditStatus()) {
        return true;
      }
    }
    return false;
  }
}
