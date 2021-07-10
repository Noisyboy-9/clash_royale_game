package cards.troops.barbarians;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import user.User;

import java.util.UUID;

/**
 * The type Barbarian.
 */
public class Barbarian extends Troop {
    /**
     * Instantiates a new Barbarian.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Barbarian(UUID id, int cost, User owner, Position position, int HP, int damage) {
        super(id, cost, owner, position, HP, damage, MovementSpeedEnum.MEDIUM, 1, 1, 1.5);
    }
}
