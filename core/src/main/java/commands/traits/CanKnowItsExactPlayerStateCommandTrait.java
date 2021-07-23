package commands.traits;

import commands.playerStateCommands.PlayerLevelUpCommand;
import commands.playerStateCommands.PlayerStateCommand;

/**
 * The interface Can know its exact player state command trait.
 */
public interface CanKnowItsExactPlayerStateCommandTrait {
    /**
     * Is player state command boolean.
     *
     * @return the boolean
     */
    default boolean isPlayerStateCommand() {
        return this instanceof PlayerStateCommand;
    }

    /**
     * Is player level up command boolean.
     *
     * @return the boolean
     */
    default boolean isPlayerLevelUpCommand() {
        return this instanceof PlayerLevelUpCommand;
    }
}
