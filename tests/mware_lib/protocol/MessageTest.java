package mware_lib.protocol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    private Message message;

    @Before
    public void setUp() throws Exception {
        String string = "127.0.0.1|15000|nameService|123456!rebind|servant|name";
        message = Messages.fromString(string);
    }

    @Test
    public void testMessageRecognizesObjectParts() throws Exception {
        assertEquals("127.0.0.1", message.getHostname());
        assertEquals(15000, message.getPort());
        assertEquals("nameService", message.getObjectName());
        assertEquals("123456", message.getHashCode());
    }

    @Test
    public void testRecognizesMethodCallParts() throws Exception {
        assertEquals("rebind|servant|name", message.getMethodCall());
    }

}