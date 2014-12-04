package mware_lib;

import mware_lib.networking.RequestService;

public class ObjectBroker {

    private final String serviceHost;
    private final int nsPort;
    private final boolean debug;
    private final RequestService requestService;
    private final ReferenceManager referenceManager = new ReferenceManager();

    private ObjectBroker(String serviceHost, int nsPort, boolean debug) {
        this.serviceHost = serviceHost;
        this.nsPort = nsPort;
        this.debug = debug;

        this.requestService = new RequestService(this);

        Thread thread = new Thread(requestService);
        thread.start();
    }

    public static ObjectBroker init(String serviceHost,
                                    int listenPort,
                                    boolean debug) {
        return new ObjectBroker(serviceHost, listenPort, debug);
    }

    public NameService getNameService() {
        return new NameServiceProxy(serviceHost, nsPort, referenceManager, requestService.getPort());
    }

    public Skeleton getSkeleton(Object reference) {
        return referenceManager.getSkeleton(reference);
    }

    public int getPort() {
        return requestService.getPort();
    }

    public void shutDown() {
        requestService.shutdown();
    }

}
