package com.codecool.snake.controller;

import com.codecool.snake.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    public static final double MENU_WIDTH = 600;
    public static final double MENU_HEIGHT = 400;
    public static final String MenuFXML = "../view/fxml/main_menu.fxml";

    @FXML private Button newGameBtn1p;
    @FXML private Button newGameBtn2p;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void startNewGame1p() {
        mainController.startNewGame(Game.GameMode.SINGLE_PLAYER);
    }

    @FXML
    private void startNewGame2p() {
        mainController.startNewGame(Game.GameMode.TWO_PLAYERS);
    }
}
