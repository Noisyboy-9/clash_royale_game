package commands.traits;

import commands.matchRequestCommands.FourPlayerMatchRequesterCommand;
import commands.matchRequestCommands.MatchRequestCommand;
import commands.matchRequestCommands.TwoPlayerMatchRequestCommand;

/**
 * The interface Can know its exact match request command trait.
 */
public interface CanKnowItsExactMatchRequestCommandTrait {
    /**
     * Is match request command boolean.
     *
     * @return the boolean
     */
    default boolean isMatchRequestCommand() {
        return this instanceof MatchRequestCommand;
    }

    /**
     * Is two player match request command boolean.
     *
     * @return the boolean
     */
    default boolean isTwoPlayerMatchRequestCommand() {
        return this instanceof TwoPlayerMatchRequestCommand;
    }

    /**
     * Is four player match request command boolean.
     *
     * @return the boolean
     */
    default boolean isFourPlayerMatchRequestCommand() {
        return this instanceof FourPlayerMatchRequesterCommand;
    }
}
