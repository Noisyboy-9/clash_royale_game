package commands.gameStateCommands.troopCommands;

import cards.troops.Troop;
import cards.utils.Position;

/**
 * The type Troop added command.
 */
public class TroopAddedCommand extends TroopCommand {
    private final Troop troop;
    private final Position position;

    /**
     * Instantiates a new Troop added command.
     *
     * @param troop    the troop
     * @param position the position
     */
    public TroopAddedCommand(Troop troop, Position position) {
        this.troop = troop;
        this.position = position;
    }

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public Troop getTroop() {
        return troop;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }
}
