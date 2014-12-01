package mware_lib.protocol;

public interface Message {
    String getHostname();

    int getPort();

    String getObjectName();

    int getHashCode();

    String getMethodCallAsString();

    String asString();
}
