package commands.gameStateCommands.towerCommands;

import javafx.event.Event;
import towers.Tower;

/**
 * The type Tower destroyed command.
 */
public class TowerDestroyedCommand extends TowerCommand {
    private final Tower tower;

    /**
     * Instantiates a new Tower destroyed command.
     *
     * @param tower the tower
     */
    public TowerDestroyedCommand(Tower tower) {
        this.tower = tower;
    }

    /**
     * Gets tower.
     *
     * @return the tower
     */
    public Tower getTower() {
        return tower;
    }
}
