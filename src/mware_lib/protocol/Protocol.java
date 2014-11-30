package mware_lib.protocol;

import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;


public class Protocol {

    public static final NullMessage NULL_MESSAGE = new NullMessage();
    public static final String REGEX_DELIMITER = "\\|";
    public static final String RETURN = "return";
    public static final String EXCEPTION = "exception";
    public static final String CALL_DELIMITER = "!";
    public static final String DELIMITER = "|";
    private static final ReturnValue NULL_RETURN = new NullReturnValue();

    public static Message message(String message) throws InvalidMessageException {
        return new MessageImpl(message);
    }

    public static Message messageFromParts(String hostname, int port, Object object) {
        return new MessageImpl(hostname, port, object);
    }

    public static MethodCall methodCallFromMessage(String message, Class<?> type) {
        return new MethodCallFromMessage(message);
    }

    public static MethodCall methodCall(String methodName, Object... args) {
        return new MethodCallFromMethodWithParams(methodName, args);
    }

    public static <E> ReturnValue<E> returnValueFromMessage(
            String message, Class<E> type) throws IllegalTypeException {
        return new ReturnValueFromMessage<>(message, type);
    }

    public static <E> ReturnValue<E> returnValue(E value) throws IllegalTypeException {
        return new ReturnValueFromValue<>(value);
    }


    public static Message nullMessage() {
        return NULL_MESSAGE;
    }
    public static ReturnValue nullReturnValue() {
        return NULL_RETURN;
    }

    private static class NullMessage implements Message {

        @Override
        public String getHostname() {
            return null;
        }

        @Override
        public int getPort() {
            return 0;
        }

        @Override
        public String getObjectName() {
            return null;
        }

        @Override
        public String getHashCode() {
            return null;
        }

        @Override
        public String getMethodCall() {
            return null;
        }

        @Override
        public void setMethod(String methodName, Object... args) {

        }
    }

    private static class NullReturnValue implements ReturnValue<Object> {
        @Override
        public Object getValue() throws InvalidMessageException {
            return null;
        }

        @Override
        public String asString() {
            return null;
        }
    }
}
