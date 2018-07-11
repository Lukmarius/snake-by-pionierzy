package com.codecool.snake;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage primaryStage;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showMainMenu() {
        try {
            FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource(MainMenuController.MenuFXML));
            Parent root = mainMenuLoader.load();
            MainMenuController mainMenuController = mainMenuLoader.getController();
            mainMenuController.setMainController(this);
            primaryStage.setScene(new Scene(root, MainMenuController.MENU_WIDTH, MainMenuController.MENU_HEIGHT));
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    public void startNewGame(Game.GameMode gameMode) {
        try {
            primaryStage.hide();

            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource(GameController.GameFXML));
            Parent root = gameLoader.load();

            GameController gameController = gameLoader.getController();
            gameController.setMainController(this);
            primaryStage.setScene(new Scene(root, GameController.WINDOW_WIDTH, GameController.WINDOW_HEIGHT));

            gameController.setUpGame(gameMode);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
