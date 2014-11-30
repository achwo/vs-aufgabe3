package mware_lib;

import mware_lib.protocol.Protocol;
import mware_lib.protocol.MethodCall;
import mware_lib.protocol.ReturnSerializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Skeleton {
    private Object servant;

    public Skeleton(Object servant) {
        this.servant = servant;
    }

    public String invoke(String message) throws InvocationTargetException, IllegalAccessException {
        MethodCall deserializer =
                Protocol.methodCallFromMessage(message, servant.getClass());

        Method method = deserializer.getMethod(servant.getClass());
        Object[] args = deserializer.getParams(servant.getClass());
        //things.toArray(new Thing[things.size()])
        Object result = method.invoke(servant, args);

        ReturnSerializer<Object> serializer = new ReturnSerializer<>(result);

        return serializer.serialize();
    }
}
