package mware_lib;

import java.io.Serializable;

class MethodResponse implements Serializable {
    public final Object payload;
    public final Exception exception;

    public MethodResponse(Object payload, Exception exception) {
        this.payload = payload;
        this.exception = exception;
    }

    Object getPayload() {
        return payload;
    }

    Exception getException() {
        return exception;
    }
}
