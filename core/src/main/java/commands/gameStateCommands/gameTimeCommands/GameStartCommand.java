package commands.gameStateCommands.gameTimeCommands;

import commands.gameStateCommands.enums.GameTypeEnum;
import user.User;

import java.util.ArrayList;

/**
 * The type Game start command.
 */
public class GameStartCommand extends GameTimeCommand {
    private final ArrayList<User> users;

    /**
     * Instantiates a new Game time command.
     *
     * @param gameType the game type
     * @param users    the users
     */
    public GameStartCommand(GameTypeEnum gameType, ArrayList<User> users) {
        super(gameType);
        this.users = users;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
