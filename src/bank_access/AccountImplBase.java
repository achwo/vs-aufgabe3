package bank_access;



public abstract class AccountImplBase {
    public static AccountImplBase narrowCast(Object rawObjectRef) {
        return new AccountImplProxy((String) rawObjectRef);
    }

    public abstract void transfer(double amount) throws OverdraftException;

    public abstract double getBalance() throws cash_access.InvalidParamException;
}
