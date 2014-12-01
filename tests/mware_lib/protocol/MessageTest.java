package mware_lib.protocol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    private Message messageFromString;

    @Before
    public void setUp() throws Exception {
        String string = "127.0.0.1:15000:nameService:123456!rebind|servant|name";
        messageFromString = Protocol.message(string);
    }

    @Test
    public void testMessageRecognizesObjectParts() throws Exception {
        assertEquals("127.0.0.1", messageFromString.getHostname());
        assertEquals(15000, messageFromString.getPort());
        assertEquals("nameService", messageFromString.getObjectName());
        assertEquals(123456, messageFromString.getHashCode());
        assertEquals("rebind|servant|name", messageFromString.getMethodCallAsString());
    }

    @Test
    public void testMessageFromParts() throws Exception {
        Message messageFromParts =
                Protocol.messageFromParts("127.0.0.1", 15000, "hallo", "startsWith", "h", 15);

        String expected = "127.0.0.1:15000:hallo:99043158!startsWith|h|15";

        assertEquals("127.0.0.1", messageFromParts.getHostname());
        assertEquals(15000, messageFromParts.getPort());
        assertEquals("hallo", messageFromParts.getObjectName());
        assertEquals("startsWith|h|15", messageFromParts.getMethodCallAsString());
        assertEquals(expected, messageFromParts.asString());
    }
}