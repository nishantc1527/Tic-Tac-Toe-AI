package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Pair;

public class Controller {

  private final static boolean x = true, o = false;
  private boolean gameEnded = false;

  @FXML
  private Button button1,
          button2,
          button3,
          button4,
          button5,
          button6,
          button7,
          button8,
          button9;

  @FXML
  protected void buttonClicked(ActionEvent e) {
    if (gameEnded) {
      return;
    }

    Button[] buttons = new Button[]{
            button1, button2, button3, button4, button5, button6, button7, button8, button9
    };

    Button source = (Button) e.getSource();

    if (!source.getText().equals("")) {
      return;
    }

    source.setText("X");

    if (checkWin("X")) {
      source.setStyle("-fx-background-color: blue;");
      gameEnded = true;
      return;
    }

    if (checkTie()) {
      source.setStyle("-fx-background-color: green;");
      gameEnded = true;
      return;
    }

    Button bestOption = minimax(buttons, o).getValue();

    bestOption.setText("O");

    if (checkWin("O")) {
      bestOption.setStyle("-fx-background-color: red;");
      gameEnded = true;
      return;
    }

    if (checkTie()) {
      source.setStyle("-fx-background-color: green;");
      gameEnded = true;
    }
  }

  private Pair<Integer, Button> minimax(Button[] buttons, boolean turn) {
    if (checkWin("O")) {
      return new Pair<>(1, null);
    }

    if (checkWin("X")) {
      return new Pair<>(-1, null);
    }

    if (checkTie()) {
      return new Pair<>(0, null);
    }

    if (turn == o) {
      int max = Integer.MIN_VALUE;
      Button best = null;

      for (Button button : buttons) {
        if (button.getText().equals("")) {
          button.setText("O");
          Pair<Integer, Button> current = minimax(buttons, x);
          button.setText("");

          if (current.getKey() > max) {
            max = current.getKey();
            best = button;
          }
        }
      }

      return new Pair<>(max, best);
    } else {
      int min = Integer.MAX_VALUE;
      Button best = null;

      for (Button button : buttons) {
        if (button.getText().equals("")) {
          button.setText("X");
          Pair<Integer, Button> current = minimax(buttons, o);
          button.setText("");

          if (current.getKey() < min) {
            min = current.getKey();
            best = button;
          }
        }
      }

      return new Pair<>(min, best);
    }
  }

  private boolean checkWin(String c) {
    return
            (button1.getText().equals(button2.getText()) && button1.getText().equals(button3.getText()) && button1.getText().equals(c)) ||
                    (button4.getText().equals(button5.getText()) && button4.getText().equals(button6.getText()) && button4.getText().equals(c)) ||
                    (button7.getText().equals(button8.getText()) && button7.getText().equals(button9.getText()) && button7.getText().equals(c))

                    ||

                    (button1.getText().equals(button4.getText()) && button1.getText().equals(button7.getText()) && button1.getText().equals(c)) ||
                    (button2.getText().equals(button5.getText()) && button2.getText().equals(button8.getText()) && button2.getText().equals(c)) ||
                    (button3.getText().equals(button6.getText()) && button3.getText().equals(button9.getText()) && button3.getText().equals(c))

                    ||

                    (button1.getText().equals(button5.getText()) && button1.getText().equals(button9.getText()) && button1.getText().equals(c)) ||
                    (button3.getText().equals(button5.getText()) && button5.getText().equals(button7.getText()) && button3.getText().equals(c));
  }

  private boolean checkTie() {
    return !button1.getText().equals("") &&
            !button2.getText().equals("") &&
            !button3.getText().equals("") &&
            !button4.getText().equals("") &&
            !button5.getText().equals("") &&
            !button6.getText().equals("") &&
            !button7.getText().equals("") &&
            !button8.getText().equals("") &&
            !button9.getText().equals("");
  }

}
