package exceptions;

public class DuplicateGameRequestException extends Exception {
    public DuplicateGameRequestException(String message) {
        super(message);
    }
}
