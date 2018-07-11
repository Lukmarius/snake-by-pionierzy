package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    private static final int LENGTH_GAIN = 2;

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.GAME_WIDTH);
        setY(rnd.nextDouble() * Globals.GAME_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(LENGTH_GAIN);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
