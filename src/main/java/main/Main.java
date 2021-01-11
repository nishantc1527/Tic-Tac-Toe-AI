package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.setResizable(false);

    Scene scene = new Scene(FXMLLoader.load(
            Objects.requireNonNull(
                    getClass().getClassLoader().getResource("main.fxml")
            )
    ), 600, 600);

    primaryStage.setScene(scene);
    primaryStage.show();
    new GridPane().getRowConstraints().add(new RowConstraints());
  }
}
