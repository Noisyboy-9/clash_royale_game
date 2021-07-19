package commands.gameStateCommands.troopCommands;

import cards.troops.TroopTraitCard;

/**
 * The type Troop killed command.
 */
public class TroopKilledCommand extends TroopCommand {
    private final TroopTraitCard troop;

    /**
     * Instantiates a new Troop killed command.
     *
     * @param troop the troop
     */
    public TroopKilledCommand(TroopTraitCard troop) {
        this.troop = troop;
    }

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public TroopTraitCard getTroop() {
        return troop;
    }
}
