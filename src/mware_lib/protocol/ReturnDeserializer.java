package mware_lib.protocol;

import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Pattern;

public class ReturnDeserializer<E> {

    public static final String DELIMITER = "\\|";
    public static final String RETURN = "return";
    private String message;
    private Class<E> type;

    public ReturnDeserializer(String message, Class<E> type) throws IllegalTypeException {
        this.message = message;
        if (type.isPrimitive())
            throw new IllegalTypeException("Type must not be primitive");
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public E deserialize() throws InvalidMessageException {
        if (message == null || Objects.equals(message, ""))
            throw new InvalidMessageException("Message too short or null");
        if (!Pattern.compile(DELIMITER).matcher(message).find())
            throw new InvalidMessageException("Delimiter not found");

        String[] parts = message.split(DELIMITER);

        if(!message.startsWith(RETURN))
            throw new InvalidMessageException("Wrong message type");
        if(parts.length < 1)
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
        return (E) returnValue;
    }

}
