package cash_access;

import mware_lib.MethodResponse;
import mware_lib.Stub;

public class RemoteTranscationImpl extends TransactionImplBase
{
    private final Stub stub;

    public RemoteTranscationImpl(Object stub) {
        System.out.println(stub.getClass());

        if (stub instanceof Stub) {
            this.stub = (Stub) stub;
        } else {
            throw new RuntimeException("narrowCast of unknown object.");
        }
    }

    @Override
    public void deposit(String accountId, double amount) throws InvalidParamException {
        MethodResponse response = this.stub.sendMethodRequest("deposit",
                new Class[]{String.class, Double.class},
                new Object[]{accountId, amount});

        if (response.getException() != null)
            throw new RuntimeException("Remote Exception", response.getException());
    }

    @Override
    public void withdraw(String accountId, double amount) throws InvalidParamException, OverdraftException {
        MethodResponse response = this.stub.sendMethodRequest("withdraw",
                new Class[]{String.class, Double.class},
                new Object[]{accountId, amount});

        if (response.getException() != null)
            throw new RuntimeException("Remote Exception", response.getException());
    }

    @Override
    public double getBalance(String accountId) throws InvalidParamException {
        MethodResponse response = this.stub.sendMethodRequest("getBalance",
                new Class[]{String.class},
                new Object[]{accountId});

        if (response.getException() != null)
            throw new RuntimeException("Remote Exception", response.getException());

        return Double.parseDouble(response.getPayload().toString());
    }
}
