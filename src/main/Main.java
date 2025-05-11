package main;

import Helper.Render;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Snake Game");
        Screen screen = new Screen();
        window.add(screen);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Render.loadSprites();
        screen.startGame();
    }
}
