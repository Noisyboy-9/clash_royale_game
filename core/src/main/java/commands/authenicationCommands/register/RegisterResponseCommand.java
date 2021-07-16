package commands.authenicationCommands.register;

import user.User;

/**
 * The type Register response command.
 */
public class RegisterResponseCommand extends RegisterCommand {
    private final boolean successful;
    private final String message;

    /**
     * Instantiates a new Register response command.
     *
     * @param user       the user
     * @param successful the successful
     * @param message    the message
     */
    public RegisterResponseCommand(User user, boolean successful, String message) {
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
