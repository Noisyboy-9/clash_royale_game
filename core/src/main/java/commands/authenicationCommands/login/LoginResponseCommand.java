package commands.authenicationCommands.login;

/**
 * The type Login response command.
 */
public class LoginResponseCommand extends LoginCommand {
    private boolean successful;
    private String message;

    /**
     * Instantiates a new Login response command.
     *
     * @param username the username
     * @param password the password
     */
    public LoginResponseCommand(String username, String password) {
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
