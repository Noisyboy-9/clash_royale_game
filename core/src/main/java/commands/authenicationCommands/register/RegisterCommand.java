package commands.authenicationCommands.register;

import commands.authenicationCommands.AuthenticationCommand;

/**
 * The type Register command.
 */
public class RegisterCommand extends AuthenticationCommand {
    private final String username;
    private final String password;

    /**
     * Instantiates a new Register command.
     *
     * @param username the email
     * @param password the password
     */
    public RegisterCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
