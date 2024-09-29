package com.tolmic.puzzle15;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Puzzle15Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Puzzle15Application.class.getResource("puzzle-solver.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        stage.setTitle("Puzzle 15");
        stage.setScene(scene);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("images/icon.jpg")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
