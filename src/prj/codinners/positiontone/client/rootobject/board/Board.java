package prj.codinners.positiontone.client.rootobject.board;

import java.awt.*;

public interface Board {
    void init();

    void tick();
    void render(Graphics graphics);

    int getPositionX();
    int getPositionY();

    String positionToString();
}
