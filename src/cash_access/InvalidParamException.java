package cash_access;

public class InvalidParamException extends Exception {
    public InvalidParamException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidParamException";
    }

    public static boolean check(Throwable t) {
        return t != null && t.toString().equals(new InvalidParamException("").toString());
    }
}
