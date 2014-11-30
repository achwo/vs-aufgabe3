package mware_lib;

import mware_lib.protocol.Message;
import mware_lib.protocol.Messages;
import mware_lib.protocol.ReturnDeserializer;

import java.io.*;
import java.net.Socket;

public class NameServiceProxy extends NameService {

    private final String nameServiceHost;
    private final int nameServicePort;
    private final ReferenceManager referenceManager;

    public NameServiceProxy(String nameServiceHost,
                            int nameServicePort,
                            ReferenceManager referenceManager) {
        this.nameServiceHost = nameServiceHost;
        this.nameServicePort = nameServicePort;
        this.referenceManager = referenceManager;
    }

    @Override
    public void rebind(Object servant, String name) {
        referenceManager.putSkeleton(servant, new Skeleton(servant));

        // todo serialize object
        // todo serialize message


        String host = "127.0.0.1";
        int port = 15001;
        Object object = servant;
        Message message = null;
        message = Messages.fromParts(host, port, object);
        message.setMethod("rebind", servant, name);

        String request = "127.0.0.1|15001|nameservice|bla!rebind|servant|name";
        try {
            request(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object resolve(String name) {
        // todo echt machen (ueber netzwerkkram)
//        return nameService.resolve(name);

        // todo serialize method call
        // todo build request
        String request = "127.0.0.1|15001|nameservice|bla!resolve|name";
        String result;
        Object resultObject = null;

        try {
            result = request(request);
            ReturnDeserializer<Object> returnDeserializer =
                    new ReturnDeserializer<>(result, Object.class);
            resultObject = returnDeserializer.deserialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    private String request(String message) throws IOException {
        // todo use request object
        Socket socket = new Socket(nameServiceHost, nameServicePort);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(message);
        out.newLine();
        out.flush();

        String result = in.readLine();


        socket.close();
        return result;
    }

}
