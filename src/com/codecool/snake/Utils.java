package com.codecool.snake;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

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
        double x = ThreadLocalRandom.current().nextDouble(Game.EDGE_SHIFT_TL, Game.GAME_WIDTH - Game.EDGE_SHIFT_BR);
        double y = ThreadLocalRandom.current().nextDouble(Game.EDGE_SHIFT_TL, Game.GAME_HEIGHT - Game.EDGE_SHIFT_BR);

        return new Point2D(x, y);
    }

    public static Bounds getResizedBounds(Bounds bounds, double shift) {
        return new BoundingBox(
            bounds.getMinX() - shift,
            bounds.getMinY() - shift,
            bounds.getWidth() + shift * 2,
            bounds.getHeight() + shift * 2);
    }
}
