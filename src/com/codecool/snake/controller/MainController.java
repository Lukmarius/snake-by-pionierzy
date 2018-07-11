package com.codecool.snake.controller;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
            primaryStage.hide();

            FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource(MainMenuController.MenuFXML));
            Parent root = mainMenuLoader.load();

            MainMenuController mainMenuController = mainMenuLoader.getController();
            mainMenuController.setMainController(this);
            primaryStage.setScene(new Scene(root, MainMenuController.MENU_WIDTH, MainMenuController.MENU_HEIGHT));
            primaryStage.show();

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

    public void showPauseMenu() {
        try {
            FXMLLoader pauseLoader = new FXMLLoader(getClass().getResource(PauseMenuController.PauseMenuFXML));
            Parent root = pauseLoader.load();

            PauseMenuController pauseMenuController = pauseLoader.getController();
            pauseMenuController.setMainController(this);

            Stage popUpWindow = new Stage(StageStyle.UTILITY);
            popUpWindow.initModality(Modality.WINDOW_MODAL);
            popUpWindow.initOwner(primaryStage);
            popUpWindow.setTitle("Game paused");
            popUpWindow.setScene(new Scene(root));
            popUpWindow.setOnCloseRequest(event -> Globals.isGamePaused = false);
            popUpWindow.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
