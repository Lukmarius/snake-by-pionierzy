package com.codecool.snake.controller;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
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

    private Game game;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    void setUpGame(Game.GameMode gameMode) {
        this.game = new Game(gameMode);
        gameContainer.getChildren().add(game);

        for (int i = 0; i < this.game.playersCount; i++) {
            loadPlayerDataDisplay(i);
        }

        Globals.gameOver = new GameOver();
        Globals.gameOver.setMainController(mainController);

        Scene scene = game.getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeysDown.set(0, true); break;
                case RIGHT: Globals.rightKeysDown.set(0, true); break;
                case A: Globals.leftKeysDown.set(1, true); break;
                case D: Globals.rightKeysDown.set(1, true); break;
                case ESCAPE: pauseGame(); break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeysDown.set(0, false); break;
                case RIGHT: Globals.rightKeysDown.set(0, false); break;
                case A: Globals.leftKeysDown.set(1, false); break;
                case D: Globals.rightKeysDown.set(1, false); break;
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
