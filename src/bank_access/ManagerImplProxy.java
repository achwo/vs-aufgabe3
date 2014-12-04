package bank_access;

import mware_lib.Logger;
import mware_lib.ObjectBroker;
import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.Protocol;

import java.util.Objects;

import static mware_lib.protocol.Protocol.*;

public class ManagerImplProxy extends ManagerImplBase {
    private final String objectReference;
    private final Logger logger = new Logger(this, ObjectBroker.LOGGING);

    public ManagerImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }

    @Override
    public String createAccount(String owner, String branch) throws InvalidParamException {
     String returnValue = sendMessage(objectReference, "createAccount", owner, branch);
        logger.log("Called ManagerImplProxy -> createAccount("+owner+","+branch+")");
        throwIfInvalidParamException(returnValue);

        String retValue =  Protocol.returnValueFromMessage(returnValue, String.class).getValue();
        logger.log("Returnvalue: " + retValue);
        return retValue;
    }

    protected void throwIfInvalidParamException(String message) throws InvalidParamException {
        if(Objects.equals(returnMessageType(message), EXCEPTION)) {
            ExceptionValue exceptionValue = exceptionValueFromMessage(message);
            if(Objects.equals(exceptionValue.getType(), InvalidParamException.class))
                throw (InvalidParamException)exceptionValue.getValue();
        }
    }

    @Override
    public String toString() {
        return "ManagerImplProxy(" + objectReference + ")";
    }
}
