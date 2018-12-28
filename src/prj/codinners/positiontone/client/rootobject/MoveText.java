package prj.codinners.positiontone.client.rootobject;

import prj.codinners.positiontone.client.TextFormat;
import prj.codinners.positiontone.client.root.Display;

import java.awt.*;

public class MoveText extends RootObject {
    private double x, y;
    private int targetX, targetY, friction;
    private String text;
    private TextFormat textFormat;
    private Display display;

    public MoveText(int x, int y, int targetX, int targetY, int friction, String text, TextFormat textFormat, Display display) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.friction = friction;
        this.text = text;
        this.textFormat = textFormat;
        this.display = display;
    }

    @Override
    public void tick() {
        double deltaX = targetX - x, deltaY = targetY - y;

        if (deltaX != 0) {
            if (deltaX < 1)
                x = targetX;
            else
                x += deltaX / (display.getFps() / friction);
        } if (deltaY != 0) {
            if (deltaY < 1)
                y = targetY;
            else
                y += deltaY / (display.getFps() / friction);
        }
    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setFont(textFormat.getFont());
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(textFormat.getColor());
        graphics2D.drawString(text, (int) x, (int) y);
    }
}
