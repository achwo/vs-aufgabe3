import cash_access.InvalidParamException;
import cash_access.OverdraftException;
import cash_access.TransactionImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TransactionImplBaseIntegrationTest {

    @Test
    public void testTransactionDeposit() throws Exception {
        int nsPort = 15000;
        name_service.NameService ns = new name_service.NameService(nsPort);
        Thread nsThread = new Thread(ns);
        nsThread.start();

        TestObject obj = new TestObject();

        ObjectBroker broker = ObjectBroker.init("127.0.0.1", nsPort, false);
        NameService nameService = broker.getNameService();

        nameService.rebind(obj, "test");

        Object rawObjRef = nameService.resolve("test");

        final String accountName = "accountName";
        TransactionImplBase transaction = TransactionImplBase.narrowCast(rawObjRef);

        // Act
        transaction.deposit(accountName, 30.0);

        // Assert
        assertEquals(30.0, transaction.getBalance(accountName));

        ns.shutdown();
        broker.shutdown();
    }


    private class TestObject extends TransactionImplBase{
        private double balance = 0.0;

        @Override
        public void deposit(String accountID, double amount) throws InvalidParamException {
            balance += amount;
        }

        @Override
        public void withdraw(String accountID, double amount) throws InvalidParamException, OverdraftException {
            balance -= amount;
        }

        @Override
        public double getBalance(String accountID) throws InvalidParamException {
            return balance;
        }
    }
}
