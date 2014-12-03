package mware_lib.protocol;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class ProtocolHelper {
    private static final Map<Class<?>, Class<?>> wrappedPrimitives = new HashMap<>();

    static {
        wrappedPrimitives.put(boolean.class, Boolean.class);
        wrappedPrimitives.put(byte.class, Byte.class);
        wrappedPrimitives.put(char.class, Character.class);
        wrappedPrimitives.put(double.class, Double.class);
        wrappedPrimitives.put(float.class, Float.class);
        wrappedPrimitives.put(int.class, Integer.class);
        wrappedPrimitives.put(long.class, Long.class);
        wrappedPrimitives.put(short.class, Short.class);
        wrappedPrimitives.put(void.class, Void.class);
    }

    static Method findMethod(String methodName, Class subject) {
        for (Method m : subject.getMethods())
            if (Objects.equals(m.getName(), methodName)) return m;
        return null;
    }

    static Class<?> wrapPrimitive(Class<?> primitive) {
        return wrappedPrimitives.get(primitive);
    }

    static String returnMessageType(String message) {
        String type = "";
        if(message.startsWith(Protocol.EXCEPTION)) type = Protocol.EXCEPTION;
        else if(message.startsWith(Protocol.RETURN)) type = Protocol.RETURN;

        return type;
    }
}
