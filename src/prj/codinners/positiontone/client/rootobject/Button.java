package prj.codinners.positiontone.client.rootobject;

import prj.codinners.positiontone.client.TextFormat;

import java.awt.*;

public class Button extends RootObject {
    private int x, y, width, height;
    private Color backgroundColor, borderColor, textColor;
    private String string;
    private TextFormat textFormat;

    private int stringX, stringY;

    public Button(int x, int y, int width, int height, Color backgroundColor, Color borderColor, Color textColor, String string, TextFormat textFormat) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        this.string = string;
        this.textFormat = textFormat;

        init();
    }

    private int center(double a, double b) {
        return (int) ((a - b) / 2);
    }

    private void init() {
        stringX = center(width, textFormat.getWidth(string)) + x;
        stringY = (int) (center(height, textFormat.getSize()) + y + textFormat.getSize());
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(backgroundColor);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(textColor);
        graphics.setFont(textFormat.getFont());
        graphics.drawString(string, x, y);

        graphics.setColor(borderColor);
        graphics.fillRect(x, y, width, height);
    }
}
