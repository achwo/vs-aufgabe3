import bank_access.AccountImplBase;
import cash_access.InvalidParamException;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AccountIntegrationTest {

    private name_service.NameService ns;
    private AccountImplBase account;

    @Before
    public void setUp() throws Exception {
        int nsPort = 15000;
        ns = new name_service.NameService(nsPort);
        Thread nsThread = new Thread(ns);
        nsThread.start();

        TestObject obj = new TestObject();

        ObjectBroker broker = ObjectBroker.init("127.0.0.1", nsPort, false);
        NameService nameService = broker.getNameService();

        nameService.rebind(obj, "test");

        Object rawObjRef = nameService.resolve("test");
        account = AccountImplBase.narrowCast(rawObjRef);
    }


    @Test
    public void testAccount() throws Exception {
        //Act
        account.transfer(1000.00);

        //Assert
        assertEquals(1000.00, account.getBalance());
    }



    @After
    public void tearDown() throws Exception {
        ns.shutdown();
    }


    private class TestObject extends AccountImplBase {
        private double balance = 0.0;

        public void transfer(double amount) {
            balance += amount;
        }

        @Override
        public double getBalance() throws InvalidParamException {
            return balance;
        }
    }
}