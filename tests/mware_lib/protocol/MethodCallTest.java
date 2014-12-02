package mware_lib.protocol;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MethodCallTest {

    private String messageWithParams;
    private Method expectedWithParams;
    private String methodNameWithoutParams;
    private String methodNameWithParams;
    private Method expectedWithoutParams;

    @Before
    public void setUp() throws Exception {
        methodNameWithParams = "add";
        messageWithParams = methodNameWithParams + "|1|2";
        expectedWithParams = TestObject.class.getMethod(methodNameWithParams, Integer.class, Integer.class);

        methodNameWithoutParams = "remove";
        expectedWithoutParams = TestObject.class.getMethod(methodNameWithoutParams);
    }

    @Test
    public void testFromMessage() throws Exception {
        MethodCall methodCallWithParamsFromMsg = Protocol.methodCallFromMessage(messageWithParams);

        assertEquals(expectedWithParams, methodCallWithParamsFromMsg.getMethod(TestObject.class));
        assertEquals(1, methodCallWithParamsFromMsg.getParams(TestObject.class)[0]);
        assertEquals(2, methodCallWithParamsFromMsg.getParams(TestObject.class)[1]);
        assertEquals(messageWithParams, methodCallWithParamsFromMsg.asString());
    }

    @Test
    public void testFromMethod() throws Exception {
        MethodCall methodCallWithParams = Protocol.methodCall(methodNameWithParams, 1, 2);

        assertEquals(expectedWithParams, methodCallWithParams.getMethod(TestObject.class));
        assertEquals(messageWithParams, methodCallWithParams.asString());
    }

    @Test
    public void testNoParamsFromMessage() throws Exception {
        MethodCall methodCall = Protocol.methodCallFromMessage(methodNameWithoutParams);

        assertEquals(expectedWithoutParams, methodCall.getMethod(TestObject.class));
        assertEquals(methodNameWithoutParams, methodCall.asString());

    }

    @Test
    public void testNoParams() throws Exception {
        MethodCall methodCall = Protocol.methodCall(methodNameWithoutParams);

        assertEquals(expectedWithoutParams, methodCall.getMethod(TestObject.class));
        assertEquals(methodNameWithoutParams, methodCall.asString());

    }

    @SuppressWarnings("UnusedDeclaration")
    private class TestObject {
        public void add(Integer i, Integer j) {}
        public void remove() {}
    }
}