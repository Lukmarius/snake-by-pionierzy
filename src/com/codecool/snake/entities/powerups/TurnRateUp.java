package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class TurnRateUp extends GameEntity implements Interactable {

    public TurnRateUp(Pane pane) {
        super(pane);
        this.setImage(Globals.turnRateUp);
        pane.getChildren().add(this);

        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        this.destroy();
        snakeHead.turnFaster();

    }

    @Override
    public String getMessage() {
        return "You turn faster now";
    }
}
