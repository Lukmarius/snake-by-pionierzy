package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class SpeedUpTurnUp extends GameEntity implements Interactable {

    public static final String NAME = "SpeedUpTurnUp";

    private static final float SUPER_TURN_RATE = 6;
    private static final float SUPER_SPEED = 3;
    private static final int POWER_UP_DURATION = 60 * 5;

    public SpeedUpTurnUp(Game game) {
        super(game);
        this.setLook(NAME);
        game.getChildren().add(this);
        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        this.destroy();
        snakeHead.beFaster(SUPER_SPEED, SUPER_TURN_RATE, POWER_UP_DURATION);
    }

    @Override
    public String getMessage() {
        return "You turn faster now";
    }
}
