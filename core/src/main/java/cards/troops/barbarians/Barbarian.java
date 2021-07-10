package cards.troops.barbarians;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;

public class Barbarian extends Troop {
    public Barbarian(int HP, int damage) {
        super(HP, damage, MovementSpeedEnum.MEDIUM, 1, 1);
    }
}
