package newsCaster.runnables;

import commands.Command;
import commands.gameStateCommands.gameTimeCommands.GameDurationFinished;
import commands.gameStateCommands.gameTimeCommands.GameFinishedCommand;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;
import newsCaster.NewsRedirector;
import towers.KingTower;
import workers.PlayerWorker;

import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerCommandReceiverRunnable implements Runnable {
    private final PlayerWorker worker;
    private final NewsRedirector newsRedirector;

    public PlayerCommandReceiverRunnable(PlayerWorker worker, NewsRedirector newsRedirector) {
        this.worker = worker;
        this.newsRedirector = newsRedirector;
    }

    @Override
    public void run() {
        ObjectInputStream request = worker.getRequest();

        try {
            while (true) {
                Command command = (Command) request.readObject();
                this.newsRedirector.broadcastCommand(command, this.worker);

                if (this.isGameFinished(command)) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }

    private boolean isGameFinished(Command command) {
        return command instanceof GameFinishedCommand ||
                command instanceof GameDurationFinished ||
                (command instanceof TowerDestroyedCommand && ((TowerDestroyedCommand) command).getTower() instanceof KingTower);
    }
}
