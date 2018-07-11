package com.codecool.snake;

import com.codecool.snake.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TITLE = "Wild snakes";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainController mainController = new MainController(primaryStage);
        primaryStage.setTitle(TITLE);
        mainController.showMainMenu();
        primaryStage.show();
    }
}
