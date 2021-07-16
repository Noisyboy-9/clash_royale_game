package exceptions;

public class AlreadyConnectedToServerException extends Exception {
    public AlreadyConnectedToServerException(String message) {
        super(message);
    }
}
