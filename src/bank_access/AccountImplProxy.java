package bank_access;

import mware_lib.protocol.ExceptionValue;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

import java.util.Objects;

import static mware_lib.protocol.Protocol.EXCEPTION;
import static mware_lib.protocol.Protocol.exceptionValueFromMessage;
import static mware_lib.protocol.Protocol.returnMessageType;

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
