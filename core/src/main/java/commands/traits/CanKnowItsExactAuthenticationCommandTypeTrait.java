package commands.traits;

import commands.authenicationCommands.AuthenticationCommand;
import commands.authenicationCommands.login.LoginCommand;
import commands.authenicationCommands.login.LoginResponseCommand;
import commands.authenicationCommands.register.RegisterResponseCommand;

public interface CanKnowItsExactAuthenticationCommandTypeTrait {
    default boolean isAuthenticationCommand() {
        return this instanceof AuthenticationCommand;
    }

    default boolean isRegisterCommand() {
        return this instanceof RegisterResponseCommand;
    }

    default boolean isLoginCommand() {
        return this instanceof LoginCommand;
    }

    default boolean isRegisterResponseCommand() {
        return this instanceof RegisterResponseCommand;
    }

    default boolean isLoginResponseCommand() {
        return this instanceof LoginResponseCommand;
    }
}
