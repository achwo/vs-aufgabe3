package cash_access;

import mware_lib.Proxy;

public abstract class TransactionImplBase extends Proxy {
    public static TransactionImplBase narrowCast(Object rawObjectRef) {
        return new TransactionImplProxy((String) rawObjectRef);
    }

    public abstract void deposit(String accountID, double amount)
            throws InvalidParamException;

    public abstract void withdraw(String accountID, double amount)
            throws InvalidParamException, OverdraftException;

    public abstract double getBalance(String accountID)
            throws InvalidParamException;
}
