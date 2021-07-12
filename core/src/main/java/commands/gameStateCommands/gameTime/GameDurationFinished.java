package commands.gameStateCommands.gameTime;

import commands.gameStateCommands.enums.GameTypeEnum;
import user.User;

import java.util.ArrayList;

/**
 * The type Game duration finished.
 */
public class GameDurationFinished extends GameTimeCommand {
    private final ArrayList<User> winners;
    private final ArrayList<User> losers;

    /**
     * Instantiates a new Game time command.
     *
     * @param gameType the game type
     */
    public GameDurationFinished(GameTypeEnum gameType, ArrayList<User> winners, ArrayList<User> losers) {
        super(gameType);
        this.winners = winners;
        this.losers = losers;
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

    /**
     * Is winner boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean isWinner(User user) {
        return this.winners.contains(user);
    }
}
