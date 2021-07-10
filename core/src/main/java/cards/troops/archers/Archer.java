package cards.troops.archers;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;

public class Archer extends Troop {
    public Archer(int HP, int damage) {
        super(HP, damage, MovementSpeedEnum.FAST, 1, 1);
    }
}
