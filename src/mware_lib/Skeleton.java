package mware_lib;

import mware_lib.protocol.MethodCall;
import mware_lib.protocol.ReturnValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static mware_lib.protocol.Protocol.*;

public class Skeleton {
    private final Object servant;

    public Skeleton(Object servant) {
        this.servant = servant;
    }

    public String invoke(String message) {
        MethodCall methodCall =
                methodCallFromMessage(message);

        Method method = methodCall.getMethod(servant.getClass());
        Object[] args = methodCall.getParams(servant.getClass());
        method.setAccessible(true);
        Object result = null;
        try {
            result = method.invoke(servant, args);

        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if(cause instanceof InvalidParamException
                    || cause instanceof OverdraftException) {
                return exceptionValue(cause).asString();
            }
            else e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ReturnValue<Object> returnValue = returnValue(result);

        return returnValue.asString();
    }
}
