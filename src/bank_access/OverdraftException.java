package bank_access;

public class OverdraftException extends Exception {
    public OverdraftException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "OverdraftException";
    }

    public static boolean check(Throwable t) {
        return t != null && t.toString().equals(new OverdraftException("").toString());
    }

}
