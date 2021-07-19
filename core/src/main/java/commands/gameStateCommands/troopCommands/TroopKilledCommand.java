package commands.gameStateCommands.troopCommands;

import cards.troops.TroopTrait;

/**
 * The type Troop killed command.
 */
public class TroopKilledCommand extends TroopCommand {
    private final TroopTrait troop;

    /**
     * Instantiates a new Troop killed command.
     *
     * @param troop the troop
     */
    public TroopKilledCommand(TroopTrait troop) {
        this.troop = troop;
    }

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public TroopTrait getTroop() {
        return troop;
    }
}
