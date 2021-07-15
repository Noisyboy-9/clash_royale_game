package newsCaster;

import commands.Command;
import commands.gameStateCommands.enums.GameTypeEnum;
import commands.gameStateCommands.gameTimeCommands.GameStartCommand;
import newsCaster.runnables.PlayerCommandReceiverRunnable;
import user.User;
import workers.PlayerWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type News redirector.
 */
public class NewsRedirector {
    private final ArrayList<PlayerWorker> players;

    /**
     * Instantiates a new News redirector.
     *
     * @param players the players
     */
    public NewsRedirector(ArrayList<PlayerWorker> players) {
        this.players = players;
    }

    /**
     * Start.
     */
    public void start() {
        ArrayList<User> users = this.players
                .stream()
                .map(PlayerWorker::getUserData)
                .collect(Collectors.toCollection(ArrayList::new));

        try {
            this.broadcastCommand(new GameStartCommand(GameTypeEnum.TWO_PLAYER_MODE, users));
            this.startGameLoop();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void broadcastCommand(Command command) throws IOException {
        for (PlayerWorker worker : this.players) {
            worker.getResponse().writeObject(command);
        }
    }

    public void broadcastCommand(Command command, PlayerWorker sender) throws IOException {
        for (PlayerWorker worker : this.players) {
            if (worker.equals(sender)) {
//                don't send the command to the player who is the initiator of the command
                continue;
            }
            worker.getResponse().writeObject(command);
        }
    }

    private void startGameLoop() {
        for (PlayerWorker worker : this.players) {
            new Thread(new PlayerCommandReceiverRunnable(worker, this)).start();
        }
    }
}
