package mware_lib.protocol;

import mware_lib.protocol.exceptions.InvalidMessageException;

public interface ReturnValue<E> {
    E getValue() throws InvalidMessageException;

    String asString();
}
