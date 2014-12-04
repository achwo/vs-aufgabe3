package mware_lib.protocol;

import mware_lib.protocol.exceptions.InvalidMessageException;

public interface ExceptionValue<E extends Throwable> extends ReturnValue<E> {

    public E getValue() throws InvalidMessageException;
    public Class<E> getType();
    public String asString();
    public String getTypeAsString();
}
