package prj.codinners.positiontone.rootobject.board;

import prj.codinners.positiontone.Colors;
import prj.codinners.positiontone.TextFormat;
import prj.codinners.positiontone.root.Display;
import prj.codinners.positiontone.root.Root;
import prj.codinners.positiontone.rootobject.RootObject;
import prj.codinners.positiontone.rootobject.Text;

import java.awt.*;

public class GomokuBoard implements Board {
    private Display display;
    private Root root;

    private int size, gap;

    private boolean deployable = true, cursorout = false;
    private int previewBoxX, previewBoxY;

    public GomokuBoard(Display display, Root root) {
        this.display = display;
        this.root = root;

        init();
    }

    @Override
    public void init() {
        gap = (display.getHeight() - 40) / 18;
        size = gap * 18;
    }

    @Override
    public void tick() {
        if (deployable) {
            previewBoxX = 20 - gap / 2 + (getPositionX() + 9) * gap;
            previewBoxY = 20 - gap / 2 + (9 - getPositionY()) * gap;

            cursorout = getPositionX() == Integer.MAX_VALUE || getPositionY() == Integer.MAX_VALUE;
        }
    }

    @Override
    public int getPositionX() {
        int x = root.getMouseManager().getX() - 20 + gap / 2;
        if (x / gap < 19)
            return x / gap - 9;
        return Integer.MAX_VALUE;
    }

    @Override
    public int getPositionY() {
        int y = root.getMouseManager().getY() - 20 + gap / 2;
        if (y / gap < 19)
            return 9 - y / gap;
        return Integer.MAX_VALUE;
    }

    @Override
    public String positionToString() {
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
