package bank_access;


import mware_lib.MethodResponse;
import mware_lib.Stub;

public class RemoteAccountImpl extends AccountImplBase {
    private final Stub stub;

    public RemoteAccountImpl(Object stub) {
        if (stub instanceof Stub) {
            this.stub = (Stub) stub;
        } else {
            throw new RuntimeException("narrowCast of unknown object.");
        }
    }

    @Override
    public void transfer(double amount) throws OverdraftException {
        MethodResponse response = this.stub.sendMethodRequest("transfer",
                new Class[]{double.class},
                new Object[]{amount});

        Exception e = response.getException();
        if (e != null) throw new RuntimeException("Remote Exception", e);
    }

    @Override
    public double getBalance() {
        MethodResponse response = this.stub.sendMethodRequest("getBalance",
                new Class[]{},
                new Object[]{});

        Exception e = response.getException();
        if (e != null) throw new RuntimeException("Remote Exception", e);

        return Double.parseDouble(response.getPayload().toString());
    }
}
