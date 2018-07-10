package com.codecool.snake;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private Stage primaryStage;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showMainMenu() {
        try {
            FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource(MainMenuController.MenuFXML));
            Parent root = mainMenuLoader.load();
            MainMenuController mainMenuController = mainMenuLoader.getController();
            mainMenuController.setMainController(this);
            primaryStage.setScene(new Scene(root, MainMenuController.MENU_WIDTH, MainMenuController.MENU_HEIGHT));
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    public void startNewGame(Game.GameMode gameMode) {
        Game game = new Game();
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        game.start();
    }
}
