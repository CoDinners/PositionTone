package prj.codinners.positiontone.client.rootobject.board;

import prj.codinners.positiontone.client.communication.Connector;

import java.util.Arrays;

class BoardState {
    private int width, height;
    private Connector connector;

    private BoardPiece[][] state;

    BoardState(int width, int height, Connector connector) {
        this.width = width;
        this.height = height;
        this.connector = connector;

        init();
    }

    private void init() {
        state = new BoardPiece[height][width];

        for (BoardPiece[] row : state)
            Arrays.fill(row, BoardPiece.NONE);
    }

    void deployBlack(int x, int y) {
        state[y][x] = BoardPiece.BLACK;

        if (connector.isConnected()) {
            connector.deploy(x, y, BoardPiece.BLACK);
        }
    }

    void deployWhite(int x, int y) {
        state[y][x] = BoardPiece.WHITE;

        if (connector.isConnected()) {
            connector.deploy(x, y, BoardPiece.WHITE);
        }
    }

    void take(int x, int y) {
        state[y][x] = BoardPiece.NONE;

        if (connector.isConnected()) {
            connector.deploy(x, y, BoardPiece.NONE);
        }
    }

    BoardPiece get(int x, int y) {
        return state[y][x];
    }
}
