import cash_access.InvalidParamException;
import cash_access.OverdraftException;
import cash_access.TransactionImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import mware_lib.ReferenceManager;
import mware_lib.Skeleton;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NameServiceProxyTest {


    @Test
    public void testBindObjectToNameServiceSavesName() throws Exception {
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

    @Test
    public void testBindObjectToNameServicePutsSkeletonToRefManager() throws Exception {
        // Arrange
        TestObjectBroker broker = new TestObjectBroker("127.0.0.1", 15000, false);
        TestReferenceManager testManager = new TestReferenceManager();
        broker.setReferenceManager(testManager);

        NameService nameServiceProxy = broker.getNameService();
        TransactionImplBase testObject = new TestObject();

        // Act
        nameServiceProxy.rebind(testObject, "name");

        // Assert
        assertEquals(true, ((TestReferenceManager)broker.getReferenceManager()).wasCalled);
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

    private class TestObjectBroker extends ObjectBroker {

        public TestObjectBroker(String serviceHost, int listenPort, boolean debug) {
            super(serviceHost, listenPort, debug);
        }

        public void setReferenceManager(ReferenceManager referenceManager) {
            super.referenceManager = referenceManager;
        }

        public ReferenceManager getReferenceManager() {
            return super.referenceManager;
        }
    }

    private class TestReferenceManager extends ReferenceManager {
        public boolean wasCalled = false;

        @Override
        public void putSkeleton(Object reference, Skeleton skeleton) {
            wasCalled = true;
        }



    }
}
