package mware_lib.protocol;

import mware_lib.protocol.exceptions.InvalidMessageException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;

class ExceptionValueFromMessage<E extends Throwable> implements ExceptionValue<E> {

    private final String type;
    private final String message;
    private final String stringVersion;

    public ExceptionValueFromMessage(String message) throws InvalidMessageException {
        String[] parts = message.split(Protocol.REGEX_DELIMITER);

        if(parts.length < 2)
            throw new InvalidMessageException("Malformed Message");

        if (!Objects.equals(parts[0], Protocol.EXCEPTION))
            throw new InvalidMessageException("Wrong message type");

        this.message = parts.length > 2 ? parts[2] : "";
        type = parts[1];
        this.stringVersion = message;
    }

    // todo implement
    @Override
    public E getValue() {
        E object = null;
        try {
            Class<?> cl = getType();
            Constructor<?> constructor = cl.getConstructor(String.class);
            object = (E)constructor.newInstance(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Class<E> getType() {
        try {
            return (Class<E>) Class.forName(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String asString() {
        return stringVersion;
    }

    @Override
    public String toString() {
        return asString();
    }
}
