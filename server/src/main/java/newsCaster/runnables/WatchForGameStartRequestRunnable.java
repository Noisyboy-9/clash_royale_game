package newsCaster.runnables;

import commands.matchRequestCommands.FourPlayerMatchRequesterCommand;
import commands.matchRequestCommands.MatchRequestCommand;
import commands.matchRequestCommands.TwoPlayerMatchRequestCommand;
import exceptions.DuplicateGameRequestException;
import newsCaster.NewsCaster;
import workers.PlayerWorker;

import java.io.IOException;

public class WatchForGameStartRequestRunnable implements Runnable {
    private final PlayerWorker player;

    public WatchForGameStartRequestRunnable(PlayerWorker player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            MatchRequestCommand command = (MatchRequestCommand) this.player.getRequest().readObject();

            if (command instanceof FourPlayerMatchRequesterCommand) {
                NewsCaster.getSingletonInstance().addFourPlayerModeRequester(this.player);
            }

            if (command instanceof TwoPlayerMatchRequestCommand) {
                NewsCaster.getSingletonInstance().addTwoPlayerModeRequester(this.player);
            }
        } catch (IOException | ClassNotFoundException | DuplicateGameRequestException ioException) {
            ioException.printStackTrace();
        }
    }
}
