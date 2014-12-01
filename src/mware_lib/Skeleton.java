package mware_lib;

import mware_lib.protocol.Protocol;
import mware_lib.protocol.MethodCall;
import mware_lib.protocol.ReturnValue;

import java.lang.reflect.Method;

public class Skeleton {
    private Object servant;

    public Skeleton(Object servant) {
        this.servant = servant;
    }

    public String invoke(String message) {
        MethodCall deserializer =
                Protocol.methodCallFromMessage(message);

        Method method = deserializer.getMethod(servant.getClass());
        Object[] args = deserializer.getParams(servant.getClass());
        String value = "";
        try {
            method.setAccessible(true);
            Object result = method.invoke(servant, args);
            ReturnValue<Object> returnValue = Protocol.returnValue(result);
            value = returnValue.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}
