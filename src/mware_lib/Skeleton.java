package mware_lib;

import cash_access.InvalidParamException;
import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.MethodCall;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Skeleton {
    private final Object servant;

    public Skeleton(Object servant) {
        this.servant = servant;
    }

    public String invoke(String message) {
        MethodCall deserializer =
                Protocol.methodCallFromMessage(message);

        Method method = deserializer.getMethod(servant.getClass());
        Object[] args = deserializer.getParams(servant.getClass());
        method.setAccessible(true);
        Object result = null;
        try {
            result = method.invoke(servant, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof InvalidParamException) {
                ExceptionValue<InvalidParamException> exceptionValue =
                        Protocol.exceptionValue(
                                (InvalidParamException) e.getCause(),
                                InvalidParamException.class);
                return exceptionValue.asString();
            } else e.printStackTrace();
        }
        ReturnValue<Object> returnValue = Protocol.returnValue(result);

        return returnValue.asString();
    }
}
