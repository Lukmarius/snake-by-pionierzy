package com.codecool.snake.entities;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected void setLocation(Point2D location) {
        this.setX(location.getX());
        this.setY(location.getY());
    }

    protected boolean isOutOfBounds() {
        if (getX() > Game.GAME_WIDTH || getX() < 0 ||
            getY() > Game.GAME_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }
}
