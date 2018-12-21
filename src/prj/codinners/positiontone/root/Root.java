package prj.codinners.positiontone.root;

import prj.codinners.positiontone.Colors;
import prj.codinners.positiontone.rootobject.Board;
import prj.codinners.positiontone.rootobject.RootObject;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Root implements Runnable {
    private Display display;
    
    private Thread thread;
    private boolean running = false;

    public Root(int width, int height, String caption) {
        this.display = new Display(width, height, caption);

        thread = new Thread(this);
    }

    private void init() {
        RootObject.add(new Board(display));
    }

    private void tick() {
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

    public Display getDisplay() {
        return display;
    }
}
