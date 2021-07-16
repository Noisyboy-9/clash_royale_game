package commands.authenicationCommands.login;

import commands.authenicationCommands.AuthenticationCommand;
import user.User;

/**
 * The type Login command.
 */
public class LoginCommand extends AuthenticationCommand {
    /**
     * Instantiates a new Login command.
     *
     * @param user the user
     */
    public LoginCommand(User user) {
        super(user);
    }
}
