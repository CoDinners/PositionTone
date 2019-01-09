package prj.codinners.positiontone.client.state;

import prj.codinners.positiontone.client.Colors;
import prj.codinners.positiontone.client.TextFormat;
import prj.codinners.positiontone.client.root.Display;
import prj.codinners.positiontone.client.root.Root;
import prj.codinners.positiontone.client.rootobject.MoveImage;
import prj.codinners.positiontone.client.rootobject.MoveText;
import prj.codinners.positiontone.client.util.Timer;

import java.awt.*;

public class Intro extends State {
    private Display display;
    private Root root;

    private MoveImage codinnersLogo0, codinnersLogo1;
    private MoveText codinners1;
    private Timer timer2;
    private int scene;

    public Intro(Display display, Root root) {
        this.display = display;
        this.root = root;

        init();
    }

    private void init() {
        scene = 0;
        codinnersLogo0 = new MoveImage(-display.getHeight() / 6 - 10, (display.getHeight() - display.getHeight() / 6) / 2,
                (display.getWidth() - display.getHeight() / 6) / 2, (display.getHeight() - display.getHeight() / 6) / 2, 6,
                "./res/image/codinners-logo.png", display.getHeight() / 6, display.getHeight() / 6, display);

        codinnersLogo1 = new MoveImage((display.getWidth() - display.getHeight() / 6) / 2, (display.getHeight() - display.getHeight() / 6) / 2,
                (display.getWidth() - display.getHeight() / 6) / 2, (display.getHeight() - display.getHeight() / 6) / 2 - 36, 6,
                "./res/image/codinners-logo.png", display.getHeight() / 6, display.getHeight() / 6, display);

        TextFormat codinners1TextFormat = new TextFormat("./res/font/NANUMGOTHIC.TTF", 36, Colors.WHITE);
        codinners1 = new MoveText((display.getWidth() - codinners1TextFormat.getWidth("Codinners™")) / 2, display.getHeight() + 10,
                (display.getWidth() - codinners1TextFormat.getWidth("Codinners™")) / 2, (display.getHeight() - 36) / 2 + display.getHeight() / 6, 6,
                "Codinners™", codinners1TextFormat, display);

        timer2 = new Timer(1000);
    }

    @Override
    public void tick() {
        if (scene == 0) {
            codinnersLogo0.tick();

            if (codinnersLogo0.isMoveStopped())
                scene = 1;
        } else if (scene == 1) {
            codinnersLogo1.tick();
            codinners1.tick();

            if (codinnersLogo1.isMoveStopped() && codinners1.isMoveStopped()) {
                scene = 2;
                timer2.start();
            }
        } else if (scene == 2) {
            if (timer2.check())
                scene = 3;
        } else if (scene == 3) {
            root.setState(new Lobby(display, root.getPropertiesManager()));
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Colors.BLACK);
        graphics.fillRect(0, 0, display.getWidth(), display.getHeight());

        if (scene == 0) {
            codinnersLogo0.render(graphics);
        } else if (scene == 1 || scene == 2) {
            codinnersLogo1.render(graphics);
            codinners1.render(graphics);
        }
    }
}
