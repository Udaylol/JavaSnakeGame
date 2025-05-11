package main;

import java.awt.*;

import static main.Screen.TILE_SIZE;

public class Snake {
    public Rectangle head;
    public int x;
    public int y;
    public char direction;
    public int speed;

    private KeyManager key;
    private Screen screen;

    public Snake(KeyManager key, Screen screen) {
        this.x = Screen.WIDTH / 2;
        this.y = Screen.HEIGHT / 2;
        this.key = key;
        this.screen = screen;
        head = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
        direction = 'R';
        speed = 4;
    }

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
        switch (direction) {
            case 'U' -> this.y -= speed;
            case 'D' -> this.y += speed;
            case 'L' -> this.x -= speed;
            case 'R' -> this.x += speed;
        }
    }
}
