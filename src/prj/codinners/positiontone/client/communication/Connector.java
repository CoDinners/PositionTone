package prj.codinners.positiontone.client.communication;

import prj.codinners.positiontone.client.rootobject.board.BoardPiece;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;

public class Connector {
    private String host;
    private int port;

    private PrintStream printStream;

    private boolean connected = false;

    public Connector(String host, int port) throws IOException {
        this.host = host;
        this.port = port;

        init();
    }

    private void init() throws IOException {
        Socket socket;
        try {
            socket = new Socket(host, port);
        } catch (ConnectException e) {
            return;
        }
        connected = true;

        ConnectorThread connectorThread = new ConnectorThread(socket);
        printStream = new PrintStream(socket.getOutputStream());

        connectorThread.start();
    }

    private void send(String s) {
        printStream.println(s);
    }

    public void deploy(int x, int y, BoardPiece piece) {
        send("deploy\t" + x + "\t" + y + "\t" + piece);
    }

    public boolean isConnected() {
        return connected;
    }
}
