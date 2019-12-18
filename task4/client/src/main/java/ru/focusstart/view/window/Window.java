package ru.focusstart.view.window;

import javax.swing.*;

public abstract class Window extends JFrame {

    public Window(String title, int width, int height) {
        super(title);
        setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}