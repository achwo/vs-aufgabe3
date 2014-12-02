package bank_access;

/**
 * Created by johnstar on 02.12.14.
 */
public class AccountImplProxy extends AccountImplBase {
    private final String objectReference;

    public AccountImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }

    @Override
    public void transfer(double amount) throws OverdraftException {

    }

    @Override
    public double getBalance() {
        return 0;
    }
}
