package com.codecool.snake;

import javafx.geometry.Point2D;

import java.util.Random;

public class Utils {

    public static final Point2D N_WALL = new Point2D(0, -1).normalize();
    public static final Point2D E_WALL = new Point2D(-1, 0).normalize();
    public static final Point2D S_WALL = new Point2D(0, 1).normalize();
    public static final Point2D W_WALL = new Point2D(1, 0).normalize();

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static Point2D getRndSpawnableLocation() {

        // Add shift in order not to spawn too close to the edges
        Random rnd = new Random();
        double x = rnd.nextDouble() * (Game.GAME_WIDTH - Game.EDGE_SHIFT) + Game.EDGE_SHIFT;
        double y = rnd.nextDouble() * (Game.GAME_HEIGHT - Game.EDGE_SHIFT) + Game.EDGE_SHIFT;

        return new Point2D(x, y);
    }
}
