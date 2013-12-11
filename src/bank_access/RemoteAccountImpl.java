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

        Throwable t = response.getThrowable();
        if (t != null) {
            if (OverdraftException.check(t)) {
                throw new OverdraftException(t.getMessage());
            } else {
                throw new RuntimeException("Remote Exception", t);
            }
        }
    }

    @Override
    public double getBalance() {
        MethodResponse response = this.stub.sendMethodRequest("getBalance",
                new Class[]{},
                new Object[]{});

        Throwable t = response.getThrowable();
        if (t != null) throw new RuntimeException("Remote Exception", t);

        return Double.parseDouble(response.getPayload().toString());
    }
}
