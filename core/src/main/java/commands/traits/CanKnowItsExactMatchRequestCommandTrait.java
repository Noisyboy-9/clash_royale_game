package commands.traits;

import commands.matchRequestCommands.FourPlayerMatchRequesterCommand;
import commands.matchRequestCommands.MatchRequestCommand;
import commands.matchRequestCommands.TwoPlayerMatchRequestCommand;

public interface CanKnowItsExactMatchRequestCommandTrait {
    default boolean isMatchRequestCommand() {
        return this instanceof MatchRequestCommand;
    }

    default boolean isTwoPlayerMatchRequestCommand() {
        return this instanceof TwoPlayerMatchRequestCommand;
    }

    default boolean isFourPlayerMatchRequestCommand() {
        return this instanceof FourPlayerMatchRequesterCommand;
    }
}
