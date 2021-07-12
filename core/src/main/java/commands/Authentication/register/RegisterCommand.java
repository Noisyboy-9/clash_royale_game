package commands.Authentication.register;

import commands.Authentication.AuthenticationCommand;

/**
 * The type Register command.
 */
public class RegisterCommand extends AuthenticationCommand {
    private final String email;
    private final String password;

    /**
     * Instantiates a new Register command.
     *
     * @param email    the email
     * @param password the password
     */
    public RegisterCommand(String email, String password) {
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
