package mware_lib;

import java.util.HashMap;
import java.util.Map;

public class ReferenceManager {

    private final Map<Object, Skeleton> references = new HashMap<>();

    public void putSkeleton(Object reference, Skeleton skeleton) {
        references.put(reference, skeleton);
    }

    public Skeleton getSkeleton(Object reference) {
        return references.get(reference);
    }

}
