package name_service;

import java.util.HashMap;
import java.util.Map;

public class NameService extends mware_lib.NameService {

    private Map<String, Object> names = new HashMap<>();

    @Override
    public void rebind(Object servant, String name) {
        names.put(name, servant);
    }

    @Override
    public Object resolve(String name) {
        return names.get(name);
    }
}
