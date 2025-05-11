package main;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel implements Runnable {

    public static final int TILE_SIZE = 48;
    public static final int ROWS = 12;
    public static final int COLS = 12;
    public static final int HEIGHT = ROWS * TILE_SIZE; // 576px
    public static final int WIDTH = COLS * TILE_SIZE; // 768px

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.PINK);
        this.setFocusable(true);
    }


    @Override
    public void run() {

    }
}
