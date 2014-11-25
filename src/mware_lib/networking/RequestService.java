package mware_lib.networking;

import mware_lib.ObjectBroker;

public class RequestService implements Runnable {

    private final ObjectBroker broker;
    private final Integer port;

    public RequestService(ObjectBroker broker, Integer port) {
        this.broker = broker;
        this.port = port;
    }

    @Override
    public void run() {

    }

    public void shutdown() {

    }

    public Integer getPort() {
        return port;
    }
}
