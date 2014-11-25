package mware_lib.networking;

public class Request {
    private final String address;
    private final Integer port;
    private final String message;

    public Request(String address, Integer port, String message) {

        this.address = address;
        this.port = port;
        this.message = message;
    }

    public String invoke() {
        return "";
    }
}
