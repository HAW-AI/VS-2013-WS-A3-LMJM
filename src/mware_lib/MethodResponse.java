package mware_lib;

import java.io.Serializable;
import java.util.Arrays;

public class MethodResponse implements Serializable {
    public final Object payload;
    public final Throwable throwable;

    public MethodResponse(Object payload, Throwable throwable) {
        this.payload = payload;
        this.throwable = throwable;
    }

    public Object getPayload() {
        return payload;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
