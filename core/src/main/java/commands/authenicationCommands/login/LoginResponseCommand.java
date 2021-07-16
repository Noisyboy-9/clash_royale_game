package commands.authenicationCommands.login;

import user.User;

/**
 * The type Login response command.
 */
public class LoginResponseCommand extends LoginCommand {
    private final boolean successful;
    private final String message;
    private final User user;

    /**
     * Instantiates a new Login response command.
     * for the situation that a login is successfully made.
     *
     * @param user    the user
     * @param message the message
     */
    public LoginResponseCommand(User user, String message) {
        super(user.getUsername(), user.getPassword());
        this.user = user;
        this.successful = true;
        this.message = message;
    }

    /**
     * Instantiates a new Login response command.
     * for the situation that the Login is not successful;
     *
     * @param username the username
     * @param password the password
     * @param message  the message
     */
    public LoginResponseCommand(String username, String password, String message) {
        super(username, password);
        this.message = message;
        this.user = null;
        this.successful = false;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
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
