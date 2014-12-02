package bank_access;

import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

/**
 * Created by johnstar on 02.12.14.
 */
public class AccountImplProxy extends AccountImplBase {
    private final String objectReference;

    public AccountImplProxy(String rawObjRef) {this.objectReference = rawObjRef;}

    @Override
    public void transfer(double amount) throws OverdraftException {
        sendMessage(objectReference, "transfer", amount);
    }

    @Override
    public double getBalance() {
        String result = sendMessage(objectReference, "getBalance");
        Double balance = -1.0;
        try {
            ReturnValue<Double> value = Protocol.returnValueFromMessage(result, Double.class);
            balance = value.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
