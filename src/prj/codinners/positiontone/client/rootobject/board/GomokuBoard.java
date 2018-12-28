package prj.codinners.positiontone.client.rootobject.board;

import prj.codinners.positiontone.client.Colors;
import prj.codinners.positiontone.client.TextFormat;
import prj.codinners.positiontone.client.root.Display;
import prj.codinners.positiontone.client.root.Root;
import prj.codinners.positiontone.client.rootobject.Text;

import java.awt.*;

public class GomokuBoard implements Board {
    private Display display;
    private Root root;

    private int size, gap;
    private BoardState boardState;

    private boolean deployable = true, cursorOut = false;
    private int previewBoxX, previewBoxY;

    private int turn;

    public GomokuBoard(Display display, Root root) {
        this.display = display;
        this.root = root;

        init();
    }

    @Override
    public void init() {
        gap = (display.getHeight() - 40) / 18;
        size = gap * 18;

        boardState = new BoardState(19, 19, root.getConnector());

        turn = 0;
    }

    @Override
    public void tick() {
        if (deployable) {
            previewBoxX = 20 - gap / 2 + (getPositionX() + 9) * gap;
            previewBoxY = 20 - gap / 2 + (9 - getPositionY()) * gap;

            cursorOut = getPositionX() == Integer.MAX_VALUE || getPositionY() == Integer.MAX_VALUE;

            if (!cursorOut) {
                if (root.getMouseManager().getEndPressed()[0]) {
                    if (boardState.get(getPositionX() + 9, getPositionY() + 9) == BoardPiece.NONE) {
                        if (turn % 2 == 0) {
                            boardState.deployBlack(getPositionX() + 9, getPositionY() + 9);
                        } else {
                            boardState.deployWhite(getPositionX() + 9, getPositionY() + 9);
                        }
                        turn++;
                    }
                }
            }
        }

        System.out.println(getWinner());
    }

    private BoardPiece getWinner() {
        BoardPiece[][] state = boardState.getState();

        for (int i = 0; i < 19; i++)
            for (int j = 0; j < 19; j++) {
                if (state[i][j] == BoardPiece.NONE) continue;
                for (int p = -1; p <= 1; p++)
                    for (int q = -1; q <= 1; q++) {
                        if (p == 0 && q == 0) continue;
                        boolean flag = true;
                        for (int k = 1; k < 5; k++) {
                            if (!(0 <= i + k*p && i + k*p < 19 && 0 <= j + k*q && j + k*q < 19)) {
                                flag = false;
                                break;
                            }
                            if (state[i + k*p][j + k*q] != state[i][j]) {
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            return state[i][j];
                        }
                    }
            }

        return BoardPiece.NONE;
    }

    @Override
    public int getPositionX() {
        int x = root.getMouseManager().getX() - 20 + gap / 2;
        if (x / gap < 19)
            return x / gap - 9;
        return Integer.MAX_VALUE;
    }

    @Override
    public int getPositionY() {
        int y = root.getMouseManager().getY() - 20 + gap / 2;
        if (y / gap < 19)
            return 9 - y / gap;
        return Integer.MAX_VALUE;
    }

    @Override
    public String positionToString() {
        return "" + getPositionX() + getPositionY();
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

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int y = -9; y <= 9; y++) {
            for (int x = -9; x <= 9; x++) {
                BoardPiece state = boardState.get(x + 9 , y + 9);
                if (state != BoardPiece.NONE) {
                    if (state == BoardPiece.BLACK) {
                        graphics.setColor(Colors.BLACK);
                    }
                    else {
                        graphics.setColor(Colors.WHITE);
                    }
                    graphics2D.fillOval(20 + (x + 9) * gap - gap / 4, 20 + (9 - y) * gap - gap / 4, gap / 2, gap / 2);
                }
            }
        }

        graphics2D.setColor(Colors.LINE);
        if (deployable && !cursorOut) {
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(0.3f));
            graphics2D.fillRect(previewBoxX, previewBoxY, gap, gap);
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(1f));
        }
    }
}
