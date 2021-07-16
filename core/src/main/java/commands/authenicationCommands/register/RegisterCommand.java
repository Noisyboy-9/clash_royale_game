package commands.authenicationCommands.register;

import commands.authenicationCommands.AuthenticationCommand;
import user.User;

/**
 * The type Register command.
 */
public class RegisterCommand extends AuthenticationCommand {
    /**
     * Instantiates a new Register command.
     *
     * @param user the user
     */
    public RegisterCommand(User user) {
        super(user);
    }
}
