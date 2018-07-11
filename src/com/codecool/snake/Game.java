package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.TurnRateUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    private GameMode gameMode;

    public Game(GameMode gameMode) {
        Globals.init();
        this.gameMode = gameMode;

        new SnakeHead(this, 500, 500);
        if (gameMode == GameMode.TWO_PLAYERS) {
            new SnakeHead(this, 200, 500);
        }

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new TurnRateUp(this);
        new TurnRateUp(this);
        new TurnRateUp(this);

    }

    public void start() {
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public enum GameMode {
        SINGLE_PLAYER,
        TWO_PLAYERS
    }
}
