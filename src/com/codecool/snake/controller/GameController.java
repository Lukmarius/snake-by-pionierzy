package com.codecool.snake.controller;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameController {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 780;
    public static final String GameFXML = "../view/fxml/game.fxml";

    @FXML private Rectangle playerHealthBar1;
    @FXML private Label playerScore1;
    @FXML private Label playerName1;

    @FXML private Button pauseBtn;
    @FXML private Pane gameContainer;

    private boolean isPaused = true;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    void showPauseMenu(ActionEvent event) {
        if (isPaused) {
            Globals.gameLoop.start();
        } else {
            Globals.gameLoop.stop();
        }
        isPaused = !isPaused;
    }

    void setUpGame(Game.GameMode gameMode) {
        Game game = new Game(gameMode);
        gameContainer.getChildren().add(game);

        initIndicators();

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

        isPaused = false;
        game.start();
    }

    void initIndicators() {
        playerHealthBar1.widthProperty().bind(Globals.player1.getHealthProperty());
//        playerScore1.textProperty().bind(Globals.player1.length.asString());
    }
}
