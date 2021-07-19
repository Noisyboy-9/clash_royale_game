package commands.gameStateCommands.troopCommands;

import cards.troops.Troop;

/**
 * The type Troop killed command.
 */
public class TroopKilledCommand extends TroopCommand {
    private final Troop troop;

    /**
     * Instantiates a new Troop killed command.
     *
     * @param troop the troop
     */
    public TroopKilledCommand(Troop troop) {
        this.troop = troop;
    }

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public Troop getTroop() {
        return troop;
    }
}
