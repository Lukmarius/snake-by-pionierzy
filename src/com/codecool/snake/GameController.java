package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    public static final String GameFXML = "view/fxml/game.fxml";

    @FXML private Rectangle playerHealthBar1;
    @FXML private Rectangle playerHealthBarLost1;
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
        Game game = new Game();
        gameContainer.getChildren().add(game);
        initHealthBar();
        isPaused = false;
        game.start();
    }

    void initHealthBar() {
        System.out.println(playerHealthBar1.widthProperty());
        playerHealthBar1.widthProperty().bind(Globals.player1.healthProperty);
    }
}
