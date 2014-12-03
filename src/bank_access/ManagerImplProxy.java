package bank_access;

import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

/**
 * Created by janlepel on 02.12.14.
 */
public class ManagerImplProxy extends ManagerImplBase {
    private final String objectReference;

    public ManagerImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }


    @Override
    public String createAccount(String owner, String branch) throws InvalidParamException {
     sendMessage(objectReference, "transfer", amount);

    }
}
