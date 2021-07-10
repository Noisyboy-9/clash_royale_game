package cards.troops.archers;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import user.User;

import java.util.UUID;

/**
 * The type Archer.
 */
public class Archer extends Troop {
    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Archer(UUID id, User owner, Position position, int HP, int damage) {
        super(id, 3, owner, position, HP, damage, MovementSpeedEnum.MEDIUM, false, 5, 1.2);
    }
}
