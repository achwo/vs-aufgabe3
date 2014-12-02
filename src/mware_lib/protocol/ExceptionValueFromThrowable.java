package mware_lib.protocol;

public class ExceptionValueFromThrowable<E extends Throwable> implements ExceptionValue<E> {
    private final Class<E> type;
    private final String message;
    private final E value;

    public ExceptionValueFromThrowable(E e, Class<E> type) {
        this.type = type;
        this.message = e.getMessage();
        this.value = e;
    }

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public Class<E> getType() {
        return type;
    }

    @Override
    public String asString() {
        return String.join(Protocol.DELIMITER, Protocol.EXCEPTION,
                type.getCanonicalName(), message);
    }

    @Override
    public String toString() {
        return asString();
    }
}
