package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOver {
    public void showPopUp() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.DECORATED);

        Button replayBtn = new Button("Play again");
        //replayBtn.setOnAction(event -> Main.restart()); -------RESTART
        Button exitBtn = new Button("Exit game");
        exitBtn.setOnAction(event -> Platform.exit());

        Text t = new Text("GAME OVER");
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFont(Font.font("Arial",80));

        t.setFill(Paint.valueOf("Red"));
        t.setStroke(Paint.valueOf("Black"));
        VBox vbox = new VBox(t, replayBtn, exitBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(15);

        modalStage.setScene(new Scene(vbox));
        modalStage.show();
    }

    public void gameOverIfAnotherSnakeIsDead(SnakeHead snake){
        int count = 0;
        for(GameEntity gameObject : Globals.gameObjects){
            if (gameObject instanceof SnakeHead && !gameObject.equals(snake)) {
                count++;
            }
        }
        if (count<=0){
            Globals.gameLoop.stop();
            showPopUp();
        }
    }
}
