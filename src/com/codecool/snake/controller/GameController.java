package com.codecool.snake.controller;

import com.codecool.snake.Game;
import com.codecool.snake.GameOver;
import com.codecool.snake.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameController {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 780;
    public static final String GameFXML = "../view/fxml/game.fxml";

    @FXML private Button pauseBtn;
    @FXML private Pane gameContainer;
    @FXML private HBox playerDataContainer;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    void setUpGame(Game.GameMode gameMode) {
        Game game = new Game(gameMode);
        gameContainer.getChildren().add(game);

        int players = gameMode.ordinal();
        for (int i = 0; i <= players; i++) {
            loadPlayerDataDisplay(i);
        }

        Globals.gameOver = new GameOver();
        Globals.gameOver.setMainController(mainController);

        Scene scene = game.getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case A: Globals.AKeyDown  = true; break;
                case D: Globals.DKeyDown  = true; break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case A: Globals.AKeyDown  = false; break;
                case D: Globals.DKeyDown  = false; break;
            }
        });

        game.start();
    }

    private void loadPlayerDataDisplay(int playerId) {
        try {
            FXMLLoader playerLoader = new FXMLLoader(getClass().getResource(PlayerDataController.PlayerDataFXML));
            VBox playerData = playerLoader.load();
            PlayerDataController playerDataController = playerLoader.getController();
            playerDataController.setUp(playerId);
            playerDataContainer.getChildren().add(playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pauseGame() {
        Globals.isGamePaused = true;
        Globals.gameLoop.stop();
        mainController.showPauseMenu();
        if (!Globals.isGamePaused) {
            Globals.gameLoop.start();
        }
    }

}
