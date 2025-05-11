package GameObjects;

import main.KeyManager;
import main.Screen;

import java.util.LinkedList;

import static main.Screen.TILE_SIZE;

public class Snake {

    // PUBLIC DATA MEMBERS
    public Point head;
    public LinkedList<Point> snakePoints;
    public char direction;
    public int speed;

    // PRIVATE DATA MEMBERS
    private final KeyManager key;

    // CONSTRUCTOR
    public Snake(KeyManager key) {
        int x = (Screen.COLS % 2 == 0) ? Screen.WIDTH / 2 : Screen.WIDTH / 2 - TILE_SIZE / 2;
        int y = (Screen.ROWS % 2 == 0) ? Screen.HEIGHT / 2 : Screen.HEIGHT / 2 - TILE_SIZE / 2;
        this.head = new Point(x, y);
        snakePoints = new LinkedList<>();
        snakePoints.add(head);
        this.key = key;
        direction = 'X';
        speed = TILE_SIZE;
    }

    // PUBLIC METHODS
    public void update() {
        if (key.upPressed) {
            direction = 'U';
        } else if (key.downPressed) {
            direction = 'D';
        } else if (key.leftPressed) {
            direction = 'L';
        } else if (key.rightPressed) {
            direction = 'R';
        }
        for (int i = snakePoints.size() - 1; i > 0; i--) {
            snakePoints.get(i).x = snakePoints.get(i - 1).x;
            snakePoints.get(i).y = snakePoints.get(i - 1).y;
        }
        move();
    }

    public boolean bodyContains(Point point) {
        for (int i = 1; i < snakePoints.size(); i++) {
            Point snakePoint = snakePoints.get(i);
            if (snakePoint.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCollided() {
        if (head.x > Screen.WIDTH - TILE_SIZE || head.x < 0 ) {
            return true;
        }
        if (head.y > Screen.HEIGHT - TILE_SIZE || head.y < 0) {
            return true;
        }
        return this.bodyContains(this.head);
    }

    public void increaseLength() {
        int newX = head.x, newY = head.y;

        if (snakePoints.size() > 1) {
            Point last = snakePoints.get(snakePoints.size() - 1);
            Point secondLast = snakePoints.get(snakePoints.size() - 2);
            int dx = last.x - secondLast.x;
            int dy = last.y - secondLast.y;
            newX = last.x + dx;
            newY = last.y + dy;
        } else {
            switch (direction) {
                case 'U' -> newY += TILE_SIZE;
                case 'D' -> newY -= TILE_SIZE;
                case 'L' -> newX += TILE_SIZE;
                case 'R' -> newX -= TILE_SIZE;
            }
        }
        snakePoints.add(new Point(newX, newY));
    }

    // PRIVATE METHODS
    private void move() {
        switch (direction) {
            case 'U' -> head.y -= speed;
            case 'D' -> head.y += speed;
            case 'L' -> head.x -= speed;
            case 'R' -> head.x += speed;
        }
    }
}
