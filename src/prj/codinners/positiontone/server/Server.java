package prj.codinners.positiontone.server;

import prj.codinners.positiontone.PropertiesManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    private static boolean SERVER_ON = false;

    private ArrayList<ServerThread> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    private void start() throws IOException {
        PropertiesManager manager = new PropertiesManager("./config/server/ip.ini");

        int port = Integer.parseInt(manager.getProperties("port"));

        ServerSocket serverSocket = new ServerSocket(port);
        SERVER_ON = true;
        System.out.println("서버가 포트 " + port + "에서 열렸습니다.");

        while (SERVER_ON) {
            new ServerThread(serverSocket.accept(), this).start();
        }
    }

    ArrayList<ServerThread> getClients() {
        return clients;
    }

    public static void setServerOn(boolean serverOn) {
        SERVER_ON = serverOn;
    }
}
