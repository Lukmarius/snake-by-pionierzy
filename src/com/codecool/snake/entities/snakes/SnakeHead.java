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
    private static final float BASE_TURN_RATE = 2;

    private static int snakeCounter = 0;
    private int snakeID;

    private IntegerProperty health;
    private IntegerProperty length;
    private float speed;
    private float turnRate;
    private boolean invulnerable;
    private boolean faster;

    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int turnerUpDuration;
    private int invulnerabilityDuration;
    private List<GameEntity> tailElements;

    public static void resetSnakeCounter() {
        SnakeHead.snakeCounter = 0;
    }

    public SnakeHead(Game game, int xc, int yc) {
        super(game);
        this.snakeID = snakeCounter++;
        this.setX(xc);
        this.setY(yc);
        if (snakeID == 0){
            setImage(Globals.snakeHead);
        } else {
            setImage(Globals.snakeHead1);
        }
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

    public int getSnakeID() {
        return snakeID;
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
        double dir = getRotate();

        if (snakeID == 0) {
            if (Globals.leftKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir + turnRate;
            }
        } else if (snakeID == 1) {
            if (Globals.AKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.DKeyDown) {
                dir = dir + turnRate;
            }
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

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


    public void beFaster(float superTurnRate, int duration) {
        faster = true;
        turnRate = superTurnRate;
        turnerUpDuration = duration;
    }

    private void beFasterFor5Seconds() {
        if (turnerUpDuration > 0) {
            turnerUpDuration--;
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
            if (snakeID == 0){
                setImage(Globals.snakeHead);
            } else {
                setImage(Globals.snakeHead1);
            }
        }
    }
}
