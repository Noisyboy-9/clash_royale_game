package exceptions;

/**
 * The type Target already exist exception.
 */
public class TargetAlreadyExistException extends Exception{
    /**
     * Instantiates a new Target already exist exception.
     *
     * @param message the message
     */
    public TargetAlreadyExistException(String message) {
        super(message);
    }
}
