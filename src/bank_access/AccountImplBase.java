package bank_access;


import mware_lib.Proxy;

public abstract class AccountImplBase extends Proxy{
    public static AccountImplBase narrowCast(Object rawObjectRef) {
        return new AccountImplProxy((String) rawObjectRef);
    }

    public abstract void transfer(double amount) throws OverdraftException;

    public abstract double getBalance() throws cash_access.InvalidParamException;
}
