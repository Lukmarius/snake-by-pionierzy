package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private boolean snakeExists = false;

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                if (gameObject instanceof SnakeHead) {
                    snakeExists = true;
                }
                Animatable animObject = (Animatable) gameObject;
                animObject.step();
            }
        }

        if (Globals.gameObjects.size() > 0) {
            Globals.gameOver.gameOverIfSnakesAreDead(snakeExists);
        }

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
        snakeExists = false;
    }
}
