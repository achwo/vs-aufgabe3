package mware_lib.networking;

import mware_lib.ObjectBroker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestService implements Runnable {

    private final ObjectBroker broker;
    private final Integer port;
    private ServerSocket serverSocket;
    private boolean running = false;

    public RequestService(ObjectBroker broker, Integer port) {
        this.broker = broker;
        this.port = port;

        try {
            this.serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestService(ObjectBroker broker) {
        this(broker, 0);
    }

    public Integer getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new RequestProcessor(socket, broker)).start();
            } catch (IOException e) {
                running = false;
            }
        }

    }

    public void shutdown() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
