package com.codecool.snake;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOver {
    public void showPopUp() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UNDECORATED);

        Button replayBtn = new Button("Play again");
        //replayBtn.setOnAction(event -> Main.restart()); -------RESTART
        Button exitBtn = new Button("Exit game");
        exitBtn.setOnAction(event -> Platform.exit());

        VBox vbox = new VBox(new Text("You won!"), replayBtn, exitBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(15);

        modalStage.setScene(new Scene(vbox));
        modalStage.show();
    }
}
