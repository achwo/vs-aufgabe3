package mware_lib.protocol;

import java.lang.reflect.Method;
import java.util.Objects;

public class ProtocolHelper {
    protected static Method findMethod(String methodName, Class subject) {
        for(Method m: subject.getMethods())
            if (Objects.equals(m.getName(), methodName)) return m;
        return null;
    }
}
