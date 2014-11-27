import cash_access.InvalidParamException;
import cash_access.OverdraftException;
import cash_access.TransactionImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameServiceTest {


    @Test
    public void testBindObjectToNameService() throws Exception {
        // Arrange
        ObjectBroker broker = ObjectBroker.init("127.0.0.1", 15000, false);
        NameService nameServiceProxy = broker.getNameService();

        // todo is das richtig, oder muss da ne objektreferenz hin?
        TransactionImplBase testObject = new TestObject();

        // Act
        nameServiceProxy.rebind(testObject, "name");

        // Assert
        assertEquals(testObject, nameServiceProxy.resolve("name"));
    }

    private class TestObject extends TransactionImplBase {
        @Override
        public void deposit(String accountID, double amount) throws InvalidParamException {

        }

        @Override
        public void withdraw(String accountID, double amount) throws InvalidParamException, OverdraftException {

        }

        @Override
        public double getBalance(String accountID) throws InvalidParamException {
            return 0;
        }
    }
}
