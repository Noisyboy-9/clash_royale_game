package newsCaster;

import exceptions.LowPlayerCountException;
import exceptions.invalidPlayerArgumentException;
import workers.PlayerWorker;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type News caster.
 */
public class NewsCaster {
    private static NewsCaster singletonInstance = null;
    private final ArrayList<PlayerWorker> onlinePlayers = new ArrayList<>();
    private final ArrayList<PlayerWorker> readyPlayers = new ArrayList<>();

    private NewsCaster() {

    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static NewsCaster getSingletonInstance() {
        if (Objects.isNull(singletonInstance)) singletonInstance = new NewsCaster();
        return singletonInstance;
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addOnlinePlayer(PlayerWorker player) {
        if (!this.onlinePlayers.contains(player)) {
            this.onlinePlayers.add(player);
        }
    }

    public void makePlayerReady(PlayerWorker player) throws invalidPlayerArgumentException {
        if (!this.onlinePlayers.contains(player)) {
            throw new invalidPlayerArgumentException("Player is not online, can't add it to ready players");
        }

        this.onlinePlayers.remove(player);
        this.readyPlayers.add(player);
    }

    /**
     * Start two player mode.
     *
     * @throws LowPlayerCountException the low player count exception
     */
    public void startTwoPlayerMode() throws LowPlayerCountException {
        if (this.onlinePlayers.size() < 2) {
            throw new LowPlayerCountException("Online players count are lower than 2, can't start a game");
        }

        ArrayList<PlayerWorker> gamePlayers = new ArrayList<>(); //players who will play in this round of game.
        gamePlayers.add(this.onlinePlayers.get(0));
        gamePlayers.add(this.onlinePlayers.get(1));

        new NewsRedirector(gamePlayers).start();
    }

    /**
     * Start four player mode.
     *
     * @throws LowPlayerCountException the low player count exception
     */
    public void startFourPlayerMode() throws LowPlayerCountException {
        if (this.onlinePlayers.size() < 4) {
            throw new LowPlayerCountException("Online players count are lower than 4, can't start a game");
        }

        ArrayList<PlayerWorker> gamePlayers = new ArrayList<>(); //players who will play in this round of game.
        gamePlayers.add(this.onlinePlayers.get(0));
        gamePlayers.add(this.onlinePlayers.get(1));
        gamePlayers.add(this.onlinePlayers.get(2));
        gamePlayers.add(this.onlinePlayers.get(3));

        new NewsRedirector(gamePlayers).start();
    }
}
