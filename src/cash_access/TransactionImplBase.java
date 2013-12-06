package cash_access;

public abstract class TransactionImplBase {
    public abstract void deposit(String accountId, double amount)
            throws InvalidParamException;

    public abstract void withdraw(String accountId, double amount)
            throws InvalidParamException, OverdraftException;

    public abstract double getBalance(String accountId)
            throws InvalidParamException;

    public static TransactionImplBase narrowCast(Object gor) {
        return new RemoteTranscationImpl(gor);
    }
}
