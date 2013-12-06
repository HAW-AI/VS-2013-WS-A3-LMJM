package bank_access;


import mware_lib.MethodResponse;
import mware_lib.Stub;

public class RemoteAccountImpl extends AccountImplBase
{
    private final Stub stub;

    public RemoteAccountImpl(Object stub) {
        if (stub instanceof Stub) {
            this.stub = (Stub) stub;
        } else {
            throw new RuntimeException("narrowCast of unknown object.");
        }
    }


    @Override
    public void transfer(double amount) throws OverdraftException
    {
        MethodResponse response = this.stub.sendMethodRequest("transfer",
                new Class[]{double.class},
                new Object[]{amount});

        if (response.getException() != null)
            throw new RuntimeException("Remote Exception", response.getException());
    }

    @Override
    public double getBalance()
    {
        MethodResponse response = this.stub.sendMethodRequest("getBalance",
                new Class[]{},
                new Object[]{});

        if (response.getException() != null)
            throw new RuntimeException("Remote Exception", response.getException());

        return Double.parseDouble(response.getPayload().toString());
    }
}
