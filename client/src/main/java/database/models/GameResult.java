package database.models;

import user.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Game result.
 */
public class GameResult implements Serializable {
    private final ArrayList<User> winners;
    private final ArrayList<User> losers;
    private final int winnersCrownCount;
    private final int losersCrownCount;

    /**
     * Instantiates a new Game result.
     *
     * @param winners           the winners
     * @param losers            the losers
     * @param winnersCrownCount the winners crown count
     * @param losersCrownCount  the losers crown count
     */
    public GameResult(ArrayList<User> winners, ArrayList<User> losers, int winnersCrownCount, int losersCrownCount) {
        this.winners = winners;
        this.losers = losers;
        this.winnersCrownCount = winnersCrownCount;
        this.losersCrownCount = losersCrownCount;
    }

    /**
     * Gets winners.
     *
     * @return the winners
     */
    public ArrayList<User> getWinners() {
        return winners;
    }

    /**
     * Gets losers.
     *
     * @return the losers
     */
    public ArrayList<User> getLosers() {
        return losers;
    }

    /**
     * Gets winners crown count.
     *
     * @return the winners crown count
     */
    public int getWinnersCrownCount() {
        return winnersCrownCount;
    }

    /**
     * Gets losers crown count.
     *
     * @return the losers crown count
     */
    public int getLosersCrownCount() {
        return losersCrownCount;
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
