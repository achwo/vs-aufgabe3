package cash_access;

import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;
import mware_lib.protocol.exceptions.InvalidMessageException;

import java.util.Objects;

import static mware_lib.protocol.Protocol.*;

public class TransactionImplProxy extends TransactionImplBase {
    private final String objectReference;

    public TransactionImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }

    @Override
    public void deposit(String accountID, double amount) throws InvalidParamException {
        String returnValue = sendMessage(objectReference, "deposit", accountID, amount);

        if(Objects.equals(returnMessageType(returnValue), EXCEPTION)) {
            try {
                ExceptionValue<InvalidParamException> exceptionValue =
                        exceptionValueFromMessage(returnValue, InvalidParamException.class);
                throw exceptionValue.getValue();
            } catch (InvalidMessageException e) {
                e.printStackTrace();
            }
        }
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
            ReturnValue<Double> value = returnValueFromMessage(result, Double.class);
            balance = value.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;

        // todo throw exceptions
    }
}
