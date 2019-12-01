package ru.focusstart.view;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {
    //private JFrame jFrame;

    public Window(String title, int width, int height) {
        //this.jFrame = new JFrame(title);
        super(title);
        setSize(width, height);
       // this.setBounds(border.x, border.y, border.width, border.height);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        //this.pack();
    }

    public abstract boolean chek(String checkString);
}
