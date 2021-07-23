package commands.gameStateCommands.gameTimeCommands;

import commands.gameStateCommands.enums.GameTypeEnum;
import user.User;

import java.util.ArrayList;

/**
 * The type Game finished command.
 */
public class GameFinishedCommand extends GameTimeCommand {
    private final ArrayList<User> winners;
    private final ArrayList<User> losers;

    /**
     * Instantiates a new Game time command.
     *
     * @param gameType the game type
     * @param winners  the winners
     * @param losers   the losers
     */
    public GameFinishedCommand(GameTypeEnum gameType, ArrayList<User> winners, ArrayList<User> losers) {
        super(gameType);
        this.winners = winners;
        this.losers = losers;
    }


    /**
     * Is winner boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean isWinner(User user) {
        return this.winners.contains(user);
    }

    /**
     * Is loser boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean isLoser(User user) {
        return this.losers.contains(user);
    }
}
