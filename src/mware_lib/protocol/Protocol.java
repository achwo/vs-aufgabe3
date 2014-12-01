package mware_lib.protocol;

import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;


public class Protocol {

    public static final String RETURN = "return";
    public static final String EXCEPTION = "exception";
    public static final String CALL_DELIMITER = "!";
    public static final String DELIMITER = "|";
    public static final String REGEX_DELIMITER = "\\|";
    public static final String OBJECT_DELIMITER = ":";
    public static final String REGEX_OBJECT_DELIMITER = "\\:";
    private static final ReturnValue NULL_RETURN = new NullReturnValue();
    private static final NullMessage NULL_MESSAGE = new NullMessage();

    public static Message message(String message) throws InvalidMessageException {
        return new MessageImpl(message);
    }

    public static Message messageFromParts(String hostname, int port,
                                           Object object, String methodName,
                                           Object... methodParams) {
        return new MessageImpl(hostname, port, object, methodName, methodParams);
    }

    public static MethodCall methodCallFromMessage(String message) {
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

    public static Message messageFromParts(String objectReference, String deposit, Object... args) {
        return new MessageImpl(objectReference, deposit, args);
    }

    public static ObjectReference objectReference(Object servant, String hostname, int port) {
        return new ObjectReferenceFromParts(servant, hostname, port);
    }

    public static ObjectReference objectReferenceFromMessage(String message) {
        return new ObjectReferenceFromMessage(message);
    }

    public static Class<?> wrap(Class<?> primitive) {
        return ProtocolHelper.wrapPrimitive(primitive);
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
        public Object getObject() {
            return null;
        }

        @Override
        public int getHashCode() {
            return 0;
        }

        @Override
        public String getMethodCallAsString() {
            return null;
        }

        @Override
        public String asString() {
            return null;
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
