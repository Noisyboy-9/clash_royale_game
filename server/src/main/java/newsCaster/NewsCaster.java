package newsCaster;

import players.PlayerWorker;

import java.util.ArrayList;
import java.util.Objects;

public class NewsCaster {
    private static NewsCaster singletonInstance = null;
    private final ArrayList<PlayerWorker> players = new ArrayList<>();

    private NewsCaster() {

    }

    public static NewsCaster getSingletonInstance() {
        if (Objects.isNull(singletonInstance)) singletonInstance = new NewsCaster();
        return singletonInstance;
    }

    public void addPlayer(PlayerWorker player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }
}
