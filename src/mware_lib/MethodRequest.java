package mware_lib;

import java.io.Serializable;

class MethodRequest implements Serializable {

    private final String target;
    private final String method;

    private final Class[] types;
    private final Object[] arguments;

    public MethodRequest(String target, String method, Class[] types, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.types = types;
        this.arguments = arguments;
    }

    String getTarget() {
        return target;
    }

    String getMethod() {
        return method;
    }

    Class[] getTypes() {
        return types;
    }

    Object[] getArguments() {
        return arguments;
    }
}
