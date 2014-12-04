package bank_access;

import mware_lib.Logger;
import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

import java.util.Objects;

import static mware_lib.protocol.Protocol.*;

public class AccountImplProxy extends AccountImplBase {
    private final String objectReference;
    private Logger logger = new Logger(this);
    public AccountImplProxy(String rawObjRef) {this.objectReference = rawObjRef;}

    @Override
    public void transfer(double amount) throws OverdraftException {
        logger.log("Called AccountImplProxy -> transfer("+amount+")");

        String returnValue = sendMessage(objectReference, "transfer", amount);
        logger.log("Returnvalue: " + returnValue);
        throwIfOverdraftException(returnValue);
    }

    @Override
    public double getBalance() throws InvalidParamException{
        logger.log("getBalanced()");
        String returnValue = sendMessage(objectReference, "getBalance");
        logger.log("From sendMessage: " + returnValue);

        throwIfInvalidParamException(returnValue);
        ReturnValue<Double> value = Protocol.returnValueFromMessage(returnValue, Double.class);

        logger.log("getBalance Returnvalue: " + value.getValue());
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
        return "AccountImplProxy(" + objectReference + ")";
    }
}
