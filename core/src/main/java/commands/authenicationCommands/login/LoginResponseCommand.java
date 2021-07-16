package commands.authenicationCommands.login;

import user.User;

/**
 * The type Login response command.
 */
public class LoginResponseCommand extends LoginCommand {
    private final boolean successful;
    private final String message;

    /**
     * Instantiates a new Login response command.
     *
     * @param user       the user
     * @param successful the successful
     * @param message    the message
     */
    public LoginResponseCommand(User user, boolean successful, String message) {
        super(user);
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
