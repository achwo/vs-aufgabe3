package mware_lib;

import mware_lib.networking.Request;
import mware_lib.protocol.Message;
import mware_lib.protocol.ObjectReference;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

public class NameServiceProxy extends NameService {

    private final String nameServiceHost;
    private final int nameServicePort;
    private final ReferenceManager referenceManager;
    private int localPort;

    public NameServiceProxy(String nameServiceHost,
                            int nameServicePort,
                            ReferenceManager referenceManager,
                            int localPort) {
        this.nameServiceHost = nameServiceHost;
        this.nameServicePort = nameServicePort;
        this.referenceManager = referenceManager;
        this.localPort = localPort;
    }

    @Override
    public void rebind(Object servant, String name) {
        ObjectReference ref = Protocol.objectReference(servant, "127.0.0.1", localPort);

        referenceManager.putSkeleton(ref.getObjectName(), new Skeleton(servant));
        Message message =
                Protocol.messageFromParts(nameServiceHost, nameServicePort,
                        "nameservice", "rebind", ref.asString(), name);
        request(message.asString());

    }

    @Override
    public Object resolve(String name) {

        Message message = Protocol.messageFromParts(nameServiceHost, nameServicePort,
                "nameservice", "resolve", name);

        Object resultObject = null;

        try {
            String result = request(message.asString());
            ReturnValue<Object> returnValue =
                    Protocol.returnValueFromMessage(result, Object.class);
            resultObject = returnValue.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    private String request(String message) {
        Request request = new Request(nameServiceHost, nameServicePort, message);
        return request.invoke();
    }

}
