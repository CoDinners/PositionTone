package prj.codinners.positiontone.root;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display {
    private int width, height;
    private String caption;
    private Root root;

    private JFrame frame;
    private MouseManager mouseManager;
    private Canvas canvas;

    Display(int width, int height, String caption, Root root) {
        this.width = width;
        this.height = height;
        this.caption = caption;
        this.root = root;

        init();
    }

    private void init() {
        frame = new JFrame(caption);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);
        canvas.addMouseListener(root.getMouseManager());
        canvas.addMouseMotionListener(root.getMouseManager());
        frame.add(canvas);

        frame.setVisible(true);
    }

    Canvas getCanvas() {
        return canvas;
    }

    int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
