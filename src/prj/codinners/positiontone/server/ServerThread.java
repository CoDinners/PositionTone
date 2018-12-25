package prj.codinners.positiontone.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket socket;
    private Server server;

    private Scanner scanner;
    private PrintStream printStream;
    private Thread thread;

    private boolean running = false;

    ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;

        init();
    }

    private void init() throws IOException {
        scanner = new Scanner(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream());

        thread = new Thread(this);
    }

    private void send(String s) {
        printStream.println(s);
    }

    private String recv() {
        String msg;
        try {
            msg = scanner.nextLine();
        } catch (NoSuchElementException e) {
            return null;
        }

        return msg;
    }

    @Override
    public void run() {
        String msg;
        while (running) {
            msg = recv();
            if (msg == null) break;
            send(msg);

            System.out.println(msg);
        }

        stop();
    }

    void start() {
        if (running)
            return;
        running = true;
        thread.start();
        server.getClients().add(this);
        System.out.println("클라이언트 주소 " + socket.getLocalAddress() + "의 스레드 작동을 시작했습니다. " +
                "현재 동작중인 스레드 갯수는 " + server.getClients().size() + "개 입니다.");
    }

    private void stop() {
        if (!running)
            return;
        running = false;
        server.getClients().remove(this);
        System.out.println("클라이언트 주소 " + socket.getLocalAddress() + "의 스레드 작동을 종료했습니다. " +
                "현재 동작중인 스레드 갯수는 " + server.getClients().size() + "개 입니다.");
    }
}
