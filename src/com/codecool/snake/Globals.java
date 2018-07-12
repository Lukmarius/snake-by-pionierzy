package com.codecool.snake;

import com.codecool.snake.controller.GameOver;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerUp;
import com.codecool.snake.entities.powerups.Invulnerability;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedUpTurnUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;

import java.util.*;

// class for holding all static stuff
public class Globals {

    private static final int MAX_PLAYERS = Game.GameMode.TWO_PLAYERS.ordinal() + 1;
    public static HashMap<String, Image> images = new HashMap<>();
    public static List<Boolean> leftKeysDown;
    public static List<Boolean> rightKeysDown;

    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static List<SnakeHead> players;

    public static boolean isGamePaused;

    public static GameOver gameOver;

    public static void init() {
        loadImages();
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        gameLoop = null;
        isGamePaused = false;
        players = new ArrayList<>(MAX_PLAYERS);
        leftKeysDown = Arrays.asList(false, false);
        rightKeysDown = Arrays.asList(false, false);
    }

    private static void loadImages() {
        images.put(SimplePowerUp.NAME, new Image("powerup_berry.png"));
        images.put(HealthPowerUp.NAME, new Image("health_powerup.png"));
        images.put(SpeedUpTurnUp.NAME, new Image("turnrate_powerup.png"));
        images.put(Invulnerability.NAME, new Image("invulnerability_powerup.png"));
        images.put(SimpleEnemy.NAME, new Image("angry_enemy.png"));
    }

    public static void loadPlayerImages(int playerId) {
        images.put("snakeHead" + playerId, new Image("snake_head" + (playerId + 1) + ".png"));
        images.put("snakeBody" + playerId, new Image("snake_body" + (playerId + 1) + ".png"));
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    public static void addPlayer(SnakeHead player) {
        players.add(player);
    }
}
