import bank_access.OverdraftException;
import bank_access.ManagerImplBase;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by janlepel on 03.12.14.
 */
public class ManagerIntegrationTest {

    @Test
    public void testManagerAccount() throws Exception {
        int nsPort = 15000;
        name_service.NameService ns = new name_service.NameService(nsPort);
        Thread nsThread = new Thread(ns);
        nsThread.start();

        ObjectBroker broker = ObjectBroker.init("127.0.0.1", nsPort, false);
        NameService nameServiceProxy = broker.getNameService();


        TestObject testObject = new TestObject();
        nameServiceProxy.rebind(testObject, "testObject");

        Object proxyObject = nameServiceProxy.resolve("testObject");

        ManagerImplBase managerImplObject = ManagerImplBase.narrowCast(proxyObject);

        String owner = "Peterson";
        String branch = "Hamburg";
        assertEquals("exists", managerImplObject.createAccount(owner, branch));

        String anotherOwner = "Buskubusku";
        String anotherBranch = "Hamburg";
        assertEquals("accepted", managerImplObject.createAccount(anotherOwner, anotherBranch));

        ns.shutdown();
        broker.shutdown();
    }


    private class TestObject extends ManagerImplBase{
        String returnValue = "accepted";

        @Override
        public String createAccount(String owner, String branch) {
            return owner == "Peters" ? "exists" : "accepted";
        }
    }

}
