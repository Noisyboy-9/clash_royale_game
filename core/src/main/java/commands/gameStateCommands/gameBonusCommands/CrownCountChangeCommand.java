package commands.gameStateCommands.gameBonusCommands;

import user.User;

/**
 * The type Crown count change command.
 */
public class CrownCountChangeCommand extends GameBonusCommands {
    private final User user;
    private final int crownCount;

    /**
     * Instantiates a new Crown count change command.
     *
     * @param user       the user
     * @param crownCount the crown count
     */
    public CrownCountChangeCommand(User user, int crownCount) {
        this.user = user;
        this.crownCount = crownCount;
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
     * Gets crown count.
     *
     * @return the crown count
     */
    public int getCrownCount() {
        return crownCount;
    }
}
