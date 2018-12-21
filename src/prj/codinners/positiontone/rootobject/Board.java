package prj.codinners.positiontone.rootobject;

import prj.codinners.positiontone.Colors;
import prj.codinners.positiontone.root.Display;

import java.awt.*;

public class Board extends RootObject {
    private Display display;
    private int size, gap;

    public Board(Display display) {
        this.display = display;

        gap = (display.getHeight() - 40) / 18;
        size = gap * 18;
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
    }
}
