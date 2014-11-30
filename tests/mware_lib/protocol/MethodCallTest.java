package mware_lib.protocol;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MethodCallTest {


    @Test
    public void testTestObject() throws Exception {
        String message = "add|1|2";

        MethodCall deserializer =
                new MethodCall(message, TestObject.class);

        Method expected = TestObject.class.getMethod("add", Integer.class, Integer.class);

        assertEquals(expected, deserializer.getMethod());
        assertEquals(1, deserializer.getParams()[0]);
        assertEquals(2, deserializer.getParams()[1]);
    }

    private class TestObject {
        public boolean wasCalled = false;

        public void add(Integer i, Integer j) {
            wasCalled = true;
        }
    }
}