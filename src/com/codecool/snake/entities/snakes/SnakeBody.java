package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable {

    private GameEntity parent;
    private Queue<Vec2d> history = new LinkedList<>();
    private static final int HISTORY_SIZE = 10;

    public SnakeBody(Game game, GameEntity parent, int snakeID) {
        super(game);
        this.parent = parent;
        this.setLook("snakeBody" + snakeID);

        // setLocation it visually below the current tail
        List<Node> children = game.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        this.setX(xc);
        this.setY(yc);
        for (int i = 0; i < HISTORY_SIZE; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    public void step() {
        Vec2d pos = history.poll(); // remove the oldest item from the history
        this.setX(pos.x);
        this.setY(pos.y);
        history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history
    }

}
