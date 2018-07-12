package com.codecool.snake;

import com.codecool.snake.controller.GameOver;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerUp;
import com.codecool.snake.entities.powerups.Invulnerability;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.TurnRateUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;

import java.util.*;

// class for holding all static stuff
public class Globals {

    public static HashMap<String, Image> images = new HashMap<>();

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image snakeHead1 = new Image("snake_head1.png");
    public static Image snakeBody1 = new Image("snake_body1.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean AKeyDown;
    public static boolean DKeyDown;
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
        players = new ArrayList<>(2);
        isGamePaused = false;
        leftKeyDown = false;
        rightKeyDown = false;
    }

    private static void loadImages() {
        images.put(SimplePowerUp.NAME, new Image("powerup_berry.png"));
        images.put(HealthPowerUp.NAME, new Image("health_powerup.png"));
        images.put(TurnRateUp.NAME, new Image("turnrate_powerup.png"));
        images.put(Invulnerability.NAME, new Image("invulnerability_powerup.png"));
        images.put(SimpleEnemy.NAME, new Image("simple_enemy.png"));
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
