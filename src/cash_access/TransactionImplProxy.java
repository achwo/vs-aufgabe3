package cash_access;

import mware_lib.networking.Request;
import mware_lib.protocol.Message;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;
import mware_lib.protocol.exceptions.IllegalTypeException;
import mware_lib.protocol.exceptions.InvalidMessageException;

public class TransactionImplProxy extends TransactionImplBase {
    private String objectReference;

    public TransactionImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }

    @Override
    public void deposit(String accountID, double amount) throws InvalidParamException {
        sendMessage(objectReference, "deposit", accountID, amount);
    }

    @Override
    public void withdraw(String accountID, double amount) throws InvalidParamException, OverdraftException {
        sendMessage(objectReference, "withdraw", accountID, amount);
    }

    @Override
    public double getBalance(String accountID) throws InvalidParamException {
        String result = sendMessage(objectReference, "getBalance", accountID);
        Double balance = -1.0;
        try {
            ReturnValue<Double> value = Protocol.returnValueFromMessage(result, Double.class);
            balance = value.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;

        // todo throw exceptions
    }
}
