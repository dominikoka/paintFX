package org.example.paintfx.model.history;

import javafx.scene.image.WritableImage;
import lombok.Data;
import org.example.paintfx.model.display.draw.DrawEditor;
import org.example.paintfx.model.display.draw.DrawingPath;
import org.example.paintfx.model.display.text.GroupLetter;
import org.example.paintfx.model.display.text.TextEditor;

import java.util.Stack;

@Data
public class History {
  Stack<DrawingPath> drawingPaths;
  Stack<GroupLetter> groupLetters;
  Stack<HistoryType> historyTypes;
  Stack<WritableImage> bucket;
  //int counterAddGT = 1;

  public History() {
    drawingPaths = new Stack<>();
    groupLetters = new Stack<>();
    historyTypes = new Stack<>();
    bucket = new Stack<>();
  }

  public void addDrawRoad(DrawingPath drawingPath) {
    //var draw = drawRoad.snapshot();
    //System.out.println("add road history");
    drawingPaths.push(drawingPath);
    historyTypes.push(HistoryType.Draw);
  }

  public void saveByEditType(DrawEditor drawingEditor, TextEditor textEditor) {
    var isDrawingEdit = drawingEditor.isEdit();
    var isTextEdit = textEditor.isEdit();
    if (isDrawingEdit && isTextEdit) {
      drawingPaths.add(drawingEditor.snapshot());
      groupLetters.add(textEditor.snapshot());
      historyTypes.push(HistoryType.Together);
      return;
    }
    if (isDrawingEdit) {
      drawingPaths.add(drawingEditor.snapshot());
      historyTypes.push(HistoryType.Draw);
      return;
    }
    if (isTextEdit) {
      groupLetters.add(textEditor.snapshot());
      historyTypes.push(HistoryType.Text);
    }

  }

  private void removeLastDraw() {
    if (drawingPaths.isEmpty()) {
      return;
    }
    drawingPaths.pop();
  }

  private void removeLastWrite() {
    if (groupLetters.isEmpty()) {
      return;
    }
    groupLetters.pop();
  }

  public void addGroupText(GroupLetter groupLetter) {
    groupLetters.add(groupLetter);
    historyTypes.push(HistoryType.Text);
    //counterAddGT++;
  }


  public void removeLastStep() {
    if (historyTypes.isEmpty()) {
      return;
    }
    var type = historyTypes.pop();
    if (type.equals(HistoryType.Together)) {
      removeLastDraw();
      removeLastWrite();
      return;
    }
    if (type.equals(HistoryType.Bucket)) {
      removeLastBucket();
    }
    if (type.equals(HistoryType.Draw)) {
      removeLastDraw();
    } else {
      removeLastWrite();
    }
  }

  private void removeLastBucket() {
    if (bucket.isEmpty()) {
      return;
    }
    bucket.pop();
  }

  public GroupLetter getLastGroupTexts() {
    if (groupLetters == null || groupLetters.isEmpty()) {
      return new GroupLetter();
    } else {
      return groupLetters.getLast();
    }
  }

  public WritableImage getLastBucket() {
    if (bucket == null || bucket.isEmpty()) {
      return null;
    } else {
      return bucket.getLast();
    }
  }

  public DrawingPath getLastDraw() {
    if (drawingPaths == null || drawingPaths.isEmpty()) {
      return new DrawingPath();
    } else {
      return drawingPaths.getLast();
    }
  }

  public void addLayer(WritableImage writableImage) {
    bucket.push(writableImage);
    historyTypes.push(HistoryType.Bucket);
  }
}
