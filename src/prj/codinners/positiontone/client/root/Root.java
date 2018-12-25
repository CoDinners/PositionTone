package prj.codinners.positiontone.client.root;

import prj.codinners.positiontone.client.Colors;
import prj.codinners.positiontone.PropertiesManager;
import prj.codinners.positiontone.client.communication.Connector;
import prj.codinners.positiontone.client.rootobject.RootObject;
import prj.codinners.positiontone.client.rootobject.board.Board;
import prj.codinners.positiontone.client.rootobject.board.GomokuBoard;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Root implements Runnable {
    private int width, height, fps;
    private String caption;

    private Thread thread;
    private boolean running = false;

    private PropertiesManager propertiesManager;
    private MouseManager mouseManager;
    private Display display;
    private Board board;
    private Connector connector;

    public Root() {
        propertiesManager = new PropertiesManager("./config/client/setting.ini");

        if (propertiesManager.getProperties("fullscreen").equalsIgnoreCase("true")) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            this.width = (int) toolkit.getScreenSize().getWidth();
            this.height = (int) toolkit.getScreenSize().getHeight();
        } else {
            this.width = Integer.parseInt(propertiesManager.getProperties("width"));
            this.height = Integer.parseInt(propertiesManager.getProperties("height"));
        }
        this.fps = Integer.parseInt(propertiesManager.getProperties("fps"));
        this.caption = "PositionTone";

        thread = new Thread(this);
    }

    private void init() throws IOException {
        mouseManager = new MouseManager();
        display = new Display(width, height, fps, caption, this);

        connector = new Connector(propertiesManager.getProperties("host"), Integer.parseInt(propertiesManager.getProperties("port")));

        board = new GomokuBoard(display, this);
    }

    private void tick() {
        mouseManager.tick();

        board.tick();

        for (RootObject object : RootObject.OBJECTS) {
            object.tick();
        }
        RootObject.update();
    }

    private void render() {
        BufferStrategy bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Colors.BACKGROUND);
        graphics.fillRect(0, 0, display.getWidth(), display.getHeight());

        board.render(graphics);

        for (RootObject object : RootObject.OBJECTS) {
            object.render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double timePerLoop = 1_000_000_000.0 / display.getFps();
        double delta = 0;
        double time = System.nanoTime();
        double previousTime;

        while (running) {
            previousTime = time;
            time = System.nanoTime();

            delta += (time - previousTime) / timePerLoop;

            if (delta >= 1) {
                delta--;

                tick();
                render();
            }
        }

        stop();
    }

    public void start() {
        if (running) return;
        running = true;
        thread.start();
    }
    
    private void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public PropertiesManager getPropertiesManager() {
        return propertiesManager;
    }

    public Connector getConnector() {
        return connector;
    }
}
