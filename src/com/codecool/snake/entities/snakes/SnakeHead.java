package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final int INITIAL_LENGTH = 4;

    private static final float speed = 2;
    private static final float baseTurnRate = 2;
    private static final float superTurnRate = 6;
    private float actualTurnRate = baseTurnRate;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    public IntegerProperty healthProperty;
    public IntegerProperty length;
    private boolean invulnerable;
    private boolean turningFaster;
    private static int snakeCounter = 0;
    private int snakeID;
    private int turnerUpDuration;
    private int involnerabiltyDuration = 60*5;


    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        healthProperty = new SimpleIntegerProperty(health);
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(INITIAL_LENGTH);
        length = new SimpleIntegerProperty(INITIAL_LENGTH);
        Globals.player1 = this;
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - actualTurnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + actualTurnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
        if(turningFaster){
           turnFasterFor5sec();
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        healthProperty.set(health);
    }



    public void switchInvulnerable() {
        this.invulnerable = !invulnerable;
    }


    public void turnFaster(){
        turningFaster = true;
        actualTurnRate = superTurnRate;
        turnerUpDuration = 60*5;
    }

    private void turnFasterFor5sec(){
        if(turnerUpDuration>0){
            turnerUpDuration--;
            System.out.println(turnerUpDuration);
        }else{
            actualTurnRate = baseTurnRate;
            turningFaster = false;
        }
    }
}
