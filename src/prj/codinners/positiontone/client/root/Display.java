package prj.codinners.positiontone.client.root;

import javax.swing.*;
import java.awt.*;

public class Display {
    private int width, height, fps;
    private String caption;
    private Root root;

    private JFrame frame;
    private Canvas canvas;

    Display(int width, int height, int fps, String caption, Root root) {
        this.width = width;
        this.height = height;
        this.fps = fps;
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

    int getFps() {
        return fps;
    }
}
