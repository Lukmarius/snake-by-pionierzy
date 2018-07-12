package com.codecool.snake.controller;

import com.codecool.snake.Globals;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOver {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void showPopUp() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UTILITY);
        modalStage.initOwner(mainController.getPrimaryStage());

        Button replayBtn = new Button("Play again");
        replayBtn.setOnAction(event -> {
            modalStage.close();
            mainController.showMainMenu();
        });
        Button exitBtn = new Button("Exit game");
        exitBtn.setOnAction(event -> Platform.exit());

        exitBtn.getStyleClass().add("backBtn");
        replayBtn.getStyleClass().add("menuBtn");

        HBox hBox = new HBox(exitBtn, replayBtn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Text t = new Text("GAME OVER");
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFont(Font.font("Arial",80));

        t.setFill(Paint.valueOf("Red"));
        t.setStroke(Paint.valueOf("Black"));
        VBox vbox = new VBox(t, hBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(15);
        vbox.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        modalStage.setScene(new Scene(vbox));
        modalStage.show();
    }


    public void gameOverIfSnakesAreDead (boolean exists){
        if (!exists){
            Globals.gameLoop.stop();
            showPopUp();
        }
    }
}
