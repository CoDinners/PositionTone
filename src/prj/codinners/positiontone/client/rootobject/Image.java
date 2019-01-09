package prj.codinners.positiontone.client.rootobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends RootObject {
    private int x, y;
    private String path;
    private int width, height;

    private BufferedImage image;

    public Image(int x, int y, String path) {
        this.x = x;
        this.y = y;
        this.path = path;
        this.width = 0;
        this.height = 0;

        init();
    }

    public Image(int x, int y, String path, int width, int height) {
        this.x = x;
        this.y = y;
        this.path = path;
        this.width = width;
        this.height = height;

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
    public void render(Graphics graphics) {
        if (width == 0 || height == 0) {
            graphics.drawImage(image, x, y, null);
        } else {
            graphics.drawImage(image, x, y, width, height, null);
        }
    }
}
