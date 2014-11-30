package mware_lib.protocol;

import mware_lib.protocol.exceptions.InvalidMessageException;


public class Protocol {

    public static final NullMessage NULL_MESSAGE = new NullMessage();

    public static Message messageFromString(String message) throws InvalidMessageException {
        return new MessageImpl(message);
    }

    public static MessageImpl messageFromParts(String hostname, int port, Object object) {
        return new MessageImpl(hostname, port, object);
    }

    public static MethodCall methodCallFromMessage(String message, Class<?> type) {
        return new MethodCallFromMessage(message);
    }

    public static MethodCall methodCall(String methodName, Object... args) {
        return new MethodCallFromMethodWithParams(methodName, args);
    }


    public static Message nullMessage() {
        return NULL_MESSAGE;
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

}
