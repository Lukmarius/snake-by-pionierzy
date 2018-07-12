package com.codecool.snake;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public enum Wall {
    N (0, -1, new BoundingBox(0, -2, Game.GAME_WIDTH, 1)),
    E (-1, 0, new BoundingBox(Game.GAME_WIDTH + 1, 0, 1, Game.GAME_HEIGHT)),
    S (0, 1, new BoundingBox(0, Game.GAME_HEIGHT + 1, Game.GAME_WIDTH, 1)),
    W (1, 0, new BoundingBox(-2, 0, 1, Game.GAME_HEIGHT));

    private Point2D normalVector;
    private Bounds bounds;

    Wall(int normalX, int normalY, Bounds bounds) {
        this.normalVector = new Point2D(normalX, normalY).normalize();
        this.bounds = bounds;
    }

    public Point2D getNormalVector() {
        return normalVector;
    }

    public Bounds getBounds() {
        return bounds;
    }
}