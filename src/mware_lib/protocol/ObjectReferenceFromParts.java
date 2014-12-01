package mware_lib.protocol;

import java.util.Objects;

public class ObjectReferenceFromParts implements ObjectReference {
    private final Object object;
    private final String hostname;
    private final int port;

    ObjectReferenceFromParts(Object object, String hostname, int port) {
        this.object = object;
        this.hostname = hostname;
        this.port = port;
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
        return object.hashCode();
    }

    @Override
    public String asString() {
        return String.join(Protocol.OBJECT_DELIMITER,
                getHostname(),
                Objects.toString(getPort()),
                getObjectName(),
                Objects.toString(getHashCode()));
    }

    @Override
    public String getObjectName() {
        return Objects.toString(object);
    }

    @Override
    public String toString() {
        return asString();
    }
}
