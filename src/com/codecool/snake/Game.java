package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.TurnRateUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public static final double GAME_WIDTH = 1000;
    public static final double GAME_HEIGHT = 700;
    public static final double EDGE_SHIFT_BR = 45;
    public static final double EDGE_SHIFT_TL = 30;

    public Game(GameMode gameMode) {
        Globals.init();
        SnakeHead.resetSnakeCounter();

        new SnakeHead(this, 500, 500);
        if (gameMode == GameMode.TWO_PLAYERS) {
            new SnakeHead(this, 200, 500);
        }

        for (int i = 0; i < 10; i++) {
            new SimpleEnemy(this);
        }

        new SimplePowerUp(this);
        new SimplePowerUp(this);
        new SimplePowerUp(this);
        new SimplePowerUp(this);

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
