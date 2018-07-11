package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class HealthPowerup extends GameEntity implements Interactable {

    public HealthPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.GAME_WIDTH);
        setY(rnd.nextDouble() * Globals.GAME_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.healthProperty.add(10);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got +10 health";
    }
}
