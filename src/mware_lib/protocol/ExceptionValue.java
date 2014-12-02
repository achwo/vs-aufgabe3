package mware_lib.protocol;

public interface ExceptionValue<E extends Throwable> {

    public E getValue();

    public Class<E> getType();

    public String asString();
}
