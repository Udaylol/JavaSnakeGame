package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        Screen screen = new Screen();
        window.add(screen);
        window.pack();
        window.setTitle("Snake Game");
        window.setVisible(true);
    }
}
