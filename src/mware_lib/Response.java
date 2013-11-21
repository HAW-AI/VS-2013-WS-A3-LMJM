package mware_lib;

import java.io.Serializable;

class Response implements Serializable {
    public final Object payload;

    public Response(Object payload) {
        this.payload = payload;
    }
}
