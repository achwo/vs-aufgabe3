package mware_lib.protocol;

import java.lang.reflect.Method;

public interface MethodCall {
    Method getMethod(Class<?> type);

    Object[] getParams(Class<?> type);

    String asString();
}
