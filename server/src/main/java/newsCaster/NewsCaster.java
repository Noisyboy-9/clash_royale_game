package newsCaster;

import exceptions.DuplicateGameRequestException;
import newsCaster.runnables.WatchForGameStartRequestRunnable;
import workers.PlayerWorker;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type News caster.
 */
public class NewsCaster {
    private static NewsCaster singletonInstance = null;
    private final ArrayList<PlayerWorker> onlinePlayers = new ArrayList<>();
    private final ArrayList<PlayerWorker> twoPlayerModeGameRequesters = new ArrayList<>();
    private final ArrayList<PlayerWorker> fourPlayerModeGameRequesters = new ArrayList<>();

    private NewsCaster() {

    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addOnlinePlayer(PlayerWorker player) {
        if (!this.onlinePlayers.contains(player)) {
            this.onlinePlayers.add(player);
            new Thread(new WatchForGameStartRequestRunnable(player)).start();
        }
    }

    /**
     * Add two player mode requester.
     *
     * @param worker the worker
     * @throws DuplicateGameRequestException the duplicate game request exception
     */
    public void addTwoPlayerModeRequester(PlayerWorker worker) throws DuplicateGameRequestException {
        if (!this.twoPlayerModeGameRequesters.contains(worker)) {
            throw new DuplicateGameRequestException("player has already requested for an two player match, can't request again");
        }

        if (!this.fourPlayerModeGameRequesters.contains(worker)) {
            throw new DuplicateGameRequestException("Player has already requested for an four player match, can't request for another match");
        }

        this.twoPlayerModeGameRequesters.add(worker);

        if (this.twoPlayerModeGameRequesters.size() == 2) {
            this.startTwoPlayerMode();
        }
    }

    /**
     * Add four player mode requester.
     *
     * @param worker the worker
     * @throws DuplicateGameRequestException the duplicate game request exception
     */
    public void addFourPlayerModeRequester(PlayerWorker worker) throws DuplicateGameRequestException {
        if (!this.twoPlayerModeGameRequesters.contains(worker)) {
            throw new DuplicateGameRequestException("player has already requested for an two player match, can't request again");
        }

        if (!this.fourPlayerModeGameRequesters.contains(worker)) {
            throw new DuplicateGameRequestException("Player has already requested for an four player match, can't request for another match");
        }

        this.fourPlayerModeGameRequesters.add(worker);

        if (this.fourPlayerModeGameRequesters.size() == 4) {
            this.startFourPlayerMode();
        }
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
     * Start two player mode.
     */
    private void startTwoPlayerMode() {
        (new NewsRedirector(this.twoPlayerModeGameRequesters.get(0), this.twoPlayerModeGameRequesters.get(1))).start();
    }

    /**
     * Start four player mode.
     */
    private void startFourPlayerMode() {
        (new NewsRedirector(this.fourPlayerModeGameRequesters.subList(0, 1), this.fourPlayerModeGameRequesters.subList(2, 3))).start();
    }
}
