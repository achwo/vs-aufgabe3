package mware_lib;

import mware_lib.networking.Request;
import mware_lib.protocol.Message;
import mware_lib.protocol.ObjectReference;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NameServiceProxy extends NameService {

    private final ReferenceManager referenceManager;
    private final String objectReference;
    private final int localPort;

    public NameServiceProxy(
            String nameServiceHost, int nameServicePort,
            ReferenceManager referenceManager, int localPort) {

        this.referenceManager = referenceManager;
        this.localPort = localPort;
        this.objectReference = Protocol.objectReference(
                "nameservice", nameServiceHost, nameServicePort).asString();
    }

    @Override
    public void rebind(Object servant, String name) {
        ObjectReference ref = Protocol.objectReference(servant, getHostname(), localPort);
        referenceManager.putSkeleton(ref.getObjectName(), new Skeleton(servant));
        sendMessage("rebind", ref.asString(), name);
    }

    private String getHostname() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "127.0.0.1";
        }
        return hostname;
    }

    @Override
    public Object resolve(String name) {
        String result = sendMessage("resolve", name);
        Object resultObject = null;

        try {
            ReturnValue<Object> returnValue = Protocol.returnValueFromMessage(result, Object.class);
            resultObject = returnValue.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    private String sendMessage(String methodName, Object... args) {
        Message message = Protocol.messageFromParts(objectReference, methodName, args);
        Request request =
                new Request(message.getHostname(), message.getPort(), message.asString());
        return request.invoke();
    }
}
