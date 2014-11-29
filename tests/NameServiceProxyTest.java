import cash_access.InvalidParamException;
import cash_access.OverdraftException;
import cash_access.TransactionImplBase;
import mware_lib.*;
import mware_lib.NameService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameServiceProxyTest {

    private NameService nameServiceProxy;
    private TestReferenceManager testManager;
    private TransactionImplBase testObject;

    @Before
    public void setUp() {
        testManager = new TestReferenceManager();
        nameServiceProxy = new NameServiceProxy(testManager);
        testObject = new TestObject();
    }

    @Test
    public void testBindObjectToNameServiceSavesName() throws Exception {
        // todo is das richtig, oder muss da ne objektreferenz hin?
        nameServiceProxy.rebind(testObject, "name");
        assertEquals(testObject, nameServiceProxy.resolve("name"));
    }

    @Test
    public void testBindObjectToNameServicePutsSkeletonToRefManager() throws Exception {
        // todo is das richtig, oder muss da ne objektreferenz hin?
        nameServiceProxy.rebind(testObject, "name");
        assertEquals(true, testManager.wasCalled);
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

    private class TestReferenceManager extends ReferenceManager {
        public boolean wasCalled = false;

        @Override
        public void putSkeleton(Object reference, Skeleton<name_service.NameService> skeleton) {
            wasCalled = true;
        }



    }
}
