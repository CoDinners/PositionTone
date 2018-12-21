package prj.codinners.positiontone.root;

import javax.swing.*;
import java.awt.*;

public class Display {
    private int width, height;
    private String caption;

    private JFrame frame;
    private Canvas canvas;

    Display(int width, int height, String caption) {
        this.width = width;
        this.height = height;
        this.caption = caption;

        frame = new JFrame(caption);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        frame.add(canvas);

        frame.setVisible(true);
    }

    Canvas getCanvas() {
        return canvas;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
