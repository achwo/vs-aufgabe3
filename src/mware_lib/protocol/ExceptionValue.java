package mware_lib.protocol;

interface ExceptionValue<E> {

    public E getValue();

    public Class<E> getType();

    public String asString();
}
