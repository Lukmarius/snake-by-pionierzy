package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.Wall;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import java.util.Random;

public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    public static final String NAME = "SimpleEnemy";
    private static final int DAMAGE = 40;
    private static final int SPEED = 2;

    private Point2D heading;
    private double direction;

    public SimpleEnemy(Game game) {
        super(game);
        this.setLook(NAME);
        game.getChildren().add(this);

        boolean isOverlapping;
        do {
            isOverlapping = !spawnEnemyValid();
        } while (isOverlapping);

        Random rnd = new Random();
        direction = rnd.nextDouble() * 360;
        this.setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
    }

    private boolean spawnEnemyValid() {
        double restrictedRange = 30;
        this.setLocation(Utils.getRndSpawnableLocation());
        for (SnakeHead snake : Globals.players) {
            Bounds snakeBounds = snake.getBoundsInParent();
            Bounds restricted = Utils.getResizedBounds(snakeBounds, restrictedRange);
            Bounds bounds = this.getBoundsInParent();
            if (bounds.intersects(restricted) && this.getTouchingWall(bounds) != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void step() {
        Point2D touchingWall = this.getTouchingWall(this.getBoundsInParent());
        if (touchingWall != null) {
            this.bounce(touchingWall);
            this.setX(this.getX() + SPEED * this.heading.getX());
            this.setY(this.getY() + SPEED * this.heading.getY());
        }

        this.setX(this.getX() + this.heading.getX());
        this.setY(this.getY() + this.heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        this.destroy();
    }

    private void bounce(Point2D wall) {
        Point2D reflectedHeading = heading.subtract(wall.multiply(2).multiply(wall.dotProduct(heading)));
        heading = reflectedHeading;
    }

    @Override
    public String getMessage() {
        return "40 DAMAGE";
    }
}
