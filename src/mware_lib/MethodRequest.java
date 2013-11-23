package mware_lib;

import java.io.Serializable;

class MethodRequest implements Serializable {
    private final String target;
    private final String type;
    private final String method;
    private final Object[] arguments;

    public MethodRequest(String target, String type, String method, Object[] arguments) {
        this.target = target;
        this.type = type;
        this.method = method;
        this.arguments = arguments;
    }

    String getTarget() {
        return target;
    }

    String getType() {
        return type;
    }

    String getMethod() {
        return method;
    }

    Object[] getArguments() {
        return arguments;
    }
}
