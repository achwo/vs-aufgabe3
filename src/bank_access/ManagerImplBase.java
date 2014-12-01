package bank_access;

public abstract class ManagerImplBase {
    public static ManagerImplBase narrowCast(Object rawObjectRef) {
        return null; // todo implement
    }

    public abstract String createAccount(String owner, String branch)
            throws InvalidParamException;
}
