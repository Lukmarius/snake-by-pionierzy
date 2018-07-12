package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class HealthPowerUp extends GameEntity implements Interactable {

    public static final String NAME = "HealthPowerUp";
    private static final int HEALTH_GAIN = 20;

    public HealthPowerUp(Game game) {
        super(game);
        this.setLook(NAME);
        game.getChildren().add(this);
        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(HEALTH_GAIN);
        this.destroy();
    }

    @Override
    public String getMessage() {
        return "Got +20 health";
    }
}
