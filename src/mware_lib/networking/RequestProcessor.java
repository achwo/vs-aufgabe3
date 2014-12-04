package mware_lib.networking;

import mware_lib.Logger;
import mware_lib.ObjectBroker;
import mware_lib.Skeleton;
import mware_lib.protocol.Message;
import mware_lib.protocol.Protocol;

import java.io.*;
import java.net.Socket;

class RequestProcessor implements Runnable {

    private final Socket socket;
    private final ObjectBroker broker;
    private final Logger logger =  new Logger(this, ObjectBroker.LOGGING);

    public RequestProcessor(Socket socket, ObjectBroker broker) {

        this.socket = socket;
        this.broker = broker;
    }

    @Override
    public void run() {
        Message message = readFromSocket();

        Skeleton skeleton = broker.getSkeleton(message.getObjectName());
        String result = skeleton.invoke(message.getMethodCallAsString());

        sendToSocket(result);
    }

    private void sendToSocket(String result) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(result);
            out.newLine();
            out.flush();

            socket.close();
        } catch (IOException e) {
            logger.log(e.getMessage());
        }
    }

    private Message readFromSocket() {
        Message message = Protocol.nullMessage();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();
            message = Protocol.message(line);
        } catch (Exception e) {
            logger.log(e.getMessage());
        }
        return message;
    }
}
