package cash_access;

import mware_lib.MethodResponse;
import mware_lib.Stub;

public class RemoteTransactionImpl extends TransactionImplBase {
    private final Stub stub;

    public RemoteTransactionImpl(Object stub) {
        if (stub instanceof Stub) {
            this.stub = (Stub) stub;
        } else {
            throw new RuntimeException("narrowCast of unknown object.");
        }
    }

    @Override
    public void deposit(String accountId, double amount) throws InvalidParamException {
        MethodResponse response = this.stub.sendMethodRequest("deposit",
                new Class[]{String.class, double.class},
                new Object[]{accountId, amount});

        Exception e = response.getException();
        if (e != null) {
            if (e instanceof InvalidParamException) {
                throw (InvalidParamException) e;
            } else {
                throw new RuntimeException("Remote Exception", e);
            }
        }
    }

    @Override
    public void withdraw(String accountId, double amount) throws InvalidParamException, OverdraftException {
        MethodResponse response = this.stub.sendMethodRequest("withdraw",
                new Class[]{String.class, double.class},
                new Object[]{accountId, amount});

        Exception e = response.getException();
        if (e != null) {
            if (e instanceof InvalidParamException) {
                throw (InvalidParamException) e;
            } else if (e instanceof OverdraftException) {
                throw (OverdraftException) e;
            } else {
                throw new RuntimeException("Remote Exception", e);
            }
        }
    }

    @Override
    public double getBalance(String accountId) throws InvalidParamException {
        MethodResponse response = this.stub.sendMethodRequest("getBalance",
                new Class[]{String.class},
                new Object[]{accountId});

        Exception e = response.getException();
        if (e != null) {
            if (e instanceof InvalidParamException) {
                throw (InvalidParamException) e;
            } else {
                throw new RuntimeException("Remote Exception", e);
            }
        }

        return Double.parseDouble(response.getPayload().toString());
    }
}
