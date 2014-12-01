package mware_lib.protocol;

import java.util.Objects;

class ReturnValueFromValue<E> implements ReturnValue<E> {

    private final E value;
    private String message;

    ReturnValueFromValue(E value) {
        this.value = value;
    }

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public String asString() {
        if (message == null) createMessage();
        return message;
    }

    private void createMessage() {
        message = String.join(Protocol.DELIMITER, Protocol.RETURN, Objects.toString(value));
    }

    @Override
    public String toString() {
        return asString();
    }
}
