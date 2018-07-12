package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class SpeedUpTurnUp extends GameEntity implements Interactable {

    public static final String NAME = "SpeedUpTurnUp";

    public SpeedUpTurnUp(Game game) {
        super(game);
        this.setLook(NAME);
        game.getChildren().add(this);
        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        this.destroy();
        snakeHead.beFaster();
    }

    @Override
    public String getMessage() {
        return "You turn faster now";
    }
}
