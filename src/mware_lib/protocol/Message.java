package mware_lib.protocol;

public interface Message {
    String getHostname();

    int getPort();

    String getObjectName();

    String getHashCode();

    String getMethodCall();

    void setMethod(String methodName, Object... args);
}
