package mware_lib;

import java.io.Serializable;

class Request implements Serializable {
    private final String target;
    private final String message;
    private final String[] arguments;

    public Request(String target, String message, String[] arguments) {
        this.target = target;
        this.message = message;
        this.arguments = arguments;
    }
}
