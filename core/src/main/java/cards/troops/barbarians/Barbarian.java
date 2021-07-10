package cards.troops.barbarians;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;

/**
 * The type Barbarian.
 */
public class Barbarian extends Troop {
    /**
     * Instantiates a new Barbarian.
     *
     * @param HP     the hp
     * @param damage the damage
     */
    public Barbarian(int HP, int damage) {
        super(cost, HP, damage, MovementSpeedEnum.MEDIUM, 1, 1, 1.5);
    }
}
