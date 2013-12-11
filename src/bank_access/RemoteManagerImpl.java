package bank_access;

import mware_lib.MethodResponse;
import mware_lib.Stub;

public class RemoteManagerImpl extends ManagerImplBase {

    private final Stub stub;

    public RemoteManagerImpl(Object stub) {
        if (stub instanceof Stub) {
            this.stub = (Stub) stub;
        } else {
            throw new RuntimeException("narrowCast of unknown object.");
        }
    }

    @Override
    public String createAccount(String owner, String branch) {
        MethodResponse response = this.stub.sendMethodRequest("createAccount",
                new Class[]{String.class, String.class},
                new Object[]{owner, branch});

        Throwable t = response.getThrowable();
        if (t != null) throw new RuntimeException("Remote Exception", t);

        return (String) response.getPayload();
    }
}
