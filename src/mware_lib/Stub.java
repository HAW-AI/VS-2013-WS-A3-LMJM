package mware_lib;

class Stub {

    private final String response;

    Stub(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Stub " + response;
    }
}
