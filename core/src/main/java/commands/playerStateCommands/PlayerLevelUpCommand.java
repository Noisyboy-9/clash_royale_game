package commands.playerStateCommands;

import user.User;
import user.UserLevelEnum;

/**
 * The type Player level up command.
 */
public class PlayerLevelUpCommand extends PlayerStateCommand {
    private User user;
    private UserLevelEnum newLevel;

    /**
     * Instantiates a new Player level up command.
     *
     * @param user  the user
     * @param level the level
     */
    public PlayerLevelUpCommand(User user, UserLevelEnum level) {
        this.user = user;
        this.newLevel = level;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets new level.
     *
     * @return the new level
     */
    public UserLevelEnum getNewLevel() {
        return newLevel;
    }
}
