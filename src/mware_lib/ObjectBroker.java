package mware_lib;

import mware_lib.networking.RequestService;

public class ObjectBroker {

    public static boolean LOGGING = false;

    private final String serviceHost;
    private final int nsPort;
    private final RequestService requestService;
    private final ReferenceManager referenceManager = new ReferenceManager();
    private final Logger logger;


    private ObjectBroker(String serviceHost, int nsPort, boolean debug) {
        LOGGING = debug;
        logger = new Logger(this, LOGGING);
        this.serviceHost = serviceHost;
        this.nsPort = nsPort;

        logger.log("Starting");

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
        logger.log("getNameService()");
        return new NameServiceProxy(serviceHost, nsPort, referenceManager, requestService.getPort());
    }

    public Skeleton getSkeleton(Object reference) {
        logger.log("getSkeleton(" + reference + ")");
        return referenceManager.getSkeleton(reference);
    }

    public int getPort() {
        return requestService.getPort();
    }

    public void shutDown() {
        logger.log("shutDown()");
        requestService.shutdown();
    }

    @Override
    public String toString() {
        return "ObjectBroker";
    }
}
