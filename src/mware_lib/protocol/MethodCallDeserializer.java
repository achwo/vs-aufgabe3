package mware_lib.protocol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class MethodCallDeserializer {
    private Method method;
    private Object[] params;

    public MethodCallDeserializer(String message, Class<?> type) {
        String[] parts = message.split("\\|", 2);
        try {
            method = findMethod(parts[0], type);
            params = findMethodParams(parts[1].split("\\|"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object[] findMethodParams(String[] stringParams)
            throws InvocationTargetException, IllegalAccessException {
        Object[] returnObjects = new Object[stringParams.length];
        int i = 0;
        for (Class<?> paramType : method.getParameterTypes()) {
            Method valueOf;
            try {
                valueOf = paramType.getMethod("valueOf", String.class);
                returnObjects[i] = valueOf.invoke(null, stringParams[i]);
            } catch (NoSuchMethodException e) {
                returnObjects[i] = stringParams[i];
            }
            i++;
        }
        return returnObjects;
    }

    private Method findMethod(String methodName, Class subject) {
        for(Method m: subject.getMethods())
            if (Objects.equals(m.getName(), methodName)) return m;
        return null;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParams() {
        return params;
    }
}
