package commands.authenicationCommands.login;

/**
 * The type Login response command.
 */
public class LoginResponseCommand extends LoginCommand {
    private final boolean successful;
    private final String message;

    /**
     * Instantiates a new Login response command.
     *
     * @param username the username
     * @param password the password
     */
    public LoginResponseCommand(String username, String password, boolean successful, String message) {
        super(username, password);
        this.successful = successful;
        this.message = message;
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
