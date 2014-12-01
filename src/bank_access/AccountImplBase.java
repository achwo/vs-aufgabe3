package bank_access;

public abstract class AccountImplBase {
    public static AccountImplBase narrowCast(Object rawObjectRef) {
        return null; // todo implement
    }

    public abstract void transfer(double amount) throws OverdraftException;

    public abstract double getBalance();
}
