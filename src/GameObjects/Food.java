package GameObjects;

import main.Screen;

import java.util.Random;
import static main.Screen.TILE_SIZE;

public class Food {
    // PUBLIC DATA MEMBERS
    public Point location;

    // CONSTRUCTOR
    public Food() {
        int x = Screen.WIDTH - 2 * TILE_SIZE;
        int y = Screen.HEIGHT / 2 - TILE_SIZE / 2;
        this.location = new Point(x, y);
    }

    // PUBLIC METHODS
    public void update(Snake snake) {
        while (true) {
            Point foodPoint = generateRandomPoint();
            boolean overlap = snake.bodyContains(foodPoint);
            if (!overlap) {
                location = foodPoint;
                break;
            }
        }
    }

    // PRIVATE METHODS
    private Point generateRandomPoint() {
        Random random = new Random();
        int newRow = random.nextInt(Screen.ROWS);
        int newCol = random.nextInt(Screen.COLS);
        int newX = newCol * TILE_SIZE;
        int newY = newRow * TILE_SIZE;
        return new Point(newX, newY);
    }
}
