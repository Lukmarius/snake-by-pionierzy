package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public class SimplePowerUp extends GameEntity implements Interactable {

    public static final String NAME = "SimplePowerUp";
    private static final int LENGTH_GAIN = 2;

    private boolean willBeDestroyed = false;

    public SimplePowerUp(Game game) {
        super(game);
        this.setLook(NAME);
        game.getChildren().add(this);
        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (willBeDestroyed) return;
        willBeDestroyed = true;

        // Spawn new Simple PowerUp
        Globals.addGameObject(new SimplePowerUp(game));
        // Increase snake's body length
        snakeHead.addPart(LENGTH_GAIN);

        this.destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
