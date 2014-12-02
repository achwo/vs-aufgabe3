package mware_lib.protocol;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class MethodCallFromMessage implements MethodCall {
    private final String methodName;
    private final String[] stringParams;
    private Method method;
    private Object[] params;


    MethodCallFromMessage(String message) {
        String[] parts = message.split("\\|", 2);
        methodName = parts[0];
        stringParams = parts.length > 1 ? parts[1].split("\\|") : new String[0];
    }

    private Object[] findMethodParams(String[] stringParams, Method method) {
        Object[] returnObjects = new Object[stringParams.length];
        int i = 0;
        for (Class<?> paramType : method.getParameterTypes()) {
            Method valueOf;
            try {
                if (paramType.isPrimitive())
                    paramType = Protocol.wrap(paramType);
                valueOf = paramType.getMethod("valueOf", String.class);
                returnObjects[i] = valueOf.invoke(null, stringParams[i]);
            } catch (NoSuchMethodException e) {
                returnObjects[i] = stringParams[i];
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        return returnObjects;
    }

    @Override
    public Method getMethod(Class<?> type) {
        if (method == null) {
            method = ProtocolHelper.findMethod(methodName, type);
        }
        return method;
    }

    @Override
    public Object[] getParams(Class<?> type) {
        if (params == null) {
            params = findMethodParams(stringParams, getMethod(type));
        }
        return params;
    }

    @Override
    public String asString() {
        List<String> strings = new ArrayList<>();
        strings.add(methodName);

        for (Object o : stringParams) {
            strings.add(Objects.toString(o));
        }

        return StringUtils.join(strings, Protocol.DELIMITER);
    }

    @Override
    public String toString() {
        return asString();
    }
}
