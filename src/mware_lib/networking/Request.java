package mware_lib.networking;

import mware_lib.Logger;
import mware_lib.ObjectBroker;

import java.io.*;
import java.net.Socket;

public class Request {
    private final String host;
    private final Integer port;
    private final String message;
    private final Logger logger =  new Logger(this, ObjectBroker.LOGGING);

    public Request(String host, Integer port, String message) {

        this.host = host;
        this.port = port;
        this.message = message;
    }

    public String invoke() {

        String result = "";
        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            out.write(message);
            out.newLine();
            out.flush();

            result = in.readLine();
            socket.close();

        } catch (IOException e) {
            logger.log(e.getMessage());
        }

        return result;
    }
}
