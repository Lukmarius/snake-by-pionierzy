package com.codecool.snake.entities.snakes;


import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.Invulnerability;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SnakeHead extends GameEntity implements Animatable {

    private static final int INITIAL_LENGTH = 4;
    private static final int INITIAL_HEALTH = 100;

    private static final int POWER_UP_DURATION = 60 * 5;
    private static final float SPEED = 2;
    private static final float BASE_TURN_RATE = 2;
    private static final float SUPER_TURN_RATE = 6;
    private static int snakeCounter = 0;
    public IntegerProperty health;
    public IntegerProperty length;
    private float actualTurnRate = BASE_TURN_RATE;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private boolean invulnerable;
    private boolean turningFaster;
    private int snakeID;
    private int turnerUpDuration;
    private int involnerabiltyDuration;
    private List<GameEntity> tailElements;

    public static void resetSnakeCounter() {
        SnakeHead.snakeCounter = 0;
    }

    public SnakeHead(Game game, int xc, int yc) {
        super(game);
        this.snakeID = snakeCounter++;
        setX(xc);
        setY(yc);
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
                dir = dir - actualTurnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir + actualTurnRate;
            }
        } else if (snakeID == 1) {
            if (Globals.AKeyDown) {
                dir = dir - actualTurnRate;
            }
            if (Globals.DKeyDown) {
                dir = dir + actualTurnRate;
            }
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, SPEED);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeHead && !entity.equals(this) && !invulnerable && !((SnakeHead) entity).invulnerable) {
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
        if (turningFaster) turnFasterFor5sec();
        if (invulnerable) makeInvulnerableFor5sec();

        // check for game over condition
        if (isOutOfBounds() || getHealth() <= 0) {
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
        health.setValue(getHealth() + diff);
    }


    public void switchInvulnerableOn() {
        involnerabiltyDuration = POWER_UP_DURATION;
        this.invulnerable = true;
        this.setLook(Invulnerability.NAME);
    }


    public void turnFaster() {
        turningFaster = true;
        actualTurnRate = SUPER_TURN_RATE;
        turnerUpDuration = POWER_UP_DURATION;
    }

    private void turnFasterFor5sec() {
        if (turnerUpDuration > 0) {
            turnerUpDuration--;
        } else {
            actualTurnRate = BASE_TURN_RATE;
            turningFaster = false;
        }
    }

    private void makeInvulnerableFor5sec() {
        if (involnerabiltyDuration > 0) {
            involnerabiltyDuration--;
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
