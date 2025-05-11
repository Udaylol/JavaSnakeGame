package main;

import java.awt.*;

import static main.Screen.*;

public class Rendering {
    public static void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = TILE_SIZE; i < WIDTH; i += TILE_SIZE) {
            g.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = TILE_SIZE; i < HEIGHT; i += TILE_SIZE) {
            g.drawLine(0, i, WIDTH, i);
        }
    }

    public static void drawSnake(Graphics g, Snake snake) {
        g.setColor(Color.BLACK);
        g.drawRect(snake.x, snake.y, TILE_SIZE, TILE_SIZE);
    }
}
