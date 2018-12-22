package prj.codinners.positiontone.root;

import prj.codinners.positiontone.Colors;
import prj.codinners.positiontone.TextFormat;
import prj.codinners.positiontone.rootobject.Board;
import prj.codinners.positiontone.rootobject.RootObject;
import prj.codinners.positiontone.rootobject.Text;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Root implements Runnable {
    private int width, height;
    private String caption;

    private Thread thread;
    private boolean running = false;

    private MouseManager mouseManager;
    private Display display;

    public Root(int width, int height, String caption) {
        this.width = width;
        this.height = height;
        this.caption = caption;

        thread = new Thread(this);
    }

    private void init() {
        mouseManager = new MouseManager();
        display = new Display(width, height, caption, this);

        RootObject.add(new Board(display, this));
    }

    private void tick() {
        mouseManager.tick();

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

        for (RootObject object : RootObject.OBJECTS) {
            object.render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    @Override
    public void run() {
        init();

        while (running) {
            tick();
            render();
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
}
