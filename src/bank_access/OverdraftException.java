package bank_access;

public class OverdraftException extends Exception {
    public OverdraftException(String msg) {
        super(msg);
    }
}
