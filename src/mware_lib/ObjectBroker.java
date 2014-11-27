package mware_lib;

public class ObjectBroker {

    private final String serviceHost;
    private final int listenPort;
    private final boolean debug;
    protected ReferenceManager referenceManager = new ReferenceManager();

    public ObjectBroker(String serviceHost, int listenPort, boolean debug) {

        // todo really do stuff
        this.serviceHost = serviceHost;
        this.listenPort = listenPort;
        this.debug = debug;
    }

    public static ObjectBroker init(String serviceHost,
                                    int listenPort,
                                    boolean debug) {
        // todo something todo here?
        return new ObjectBroker(serviceHost, listenPort, debug);
    }

    public NameService getNameService() {
        // todo implement
        return new NameServiceProxy(referenceManager);
    }

    public void shutdown() {
        // todo implement shutdown
    }

}
