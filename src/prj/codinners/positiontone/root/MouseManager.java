package prj.codinners.positiontone.root;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseInputAdapter {
    private int x, y;
    private boolean[] pressed = new boolean[5], previousPressed = new boolean[5];
    private boolean[] firstPressed = new boolean[5], endPressed = new boolean[5];

    @Override
    public void mousePressed(MouseEvent e) {
        pressed[e.getButton() - 1] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed[e.getButton() - 1] = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    void tick() {
        for (int i = 0; i < 5; i++) {
            firstPressed[i] = !previousPressed[i] && pressed[i];
            endPressed[i] = previousPressed[i] && !pressed[i];
        }

        previousPressed = pressed.clone();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
