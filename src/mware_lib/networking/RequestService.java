package mware_lib.networking;

import mware_lib.Logger;
import mware_lib.ObjectBroker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestService implements Runnable {

    private final ObjectBroker broker;
    private ServerSocket serverSocket;
    private final Logger logger =  new Logger(this, ObjectBroker.LOGGING);

    public RequestService(ObjectBroker broker, Integer port) {
        this.broker = broker;

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.log(e.getMessage());
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
        boolean running = true;
        while (running) {
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
            logger.log(e.getMessage());
        }
    }

}
