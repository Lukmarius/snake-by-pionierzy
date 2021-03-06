package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerUp;
import com.codecool.snake.entities.powerups.Invulnerability;
import com.codecool.snake.entities.powerups.SpeedUpTurnUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;

import java.util.Random;

public class GameLoop extends AnimationTimer {
    private Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        boolean snakeExists = false;
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
        Random rnd = new Random();
        int spawnChances = rnd.nextInt(100000);
        if (spawnChances < 40) {
            new Invulnerability(game);
        } else if (spawnChances < 80) {
            new HealthPowerUp(game);
        } else if (spawnChances < 120) {
            new SpeedUpTurnUp(game);
        } else if (spawnChances < 160) {
            new SimpleEnemy(game);
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
