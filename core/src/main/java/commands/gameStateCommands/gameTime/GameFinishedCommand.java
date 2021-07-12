package commands.gameStateCommands.gameTime;

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
     */
    public GameFinishedCommand(GameTypeEnum gameType) {
        super(gameType);
        this.losers = new ArrayList<>();
        this.winners = new ArrayList<>();
    }


    /**
     * Add winner.
     *
     * @param user the user
     */
    public void addWinner(User user) {
        if (!this.winners.contains(user)) {
            this.winners.add(user);
        }
    }

    /**
     * Add loser.
     *
     * @param user the user
     */
    public void addLoser(User user) {
        if (this.losers.contains(user)) {
            this.losers.add(user);
        }
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
