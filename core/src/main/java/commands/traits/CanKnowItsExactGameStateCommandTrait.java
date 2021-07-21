package commands.traits;

import commands.gameStateCommands.GameStateCommand;
import commands.gameStateCommands.cardCommands.CardAddedCommand;
import commands.gameStateCommands.cardCommands.CardCommand;
import commands.gameStateCommands.cardCommands.CardDeletedCommand;
import commands.gameStateCommands.gameBonusCommands.CrownCountChangeCommand;
import commands.gameStateCommands.gameBonusCommands.GameBonusCommands;
import commands.gameStateCommands.gameTimeCommands.GameDurationFinished;
import commands.gameStateCommands.gameTimeCommands.GameFinishedCommand;
import commands.gameStateCommands.gameTimeCommands.GameStartCommand;
import commands.gameStateCommands.gameTimeCommands.GameTimeCommand;
import commands.gameStateCommands.towerCommands.TowerActiveCommand;
import commands.gameStateCommands.towerCommands.TowerCommand;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;

public interface CanKnowItsExactGameStateCommandTrait {
    default boolean isGameStateCommand() {
        return this instanceof GameStateCommand;
    }

    default boolean isCardCommand() {
        return this instanceof CardCommand;
    }

    default boolean isCardAddedCommand() {
        return this instanceof CardAddedCommand;
    }

    default boolean isCardDeletedCommand() {
        return this instanceof CardDeletedCommand;
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

    default boolean isTowerActiveCommand() {
        return this instanceof TowerActiveCommand;
    }

    default boolean isTowerCommand() {
        return this instanceof TowerCommand;
    }

    default boolean isTowerDestroyedCommand() {
        return this instanceof TowerDestroyedCommand;
    }

}
