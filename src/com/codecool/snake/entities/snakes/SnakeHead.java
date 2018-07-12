package com.codecool.snake.entities.snakes;


import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.Invulnerability;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class SnakeHead extends GameEntity implements Animatable {

    private static final int INITIAL_LENGTH = 4;
    private static final int INITIAL_HEALTH = 100;
    private static final float BASE_SPEED = 2;
    private static final float BASE_TURN_RATE = 3f;

    private int snakeID;
    private String name;

    private IntegerProperty health;
    private IntegerProperty length;
    private float speed;
    private float turnRate;
    private boolean invulnerable;
    private boolean faster;
    private int fasterDuration;
    private int invulnerabilityDuration;

    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private List<GameEntity> tailElements;

    public SnakeHead(Game game, int snakeID, double xc, double yc) {
        super(game);
        this.snakeID = snakeID;
        this.name = "snakeHead" + snakeID;

        this.setX(xc);
        this.setY(yc);
        this.setLook(this.name);
        game.getChildren().add(this);

        health = new SimpleIntegerProperty(INITIAL_HEALTH);

        tail = this;
        tailElements = new ArrayList<>();
        length = new SimpleIntegerProperty(INITIAL_LENGTH);
        addPart(INITIAL_LENGTH);

        this.speed = BASE_SPEED;
        this.turnRate = BASE_TURN_RATE;

        Globals.addPlayer(this);
    }

    public int getHealth() {
        return health.get();
    }

    public IntegerProperty getHealthProperty() {
        return health;
    }

    public IntegerProperty getLengthProperty() {
        return length;
    }

    public void step() {
        double dir = this.getRotate();
        if (Globals.leftKeysDown.get(snakeID)) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeysDown.get(snakeID)) {
            dir = dir + turnRate;
        }

        // set rotation and position
        this.setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        this.setX(this.getX() + heading.getX());
        this.setY(this.getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeHead && !entity.equals(this) &&
                        !invulnerable && !((SnakeHead) entity).invulnerable) {
                    Globals.gameLoop.stop();
                    Globals.gameOver.showPopUp();
                } else if (entity instanceof Interactable && !invulnerable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
                if (invulnerable && !(entity instanceof SimpleEnemy) && entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
        if (faster) beFasterFor5Seconds();
        if (invulnerable) makeInvulnerableFor5sec();

        // check for game over condition
        if (this.getTouchingWall(this.getBoundsInParent()) != null || getHealth() <= 0) {
            System.out.println("Snake Dead");
            this.health.setValue(0);
            for (GameEntity tail : tailElements) {
                tail.destroy();
            }
            destroy();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(game, tail, snakeID);
            tailElements.add(newPart);
            tail = newPart;
        }
        length.setValue(tailElements.size());
    }

    public void changeHealth(int diff) {
        health.setValue(Math.min(100, getHealth() + diff));
    }


    public void switchInvulnerableOn(int duration) {
        invulnerabilityDuration = duration;
        invulnerable = true;
        this.setLook(Invulnerability.NAME);
    }


    public void beFaster(float superSpeed, float superTurnRate, int duration) {
        faster = true;
        turnRate = superTurnRate;
        speed = superSpeed;
        fasterDuration = duration;
    }

    private void beFasterFor5Seconds() {
        if (fasterDuration > 0) {
            fasterDuration--;
        } else {
            turnRate = BASE_TURN_RATE;
            speed = BASE_SPEED;
            faster = false;
        }
    }

    private void makeInvulnerableFor5sec() {
        if (invulnerabilityDuration > 0) {
            invulnerabilityDuration--;
        } else {
            invulnerable = false;
            this.setLook(this.name);
        }
    }
}
