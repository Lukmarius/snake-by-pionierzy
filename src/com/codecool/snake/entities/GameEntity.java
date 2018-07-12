package com.codecool.snake.entities;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Wall;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public abstract class GameEntity extends ImageView {

    protected Game game;

    protected GameEntity(Game game) {
        this.game = game;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            game.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected void setLocation(Point2D location) {
        this.setX(location.getX());
        this.setY(location.getY());
    }

    protected void setLook(String name) {
        this.setImage(Globals.images.get(name));
    }

    protected boolean isOutOfBounds() {
        if (getX() > Game.GAME_WIDTH || getX() < 0 ||
            getY() > Game.GAME_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    protected Point2D getTouchingWall(Bounds bounds) {
        if (bounds.intersects(Wall.N.getBounds())) {
            return Wall.N.getNormalVector();
        } else if (bounds.intersects(Wall.E.getBounds())) {
            return Wall.E.getNormalVector();
        } else if (bounds.intersects(Wall.S.getBounds())) {
            return Wall.S.getNormalVector();
        } else if (bounds.intersects(Wall.W.getBounds())) {
            return Wall.W.getNormalVector();
        }
        return null;
    }
}
