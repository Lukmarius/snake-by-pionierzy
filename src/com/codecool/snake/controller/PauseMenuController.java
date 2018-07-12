package com.codecool.snake.controller;

import com.codecool.snake.Globals;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseMenuController {

    public static final String PauseMenuFXML = "../view/fxml/pause_menu.fxml";

    private MainController mainController;
    private Stage popUpWindow;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setPopUpWindow(Stage popUpWindow) {
        this.popUpWindow = popUpWindow;
    }

    @FXML private Button exitBtn;
    @FXML private Button backBtn;
    @FXML private Button continueBtn;

    @FXML
    void exitGame() {
        Platform.exit();
    }

    @FXML
    void backToMainMenu() {
        mainController.showMainMenu();
    }

    @FXML
    void continueGame() {
        Globals.isGamePaused = false;
        popUpWindow.close();
    }

}
