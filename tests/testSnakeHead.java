import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class testSnakeHead {

    @Test
    void testCounter() {
        List<SnakeHead> players = new ArrayList<>(2);

        Globals.init();
        Game game = new Game(Game.GameMode.TWO_PLAYERS);
        for (GameEntity entity : Globals.gameObjects) {
            if (entity instanceof SnakeHead) {
                players.add((SnakeHead) entity);
            }
        }
        assertEquals(0, players.get(0).getSnakeID());
        assertEquals(1, players.get(1).getSnakeID());
    }
}
