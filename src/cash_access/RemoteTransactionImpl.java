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

        Throwable t = response.getThrowable();
        if (t != null) {
            if (InvalidParamException.check(t)) {
                throw new InvalidParamException(t.getMessage());
            } else {
                throw new RuntimeException("Remote Exception", t);
            }
        }
    }

    @Override
    public void withdraw(String accountId, double amount) throws InvalidParamException, OverdraftException {
        MethodResponse response = this.stub.sendMethodRequest("withdraw",
                new Class[]{String.class, double.class},
                new Object[]{accountId, amount});

        Throwable t = response.getThrowable();
        if (t != null) {
            if (InvalidParamException.check(t)) {
                throw new InvalidParamException(t.getMessage());
            } else if (OverdraftException.check(t)) {
                throw new OverdraftException(t.getMessage());
            } else {
                throw new RuntimeException("Remote Exception", t);
            }
        }
    }

    @Override
    public double getBalance(String accountId) throws InvalidParamException {
        MethodResponse response = this.stub.sendMethodRequest("getBalance",
                new Class[]{String.class},
                new Object[]{accountId});

        Throwable t = response.getThrowable();
        if (t != null) {
            if (InvalidParamException.check(t)) {
                throw new InvalidParamException(t.getMessage());
            } else {
                throw new RuntimeException("Remote Exception", t);
            }
        }

        return Double.parseDouble(response.getPayload().toString());
    }
}
