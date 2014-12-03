package mware_lib.protocol;



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

    public static Message message(String message) {
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

    public static String returnMessageType(String message) {
        return ProtocolHelper.returnMessageType(message);
    }

    public static <E> ReturnValue<E> returnValueFromMessage(String message, Class<E> type) {
        return new ReturnValueFromMessage<>(message, type);
    }

    public static <E> ReturnValue<E> returnValue(E value) {
        ReturnValue<E> returnValue;
        if(value == null) returnValue = nullReturnValue();
        else returnValue = new ReturnValueFromValue<>(value);

        return returnValue;
    }

    public static <E extends Throwable> ExceptionValue<E> exceptionValueFromMessage(String message, Class<E> exceptionClass) {
        return new ExceptionValueFromMessage<>(message);
    }

    public static <E extends Throwable> ExceptionValue<E> exceptionValue(E e, Class<E> type) {
        return new ExceptionValueFromThrowable<>(e, type);
    }

    private static class NullMessage implements Message {

        private final String nullMessage = "NullMessage";

        @Override
        public String getHostname() {
            return nullMessage;
        }

        @Override
        public int getPort() {
            return 0;
        }

        @Override
        public String getObjectName() {
            return nullMessage;
        }

        @Override
        public int getHashCode() {
            return 0;
        }

        @Override
        public String getMethodCallAsString() {
            return nullMessage;
        }

        @Override
        public String asString() {
            return nullMessage;
        }

        @Override
        public String toString() {
            return nullMessage;
        }
    }

    private static class NullReturnValue implements ReturnValue<String> {

        private final String nullReturnValue = "return|null";

        @Override
        public String getValue() {
            return null;
        }

        @Override
        public String asString() {
            return nullReturnValue;
        }

        @Override
        public String toString() {
            return nullReturnValue;
        }
    }
}
