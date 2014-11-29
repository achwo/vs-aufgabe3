package mware_lib.protocol;

import java.util.Objects;

public class ReturnSerializer<E> {
    private E value;

    public ReturnSerializer(E value) {
        this.value = value;
    }

    public String serialize() {
        return "return|" + Objects.toString(value);
    }
}
