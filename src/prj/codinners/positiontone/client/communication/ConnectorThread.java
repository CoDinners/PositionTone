package prj.codinners.positiontone.client.communication;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ConnectorThread implements Runnable {
    private Socket socket;

    private Scanner scanner;

    private boolean running = false;
    private Thread thread;

    ConnectorThread(Socket socket) throws IOException {
        this.socket = socket;

        init();
    }

    private void init() throws IOException {
        scanner = new Scanner(socket.getInputStream());

        thread = new Thread(this);
    }

    private String recv() {
        return scanner.nextLine();
    }

    @Override
    public void run() {
        String msg;
        while (running) {
            msg = recv();

            System.out.println("ConnectorThread.run " + msg);
        }

        stop();
    }

    void start() {
        if (running)
            return;
        running = true;
        thread.start();
    }

    private void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
