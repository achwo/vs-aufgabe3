package mware_lib.protocol;

public interface Message {
    public String getHostname();
    public int getPort();
    public String getObjectName();
    public int getHashCode();
    public String getMethodCallAsString();
    public String asString();
}
