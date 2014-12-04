package cash_access;

import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.ReturnValue;

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

        throwIfInvalidParamException(returnValue);
    }

    @Override
    public void withdraw(String accountID, double amount) throws InvalidParamException, OverdraftException {
        String returnValue = sendMessage(objectReference, "withdraw", accountID, amount);

        throwIfInvalidParamException(returnValue);
        throwIfOverdraftException(returnValue);
    }

    @Override
    public double getBalance(String accountID) throws InvalidParamException {
        String returnValue = sendMessage(objectReference, "getBalance", accountID);

        throwIfInvalidParamException(returnValue);

        ReturnValue<Double> value = returnValueFromMessage(returnValue, Double.class);
        return value.getValue();
    }

    protected void throwIfOverdraftException(String message) throws OverdraftException {
        if(Objects.equals(returnMessageType(message), EXCEPTION)) {
            ExceptionValue exceptionValue = exceptionValueFromMessage(message);
            if (Objects.equals(exceptionValue.getType(), OverdraftException.class))
                throw (OverdraftException) exceptionValue.getValue();
        }
    }

    protected void throwIfInvalidParamException(String message) throws InvalidParamException {
        if(Objects.equals(returnMessageType(message), EXCEPTION)) {
            ExceptionValue exceptionValue = exceptionValueFromMessage(message);
            if(Objects.equals(exceptionValue.getType(), InvalidParamException.class))
                throw (InvalidParamException)exceptionValue.getValue();
        }
    }


}
