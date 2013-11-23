package mware_lib;

class Stub {

    private final String name;
    private final String host;
    private final int port;

    Stub(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return String.format("Stub %s@%s:%d", this.name, this.host, this.port);
    }
}
