package mware_lib.protocol;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Messages {

    public static final NullMessage NULL_MESSAGE = new NullMessage();

    public static Message fromString(String message) throws InvalidMessageException {
        return new MessageImpl(message);
    }

    public static MessageImpl fromParts(String hostname, int port, Object object) {
        return new MessageImpl(hostname, port, object);
    }

    public static Message nullMessage() {
        return NULL_MESSAGE;
    }

    public static class MessageImpl implements Message {

        public static final String DELIMITER = "!";
        private String hostname;
        private final int port;
        private final Object object;
        private final int hashCode;
        private String methodCall;


        private MessageImpl(String hostname, int port, Object object) {
            this.hostname = hostname;
            this.port = port;
            this.object = object;
            this.hashCode = Objects.hashCode(object);
        }

        private MessageImpl(String message) throws InvalidMessageException {
            String[] split = message.split(DELIMITER);

            if(split.length < 2)
                throw new InvalidMessageException("No Delimiter '" + DELIMITER + "' found.");

            String objReference = split[0];
            methodCall = split[1];

            String[] objParts = objReference.split("\\|");
            hostname = objParts[0];
            port = Integer.valueOf(objParts[1]);
            object = objParts[2];
            hashCode = Integer.valueOf(objParts[3]);
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
        public String getObjectName() {
            return Objects.toString(object);
        }

        @Override
        public String getHashCode() {
            return Objects.toString(hashCode);
        }

        @Override
        public String getMethodCall() {
            return methodCall;
        }

        public void setMethod(String methodName, Object... args) {
            List<String> strings = new ArrayList<>();
            strings.add(methodName);

            for(Object o: args) {
                strings.add(Objects.toString(o));
            }

            methodCall = StringUtils.join(Arrays.asList(strings), "|");
        }


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
