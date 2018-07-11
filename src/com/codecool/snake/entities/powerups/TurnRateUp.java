package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class TurnRateUp extends GameEntity implements Interactable {

    public TurnRateUp(Pane pane) {
        super(pane);
        setImage(Globals.snakeBody);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.GAME_WIDTH);
        setY(rnd.nextDouble() * Globals.GAME_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        destroy();
        snakeHead.turnFaster();

    }

    @Override
    public String getMessage() {
        return "You turn faster now";
    }
}
