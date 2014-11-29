package mware_lib.protocol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MethodCallDeserializerTest {

    private MethodCallDeserializer deserializer;

    @Before
    public void setUp() throws Exception {
        String message = "127.0.0.1|15000|nameService|woher!rebind|servant|name";
        deserializer = new MethodCallDeserializer(message);
    }

    @Test
    public void testMessageRecognizesObjectParts() throws Exception {
        assertEquals("127.0.0.1", deserializer.getHostname());
        assertEquals(15000, deserializer.getPort());
        assertEquals("nameService", deserializer.getObjectName());
        assertEquals("woher", deserializer.getHashCode());
    }

    @Test
    public void testRecognizesMethodCallParts() throws Exception {
        assertEquals("rebind|servant|name", deserializer.getMethodCall());
    }

}