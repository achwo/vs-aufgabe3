package bank_access;

import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.Protocol;

import java.util.Objects;

import static mware_lib.protocol.Protocol.EXCEPTION;
import static mware_lib.protocol.Protocol.exceptionValueFromMessage;
import static mware_lib.protocol.Protocol.returnMessageType;

public class ManagerImplProxy extends ManagerImplBase {
    private final String objectReference;

    public ManagerImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }

    @Override
    public String createAccount(String owner, String branch) throws InvalidParamException {
     String returnValue = sendMessage(objectReference, "createAccount", owner, branch);

        throwIfInvalidParamException(returnValue);

        return Protocol.returnValueFromMessage(returnValue, String.class).getValue();
    }

    protected void throwIfInvalidParamException(String message) throws InvalidParamException {
        if(Objects.equals(returnMessageType(message), EXCEPTION)) {
            ExceptionValue exceptionValue = exceptionValueFromMessage(message);
            if(Objects.equals(exceptionValue.getType(), InvalidParamException.class))
                throw (InvalidParamException)exceptionValue.getValue();
        }
    }
}
