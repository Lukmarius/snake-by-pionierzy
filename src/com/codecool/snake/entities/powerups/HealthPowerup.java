package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class HealthPowerup extends GameEntity implements Interactable {

    private static final int HEALTH_GAIN = 10;

    public HealthPowerup(Pane pane) {
        super(pane);
        this.setImage(Globals.healthPowerUp);
        pane.getChildren().add(this);

        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(HEALTH_GAIN);
        this.destroy();
    }

    @Override
    public String getMessage() {
        return "Got +10 health";
    }
}
