package mware_lib.protocol;

import mware_lib.protocol.exceptions.InvalidMessageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MessageImpl implements Message {

    private final ObjectReference objectReference;
    private String methodCall;
    private String message;


    MessageImpl(String hostname, int port, Object object,
                String methodName, Object[] methodParams) {
        this.setMethod(methodName, methodParams);
        this.objectReference = Protocol.objectReference(object, hostname, port);
    }

    MessageImpl(String message) throws InvalidMessageException {
        String[] split = message.split(Protocol.CALL_DELIMITER);

        if (split.length < 2)
            throw new InvalidMessageException("No Delimiter '" + Protocol.CALL_DELIMITER + "' found.");

        methodCall = split[1];
        this.objectReference = Protocol.objectReferenceFromMessage(split[0]);
        this.message = message;
    }

    public MessageImpl(String objectReference, String methodName, Object[] methodParams) {
        this.objectReference = Protocol.objectReferenceFromMessage(objectReference);
        setMethod(methodName, methodParams);
    }

    @Override
    public String getHostname() {
        return objectReference.getHostname();
    }

    @Override
    public int getPort() {
        return objectReference.getPort();
    }

    @Override
    public String getObjectName() {
        return objectReference.getObjectName();
    }

    @Override
    public int getHashCode() {
        return objectReference.getHashCode();
    }

    @Override
    public String getMethodCallAsString() {
        return methodCall;
    }

    @Override
    public String asString() {
        if (message == null) buildMessage();
        return message;
    }

    private void buildMessage() {
        this.message = String.join(
                Protocol.CALL_DELIMITER,
                getObjectReferenceAsString(),
                getMethodCallAsString());
    }

    private void setMethod(String methodName, Object... args) {
        List<String> strings = new ArrayList<>();
        strings.add(methodName);

        for (Object o : args) {
            strings.add(Objects.toString(o));
        }

        methodCall = Protocol.join(strings, Protocol.DELIMITER);
    }


    private String getObjectReferenceAsString() {
        return objectReference.asString();
    }

    @Override
    public String toString() {
        return asString();
    }
}
