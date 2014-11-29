package mware_lib;

import mware_lib.protocol.MethodCallDeserializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Skeleton<E> {
    private E servant;

    public Skeleton(E servant) {
        this.servant = servant;
    }

    public void invoke(String message) {
        MethodCallDeserializer deserializer = new MethodCallDeserializer(message);

        Method method = deserializer.getMethod();
        Object[] args = deserializer.getParams();
        //things.toArray(new Thing[things.size()])
        try {
            method.invoke(servant, args);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
