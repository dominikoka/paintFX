package org.example.paintfx.model.display.figure;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class FigureEditorTest {
  private FigureEditor figureEditor;

  @BeforeEach
  void setUp() {
    figureEditor = new FigureEditor();
  }

  @Test
  void shouldDeletePreviewFigureAndSetNull() {
    //arrange
    Point2D startP = new Point2D(5, 5);
    Point2D endP = new Point2D(50, 50);
    figureEditor.previewFigure(startP, endP, FigureType.RECTANGLE, Color.GREEN);
    //act
    figureEditor.removePreviewFigure();
    //asset
    assertNull(figureEditor.getFigure());
    assertEquals(0, figureEditor.getFigures().size());
  }

  @Test
  void shouldCreatePreviewLine() {
    //arrange
    Point2D startP = new Point2D(5, 5);
    Point2D endP = new Point2D(50, 50);
    Color color = Color.GREEN;
    //act
    figureEditor.previewFigure(startP, endP, FigureType.LINE, color);
    //assert
    assertEquals(color, figureEditor.getFigure().getColor());
    assertEquals(FigureType.LINE, figureEditor.getFigure().getFigureType());
  }

  @Test
  void initializeFigureEditorWithEmptyFiguresList() {
    //arrange
    FigureEditor editor = new FigureEditor();
    //act
    //assert
    assertNotNull(editor.getFigures());
    assertEquals(0, editor.getFigures().size());
    assertNull(editor.getFigure());

  }
  @Test
  void shouldCreateRectangleWithReversePoints() {
    //arrange
    Point2D startP = new Point2D(50, 50);
    Point2D endP = new Point2D(5, 5);
    Color color = Color.GREEN;
    //act
    figureEditor.previewFigure(startP,endP,FigureType.RECTANGLE,color);
    //assert
    assertNotNull(figureEditor.getFigure());

  }
}
