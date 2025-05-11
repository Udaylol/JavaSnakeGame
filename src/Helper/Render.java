package Helper;

import GameObjects.Food;
import GameObjects.Point;
import GameObjects.Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static main.Screen.*;

public class Render {

    // ASSETS
    public static BufferedImage[] snakeHeads = new BufferedImage[4];
    public static BufferedImage snakeBody;
    public static BufferedImage apple;

    private Render(){
        // CANNOT BE INSTANTIATED
    }

    public static void loadSprites() {
        try {
            snakeHeads[0] = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/snakeHead0.png")));;
            snakeHeads[1] = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/snakeHead1.png")));;
            snakeHeads[2] = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/snakeHead2.png")));;
            snakeHeads[3] = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/snakeHead3.png")));;
            snakeBody = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/snakeBody.png")));
            apple = ImageIO.read(Objects.requireNonNull(Render.class.getResourceAsStream("/apple.png")));
        } catch (Exception e) {
            System.err.println("Failed to load sprites");
        }
    }

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
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 1; i < snake.snakePoints.size(); i++) {
            Point point = snake.snakePoints.get(i);
            g2.drawImage(snakeBody, point.x, point.y, TILE_SIZE, TILE_SIZE, null);
        }
        BufferedImage snakeHead = switch (snake.direction) {
            case 'U' -> snakeHeads[0];
            case 'D' -> snakeHeads[2];
            case 'L' -> snakeHeads[1];
            case 'R' -> snakeHeads[3];
            default -> null;
        };
        g2.drawImage(snakeHead, snake.head.x, snake.head.y, TILE_SIZE, TILE_SIZE, null);
    }

    public static void drawFood(Graphics g, Food food) {
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(apple, food.location.x, food.location.y, TILE_SIZE, TILE_SIZE, null);
    }

    public static long regulateFPS(long nextDrawTime, long drawInterval) {
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

    public static void showGameOver(int score) {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true); // Keep on top
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridBagLayout());

        Box verticalBox = Box.createVerticalBox();

        JLabel label1 = new JLabel("Game Over", SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 50));
        label1.setForeground(Color.RED);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("Score: " + score, SwingConstants.CENTER);
        label2.setFont(new Font("Arial", Font.PLAIN, 30));
        label2.setForeground(Color.LIGHT_GRAY);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        verticalBox.add(label1);
        verticalBox.add(Box.createVerticalStrut(20)); // Space between labels
        verticalBox.add(label2);

        panel.add(verticalBox);
        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
