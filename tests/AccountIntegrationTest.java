import bank_access.AccountImplBase;
import bank_access.InvalidParamException;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import bank_access.OverdraftException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AccountIntegrationTest {

    private name_service.NameService ns;
    private AccountImplBase account;
    private ObjectBroker broker;

    @Before
    public void setUp() throws Exception {
        int nsPort = 15000;
        ns = new name_service.NameService(nsPort);
        Thread nsThread = new Thread(ns);
        nsThread.start();

        TestObject obj = new TestObject();

        broker = ObjectBroker.init("127.0.0.1", nsPort, false);
        NameService nameService = broker.getNameService();

        nameService.rebind(obj, "test");

        Object rawObjRef = nameService.resolve("test");
        account = AccountImplBase.narrowCast(rawObjRef);
    }


    @Test
    public void testAccount() throws Exception {
        account.transfer(1000.00);
        assertEquals(1000.00, account.getBalance());
    }

    @Test(expected = OverdraftException.class)
    public void testOverdraftException() throws Exception {
        account.transfer(-25.0);
    }

    @After
    public void tearDown() throws Exception {
        ns.shutdown();
        broker.shutDown();
    }

    private class TestObject extends AccountImplBase {
        private double balance = 0.0;

        public void transfer(double amount) throws OverdraftException {
            if(amount < 0.0) throw new OverdraftException("bla");
            balance += amount;
        }

        @Override
        public double getBalance() throws InvalidParamException {
            return balance;
        }
    }
}