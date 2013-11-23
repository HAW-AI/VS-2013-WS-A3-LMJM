package name_service;

class Reference {

    private final String host;
    private final int port;

    public Reference(String host, int port) {
        this.host = host;
        this.port = port;
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return String.format("%s:%d", this.getHost(), this.getPort());
    }

}
