package mware_lib;

import mware_lib.protocol.MethodCallDeserializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Skeleton {
    private Object servant;

    public Skeleton(Object servant) {
        this.servant = servant;
    }

    public void invoke(String message) throws InvocationTargetException, IllegalAccessException {
        MethodCallDeserializer deserializer =
                new MethodCallDeserializer(message, servant.getClass());

        Method method = deserializer.getMethod();
        Object[] args = deserializer.getParams();
        //things.toArray(new Thing[things.size()])
        method.invoke(servant, args);

    }
}
