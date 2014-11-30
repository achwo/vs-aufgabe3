package name_service;

import mware_lib.Skeleton;
import mware_lib.protocol.exceptions.InvalidMessageException;
import mware_lib.protocol.Message;
import mware_lib.protocol.Protocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NameService implements Runnable {

    private final NameServiceRequestService requestService;
    private Map<String, Object> names = new HashMap<>();

    public NameService(int port) {
        this.requestService = new NameServiceRequestService(port, this);
    }

    public void rebind(Object servant, String name) {
        names.put(name, servant);
    }

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

    // erhaelt ueber socket ne message und wandelt sie in aufrufe um
    private class NameServiceRequestProcessor {
        public NameServiceRequestProcessor(Socket socket, NameService nameService) throws IOException{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();

            Message message = Protocol.nullMessage();
            try {
                message = Protocol.messageFromString(line);
            } catch (InvalidMessageException e) {
                e.printStackTrace();
            }
            String methodCall = message.getMethodCall();

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
            // 1. nachricht auseinandernehmen
            // "127.0.0.1|15000|NameService!woher|rebind|servant|name";
            // split: Nachricht -> Objektreferenz | Aufruf
            // split: Aufruf -> Methode | Params...
        }
    }

    // hoert auf eingehende verbindungen und delegiert die verbindung
    private class NameServiceRequestService implements Runnable {
        private final int port;
        private NameService nameService;
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

                while(running) {
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
