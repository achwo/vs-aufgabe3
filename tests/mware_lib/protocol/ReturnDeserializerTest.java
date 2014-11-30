package mware_lib.protocol;

import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReturnDeserializerTest {

    // 1. nachricht auseinandernehmen
    // "127.0.0.1|15000|NameService!woher|rebind|servant|name";
    // split: Nachricht -> Objektreferenz | Aufruf
    // split: Aufruf -> Methode | Params...

    @Test(expected = InvalidMessageException.class)
    public void testEmptyMessage() throws Exception {
        new ReturnDeserializer<>("", String.class).deserialize();
    }

    @Test(expected = InvalidMessageException.class)
    public void testNullMessage() throws Exception {
        new ReturnDeserializer<>(null, String.class).deserialize();
    }

    @Test
    public void testReturnMessageIsObject() throws Exception {
        ReturnDeserializer<Object> deserializer =
                new ReturnDeserializer<>("return|hallo", Object.class);
        assertEquals("hallo", deserializer.deserialize());
    }

    @Test
    public void testReturnMessageIsString() throws Exception {
        ReturnDeserializer<String> deserializer =
                new ReturnDeserializer<>("return|hallo", String.class);
        assertEquals("hallo", deserializer.deserialize());
    }

    @Test
    public void testReturnMessageIsInteger() throws Exception {
        ReturnDeserializer<Integer> deserializer =
                new ReturnDeserializer<>("return|15", Integer.class);
        assertEquals(new Integer(15), deserializer.deserialize());
    }

    @Test(expected = IllegalTypeException.class)
    public void testReturnMessageIsPrimitiveInt() throws Exception {
        new ReturnDeserializer<>("return|15", int.class);
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_WrongKeyword() throws Exception {
        new ReturnDeserializer<>("retur|asdf", String.class).deserialize();
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_HasNoDelimiter() throws Exception {
        new ReturnDeserializer<>("asdf", String.class).deserialize();
    }

    @Test
    public void testReturnMessageIsEmptyString() throws Exception {
        String result = new ReturnDeserializer<>("return|", String.class).deserialize();
        assertEquals("", result);
    }

    @Test
    public void testReturnMessageIsNull() throws Exception {
        new ReturnDeserializer<>("return|null", String.class).deserialize();
    }
}