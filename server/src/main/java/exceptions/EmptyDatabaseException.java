package exceptions;

public class EmptyDatabaseException extends Exception {
    public EmptyDatabaseException(String message) {
        super(message);
    }
}
