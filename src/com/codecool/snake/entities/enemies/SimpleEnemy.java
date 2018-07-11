package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private double direction;
    private static final int damage = 40;
    private int speed;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.GAME_WIDTH);
        setY(rnd.nextDouble() * Globals.GAME_HEIGHT);

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            bounce();
            setX(getX() + 5 * heading.getX());
            setY(getY() + 5 * heading.getY());
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    private void bounce() {
        Random rnd = new Random();

        direction = direction - rnd.nextDouble() * 90;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

    public double getDirection() {
        return direction;
    }
}
