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

/**
 * The interface Can know its exact game state command trait.
 */
public interface CanKnowItsExactGameStateCommandTrait {
    /**
     * Is game state command boolean.
     *
     * @return the boolean
     */
    default boolean isGameStateCommand() {
        return this instanceof GameStateCommand;
    }

    /**
     * Is card command boolean.
     *
     * @return the boolean
     */
    default boolean isCardCommand() {
        return this instanceof CardCommand;
    }

    /**
     * Is card added command boolean.
     *
     * @return the boolean
     */
    default boolean isCardAddedCommand() {
        return this instanceof CardAddedCommand;
    }

    /**
     * Is card deleted command boolean.
     *
     * @return the boolean
     */
    default boolean isCardDeletedCommand() {
        return this instanceof CardDeletedCommand;
    }

    /**
     * Is game time command boolean.
     *
     * @return the boolean
     */
    default boolean isGameTimeCommand() {
        return this instanceof GameTimeCommand;
    }

    /**
     * Is game start command boolean.
     *
     * @return the boolean
     */
    default boolean isGameStartCommand() {
        return this instanceof GameStartCommand;
    }

    /**
     * Is game finished command boolean.
     *
     * @return the boolean
     */
    default boolean isGameFinishedCommand() {
        return this instanceof GameFinishedCommand;
    }

    /**
     * Is game duration finished command boolean.
     *
     * @return the boolean
     */
    default boolean isGameDurationFinishedCommand() {
        return this instanceof GameDurationFinished;
    }

    /**
     * Is game bonus command boolean.
     *
     * @return the boolean
     */
    default boolean isGameBonusCommand() {
        return this instanceof GameBonusCommands;
    }

    /**
     * Is crown count change command boolean.
     *
     * @return the boolean
     */
    default boolean isCrownCountChangeCommand() {
        return this instanceof CrownCountChangeCommand;
    }

    /**
     * Is tower active command boolean.
     *
     * @return the boolean
     */
    default boolean isTowerActiveCommand() {
        return this instanceof TowerActiveCommand;
    }

    /**
     * Is tower command boolean.
     *
     * @return the boolean
     */
    default boolean isTowerCommand() {
        return this instanceof TowerCommand;
    }

    /**
     * Is tower destroyed command boolean.
     *
     * @return the boolean
     */
    default boolean isTowerDestroyedCommand() {
        return this instanceof TowerDestroyedCommand;
    }

}
