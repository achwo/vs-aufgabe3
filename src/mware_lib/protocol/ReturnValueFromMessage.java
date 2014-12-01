package mware_lib.protocol;

import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Pattern;

class ReturnValueFromMessage<E> implements ReturnValue<E> {

    private final String message;
    private final Class<E> type;
    private E value;

    ReturnValueFromMessage(String message, Class<E> type) throws IllegalTypeException {
        if (type.isPrimitive())
            throw new IllegalTypeException("Type must not be primitive");

        this.message = message;
        this.type = type;
    }

    @Override
    public E getValue() throws InvalidMessageException {
        if (value == null) deserialize();
        return value;
    }

    @Override
    public String asString() {
        return message;
    }

    @SuppressWarnings("unchecked")
    private void deserialize() throws InvalidMessageException {
        if (message == null || Objects.equals(message, ""))
            throw new InvalidMessageException("Message too short or null");
        if (!Pattern.compile(Protocol.REGEX_DELIMITER).matcher(message).find())
            throw new InvalidMessageException("Delimiter not found");

        String[] parts = message.split(Protocol.REGEX_DELIMITER);

        if (!message.startsWith(Protocol.RETURN))
            throw new InvalidMessageException("Wrong message type");
        if (parts.length < 1)
            throw new InvalidMessageException("No return value found");

        String value = parts.length > 1 ? parts[1] : "";

        Object returnValue;

        try {
            Method valueOf = type.getMethod("valueOf", String.class);
            returnValue = valueOf.invoke(null, value);
        } catch (Exception e) {
            // Class has no valueOf method
            returnValue = value;
        }
        this.value = (E) returnValue;
    }

    @Override
    public String toString() {
        return asString();
    }
}
