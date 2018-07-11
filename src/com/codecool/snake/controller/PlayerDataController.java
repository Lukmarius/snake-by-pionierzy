package com.codecool.snake.controller;

import com.codecool.snake.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;


public class PlayerDataController {

    public static final String PlayerDataFXML = "../view/fxml/player_data.fxml";

    @FXML private Label playerName;

    @FXML private Label playerScore;

    @FXML private Rectangle playerHealthBar;

    void setUp(int playerId) {
        this.playerName.setText("Player " + String.valueOf(playerId + 1));
        playerHealthBar.widthProperty().bind(Globals.players.get(playerId).getHealthProperty());
        playerScore.textProperty().bind(Globals.players.get(playerId).getLengthProperty().asString());
    }
}
