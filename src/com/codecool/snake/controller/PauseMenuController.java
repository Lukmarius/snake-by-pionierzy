package com.codecool.snake.controller;

import com.codecool.snake.Globals;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PauseMenuController {

    public static final String PauseMenuFXML = "../view/fxml/pause_menu.fxml";

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private Button exitBtn;
    @FXML private Button backBtn;

    @FXML
    void exitGame() {
        Platform.exit();
    }

    @FXML
    void backToMainMenu() {
        mainController.showMainMenu();
    }

}
