package mware_lib.protocol;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MethodCallFromMethodWithParams implements MethodCall {
    private final String methodName;
    private final Object[] params;
    private Method method;

    MethodCallFromMethodWithParams(String methodName, Object[] params) {
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public Method getMethod(Class<?> type) {
        if (method == null)
            method = ProtocolHelper.findMethod(methodName, type);
        return method;
    }

    @Override
    public Object[] getParams(Class<?> type) {
        return params;
    }

    @Override
    public String asString() {
        List<String> strings = new ArrayList<>();
        strings.add(methodName);

        for (Object o : params) {
            strings.add(Objects.toString(o));
        }

        return Protocol.join(strings, "|");
    }

    @Override
    public String toString() {
        return asString();
    }
}
