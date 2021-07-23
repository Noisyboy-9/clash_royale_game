package newsCaster.runnables;

import commands.Command;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;
import newsCaster.NewsRedirector;
import towers.KingTower;
import workers.PlayerWorker;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The type Player command receiver runnable.
 */
public record PlayerCommandReceiverRunnable(PlayerWorker worker, NewsRedirector newsRedirector) implements Runnable {

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
        return command.isGameFinishedCommand() ||
                command.isGameDurationFinishedCommand() ||
                (command.isTowerDestroyedCommand() && ((TowerDestroyedCommand) command).getTower() instanceof KingTower);
    }
}
