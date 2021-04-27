package com.window;

import javax.swing.*;
import java.awt.event.KeyListener;

public class Frame extends JFrame {

    public Frame() {
        createFrame();
    }

    public void createFrame() {
        this.setTitle("Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void addPanel(JPanel panel) {
        this.add(panel);
    }

    public void addListeners(KeyListener keyListener) {
        this.addKeyListener(keyListener);
    }
}
