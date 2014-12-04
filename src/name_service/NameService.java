package name_service;

import mware_lib.Logger;
import mware_lib.Skeleton;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static mware_lib.protocol.Protocol.*;

public class NameService implements Runnable {

    private final NameServiceRequestService requestService;
    private final Map<String, Object> names = new HashMap<>();
    private final Logger logger;
    private final boolean logging;

    public static void main(String[] args) {
        int port = 15000;
        if (args.length != 0) port = Integer.parseInt(args[0]);

        new Thread(new NameService(port, true)).start();
    }

    public NameService(int port, boolean logging) {
        this.logging = logging;
        logger = new Logger(this, this.logging);
        logger.log("test");
        this.requestService = new NameServiceRequestService(port, this);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void rebind(Object servant, String name) {
        logger.log("rebind(" + servant + ", " + name + ")");
        names.put(name, servant);
    }

    @SuppressWarnings("UnusedDeclaration")
    public Object resolve(String name) {
        logger.log("resolve(" + name + ")");
        return names.get(name);
    }

    public void shutdown() {
        logger.log("shutdown()");
        requestService.shutdown();
    }


    @Override
    public void run() {
        Thread t = new Thread(requestService);
        t.run();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // gets message via socket and converts them to method calls
    private class NameServiceRequestProcessor {
        public NameServiceRequestProcessor(Socket socket, NameService nameService) throws IOException {
            Logger logger = new Logger(this, logging);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();

            String methodCall = message(line).getMethodCallAsString();
            logger.log("Got message: " + methodCall + " from " + socket.getInetAddress());

            Skeleton skeleton = new Skeleton(nameService);
            String result = skeleton.invoke(methodCall);
g
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.write(result);
            out.newLine();
            out.flush();

            logger.log("Sent message {" + result + "} to " + socket.getInetAddress());

            socket.close();
        }
    }

    // listens for incoming connections and delegates the connection to request processor
    private class NameServiceRequestService implements Runnable {
        private final int port;
        private final NameService nameService;
        private final Logger logger;
        private ServerSocket serverSocket;
        private boolean running;

        public NameServiceRequestService(int port, NameService nameService) {
            this.logger = new Logger(this, logging);
            this.port = port;
            this.nameService = nameService;
            try {
                logger.log("Open ServerSocket on port " + this.port);
                this.serverSocket = new ServerSocket(this.port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            running = true;
            try {

                while (running) {
                    this.logger.log("Waiting for incoming connection");
                    Socket socket = serverSocket.accept();
                    this.logger.log("Incoming connection from " + socket.getInetAddress());
                    new NameServiceRequestProcessor(socket, nameService);
                }
            } catch (IOException e) {
                running = false;
            }
        }

        public void shutdown() {
            try {
                logger.log("shutdown()");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
