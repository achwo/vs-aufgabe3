package mware_lib.protocol;

class ObjectReferenceFromMessage implements ObjectReference {
    private final String hostname;
    private final Integer port;
    private final String objectName;
    private final int hashCode;
    private final String message;

    ObjectReferenceFromMessage(String message) {
        this.message = message;
        String[] split = message.split(Protocol.REGEX_OBJECT_DELIMITER);
        this.hostname = split[0];
        this.port = Integer.valueOf(split[1]);
        this.objectName = split[2];
        this.hashCode = Integer.valueOf(split[3]);

    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int getHashCode() {
        return hashCode;
    }

    @Override
    public String asString() {
        return message;
    }

    @Override
    public String getObjectName() {
        return objectName;
    }

    @Override
    public String toString() {
        return asString();
    }
}
