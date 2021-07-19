package commands.gameStateCommands.gameBonusCommands;

import user.User;

import java.util.ArrayList;

/**
 * The type Crown count change command.
 */
public class CrownCountChangeCommand extends GameBonusCommands {
    private final ArrayList<User> targets;
    private final int crownCount;

    /**
     * Instantiates a new Crown count change command.
     *
     * @param targets    the user
     * @param crownCount the crown count
     */
    public CrownCountChangeCommand(ArrayList<User> targets, int crownCount) {
        this.targets = targets;
        this.crownCount = crownCount;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public ArrayList<User> getTargets() {
        return targets;
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
