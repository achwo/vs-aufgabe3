package mware_lib.protocol;

public interface ObjectReference {

    public String getHostname();
    public int getPort();
    public Object getObject();
    public int getHashCode();
    public String asString();

    String getObjectName();
}
