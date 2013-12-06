package mware_lib;

import java.io.Serializable;
import java.util.Arrays;

public class MethodResponse implements Serializable {
    public final Object payload;
    public final Exception exception;

    public MethodResponse(Object payload, Exception exception) {
        this.payload = payload;
        this.exception = exception;

        if(exception != null)
        {
            System.out.println(exception.getLocalizedMessage());
            exception.printStackTrace();
        }
    }

    public Object getPayload() {
        return payload;
    }

    public Exception getException() {
        return exception;
    }
}
