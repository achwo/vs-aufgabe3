package mware_lib.protocol;

public interface ObjectReference {

    public String getHostname();

    public int getPort();

    public int getHashCode();

    public String asString();

    String getObjectName();
}
