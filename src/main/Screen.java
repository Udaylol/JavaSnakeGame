package main;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel implements Runnable {

    // STATIC CONSTANTS
    public static final int TILE_SIZE = 48;
    public static final int ROWS = 12;
    public static final int COLS = 12;
    public static final int HEIGHT = ROWS * TILE_SIZE; // 576px
    public static final int WIDTH = COLS * TILE_SIZE; // 768px
    private static final int FPS = 30;

    // INITIALISATION
    Thread game;
    KeyManager key = new KeyManager();
    Snake snake = new Snake(key, this);

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(key);
    }

    @Override
    public void run() {
        long drawInterval = 1_000_000_000L / FPS;
        long nextDrawTime = System.nanoTime() + drawInterval;

        while (game != null) {
            update();
            repaint();
            nextDrawTime = regulateFPS(nextDrawTime, drawInterval);
        }
    }
    public void startGame() {
        game = new Thread(this);
        game.start();
    }

    public void update() {
        snake.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rendering.drawGrid(g);
        Rendering.drawSnake(g, snake);
    }
    private static long regulateFPS(long nextDrawTime, long drawInterval) {
        long sleepTime = (nextDrawTime - System.nanoTime()) / 1_000_000;
        if (sleepTime < 0) {
            sleepTime = 0;
        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return nextDrawTime;
        }
        return nextDrawTime + drawInterval;
    }
}
