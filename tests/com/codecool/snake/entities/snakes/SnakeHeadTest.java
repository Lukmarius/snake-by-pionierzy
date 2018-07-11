package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeHeadTest {



    SnakeHead snake1 = new SnakeHead(new Pane(),1,1);
    SnakeHead snake2 = new SnakeHead(new Pane(),1,1);






    @Test
    void namesAreDifferent() {
        assertNotEquals(1,2);
    }
}