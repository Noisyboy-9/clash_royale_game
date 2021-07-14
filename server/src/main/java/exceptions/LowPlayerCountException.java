package exceptions;

public class LowPlayerCountException extends Exception {
    public LowPlayerCountException(String message) {
        super(message);
    }
}
