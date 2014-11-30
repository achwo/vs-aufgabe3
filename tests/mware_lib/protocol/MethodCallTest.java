package mware_lib.protocol;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MethodCallTest {


    @Test
    public void testFromMessage() throws Exception {
        String message = "add|1|2";

        MethodCall methodCall = Protocol.methodCallFromMessage(message, TestObject.class);

        Method expected = TestObject.class.getMethod("add", Integer.class, Integer.class);

        assertEquals(expected, methodCall.getMethod(TestObject.class));
        assertEquals(1, methodCall.getParams(TestObject.class)[0]);
        assertEquals(2, methodCall.getParams(TestObject.class)[1]);
        assertEquals(message, methodCall.asString());
    }

    @Test
    public void testFromMethod() throws Exception {
        MethodCall methodCall = Protocol.methodCall("add", 1, 2);

        Method expected = TestObject.class.getMethod("add", Integer.class, Integer.class);

        assertEquals(expected, methodCall.getMethod(TestObject.class));
        assertEquals("add|1|2", methodCall.asString());
    }

    @SuppressWarnings("UnusedDeclaration")
    private class TestObject {
        public void add(Integer i, Integer j) {}
    }
}