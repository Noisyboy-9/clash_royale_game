package commands.traits;

import commands.playerStateCommands.PlayerLevelUpCommand;
import commands.playerStateCommands.PlayerStateCommand;

public interface CanKnowItsExactPlayerStateCommandTrait {
    default boolean isPlayerStateCommand() {
        return this instanceof PlayerStateCommand;
    }

    default boolean isPlayerLevelUpCommand() {
        return this instanceof PlayerLevelUpCommand;
    }
}
