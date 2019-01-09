package prj.codinners.positiontone.client.rootobject;

import prj.codinners.positiontone.client.root.Display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MoveImage extends RootObject {
    private double x, y, targetX, targetY;
    private double friction;
    private String path;
    private Display display;
    private int width, height;

    private BufferedImage image;

    public MoveImage(int x, int y, int targetX, int targetY, double friction, String path, Display display) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.friction = friction;
        this.path = path;
        this.display = display;
        this.width = 0;
        this.height = 0;

        init();
    }

    public MoveImage(int x, int y, int targetX, int targetY, double friction, String path,
                     int width, int height, Display display) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.friction = friction;
        this.path = path;
        this.width = width;
        this.height = height;
        this.display = display;

        init();
    }

    private void init() {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        double deltaX = targetX - x;
        double deltaY = targetY - y;

        if (deltaX != 0) {
            if (Math.abs(deltaX) >= 1) {
                x += deltaX / (display.getFps() / friction);
            } else {
                x = targetX;
            }
        } if (deltaY != 0) {
            if (Math.abs(deltaY) >= 1) {
                y += deltaY / (display.getFps() / friction);
            } else {
                y = targetY;
            }
        }
    }

    private boolean isXMoveStopped() {
        return targetX - x == 0;
    }

    private boolean isYMoveStopped() {
        return targetY - y == 0;
    }

    public boolean isMoveStopped() {
        return isXMoveStopped() && isYMoveStopped();
    }

    @Override
    public void render(Graphics graphics) {
        if (width == 0 || height == 0) {
            graphics.drawImage(image, (int) x, (int) y, null);
        } else {
            graphics.drawImage(image, (int) x, (int) y, width, height, null);
        }
    }
}
