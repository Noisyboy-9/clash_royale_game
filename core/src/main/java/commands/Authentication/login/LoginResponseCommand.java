package commands.Authentication.login;

/**
 * The type Login response command.
 */
public class LoginResponseCommand extends LoginCommand {
    private boolean successful;
    private String message;

    /**
     * Instantiates a new Login response command.
     *
     * @param email    the email
     * @param password the password
     */
    public LoginResponseCommand(String email, String password) {
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
