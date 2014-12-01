import mware_lib.NameService;
import mware_lib.NameServiceProxy;
import mware_lib.ReferenceManager;
import mware_lib.Skeleton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class NameServiceProxyTest {

    private NameService nameServiceProxy;
    private TestReferenceManager testManager;
    private String testObject;
    private name_service.NameService nameService;
    public static final int PORT = 15001;

    @Before
    public void setUp() {
        testManager = new TestReferenceManager();
        nameService = new name_service.NameService(PORT);
        new Thread(nameService).start();
        nameServiceProxy = new NameServiceProxy("127.0.0.1", PORT, testManager, 20000);
        testObject = "servant";
    }

    @After
    public void tearDown() {
        nameService.shutdown();
    }

    @Test
    public void testBindObjectToNameServiceSavesName() throws Exception {
        nameServiceProxy.rebind(testObject, "name");
        Object result = nameServiceProxy.resolve("name");
        assertTrue(((String) result).contains("20000:servant:1984145937"));
    }

    @Test
    public void testBindObjectToNameServicePutsSkeletonToRefManager() throws Exception {
        nameServiceProxy.rebind(testObject, "name");
        assertEquals(true, testManager.wasCalled);
    }

    private class TestReferenceManager extends ReferenceManager {
        public boolean wasCalled = false;

        @Override
        public void putSkeleton(Object reference, Skeleton skeleton) {
            wasCalled = true;
        }
    }
}
