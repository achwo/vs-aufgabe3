
import bank_access.ManagerImplBase;
import bank_access.InvalidParamException;
import mware_lib.NameService;
import mware_lib.ObjectBroker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

public class ManagerIntegrationTest {
    String acceptedOwner = "accepted";
    String existsOwner = "exists";
    private name_service.NameService ns;
    private ManagerImplBase manager;
    private ObjectBroker broker;

    @Before
    public void setUp() throws Exception {
        int nsPort = 15000;

        ns = new name_service.NameService(nsPort);
        new Thread(ns).start();

        TestObject obj = new TestObject();

        broker = ObjectBroker.init("127.0.0.1", nsPort, false);
        NameService nameService = broker.getNameService();

        nameService.rebind(obj, "test");

        Object rawObjRef = nameService.resolve("test");
        manager = ManagerImplBase.narrowCast(rawObjRef);
    }

    @After
    public void tearDown() throws Exception {
        ns.shutdown();
        broker.shutDown();
    }

    @Test
    public void testManagerAccount() throws Exception {
        assertEquals(existsOwner, manager.createAccount("Peterson", "Hamburg"));
        assertEquals(acceptedOwner, manager.createAccount("Buskubusku", "Hamburg"));
    }

    @Test(expected = InvalidParamException.class)
    public void testInvalidParamException() throws Exception {
        manager.createAccount("invalid", "Hamburg");
    }

    private class TestObject extends ManagerImplBase{
        @Override
        public String createAccount(String owner, String branch) throws InvalidParamException {
            if(Objects.equals(owner, "invalid")) throw new InvalidParamException("invalid");
            return owner.equals("Peterson") ? "exists" : "accepted";
        }
    }

}
