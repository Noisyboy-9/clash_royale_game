package commands.authenicationCommands.register;

import user.User;

/**
 * The type Register response command.
 */
public class RegisterResponseCommand extends RegisterCommand {
    private final boolean successful;
    private final String message;
    private final User user;

    /**
     * Instantiates a new Register response command.
     * for the situation that an unsuccessful register has been made
     *
     * @param username the username
     * @param password the password
     * @param message  the message
     */
    public RegisterResponseCommand(String username, String password, String message) {
        super(username, password);
        this.user = null;
        this.successful = false;
        this.message = message;
    }

    /**
     * Instantiates a new Register response command.
     * for the situation that a successful register has been made.
     *
     * @param user    the user
     * @param message the message
     */
    public RegisterResponseCommand(User user, String message) {
        super(user.getUsername(), user.getPassword());
        this.user = user;
        this.successful = true;
        this.message = message;
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
