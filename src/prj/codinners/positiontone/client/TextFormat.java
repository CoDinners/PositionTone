package prj.codinners.positiontone.client;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextFormat {
    private String path;
    private float size;
    private Color color;

    private Font font;

    public TextFormat(String path, float size, Color color) {
        this.path = path;
        this.size = size;
        this.color = color;

        init();
}

    private void init() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }
}
