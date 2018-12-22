package prj.codinners.positiontone.rootobject.board;

import prj.codinners.positiontone.Colors;
import prj.codinners.positiontone.TextFormat;
import prj.codinners.positiontone.root.Display;
import prj.codinners.positiontone.root.Root;
import prj.codinners.positiontone.rootobject.Text;

import java.awt.*;

public class KoreanChessBoard implements Board {
    private Display display;
    private Root root;

    private int size, gapHorizontal, gapVertical;  // hor 가로, ver 세로

    private boolean deployable = true, cursorout = false;
    private int previewBoxX, previewBoxY;

    public KoreanChessBoard(Display display, Root root) {
        this.display = display;
        this.root = root;

        init();
    }

    @Override
    public void init() {
        size = display.getHeight() - 40;
        gapHorizontal = size / 9;
        gapVertical = size / 8;
    }

    @Override
    public void tick() {
        cursorout = getPositionX() == Integer.MAX_VALUE || getPositionY() == Integer.MAX_VALUE;

        previewBoxX = getPositionX() * gapVertical + 20 - gapVertical / 2;
        previewBoxY = getPositionY() * gapHorizontal + 20 - gapHorizontal / 2;
    }

    @Override
    public int getPositionX() {
        int x = root.getMouseManager().getX() - 20 + gapVertical / 2;
        if (x / gapVertical < 9)
            return x / gapVertical;
        return Integer.MAX_VALUE;
    }

    @Override
    public int getPositionY() {
        int y = root.getMouseManager().getY() - 20 + gapHorizontal / 2;
        if (y / gapHorizontal < 10)
            return y / gapHorizontal;
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
        for (i = 0; i < 9; i++) { // vertical
            position = 20 + i * gapVertical;
            graphics.drawLine(position, 20, position, size + 15);
        } for (i = 0; i < 10; i++) { // horizontal
            position = 20 + i * gapHorizontal;
            graphics.drawLine(20, position, size + 20, position);
        }

        if (deployable && !cursorout) {
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(0.3f));
            graphics2D.fillRect(previewBoxX, previewBoxY, gapVertical, gapHorizontal);
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(1f));
            new Text(20, 15, positionToString(), new TextFormat("./res/font/D2Coding.ttc", 16, Colors.LINE)).render(graphics);
        }
    }
}
