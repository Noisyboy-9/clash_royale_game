package commands.authenicationCommands.login;

import commands.authenicationCommands.AuthenticationCommand;

/**
 * The type Login command.
 */
public class LoginCommand extends AuthenticationCommand {
    private final String username;
    private final String password;

    /**
     * Instantiates a new Login command.
     *
     * @param username the email
     * @param password the password
     */
    public LoginCommand(String username, String password) {
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
