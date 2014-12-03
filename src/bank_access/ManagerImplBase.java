package bank_access;

import mware_lib.InvalidParamException;

public abstract class ManagerImplBase {



    public static ManagerImplBase narrowCast(Object rawObjectRef) {
        return new ManagerImplProxy((String) rawObjectRef);
    }

    public abstract String createAccount(String owner, String branch)
            throws InvalidParamException;
}
