package mware_lib;

import mware_lib.networking.Request;
import mware_lib.protocol.Message;
import mware_lib.protocol.Protocol;

public abstract class Proxy {
    protected String sendMessage(String objectReference, String methodName, Object... args) {
        Message message = Protocol.messageFromParts(objectReference, methodName, args);
        Request request =
                new Request(message.getHostname(), message.getPort(), message.asString());
        return request.invoke();
    }

    protected String sendMessage(String objectReference, String methodName) {
        Message message = Protocol.messageFromParts(objectReference, methodName);
        Request request =
                new Request(message.getHostname(), message.getPort(), message.asString());
        return request.invoke();
    }
}
