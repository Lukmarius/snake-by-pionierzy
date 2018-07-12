package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerUp;
import com.codecool.snake.entities.powerups.Invulnerability;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedUpTurnUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public static final double GAME_WIDTH = 1000;
    public static final double GAME_HEIGHT = 700;
    public static final double EDGE_SHIFT_BR = 45;
    public static final double EDGE_SHIFT_TL = 30;

    private static final int INITIAL_ENEMIES_COUNT = 10;
    private static final int SIMPLE_POWERUPS_COUNT_MULTIPLIER = 3;

    public int playersCount;

    public Game(GameMode gameMode) {
        Globals.init();

        this.playersCount = gameMode.ordinal() + 1;

        // Spawn players
        double spacing = GAME_WIDTH / (playersCount + 1);
        for (int i = 0; i < playersCount; i++) {
            Globals.loadPlayerImages(i);
            new SnakeHead(this, i, spacing * (i + 1), 500);
        }

        // Spawn enemies
        for (int i = 0; i < INITIAL_ENEMIES_COUNT; i++) {
            new SimpleEnemy(this);
        }

        // Spawn simple power-ups
        int simplePowerUpsCount = SIMPLE_POWERUPS_COUNT_MULTIPLIER * playersCount;
        for (int i = 0; i < simplePowerUpsCount; i++) {
            new SimplePowerUp(this);
        }

        // Spawn one of each other power-ups
        new HealthPowerUp(this);
        new SpeedUpTurnUp(this);
        new Invulnerability(this);
    }

    public void start() {
        Globals.gameLoop = new GameLoop(this);
        Globals.gameLoop.start();
    }

    public enum GameMode {
        SINGLE_PLAYER,
        TWO_PLAYERS
    }
}
