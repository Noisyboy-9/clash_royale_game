package commands.traits;

import commands.gameStateCommands.GameStateCommand;
import commands.gameStateCommands.gameBonusCommands.CrownCountChangeCommand;
import commands.gameStateCommands.gameBonusCommands.GameBonusCommands;
import commands.gameStateCommands.gameTimeCommands.GameDurationFinished;
import commands.gameStateCommands.gameTimeCommands.GameFinishedCommand;
import commands.gameStateCommands.gameTimeCommands.GameStartCommand;
import commands.gameStateCommands.gameTimeCommands.GameTimeCommand;
import commands.gameStateCommands.spellCommands.SpellAddedCommand;
import commands.gameStateCommands.spellCommands.SpellCommand;
import commands.gameStateCommands.spellCommands.SpellDurationFinishedCommand;
import commands.gameStateCommands.towerCommands.TowerActiveCommand;
import commands.gameStateCommands.towerCommands.TowerCommand;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;
import commands.gameStateCommands.troopCommands.TroopAddedCommand;
import commands.gameStateCommands.troopCommands.TroopCommand;
import commands.gameStateCommands.troopCommands.TroopKilledCommand;

public interface CanKnowItsExactGameStateCommandTrait {
    default boolean isGameStateCommand() {
        return this instanceof GameStateCommand;
    }

    default boolean isTroopCommand() {
        return this instanceof TroopCommand;
    }

    default boolean isTroopAddedCommand() {
        return this instanceof TroopAddedCommand;
    }

    default boolean isTroopKilledCommand() {
        return this instanceof TroopKilledCommand;
    }

    default boolean isTowerCommand() {
        return this instanceof TowerCommand;
    }

    default boolean isTowerActiveCommand() {
        return this instanceof TowerActiveCommand;
    }

    default boolean isTowerDestroyedCommand() {
        return this instanceof TowerDestroyedCommand;
    }

    default boolean isSpellCommand() {
        return this instanceof SpellCommand;
    }

    default boolean isSpellAddedCommand() {
        return this instanceof SpellAddedCommand;
    }

    default boolean isSpellDurationFinishedCommand() {
        return this instanceof SpellDurationFinishedCommand;
    }

    default boolean isGameTimeCommand() {
        return this instanceof GameTimeCommand;
    }

    default boolean isGameStartCommand() {
        return this instanceof GameStartCommand;
    }

    default boolean isGameFinishedCommand() {
        return this instanceof GameFinishedCommand;
    }

    default boolean isGameDurationFinishedCommand() {
        return this instanceof GameDurationFinished;
    }

    default boolean isGameBonusCommand() {
        return this instanceof GameBonusCommands;
    }

    default boolean isCrownCountChangeCommand() {
        return this instanceof CrownCountChangeCommand;
    }
}
