package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class TurnRateUp extends GameEntity implements Interactable {

    public static final String NAME = "TurnRateUp";

    private static final float SUPER_TURN_RATE = 6;
    private static final int POWER_UP_DURATION = 60 * 5;

    public TurnRateUp(Game game) {
        super(game);
        this.setLook(NAME);
        game.getChildren().add(this);
        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        this.destroy();
        snakeHead.turnFaster(SUPER_TURN_RATE, POWER_UP_DURATION);
    }

    @Override
    public String getMessage() {
        return "You turn faster now";
    }
}
