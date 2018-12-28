package prj.codinners.positiontone.client.state;

import prj.codinners.positiontone.client.root.Display;
import prj.codinners.positiontone.client.root.Root;
import prj.codinners.positiontone.client.rootobject.board.Board;
import prj.codinners.positiontone.client.rootobject.board.GomokuBoard;

import java.awt.*;

public class InGame extends State {
    private Display display;
    private Root root;

    private Board board;

    public InGame(Display display, Root root) {
        this.root = root;
        this.display = display;

        init();
    }

    private void init() {
        board = new GomokuBoard(display, root);
    }

    @Override
    public void tick() {
        board.tick();
    }

    @Override
    public void render(Graphics graphics) {
        board.render(graphics);
    }
}
