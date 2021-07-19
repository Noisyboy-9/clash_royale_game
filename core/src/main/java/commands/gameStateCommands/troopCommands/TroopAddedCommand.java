package commands.gameStateCommands.troopCommands;

import cards.troops.TroopTrait;
import javafx.geometry.Point2D;

/**
 * The type Troop added command.
 */
public class TroopAddedCommand extends TroopCommand {
    private final TroopTrait troop;
    private final Point2D position;

    /**
     * Instantiates a new Troop added command.
     *
     * @param troop    the troop
     * @param position the position
     */
    public TroopAddedCommand(TroopTrait troop, Point2D position) {
        this.troop = troop;
        this.position = position;
    }

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public TroopTrait getTroop() {
        return troop;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }
}
