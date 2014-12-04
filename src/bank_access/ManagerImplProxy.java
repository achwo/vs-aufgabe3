package bank_access;

import mware_lib.InvalidParamException;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

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
}
