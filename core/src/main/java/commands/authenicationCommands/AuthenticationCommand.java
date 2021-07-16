package commands.authenicationCommands;

import commands.Command;
import user.User;

/**
 * The type Authentication command.
 */
public abstract class AuthenticationCommand extends Command {
    private User user;

    /**
     * Instantiates a new Authentication command.
     *
     * @param user the user
     */
    public AuthenticationCommand(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
}
