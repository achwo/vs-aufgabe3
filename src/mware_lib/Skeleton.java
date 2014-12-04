package mware_lib;

import mware_lib.protocol.MethodCall;
import mware_lib.protocol.ReturnValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static mware_lib.protocol.Protocol.*;

public class Skeleton {
    private final Object servant;
    private final Logger logger;

    public Skeleton(Object servant) {
        this.logger = new Logger(this, ObjectBroker.LOGGING);
        this.servant = servant;
    }

    public String invoke(String message) {
        logger.log("invoke(" + message + ")");
        MethodCall methodCall =
                methodCallFromMessage(message);
        logger.log("methodCall = " + methodCall);

        Method method = methodCall.getMethod(servant.getClass());
        Object[] args = methodCall.getParams(servant.getClass());
        method.setAccessible(true);
        Object result = null;
        try {
            logger.log("Invoking method");
            result = method.invoke(servant, args);

        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if(isOneOfOurExceptions(cause)) return exceptionValue(cause).asString();
            else e.printStackTrace();
        } catch (IllegalAccessException e) {
            logger.log(e.getMessage());
        }
        ReturnValue<Object> returnValue = returnValue(result);

        return returnValue.asString();
    }

    private boolean isOneOfOurExceptions(Throwable cause) {
        return cause instanceof bank_access.InvalidParamException
                || cause instanceof bank_access.OverdraftException
                || cause instanceof cash_access.InvalidParamException
                || cause instanceof cash_access.OverdraftException;
    }

    @Override
    public String toString() {
        return "Skeleton(" + servant + ")";
    }
}
