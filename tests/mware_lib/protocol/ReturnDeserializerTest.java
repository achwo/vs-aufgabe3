package mware_lib.protocol;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReturnDeserializerTest {

    // 1. nachricht auseinandernehmen
    // "127.0.0.1|15000|NameService!woher|rebind|servant|name";
    // split: Nachricht -> Objektreferenz | Aufruf
    // split: Aufruf -> Methode | Params...

    @Test(expected = InvalidMessageException.class)
    public void testEmptyMessage() throws Exception {
        new ReturnDeserializer<>("", String.class).parse();
    }

    @Test(expected = InvalidMessageException.class)
    public void testNullMessage() throws Exception {
        new ReturnDeserializer<>(null, String.class).parse();
    }

    @Test
    public void testReturnMessageIsObject() throws Exception {
        ReturnDeserializer<Object> deserializer =
                new ReturnDeserializer<>("return|hallo", Object.class);
        assertEquals("hallo", deserializer.parse());
    }

    @Test
    public void testReturnMessageIsString() throws Exception {
        ReturnDeserializer<String> deserializer =
                new ReturnDeserializer<>("return|hallo", String.class);
        assertEquals("hallo", deserializer.parse());
    }

    @Test
    public void testReturnMessageIsInteger() throws Exception {
        ReturnDeserializer<Integer> deserializer =
                new ReturnDeserializer<>("return|15", Integer.class);
        assertEquals(new Integer(15), deserializer.parse());
    }

    @Test(expected = IllegalTypeException.class)
    public void testReturnMessageIsPrimitiveInt() throws Exception {
        new ReturnDeserializer<>("return|15", int.class);
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_WrongKeyword() throws Exception {
        new ReturnDeserializer<>("retur|asdf", String.class).parse();
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_HasNoDelimiter() throws Exception {
        new ReturnDeserializer<>("asdf", String.class).parse();
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_HasNoReturnValue() throws Exception {
        new ReturnDeserializer<>("return|", String.class).parse();
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_HasNullReturnValue() throws Exception {
        new ReturnDeserializer<>("return|null", String.class).parse();
    }


    // todo can return value be null?
    // todo exception|type ?

    // todo exception|type|message
    // todo exception|type|


}