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
    private boolean snakeExists = false;
    private Game game;
    public GameLoop(Game game) {
        this.game = game;
    }
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
        Random rnd = new Random();
        int spawnChances = rnd.nextInt(100000);
        if (spawnChances < 40) {
            Globals.addGameObject(new Invulnerability(game));
        }
        else if (spawnChances < 80) {
            Globals.addGameObject(new HealthPowerUp(game));
        }
        else if (spawnChances < 120) {
            Globals.addGameObject(new SpeedUpTurnUp(game));
        }
        else if (spawnChances < 160) {
            Globals.addGameObject(new SimpleEnemy(game));
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
        snakeExists = false;
    }
}
