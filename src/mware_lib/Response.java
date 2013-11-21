package mware_lib;

import java.io.Serializable;

class Response implements Serializable {
    public final Object payload;
    public final Exception exception;

    public Response(Object payload, Exception exception) {
        this.payload = payload;
        this.exception = exception;
    }
}
