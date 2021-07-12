package commands.Authentication.register;

/**
 * The type Register response command.
 */
public class RegisterResponseCommand extends RegisterCommand {
    private boolean successful;
    private String message;

    /**
     * Instantiates a new Register response command.
     *
     * @param email    the email
     * @param password the password
     */
    public RegisterResponseCommand(String email, String password) {
        super(email, password);
    }

    /**
     * Is successful boolean.
     *
     * @return the boolean
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
