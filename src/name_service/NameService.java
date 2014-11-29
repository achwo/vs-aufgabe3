package name_service;

import mware_lib.Skeleton;
import mware_lib.protocol.MessageDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NameService implements Runnable {

    private final NameServiceRequestService requestService;
    private Map<String, Object> names = new HashMap<>();

    public NameService() {
        this.requestService = new NameServiceRequestService(15000, this);
    }

    public void rebind(Object servant, String name) {
        names.put(name, servant);
    }

    public Object resolve(String name) {
        return names.get(name);
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
            String message = in.readLine();

            MessageDeserializer deserializer = new MessageDeserializer(message);
            String methodCall = deserializer.getMethodCall();

            Skeleton<NameService> skeleton = new Skeleton<>(nameService);
            skeleton.invoke(message);

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

        public NameServiceRequestService(int port, NameService nameService) {
            this.port = port;
            this.nameService = nameService;
        }

        @Override
        public void run() {
            try {
                this.serverSocket = new ServerSocket(this.port);
                Socket socket = serverSocket.accept();

                NameServiceRequestProcessor processor =
                        new NameServiceRequestProcessor(socket, nameService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
