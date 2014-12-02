package name_service;

import mware_lib.Skeleton;
import mware_lib.protocol.Message;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.exceptions.InvalidMessageException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NameService implements Runnable {

    private final NameServiceRequestService requestService;
    private final Map<String, Object> names = new HashMap<>();

    public static void main(String[] args) {
        // todo use port from args
        NameService ns = new name_service.NameService(15000);
        Thread nsThread = new Thread(ns);
        nsThread.start();
    }

    public NameService(int port) {
        this.requestService = new NameServiceRequestService(port, this);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void rebind(Object servant, String name) {
        names.put(name, servant);
    }

    @SuppressWarnings("UnusedDeclaration")
    public Object resolve(String name) {
        return names.get(name);
    }

    public void shutdown() {
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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();

            Message message = Protocol.nullMessage();
            try {
                message = Protocol.message(line);
            } catch (InvalidMessageException e) {
                e.printStackTrace();
            }
            String methodCall = message.getMethodCallAsString();

            Skeleton skeleton = new Skeleton(nameService);
            String result = "";
            try {
                result = skeleton.invoke(methodCall);
            } catch (Exception e) {
                e.printStackTrace();
            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.write(result);
            out.newLine();
            out.flush();

            socket.close();
        }
    }

    // listens for incoming connections and delegates the connection to request processor
    private class NameServiceRequestService implements Runnable {
        private final int port;
        private final NameService nameService;
        private ServerSocket serverSocket;
        private boolean running;

        public NameServiceRequestService(int port, NameService nameService) {
            this.port = port;
            this.nameService = nameService;
            try {
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
                    Socket socket = serverSocket.accept();
                    new NameServiceRequestProcessor(socket, nameService);
                }
            } catch (IOException e) {
                running = false;
            }
        }

        public void shutdown() {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
