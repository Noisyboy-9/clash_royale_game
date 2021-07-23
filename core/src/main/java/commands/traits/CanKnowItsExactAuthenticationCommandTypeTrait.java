package commands.traits;

import commands.authenicationCommands.AuthenticationCommand;
import commands.authenicationCommands.login.LoginCommand;
import commands.authenicationCommands.login.LoginResponseCommand;
import commands.authenicationCommands.register.RegisterResponseCommand;

/**
 * The interface Can know its exact authentication command type trait.
 */
public interface CanKnowItsExactAuthenticationCommandTypeTrait {
    /**
     * Is authentication command boolean.
     *
     * @return the boolean
     */
    default boolean isAuthenticationCommand() {
        return this instanceof AuthenticationCommand;
    }

    /**
     * Is register command boolean.
     *
     * @return the boolean
     */
    default boolean isRegisterCommand() {
        return this instanceof RegisterResponseCommand;
    }

    /**
     * Is login command boolean.
     *
     * @return the boolean
     */
    default boolean isLoginCommand() {
        return this instanceof LoginCommand;
    }

    /**
     * Is register response command boolean.
     *
     * @return the boolean
     */
    default boolean isRegisterResponseCommand() {
        return this instanceof RegisterResponseCommand;
    }

    /**
     * Is login response command boolean.
     *
     * @return the boolean
     */
    default boolean isLoginResponseCommand() {
        return this instanceof LoginResponseCommand;
    }
}
