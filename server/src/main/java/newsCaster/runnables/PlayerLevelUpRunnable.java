package newsCaster.runnables;

import commands.playerStateCommands.PlayerLevelUpCommand;
import database.QueryBuilder;
import workers.PlayerWorker;

import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerLevelUpRunnable implements Runnable {
    private final PlayerWorker player;

    public PlayerLevelUpRunnable(PlayerWorker player) {
        this.player = player;
    }

    @Override
    public void run() {
        while (true) {
            ObjectInputStream request = this.player.getRequest();
            try {
                PlayerLevelUpCommand command = (PlayerLevelUpCommand) request.readObject();

                QueryBuilder.getSingletonInstance().levelUpUser(command.getUser(), command.getNewLevel());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
