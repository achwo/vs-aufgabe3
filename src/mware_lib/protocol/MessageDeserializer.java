package mware_lib.protocol;

public class MessageDeserializer {

    String message = "127.0.0.1|15000|NameService|woher!rebind|servant|name";
    private String hostname;
    private int port;
    private String objectName;
    private String hashCode;
    private String methodCall;

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
