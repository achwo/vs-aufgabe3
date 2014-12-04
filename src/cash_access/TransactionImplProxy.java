package cash_access;

import mware_lib.Logger;
import mware_lib.ObjectBroker;
import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.ReturnValue;

import java.util.Objects;

import static mware_lib.protocol.Protocol.*;

public class TransactionImplProxy extends TransactionImplBase {
    private final String objectReference;
    private final Logger logger = new Logger(this, ObjectBroker.LOGGING);

    public TransactionImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }

    @Override
    public void deposit(String accountID, double amount) throws InvalidParamException {
        logger.log("Called TransactionImplProxy -> deposit("+accountID+","+amount+")");
        String returnValue = sendMessage(objectReference, "deposit", accountID, amount);
        logger.log("Returnvalue: " + returnValue);
        throwIfInvalidParamException(returnValue);
    }

    @Override
    public void withdraw(String accountID, double amount) throws InvalidParamException, OverdraftException {
        logger.log("Called TransactionImplProxy -> withdraw("+accountID+","+amount+")");
        String returnValue = sendMessage(objectReference, "withdraw", accountID, amount);
        logger.log("Returnvalue: " + returnValue);

        throwIfInvalidParamException(returnValue);
        throwIfOverdraftException(returnValue);
    }

    @Override
    public double getBalance(String accountID) throws InvalidParamException {
        logger.log("Called TransactionImplProxy -> getBalance("+accountID+")");
        String returnValue = sendMessage(objectReference, "getBalance", accountID);
        logger.log("From sendMessage: " + returnValue);
        throwIfInvalidParamException(returnValue);

        ReturnValue<Double> value = returnValueFromMessage(returnValue, Double.class);
        logger.log("Returnvalue: " + value.getValue());
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

    @Override
    public String toString() {
        return "TransactionImplProxy(" + objectReference + ")";
    }
}
