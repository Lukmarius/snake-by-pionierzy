package com.codecool.snake.entities.powerups;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class SimplePowerup extends GameEntity implements Interactable {

    private static final int LENGTH_GAIN = 2;
    private boolean willBeDestroyed = false;

    public SimplePowerup(Pane pane) {
        super(pane);
        this.setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        this.setLocation(Utils.getRndSpawnableLocation());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (willBeDestroyed) return;
        willBeDestroyed = true;

        // Spawn new Simple PowerUp
        Globals.addGameObject(new SimplePowerup(pane));
        // Increase snake's body length
        snakeHead.addPart(LENGTH_GAIN);

        this.destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
