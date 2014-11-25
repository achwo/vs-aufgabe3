package mware_lib.networking;

import mware_lib.ObjectBroker;

import java.net.Socket;

public class RequestProcessor implements Runnable {

    private final Socket socket;
    private final ObjectBroker broker;

    public RequestProcessor(Socket socket, ObjectBroker broker) {
        this.socket = socket;
        this.broker = broker;
    }

    @Override
    public void run() {

    }
}
