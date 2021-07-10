package cards.troops.archers;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;

/**
 * The type Archer.
 */
public class Archer extends Troop {
    /**
     * Instantiates a new Archer.
     *
     * @param HP     the hp
     * @param damage the damage
     */
    public Archer(int HP, int damage) {
        super(cost, HP, damage, MovementSpeedEnum.FAST, 1, 1, 1.2);
    }
}
