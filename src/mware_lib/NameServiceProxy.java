package mware_lib;

public class NameServiceProxy extends NameService {

    private name_service.NameService nameService = new name_service.NameService();
    private ReferenceManager referenceManager;

    public NameServiceProxy(ReferenceManager referenceManager) {
        this.referenceManager = referenceManager;
    }

    @Override
    public void rebind(Object servant, String name) {
        referenceManager.putSkeleton(servant, new Skeleton(servant));
        nameService.rebind(servant, name);
    }

    @Override
    public Object resolve(String name) {
        // todo echt machen (ueber netzwerkkram)
        return nameService.resolve(name);
    }
}
