package mware_lib.protocol;

public class MessageDeserializer {

    private final String hostname;
    private final int port;
    private final String objectName;
    private final String hashCode;
    private final String methodCall;

    public MessageDeserializer(String message) {
        String[] split = message.split("!");

        String objReference = split[0];
        methodCall = split[1];

        String[] objParts = objReference.split("\\|");
        hostname = objParts[0];
        port = Integer.valueOf(objParts[1]);
        objectName = objParts[2];
        hashCode = objParts[3];

    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getHashCode() {
        return hashCode;
    }

    public String getMethodCall() {
        return methodCall;
    }
}
