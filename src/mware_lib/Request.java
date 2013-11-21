package mware_lib;

import java.io.Serializable;

class Request implements Serializable {
    private final String target;
    private final String method;
    private final String[] arguments;

    public Request(String target, String method, String[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }
}
