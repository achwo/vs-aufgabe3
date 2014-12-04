package mware_lib.protocol;

import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReturnValueTest {

    @Test(expected = InvalidMessageException.class)
    public void testEmptyMessage() throws Exception {
        Protocol.returnValueFromMessage("", String.class).getValue();
    }

    @Test(expected = InvalidMessageException.class)
    public void testNullMessage() throws Exception {
        Protocol.returnValueFromMessage(null, String.class).getValue();
    }

    @Test
    public void testReturnMessageIsObject() throws Exception {
        ReturnValue<Object> returnValue =
                Protocol.returnValueFromMessage("return|hallo", Object.class);
        assertEquals("hallo", returnValue.getValue());
    }

    @Test
    public void testReturnMessageIsString() throws Exception {
        ReturnValue<String> returnValue =
                Protocol.returnValueFromMessage("return|hallo", String.class);
        assertEquals("hallo", returnValue.getValue());
    }

    @Test
    public void testReturnMessageIsInteger() throws Exception {
        String expected = "return|15";
        ReturnValue<Integer> returnValue =
                Protocol.returnValueFromMessage(expected, Integer.class);
        assertEquals(new Integer(15), returnValue.getValue());
        assertEquals(expected, returnValue.asString());
    }

    @Test(expected = IllegalTypeException.class)
    public void testReturnMessageIsPrimitiveInt() throws Exception {
        Protocol.returnValueFromMessage("return|15", int.class);
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_WrongKeyword() throws Exception {
        Protocol.returnValueFromMessage("retur|asdf", String.class).getValue();
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_HasNoDelimiter() throws Exception {
        Protocol.returnValueFromMessage("asdf", String.class).getValue();
    }

    @Test
    public void testReturnMessageIsEmptyString() throws Exception {
        String result = Protocol.returnValueFromMessage("return|", String.class).getValue();
        assertEquals("", result);
    }

    @Test
    public void testReturnMessageIsNull() throws Exception {
        ReturnValue<String> stringReturnValue =
                Protocol.returnValueFromMessage("return|null", String.class);
        assertEquals(null, stringReturnValue.getValue());
    }

    @Test
    public void testString() throws Exception {
        ReturnValue value = Protocol.returnValue("hallo");
        assertEquals("return|hallo", value.asString());
        assertEquals("hallo", value.getValue());
    }

    @Test
    public void testEmptyString() throws Exception {
        ReturnValue<String> value = Protocol.returnValue("");
        assertEquals("return|", value.asString());
        assertEquals("", value.getValue());
    }

    @Test
    public void testInteger() throws Exception {
        ReturnValue<Integer> value = Protocol.returnValue(15);
        assertEquals("return|15", value.asString());
        assertEquals(new Integer(15), value.getValue());
    }

    @Test
    public void testNull() throws Exception {
        ReturnValue<String> value = Protocol.returnValue(null);
        assertEquals("return|null", value.asString());
        assertEquals(null, value.getValue());
    }
}