import cash_access.InvalidParamException;
import cash_access.OverdraftException;
import cash_access.TransactionImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TransactionIntegrationTest {

    private String accountName = "bank0000000999";
    private name_service.NameService realNameService;
    private ObjectBroker broker;
    private TransactionImplBase transaction;

    @Before
    public void setUp() {
        int nsPort = 15000;
        realNameService = new name_service.NameService(nsPort);
        new Thread(realNameService).start();
        broker = ObjectBroker.init("127.0.0.1", nsPort, false);

        NameService nameService = broker.getNameService();

        TestObject obj = new TestObject();
        nameService.rebind(obj, "test");

        Object rawObjRef = nameService.resolve("test");
        transaction = TransactionImplBase.narrowCast(rawObjRef);

    }

    @After
    public void tearDown() {
        realNameService.shutdown();
        broker.shutDown();
    }

    @Test
    public void testTransactionDeposit() throws Exception {
        transaction.deposit(accountName, 30.0);
        assertEquals(30.0, transaction.getBalance(accountName));

        transaction.withdraw(accountName, 25.0);
        assertEquals(5.0, transaction.getBalance(accountName));
    }

    @Test(expected = InvalidParamException.class)
    public void testInvalidParamException() throws Exception {
        transaction.deposit(accountName, -25.0);
    }

    @Test(expected = OverdraftException.class)
    public void testOverdraftException() throws Exception {
        transaction.withdraw(accountName, 25.0);
    }

    @Test(expected = InvalidParamException.class)
    public void testWithdrawInvalidParamException() throws Exception {
        transaction.withdraw(accountName, -12.0);

    }

    private class TestObject extends TransactionImplBase{
        private double balance = 0.0;

        @Override
        public void deposit(String accountID, double amount) throws InvalidParamException {
            assertEquals(accountName, accountID);
            checkPositiveValue(amount);
            balance += amount;
        }

        @Override
        public void withdraw(String accountID, double amount)
                throws InvalidParamException, OverdraftException {
            assertEquals(accountName, accountID);
            checkPositiveValue(amount);
            checkBalanceCovered(amount);
            balance -= amount;
        }

        @Override
        public double getBalance(String accountID) throws InvalidParamException {
            assertEquals(accountName, accountID);
            return balance;
        }

        private void checkBalanceCovered(double amount) throws OverdraftException {
            if(amount > balance) throw new OverdraftException("Nicht genug Geld");
        }

        private void checkPositiveValue(double amount) throws InvalidParamException {
            if(amount < 0.0) throw new InvalidParamException("Betrag muss positiv sein");
        }
    }
}
