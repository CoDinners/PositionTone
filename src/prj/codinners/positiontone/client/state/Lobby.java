package prj.codinners.positiontone.client.state;

import prj.codinners.positiontone.PropertiesManager;
import prj.codinners.positiontone.client.Colors;
import prj.codinners.positiontone.client.TextFormat;
import prj.codinners.positiontone.client.root.Display;
import prj.codinners.positiontone.client.rootobject.MoveText;

import java.awt.*;

public class Lobby extends State {
    private Display display;
    private PropertiesManager propertiesManager;

    private MoveText title;

    public Lobby(Display display, PropertiesManager propertiesManager) {
        this.display = display;
        this.propertiesManager = propertiesManager;

        init();
    }

    private int center(double x, double y) {
        return (int) ((x - y) / 2);
    }

    private void init() {
        TextFormat textFormat = new TextFormat(propertiesManager.getProperties("font"), 72, Colors.LINE);

        String titleText = "PositionTone";
        title = new MoveText(center(display.getWidth(), textFormat.getWidth(titleText)), -10, center(display.getWidth(), textFormat.getWidth(titleText)), 100, 6, titleText, textFormat, display);
    }

    @Override
    public void tick() {
        title.tick();
    }

    @Override
    public void render(Graphics graphics) {
        title.render(graphics);
    }
}
