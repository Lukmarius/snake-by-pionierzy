package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class TurnRateUp extends GameEntity implements Interactable {

    protected TurnRateUp(Pane pane) {
        super(pane);
    }

    @Override
    public void apply(SnakeHead snakeHead) {

    }

    @Override
    public String getMessage() {
        return null;
    }
}
