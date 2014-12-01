package mware_lib.protocol;

import java.util.Objects;

public class ObjectReferenceFromMessage implements ObjectReference {
    private final String hostname;
    private final Integer port;
    private final String objectName;
    private final Object object;
    private final int hashCode;
    private final String message;

    ObjectReferenceFromMessage(String message) {
        this.message = message;
        String[] split = message.split(Protocol.REGEX_OBJECT_DELIMITER);
        this.hostname = split[0];
        this.port = Integer.valueOf(split[1]);

        this.object = split[2]; // todo hier muss ein echtes object gesetzt werden.
        this.objectName = Objects.toString(object);
        this.hashCode = object.hashCode();

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
    public Object getObject() {
        return object;
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
}
