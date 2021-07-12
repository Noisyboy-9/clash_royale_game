package commands.gameStateCommands.gameTimeCommands;

import commands.gameStateCommands.GameStateCommand;
import commands.gameStateCommands.enums.GameTypeEnum;

/**
 * The type Game time command.
 */
public abstract class GameTimeCommand extends GameStateCommand {
    private final GameTypeEnum gameType;

    /**
     * Instantiates a new Game time command.
     *
     * @param gameType the game type
     */
    public GameTimeCommand(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    /**
     * Gets game type.
     *
     * @return the game type
     */
    public GameTypeEnum getGameType() {
        return gameType;
    }
}
