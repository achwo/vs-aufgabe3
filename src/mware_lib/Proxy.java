package mware_lib;

import bank_access.InvalidParamException;
import bank_access.OverdraftException;
import mware_lib.networking.Request;
import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.Message;
import mware_lib.protocol.Protocol;

import java.util.Objects;

import static mware_lib.protocol.Protocol.EXCEPTION;
import static mware_lib.protocol.Protocol.exceptionValueFromMessage;
import static mware_lib.protocol.Protocol.returnMessageType;

public abstract class Proxy {
    protected String sendMessage(String objectReference, String methodName, Object... args) {
        Message message = Protocol.messageFromParts(objectReference, methodName, args);
        return request(message);
    }

    protected String sendMessage(String objectReference, String methodName) {
        Message message = Protocol.messageFromParts(objectReference, methodName);
        return request(message);
    }

    private String request(Message message) {
        Request request =
                new Request(message.getHostname(), message.getPort(), message.asString());
        return request.invoke();
    }

}
