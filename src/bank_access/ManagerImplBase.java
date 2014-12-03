package bank_access;

public abstract class ManagerImplBase {



    public static ManagerImplBase narrowCast(Object rawObjectRef) {
        return new ManagerImplProxy((String) rawObjectRef);
    }

    public abstract String createAccount(String owner, String branch)
            throws InvalidParamException;
}
