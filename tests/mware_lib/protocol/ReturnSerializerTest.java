package mware_lib.protocol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReturnSerializerTest {

    @Test
    public void testString() throws Exception {
        ReturnSerializer<String> serializer = new ReturnSerializer<>("hallo");
        assertEquals("return|hallo", serializer.serialize());
    }

    @Test
    public void testEmptyString() throws Exception {
        ReturnSerializer<String> serializer = new ReturnSerializer<>("");
        assertEquals("return|", serializer.serialize());
    }

    @Test
    public void testInteger() throws Exception {
        ReturnSerializer<Integer> serializer = new ReturnSerializer<>(15);
        assertEquals("return|15", serializer.serialize());
    }

    @Test
    public void testNull() throws Exception {
        ReturnSerializer<String> serializer = new ReturnSerializer<>(null);
        assertEquals("return|null", serializer.serialize());
    }
}