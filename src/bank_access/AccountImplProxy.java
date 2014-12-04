package bank_access;

import mware_lib.InvalidParamException;
import mware_lib.OverdraftException;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

public class AccountImplProxy extends AccountImplBase {
    private final String objectReference;

    public AccountImplProxy(String rawObjRef) {this.objectReference = rawObjRef;}

    @Override
    public void transfer(double amount) throws OverdraftException {
        String returnValue = sendMessage(objectReference, "transfer", amount);

        throwIfOverdraftException(returnValue);
    }

    @Override
    public double getBalance() throws InvalidParamException{
        String returnValue = sendMessage(objectReference, "getBalance");

        throwIfInvalidParamException(returnValue);
        ReturnValue<Double> value = Protocol.returnValueFromMessage(returnValue, Double.class);

        return value.getValue();
    }
}
