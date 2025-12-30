package org.example.paintfx.model.display.text;

public enum LetterRotation {
  NORMAL_0(0),
  LEFT_270(270),
  RIGHT_90(90),
  UPSIDE_DOWN_180(180);

  private final int degree;

  LetterRotation(int degree) {
    this.degree = degree;
  }

  public LetterRotation next() {
    if (degree == 0) {
      return LetterRotation.RIGHT_90;
    } else if (degree == 90) {
      return LetterRotation.UPSIDE_DOWN_180;
    } else if (degree == 180) {
      return LetterRotation.LEFT_270;
    } else {
      return LetterRotation.NORMAL_0;
    }
  }

  public LetterRotation prev() {
    if (degree == 0) {
      return LetterRotation.LEFT_270;
    } else if (degree == 90) {
      return LetterRotation.NORMAL_0;
    } else if (degree == 180) {
      return LetterRotation.RIGHT_90;
    } else {
      return LetterRotation.UPSIDE_DOWN_180;
    }
  }

  public int getDegree() {
    return this.degree;
  }

}
