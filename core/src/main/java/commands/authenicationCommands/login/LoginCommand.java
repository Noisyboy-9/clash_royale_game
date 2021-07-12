package commands.authenicationCommands.login;

import commands.authenicationCommands.AuthenticationCommand;

/**
 * The type Login command.
 */
public class LoginCommand extends AuthenticationCommand {
    private final String email;
    private final String password;

    /**
     * Instantiates a new Login command.
     *
     * @param email    the email
     * @param password the password
     */
    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
