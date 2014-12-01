package mware_lib.protocol;

public class ExceptionValueFromMessage<E> implements ExceptionValue<E> {

    public ExceptionValueFromMessage(String message) {

    }

    // todo implement
    @Override
    public E getValue() {
        return null;
    }

    @Override
    public Class<E> getType() {
        return null;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    public String toString() {
        return asString();
    }
}
