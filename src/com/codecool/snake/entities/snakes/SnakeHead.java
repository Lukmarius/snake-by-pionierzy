package com.codecool.snake.entities.snakes;


import com.codecool.snake.GameLoop;
import com.codecool.snake.GameOver;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SnakeHead extends GameEntity implements Animatable {

    private static final int INITIAL_LENGTH = 4;
    private static final int INITIAL_HEALTH = 100;

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.

    private IntegerProperty health;
    private IntegerProperty length;

    private boolean isInvulnerable;
    private static int snakeCounter = 0;
    private int snakeID;
    private List<GameEntity> tailElements;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        this.snakeID = snakeCounter++;

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
                dir = dir - turnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir + turnRate;
            }
        }
        else if (snakeID == 1) {
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
                if (entity instanceof SnakeHead && !entity.equals(this)) {
                    Globals.gameLoop.stop();
                }
                else if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || getHealth() <= 0) {
            System.out.println("Snake Dead");
            for (GameEntity tail: tailElements) {
                tail.destroy();
            }
            destroy();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tailElements.add(newPart);
            tail = newPart;
        }
        length.setValue(tailElements.size());
    }

    public void changeHealth(int diff) {
        health.setValue(getHealth() + diff);
    }
}
