package main;

import Helper.Render;
import GameObjects.Food;
import GameObjects.Snake;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel implements Runnable {

    // STATIC CONSTANTS
    public static final int TILE_SIZE = 48;
    public static final int ROWS = 11;
    public static final int COLS = 11;
    public static final int HEIGHT = ROWS * TILE_SIZE; // 576px
    public static final int WIDTH = COLS * TILE_SIZE; // 768px
    public static final int FPS = 2;

    // INITIALISATION
    Thread game;
    KeyManager key = new KeyManager();
    Snake snake = new Snake(key);
    Food food = new Food();

    // CONSTRUCTOR
    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(key);
    }

    // ABSTRACT METHOD FROM RUNNABLE INTERFACE
    @Override
    public void run() {
        long drawInterval = 1_000_000_000L / FPS;
        long nextDrawTime = System.nanoTime() + drawInterval;

        while (game != null) {
            update();
            repaint();
            if (snake.hasCollided()) {
                Render.showGameOver(snake.snakePoints.size());
                break;
            }
            nextDrawTime = Render.regulateFPS(nextDrawTime, drawInterval);
        }
    }

    // PUBLIC METHODS
    public void startGame() {
        game = new Thread(this);
        game.start();
    }

    public void update() {
        snake.update();
        if (snake.head.equals(food.location)) {
            food.update(snake);
            snake.increaseLength();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Render.drawGrid(g);
        Render.drawSnake(g, snake);
        Render.drawFood(g, food);
    }
}
