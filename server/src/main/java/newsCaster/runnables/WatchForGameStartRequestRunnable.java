package newsCaster.runnables;

import commands.matchRequestCommands.MatchRequestCommand;
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
        while (true) {
            try {
                MatchRequestCommand command = (MatchRequestCommand) this.player.getRequest().readObject();

                if (command.isFourPlayerMatchRequestCommand()) {
                    NewsCaster.getSingletonInstance().addFourPlayerModeRequester(this.player);
                }

                if (command.isTwoPlayerMatchRequestCommand()) {
                    NewsCaster.getSingletonInstance().addTwoPlayerModeRequester(this.player);
                }
            } catch (IOException | ClassNotFoundException | DuplicateGameRequestException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
