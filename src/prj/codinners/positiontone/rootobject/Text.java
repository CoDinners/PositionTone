package prj.codinners.positiontone.rootobject;

import prj.codinners.positiontone.TextFormat;

import java.awt.*;

public class Text extends RootObject {
    private int x, y;
    private String text;
    private TextFormat textFormat;

    public Text(int x, int y, String text, TextFormat textFormat) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.textFormat = textFormat;
    }

    @Override
    public void render(Graphics graphics) {
//        graphics.setFont(textFormat.getFont());
//        graphics.setColor(textFormat.getColor());
//        graphics.drawString(text, x, y);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setFont(textFormat.getFont());
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(textFormat.getColor());
        graphics2D.drawString(text, x, y);
    }
}
