package commands.authenicationCommands.register;

/**
 * The type Register response command.
 */
public class RegisterResponseCommand extends RegisterCommand {
    private boolean successful;
    private String message;

    /**
     * Instantiates a new Register response command.
     *
     * @param username the username
     * @param password the password
     */
    public RegisterResponseCommand(String username, String password) {
        super(username, password);
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
