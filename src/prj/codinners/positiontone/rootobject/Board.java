package prj.codinners.positiontone.rootobject;

import prj.codinners.positiontone.Colors;
import prj.codinners.positiontone.TextFormat;
import prj.codinners.positiontone.root.Display;
import prj.codinners.positiontone.root.MouseManager;
import prj.codinners.positiontone.root.Root;

import java.awt.*;

public class Board extends RootObject {
    private Display display;
    private Root root;

    private int size, gap;

    private boolean deployable = true, cursorout = false;
    private int previewBoxX, previewBoxY;

    public Board(Display display, Root root) {
        this.display = display;
        this.root = root;

        gap = (display.getHeight() - 40) / 18;
        size = gap * 18;
    }

    @Override
    public void tick() {
        if (deployable) {
            previewBoxX = 20 - gap / 2 + (getPositionX() + 9) * gap;
            previewBoxY = 20 - gap / 2 + (9 - getPositionY()) * gap;

            cursorout = getPositionX() == Integer.MAX_VALUE || getPositionY() == Integer.MAX_VALUE;

            System.out.println(positionToString());
        }
    }

    private int getPositionX() {
        int x = root.getMouseManager().getX();
        if (20 - gap / 2 < x && x < 20 + 18.5 * gap) {  // 20 + 18.5 * gap == 20 - gap / 2 + 'gap * 19'
            return x / gap - 9;
        }
        return Integer.MAX_VALUE;
    }

    private int getPositionY() {
        int y = root.getMouseManager().getY();
        if (20 - gap / 2 < y && y < 20 + 18.5 * gap) {
            return 9 - y / gap;
        }
        return Integer.MAX_VALUE;
    }

    private String positionToString() {
        return "" + getPositionX() + getPositionY();
    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Colors.LINE);
        graphics2D.setStroke(new BasicStroke(2));
        int position, i;
        for (i = 0; i < 19; i++) {
            if (i != 9) {
                position = 20 + i * gap;
                graphics2D.drawLine(position, 20, position, 20 + size);
                graphics2D.drawLine(20, position, 20 + size, position);
            }
        }
        graphics2D.setStroke(new BasicStroke(4));
        position = 20 + 9 * gap;
        graphics2D.drawLine(position, 22, position, 18 + size);  // 세로
        graphics2D.drawLine(22, position, 18 + size, position);  // 가로

        if (deployable && !cursorout) {
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(0.3f));
            graphics2D.fillRect(previewBoxX, previewBoxY, gap, gap);
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(1f));
            new Text(20, 15, positionToString(), new TextFormat("./res/font/D2Coding.ttc", 16, Colors.LINE)).render(graphics);
        }
    }
}
