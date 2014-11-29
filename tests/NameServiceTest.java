import name_service.NameService;
import org.junit.Test;
import sun.jvm.hotspot.runtime.Threads;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class NameServiceTest {


    @Test
    public void testNetworking() throws Exception {
        // Arrange
        // 1. (NSProxy), hier wir -> NSRequestProcessor:
        // msg = ip|port|NameService|HashCode!rebind|servant|name
        // 2. demarshall Message
        // 3. NS.rebind(servant, name)
        // 4. if exception: answer (?)

        // todo where to get the hashcode from?

        NameService ns = new NameService();

        Thread nameServiceThread = new Thread(ns);
        nameServiceThread.start();

        TestSender sender = new TestSender();
        Thread senderThread = new Thread(sender);
        senderThread.start();

        senderThread.join();
        nameServiceThread.join();

        // Act
        // Assert
        assertEquals("servant", ns.resolve("name"));
//        assertEquals(true, false);

//        socket.close();
    }

    public class TestSender implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket("127.0.0.1", 15000);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String message = "127.0.0.1|15000|NameService|woher!rebind|servant|name";

                out.write(message);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
